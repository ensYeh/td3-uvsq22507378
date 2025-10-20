package fr.uvsq.cprog.collex;

import java.util.List;

public class RechercheDomaineCommand implements Command {
    private String domaine;

    public RechercheDomaineCommand(String domaine) {
        this.domaine = domaine;
    }

    @Override
    public Object execute(Dns dns) {
        List<DnsItem> items = dns.getItems(domaine);
        if (items.isEmpty()) return null;

        StringBuilder sb = new StringBuilder();
        for (DnsItem item : items) {
            sb.append(item.getIp().getipAdress())
                    .append(" ")
                    .append(item.getNom().getNomMachine())
                    .append("\n");
        }
        return sb.toString();
    }
}