package com.minkov.mvpdemo.models;

public class Superhero {
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
