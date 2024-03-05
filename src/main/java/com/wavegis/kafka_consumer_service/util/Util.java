package com.wavegis.kafka_consumer_service.util;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import retrofit2.Call;
import retrofit2.Response;

public class Util {

	private static final Logger logger = LoggerFactory.getLogger(Util.class);

	public static <T> List<T> replaceNullToList(List<T> list) {
		if (list == null) {
			return Collections.emptyList();
		} else {
			return list;
		}
	}

	public static <T> T replaceNullToT(T responseBody, Class<T> clazz) {
        if (responseBody == null) {
            try {
                return clazz.getDeclaredConstructor().newInstance();
            } catch (InstantiationException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (SecurityException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        } else {
            return responseBody;
        }
    }

	public static <D, V> V toVo(D dto, V vo) {
		BeanUtils.copyProperties(dto, vo);
		return vo;
	}

    public static String apiDateToWraDate(String date) {
        return date.replaceAll("-", "/") + "+08:00";
    }

	public static Set<String> sortSetOfString(Set<String> set) {
		Set<String> sortSet = new TreeSet<String>((o1, o2) -> o1.compareTo(o2));
        sortSet.addAll(set);
		return sortSet;
	}
	
	public static Set<String> sortSetToString(String str){
	   return Arrays.stream(Optional.ofNullable(str).orElse("").split(","))
        .map(String :: trim)
        .collect(Collectors.toSet());
	}
	
	public static <T> List<T> callListApi(Call<List<T>> call, String method) {
		Response<List<T>> res = null;
		try {
			res = call.execute();
			int code = res.code();
			if (code >= 200 && code < 300) {
			    call.cancel();
				return Util.replaceNullToList(res.body());
			}else {
				logger.error("api return http code = {}, url = {}, message = {}", res.code(), call.request().url().toString(), res.errorBody().string());
				call.cancel();
				return Collections.emptyList();
			}
		} catch (IOException ioe) {
			logger.error("api throw IOException url={},exception is",call.request().url().toString(),ioe);
			return Util.reTryListApi(res, call);
		} catch (Exception ex) {
			logger.error("api throw Exception url={},exception is",call.request().url().toString(),ex);
			call.cancel();
			return Collections.emptyList();
		}
	}
	
	public static <T> List<T> reTryListApi(Response<List<T>> res, Call<List<T>> call) {
		int time = 1;
		int code = 204;
		while(time <= 10) {
			try {
				Thread.sleep(5000);
				res = call.clone().execute();
				code = res.code();
				if (code >= 200 && code < 300) {
					logger.info("time={},api execution complete",time);
					call.clone().cancel();
					return Util.replaceNullToList(res.body());
				}else {
					logger.error("time={},api return http code = {}, url = {}, message = {}",time, code, call.request().url().toString(), res.errorBody().string());
				}
			} catch (IOException ioe) {
				logger.error("time={},api throw IOException url={},exception is",time, call.request().url().toString(), ioe);
			} catch (Exception ex) {
				logger.error("time={},api throw Exception url={},exception is",time, call.request().url().toString(), ex);
                call.clone().cancel();
                return null ;
			}
			time++;
			call.clone().cancel();
		}
		return null;
	}
	
	public static <T> T callApi(Call<T> call, String method, Class<T> clazz) {
		Response<T> res = null;
		try {
			res = call.execute();
			final int code = res.code();
			if (code >= 200 && code < 300) {
			    call.cancel();
                call = null;
				return Util.replaceNullToT(res.body(), clazz);
			}else {
			    call.cancel();
				logger.error("api return http code = {}, url = {}, message = {}", res.code(), call.request().url().toString(), res.errorBody().string());
				return null ;
			}
		} catch (IOException ioe) {
			logger.error("api throw IOException url={},exception is", call.request().url().toString(), ioe);
			return Util.reTryApi(res, call, clazz);
		} catch (Exception ex) {
			call.cancel();
			logger.error("api throw Exception url={},exception is", call.request().url().toString(), ex);
			return null;
		}
	}
	
	public static <T> T reTryApi(Response<T> res, Call<T> call, Class<T> clazz) {
		int time = 1;
		int code = 204;
		while(time <= 10) {
			try {
				Thread.sleep(5000);
				res = call.clone().execute();
				code = res.code();
				if (code >= 200 && code < 300) {
					logger.info("time={},api execution complete",time);
					call.clone().cancel();
					return Util.replaceNullToT(res.body(), clazz);
				}else {
					logger.error("time={},api return http code = {}, url = {}, message = {}",time, code, call.request().url().toString(), res.errorBody().string());
				}
			} catch (IOException ioe) {
				logger.error("time={},api throw IOException url={},exception is",time,call.request().url().toString(),ioe);
			} catch (Exception ex) {
			    logger.error("time={},api throw Exception url={},exception is",time,call.request().url().toString(),ex);
				call.clone().cancel();
				return null ;
			}
			time++;
			call.clone().cancel();
		}
		return null ;
	}

	public static <T> int callApiResponseCode(Call<T> call, String method) {
        Response<T> res = null;
        int code = 204;
        try {
            res = call.execute();
            code = res.code();
            if (code == 200) {
                call.cancel();
                return code;
            }else if(code == 401){
                logger.error("api return http code = {}, url = {}, message = {}", code, call.request().url().toString(), res.errorBody().string());
                call.cancel();
                return code;
            }else {
                logger.error("api return http code = {}, url = {}, message = {}", code, call.request().url().toString(), res.errorBody().string());
                return reTryApiResponseCode(res, call, method);
            }
        } catch (IOException ioe) {
            logger.error("api throw IOException url={}",call.request().url().toString());
            return reTryApiResponseCode(res, call, method);
        } catch (Exception ex) {
            logger.error("api throw Exception url={},exception is",call.request().url().toString(),ex);
            call.cancel();
            return 501;
        }
    }
    
    public static <T> int reTryApiResponseCode(Response<T> res, Call<T> call, String method) {
        int time = 1;
        int code = 204;
        while(time <= 10) {
            try {
                Thread.sleep(1000*5);
                res = call.clone().execute();
                code = res.code();
                if (code == 200) {
                    logger.info("time={},code={}",time, code);
                    call.clone().cancel();
                    return code;
                }else {
                    logger.error("time={},api return http code = {}, url = {}, message = {}",time, code, call.request().url().toString(), res.errorBody().string());
                }
            } catch (IOException ioe) {
                logger.error("time={},api throw IOException url={}",time, call.request().url().toString());
            } catch (Exception ex) {
                logger.error("time={},api throw Exception url={},exception is",time, call.request().url().toString(),ex);
                call.clone().cancel();
                return 501;
            }
            time++;
            call.clone().cancel();
        }
        return code;
    }
}