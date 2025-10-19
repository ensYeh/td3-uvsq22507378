package fr.uvsq.cprog.collex;

public class NomMachine {
private final String nomMachine;

    public NomMachine(String nomMachine) {
        this.nomMachine = nomMachine;
    }

    public String getNomMachine() {
        return nomMachine;
    }

    public static boolean checkNom(String nom) {
        if (nom == null || nom.isEmpty()) {
            return false;
        }

        // Un FQDN peut avoir entre 1 et 253 caractères
        if (nom.length() > 253) {
            return false;
        }

        // Regex pour valider un FQDN
        String fqdnPattern = "^(?!-)[A-Za-z0-9-]{1,63}(?<!-)(\\.[A-Za-z0-9-]{1,63}(?<!-))*\\.?$";
        return nom.matches(fqdnPattern);
    }

    public Boolean same(String s) {
    return nomMachine.equals(s);
    }

}
