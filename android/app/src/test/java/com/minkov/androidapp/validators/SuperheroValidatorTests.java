package com.minkov.androidapp.validators;

import com.minkov.androidapp.Constants;
import com.minkov.androidapp.models.Superhero;
import com.minkov.androidapp.utils.StringUtils;
import com.minkov.androidapp.utils.SuperheroesGenerator;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class SuperheroValidatorTests {
    private SuperheroValidator mValidator;

    @Before
    public void setupTest() {
        mValidator = new SuperheroValidator();
    }

    @Test
    public void superheroValidatorIsValid_whenAllIsValid_expectTrue() {
        Superhero superhero = SuperheroesGenerator.generateSuperhero();
        boolean isValid = mValidator.isValid(superhero);
        assertTrue(isValid);
    }

    @Test
    public void superheroValidatorIsValid_whenNameLength2_expectFalse() {
        Superhero superhero = SuperheroesGenerator.generateSuperhero();
        superhero.name = StringUtils.repeat("s", 2);
        boolean isValid = mValidator.isValid(superhero);
        assertFalse(isValid);
    }

    @Test
    public void superheroValidatorIsValid_whenNameLength51_expectFalse() {
        Superhero superhero = SuperheroesGenerator.generateSuperhero();
        superhero.name = StringUtils.repeat("s", 51);
        boolean isValid = mValidator.isValid(superhero);
        assertFalse(isValid);
    }

    @Test
    public void superheroValidatorIsValid_whenSecretIdentityLength2_expectFalse() {
        Superhero superhero = SuperheroesGenerator.generateSuperhero();
        superhero.secretIdentity = StringUtils.repeat("s", 2);
        boolean isValid = mValidator.isValid(superhero);
        assertFalse(isValid);
    }

    @Test
    public void superheroValidatorIsValid_whenSecretIdentityLength51_expectFalse() {
        Superhero superhero = SuperheroesGenerator.generateSuperhero();
        superhero.secretIdentity = StringUtils.repeat("s", 51);
        boolean isValid = mValidator.isValid(superhero);
        assertFalse(isValid);
    }
}
