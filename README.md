# Informe de Pruebas Automatizadas - HealthTrack

**Autor:** Juan José Abarca
**BootCamp:** DevOps TD 2025

Este documento presenta el **Análisis del Estado Actual de la Plataforma** HealthTrack, realizado como parte de la Evaluación del Módulo 4. A continuación se describen los hallazgos sobre la lógica del código, su impacto en la experiencia del usuario, las carencias en los procesos de validación y pruebas, así como la comparación entre la versión original y la corregida de la clase `Usuario`.

---

## 🧠 Análisis del Estado Actual de la Plataforma

### 1. Descripción del Error en la Lógica del Código

En la clase `Usuario`, el método `actualizarPeso(double nuevoPeso)` no registra el valor proporcionado por el usuario. En su lugar, **se resta siempre 1 kg** al peso actual:

```java
public void actualizarPeso(double nuevoPeso) {
    // ERROR: resta fija de 1 kg en lugar de usar nuevoPeso
    this.peso -= 1;
}
```

Este comportamiento es incorrecto y no refleja la intención real de actualizar el peso al valor ingresado.

---

### 2. Impacto del Error en la Experiencia del Usuario

* **Resultados Equivocados:** Cada actualización muestra un peso 1 kg inferior al registrado.
* **Desconfianza:** Los usuarios perderán la confianza en la plataforma al ver datos incoherentes.
* **Pérdida de Motivación:** Seguimiento de metas de salud se vuelve inútil.
* **Riesgo Clínico:** En entornos médicos, datos erróneos pueden derivar en decisiones incorrectas.

---

### 3. Falta de Procesos de Validación y Pruebas

* **Sin pruebas unitarias:** No hay tests para verificar la lógica de `actualizarPeso`.
* **Sin pruebas de integración:** No se comprueba la interacción con otros módulos (por ejemplo, repositorios o servicios).
* **Sin pruebas funcionales:** No se simulan flujos completos de usuario para detectar errores de negocio.
* **Sin pruebas de regresión:** Cambios futuros pueden reintroducir este o nuevos errores sin detección.
* **Sin CI/CD:** El código defectuoso llega a producción sin validación automática.

---

### 4. Versión Original vs. Versión Corregida

#### Archivo Original

```java
public class Usuario {
    private String nombre;
    private double peso;

    public Usuario(String nombre, double peso) {
        this.nombre = nombre;
        this.peso = peso;
    }

    public void actualizarPeso(double nuevoPeso) {
        // ERROR: En lugar de asignar el nuevo peso, se está restando 1kg.
        this.peso -= 1;
    }
    // ... resto de la clase
}
```

#### Archivo Corregido

```java
public class Usuario {
    private String nombre;
    private double peso;

    public Usuario(String nombre, double peso) {
        this.nombre = nombre;
        this.peso = peso;
    }

    public void actualizarPeso(double nuevoPeso) {
        // CORRECCIÓN: asigna el valor ingresado por el usuario
        this.peso = nuevoPeso;
    }
    // ... resto de la clase
}
```
---

# 🧪 Diseño y Desarrollo de Pruebas Automatizadas

En esta sección describimos cómo añadiremos distintos tipos de pruebas a la plataforma **HealthTrack** y cómo las integraremos en GitHub Actions.

---

## 1. Pruebas Unitarias (JUnit)

Creamos una clase de test para validar que `actualizarPeso` guarda correctamente el nuevo valor:

```java
// src/test/java/com/healthtrack/UsuarioTest.java
package com.healthtrack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {
    private Usuario usuario;

    @BeforeEach
    void setUp() {
        usuario = new Usuario("Ana", 70.0);
    }

    @Test
    void actualizarPeso_conValorValido_actualizaCorrectamente() {
        usuario.actualizarPeso(68.5);
        assertEquals(68.5, usuario.getPeso(), 0.001);
    }

    @Test
    void actualizarPeso_multiplesLlamadas_soloReemplazaValor() {
        usuario.actualizarPeso(68.5);
        usuario.actualizarPeso(67.0);
        assertEquals(67.0, usuario.getPeso(), 0.001);
    }
}
