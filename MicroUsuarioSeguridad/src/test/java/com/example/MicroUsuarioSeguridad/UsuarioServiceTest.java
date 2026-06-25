package com.example.MicroUsuarioSeguridad;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.MicroUsuarioSeguridad.dto.UsuarioDTO;
import com.example.MicroUsuarioSeguridad.model.Usuario;
import com.example.MicroUsuarioSeguridad.repository.UsuarioRepository;
import com.example.MicroUsuarioSeguridad.service.impl.UsuarioServiceimpl;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    // Inyecta los mocks dentro de la implementación real del servicio (sin levantar el contexto de Spring).
    @InjectMocks
    private UsuarioServiceimpl usuarioService;

    // Mock del repositorio de Usuario para simular su comportamiento.
    @Mock
    private UsuarioRepository usuarioRepository;

    private Usuario usuario;

    @BeforeEach
    public void setUp() {
        usuario = new Usuario();
        usuario.setId_usuario(1);
        usuario.setNombre("John Doe");
        usuario.setCorreo("john.doe@example.com");
        usuario.setTelefono("1234567890");
        usuario.setIdTienda(1);
        usuario.setEstado(true);
        // genero y rol se dejan null aquí: toResponse() los maneja con chequeo de null.
    }

    @Test
    public void testListar() {
        // Define el comportamiento del mock: cuando se llame a findAll(), devuelve una lista con un Usuario.
        when(usuarioRepository.findAll()).thenReturn(List.of(usuario));

        // Llama al método listar() del servicio.
        List<UsuarioDTO.Response> usuarios = usuarioService.listar();

        // Verifica que la lista devuelta no sea nula y contenga exactamente un usuario.
        assertNotNull(usuarios);
        assertEquals(1, usuarios.size());
        assertEquals("John Doe", usuarios.get(0).getNombre());
    }

    @Test
    public void testBuscarPorId() {
        // Define el comportamiento del mock: cuando se llame a findById(1), devuelve el Usuario.
        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));

        // Llama al método buscarPorId() del servicio.
        UsuarioDTO.Response found = usuarioService.buscarPorId(1);

        // Verifica que el Usuario devuelto no sea nulo y que su id coincida con el esperado.
        assertNotNull(found);
        assertEquals(1, found.getId_usuario());
    }

    @Test
    public void testGuardar() {
        UsuarioDTO.Request request = new UsuarioDTO.Request();
        request.setNombre("John Doe");
        request.setCorreo("john.doe@example.com");
        request.setTelefono("1234567890");
        request.setId_tienda(1);
        request.setEstado(true);
        request.setId_genero(1);
        request.setId_rol(1);

        // Define el comportamiento del mock: cuando se llame a save(), devuelve el Usuario simulado.
        when(usuarioRepository.save(org.mockito.ArgumentMatchers.any(Usuario.class))).thenReturn(usuario);

        // Llama al método guardar() del servicio.
        UsuarioDTO.Response saved = usuarioService.guardar(request);

        // Verifica que el resultado no sea nulo y que el nombre coincida con el esperado.
        assertNotNull(saved);
        assertEquals("John Doe", saved.getNombre());
    }

    @Test
    public void testEliminar() {
        // Llama al método eliminar() del servicio.
        usuarioService.eliminar(1);

        // Verifica que el método deleteById() del repositorio se haya llamado exactamente una vez con el id proporcionado.
        verify(usuarioRepository, times(1)).deleteById(1);
    }
}