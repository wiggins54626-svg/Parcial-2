package com.example.Modelo;

public class Arma {

    private String nombre;

    private int dañoAdicional;

    /**
     * Constructor del arma.
     *
     * @param nombre        
     * @param dañoAdicional 
     */
    public Arma(String nombre, int dañoAdicional) {
        this.nombre = nombre;
        this.dañoAdicional = dañoAdicional;
    }

    /**
     * 
     * 
     *
     * @param objetivo criatura que recibe el impacto del arma
     */
    public void atacarConArma(Criatura objetivo) {
        System.out.println(" [ARMA: " + nombre + "] golpea a "
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
