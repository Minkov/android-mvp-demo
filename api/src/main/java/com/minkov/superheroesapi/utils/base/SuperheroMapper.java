package com.minkov.superheroesapi.utils.base;

import com.minkov.superheroesapi.models.Superhero;
import com.minkov.superheroesapi.viewmodels.SuperheroDetailsViewModel;
import com.minkov.superheroesapi.viewmodels.SuperheroViewModel;

import java.util.List;

public interface SuperheroMapper {
    SuperheroViewModel map(Superhero model);

    Superhero map(SuperheroViewModel viewModel);

    List<SuperheroViewModel> mapMany(List<Superhero> models);

    SuperheroDetailsViewModel mapDetails(Superhero model);
}
