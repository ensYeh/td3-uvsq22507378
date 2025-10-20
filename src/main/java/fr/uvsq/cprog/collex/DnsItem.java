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
      String ip=d.getIp().getipAdress();
      return ip.equals(s);

    }
    public static Boolean compareNom(DnsItem d,String s){
      //return  d.getNom().getNomMachine().equals(s);
    String nommachine=d.getNom().getNomMachine();
    return s.equals(nommachine);
  }

    public static boolean compareDomain(DnsItem d, String s) {
        String domainName = d.getNom().getNomMachine();
        if (domainName == null || s == null) {
            return false;
        }

        String[] parts = domainName.split("\\.");
        String[] partss = s.split("\\.");

        if (parts.length >= partss.length) {
            boolean res = true;
            // Comparer depuis la FIN des deux tableaux
            for(int i = 0; i < partss.length; i++){
                int partsIndex = parts.length - partss.length + i;
                res = res && parts[partsIndex].equals(partss[i]);
            }
            return res;
        }
        return false;
    }
}
