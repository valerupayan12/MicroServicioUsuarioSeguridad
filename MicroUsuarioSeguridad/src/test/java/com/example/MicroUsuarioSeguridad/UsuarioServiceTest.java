package com.example.MicroUsuarioSeguridad;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.MicroUsuarioSeguridad.model.Usuario;
import com.example.MicroUsuarioSeguridad.repository.UsuarioRepository;
import com.example.MicroUsuarioSeguridad.service.UsuarioService;

@SpringBootTest
public class UsuarioServiceTest {

    // Inyecta el servicio de Usuario para ser probado.
    @Autowired
    private UsuarioService usuarioService;

    // Crea un mock del repositorio de Usuario para simular su comportamiento.
    @Mock
    private UsuarioRepository usuarioRepository;

    @Test
    public void testFindAll() {
        // Define el comportamiento del mock: cuando se llame a findAll(), devuelve una lista con un Usuario.
        when(usuarioRepository.findAll()).thenReturn(List.of(new Usuario("1", "John Doe ", "john.doe@example.com", "1234567890", new RolPermiso(1, "Administrador"), 1, true)));

        // Llama al método findAll() del servicio.
        List<Usuario> usuarios = usuarioService.findAll();

        // Verifica que la lista devuelta no sea nula y contenga exactamente un Usuario.
        assertNotNull(usuarios);
        assertEquals(1, usuarios.size());
    }

    @Test
    public void testFindByCodigo() {
        String codigo = "1";
        Usuario usuario = new Usuario(codigo, "John Doe ", "john.doe@example.com", "1234567890", new RolPermiso(1, "Administrador"), 1, true);

        // Define el comportamiento del mock: cuando se llame a findById() con "1", devuelve un Usuario opcional.
        when(usuarioRepository.findById(codigo)).thenReturn(Optional.of(usuario));

        // Llama al método findByCodigo() del servicio.
        Usuario found = usuarioService.findByCodigo(codigo);

        // Verifica que el Usuario devuelto no sea nulo y que su código coincida con el código esperado.
        assertNotNull(found);
        assertEquals(codigo, found.getId());
    }

    @Test
    public void testSave() {
        Usuario usuario = new Usuario("1", "John Doe ", "john.doe@example.com", "1234567890", new RolPermiso(1, "Administrador"), 1, true);

        // Define el comportamiento del mock: cuando se llame a save(), devuelve el Usuario proporcionado.
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        // Llama al método save() del servicio.
        Usuario saved = usuarioService.save(usuario);

        // Verifica que el Usuario guardado no sea nulo y que su nombre coincida con el nombre esperado.
        assertNotNull(saved);
        assertEquals("John Doe ", saved.getNombre());
    }

    @Test
    public void testDeleteByCodigo() {
        String codigo = "1";

        // Define el comportamiento del mock: cuando se llame a deleteById(), no hace nada.
        doNothing().when(usuarioRepository).deleteAll();

        // Llama al método deleteByCodigo() del servicio.
        usuarioService.deleteByCodigo(codigo);

        // Verifica que el método deleteById() del repositorio se haya llamado exactamente una vez con el código proporcionado.
        verify(usuarioRepository, times(1)).delete(codigo);
    }
}