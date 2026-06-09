package com.example.MicroUsuarioSeguridad.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.MicroUsuarioSeguridad.dto.UsuarioDTO;
import com.example.MicroUsuarioSeguridad.model.Usuario;
import com.example.MicroUsuarioSeguridad.repository.UsuarioRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsuarioServiceimpl implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Transactional(readOnly = true)
    public List<UsuarioDTO.Response> listarTodos() {
        return usuarioRepository.findAll().stream().map(this::mapToResponse1).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UsuarioDTO.Response buscarPorId(int id) {
        Usuario u = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
        return mapToResponse1(u);
    }

    @Transactional
    public UsuarioDTO.Response crear(UsuarioDTO.Request request) {
        Usuario u = new Usuario();
        u.setNombre(request.getNombre());
        u.setCorreo(request.getCorreo());
        u.setTelefono(request.getTelefono());
        return mapToResponse1(usuarioRepository.save(u));
    }

    @Transactional
    public UsuarioDTO.Response actualizar(int id, UsuarioDTO.Request request) {
        Usuario u = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
        u.setNombre(request.getNombre());
        u.setCorreo(request.getCorreo());
        u.setTelefono(request.getTelefono());
        return mapToResponse1(usuarioRepository.save(u));
    }

    @Transactional
    public void eliminar1(int id) {
        if (!usuarioRepository.existsById(id))
            throw new RuntimeException("Usuario no encontrado con id: " + id);
        usuarioRepository.deleteById(id);
    }

    private UsuarioDTO.Response mapToResponse1(Usuario u) {
        return new UsuarioDTO.Response();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'loadUserByUsername'");
    }
}
