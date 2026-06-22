package com.example.MicroUsuarioSeguridad;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.MicroUsuarioSeguridad.model.RolPermiso;
import com.example.MicroUsuarioSeguridad.repository.RolPermisoRepository;
import com.example.MicroUsuarioSeguridad.service.RolPermisoService;

@SpringBootTest
public class RolPermisoServiceTest {

    // Inyecta el servicio de Carrera para ser probado.
    @Autowired
    private RolPermisoService rolPermisoService;

    // Crea un mock del repositorio de Carrera para simular su comportamiento.
    @Mock
    private RolPermisoRepository rolPermisoRepository;

    @Test
    public void testFindAll() {
        // Define el comportamiento del mock: cuando se llame a findAll(), devuelve una lista con una Carrera.
        when(rolPermisoRepository.findAll()).thenReturn(List.of(new RolPermiso(1, "Administrador")));

        // Llama al método findAll() del servicio.
        List<RolPermiso> rolPermisos = rolPermisoService.findAll();

        // Verifica que la lista devuelta no sea nula y contenga exactamente una Carrera.
        assertNotNull(rolPermisos);
        assertEquals(1, rolPermisos.size());
    }

    @Test
    public void testFindByCodigo() {
        String codigo = "1";
        RolPermiso rolPermiso = new RolPermiso(codigo, "Administrador");

        // Define el comportamiento del mock: cuando se llame a findById() con "1", devuelve una Carrera opcional.
        when(rolPermisoRepository.findById(codigo)).thenReturn(Optional.of(rolPermiso));

        // Llama al método findByCodigo() del servicio.
        RolPermiso found = rolPermisoService.findByCodigo(codigo);

        // Verifica que la Carrera devuelta no sea nula y que su código coincida con el código esperado.
        assertNotNull(found);
        assertEquals(codigo, found.getId_rol());
    }

    @Test
    public void testSave() {
        RolPermiso rolPermiso = new RolPermiso(1, "Administrador");

        // Define el comportamiento del mock: cuando se llame a save(), devuelve la Carrera proporcionada.
        when(rolPermisoRepository.save(rolPermiso)).thenReturn(rolPermiso);

        // Llama al método save() del servicio.
        RolPermiso saved = rolPermisoService.save(rolPermiso);

        // Verifica que la Carrera guardada no sea nula y que su nombre coincida con el nombre esperado.
        assertNotNull(saved);
        assertEquals("Administrador", saved.getNombre());
    }

    @Test
    public void testDeleteByCodigo() {
        String codigo = "1";

        // Define el comportamiento del mock: cuando se llame a deleteById(), no hace nada.
        doNothing().when(rolPermisoRepository).deleteById(codigo);

        // Llama al método deleteByCodigo() del servicio.
        rolPermisoService.deleteByCodigo(codigo);

        // Verifica que el método deleteById() del repositorio se haya llamado exactamente una vez con el código proporcionado.
        verify(rolPermisoRepository, times(1)).deleteById(codigo);
    }
}