package fr.uvsq.cprog.collex;

import java.io.*;
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
                saveItemToFile(e);

                return 0;
            }
            else{
                return -1;
            }
    }
    private void saveItemToFile(DnsItem item) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Config.FILE_PATH, true))) {
            writer.write(item.getIp().getipAdress());
            writer.write(" ");
            writer.write(item.getNom().getNomMachine());
            writer.write("\n");
            writer.flush();
        } catch (IOException e) {
            System.err.println("Erreur lors de la sauvegarde du fichier : " + e.getMessage());
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