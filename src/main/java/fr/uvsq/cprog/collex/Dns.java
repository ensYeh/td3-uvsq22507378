package fr.uvsq.cprog.collex;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class Dns {
    List<DnsItem> itemes = new ArrayList<DnsItem>();

    public Dns() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(Config.FILE_PATH));

            String line = reader.readLine();

            while (line != null) {
                String[] sitesm = line.split(" ");

                DnsItem di = new DnsItem(sitesm[0], sitesm[1]);

                itemes.add(di);
                line = reader.readLine();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException k) {
            k.printStackTrace();
        }
    }


    public DnsItem getItem(String param, BiFunction<DnsItem, String, Boolean> comparaison) {
        for (DnsItem e : itemes) {
            if (comparaison.apply(e, param)) {
                return e;
            }
        }
        return null;
    }


    public List<DnsItem> getItems(String param) {


        List<DnsItem> dilist = new ArrayList<>();
        for (DnsItem e : itemes) {
            if (DnsItem.compareDomain(e, param)) {
                dilist.add(e);
            }
        }

        return dilist;

    }

    public int addItem(String nom,String ip) throws IllegalArgumentException {

            DnsItem e =new DnsItem(ip,nom);
            if(!itemes.contains(e)) {
                itemes.add(e);
                return 0;
            }
            else{
                return -1;
            }
    }

    @Override
    public String toString(){
        StringBuilder s=new StringBuilder();
        for(DnsItem e:itemes){
            s.append(e.getIp().getipAdress());
            s.append(e.getNom().getNomMachine());
            s.append("\n");
        }
        return s.toString();
    }

}