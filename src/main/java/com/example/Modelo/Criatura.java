package com.example.Modelo;

/**
 * Clase Abstracta Criatura
 * Define la plantilla común para todas las criaturas del juego.
 *
 * Decisión de diseño: se usa clase abstracta (no interfaz) porque
 * las criaturas comparten estado (nombre, salud, fuerza) y un método
 * concreto (estaViva). Los métodos abstractos obligan a cada subclase
 * a implementar su propia lógica de ataque y defensa.
 */
public abstract class Criatura {

    /** Nombre identificador de la criatura */
    protected String nombre;

    /** Puntos de salud actuales; cuando llega a 0 la criatura muere */
    protected int salud;

    /** Fuerza base usada para calcular el daño de ataque */
    protected int fuerza;

    /**
     * Constructor base para todas las criaturas.
     *
     * @param nombre nombre de la criatura
     * @param salud  salud inicial
     * @param fuerza fuerza de ataque base
     */
    public Criatura(String nombre, int salud, int fuerza) {
        this.nombre  = nombre;
        this.salud   = salud;
        this.fuerza  = fuerza;
    }

    // ──────────────────────────── Métodos abstractos ────────────────────────────

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

    // ──────────────────────────── Método concreto ───────────────────────────────

    /**
     * Indica si la criatura sigue viva.
     *
     * @return true si salud > 0, false en caso contrario
     */
    public boolean estaViva() {
        return salud > 0;
    }

    // ──────────────────────────── Getters y Setters ─────────────────────────────

    public String getNombre() { return nombre; }
    public int getSalud()     { return salud;  }
    public int getFuerza()    { return fuerza; }

    /** Permite modificar la salud externamente (p.ej. curación o penalización) */
    public void setSalud(int salud) { this.salud = salud; }

    @Override
    public String toString() {
        return nombre + " [Salud: " + salud + " | Fuerza: " + fuerza + "]";
    }
}