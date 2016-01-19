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
        nameTextField.setText("   ");
        GridBagConstraints cellSettings = new GridBagConstraints();

        cellSettings.gridx = 0;
        cellSettings.gridy = 0;
        cellSettings.gridheight = 1;
        cellSettings.gridwidth = 2;
        cellSettings.weightx = 1;
        cellSettings.weighty = 1;
        cellSettings.anchor = GridBagConstraints.NORTHEAST;
        cellSettings.fill = GridBagConstraints.NONE;
        cellSettings.insets = new Insets(50,0,0,0);
        cellSettings.ipadx = 0;
        cellSettings.ipady = 0;

        panel.add(textName, cellSettings);

        cellSettings.gridx = 2;
        cellSettings.anchor = GridBagConstraints.NORTHWEST;

        panel.add(nameTextField, cellSettings);

        cellSettings.gridx = 0;
        cellSettings.gridy = 1;
        cellSettings.gridwidth = 1;
        cellSettings.anchor = GridBagConstraints.CENTER;
        cellSettings.insets = new Insets(0,0,0,0);

        String names[] = {"Season", "Producer", "Size", "Price"};

        for (int i = 0; i < names.length; i++){
            cellSettings.gridx = i;
            JLabel name = new JLabel(names[i]);
            name.setFont(new Font(Font.SERIF, Font.BOLD, 18));
            panel.add((name), cellSettings);
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

        cellSettings.gridx = 0;
        cellSettings.gridy = 2;
        cellSettings.fill = GridBagConstraints.VERTICAL;

        panel.add(radioButtonsSeasons, cellSettings);

        final JComboBox<Producers> producersMenu = new JComboBox<>();
        for(int i = 0; i < storage.producersList.size(); i++){
            producersMenu.addItem(storage.producersList.get(i));
        }
        cellSettings.gridx = 1;
        cellSettings.fill = GridBagConstraints.HORIZONTAL;

        panel.add(producersMenu, cellSettings);

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
        cellSettings.gridx = 2;
        panel.add(sizes, cellSettings);


        final JTextField priceField = new JTextField("0 $");
        priceField.setEditable(false);
        priceField.setColumns(5);
        priceField.setFont(new Font("Verdana", Font.BOLD, 16));

        cellSettings.gridx = 3;
        cellSettings.gridy = 2;
        cellSettings.anchor = GridBagConstraints.CENTER;
        cellSettings.fill = GridBagConstraints.NONE;

        panel.add(priceField, cellSettings);

        final JLabel amount = new JLabel("Amount: ");

        cellSettings.gridx = 1;
        cellSettings.gridy = 3;
        cellSettings.ipadx = 0;
        cellSettings.ipady = 0;
        cellSettings.anchor = GridBagConstraints.EAST;

        panel.add(amount, cellSettings);

        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        final JFormattedTextField counter = new JFormattedTextField(numberFormat);
        counter.setColumns(3);
        counter.setValue(1);
        cellSettings.gridx = 2;
        cellSettings.anchor = GridBagConstraints.WEST;

        panel.add(counter, cellSettings);

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

        cellSettings.gridx = 3;
        cellSettings.gridy = 3;
        cellSettings.ipadx = 150;
        cellSettings.ipady = 30;
        cellSettings.anchor = GridBagConstraints.CENTER;

        panel.add(button, cellSettings);



        return panel;
    }
}
