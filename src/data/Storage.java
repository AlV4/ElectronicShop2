package data;

import items.*;

import java.util.ArrayList;
import java.util.Random;

public class Storage {

    private ArrayList<Tire> tires;
    private ArrayList<Tire> temporarySortedTires;
    private ArrayList<Tire> sortedBySeason;
    private ArrayList<Tire> sortedByProducer;

    public int[] widths = {135, 145, 155, 165, 175, 185, 195, 205, 215, 225, 235, 245, 255, 265, 275, 285, 295};
    public int[] ratio = {40, 50, 60, 70, 80, 90};
    public int[] radiuses = {13, 14, 15, 16, 17, 18};

    public Storage() {
    }

    public void storageInit() {
        tires = new ArrayList<>();
        sortedBySeason = new ArrayList<>();
        sortedByProducer = new ArrayList<>();
    }

  public void getAmounts() {
        System.out.println(tires.size());

    }

    public void putTireIntoStorage(Tire tire, int amount) {
        if(tire != null && amount > 0) {
            while (amount > 0) {
                tires.add(tire);
                amount--;
            }
            System.out.println("Operation successfull!");
        }
        System.out.println("Something wrong, check your input data.");
    }

    public void removeTiresFromTheStorage(Transaction t) {
        int amount = t.getAmountOfItems();
        while (amount > 0) {
            tires.remove(t.getTire());
            amount--;
        }
    }

    public boolean isSuchTiresEnough(Tire tire, int amount){
        int suchTiresInStorage = 0;
        for(Tire t : tires){
            if(t.equals(tire)){
                suchTiresInStorage++;
                if(suchTiresInStorage == amount){
                    return true;
                }
            }
        }
        return false;
    }


    public void randomStorageInit(int size) {
        tires = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            tires.add(tiresGenerator());
        }
    }

    public Tire tiresGenerator() {
        Tire t = new Tire();
        t.setWidth(widths[new Random().nextInt(widths.length)]);
        t.setAspectRatio(ratio[new Random().nextInt(ratio.length)]);
        t.setRadius(radiuses[new Random().nextInt(radiuses.length)]);
        t.setProduser(Producers.values()[new Random().nextInt(Producers.values().length)]);
        t.setSeason(Seasons.values()[new Random().nextInt(Seasons.values().length)]);
        t.setPurchasePrice(t.getWidth() / t.getAspectRatio() * t.getRadius());
        t.setSellingPrice(t.getPurchasePrice() + t.getPurchasePrice() * 0.3);

        return t;
    }

    public ArrayList getTires() {
        return tires;
    }

   public ArrayList getSortedBySeason() {
        return sortedBySeason;
    }

    public ArrayList getSortedByProducer() {
        return sortedByProducer;
    }
}
