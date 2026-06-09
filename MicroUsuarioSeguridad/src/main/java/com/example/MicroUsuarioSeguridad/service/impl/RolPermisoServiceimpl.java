package com.example.MicroUsuarioSeguridad.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.MicroUsuarioSeguridad.model.RolPermiso;
import com.example.MicroUsuarioSeguridad.model.RolPermiso.Response;
import com.example.MicroUsuarioSeguridad.repository.RolPermisoRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor

public class RolPermisoServiceimpl implements RolPermisoService {

    private final RolPermisoRepository rolPermisoRepository;

    @Transactional(readOnly = true)
    public List<RolPermiso.Response> listarTodos() {
        return rolPermisoRepository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public RolPermiso.Response buscarPorId(int id) {
        RolPermiso r = rolPermisoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("RolPermiso no encontrado con id: " + id));
        return mapToResponse(r);
    }

    @Transactional
    public RolPermiso.Response crear(RolPermiso.Request request) {
        RolPermiso r = new RolPermiso();
        r.setId_rol(request.getId_rol());
        r.setId_permiso(request.getId_permiso());
        return mapToResponse(rolPermisoRepository.save(r));
    }

    @Transactional
    public RolPermiso.Response actualizar1(int id, RolPermiso.Request request) {
        RolPermiso r = rolPermisoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("RolPermiso no encontrado con id: " + id));
        r.setId_rol(request.getId_rol());
        r.setId_permiso(request.getId_permiso());
        return mapToResponse(rolPermisoRepository.save(r));
    }

    @Transactional
    public RolPermiso.Response actualizar(int id, RolPermiso.Request request) {
        RolPermiso r = rolPermisoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("RolPermiso no encontrado con id: " + id));
        r.setId_rol(request.getId_rol());
        r.setId_permiso(request.getId_permiso());
        return mapToResponse(rolPermisoRepository.save(r));
    }

    private Response mapToResponse(RolPermiso save) {
        throw new UnsupportedOperationException("Unimplemented method 'mapToResponse'");
    }

    @Transactional
    public void eliminar(int id) {
        if (!rolPermisoRepository.existsById(id))
            throw new RuntimeException("RolPermiso no encontrado con id: " + id);
        rolPermisoRepository.deleteById(id);
    }
}
