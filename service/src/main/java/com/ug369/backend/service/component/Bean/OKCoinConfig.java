package com.ug369.backend.service.component.Bean;

/**
 * Created by Roy on 2017/8/18.
 */
public class OKCoinConfig {

    private  String key = "162fb13d-ad86-429b-8ec1-f10f214af35e";
    private  String secret = "5398E4646EF1080A78EFEA6B65BE9426";


    public OKCoinConfig(String key,String secret){
        this.key = key;
        this.secret = secret;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
