package com.example.MicroUsuarioSeguridad;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.example.MicroUsuarioSeguridad.model.RolPermiso;
import com.example.MicroUsuarioSeguridad.model.Usuario;
import com.example.MicroUsuarioSeguridad.repository.RolPermisoRepository;
import com.example.MicroUsuarioSeguridad.repository.UsuarioRepository;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private RolPermisoRepository rolpermisoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    
    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        Random random = new Random();

        // Generar roles y permisos
        for (int i = 0; i < 3; i++) {
            RolPermiso rolPermiso = new RolPermiso();
            rolPermiso.setId_rol(i + 1);
            rolPermiso.setNombre(faker.book().genre());
            rolpermisoRepository.save(rolPermiso);
        }
        List<RolPermiso> roles = rolpermisoRepository.findAll();

        // Generar usuarios
        for (int i = 0; i < 20; i++) {
            Usuario usuario = new Usuario();
            usuario.setId(i + 1);
            usuario.setNombre(faker.name().firstName());
            usuario.setCorreo(faker.internet().emailAddress());
            usuario.setTelefono(faker.phoneNumber().cellPhone());
            usuario.setRol(roles.get(random.nextInt(roles.size())));
            usuarioRepository.save(usuario);
        }
}
}
