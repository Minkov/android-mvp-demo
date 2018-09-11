package com.minkov.androidapp.services;

import com.minkov.androidapp.models.Superhero;
import com.minkov.androidapp.repositories.base.Repository;
import com.minkov.androidapp.utils.StringUtils;
import com.minkov.androidapp.utils.SuperheroesGenerator;
import com.minkov.androidapp.validators.base.Validator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SuperheroesServiceTests {

    @Mock
    Repository<Superhero> mockRepository;

    @Mock
    Validator<Superhero> mockValidator;

    List<Superhero> mSuperheroes;

    private HttpSuperheroesService service;

    @Before
    public void setupTest() throws IOException {
        mSuperheroes = new ArrayList<>();
        when(mockRepository.getAll())
                .thenReturn(mSuperheroes);

        when(mockRepository.add(any()))
                .thenAnswer((Answer<Superhero>) invocation -> {
                    Superhero superhero = (Superhero) invocation.getArguments()[0];
                    superhero.id = 1;
                    return superhero;
                });

        service = new HttpSuperheroesService(mockRepository, mockValidator);
    }

    @Test
    public void superheroesServiceGetAllSuperheroes_whenSuperheroes_expectToReturnThem() throws Exception {
        mSuperheroes.addAll(SuperheroesGenerator.generateSuperheroesList(10));
        List<Superhero> result = service.getAllSuperheroes();

        assertEquals(mSuperheroes, result);
    }

    @Test
    public void superheroesServiceGetAllSuperheroes_whenNoSuperheroes_expectToReturnEmptyList() throws Exception {
        mSuperheroes.clear();
        List<Superhero> result = service.getAllSuperheroes();

        assertTrue(result.size() == 0);
    }

    @Test(expected = Exception.class)
    public void superheroesServiceGetAllSuperheroes_whenRepositoryThrows_expectToThrow() throws Exception {
        when(mockRepository.getAll())
                .thenThrow(new Exception());
        service.getAllSuperheroes();
    }

    @Test
    public void superheroesServiceGetFilteredSuperheroes_whenSuperheroesCaseSensitiveMatch_expectToReturnMatched() throws Exception {
        String pattern = "TO MATCH";
        List<Superhero> superheroesToMatch = SuperheroesGenerator.generateSuperheroesList(10, pattern);
        List<Superhero> superheroesNotToMatch = SuperheroesGenerator.generateSuperheroesList(10, "To NOT Match");

        mSuperheroes.addAll(superheroesToMatch);
        mSuperheroes.addAll(superheroesNotToMatch);

        List<Superhero> result = service.getFilteredSuperheroes(pattern);

        assertEquals(superheroesToMatch, result);
    }

    @Test
    public void superheroesServiceGetFilteredSuperheroes_whenSuperheroesCaseInsensitiveMatch_expectToReturnMatched() throws Exception {
        String pattern = "TO MATCH";
        List<Superhero> superheroesToMatch = SuperheroesGenerator.generateSuperheroesList(10, pattern)
                .stream()
                .map(superhero -> new Superhero(
                        StringUtils.shuffleCasing(superhero.getName()),
                        superhero.getSecretIdentity(),
                        superhero.getImageUrl()
                ))
                .collect(Collectors.toList());
        List<Superhero> superheroesNotToMatch = SuperheroesGenerator.generateSuperheroesList(10, "To NOT Match");

        mSuperheroes.addAll(superheroesToMatch);
        mSuperheroes.addAll(superheroesNotToMatch);

        List<Superhero> result = service.getFilteredSuperheroes(pattern);

        assertEquals(superheroesToMatch, result);
    }

    @Test
    public void superheroesServiceGetFilteredSuperheroes_whenNoMatch_expectToReturnEmptyList() throws Exception {
        String pattern = "TO MATCH";
        mSuperheroes.addAll(SuperheroesGenerator.generateSuperheroesList(10, "To NOT Match"));

        List<Superhero> result = service.getFilteredSuperheroes(pattern);

        assertTrue(result.size() == 0);
    }

    @Test(expected = Exception.class)
    public void superheroesServiceGetFilteredSuperheroes_whenRepositoryThrows_expectToThrow() throws Exception {
        when(mockRepository.getAll())
                .thenThrow(new Exception());
        service.getFilteredSuperheroes(anyString());
    }

    @Test
    public void superheroesServiceCreate_whenSuperheroIsValid_expectToReturnItWithId() throws Exception {
        Superhero superhero = SuperheroesGenerator.generateSuperhero();
        when(mockValidator.isValid(any()))
                .thenReturn(true);

        Superhero createdSuperhero = service.createSuperhero(superhero);

        assertEquals(superhero.getName(), createdSuperhero.getName());
        assertEquals(superhero.getSecretIdentity(), createdSuperhero.getSecretIdentity());
        assertEquals(superhero.getImageUrl(), createdSuperhero.getImageUrl());
        assertTrue(superhero.getId() > 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void superheroesServiceCreate_whenSuperheroIsInvalid_expectToThrowIllegalArgumetnException() throws Exception {
        Superhero superhero = SuperheroesGenerator.generateSuperhero();
        when(mockValidator.isValid(any()))
                .thenReturn(false);

        service.createSuperhero(superhero);
    }

    @Test
    public void superheroesServiceGetById_whenSuperheroesWithIdIsPresent_expectToReturnTheSuperhero() throws Exception {
        mSuperheroes.addAll(SuperheroesGenerator.generateSuperheroesList(10));

        when(mockRepository.getById(anyInt()))
                .thenReturn(mSuperheroes.get(0));

        Superhero superhero = service.getDetailsById(anyInt());
        assertNotNull(superhero);
    }


    @Test(expected = NullPointerException.class)
    public void superheroesServiceGetById_whenSuperheroesWithIdIsNotPresent_expectNullPointerException() throws Exception {
        when(mockRepository.getById(anyInt()))
                .thenThrow(new NullPointerException());

        service.getDetailsById(anyInt());
    }
}
