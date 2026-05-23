package com.example.MicroUsuarioSeguridad.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MicroUsuarioSeguridad.model.Cliente;
import com.example.MicroUsuarioSeguridad.repository.ClienteRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ComunaService {
    @Autowired
//SE LLAMA AL REPOSITORIO PARA PODER USAR SUS FUNCIONES
    private ClienteRepository comunaRepository; 

//OBTENER COMUNAS
    public List<Cliente> getComunas() {
        return comunaRepository.obtenerComunas();
    }
//OBTENER COMUNA POR ID
    public Cliente getComunaById(int id_comuna) {
        Cliente comunas = comunaRepository.buscarComuna(id_comuna);
        if (comunas!=null) {    
        return comunas;
        }else
        return new Cliente();
    }
//CREAR comuna
    public Cliente saveComunas(Cliente comuna) {
        return comunaRepository.save(comuna);
    }
//ACTUALIZAR comuna
    public int updateComuna(Cliente comuna) {
         comunaRepository.save(comuna);
        return 1;
    }
//ELIMINAR comuna
    public int deleteComuna(int id_comuna) {
        comunaRepository.delete(getComunaById(id_comuna));
        return 1;
    }

}
