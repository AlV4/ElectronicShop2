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
    private Tire tire;
    private Transaction transaction;
    private ArrayList<Transaction> transactions;
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy, EEEE HH:mm:ss");

    public TireShop() {
    }

    public void shopInit(Storage storage) {
        transactions = new ArrayList<>();
        this.storage = storage;
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
        transactions.add(transaction);
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

    public ArrayList getTransactions() {
        return transactions;
    }

    public void setTransactions(ArrayList transactions) {
        this.transactions = transactions;
    }
}
