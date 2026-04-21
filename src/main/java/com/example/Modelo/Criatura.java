package com.example.Modelo;

public abstract class Criatura {

    protected String nombre;
    protected int salud;
    protected int fuerza;

    /**
     * Constructor base para todas las criaturas.
     *
     * @param nombre
     * @param salud  
     * @param fuerza 
     */
    public Criatura(String nombre, int salud, int fuerza) {
        this.nombre  = nombre;
        this.salud   = salud;
        this.fuerza  = fuerza;
    }

    //Métodos abstractos

    /**
     * Ataca a la criatura objetivo.
     * Cada subclase implementa su estilo único de ataque.
     *
     * @param objetivo criatura que recibe el ataque
     */
    public abstract void atacar(Criatura objetivo);

    /**
     * Se defiende de un ataque recibido restando el daño a la salud.
     * Cada subclase puede aplicar bonificaciones defensivas propias.
     *
     * @param daño puntos de daño recibidos (equivale a la fuerza del atacante)
     */
    public abstract void defender(int daño);

    //Método concreto

    /**
     * Indica si la criatura sigue viva.
     *
     * @return true si salud > 0, false en caso contrario
     */
    public boolean estaViva() {
        return salud > 0;
    }

    //Getters y Setters

    public String getNombre() { return nombre; }
    public int getSalud()     { return salud;  }
    public int getFuerza()    { return fuerza; }

    //Permite modificar la salud externamente (p.ej. curación o penalización)
    public void setSalud(int salud) { this.salud = salud; }

    @Override
    public String toString() {
        return nombre + " [Salud: " + salud + " | Fuerza: " + fuerza + "]";
    }
}