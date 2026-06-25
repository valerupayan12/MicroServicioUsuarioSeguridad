package com.example.MicroUsuarioSeguridad;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.example.MicroUsuarioSeguridad.model.Genero;
import com.example.MicroUsuarioSeguridad.model.RolPermiso;
import com.example.MicroUsuarioSeguridad.model.Usuario;
import com.example.MicroUsuarioSeguridad.repository.GeneroRepository;
import com.example.MicroUsuarioSeguridad.repository.RolPermisoRepository;
import com.example.MicroUsuarioSeguridad.repository.UsuarioRepository;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private RolPermisoRepository rolPermisoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private GeneroRepository generoRepository;

    @Override
    public void run(String... args) throws Exception {
        com.github.javafaker.Faker faker = new com.github.javafaker.Faker();
        Random random = new Random();

        // 1. Generar Géneros
        String[] nombresGenero = {"Masculino", "Femenino", "No binario"};
        for (int i = 0; i < nombresGenero.length; i++) {
            Genero genero = new Genero(null, nombresGenero[i]);
            generoRepository.save(genero);
        }
        List<Genero> generos = generoRepository.findAll();

        // 2. Generar RolesPermisos
        String[] modulos = {"ventas", "inventario", "reportes"};
        String[] acciones = {"leer", "escribir", "eliminar"};
        for (int i = 0; i < 5; i++) {
            RolPermiso rolPermiso = new RolPermiso(
                null,                                       // id (auto generado)
                i + 1,                                      // id_rol
                i + 1,                                      // id_permiso
                faker.job().title(),                        // nombre_rol
                modulos[random.nextInt(modulos.length)],    // modulo
                acciones[random.nextInt(acciones.length)]   // accion
            );
            rolPermisoRepository.save(rolPermiso);
        }
        List<RolPermiso> roles = rolPermisoRepository.findAll();

        // 3. Generar Usuarios
        for (int i = 0; i < 20; i++) {
            Usuario usuario = new Usuario(
                i + 1,                                          // id_usuario
                generos.get(random.nextInt(generos.size())),    // genero
                faker.name().firstName(),                       // nombre
                faker.internet().emailAddress(),                // correo
                faker.phoneNumber().cellPhone(),                // telefono
                roles.get(random.nextInt(roles.size())),        // rol
                random.nextInt(5) + 1,                          // idTienda
                true                                            // estado
            );
            usuarioRepository.save(usuario);
        }
    }
}