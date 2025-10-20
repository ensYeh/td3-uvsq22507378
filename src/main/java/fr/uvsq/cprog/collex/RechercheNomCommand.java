package fr.uvsq.cprog.collex;

public class RechercheNomCommand implements Command {
    private String nom;

    public RechercheNomCommand(String nom) {
        this.nom = nom;
    }

    @Override
    public Object execute(Dns dns) {
        DnsItem item = dns.getItem(nom, DnsItem::compareNom);
        return item != null ? item.getIp().getipAdress() : null;
    }
}
