import data.Storage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ShopTests {
    Storage storage;
    TireShop shop;
    @Before
    public void init(){
        storage = new Storage();

        shop = new TireShop();
        shop.shopInit(storage);
    }
    @Test(expected = IllegalArgumentException.class)
    public void countDiscountTest_Input_Data_Less_Than_Zero(){
        shop.countDiscount(-1);
    }
    @Test
    public void countDiscountTest_Input_Data_Less_Than_500(){
        Assert.assertTrue(shop.countDiscount(499) == 0);
    }
    @Test
    public void countDiscountTest_Input_Data_500(){
        Assert.assertTrue(shop.countDiscount(500) == 0.05);

    }
    @Test
    public void countDiscountTest_Input_Data_More_Than_500(){
        Assert.assertTrue(shop.countDiscount(600) == 0.05);
    }
    @Test
    public void countDiscountTest_Input_Data_1000(){
        Assert.assertTrue(shop.countDiscount(1000) == 0.1);
    }
    @Test
    public void countDiscountTest_Input_Data_More_Than_1000(){
        Assert.assertTrue(shop.countDiscount(1001) == 0.1);
    }
}
