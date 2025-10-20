package fr.uvsq.cprog.collex;

public class RechercheIPCommand implements Command {
    private String ip;

    public RechercheIPCommand(String ip) {
        this.ip = ip;
    }

    @Override
    public Object execute(Dns dns) {
        DnsItem item = dns.getItem(ip, DnsItem::compareIp);
        return item != null ? item.getNom().getNomMachine() : null;
    }
}