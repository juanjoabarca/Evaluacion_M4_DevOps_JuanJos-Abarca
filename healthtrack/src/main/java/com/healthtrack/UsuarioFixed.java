package com.healthtrack;

/**
 * Versión corregida:
 * ahora actualizarPeso asigna el valor ingresado por el usuario.
 */
public class UsuarioFixed {
    private String nombre;
    private double peso;

    public UsuarioFixed(String nombre, double peso) {
        this.nombre = nombre;
        this.peso = peso;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPeso() {
        return peso;
    }

    public void actualizarPeso(double nuevoPeso) {
        // CORRECCIÓN: asigna el valor ingresado
        this.peso = nuevoPeso;
    }
}