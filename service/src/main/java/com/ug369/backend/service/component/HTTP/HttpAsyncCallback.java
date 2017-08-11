package com.ug369.backend.service.component.HTTP;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by chen7 on 2016/12/26.
 */
public class HttpAsyncCallback implements Callback {
    protected static final Logger log = LoggerFactory.getLogger(HttpAsyncCallback.class);

    protected String httpBodyMsg;

    public HttpAsyncCallback(String httpBodyMsg) {
        this.httpBodyMsg = httpBodyMsg;
    }

    @Override
    public void onFailure(Call call, IOException e) {
        log.error("fail2send pid={}",e);
        cacheMsg();
        statusChange();
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        if (response.code() != 200) {
            log.error("fail2send pid={}, statusCode={}", response.code());
//            log.info("recv sub url return , but status code is {}", response.code());
            cacheMsg();
            statusChange();
        }
        response.close();
    }

    private void cacheMsg() {

    }

    private void statusChange() {

    }
}
