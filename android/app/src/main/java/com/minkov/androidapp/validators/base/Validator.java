package com.minkov.androidapp.validators.base;

public interface Validator<T> {
    boolean isValid(T object);
}
