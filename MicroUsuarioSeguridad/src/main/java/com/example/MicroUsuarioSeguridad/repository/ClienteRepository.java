package com.example.MicroUsuarioSeguridad.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.MicroUsuarioSeguridad.model.Cliente;


@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    @Query("SELECT c FROM Comuna c")
    List<Cliente> obtenerComunas();

    @Query("SELECT c FROM Comuna c WHERE c.id_comuna = :id_comuna")
    Cliente buscarComuna(int id_comuna);

}
