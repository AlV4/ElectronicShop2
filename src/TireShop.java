import data.Storage;
import items.Costumer;
import items.Tire;
import items.Transaction;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TireShop {
    private Storage storage;
    private Costumer costumer;
    public Tire tire;
    private Transaction transaction;
    private ArrayList<Transaction> transactions;
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy, EE HH:mm:ss");

    public TireShop() {
    }

    public void shopInit(Storage storage) {
        transactions = new ArrayList<>();
        this.storage = storage;
        this.tire = new Tire();
    }

    public void buy(Costumer costumer, Tire tire, int amount) {
        if (costumer != null && tire != null && amount > 0 && amount <= storage.getTires().size()) {
            if (storage.isSuchTiresEnough(tire, amount)) {
                createTransaction(costumer, tire, amount);
                storage.removeTiresFromTheStorage(transaction);
                return;
            }
            System.out.println("Not enough items of " + tire.toString() + "!");
            return;
        }
        System.out.println("Incorrect input data! Please check your input information!");
    }

    public void createTransaction(Costumer costumer, Tire tire, int amount) {
        transaction = new Transaction();
        transaction.setCostumer(costumer);
        transaction.setTire(tire);
        transaction.setAmountOfItems(amount);
        transaction.setPurchasePrice(tire.getPurchasePrice());
        transaction.setSellingPrise(tire.getSellingPrice());
        transaction.setDateOfTransaction(dateFormatter.format(new Date()));
        transaction.setId(transactions.size());
        transaction.transactionUpdate();
        transactions.add(transaction);
    }

    public double storageTireSearch(int amount){
        if(tire == null || !storage.getTires().contains(tire)){
            System.out.println("No such tire or incorrect input!");
            return 0;
        }
        int i = amount;
        for(Tire t : storage.getTires()){
            if(tire.equals(t)){
                tire = t;
                i--;
                if (i == 0){
                    break;
                }
            }
        }
        if(i == 0){
            return tire.getSellingPrice() * amount;
        }
        System.out.println("Not enough such tires!");
        return 0;
    }


    public double countDiscount(double orderSum){
        if( orderSum < 0){
            throw new IllegalArgumentException("Input data can't be less than zero!");
        }
        if(orderSum >= 500 && orderSum < 1000){
            return 0.05;
        }else if(orderSum >= 1000 ){
            return 0.1;
        }
        return 0;
    }


    public void print(ArrayList<?> list) {
        for (Object o : list ) {
            if (o != null) {
                System.out.println(o.toString());
            }
        }

    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public Costumer getCostumer() {
        return costumer;
    }

    public void setCostumer(Costumer costumer) {
        this.costumer = costumer;
    }

    public Tire getTire() {
        return tire;
    }

    public void setTire(Tire tire) {
        this.tire = tire;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public ArrayList <Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(ArrayList transactions) {
        this.transactions = transactions;
    }


}
