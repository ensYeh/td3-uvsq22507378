package fr.uvsq.cprog.collex;

public class DnsItem {
   private AdresseIP ip;
   private NomMachine nom;

  public DnsItem(String ip,String nom) throws IllegalArgumentException {
       if(AdresseIP.checkIp(ip)&& NomMachine.checkNom(nom)){
           this.ip=new AdresseIP(ip);
           this.nom=new NomMachine(nom);
       }else throw new IllegalArgumentException("nom de la machine ou l'adresse ip est mal formé");
   }
 // a tester
    boolean checkIp(String ip){
      return true;
    }
    // a tester
    private boolean checkName(String nom){
      return true;
    }


    public AdresseIP getIp(){
      return ip;
    }

    public NomMachine getNom() {
        return nom;
    }

    public static Boolean compareIp(DnsItem d,String s){
      return  d.getIp().getipAdress().equals(s);

    }
    public static Boolean compareNom(DnsItem d,String s){
      return  d.getNom().getNomMachine().equals(s);
    }

    public static boolean compareDomain(DnsItem d,String s) {
        String domainName = d.getNom().getNomMachine(); // récupère le nom complet de la machine
        if (domainName == null || s == null) {
            return false; // sécurité null
        }

        String[] parts = domainName.split("\\."); // bien échapper le point

        if (parts.length >= 2) {
            // on compare le domaine (par ex. "example" dans "www.example.com")
            return s.equals(parts[parts.length - 2]);
        } else if (parts.length == 1) {
            return s.equals(parts[0]);
        } else {
            return false;
        }
  }
}
