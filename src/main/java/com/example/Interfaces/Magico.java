package com.example.Interfaces;

/**
 * Interfaz Magico
 * Define el comportamiento de las criaturas con habilidades mágicas.
 * Separada de la clase base Criatura para no forzar a todas las
 * criaturas a tener magia; solo quienes la implementen poseen este poder.
 */
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
