package com.ug369.backend.service.component.Bean;

import org.bitcoin.market.bean.AppAccount;

/**
 * Created by Roy on 2017/8/18.
 */
public class YunbiConfig {

    private String accessKey;
    private String secretKey;
//162fb13d-ad86-429b-8ec1-f10f214af35e
//5398E4646EF1080A78EFEA6B65BE9426
    //https://github.com/bihanggit/bihang-java
    public static AppAccount getAppAccount() {
        AppAccount appAccount = new AppAccount();
        appAccount.setId(1L);
        appAccount.setAccessKey("gR19dYb29xlxbcHsIGHktYzWUU9RYqRu52AgjlFx");
        appAccount.setSecretKey("FX4CVDBqKt8jicERLrWdsyuNFBNv8pK1PjmjeXtu");
        return appAccount;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
