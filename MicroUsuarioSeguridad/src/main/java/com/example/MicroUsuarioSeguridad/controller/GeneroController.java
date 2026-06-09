package com.example.MicroUsuarioSeguridad.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.MicroUsuarioSeguridad.model.Genero;
import com.example.MicroUsuarioSeguridad.service.GeneroService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/generos")
public class GeneroController {

    @Autowired
    private GeneroService generoService;

    // LISTAR GENEROS
    @GetMapping
    public List<Genero> listarGeneros() {

        return GenericConversionService.getGenero();
    }

    // AGREGAR GENERO
    @PostMapping
    public Genero agregarGenero(@Valid @RequestBody Genero genero) {

        return generoService.saveGenero(genero);
    }

    // BUSCAR GENERO POR ID
    @GetMapping("{id_genero}")
    public Genero buscarGenero(@PathVariable int id_genero) {

        return generoService.getGenero(id_genero);
    }

    // ACTUALIZAR GENERO
    @PutMapping("{id_genero}")
    public int actualizarGenero(@PathVariable int id_genero,
                                @Valid @RequestBody Genero genero) {

        genero.setId_genero(id_genero);

        return generoService.updateGenero(genero);
    }

    // ELIMINAR GENERO
    @DeleteMapping("{id_genero}")
    public String eliminarGenero(@PathVariable int id_genero) {

        if (generoService.deleteGenero(id_genero) == 1) {
            return "Genero eliminado correctamente";
        }

        return "Error al eliminar el genero";
    }
}
