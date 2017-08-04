package com.ug369.backend.service.component.HTTP;


import ch.qos.logback.core.rolling.helper.Compressor;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

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
        log.error("fail2send pid={}",subscribeInfo.getPid(),e);
        cacheMsg();
        statusChange();
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        if (response.code() != 200) {
            log.error("fail2send pid={}, statusCode={}",subscribeInfo.getPid(), response.code());
//            log.info("recv sub url return , but status code is {}", response.code());
            cacheMsg();
            statusChange();
        }
        response.close();
    }

    private void cacheMsg() {
        validator.getErrRecords().get(subscribeInfo.getPid()).incrementAndGet();
        try (Jedis j = RedisBroker.getResource()) {
            j.lpush(Util.generateRedisCacheKey(subscribeInfo.getPid()), Compressor.compress(httpBodyMsg));
        }
    }

    private void statusChange() {
        switch (subscribeInfo.getStatus()) {
            case OK:
                if (validator.getErrRecords().get(subscribeInfo.getPid()).get() != 0) {
                    subscribeInfo.setStatus(ProjectStatus.ERR_HAPPEN);
                    subscribeInfo.setFirstErrHappenTime(System.currentTimeMillis());
                    UnsentMsgChecker.setErrHappen(subscribeInfo.getPid());
                }
                break;
            case ERR_HAPPEN:
                if (validator.getErrRecords().get(subscribeInfo.getPid()).get() > Config.FREEZE_PROJECT_ERROR_NUM_THRESHOLD
                        || (subscribeInfo.getFirstErrHappenTime() > 0 && subscribeInfo.getFirstErrHappenTime() + Config.FREEZE_PROJECT_TIME_THRESHOLD < System.currentTimeMillis())) {
                    subscribeInfo.setStatus(ProjectStatus.NOT_CONNECTED);
                }
                break;
            case SENDING:
                break;
            case NOT_CONNECTED:
                break;
        }
    }
}
