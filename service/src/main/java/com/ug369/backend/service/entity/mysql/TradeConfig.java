package com.ug369.backend.service.entity.mysql;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "ug_trade_config")
public class TradeConfig implements Serializable{
	private static final long serialVersionUID = -8566197653568234573L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long platform;
    private long user;
    private String accessKey;
    private String secretKey;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPlatform() {
        return platform;
    }

    public void setPlatform(long platform) {
        this.platform = platform;
    }

    public long getUser() {
        return user;
    }

    public void setUser(long user) {
        this.user = user;
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
