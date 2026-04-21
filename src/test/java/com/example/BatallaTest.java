package com.example;

import com.example.Modelo.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias con JUnit 5
 * Cubre: Arma, Criatura (abstracta vía subclases), Dragon, Mago, Guerrero.
 *
 * Instalación: ya incluida en pom.xml con junit-jupiter 5.10.1.
 * Ejecución:   mvn test
 */
@DisplayName("Sistema de Batallas - Pruebas Unitarias")
class BatallaTest {

    //Arma

    @Nested
    @DisplayName("Pruebas de Arma")
    class ArmaTest {

        @Test
        @DisplayName("El arma se crea con nombre y daño correcto")
        void testCreacionArma() {
            Arma espada = new Arma("Espada", 15);
            assertEquals("Espada", espada.getNombre());
            assertEquals(15, espada.getDañoAdicional());
        }

        @Test
        @DisplayName("El daño adicional no puede ser negativo (valor 0 es válido)")
        void testDañoAdicionalCero() {
            Arma sinDaño = new Arma("Palo de madera", 0);
            assertEquals(0, sinDaño.getDañoAdicional());
        }
    }

    //Dragon

    @Nested
    @DisplayName("Pruebas de Dragon")
    class DragonTest {

        Dragon dragon;

        @BeforeEach
        void setUp() {
            dragon = new Dragon("Testdragón", 100, 20, "Escamas de Acero");
        }

        @Test
        @DisplayName("El dragón se crea con los atributos correctos")
        void testCreacion() {
            assertEquals("Testdragón", dragon.getNombre());
            assertEquals(100, dragon.getSalud());
            assertEquals(20, dragon.getFuerza());
            assertEquals("Escamas de Acero", dragon.getEscamas());
            assertFalse(dragon.isEnVuelo());
        }

        @Test
        @DisplayName("El dragón reduce 20% del daño al defender")
        void testDefender() {
            // daño=50 → reducción=10 → daño final=40 → salud=100-40=60
            dragon.defender(50);
            assertEquals(60, dragon.getSalud());
        }

        @Test
        @DisplayName("La salud del dragón no baja de 0")
        void testSaludNoNegativa() {
            dragon.defender(10000);
            assertEquals(0, dragon.getSalud());
        }

        @Test
        @DisplayName("El dragón ataca causando fuerza*2 al objetivo")
        void testAtacar() {
            Dragon objetivo = new Dragon("Objetivo", 200, 10, "Escamas normales");
            // fuerza=20 → daño=40 → objetivo defiende con -20% → daño final=32
            dragon.atacar(objetivo);
            assertEquals(168, objetivo.getSalud()); // 200 - 32
        }

        @Test
        @DisplayName("El arma equipada suma daño adicional al ataque")
        void testAtacarConArma() {
            dragon.equiparArma(new Arma("Garra+10", 10));
            Dragon objetivo = new Dragon("Objetivo", 200, 10, "normal");
            // fuerza*2=40 + arma=10 = 50 → reduce 20% → 40 de daño → salud=200-40=160
            dragon.atacar(objetivo);
            assertEquals(160, objetivo.getSalud());
        }

        @Test
        @DisplayName("El dragón puede volar y aterrizar")
        void testVolar() {
            dragon.volar();
            assertTrue(dragon.isEnVuelo());
            dragon.aterrizar();
            assertFalse(dragon.isEnVuelo());
        }

        @Test
        @DisplayName("estaViva() retorna false cuando salud = 0")
        void testEstaViva() {
            dragon.defender(10000);
            assertFalse(dragon.estaViva());
        }
    }

    //Mago

    @Nested
    @DisplayName("Pruebas de Mago")
    class MagoTest {

        Mago mago;

        @BeforeEach
        void setUp() {
            mago = new Mago("Testmago", 80, 25);
        }

        @Test
        @DisplayName("El mago se crea sin hechizos por defecto")
        void testCreacion() {
            assertTrue(mago.getHechizos().isEmpty());
        }

        @Test
        @DisplayName("aprenderHechizo() agrega hechizo a la lista")
        void testAprenderHechizo() {
            mago.aprenderHechizo("Rayo");
            mago.aprenderHechizo("Fuego");
            assertEquals(2, mago.getHechizos().size());
            assertTrue(mago.getHechizos().contains("Rayo"));
        }

        @Test
        @DisplayName("El mago reduce 10% del daño al defender")
        void testDefender() {
            // daño=50 → escudo=5 → daño final=45 → salud=80-45=35
            mago.defender(50);
            assertEquals(35, mago.getSalud());
        }

