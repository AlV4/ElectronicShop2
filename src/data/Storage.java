package data;

import items.*;

import java.util.Arrays;
import java.util.Random;

public class Storage {

    private Tire []tires;
    private Tire [][]sortedBySeason;
    private Tire [][]sortedByProducer;

    private Costumer costumer;
    private Transaction []transactions;

    private int []widths = {135,145,155,165,175,185,195,205,215,225,235,245,255,265,275,285,295};
    private int []ratio = {40,50,60,70,80,90};
    private int []radiuses = {13,14,15,16,17,18};

    public Storage() {
    }

    public void storageInit(int size){
        tires = new Tire[size];
        sortedBySeason = new Tire[Seasons.values().length][size];
        sortedByProducer = new Tire[Producers.values().length][size];
    }

    private void putTireInArray(Tire[] arrayOfTires, Tire tire) {
        int indexOfFirstEmptyCell = 0;
        for (int i = 0; i < arrayOfTires.length; i++) {
            if (arrayOfTires[i] == null) {
                indexOfFirstEmptyCell = i;
                break;
            }
        }
        arrayOfTires[indexOfFirstEmptyCell] = tire;
    }

    public void storageScanner() {
        for (Tire tire : tires) {
            if(tire != null) {
                putTireInArray(sortedBySeason[tire.getSeason().getId()], tire);
                putTireInArray(sortedByProducer[tire.getProduser().getId()], tire);
            }
        }
    }

    public Tire[] storeChecker(Tire tyre){
        Tire []cashOfTires = new Tire[tires.length];
        int count = 0;
        int idx = 0;
        for (Tire t : tires){
            if(t != null && tyre.compareByAllParameters(t)){
                cashOfTires[idx] = t;
                idx++;
                continue;
            }
            count++;
        }
        if (count == tires.length) {
            return new Tire[0];
        }
        return Arrays.copyOf(cashOfTires, idx);
    }

    public void getPrices(){
        for (Tire[] first:sortedByProducer) {
            if(first != null) {
                    if (first[0] != null) {
                        System.out.println(first[0].getProduser() + ", price: " + first[0].getSellingPrice());
                    }

            }
        }
    }

    public void getAmounts(){
        int amount;
        if(firstFreePlace(tires) == -1) {
            amount = tires.length;
        }else {
            amount = firstFreePlace(tires);
        }

            System.out.println("Total amount in the storage: " + amount + " tires.\n" +
                    "Categories:");
        storageScanner();
        for (Tire[] first:sortedByProducer) {
            if(first != null) {
                if (first[0] != null) {
                    System.out.println((first[0].getProduser() + ", amount: " + firstFreePlace(first)));
                }

            }
        }


    }

    public void print(Tire [] tires){
        for (Tire tire: tires){
            if(tire != null){
                System.out.println(tire.toString());
            }
        }

    }

    public void putTireIntoStorage(Tire tire, int amount){
        if(tires == null||tires.length == 0){
            System.out.println("First initialise the storage!");
            return;
        }
        if(firstFreePlace(tires) >= 0 &&
                (tires.length - firstFreePlace(tires) >= amount) ){
            int i = firstFreePlace(tires);
            while (amount > 0){
                tires[i++] = tire;
                amount--;
            }
            return;
        }
        System.out.println("Sheck conditions. Something wrong.");
    }

    public void removeTiresFromTheStorage(Transaction t){
        int amount = t.getAmountOfItems();
        for (int i = 0; i < tires.length && amount > 0; i++) {
            if(tires[i] != null && tires[i].compareByAllParameters(t.getTire())) {
               tires[i] = null;
                amount--;
            }
        }
    }

    public int firstFreePlace(Tire []tires){
        for (int i = 0; i < tires.length; i++){
            if(tires[i] == null){
                return i;
            }
        }
        System.out.println("There is no free space! Please extend the size of storage!");
        return -1;
    }

    public void randomStorageInit(int size){
        tires = new Tire[size];
        for (int i = 0; i < size; i++) {
            tires[i] = tiresGenerator();
        }
    }

    public Tire tiresGenerator(){
        Tire t = new Tire();
        t.setWidth(widths[new Random().nextInt(widths.length)]);
        t.setAspectRatio(ratio[new Random().nextInt(ratio.length)]);
        t.setRadius(radiuses[new Random().nextInt(radiuses.length)]);
        t.setProduser(Producers.values()[new Random().nextInt(Producers.values().length)]);
        t.setSeason(Seasons.values()[new Random().nextInt(Seasons.values().length)]);
        t.setPurchasePrice(t.getWidth()/t.getAspectRatio()*t.getRadius());
        t.setSellingPrice(t.getPurchasePrice()+t.getPurchasePrice()*0.3);

        return t;
    }

    public Tire[] getTires() {
        return tires;
    }

    public void setTires(Tire[] tires) {
        this.tires = tires;
    }

    public Tire[][] getSortedBySeason() {
        return sortedBySeason;
    }

    public void setSortedBySeason(Tire[][] sortedBySeason) {
        this.sortedBySeason = sortedBySeason;
    }

    public Tire[][] getSortedByProducer() {
        return sortedByProducer;
    }

    public void setSortedByProducer(Tire[][] sortedByProducer) {
        this.sortedByProducer = sortedByProducer;
    }
}
