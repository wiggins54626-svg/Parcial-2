package com.example.Simulacion;

import com.example.Interfaces.Magico;
import com.example.Interfaces.Volador;
import com.example.Modelo.Criatura;

/**
 * Clase Simulacion
 * Contiene la lógica para simular una batalla entre dos criaturas.
 *
 * Decisión de diseño: se separa de Main para respetar el principio de
 * responsabilidad única. La clase sólo sabe cómo hacer pelear; Main
 * sabe cómo configurar el escenario.
 */
public class Simulacion {

    /**
     * Simula una batalla por turnos entre dos criaturas.
     * La batalla continúa hasta que una de las dos muera (salud ≤ 0).
     * En cada turno se muestran las habilidades especiales disponibles
     * (vuelo, magia) antes del ataque.
     *
     * @param criatura1 primer combatiente
     * @param criatura2 segundo combatiente
     */
    public static void simularBatalla(Criatura criatura1, Criatura criatura2) {

        System.out.println("\n╔══════════════════════════════════════════════╗");
        System.out.println("║            INICIO DE BATALLA                 ║");
        System.out.println("╠══════════════════════════════════════════════╣");
        System.out.printf("║  %-20s  VS  %-20s║%n",
                criatura1.getNombre(), criatura2.getNombre());
        System.out.printf("║  Salud: %-6d            Salud: %-6d      ║%n",
                criatura1.getSalud(), criatura2.getSalud());
        System.out.println("╚══════════════════════════════════════════════╝");

        int turno = 1;

        while (criatura1.estaViva() && criatura2.estaViva()) {

            System.out.println("\n──────────── TURNO " + turno + " ────────────");

            // ── Turno de criatura1 ──
            if (criatura1.estaViva()) {
                activarHabilidadesEspeciales(criatura1);
                criatura1.atacar(criatura2);
            }

            // ── Turno de criatura2 (si sigue viva) ──
            if (criatura2.estaViva()) {
                activarHabilidadesEspeciales(criatura2);
                criatura2.atacar(criatura1);
            }

            turno++;

            // Seguridad: evita bucle infinito si ambas tienen reducción de daño alta
            if (turno > 100) {
                System.out.println("\n La batalla superó 100 turnos. Se declara EMPATE por agotamiento.");
                return;
            }
        }

        // ── Resultado final ──
        System.out.println("\n╔══════════════════════════════════════════════╗");
        System.out.println("║                 RESULTADO FINAL              ║");
        System.out.println("╠══════════════════════════════════════════════╣");

        if (!criatura1.estaViva() && !criatura2.estaViva()) {
            System.out.println("║             ¡EMPATE! Ambos cayeron.           ║");
        } else if (!criatura1.estaViva()) {
            System.out.printf("║  %-20s fue DERROTADO/A!          ║%n", criatura1.getNombre());
            System.out.printf("║  %-20s es el GANADOR            ║%n", criatura2.getNombre());
            System.out.printf("║  Salud restante: %-6d                       ║%n", criatura2.getSalud());
        } else {
            System.out.printf("║  %-20s fue DERROTADO/A!          ║%n", criatura2.getNombre());
            System.out.printf("║  %-20s es el GANADOR            ║%n", criatura1.getNombre());
            System.out.printf("║  Salud restante: %-6d                       ║%n", criatura1.getSalud());
        }
        System.out.println("╚══════════════════════════════════════════════╝\n");
    }

    /**
     * Activa habilidades especiales de la criatura antes de atacar:
     * - Si implementa Volador, vuela (y aterriza al final).
     * - Si implementa Magico, aprende un hechizo aleatorio.
     *
     * Decisión de diseño: se usa instanceof con las interfaces para
     * demostrar polimorfismo sin acoplar Simulacion a las subclases concretas.
     *
     * @param criatura criatura que va a activar sus habilidades
     */
    private static void activarHabilidadesEspeciales(Criatura criatura) {
        if (criatura instanceof Volador volador) {
            volador.volar();
            volador.aterrizar();
        }
        if (criatura instanceof Magico magico) {
            magico.lanzarHechizo();
        }
    }
}