        @Test
        @DisplayName("La salud del mago no baja de 0")
        void testSaludNoNegativa() {
            mago.defender(99999);
            assertEquals(0, mago.getSalud());
        }

        @Test
        @DisplayName("El mago ataca causando su fuerza base al objetivo")
        void testAtacar() {
            Guerrero objetivo = new Guerrero("Objetivo", 200, 10, "Espada");
            // fuerza=25 → objetivo reduce 30% → 17 de daño → salud=200-17=183
            mago.atacar(objetivo);
            assertEquals(183, objetivo.getSalud()); // 200 - floor(25*0.7)=17
        }

        @ParameterizedTest
        @ValueSource(strings = {"Bola de Fuego", "Rayo Glacial", "Tormenta"})
        @DisplayName("aprenderHechizo() acepta distintos nombres de hechizo")
        void testAprenderVariosHechizos(String hechizo) {
            mago.aprenderHechizo(hechizo);
            assertTrue(mago.getHechizos().contains(hechizo));
        }
    }

    //Guerrero

    @Nested
    @DisplayName("Pruebas de Guerrero")
    class GuerreroTest {

        Guerrero guerrero;

        @BeforeEach
        void setUp() {
            guerrero = new Guerrero("Testguerrero", 120, 30, "Espada");
        }

        @Test
        @DisplayName("El guerrero se crea con atributos correctos")
        void testCreacion() {
            assertEquals("Testguerrero", guerrero.getNombre());
            assertEquals(120, guerrero.getSalud());
            assertEquals(30, guerrero.getFuerza());
            assertNull(guerrero.getArma());
        }

        @Test
        @DisplayName("El guerrero reduce 30% del daño al defender")
        void testDefender() {
            // daño=50 → armadura=15 → daño final=35 → salud=120-35=85
            guerrero.defender(50);
            assertEquals(85, guerrero.getSalud());
        }

        @Test
        @DisplayName("La salud del guerrero no baja de 0")
        void testSaludNoNegativa() {
            guerrero.defender(99999);
            assertEquals(0, guerrero.getSalud());
            assertFalse(guerrero.estaViva());
        }

        @Test
        @DisplayName("Equipar arma asigna correctamente la referencia")
        void testEquiparArma() {
            Arma hacha = new Arma("Hacha Rúnica", 12);
            guerrero.equiparArma(hacha);
            assertNotNull(guerrero.getArma());
            assertEquals("Hacha Rúnica", guerrero.getArma().getNombre());
        }

        @Test
        @DisplayName("Desequipar arma deja la referencia en null")
        void testDesequiparArma() {
            guerrero.equiparArma(new Arma("Espada", 10));
            guerrero.desequiparArma();
            assertNull(guerrero.getArma());
        }

        @Test
        @DisplayName("estaViva() retorna true cuando salud > 0")
        void testEstaVivaTrue() {
            assertTrue(guerrero.estaViva());
        }
    }

    //Polimorfismo

    @Nested
    @DisplayName("Pruebas de polimorfismo")
    class PolimorfismoTest {

        @Test
        @DisplayName("Dragon, Mago y Guerrero son instancias de Criatura")
        void testHerencia() {
            Criatura d = new Dragon("D", 100, 10, "Escamas");
            Criatura m = new Mago("M", 100, 10);
            Criatura g = new Guerrero("G", 100, 10, "Espada");

            assertInstanceOf(com.example.Modelo.Criatura.class, d);
            assertInstanceOf(com.example.Modelo.Criatura.class, m);
            assertInstanceOf(com.example.Modelo.Criatura.class, g);
        }

        @Test
        @DisplayName("Dragon implementa la interfaz Volador")
        void testDragonEsVolador() {
            Criatura dragon = new Dragon("D", 100, 10, "Escamas");
            assertInstanceOf(com.example.Interfaces.Volador.class, dragon);
        }

        @Test
        @DisplayName("Mago implementa la interfaz Magico")
        void testMagoEsMagico() {
            Criatura mago = new Mago("M", 100, 10);
            assertInstanceOf(com.example.Interfaces.Magico.class, mago);
        }

        @Test
        @DisplayName("Guerrero NO implementa Volador ni Magico")
        void testGuerreroNoVuelaNoEsMagico() {
            Criatura guerrero = new Guerrero("G", 100, 10, "Espada");
            assertFalse(guerrero instanceof com.example.Interfaces.Volador);
            assertFalse(guerrero instanceof com.example.Interfaces.Magico);
        }
    }
}
