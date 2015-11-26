import data.Storage;
import items.Producers;
import items.Seasons;
import items.Tire;

public class ShopLauncher {
    public static void main(String[] args) {
        Storage storage = new Storage();
        storage.storageInit(4);

        Tire t = new Tire();
        t.setWidth(215);
        t.setAspectRatio(60);
        t.setRadius(16);
        t.setProduser(Producers.Hankook);
        t.setSeason(Seasons.SUMMER);
        t.setPurchasePrice(2000);
        t.setSellingPrice(2300);

        storage.putTireIntoStorage(t, 4);

        for (Tire tire: storage.getTires()){
            if(tire != null){
                System.out.println(tire.toString());
            }
        }
    }
}
