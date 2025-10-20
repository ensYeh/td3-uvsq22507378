package fr.uvsq.cprog.collex;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    /**
     * Rigorous Test :-)
     */
    private Dns dns;

    @Before
    public void setUp() {
        dns = new Dns();
    }

    // Tests AdresseIP
    @Test
    public void testCheckIpValid() {
        assertTrue(AdresseIP.checkIp("192.168.1.1"));
        assertTrue(AdresseIP.checkIp("0.0.0.0"));
        assertTrue(AdresseIP.checkIp("255.255.255.255"));
        assertTrue(AdresseIP.checkIp("193.51.25.12"));
    }

    @Test
    public void testCheckIpInvalid() {
        assertFalse(AdresseIP.checkIp("256.1.1.1"));
        assertFalse(AdresseIP.checkIp("192.168.1"));
        assertFalse(AdresseIP.checkIp("192.168.1.1.1"));
        assertFalse(AdresseIP.checkIp("abc.def.ghi.jkl"));
        assertFalse(AdresseIP.checkIp(""));
        assertFalse(AdresseIP.checkIp(null));
    }

    @Test
    public void testGetIpAddress() {
        AdresseIP ip = new AdresseIP("192.168.1.1");
        assertEquals("192.168.1.1", ip.getipAdress());
    }

    @Test
    public void testIpSame() {
        AdresseIP ip = new AdresseIP("192.168.1.1");
        assertTrue(ip.same("192.168.1.1"));
        assertFalse(ip.same("192.168.1.2"));
    }

    // Tests NomMachine
    @Test
    public void testCheckNomValid() {
        assertTrue(NomMachine.checkNom("example.com"));
        assertTrue(NomMachine.checkNom("ecampus.uvsq.fr"));
        assertTrue(NomMachine.checkNom("www.uvsq.fr"));
        assertTrue(NomMachine.checkNom("localhost"));
        assertTrue(NomMachine.checkNom("pikachu.uvsq.fr"));
    }

    @Test
    public void testCheckNomInvalid() {
        assertFalse(NomMachine.checkNom(""));
        assertFalse(NomMachine.checkNom(null));
        assertFalse(NomMachine.checkNom("-invalid.com"));
        assertFalse(NomMachine.checkNom("invalid-.com"));
        assertFalse(NomMachine.checkNom("in valid.com"));
    }

    @Test
    public void testCheckNomTooLong() {
        String longName = "a".repeat(254);
        assertFalse(NomMachine.checkNom(longName));
    }

    @Test
    public void testGetNomMachine() {
        NomMachine nom = new NomMachine("example.com");
        assertEquals("example.com", nom.getNomMachine());
    }

    @Test
    public void testNomSame() {
        NomMachine nom = new NomMachine("example.com");
        assertTrue(nom.same("example.com"));
        assertFalse(nom.same("other.com"));
    }

    // Tests DnsItem
    @Test
    public void testDnsItemValidCreation() {
        DnsItem item = new DnsItem("192.168.1.1", "example.com");
        assertNotNull(item.getIp());
        assertNotNull(item.getNom());
        assertEquals("192.168.1.1", item.getIp().getipAdress());
        assertEquals("example.com", item.getNom().getNomMachine());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDnsItemInvalidIp() {
        new DnsItem("256.256.256.256", "example.com");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDnsItemInvalidNom() {
        new DnsItem("192.168.1.1", "-invalid.com");
    }

    @Test
    public void testCompareIp() {
        DnsItem item = new DnsItem("192.168.1.1", "example.com");
        assertTrue(DnsItem.compareIp(item, "192.168.1.1"));
        assertFalse(DnsItem.compareIp(item, "192.168.1.2"));
    }

    @Test
    public void testCompareNom() {
        DnsItem item = new DnsItem("192.168.1.1", "example.com");
        assertTrue(DnsItem.compareNom(item, "example.com"));
        assertFalse(DnsItem.compareNom(item, "other.com"));
    }

    @Test
    public void testCompareDomain() {
        DnsItem item1 = new DnsItem("193.51.25.12", "ecampus.uvsq.fr");
        DnsItem item2 = new DnsItem("193.51.25.24", "pikachu.uvsq.fr");

        assertTrue(DnsItem.compareDomain(item1, "uvsq.fr"));
        assertTrue(DnsItem.compareDomain(item1, "ecampus.uvsq.fr"));
        assertTrue(DnsItem.compareDomain(item2, "uvsq.fr"));
        assertFalse(DnsItem.compareDomain(item1, "google.com"));
    }

    // Tests RechercheIPCommand
    @Test
    public void testRechercheIPExistant() {
        RechercheIPCommand cmd = new RechercheIPCommand("193.51.25.12");
        Object resultat = cmd.execute(dns);
        assertEquals("ecampus.uvsq.fr", resultat);
    }

    @Test
    public void testRechercheIPInexistant() {
        RechercheIPCommand cmd = new RechercheIPCommand("999.999.999.999");
        Object resultat = cmd.execute(dns);
        assertNull(resultat);
    }

    // Tests RechercheNomCommand
    @Test
    public void testRechercheNomExistant() {
        RechercheNomCommand cmd = new RechercheNomCommand("ecampus.uvsq.fr");
        Object resultat = cmd.execute(dns);
        assertEquals("193.51.25.12", resultat);
    }

    @Test
    public void testRechercheNomInexistant() {
        RechercheNomCommand cmd = new RechercheNomCommand("inexistant.com");
        Object resultat = cmd.execute(dns);
        assertNull(resultat);
    }

    // Tests RechercheDomaineCommand
    @Test
    public void testRechercheDomaineExistant() {
        RechercheDomaineCommand cmd = new RechercheDomaineCommand("uvsq.fr");
        Object resultat = cmd.execute(dns);
        assertNotNull(resultat);
        assertTrue(resultat instanceof String);
        String res = (String) resultat;
        assertTrue(res.contains("ecampus.uvsq.fr"));
        assertTrue(res.contains("pikachu.uvsq.fr"));
    }

    @Test
    public void testRechercheDomaineInexistant() {
        RechercheDomaineCommand cmd = new RechercheDomaineCommand("inexistant.com");
        Object resultat = cmd.execute(dns);
        assertNull(resultat);
    }

    // Tests AjoutCommand
    @Test
    public void testAjoutItemSucces() {
        AjoutCommand cmd = new AjoutCommand("10.0.0.1", "nouveau.example.com");
        Object resultat = cmd.execute(dns);
        assertEquals("Item ajouté avec succès", resultat);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAjoutIPInvalide() {
        AjoutCommand cmd = new AjoutCommand("256.256.256.256", "invalid.com");
        cmd.execute(dns);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAjoutNomInvalide() {
        AjoutCommand cmd = new AjoutCommand("10.0.0.1", "-invalid.com");
        cmd.execute(dns);
    }

    // Tests QuitterCommand
    @Test
    public void testQuitterCommand() {
        QuitterCommand cmd = new QuitterCommand();
        Object resultat = cmd.execute(null);
        assertEquals("Au revoir !", resultat);
    }


}