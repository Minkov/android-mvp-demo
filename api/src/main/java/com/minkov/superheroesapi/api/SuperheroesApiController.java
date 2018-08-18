package com.minkov.superheroesapi.api;

import com.minkov.superheroesapi.models.Superhero;
import com.minkov.superheroesapi.services.base.SuperheroesService;
import com.minkov.superheroesapi.utils.base.SuperheroMapper;
import com.minkov.superheroesapi.viewmodels.SuperheroDetailsViewModel;
import com.minkov.superheroesapi.viewmodels.SuperheroViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/superheroes")
public class SuperheroesApiController {
    private final SuperheroesService superheroesService;
    private final SuperheroMapper mapper;

    @Autowired
    public SuperheroesApiController(SuperheroesService superheroesService, SuperheroMapper mapper) {
        this.superheroesService = superheroesService;
        this.mapper = mapper;
    }

    @RequestMapping(
        method = RequestMethod.GET
    )
    public List<SuperheroViewModel> getAllSuperheroes() {
        List<Superhero> models = this.superheroesService.getAllSuperheroes();
        return this.mapper.mapMany(models);
    }


    @RequestMapping(
        value = "/{id}",
        method = RequestMethod.GET
    )
    public SuperheroDetailsViewModel getSuperheroById(@PathVariable("id") String idString) {
        int id = Integer.parseInt(idString);
        Superhero model = this.superheroesService.findSuperheroById(id);
        return this.mapper.mapDetails(model);
    }


    @RequestMapping(
        method = RequestMethod.POST
    )
    public ResponseEntity<SuperheroViewModel> createSuperhero(@RequestBody SuperheroViewModel superheroVm) {
        Superhero model = this.mapper.map(superheroVm);
        Superhero superhero = this.superheroesService.create(model);
        SuperheroViewModel vmToReturn = this.mapper.map(superhero);

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(vmToReturn);
    }
}
