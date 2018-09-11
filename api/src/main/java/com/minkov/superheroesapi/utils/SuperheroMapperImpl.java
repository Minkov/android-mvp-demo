package com.minkov.superheroesapi.utils;

import com.minkov.superheroesapi.models.Superhero;
import com.minkov.superheroesapi.utils.base.SuperheroMapper;
import com.minkov.superheroesapi.viewmodels.SuperheroDetailsViewModel;
import com.minkov.superheroesapi.viewmodels.SuperheroViewModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SuperheroMapperImpl implements SuperheroMapper {
    @Override
    public SuperheroViewModel map(Superhero model) {
        SuperheroViewModel vm = new SuperheroViewModel();
        vm.id = model.getId();
        vm.name = model.getName();
        vm.secretIdentity = model.getSecretIdentity();
        vm.imageUrl = model.getImageUrl();
        return vm;
    }

    @Override
    public Superhero map(SuperheroViewModel viewModel) {
        Superhero model = new Superhero();
        model.setName(viewModel.name);
        model.setSecretIdentity(viewModel.secretIdentity);
        return model;
    }

    @Override
    public List<SuperheroViewModel> mapMany(List<Superhero> models) {
        return models.stream()
            .map(this::map)
            .collect(Collectors.toList());
    }

    @Override
    public SuperheroDetailsViewModel mapDetails(Superhero model) {
        SuperheroDetailsViewModel vm = new SuperheroDetailsViewModel();
        vm.id = model.getId();
        vm.name = model.getName();
        vm.secretIdentity = model.getSecretIdentity();
        return vm;
    }
}
