package com.healthtrack;

/**
 * Versión original con el bug:
 * al actualizar peso siempre le resta 1 kg en lugar de usar el nuevo valor.
 */
public class Usuario {
    private String nombre;
    private double peso;

    public Usuario(String nombre, double peso) {
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
        // ERROR: resta fija de 1 kg en lugar de usar nuevoPeso
        this.peso -= 1;
    }
}