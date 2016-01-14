import javax.swing.*;
import java.awt.*;

public class TireShopUI {
    private TireShop shop;

    public TireShopUI(TireShop shop){
        this.shop = shop;

        JFrame frame = new JFrame("Tire shop");
        frame.setMinimumSize(new Dimension(800, 600));
        frame.setLocation(400, 100);
        frame.getContentPane().add(createPanel());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private JPanel createPanel(){
        JPanel panel = new JPanel();
        JLabel textName = new JLabel("Input Your name, please.");
        JTextField nameField = new JTextField(20);
        panel.add(textName);
        panel.add(nameField);

        return panel;
    }
}
