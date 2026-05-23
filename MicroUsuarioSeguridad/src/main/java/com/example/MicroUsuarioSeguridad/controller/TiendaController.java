package com.example.MicroUsuarioSeguridad.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.MicroUsuarioSeguridad.model.Usuario;
import com.example.MicroUsuarioSeguridad.service.RolPermisoService;

import jakarta.validation.Valid;

@SuppressWarnings("unused")
@RestController
@RequestMapping ("api/v2/tiendas")
public class TiendaController {
    @Autowired
    private RolPermisoService tiendaService;

    @GetMapping
    public List<Usuario> listarTiendas(){
        return tiendaService.getTiendas();
    }
    //agregar
    @PostMapping
    public Usuario agregarTienda(@Valid @RequestBody Usuario tienda){
        return tiendaService.saveTienda(tienda);
     }
    //buscar
        @GetMapping("/{id_tienda}")
        public Usuario buscarTienda(@PathVariable int id_tienda){
            return tiendaService.getTienda(id_tienda);
        }
    //actualizar
    @PutMapping("/{id_tienda}")
    public Usuario actualizarTienda(@PathVariable int id_tienda, @Valid @RequestBody Usuario tienda){
        return tiendaService.updateTienda(id_tienda, tienda);
    }
    //eliminar
    @DeleteMapping("/{id_tienda}")
    public String eliminarTienda(@PathVariable int id_tienda){
        if (tiendaService.deleteTienda(id_tienda) == 1) {
            return "Tienda eliminada correctamente";
        }
        return "Error al eliminar la tienda";
    }

}
