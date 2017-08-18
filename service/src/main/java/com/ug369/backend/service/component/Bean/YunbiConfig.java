package com.ug369.backend.service.component.Bean;

import org.bitcoin.market.bean.AppAccount;

/**
 * Created by Roy on 2017/8/18.
 */
public class YunbiConfig {

    public static AppAccount getAppAccount() {
        AppAccount appAccount = new AppAccount();
        appAccount.setId(1L);
        appAccount.setAccessKey("gR19dYb29xlxbcHsIGHktYzWUU9RYqRu52AgjlFx");
        appAccount.setSecretKey("FX4CVDBqKt8jicERLrWdsyuNFBNv8pK1PjmjeXtu");
        return appAccount;
    }
}
