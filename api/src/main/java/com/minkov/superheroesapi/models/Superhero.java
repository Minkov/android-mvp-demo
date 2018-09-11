package com.minkov.superheroesapi.models;

import com.minkov.superheroesapi.models.base.ModelBase;

public class Superhero extends ModelBase {
    private String name;
    private String secretIdentity;
    private String imageUrl;
    public Superhero() {

    }

    public Superhero(String name, String secretIdentity, String imageUrl) {
        setName(name);
        setSecretIdentity(secretIdentity);
        setImageUrl(imageUrl);
    }

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

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
