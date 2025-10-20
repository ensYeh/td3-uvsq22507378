package fr.uvsq.cprog.collex;

public class AjoutCommand implements Command {
    private String ip;
    private String nom;

    public AjoutCommand(String ip, String nom) {
        this.ip = ip;
        this.nom = nom;
    }

    @Override
    public Object execute(Dns dns) {
        int result = dns.addItem(nom, ip);
        return result == 0 ? "Item ajouté avec succès" : "Item déjà existant";
    }
}