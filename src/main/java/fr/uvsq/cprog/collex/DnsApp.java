package fr.uvsq.cprog.collex;

public class DnsApp {
    private Dns dns;
    private DnsTUI tui;

    public void run() {
        dns = new Dns();
        tui = new DnsTUI();

        System.out.println("=== DNS Application ===");
        System.out.println("<IP>,<nom fqdn>, ls <nom de domain>, quit");


        boolean continuer = true;
        while(continuer) {
            try {
                Command cmd = tui.nextCommande();

                if (cmd instanceof QuitterCommand) {
                    continuer = false;
                }

                Object resultat = cmd.execute(dns);
                tui.affiche(resultat);

            } catch (Exception e) {
                tui.affiche("Erreur: " + e.getMessage());
            }
        }
    }
}