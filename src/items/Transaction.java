package items;

import java.util.Date;

public class Transaction {
    private int id;
    private Tire tire;
    private Costumer costumer;
    private int amountOfItems;
    private double sellingPrise;
    private double purchasePrice;
    private Date dateOfTransaction;

    public Transaction() {
    }

    @Override
    public String toString() {
        return "[ Tr. id: " +getId() + "; tire: " + tire.toString() +", " + getAmountOfItems()+ " items; costumer: "+
                costumer.getFirstName() + ", " + costumer.getSecondName() + "; price: " + tire.getPurchasePrice() +
                "; date: " + getDateOfTransaction() + " ]";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Tire getTire() {
        return tire;
    }

    public void setTire(Tire tire) {
        this.tire = tire;
    }

    public Costumer getCostumer() {
        return costumer;
    }

    public void setCostumer(Costumer costumer) {
        this.costumer = costumer;
    }

    public int getAmountOfItems() {
        return amountOfItems;
    }

    public void setAmountOfItems(int amountOfItems) {
        this.amountOfItems = amountOfItems;
    }

    public double getSellingPrise() {
        return sellingPrise;
    }

    public void setSellingPrise(double sellingPrise) {
        this.sellingPrise = sellingPrise;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Date getDateOfTransaction() {
        return dateOfTransaction;
    }

    public void setDateOfTransaction(Date dateOfTransaction) {
        this.dateOfTransaction = dateOfTransaction;
    }


}
