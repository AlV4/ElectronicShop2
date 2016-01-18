import data.Storage;
import items.Producers;

import javax.swing.*;
import java.awt.*;
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
        JTextField nameTextField = new JTextField(20);
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
        JPanel radioButtonsSeasons = new JPanel(new GridLayout(names.length,0));

        String []seasons = {"Summer", "Winter", "All Seasons"};
         for (String name : seasons){
             JRadioButton radioButton = new JRadioButton(name);
             radioButtonsGroup.add(radioButton);
             radioButtonsSeasons.add(radioButton);
         }

        cellSettings.gridx = 0;
        cellSettings.gridy = 2;
        cellSettings.fill = GridBagConstraints.VERTICAL;

        panel.add(radioButtonsSeasons, cellSettings);

        JComboBox<Producers> menuProducers = new JComboBox<>(Producers.values());
        cellSettings.gridx = 1;
        cellSettings.fill = GridBagConstraints.HORIZONTAL;

        panel.add(menuProducers, cellSettings);

        JPanel sizes = new JPanel(new GridLayout(0,3));
        JComboBox<Integer> widthsMenu = new JComboBox<>();
        for(int i = 0; i < storage.widths.length; i++){
            widthsMenu.addItem(new Integer(storage.widths[i]));
        }

        JComboBox<Integer> ratioMenu = new JComboBox<>();
        for(int i = 0; i < storage.ratio.length; i++){
            ratioMenu.addItem(new Integer(storage.ratio[i]));
        }

        JComboBox<Integer> radiusesMenu = new JComboBox<>();
        for(int i = 0; i < storage.radiuses.length; i++){
            radiusesMenu.addItem(new Integer(storage.radiuses[i]));
        }
        sizes.add(widthsMenu);
        sizes.add(ratioMenu);
        sizes.add(radiusesMenu);
        cellSettings.gridx = 2;
        panel.add(sizes, cellSettings);


        JTextField priceField = new JTextField("1000   $");
        priceField.setEditable(false);
        priceField.setFont(new Font("Verdana", Font.BOLD, 16));

        cellSettings.gridx = 3;
        cellSettings.gridy = 2;
        cellSettings.anchor = GridBagConstraints.CENTER;
        cellSettings.fill = GridBagConstraints.NONE;

        panel.add(priceField, cellSettings);

        JLabel amount = new JLabel("Amount: ");

        cellSettings.gridx = 1;
        cellSettings.gridy = 3;
        cellSettings.ipadx = 0;
        cellSettings.ipady = 0;
        cellSettings.anchor = GridBagConstraints.EAST;

        panel.add(amount, cellSettings);

        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        JFormattedTextField counter = new JFormattedTextField(numberFormat);
        counter.setColumns(3);
        cellSettings.gridx = 2;
        cellSettings.anchor = GridBagConstraints.WEST;

        panel.add(counter,cellSettings);

        JButton button = new JButton("Buy");
        button.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));

        cellSettings.gridx = 3;
        cellSettings.gridy = 3;
        cellSettings.ipadx = 150;
        cellSettings.ipady = 30;
        cellSettings.anchor = GridBagConstraints.CENTER;

        panel.add(button, cellSettings);



        return panel;
    }
}
