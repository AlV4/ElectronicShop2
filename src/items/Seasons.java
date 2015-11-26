package items;

public enum Seasons {
    SUMMER(0), WINTER(1), ALLSEASONS(2);

    private int id;

    private  Seasons(int id){
        this.id = id;
    }
    public int getId(){
        return id;
    }
}
