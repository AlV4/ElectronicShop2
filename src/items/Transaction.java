package items;

public class Transaction {
    private int id;
    private Tire tire;
    private Costumer costumer;
    private int amountOfItems;
    private double sellingPrise;
    private double purchasePrice;
    private double transactionSum;
    private String dateOfTransaction;
    public Object [] fields = new Object[8];

    public Transaction() {
    }

    @Override
    public String toString() {
        return "[ Tr. id: " +getId() + "; tire: " + tire.toString() +", " + getAmountOfItems()+ " items; costumer: "+
                costumer.getFirstName() + ", " + costumer.getSecondName() + "; price: " + tire.getSellingPrice() +
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

    public double getTransactionSum() {
        return transactionSum;
    }

    public void setTransactionSum(double transactionSum) {
        this.transactionSum = transactionSum;
    }

    public String getDateOfTransaction() {
        return dateOfTransaction;
    }

    public void setDateOfTransaction(String dateOfTransaction) {
        this.dateOfTransaction = dateOfTransaction;
    }

    public void transactionUpdate(){
        Object [] fields = {id, tire, costumer, amountOfItems, sellingPrise, purchasePrice,transactionSum, dateOfTransaction};
        this.fields = fields;
    }

}
