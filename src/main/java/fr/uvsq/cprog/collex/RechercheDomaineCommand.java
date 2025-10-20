package fr.uvsq.cprog.collex;

import java.util.Comparator;
import java.util.List;

public class RechercheDomaineCommand implements Command {
    private String domaine;
    private boolean trieIp;
    public RechercheDomaineCommand(String domaine,boolean trieIp) {
        this.domaine = domaine;
        this.trieIp=trieIp;
    }

    @Override
    public Object execute(Dns dns) {
        List<DnsItem> items = dns.getItems(domaine);

        if (items.isEmpty()) return null;

        // Tri conditionnel
        if (trieIp) {
            // Tri numérique sur les octets de l’adresse IP
            items.sort(Comparator.comparingInt(i -> ipToInt(i.getIp().getipAdress())));
        } else {
            // Tri alphabétique par nom de machine
            items.sort(Comparator.comparing(i -> i.getNom().getNomMachine()));
        }



        StringBuilder sb = new StringBuilder();
        for (DnsItem item : items) {
            sb.append(item.getIp().getipAdress())
                    .append(" ")
                    .append(item.getNom().getNomMachine())
                    .append("\n");
        }
        return sb.toString();
    }

    private int ipToInt(String ip) {
        String[] parts = ip.split("\\.");
        int result = 0;
        for (String part : parts) {
            result = result * 256 + Integer.parseInt(part);
        }
        return result;
    }
}