package tests;

import modelo.universo.Carta;
import modelo.universo.Curandero;
import modelo.universo.Espadachin;
import modelo.universo.Mago;
import principal.Main;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CartaTest {

    @Test
    void ataqueCartaCorrecto() {
        Carta atacante = new Espadachin();
        Carta objetivo = new Espadachin();

        int defensaObj = objetivo.getDefensa();
        int saludObjAntes = objetivo.getSalud();
        int ataqueEfectivo = atacante.getAtaque() - defensaObj;
        if(ataqueEfectivo < 0)
            ataqueEfectivo = 5;
        atacante.atacar(objetivo);
        assertEquals(saludObjAntes - ataqueEfectivo, objetivo.getSalud());

        objetivo.matar();
        assertFalse(objetivo.estaVivo());

        int saludAntes = objetivo.getSalud();
        atacante.atacar(objetivo);
        assertEquals(saludAntes, objetivo.getSalud());
    }

    @Test
    void testHabilidadMago() {
        Carta origen = new Mago();
        Carta objetivo = new Espadachin();

        int saludAntes = objetivo.getSalud();
        origen.habilidadEspecial(objetivo);

        assertEquals(saludAntes, objetivo.getSalud());

        objetivo.matar();
        assertFalse(objetivo.estaVivo());
        origen.habilidadEspecial(objetivo);
        assertTrue(objetivo.estaVivo());
    }

    @Test
    void testHabilidadCurandero() {
        Carta origen = new Curandero();
        Carta objetivo = new Espadachin();

        int saludAntes = objetivo.getSalud();
        origen.habilidadEspecial(objetivo);

        assertEquals(saludAntes, objetivo.getSalud());

        objetivo.setSalud(50);
        saludAntes = objetivo.getSalud();
        origen.habilidadEspecial(objetivo);
        assertTrue(objetivo.getSalud() > saludAntes && objetivo.getSalud() < Carta.MAX_SALUD);
    }

    @Test
    void testHabilidadEspadachin() {
        Carta origen = new Espadachin();
        Carta objetivo1 = new Espadachin();
        Carta objetivo2 = new Espadachin();

        objetivo1.setDefensa(0);
        objetivo2.setDefensa(0);

        origen.atacar(objetivo1);
        origen.habilidadEspecial(objetivo2);

        assertTrue(objetivo1.getSalud() > objetivo2.getSalud());
    }

    @Test
    void testAddCartaMazo() {

    }
}