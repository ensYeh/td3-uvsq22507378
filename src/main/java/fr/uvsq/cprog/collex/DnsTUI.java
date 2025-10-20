package fr.uvsq.cprog.collex;

import java.util.Scanner;

public class DnsTUI {
    private Scanner scanner;

    public DnsTUI() {
        this.scanner = new Scanner(System.in);
    }

    public Command nextCommande() {
        System.out.print("> ");
        String input = scanner.nextLine().trim();
        String[] parts = input.split("\\s+");

        if (parts.length == 0 || parts[0].isEmpty()) {
            throw new IllegalArgumentException("Commande vide");
        }

      /*  switch(parts[0].toLowerCase()) {
            case "ip":
                if (parts.length < 2) throw new IllegalArgumentException("Usage: ip <adresse>");
                return new RechercheIPCommand(parts[1]);
            case "nom":
                if (parts.length < 2) throw new IllegalArgumentException("Usage: nom <machine>");
                return new RechercheNomCommand(parts[1]);
            case "domaine":
                if (parts.length < 2) throw new IllegalArgumentException("Usage: domaine <domaine>");
                return new RechercheDomaineCommand(parts[1]);
            case "add":
                if (parts.length < 3) throw new IllegalArgumentException("Usage: add <ip> <nom>");
                return new AjoutCommand(parts[1], parts[2]);
            case "quit":
            case "exit":
                return new QuitterCommand();
            default:
                throw new IllegalArgumentException("Commande inconnue: " + parts[0]);
        }*/
        if(parts[0].equals("quit")){
            return new QuitterCommand();

        }else
        if(parts[0].equals("add")){
            return new AjoutCommand(parts[1],parts[2]);
        }else

        if (parts[0].equals("ls")) {
            if (parts.length < 2) {
                throw new IllegalArgumentException("Usage: ls [-a] domaine");
            }

            if (parts.length == 3 && parts[1].equals("-a")) {
                return null; // tri par IP
            } else {
                return new RechercheDomaineCommand(parts[1]);
            }
        }else

        if(AdresseIP.checkIp(parts[0])){
         //   if (parts.length < 2) throw new IllegalArgumentException("Usage: ip <adresse>");

            return new RechercheIPCommand(parts[0]);
        }else

        if(NomMachine.checkNom(parts[0])){
          //  if (parts.length < 2) throw new IllegalArgumentException("Usage: nom <machine>");
            return new RechercheNomCommand(parts[0]);
        }
        else return null;
    }

    public void affiche(Object resultat) {
        if (resultat == null) {
            System.out.println("Aucun résultat trouvé");
        } else {
            System.out.println(resultat);
        }
    }
}