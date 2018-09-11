package com.minkov.mvpservicesdemofirebase.models.base;

import java.io.Serializable;

public class EntityBase {
    private String id;

    public EntityBase withId(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
