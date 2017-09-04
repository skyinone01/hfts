package com.ug369.backend.service.entity.mysql;

import javax.persistence.*;
import java.io.Serializable;



@Entity
@Table(name = "ug_platform")
public class Platform implements Serializable{
	private static final long serialVersionUID = -8566197653568234573L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String url;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
