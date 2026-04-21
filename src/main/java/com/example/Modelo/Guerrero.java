package com.example.Modelo;

public class Guerrero extends Criatura {

    /** Nombre del arma cuerpo a cuerpo que lleva (descripción textual interna) */
    private String armaNombre;

    /** Arma por composición; puede ser equipada o desequipada dinámicamente */
    private Arma arma;

    /**
     * Constructor del Guerrero.
     *
     * @param nombre   
     * @param salud   
     * @param fuerza    
     * @param armaNombre 
     */
    public Guerrero(String nombre, int salud, int fuerza, String armaNombre) {
        super(nombre, salud, fuerza);
        this.armaNombre = armaNombre;
    }

    //Implementación abstracta

    /**
     * El guerrero ataca con su espada/hacha causando daño igual a su fuerza.
     * Si porta un arma equipada, suma el daño adicional de ésta.
     */
    @Override
    public void atacar(Criatura objetivo) {
        int dañoBase  = fuerza;
        int dañoTotal = dañoBase;

        System.out.println("\n" + nombre + " ATACA con su " + armaNombre
                + " a " + objetivo.getNombre() + "!");

        if (arma != null) {
            arma.atacarConArma(objetivo);
            dañoTotal += arma.getDañoAdicional();
        }

        System.out.println("  Daño infligido: " + dañoTotal
                + (arma != null ? " (base=" + dañoBase + " + arma=" + arma.getDañoAdicional() + ")" : ""));

        objetivo.defender(dañoTotal);
    }

    /**
     * El guerrero se defiende con su armadura, reduciendo el daño en un 30 %.
     * Es el defensor más resistente entre las tres criaturas base.
     */
    @Override
    public void defender(int daño) {
        int armadura  = (int) (daño * 0.30);         
        int dañoFinal = daño - armadura;
        salud        -= dañoFinal;
        if (salud < 0) salud = 0;

        System.out.println("" + nombre + " bloquea con su armadura (-" + armadura
                + " de daño). Recibe " + dañoFinal + ". Salud restante: " + salud);
    }

    //Gestión de arma (composición)

    public void equiparArma(Arma arma) {
        this.arma = arma;
        System.out.println("" + nombre + " equipa: " + arma);
    }

    public void desequiparArma() {
        System.out.println("" + nombre + " desequipa: " + (arma != null ? arma : "ninguna"));
        this.arma = null;
    }

    //Getters
    public String getArmaNombre() { return armaNombre; }
    public Arma   getArma()       { return arma;       }
}
