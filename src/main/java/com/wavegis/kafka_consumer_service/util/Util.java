package com.wavegis.kafka_consumer_service.util;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import retrofit2.Call;
import retrofit2.Response;

public class Util {

	private static final Logger logger = LoggerFactory.getLogger(Util.class);

	public final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	public static LocalDateTime localDateTime;

	private static final ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

	// 限制同時執行任務數量
	private final static Semaphore semaphore = new Semaphore(100);

    public static LocalDateTime stringToLocalDateTime(String str) {
        localDateTime = LocalDateTime.parse(str, formatter);
        return localDateTime;
    }

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
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (SecurityException e) {
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
				String message=res.body().toString();
				logger.info("message={}",message);
                call.cancel();
                return code;
            }else if(code == 401){
                logger.error("api return http code = {}, url = {}, message = {}", code, call.request().url().toString(), res.errorBody().string());
                call.cancel();
                return code;
            }else {
                logger.error("api return http code = {}, url = {}, message = {}", code, call.request().url().toString(), res.errorBody().string());
                reTryApiResponseCodeAsync(call, method);
                return 204;
            }
        } catch (IOException ioe) {
            logger.error("api throw IOException url={}",call.request().url().toString());
            reTryApiResponseCodeAsync(call, method);
            return 204;
        } catch (Exception ex) {
            logger.error("api throw Exception url={},exception is",call.request().url().toString(),ex);
            call.cancel();
            return 501;
        }
    }

    public static <T> int reTryApiResponseCode(Response<T> res, Call<T> call, String method) {
    	int code = 204;
    	// 取得 semaphore，限制同時提交的任務數量
        try {
			semaphore.acquire();
			executor.submit(() -> {
				int time = 1;
				int codeRe = 0;
				while(time <= 10) {
					try {
						Thread.sleep(1000*5);
						Response<T> resRe = call.clone().execute();
						codeRe = resRe.code();
						if (codeRe == 200) {
							logger.info("time={},code={}",time, codeRe);
							call.clone().cancel();
							return codeRe;
						}else {
							logger.error("time={},api return http code = {}, url = {}, message = {}",time, codeRe, call.request().url().toString(), res.errorBody().string());
						}
					} catch (IOException ioe) {
						logger.error("time={},api throw IOException url={}",time, call.request().url().toString());
					} catch (Exception ex) {
						logger.error("time={},api throw Exception url={},exception is",time, call.request().url().toString(),ex);
						call.clone().cancel();
						return 501;
					} finally {
	                    // 任務結束後釋放 semaphore
	                    semaphore.release();
	                }
					time++;
					call.clone().cancel();
				}
				return codeRe;
			});
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
            logger.error("Semaphore acquire interrupted", e);
		}
        return code;
    }

    public static <T> CompletableFuture<Integer> reTryApiResponseCodeAsync(
            Call<T> call, String method) {

        return CompletableFuture.supplyAsync(() -> {
            try {
                // acquire semaphore，限流
                semaphore.acquire();

                int time = 1;
                int codeRe = 204;

                while (time <= 10) {
                    try {
                        Thread.sleep(5000); // 每次重試等待 5 秒

                        Response<T> resRe = call.clone().execute();
                        codeRe = resRe.code();

                        if (codeRe == 200) {
                            logger.info("[{}] time={}, code={}", method, time, codeRe);
                            return codeRe;
                        } else {
                            String errorMsg = resRe.errorBody() != null ? resRe.errorBody().string() : "no error body";
                            logger.error("[{}] time={}, api return http code={}, url={}, message={}",
                                    method, time, codeRe, call.request().url(), errorMsg);
                        }

                    } catch (IOException ioe) {
                        logger.error("[{}] time={}, api throw IOException, url={}", method, time, call.request().url(), ioe);
                    } catch (Exception ex) {
                        logger.error("[{}] time={}, api throw Exception, url={}", method, time, call.request().url(), ex);
                        return 501; // 其他例外返回 501
                    }
                    time++;
                }

                return codeRe;

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.error("[{}] Semaphore acquire interrupted", method, e);
                return 500; // semaphore 中斷返回 500
            } finally {
                semaphore.release();
            }
        }, executor);
    }
}