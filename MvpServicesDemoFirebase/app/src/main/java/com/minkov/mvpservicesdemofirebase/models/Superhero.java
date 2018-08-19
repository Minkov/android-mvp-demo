package com.minkov.mvpservicesdemofirebase.models;

import com.minkov.mvpservicesdemofirebase.models.base.EntityBase;

public class Superhero extends EntityBase {
    public String name;

    public Superhero() {
    }

    public Superhero(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
