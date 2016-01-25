import data.Storage;
import items.Costumer;
import items.Producers;
import items.Seasons;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;

public class TireShopUI {
    private TireShop shop;
    private Storage storage;

    public TireShopUI(TireShop shop){
        this.shop = shop;
        storage = shop.getStorage();

        JFrame frame = new JFrame("Tire shop");
        frame.setMinimumSize(new Dimension(800, 600));
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().add(createPanel());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

    private JPanel createPanel(){
        JPanel panel = new JPanel(new GridBagLayout());
        JLabel textName = new JLabel("Input Your name, please: ");
        final JTextField nameTextField = new JTextField(20);
        nameTextField.setText(" ");
        GridBagConstraints cellSettings = new GridBagConstraints();

        panel.add(textName, new GridBagConstraints(0,0,2,1,1,1, GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(50,0,0,0), 0,0));

        panel.add(nameTextField, new GridBagConstraints(2,0,2,1,1,1, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(50,0,0,0), 0,0));

        String names[] = {"Season", "Producer", "Size", "Price"};

        for (int i = 0; i < names.length; i++){
           JLabel name = new JLabel(names[i]);
            name.setFont(new Font(Font.SERIF, Font.BOLD, 18));
            panel.add((name), new GridBagConstraints(i,1,1,1,1,1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0), 0,0));
        }
         ButtonGroup radioButtonsGroup = new ButtonGroup();
         JPanel radioButtonsSeasons = new JPanel(new GridLayout(storage.seasonsList.size(),0));

         for (final Seasons s : storage.seasonsList){
             JRadioButton radioButton = new JRadioButton(s.toString());
             radioButton.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     shop.tire.setSeason(s);
                 }
             });
             radioButtonsGroup.add(radioButton);
             radioButtonsSeasons.add(radioButton);
         }

       panel.add(radioButtonsSeasons, new GridBagConstraints(0,2,1,1,1,1, GridBagConstraints.CENTER, GridBagConstraints.VERTICAL, new Insets(0,0,0,0), 0,0));

        final JComboBox<Producers> producersMenu = new JComboBox<>();
        for(int i = 0; i < storage.producersList.size(); i++){
            producersMenu.addItem(storage.producersList.get(i));
        }

        panel.add(producersMenu, new GridBagConstraints(1,2,1,1,1,1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0,0));

        JPanel sizes = new JPanel(new GridLayout(0,3));
        final JComboBox<Integer> widthsMenu = new JComboBox<>();
        for(int i = 0; i < storage.widthsList.size(); i++) {
            widthsMenu.addItem(new Integer(storage.widthsList.get(i)));
        }

        final JComboBox<Integer> ratioMenu = new JComboBox<>();
        for(int i = 0; i < storage.ratioList.size(); i++){
            ratioMenu.addItem(new Integer(storage.ratioList.get(i)));
        }

        final JComboBox<Integer> radiusesMenu = new JComboBox<>();
        for(int i = 0; i < storage.radiusesList.size(); i++){
            radiusesMenu.addItem(new Integer(storage.radiusesList.get(i)));
        }
        sizes.add(widthsMenu);
        sizes.add(ratioMenu);
        sizes.add(radiusesMenu);
        panel.add(sizes, new GridBagConstraints(2,2,1,1,1,1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0,0));


        final JTextField priceField = new JTextField("0 $");
        priceField.setEditable(false);
        priceField.setColumns(5);
        priceField.setFont(new Font("Verdana", Font.BOLD, 16));

        panel.add(priceField, new GridBagConstraints(3,2,1,1,1,1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0), 0,0));

        final JLabel amount = new JLabel("Amount: ");

        panel.add(amount, new GridBagConstraints(1,3,1,1,1,1, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0,0,0,0), 0,0));

        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        final JFormattedTextField counter = new JFormattedTextField(numberFormat);
        counter.setColumns(3);
        counter.setValue(1);

        panel.add(counter, new GridBagConstraints(2,3,1,1,1,1, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0,0,0,0), 0,0));

        JButton button = new JButton("Buy");
        button.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                shop.tire.setProduser((Producers) producersMenu.getSelectedItem());
                shop.tire.setWidth((int) widthsMenu.getSelectedItem());
                shop.tire.setAspectRatio((int) ratioMenu.getSelectedItem());
                shop.tire.setRadius((int) radiusesMenu.getSelectedItem());
                String selectedTire = shop.tire.toString();
                selectedTire = selectedTire.substring(0, selectedTire.lastIndexOf("price"));

                System.out.println("You selected: " + selectedTire);

                String price = " $";
                price = shop.storageTireSearch(Integer.parseInt(counter.getText())) + price;

                priceField.setText(price);
            }
        });

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Costumer costumer = new Costumer();
                String nameSurname = nameTextField.getText();
                costumer.setFirstName(nameSurname.substring(0, nameSurname.indexOf(" ")));
                costumer.setSecondName(nameSurname.substring(nameSurname.indexOf(" ")));
               shop.buy(costumer, shop.getTire(), Integer.parseInt(counter.getText()));
                shop.print(shop.getTransactions());
            }
        });

        panel.add(button, new GridBagConstraints(3,3,1,1,1,1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0),150,30));

        return panel;
    }
}
