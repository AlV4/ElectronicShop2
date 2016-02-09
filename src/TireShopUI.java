import data.Storage;
import items.Costumer;
import items.Producers;
import items.Seasons;
import items.Transaction;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;

public class TireShopUI extends JFrame{

    private TireShop shop;
    private Storage storage;
    private String [] columns = {"ID", "Tire", "Costumer", "Amount", "Sell", "Buy", "Date"};
    private JTable table;
    private TableModel model;
    private JMenuBar menuBar;
    private JMenu menu;
    private boolean isVisibleOrder;

    public TireShopUI(TireShop shop){
        this.shop = shop;
        storage = shop.getStorage();

        setJMenuBar(createMenu());
        setTitle("Tire shop");
        setMinimumSize(new Dimension(1200, 800));
        setResizable(false);
        setLocationRelativeTo(null);
        getContentPane().add(createPanel());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setVisible(isVisibleOrder);

        JFrame tableFrame = new JFrame("Tire shop");
        tableFrame.setJMenuBar(createMenu());
        tableFrame.setMinimumSize(new Dimension(1200, 800));
        tableFrame.setResizable(false);
        tableFrame.setLocationRelativeTo(null);
        tableFrame.getContentPane().add(createPanelWithTable());
        tableFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        tableFrame.pack();
        tableFrame.setVisible(true);
    }
    private JMenuBar createMenu(){
        menuBar = new JMenuBar();
        menuBar.setSize(1200, 20);
        menu = new JMenu("File");
        JButton buy = new JButton("Buy");
        buy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(true);
            }
        });
        menu.add(buy);
        menuBar.add(menu);
        return menuBar;
    }

    private JPanel createPanelWithTable(){
        JPanel panel = new JPanel(new GridBagLayout());
        model = new TableModel() {


            @Override
            public int getRowCount() {
                return shop.getTransactions().size();
            }

            @Override
            public int getColumnCount() {
                return columns.length;
            }

            @Override
            public String getColumnName(int columnIndex) {
                return columns[columnIndex];
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return new Transaction().fields.getClass();
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                return shop.getTransactions().get(rowIndex).fields[columnIndex];
            }

            @Override
            public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

            }

            @Override
            public void addTableModelListener(TableModelListener l) {

            }

            @Override
            public void removeTableModelListener(TableModelListener l) {

            }
        };

        table = new JTable(model);
        table.getColumnModel().getColumn(6).setPreferredWidth(300);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        return panel;
    }

    private JPanel createPanel(){
        JPanel panel = new JPanel(new GridBagLayout());
        final JLabel textName = new JLabel("Input Your name, please: ");
        final JTextField nameTextField = new JTextField(20);
        nameTextField.setText(" ");

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

        panel.add(priceField, new GridBagConstraints(3, 2, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

        final JLabel amount = new JLabel("Amount: ");

        panel.add(amount, new GridBagConstraints(1,3,1,1,1,1, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0,0,0,0), 0,0));

        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        final JFormattedTextField counter = new JFormattedTextField(numberFormat);
        counter.setColumns(3);
        counter.setValue(1);

        panel.add(counter, new GridBagConstraints(2, 3, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

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
                table.tableChanged(new TableModelEvent(model, TableModelEvent.ALL_COLUMNS));
                setVisible(false);

            }
        });

        panel.add(button, new GridBagConstraints(3, 3, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 150, 30));


        return panel;
    }
}
