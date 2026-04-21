package com.example.Modelo;

/**
 * Clase Arma
 * Representa un arma que las criaturas pueden equipar.
 *
 * Decisión de diseño: se usa COMPOSICIÓN (no herencia) para que las criaturas
 * "tengan" un arma en lugar de "ser" un arma. Esto permite equipar/desequipar
 * armas dinámicamente sin alterar la jerarquía de clases.
 */
public class Arma {

    /** Nombre del arma (p.ej. "Espada", "Bastón Mágico", "Garra de Fuego") */
    private String nombre;

    /** Daño adicional que suma al ataque base de la criatura */
    private int dañoAdicional;

    /**
     * Constructor del arma.
     *
     * @param nombre        nombre descriptivo del arma
     * @param dañoAdicional puntos de daño extra que aporta
     */
    public Arma(String nombre, int dañoAdicional) {
        this.nombre = nombre;
        this.dañoAdicional = dañoAdicional;
    }

    /**
     * Simula el ataque con el arma sobre un objetivo.
     * Imprime en consola el efecto del ataque.
     *
     * @param objetivo criatura que recibe el impacto del arma
     */
    public void atacarConArma(Criatura objetivo) {
        System.out.println("  ⚔️  [ARMA: " + nombre + "] golpea a "
                + objetivo.getNombre() + " causando +" + dañoAdicional + " de daño adicional!");
    }

    /**
     * Retorna el daño adicional que proporciona esta arma.
     *
     * @return daño adicional como entero positivo
     */
    public int getDañoAdicional() {
        return dañoAdicional;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre + " (+" + dañoAdicional + " daño)";
    }
}
