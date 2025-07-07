package com.healthtrack;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {

    @Test
    void actualizarPesoDebeGuardarElNuevoValor() {
        UsuarioFixed u = new UsuarioFixed("Ana", 70.0);
        u.actualizarPeso(68.5);
        assertEquals(68.5, u.getPeso(), 0.0001);
    }

    @Test
    void actualizarPesoNoDeberiaRestarUnKilo() {
        UsuarioFixed u = new UsuarioFixed("Luis", 80.0);
        u.actualizarPeso(75.0);
        assertNotEquals(79.0, u.getPeso()); // no debe restar 1 kg
    }
}