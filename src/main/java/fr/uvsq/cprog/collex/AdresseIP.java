package fr.uvsq.cprog.collex;

public class AdresseIP {
private final String ipAdress;

public AdresseIP(String ip){
    ipAdress=ip;
}

    public static boolean checkIp(String ip) {
        if (ip == null || ip.isEmpty()) {
            return false;
        }

        // Vérifier le format avec une regex
        String ipPattern = "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
        return ip.matches(ipPattern);
    }
    public String getipAdress(){
    return ipAdress;
}

    public Boolean same(String s) {
    return ipAdress.equals(s);
    }
}
