package com.ug369.backend.service.component.HTTP;

import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by Roy on 2017/8/3.
 */
public class HTTPSendor {

    /* http sender */
    public static int HTTP_CONNECT_TIMEOUT = 2000; //2s
    public static int HTTP_READ_TIMEOUT = 10000;//5s
    public static int HTTP_WRITE_TIMEOUT = 10000;//3s
    public static int HTTP_MAX_RUNNING_REQUESTS = 128;
    public static int HTTP_MAX_RUNNING_REQUESTS_PER_HOST = 8;
    public static int HTTP_MAX_RUNNING_CONNECTIONS_PER_HOST = 8;
    public static long HTTP_CONNECTION_ALIVE_TIME = 10; //min

    private static final Logger log = LoggerFactory.getLogger(HTTPSendor.class);
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static final OkHttpClient httpClient;

    static {
        Dispatcher dispatcher = new Dispatcher();
        dispatcher.setMaxRequests(HTTP_MAX_RUNNING_REQUESTS);
        dispatcher.setMaxRequestsPerHost(HTTP_MAX_RUNNING_REQUESTS_PER_HOST);
        ConnectionPool connectionPool = new ConnectionPool(HTTP_MAX_RUNNING_CONNECTIONS_PER_HOST,
                HTTP_CONNECTION_ALIVE_TIME, TimeUnit.MINUTES);

        httpClient = new OkHttpClient.Builder()
                .connectTimeout(HTTP_CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(HTTP_READ_TIMEOUT,TimeUnit.MILLISECONDS)
                .writeTimeout(HTTP_WRITE_TIMEOUT,TimeUnit.MILLISECONDS)
                .connectionPool(connectionPool)
                .dispatcher(dispatcher)
                .build();
    }

    public static void postAsync(String url, String body, Callback callback) {
        RequestBody requestBody = RequestBody.create(JSON, body);
        Request request = new Request.Builder().url(url).post(requestBody)
                .header("Accept-Encoding", "*")
                .header("User-Agent", "OneNET")
                .build();
        httpClient.newCall(request).enqueue(callback);
    }


    public static Response postSync(String url, String body) {
        RequestBody requestBody = RequestBody.create(JSON, body);
        Request request = new Request.Builder().url(url).post(requestBody)
                .header("Accept-Encoding", "*")
                .header("User-Agent", "OneNET")
                .build();
        try {
            return httpClient.newCall(request).execute();
        } catch (IOException e) {
            log.error(e.toString());
            return null;
        }
    }

    public static void close() {
        while (httpClient.dispatcher().queuedCallsCount() != 0 && httpClient.dispatcher().runningCallsCount() != 0) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                log.error("", e);
            }
        }
        httpClient.dispatcher().executorService().shutdown();
        httpClient.connectionPool().evictAll();
    }
}
