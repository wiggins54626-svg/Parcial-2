package com.example.Interfaces;

/**
 * Interfaz Volador
 * Define el comportamiento de las criaturas que pueden volar.
 * Se usa como contrato independiente de la jerarquía de herencia,
 * permitiendo que cualquier criatura adopte la capacidad de volar
 * sin modificar la clase base.
 */
public interface Volador {

    /**
     * Hace que la criatura despegue y vuele.
     * Cada implementación define cómo realiza el vuelo.
     */
    void volar();

    /**
     * Hace que la criatura aterrice.
     * Cada implementación define cómo aterriza.
     */
    void aterrizar();
}
