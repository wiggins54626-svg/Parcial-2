package com.example.Modelo;

import com.example.Interfaces.Magico;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase Mago
 * Criatura mágica que implementa la interfaz Magico.
 *
 * Decisión de diseño:
 * - Implementa Magico para obtener lanzarHechizo() y aprenderHechizo(),
 *   comportamientos que no pertenecen a la clase base abstracta.
 * - Su ataque usa fuerza base (sin multiplicador), pero puede complementarse
 *   con el arma equipada (bastón mágico, orbe, etc.).
 * - Mantiene una lista interna de hechizos aprendidos.
 */
public class Mago extends Criatura implements Magico {

    /** Colección de hechizos que el mago ha aprendido */
    private List<String> hechizos;

    /** Arma por composición (bastón mágico, libro de hechizos, etc.) */
    private Arma arma;

    /**
     * Constructor del Mago.
     *
     * @param nombre nombre del mago
     * @param salud  salud inicial
     * @param fuerza fuerza mágica base
     */
    public Mago(String nombre, int salud, int fuerza) {
        super(nombre, salud, fuerza);
        this.hechizos = new ArrayList<>();
    }

    // ──────────────────────────── Implementación abstracta ──────────────────────

    /**
     * El mago ataca lanzando un hechizo con daño igual a su fuerza base.
     * Si tiene arma, suma el daño adicional de ésta.
     */
    @Override
    public void atacar(Criatura objetivo) {
        int dañoBase  = fuerza;
        int dañoTotal = dañoBase;

        System.out.println("\n✨ " + nombre + " lanza un HECHIZO ARCANO sobre " + objetivo.getNombre() + "!");
        lanzarHechizo();                                     // efecto visual/narrativo

        if (arma != null) {
            arma.atacarConArma(objetivo);
            dañoTotal += arma.getDañoAdicional();
        }

        System.out.println("  Daño infligido: " + dañoTotal
                + (arma != null ? " (base=" + dañoBase + " + arma=" + arma.getDañoAdicional() + ")" : ""));

        objetivo.defender(dañoTotal);
    }

    /**
     * El mago se defiende con un escudo mágico que absorbe un 10 % del daño.
     */
    @Override
    public void defender(int daño) {
        int escudo    = (int) (daño * 0.10);
        int dañoFinal = daño - escudo;
        salud        -= dañoFinal;
        if (salud < 0) salud = 0;

        System.out.println("  🔮 " + nombre + " activa un escudo mágico (-" + escudo
                + " de daño). Recibe " + dañoFinal + ". Salud restante: " + salud);
    }

    // ──────────────────────────── Interfaz Magico ───────────────────────────────

    /**
     * Lanza el hechizo más reciente aprendido, o uno genérico si no tiene.
     */
    @Override
    public void lanzarHechizo() {
        if (hechizos.isEmpty()) {
            System.out.println("  📖 " + nombre + " lanza: ¡Rayo Arcano!");
        } else {
            String ultimo = hechizos.get(hechizos.size() - 1);
            System.out.println("  📖 " + nombre + " lanza: ¡" + ultimo + "!");
        }
    }

    /**
     * Agrega un nuevo hechizo al repertorio del mago.
     *
     * @param nombreHechizo nombre del hechizo a aprender
     */
    @Override
    public void aprenderHechizo(String nombreHechizo) {
        hechizos.add(nombreHechizo);
        System.out.println("  📚 " + nombre + " aprende el hechizo: " + nombreHechizo);
    }

    // ──────────────────────────── Gestión de arma (composición) ─────────────────

    public void equiparArma(Arma arma) {
        this.arma = arma;
        System.out.println("⚔️  " + nombre + " equipa: " + arma);
    }

    public void desequiparArma() {
        System.out.println("⚔️  " + nombre + " desequipa: " + (arma != null ? arma : "ninguna"));
        this.arma = null;
    }

    // ──────────────────────────── Getters ───────────────────────────────────────

    public List<String> getHechizos() { return hechizos; }
    public Arma         getArma()     { return arma;     }
}
