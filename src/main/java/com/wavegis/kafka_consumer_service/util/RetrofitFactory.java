package com.wavegis.kafka_consumer_service.util;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.OkHttpClient;
import retrofit2.Converter.Factory;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.jackson.JacksonConverterFactory;
public class RetrofitFactory {
	
	public static Map<String,Retrofit> builderMap = new ConcurrentHashMap<String,Retrofit>();
	
	public static <T> T  createService(Class<T> t , String baseUrl) {
		Retrofit rf = getRetrofit(baseUrl);
		T service = rf.create((Class<T>) t);
		return service;
	}
	
	private static Retrofit getRetrofit(String baseUrl){
		ObjectMapper mapper = getObjectMapper();
		return getRetrofit(baseUrl , JacksonConverterFactory.create(mapper));
	}
	
	private static ObjectMapper getObjectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return mapper;
	}
	
	private static synchronized Retrofit getRetrofit(String baseUrl, Factory factory){
		/*
		//設定Retrofit Logger
		//Create HttpLoggingInterceptor
		HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
		logging.setLevel(HttpLoggingInterceptor.Level.BODY);
		*/

		if(builderMap.containsKey(baseUrl)){
			return builderMap.get(baseUrl);
		}else{
			
			final TrustManager[] trustAllCerts = new TrustManager[] {
                    new X509TrustManager() {

						@Override
						public void checkClientTrusted(X509Certificate[] arg0, String arg1)
								throws CertificateException {
							
						}

						@Override
						public void checkServerTrusted(X509Certificate[] arg0, String arg1)
								throws CertificateException {
							
						}

						@Override
						public X509Certificate[] getAcceptedIssuers() {
							return new X509Certificate[]{};
						}
                    }
            };
			
			SSLContext sslContext;
            final SSLSocketFactory sslSocketFactory;
            try {
            	
            	sslContext = SSLContext.getInstance("SSL");
				sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
				sslSocketFactory = sslContext.getSocketFactory();
            	
				OkHttpClient okHttpClient = new OkHttpClient.Builder()
						.sslSocketFactory(sslSocketFactory, (X509TrustManager)trustAllCerts[0])
						.hostnameVerifier(new HostnameVerifier() {
							@Override
							public boolean verify(String arg0, SSLSession arg1) {
								return true;
							}
						})
						.connectTimeout(2400, TimeUnit.SECONDS)
						.writeTimeout(2400, TimeUnit.SECONDS)
						.readTimeout(2400, TimeUnit.SECONDS)
						/*
						//設定Retrofit Logger
						.addInterceptor(logging)
						*/
						.build();
				
				Builder rf_builder = new Retrofit.Builder();
				Retrofit rf = rf_builder.baseUrl(baseUrl)
						.addConverterFactory(factory)
						.client(okHttpClient).build();
				builderMap.put(baseUrl, rf);
				return rf;
            } catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (KeyManagementException e) {
				e.printStackTrace();
			}

			return null;
		}
	}
	
}
