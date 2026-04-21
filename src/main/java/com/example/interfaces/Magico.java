package com.example.Interfaces;

public interface Magico {

    /**
     * Lanza un hechizo ofensivo o de efecto.
     * Cada implementación define el tipo de hechizo.
     */
    void lanzarHechizo();

    /**
     * Permite a la criatura aprender un nuevo hechizo.
     *
     * @param nombreHechizo nombre del hechizo a aprender
     */
    void aprenderHechizo(String nombreHechizo);
}
