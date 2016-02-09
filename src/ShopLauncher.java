import data.Storage;
import items.Costumer;
import items.Producers;
import items.Seasons;
import items.Tire;

import java.awt.*;

public class ShopLauncher {
    public static void main(String[] args){
        Storage storage = new Storage();

        TireShop shop = new TireShop();
        shop.shopInit(storage);

        Tire t = new Tire(215,60,16,Seasons.SUMMER,Producers.Hankook,2000,2300);
        storage.putTireIntoStorage(t, 4);
        Tire t2 = new Tire(195,65,15,Seasons.WINTER,Producers.Toyo,1200,1443);
        storage.putTireIntoStorage(t2, 4);
        Tire t3 = new Tire(185,65,14,Seasons.ALLSEASONS,Producers.Matador,1000,1253);
        storage.putTireIntoStorage(t3, 4);

        shop.buy(new Costumer("John", "Smith"), t3, 1);

        shop.print(storage.getTires());

        SplashScreen screen = SplashScreen.getSplashScreen();
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){

        }
        TireShopUI shopUI = new  TireShopUI(shop);
//        screen.close();

    }
}
