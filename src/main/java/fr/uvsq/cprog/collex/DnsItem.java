package fr.uvsq.cprog.collex;

public class DnsItem {
   private AdresseIP ip;
   private NomMachine nom;

  public DnsItem(String ip,String nom){
       if(checkIp(ip)&& checkName(nom)){
           this.ip=new AdresseIP(ip);
           this.nom=new NomMachine(nom);
       }
   }
 // a tester
   private boolean checkIp(String ip){
      return true;
    }
    // a tester
    private boolean checkName(String nom){
      return true;
    }


}
