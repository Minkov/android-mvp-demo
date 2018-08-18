package com.minkov.superheroesapi.services;

import com.minkov.superheroesapi.models.Superhero;
import com.minkov.superheroesapi.repositories.base.GenericRepository;
import com.minkov.superheroesapi.services.base.SuperheroesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuperheroesServiceImpl implements SuperheroesService {

    private final GenericRepository<Superhero> superheroRepository;

    @Autowired
    public SuperheroesServiceImpl(GenericRepository<Superhero> superheroRepository) {
        this.superheroRepository = superheroRepository;
    }

    @Override
    public List<Superhero> getAllSuperheroes() {
        return this.superheroRepository.findAll();
    }

    @Override
    public Superhero findSuperheroById(int id) {
        return this.superheroRepository.findById(id);
    }

    @Override
    public Superhero create(Superhero superhero) {
        superheroRepository.add(superhero);
        return superhero;
    }
}
