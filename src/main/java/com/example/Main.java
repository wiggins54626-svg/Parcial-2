package com.example;

import com.example.Modelo.*;
import com.example.Simulacion.Simulacion;

/**
 * Clase Principal (Main)
 * Crea instancias de las criaturas, las equipa y simula las batallas.
 *
 * Decisión de diseño: Main únicamente orquesta; la lógica de combate
 * vive en Simulacion y la lógica de cada criatura en su clase concreta.
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║       SISTEMA DE BATALLAS - PARCIAL II       ║");
        System.out.println("║         Programación II  -  G411             ║");
        System.out.println("╚══════════════════════════════════════════════╝\n");

        // ── Crear armas ──────────────────────────────────────────────────────────
        Arma garraFuego   = new Arma("Garra de Fuego",   20);
        Arma bastonArcano = new Arma("Bastón Arcano",    15);
        Arma espadaGrande = new Arma("Espada Rúnica",    10);

        // ── Crear criaturas ──────────────────────────────────────────────────────
        Dragon  smaug    = new Dragon("Smaug",       150, 40, "Escamas de Obsidiana");
        Mago    gandalf  = new Mago("Gandalf",       100, 35);
        Guerrero thorin  = new Guerrero("Thorin",    120, 30, "Hacha de Guerra");

        // ── Equipar armas (composición) ──────────────────────────────────────────
        smaug.equiparArma(garraFuego);
        gandalf.equiparArma(bastonArcano);
        thorin.equiparArma(espadaGrande);

        // ── El mago aprende hechizos ──────────────────────────────────────────────
        gandalf.aprenderHechizo("Bola de Fuego");
        gandalf.aprenderHechizo("Rayo Glacial");

        // ════════════════ BATALLA 1: Dragón vs Guerrero ═════════════════════════
        System.out.println("═══════════════  BATALLA 1  ═══════════════════");
        // Clonamos valores para no afectar la batalla siguiente
        Dragon  dragon1  = new Dragon("Smaug",  150, 40, "Escamas de Obsidiana");
        Guerrero gue1    = new Guerrero("Thorin", 120, 30, "Hacha de Guerra");
        dragon1.equiparArma(new Arma("Garra de Fuego", 20));
        gue1.equiparArma(new Arma("Espada Rúnica", 10));

        Simulacion.simularBatalla(dragon1, gue1);

        // ════════════════ BATALLA 2: Mago vs Guerrero ══════════════════════════
        System.out.println("═══════════════  BATALLA 2  ═══════════════════");
        Mago    mago2    = new Mago("Gandalf", 100, 35);
        Guerrero gue2    = new Guerrero("Thorin", 120, 30, "Hacha de Guerra");
        mago2.equiparArma(new Arma("Bastón Arcano", 15));
        mago2.aprenderHechizo("Tormenta Eléctrica");
        gue2.equiparArma(new Arma("Espada Rúnica", 10));

        Simulacion.simularBatalla(mago2, gue2);

        // ════════════════ BATALLA 3: Dragón vs Mago ═════════════════════════════
        System.out.println("═══════════════  BATALLA 3  ═══════════════════");
        Dragon  dragon3  = new Dragon("Ancalagon", 180, 45, "Escamas de Dragonsteel");
        Mago    mago3    = new Mago("Saruman",     110, 38);
        dragon3.equiparArma(new Arma("Colmillo de Lava", 25));
        mago3.equiparArma(new Arma("Orbe de Palantír", 18));
        mago3.aprenderHechizo("Explosión Arcana");
        mago3.aprenderHechizo("Maldición Oscura");

        Simulacion.simularBatalla(dragon3, mago3);

        System.out.println("\n  Todas las batallas han finalizado.");
    }
}