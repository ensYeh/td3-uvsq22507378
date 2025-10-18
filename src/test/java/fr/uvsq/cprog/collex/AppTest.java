package fr.uvsq.cprog.collex;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void Dnsconstructor(){
        assertTrue(false);
    }

    @Test
    public void nomMachine(){
        NomMachine m= new NomMachine("test");
        assertEquals("test", m.getNomMachine());
    }

    @Test
    public void adresseIp(){
        AdresseIP ip=new AdresseIP("192.168.1.1");
        assertEquals("192.168.1.1",ip.getipAdress());
    }

    @Test
    public void addItem(){
        assertTrue(false);
    }

    @Test
    public void addItemes(){
        assertTrue(false);

    }

    @Test

    public void testUi(){
        assertTrue(false);
    }
}