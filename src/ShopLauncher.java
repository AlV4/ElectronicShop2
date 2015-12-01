import data.Storage;
import items.Costumer;
import items.Producers;
import items.Seasons;
import items.Tire;

import java.util.Arrays;

public class ShopLauncher {
    public static void main(String[] args) {
        Storage storage = new Storage();
        storage.storageInit(100);

        Tire t = new Tire(215,60,16,Seasons.SUMMER,Producers.Hankook,2000,2300);
        storage.putTireIntoStorage(t, 4);
        Tire t2 = new Tire(195,65,15,Seasons.WINTER,Producers.Toyo,1200,1443);
        storage.putTireIntoStorage(t2, 2);
        Tire t3 = new Tire(195,65,15,Seasons.WINTER,Producers.Toyo,1200,1443);
        storage.putTireIntoStorage(new Tire(195, 65, 15, Seasons.WINTER, Producers.Toyo, 1200, 1443), 1);
        storage.print(storage.getTires());

        Costumer costumer = new Costumer();
        costumer.setFirstName("Alex");
        costumer.setSecondName("Black");
        TireShop shop = new TireShop();
        shop.shopInit(storage);
        shop.buy(costumer, t3, 3);
        System.out.println();
        System.out.println(Arrays.asList(shop.getTransactions()));


//        storage.randomStorageInit(10);
//
        storage.storageScanner();
        storage.print(storage.getTires());
//        System.out.println();
//        storage.print(storage.getSortedBySeason()[Seasons.SUMMER.getId()]);
//        storage.print(storage.getSortedBySeason()[Seasons.WINTER.getId()]);
//        storage.print(storage.getSortedBySeason()[Seasons.ALLSEASONS.getId()]);
//        System.out.println();
//        storage.getPrices();
//        System.out.println();
        storage.getAmounts();

    }
}
