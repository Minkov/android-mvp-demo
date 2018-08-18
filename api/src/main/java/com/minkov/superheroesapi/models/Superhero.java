package com.minkov.superheroesapi.models;

import com.minkov.superheroesapi.models.base.ModelBase;

public class Superhero extends ModelBase {
    private String name;
    private String secretIdentity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecretIdentity() {
        return secretIdentity;
    }

    public void setSecretIdentity(String secretIdentity) {
        this.secretIdentity = secretIdentity;
    }
}
