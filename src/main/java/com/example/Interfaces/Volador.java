package com.example.Interfaces;

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
