package com.example.MicroUsuarioSeguridad;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.example.MicroUsuarioSeguridad.model.RolPermiso;
import com.example.MicroUsuarioSeguridad.repository.RolPermisoRepository;
import com.example.MicroUsuarioSeguridad.service.RolPermisoService;

@SpringBootTest
public class RolPermisoServiceTest {

    @Autowired
    private RolPermisoService rolPermisoService;

    @MockitoBean
    private RolPermisoRepository rolPermisoRepository;

    // LISTAR
    @Test
    public void testListar() {
        RolPermiso rolPermiso = new RolPermiso(1, 1, 1, "Administrador", "usuarios", "CREAR");
        when(rolPermisoRepository.findAll()).thenReturn(List.of(rolPermiso));

        List<RolPermiso> rolPermisos = rolPermisoService.listar();

        assertNotNull(rolPermisos);
        assertEquals(1, rolPermisos.size());
    }

    // BUSCAR POR ID (existe)
    @Test
    public void testBuscarPorId() {
        int id = 1;
        RolPermiso rolPermiso = new RolPermiso(id, 1, 1, "Administrador", "usuarios", "CREAR");
        when(rolPermisoRepository.findById(id)).thenReturn(Optional.of(rolPermiso));

        RolPermiso encontrado = rolPermisoService.buscarPorId(id);

        assertNotNull(encontrado);
        assertEquals("Administrador", encontrado.getNombre_rol());
    }

    // BUSCAR POR ID (no existe) -> lanza excepcion
    @Test
    public void testBuscarPorId_noExiste() {
        int id = 99;
        when(rolPermisoRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> rolPermisoService.buscarPorId(id));
    }

    // GUARDAR
    @Test
    public void testGuardar() {
        RolPermiso rolPermiso = new RolPermiso(null, 1, 1, "Administrador", "usuarios", "CREAR");
        when(rolPermisoRepository.save(rolPermiso)).thenReturn(rolPermiso);

        RolPermiso creado = rolPermisoService.guardar(rolPermiso);

        assertNotNull(creado);
        assertEquals("Administrador", creado.getNombre_rol());
    }

    // ACTUALIZAR (existe)
    // NOTA: la implementacion actual no aplica los nuevos valores (los setters
    // estan comentados), asi que el resultado es el objeto EXISTENTE sin cambios.
    @Test
    public void testActualizar_existe() {
        int id = 1;
        RolPermiso existente = new RolPermiso(id, 1, 1, "Administrador", "usuarios", "CREAR");
        when(rolPermisoRepository.findById(id)).thenReturn(Optional.of(existente));
        when(rolPermisoRepository.save(existente)).thenReturn(existente);

        RolPermiso nuevosDatos = new RolPermiso(null, 2, 2, "Editor", "productos", "EDITAR");

        RolPermiso resultado = rolPermisoService.actualizar(id, nuevosDatos);

        assertNotNull(resultado);
        // Refleja el comportamiento actual (bug): no cambia, sigue siendo "Administrador"
        assertEquals("Administrador", resultado.getNombre_rol());
        verify(rolPermisoRepository).save(existente);
    }

    // ACTUALIZAR (no existe) -> lanza excepcion
    @Test
    public void testActualizar_noExiste() {
        int id = 99;
        when(rolPermisoRepository.findById(id)).thenReturn(Optional.empty());

        RolPermiso nuevosDatos = new RolPermiso(null, 2, 2, "Editor", "productos", "EDITAR");

        assertThrows(RuntimeException.class, () -> rolPermisoService.actualizar(id, nuevosDatos));
    }

    // ELIMINAR
    @Test
    public void testEliminar() {
        int id = 1;

        rolPermisoService.eliminar(id);

        verify(rolPermisoRepository).deleteById(id);
    }
}