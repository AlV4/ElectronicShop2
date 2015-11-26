package data;

import items.*;

import java.util.Random;

public class Storage {
    private Tire tire;
    private Tire []tires;
    private Tire [][]sortedBySeason;

    private Costumer costumer;
    private Transaction []transactions;

    private int []widths = {135,145,155,165,175,185,195,205,215,225,235,245,255,265,275,285,295};
    private int []ratio = {40,50,60,70,80,90};
    private int []radiuses = {13,14,15,16,17,18};

    public Storage() {
    }

    public void storageInit(int size){
        tires = new Tire[size];
    }

    public void putTireIntoStorage(Tire tire, int amount){
        if(tires == null||tires.length == 0){
            System.out.println("First initialise the storage!");
            return;
        }
        if(firstFreePlace() >= 0 &&
                (tires.length - firstFreePlace() >= amount) ){
            int i = firstFreePlace();
            while (amount > 0){
                tires[i++] = tire;
                amount--;
            }
            return;
        }
        System.out.println("Sheck conditions. Something wrong.");
    }

    public int firstFreePlace(){
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


        return t;
    }

    public Tire getTire() {
        return tire;
    }

    public void setTire(Tire tire) {
        this.tire = tire;
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
}
