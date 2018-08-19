package com.minkov.mvpdemo.models;

import com.minkov.mvpdemo.models.base.EntityBase;

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
