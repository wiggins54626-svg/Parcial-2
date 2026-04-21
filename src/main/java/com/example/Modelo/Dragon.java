package com.example.Modelo;

import com.example.Interfaces.Volador;

/**
 * Clase Dragon
 * Criatura poderosa que implementa la interfaz Volador.
 *
 * Decisión de diseño:
 * - Hereda de Criatura para obtener los atributos comunes.
 * - Implementa Volador porque los dragones pueden volar; esto no se fuerza
 *   en la clase base ya que no todas las criaturas vuelan.
 * - Usa COMPOSICIÓN para portar un Arma (garra/mordida de fuego).
 * - Su ataque es el doble de su fuerza base (fuerza * 2), haciéndolo
 *   el atacante más peligroso del sistema.
 */
public class Dragon extends Criatura implements Volador {

    /** Material de las escamas; afecta la reducción de daño al defender */
    private String escamas;

    /**
     * Arma por composición: el dragón puede equipar una garra mágica
     * u otro arma además de su ataque natural.
     */
    private Arma arma;

    /** Indica si el dragón está actualmente en vuelo */
    private boolean enVuelo;

    /**
     * Constructor del Dragón.
     *
     * @param nombre  nombre del dragón
     * @param salud   salud inicial
     * @param fuerza  fuerza base (el ataque real será fuerza*2)
     * @param escamas descripción del tipo de escamas
     */
    public Dragon(String nombre, int salud, int fuerza, String escamas) {
        super(nombre, salud, fuerza);
        this.escamas  = escamas;
        this.enVuelo  = false;
    }

    // ──────────────────────────── Implementación abstracta ──────────────────────

    /**
     * El dragón ataca con fuerza doble (fuerza * 2) y,
     * si tiene arma equipada, suma el daño adicional de ésta.
     */
    @Override
    public void atacar(Criatura objetivo) {
        int dañoBase  = fuerza * 2;                          // multiplicador del dragón
        int dañoTotal = dañoBase;

        System.out.println("\n" + nombre + " lanza un ATAQUE DE FUEGO sobre " + objetivo.getNombre() + "!");

        if (arma != null) {
            arma.atacarConArma(objetivo);
            dañoTotal += arma.getDañoAdicional();
        }

        System.out.println("  Daño infligido: " + dañoTotal + " (fuerza*2=" + dañoBase
                + (arma != null ? " + arma=" + arma.getDañoAdicional() : "") + ")");

        objetivo.defender(dañoTotal);
    }

    /**
     * El dragón se defiende usando sus resistentes escamas,
     * reduciendo el daño en un 20 % (redondeado hacia abajo).
     */
    @Override
    public void defender(int daño) {
        int reduccion  = (int) (daño * 0.20);               // las escamas absorben 20%
        int dañoFinal  = daño - reduccion;
        salud         -= dañoFinal;
        if (salud < 0) salud = 0;

        System.out.println("" + nombre + " usa sus escamas (" + escamas + ") y reduce "
                + reduccion + " de daño. Recibe " + dañoFinal + ". Salud restante: " + salud);
    }

    // ──────────────────────────── Interfaz Volador ──────────────────────────────

    @Override
    public void volar() {
        enVuelo = true;
        System.out.println("" + nombre + " despliega sus alas y VUELA sobre el campo de batalla!");
    }

    @Override
    public void aterrizar() {
        enVuelo = false;
        System.out.println("" + nombre + " aterriza con un estruendo!");
    }

    // ──────────────────────────── Gestión de arma (composición) ─────────────────

    /** Equipa un arma al dragón. */
    public void equiparArma(Arma arma) {
        this.arma = arma;
        System.out.println("" + nombre + " equipa: " + arma);
    }

    /** Desequipa el arma actual. */
    public void desequiparArma() {
        System.out.println("" + nombre + " desequipa: " + (arma != null ? arma : "ninguna"));
        this.arma = null;
    }

    // ──────────────────────────── Getters ───────────────────────────────────────

    public String getEscamas()  { return escamas; }
    public Arma   getArma()     { return arma;    }
    public boolean isEnVuelo()  { return enVuelo; }
}