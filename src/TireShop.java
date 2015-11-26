import data.Storage;
import items.Costumer;
import items.Tire;
import items.Transaction;

import java.util.Date;

public class TireShop {
    private Storage storage;
    private Costumer costumer;
    private Tire tire;
    private Transaction transaction;

    private Transaction []transactions;

    public TireShop() {
    }

    public void shopInit(Storage storage){
        transactions = new Transaction[storage.getTires().length];
        this.storage = storage;
    }

//    Here needs a method to clarify ability of storage to sale necessary tires!!!

    public void buyTire(Costumer costumer, Tire tire, int amount){
        transaction = new Transaction();
        transaction.setCostumer(costumer);
        transaction.setTire(tire);
        transaction.setAmountOfItems(amount);
        transaction.setPurchasePrice(tire.getPurchasePrice());
        transaction.setSellingPrise(tire.getSellingPrice());
        transaction.setDateOfTransaction(new Date());
        transaction.setId(firstFreePlace(transactions));
        transactions[transaction.getId()] = transaction;
    }

    public int firstFreePlace(Transaction []t){
        for (int i = 0; i < t.length; i++){
            if(t[i] == null){
                return i;
            }
        }
        System.out.println("There is no free space in Transactions! Please extend the size of list!");
        return -1;
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

    public Transaction[] getTransactions() {
        return transactions;
    }

    public void setTransactions(Transaction[] transactions) {
        this.transactions = transactions;
    }
}
