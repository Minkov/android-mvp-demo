package com.minkov.androidapp.validators;

import android.support.constraint.Constraints;

import com.minkov.androidapp.Constants;
import com.minkov.androidapp.models.Superhero;
import com.minkov.androidapp.validators.base.Validator;

public class SuperheroValidator implements Validator<Superhero> {
    @Override
    public boolean isValid(Superhero object) {
        return object != null &&
                isNameValid(object) &&
                isSecretIdentityValid(object);
    }

    private boolean isSecretIdentityValid(Superhero object) {
        return object.getSecretIdentity().length() >= Constants.SUPERHERO_SECRET_IDENTITY_MIN_LENGHT &&
                object.getSecretIdentity().length() <= Constants.SUPERHERO_SECRET_IDENTITY_MAX_LENGHT;
    }

    private boolean isNameValid(Superhero object) {
        return object.getName().length() >= Constants.SUPERHERO_NAME_MIN_LENGHT &&
                object.getName().length() <= Constants.SUPERHERO_NAME_MAX_LENGHT;
    }
}
