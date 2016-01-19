package items;

public class Tire {

    private int width;
    private int aspectRatio;
    private int radius;
    private Seasons season;
    private Producers produser;

    private double purchasePrice;
    private double sellingPrice;

    public Tire() {
    }

    public Tire(int width, int aspectRatio, int radius, Seasons season, Producers produser, double purchasePrice, double sellingPrice){
        setWidth(width);
        setAspectRatio(aspectRatio);
        setRadius(radius);
        setSeason(season);
        setProduser(produser);
        setPurchasePrice(purchasePrice);
        setSellingPrice(sellingPrice);
    }

    public String toString(){
        return getWidth() + "/" + getAspectRatio() + " R " + getRadius()  + " " + getProduser() + " " + getSeason() + " price: " + getSellingPrice();
    }

    public boolean compareBySize(Tire tire){
        if(tire != null&&
                this.width == tire.getWidth()&&
                this.aspectRatio == tire.getAspectRatio()&&
                this.radius == tire.getRadius()){
            return true;
        }
        return false;
    }

    public boolean compareByProducer(Tire tire){
        if(tire != null&&
                this.produser.equals(tire.getProduser())){
            return true;
        }
        return false;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getAspectRatio() {
        return aspectRatio;
    }

    public void setAspectRatio(int aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public Seasons getSeason() {
        return season;
    }

    public void setSeason(Seasons season) {
        this.season = season;
    }

    public Producers getProduser() {
        return produser;
    }

    public void setProduser(Producers produser) {
        this.produser = produser;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tire tire = (Tire) o;

        if (width != tire.width) return false;
        if (aspectRatio != tire.aspectRatio) return false;
        if (radius != tire.radius) return false;
        if (season != tire.season) return false;
        return produser == tire.produser;

    }

    @Override
    public int hashCode() {
        int result = width;
        result = 31 * result + aspectRatio;
        result = 31 * result + radius;
        result = 31 * result + season.hashCode();
        result = 31 * result + produser.hashCode();
        return result;
    }
}
