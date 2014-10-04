/*
 *Point of sale system for STEGH Gift Shop
 */
package GiftShop;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Nathan A GUI to add, search, and
 */
public class Gui extends JFrame implements ActionListener {

    //Declare buttons and menu items
    public static ArrayList<Refrence> rList = new ArrayList<>();

    private final String fpi = "P:\\Gift Shop\\Data\\DatabaseCPY.db";
    private final String fpo = "P:\\Gift Shop\\Data\\DatabaseCPY.db";
    private final String Log = "P:\\Gift Shop\\Data\\Log.csv";
    private final String Sale = "P:\\Gift Shop\\Data\\Sales.csv";
    private final JMenuItem add = new JMenuItem("Add");
    private final JMenuItem importI = new JMenuItem("Import Inventory From CSV");
    private final JMenuItem exportI = new JMenuItem("Export Inventory to CSV");
    private final JMenuItem exportS = new JMenuItem("Export Sales to CSV");
    private final JMenuItem exportL = new JMenuItem("Export Log to CSV");
    private final JMenuItem createL = new JMenuItem("Create Label");
    private final JMenuItem exit = new JMenuItem("Exit");
    private final JButton bCSearch = new JButton("Search");
    private final JButton adjB = new JButton("Adjust");
    private final JButton addB = new JButton("Add");
    private final JButton addBP = new JButton("Add");
    private final JButton sellBP = new JButton("Sale");
    private final JButton goBack = new JButton("Go Back");
    private final JButton saveB = new JButton("Save Changes");
    private final JButton confirm = new JButton("Confirm");
    private final JButton create = new JButton("Create");
    private final String[] sellQuantity = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    final JFileChooser choose = new JFileChooser();
    final FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "Comma Separated Values", "csv");

    SpinnerListModel quantSpinner = new SpinnerListModel(sellQuantity);
    JSpinner spinner = new JSpinner(quantSpinner);
    JMenuItem search = new JMenuItem("Search");
    int currentC = 0;
    int dup = 0;
    JFrame frame = new JFrame("STEGH Gift Shop");
    JMenuBar menuBar = new JMenuBar();
    JTextArea menuBox = new JTextArea();
    JMenu menu = new JMenu("File");
   // JMenu menu2 = new JMenu("Create");

    //Change ICON
    ImageIcon img = new ImageIcon("P:\\Gift Shop\\Data\\shop.gif");

    //Labels and text fields
    String Types[] = {"Book"};
    JLabel amountLbl = new JLabel("Amount:");
    JTextField amount = new JTextField();
    JLabel quantLbl = new JLabel("Quantity:");
    JComboBox type = new JComboBox(Types);
    JLabel barCLbl = new JLabel("Bar Code:");
    JTextField barC = new JTextField();
    JLabel titleLbl = new JLabel("Description:");
    JTextField title = new JTextField();
    JLabel priceLbl = new JLabel("Price:");
    JTextField price = new JTextField();
    JLabel costLbl = new JLabel("Cost:");
    JTextField cost = new JTextField();
    JLabel cBarCLbl = new JLabel("Barcode:");
    JTextField cBarC = new JTextField();
    JTextArea LabelCode = new JTextArea();

    JLabel compLbl = new JLabel("Company:");
    JTextField comp = new JTextField();
    JTextField year = new JTextField();
    JButton resetA = new JButton("Reset");
    JButton resetS = new JButton("Reset");
    JLabel addBo = new JLabel("Adding Inventory");
    JLabel sellBo = new JLabel("Selling Inventory");
    JLabel adjBo = new JLabel("Adjusting Inventory");
    JLabel searchLbl = new JLabel("Search");
    JTextArea output = new JTextArea();
    JLabel keyWLbl = new JLabel("Description:");
    JTextField keyW = new JTextField();
    JLabel stockLbl = new JLabel("In Stock:");
    JTextField stock = new JTextField();
    String Classes[] = {"Book"};
    JLabel steghLogo = new JLabel("");

    JComboBox typeChoose = new JComboBox(Classes);
    String barcode = "";

    int cFlag = 0;
    ArrayList<HashMap<String, String>> libHashList;
//This class contains the GUI elements
    public Gui() throws InterruptedException, IOException {
        if (cFlag == 0) {
            File fileToSave = new File("P:\\Gift Shop\\Data\\DatabaseCP.db");
          
            //
            File path = new File(fileToSave.getAbsolutePath());
            File DB = new File("P:\\Gift Shop\\Data\\Database.db");
            try {
                Files.copy(DB.toPath(), path.toPath(), REPLACE_EXISTING);
            } catch (IOException ex) {
                Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        //Use the Hashmap and data file from previous assignments

        //Create the refrencelist
        //Create the menu bar.
        //Add the items to the menu/GUI
        menuBar.add(menu);
//        menuBar.add(menu2);
        frame.setJMenuBar(menuBar);
        add.addActionListener(this);
        exportI.addActionListener(this);
        createL.addActionListener(this);
        importI.addActionListener(this);
        exportS.addActionListener(this);
        exportL.addActionListener(this);
        exit.addActionListener(this);
        search.addActionListener(this);
        addB.addActionListener(this);
        adjB.addActionListener(this);
        addBP.addActionListener(this);
        resetA.addActionListener(this);
        resetS.addActionListener(this);
        goBack.addActionListener(this);
        sellBP.addActionListener(this);
        confirm.addActionListener(this);
        bCSearch.addActionListener(this);
        barC.addActionListener(this);
        saveB.addActionListener(this);
        create.addActionListener(this);
        spinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent ce) {
                String sPrice;
                String quantity;
                double dPrice;
                int iQ;
                sPrice = price.getText();
                quantity = String.valueOf(spinner.getValue());
                dPrice = Double.parseDouble(sPrice);
                iQ = Integer.parseInt(quantity);
                dPrice = dPrice * iQ;
                String outString = String.format("%.2f", dPrice);
                amount.setText(outString);

            }
        });
        frame.setLayout(null);
        menu.add(importI);
        menu.add(exportI);
        menu.add(exportS);
        menu.add(exportL);
        menu.add(exit);
//        menu2.add(createL);
        frame.setBackground(Color.white);
        frame.setSize(400, 510);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set items to invisable/Set where they are;
        barCLbl.setBounds(40, 80, 100, 40);
        frame.add(barCLbl);
        barC.setBounds(120, 90, 150, 20);
        frame.add(barC);
        cBarCLbl.setBounds(40, 80, 100, 40);
        frame.add(cBarCLbl);
        LabelCode.setBounds(40, 80, 100, 40);
        frame.add(LabelCode);
        cBarC.setBounds(120, 90, 150, 20);
        frame.add(cBarC);
        typeChoose.setBounds(120, 50, 80, 20);
        frame.add(typeChoose);

        frame.add(title);
        resetA.setBounds(300, 100, 80, 40);
        resetS.setBounds(300, 100, 80, 40);
        create.setBounds(300, 100, 80, 40);
        frame.add(resetA);
        frame.add(resetS);
//        frame.add(create);
        keyWLbl.setBounds(40, 160, 100, 40);
        frame.add(keyWLbl);

        //Searches
        title.setBounds(120, 170, 150, 20);
        titleLbl.setBounds(40, 160, 100, 40);
        frame.add(titleLbl);
        keyW.setBounds(120, 130, 160, 20);
        frame.add(keyW);
        comp.setBounds(120, 130, 150, 20);
        frame.add(comp);
        compLbl.setBounds(40, 130, 150, 20);
        frame.add(compLbl);
        price.setBounds(120, 250, 150, 20);
        frame.add(price);
        priceLbl.setBounds(40, 250, 150, 20);
        frame.add(priceLbl);
        stock.setBounds(120, 210, 150, 20);
        frame.add(stock);
        stockLbl.setBounds(40, 210, 150, 20);
        frame.add(stockLbl);
        quantLbl.setBounds(40, 280, 80, 40);
        frame.add(quantLbl);
        amountLbl.setBounds(40, 330, 80, 40);
        frame.add(amountLbl);
        amount.setBounds(120, 340, 150, 20);
        frame.add(amount);
        spinner.setBounds(120, 290, 150, 20);
        frame.add(spinner);
        costLbl.setBounds(40, 290, 150, 20);
        cost.setBounds(120, 290, 150, 20);
        frame.add(costLbl);
        frame.add(cost);

        //Add more items
        addBo.setBounds(40, 00, 200, 40);
        frame.add(addBo);
        bCSearch.setBounds(40, 150, 200, 40);
        frame.add(bCSearch);
        sellBo.setBounds(40, 0, 200, 40);
        frame.add(sellBo);
        adjBo.setBounds(40, 0, 200, 40);
        frame.add(adjBo);
        searchLbl.setBounds(40, 00, 200, 40);
        frame.add(searchLbl);
        addBP.setBounds(300, 160, 80, 40);
        frame.add(addBP);

        //Buttons
        goBack.setBounds(150, 00, 200, 40);
        frame.add(goBack);
        adjB.setBounds(60, 320, 80, 40);
        frame.add(adjB);
        sellBP.setBounds(200, 320, 80, 40);
        frame.add(sellBP);
        saveB.setBounds(200, 320, 150, 40);
        frame.add(saveB);
        confirm.setBounds(250, 400, 80, 40);
        frame.add(confirm);
        addB.setBounds(60, 40, 250, 40);
        frame.add(addB);

        //Pic
        steghLogo.setIcon(new ImageIcon("P:\\Gift Shop\\Data\\stegh.gif"));
        steghLogo.setBounds(0, 340, 400, 120);
        frame.add(steghLogo);
        frame.setIconImage(img.getImage());

        title.setEditable(false);
        price.setEditable(false);
        stock.setEditable(false);
        amount.setEditable(false);

        //Settting everything to invisable
        barC.setVisible(false);
        barCLbl.setVisible(false);
        titleLbl.setVisible(false);
        title.setVisible(false);
        resetA.setVisible(false);
        resetS.setVisible(true);
        keyWLbl.setVisible(false);
        keyW.setVisible(false);
        price.setVisible(false);
        priceLbl.setVisible(false);
        stock.setVisible(false);
        typeChoose.setVisible(false);
        addB.setVisible(false);
        adjB.setVisible(false);
        goBack.setVisible(false);
        addBP.setVisible(false);
        searchLbl.setVisible(false);
        addBo.setVisible(false);
        stockLbl.setVisible(false);
        sellBo.setVisible(false);
        adjBo.setVisible(false);
        sellBP.setVisible(false);
        bCSearch.setVisible(false);
        adjB.setVisible(false);
        addB.setVisible(false);
        confirm.setVisible(false);
        amount.setVisible(false);
        quantLbl.setVisible(false);
        amountLbl.setVisible(false);
        spinner.setVisible(false);
        saveB.setVisible(false);
        steghLogo.setVisible(true);
        cost.setVisible(false);
        costLbl.setVisible(false);
        comp.setVisible(false);
        compLbl.setVisible(false);
        cBarCLbl.setVisible(false);
        cBarC.setVisible(false);
        create.setVisible(false);
        LabelCode.setVisible(false);

        barC.setVisible(true);
        barCLbl.setVisible(true);
        bCSearch.setVisible(true);
        barC.requestFocusInWindow();
    }

    @Override
    @SuppressWarnings("empty-statement")
    public void actionPerformed(ActionEvent E) {
        revalidate();
        repaint();
//        if (E.getSource() == create) {
//            
//        
//        
//               LabelCode.setVisible(true);
//            cBarCLbl.setVisible(false);
//            cBarC.setVisible(false);
//            create.setVisible(false);
//            Font font1 = new Font("CCode39", Font.PLAIN, 7);
//            String barC = cBarC.getText();
//            LabelCode.setFont(font1);
//            LabelCode.setText("*12345*");
//            
//
//         
//
//            goBack.setVisible(true);
//
//        }

        if (E.getSource() == createL) {

            //cBarCLbl.setVisible(true);
            // cBarC.setVisible(true);
            //create.setVisible(true);
            barC.setVisible(false);
            barCLbl.setVisible(false);
            titleLbl.setVisible(false);
            title.setVisible(false);
            resetA.setVisible(false);
            resetS.setVisible(true);
            keyWLbl.setVisible(false);
            keyW.setVisible(false);
            price.setVisible(false);
            priceLbl.setVisible(false);
            stock.setVisible(false);
            typeChoose.setVisible(false);
            addB.setVisible(false);
            adjB.setVisible(false);
            goBack.setVisible(true);
            addBP.setVisible(false);
            searchLbl.setVisible(false);
            addBo.setVisible(false);
            stockLbl.setVisible(false);
            sellBo.setVisible(false);
            adjBo.setVisible(false);
            sellBP.setVisible(false);
            bCSearch.setVisible(false);
            adjB.setVisible(false);
            addB.setVisible(false);
            confirm.setVisible(false);
            amount.setVisible(false);
            quantLbl.setVisible(false);
            amountLbl.setVisible(false);
            spinner.setVisible(false);
            saveB.setVisible(false);
            steghLogo.setVisible(true);
            cost.setVisible(false);
            costLbl.setVisible(false);
            comp.setVisible(false);
            compLbl.setVisible(false);
            resetA.setVisible(false);
            resetS.setVisible(false);
        }

        if (E.getSource() == importI) {
            int value = 0;
            JOptionPane.showMessageDialog(frame, "This will overwrite the current Database! Click Cancel to Exit");

            //Open File
            //Copy old DB to DB.old
            //Create New DB
            choose.setFileFilter(filter);
            choose.setDialogTitle("Specify a filename");
            choose.setSelectedFile(new File("Inventory.csv"));
            int returnVal = choose.showOpenDialog(frame);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File fileToSave = choose.getSelectedFile();
                //
                File path = new File(fileToSave.getAbsolutePath());

                try {
                    value = FileIO.importI(path);
                    if (value == 1) {
                        JOptionPane.showMessageDialog(frame, "You've selected the wrong file!");
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        //Export Inventory
        if (E.getSource() == exportI) {

            //File Writer Shit
            //Copy Confrim log to open Save as window
            try {
                libHashList = FileIO.readDataFromFile(fpi);
            } catch (IOException ex) {
                Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
            }
            rList = FileIO.convertHashMapListToLibList(libHashList);
            try {
                FileIO.PrintIn(rList);
            } catch (FileNotFoundException | UnsupportedEncodingException ex) {
                Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
            }
            choose.setFileFilter(filter);
            choose.setDialogTitle("Specify a filename");
            choose.setSelectedFile(new File("Inventory.csv"));
            int returnVal = choose.showSaveDialog(frame);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File fileToSave = choose.getSelectedFile();
                //
                File path = new File(fileToSave.getAbsolutePath());
                File SalesCsv = new File("P:\\Gift Shop\\Data\\Inventory.csv");
                try {
                    Files.copy(SalesCsv.toPath(), path.toPath(), REPLACE_EXISTING);
                } catch (IOException ex) {
                    Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
        //Export Sales
        if (E.getSource() == exportS) {
            //Copy file
            choose.setFileFilter(filter);
            choose.setDialogTitle("Specify a filename");
            choose.setSelectedFile(new File("Sales.csv"));
            int returnVal = choose.showSaveDialog(frame);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File fileToSave = choose.getSelectedFile();
                //
                File path = new File(fileToSave.getAbsolutePath());
                File SalesCsv = new File("P:\\Gift Shop\\Data\\Sales.csv");
                try {
                    Files.copy(SalesCsv.toPath(), path.toPath(), REPLACE_EXISTING);
                } catch (IOException ex) {
                    Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
        if (E.getSource() == exportL) {
            choose.setFileFilter(filter);
            choose.setDialogTitle("Specify a filename");
            choose.setSelectedFile(new File("Log.csv"));
            int returnVal = choose.showSaveDialog(frame);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File fileToSave = choose.getSelectedFile();
                //
                File path = new File(fileToSave.getAbsolutePath());
                File SalesCsv = new File("P:\\Gift Shop\\Data\\Log.csv");
                try {
                    Files.copy(SalesCsv.toPath(), path.toPath(), REPLACE_EXISTING);
                } catch (IOException ex) {

                    Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
        if (E.getSource() == saveB) {
            int check = 1;
            String sInput;
            String INV = "";
            int iInput;
            double dInput;
            String oPrice = "";
            String oStock = "";
            String oCost = "";
            for (int i = 0; i < rList.size(); i++) {
                if (rList.get(i).getCallN().equals(barC.getText())) {

                    oPrice = rList.get(i).getPrice();
                    oStock = rList.get(i).getQuant();
                    oCost = rList.get(i).getCost();

                    if (barC.getText().equals("")) {
                        JOptionPane.showMessageDialog(frame, "You need to enter a Bar Code!");
                        break;
                    } else {
                        rList.get(i).setCallN(barC.getText());
                    }
                    if (comp.getText().equals("")) {
                        JOptionPane.showMessageDialog(frame, "You need to enter a Company!");
                        break;
                    } else if (comp.getText().contains(",")) {
                        JOptionPane.showMessageDialog(frame, "Sorry, you cant Have a comma in Company Name!");
                        check = 0;
                        break;
                    } else {
                        rList.get(i).setComp(comp.getText());
                    }
                    if (title.getText().equals("")) {
                        JOptionPane.showMessageDialog(frame, "You need to enter a Description!");
                        break;
                    } else if (title.getText().contains(",")) {
                        JOptionPane.showMessageDialog(frame, "Sorry, you cant Have a comma in the Description!");
                        check = 0;
                        break;
                    } else {
                        rList.get(i).setTitle(title.getText());
                    }
                    sInput = stock.getText();
                    try {
                        iInput = Integer.parseInt(sInput);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(frame, "You need to enter a correct whole Number for Stock!");
                        stock.setText("");
                        break;
                    }
                    if (stock.getText().equals("")) {
                        JOptionPane.showMessageDialog(frame, "You need to enter a Quantity!");
                        break;
                    } else {
                        rList.get(i).setQuant(stock.getText());
                    }
                    sInput = price.getText();
                    try {
                        dInput = Double.parseDouble(sInput);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(frame, "You need to enter a correct Number for Price!");
                        price.setText("");
                        break;
                    }
                    if (price.getText().equals("")) {
                        JOptionPane.showMessageDialog(frame, "You need to enter a Price!");
                    } else {
                        rList.get(i).setPrice(price.getText());
                    }
                    sInput = cost.getText();
                    try {
                        dInput = Double.parseDouble(sInput);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(frame, "You need to enter a correct Number for Cost!");
                        cost.setText("");
                        break;
                    }
                    if (price.getText().equals("")) {
                        JOptionPane.showMessageDialog(frame, "You need to enter a Cost!");
                    } else {
                        rList.get(i).setCost(cost.getText());
                    }
                }

                //Duplicate for callNumber and Year
                if (dup >= 2) {
                    JOptionPane.showMessageDialog(frame, "You have a duplicate!");
                    break;

                    //If no problems, add to the arrayList
                }
                try {
                    FileIO.PrintFile(rList, fpo);

                } catch (FileNotFoundException | UnsupportedEncodingException ex) {
                    Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            for (int i = 0; i < rList.size(); i++) {
                if (rList.get(i).getCallN().equals(barC.getText())) {

                    title.setText(rList.get(i).getTitle());
                    stock.setText(rList.get(i).getQuant());
                    price.setText(rList.get(i).getPrice());
                    cost.setText(rList.get(i).getCost());
                    comp.setText(rList.get(i).getComp());

                    break;
                } else {

                }

            }
            if (check == 1) {
                title.setEditable(false);
                stock.setEditable(false);
                price.setEditable(false);
                cost.setEditable(false);
                comp.setEditable(false);

                //Type,Time,Barcode,Company,Discription,Old Price,Price,Old Stock,Stock,Old Cost,Cost,Sold,Amount
                INV = (barC.getText() + "," + comp.getText() + "," + title.getText() + "," + oPrice + "," + price.getText()
                        + "," + oStock + "," + stock.getText() + "," + oCost + "," + cost.getText() + ",-," + "-");
            }
            try {
                //Export to CSV
                FileIO.PrintLogFile(Log, "Adjusting", INV);
            } catch (FileNotFoundException ex) {
            } catch (UnsupportedEncodingException ex) {
            } catch (IOException ex) {
            }

        }
        //Begin Search
        if (E.getSource() == bCSearch || E.getSource() == barC) {
            try {
                libHashList = FileIO.readDataFromFile(fpi);
            } catch (IOException ex) {
                Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
            }
            rList = FileIO.convertHashMapListToLibList(libHashList);
            barC.setEditable(false);
            String bar2 = barC.getText();
            barcode = barC.getText();
            bar2 = "0" + bar2;
            String barc3 = barC.getText();

            String found = "";
            //Start the main search structure

            for (int i = 0; i < rList.size(); i++) {
                if (rList.get(i).getCallN().equals(barC.getText())) {
                    found = "Found";
                } else if (rList.get(i).getCallN().equals(bar2)) {
                    found = "Found";
                } else if (rList.get(i).getCallN().equals(barc3.substring(1))) {
                    found = "Found";
                }
                //Found output
                if ("Found".equals(found)) {

                    String spinnerResetS = "0";
                    Object spinnerResetO = spinnerResetS;
                    spinner.setValue(spinnerResetO);
                    steghLogo.setVisible(false);
                    bCSearch.setVisible(false);
                    barC.setVisible(true);
                    barCLbl.setVisible(true);
                    adjB.setVisible(true);
                    goBack.setVisible(true);
                    sellBP.setVisible(true);
                    addB.setVisible(false);
                    adjBo.setVisible(false);
                    adjB.setVisible(false);
                    goBack.setVisible(true);
                    addBo.setVisible(false);
                    sellBo.setVisible(true);
                    addBP.setVisible(false);
                    stockLbl.setVisible(false);
                    searchLbl.setVisible(false);
                    menuBox.setVisible(false);
                    titleLbl.setVisible(true);
                    title.setVisible(true);
                    typeChoose.setVisible(false);
                    keyWLbl.setVisible(false);
                    keyW.setVisible(false);
                    resetA.setVisible(false);
                    resetS.setVisible(false);
                    stock.setVisible(true);
                    stockLbl.setVisible(true);
                    priceLbl.setVisible(true);
                    price.setVisible(true);
                    adjB.setVisible(true);
                    spinner.setVisible(false);
                    title.setEditable(false);
                    price.setEditable(false);
                    stock.setEditable(false);
                    amount.setEditable(false);
                    cost.setVisible(false);
                    costLbl.setVisible(false);
                    cost.setEditable(false);
                    comp.setEditable(false);
                    comp.setVisible(true);
                    compLbl.setVisible(true);
                    if ((rList.get(i).getCallN().equals(bar2)) || rList.get(i).getCallN().equals(barcode) || rList.get(i).getCallN().equals(barc3.substring(1))) {
                        keyW.setText(barcode);
                        barC.setText(barcode);
                        title.setText(rList.get(i).getTitle());
                        stock.setText(rList.get(i).getQuant());
                        price.setText(rList.get(i).getPrice());
                        cost.setText(rList.get(i).getCost());
                        comp.setText(rList.get(i).getComp());
                    } else {
                    }
                } else {
                    comp.setVisible(true);
                    compLbl.setVisible(true);
                    cost.setVisible(true);
                    comp.setEditable(true);
                    costLbl.setVisible(true);
                    cost.setEditable(true);
                    title.setEditable(true);
                    price.setEditable(true);
                    stock.setEditable(true);
                    amount.setEditable(true);
                    barC.setEditable(false);
                    bCSearch.setVisible(false);
                    barC.setVisible(true);
                    barCLbl.setVisible(true);
                    addB.setVisible(false);
                    goBack.setVisible(true);
                    addB.setVisible(false);
                    sellBo.setVisible(false);
                    adjB.setVisible(false);
                    goBack.setVisible(true);
                    addBo.setVisible(true);
                    adjBo.setVisible(false);
                    addBP.setVisible(true);
                    stockLbl.setVisible(false);
                    searchLbl.setVisible(false);
                    menuBox.setVisible(false);
                    titleLbl.setVisible(true);
                    title.setVisible(true);
                    typeChoose.setVisible(false);
                    keyWLbl.setVisible(false);
                    keyW.setVisible(false);
                    resetA.setVisible(false);
                    resetS.setVisible(false);
                    stock.setVisible(true);
                    stockLbl.setVisible(true);
                    priceLbl.setVisible(true);
                    price.setVisible(true);
                    amount.setVisible(false);
                    quantLbl.setVisible(false);
                    title.setText("");
                    price.setText("");
                    year.setText("");
                    keyW.setText("");
                    stock.setText("");
                    cost.setText("");
                    comp.setText("");
                }
            }
        }

        //Add refrence menu button
        if (E.getSource() == goBack) {

            comp.setVisible(false);
            compLbl.setVisible(false);
            cost.setVisible(false);
            costLbl.setVisible(false);
            steghLogo.setVisible(true);
            String spinnerResetS = "0";
            Object spinnerResetO = spinnerResetS;
            spinner.setValue(spinnerResetO);
            barC.setText("");
            saveB.setVisible(false);
            amountLbl.setVisible(false);
            barC.setVisible(false);
            barC.setEditable(true);
            barCLbl.setVisible(false);
            titleLbl.setVisible(false);
            title.setVisible(false);
            resetA.setVisible(false);
            resetS.setVisible(true);
            keyWLbl.setVisible(false);
            keyW.setVisible(false);
            price.setVisible(false);
            priceLbl.setVisible(false);
            stock.setVisible(false);
            typeChoose.setVisible(false);
            addB.setVisible(false);
            adjB.setVisible(false);
            goBack.setVisible(false);
            addBP.setVisible(false);
            searchLbl.setVisible(false);
            addBo.setVisible(false);
            stockLbl.setVisible(false);
            sellBo.setVisible(false);
            adjBo.setVisible(false);
            sellBP.setVisible(false);
            bCSearch.setVisible(false);
            adjB.setVisible(false);
            addB.setVisible(false);
            barC.setVisible(true);
            barCLbl.setVisible(true);
            bCSearch.setVisible(true);
            confirm.setVisible(false);
            amount.setVisible(false);
            quantLbl.setVisible(false);
            spinner.setVisible(false);
            barC.requestFocusInWindow();
            steghLogo.setVisible(true);
            cBarCLbl.setVisible(false);
            cBarC.setVisible(false);
            create.setVisible(false);
            LabelCode.setVisible(false);
            revalidate();
            repaint();

        }

        //Add Adjustment Button
        if (E.getSource() == adjB) {

            saveB.setVisible(true);
            addB.setVisible(false);
            adjBo.setVisible(false);
            sellBo.setVisible(false);
            searchLbl.setVisible(false);
            adjB.setVisible(false);
            search.setVisible(false);
            title.setVisible(true);
            titleLbl.setVisible(true);
            resetS.setVisible(false);
            goBack.setVisible(true);
            price.setVisible(true);
            priceLbl.setVisible(true);
            addBo.setVisible(false);
            stockLbl.setVisible(true);
            priceLbl.setVisible(true);
            price.setVisible(true);
            stockLbl.setVisible(true);
            stock.setVisible(true);
            sellBP.setVisible(false);
            costLbl.setVisible(true);
            cost.setVisible(true);

            comp.setEditable(true);
            cost.setEditable(true);
            title.setEditable(true);
            price.setEditable(true);
            stock.setEditable(true);
            amount.setEditable(true);
            spinner.setEnabled(true);

        }

        //Add Button Menu Item
        if (E.getSource() == addB) {

            addB.setVisible(false);
            sellBo.setVisible(false);
            adjB.setVisible(false);
            goBack.setVisible(true);
            addBo.setVisible(true);
            adjBo.setVisible(false);
            addBP.setVisible(true);
            stockLbl.setVisible(false);
            searchLbl.setVisible(false);
            menuBox.setVisible(false);
            titleLbl.setVisible(true);
            title.setVisible(true);
            keyWLbl.setVisible(false);
            keyW.setVisible(false);
            resetA.setVisible(true);
            resetS.setVisible(false);
            stock.setVisible(true);
            stockLbl.setVisible(true);
            priceLbl.setVisible(true);
            price.setVisible(true);
            title.setText("");
            price.setText("");
            year.setText("");
            keyW.setText("");
            stock.setText("");
        }

        //Reset button for add
        if (E.getSource() == resetA) {
            title.setText("");
            price.setText("");
            keyW.setText("");
            stock.setText("");

        }
        //Reset button for search
        if (E.getSource() == resetS) {
            barC.setText("");

        }

        //Add buton on the frame
        if (E.getSource() == addBP) {
            int check = 1;
            String sInput;
            double dInput;
            int iInput;
            String INV = "";
            Refrence ent;
            switch (typeChoose.getSelectedItem().toString()) {
                case "Book":
                    ent = new Book();

                    if (barC.getText().equals("")) {
                        JOptionPane.showMessageDialog(frame, "You need to enter a Bar Code!");
                        output.append("Failed addition \n");
                        break;
                    } else {
                        ent.setCallN(barC.getText());
                    }
                    if (comp.getText().equals("")) {
                        JOptionPane.showMessageDialog(frame, "You need to enter a Company!");
                        break;
                    } else if (title.getText().contains(",")) {
                        JOptionPane.showMessageDialog(frame, "Sorry, you cant Have a comma in the Description!");
                        check = 0;
                        break;
                    } else {
                        ent.setComp(comp.getText());
                    }
                    if (title.getText().equals("")) {
                        JOptionPane.showMessageDialog(frame, "You need to enter a Description!");
                        break;
                    } else if (title.getText().contains(",")) {
                        JOptionPane.showMessageDialog(frame, "Sorry, you cant Have a comma in the Description!");
                        check = 0;
                        break;
                    } else {
                        ent.setTitle(title.getText());
                    }
                    sInput = price.getText();
                    try {
                        dInput = Double.parseDouble(sInput);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(frame, "You need to enter a correct Number for Pice!");
                        price.setText("");
                        break;
                    }
                    if (price.getText().equals("")) {
                        JOptionPane.showMessageDialog(frame, "You need to enter a Price!");
                    } else {
                        ent.setPrice(price.getText());
                    }
                    sInput = stock.getText();
                    try {
                        iInput = Integer.parseInt(sInput);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(frame, "You need to enter a correct whole Number for Stock!");
                        stock.setText("");
                        break;
                    }
                    if (stock.getText().equals("")) {
                        JOptionPane.showMessageDialog(frame, "You need to enter a Quantity!");
                        break;
                    } else {
                        ent.setQuant(stock.getText());
                    }

                    sInput = cost.getText();
                    try {
                        dInput = Double.parseDouble(sInput);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(frame, "You need to enter a correct Number for Cost!");
                        cost.setText("");
                        break;
                    }
                    if (cost.getText().equals("")) {
                        JOptionPane.showMessageDialog(frame, "You need to enter a Cost!");
                    } else {
                        ent.setCost(cost.getText());
                    }
                    //Duplicate for callNumber and Year
                    if (dup >= 2 && check == 0) {
                        JOptionPane.showMessageDialog(frame, "You have a duplicate!");
                        break;

                        //If no problems, add to the arrayList
                    } else {
                        rList.add(ent);
                        addBP.setVisible(false);

                        INV = (barC.getText() + "," + comp.getText() + "," + title.getText() + "," + "-" + "," + price.getText()
                                + "," + "-" + "," + stock.getText() + "," + "-" + "," + cost.getText() + "," + "-" + "," + "-");
                        try {
                            //Export to CSV
                            FileIO.PrintLogFile(Log, "Adding", INV);
                        } catch (FileNotFoundException ex) {
                        } catch (UnsupportedEncodingException ex) {
                        } catch (IOException ex) {
                        }

                        try {
                            FileIO.PrintFile(rList, fpo);
                        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
                            Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                    break;
            }
        }
        if (E.getSource() == confirm) {

            confirm.setVisible(false);
            spinner.setEnabled(false);

            String spinQuant;
            String inStock;
            int iStock;
            spinQuant = String.valueOf(spinner.getValue());
            inStock = stock.getText();
            iStock = Integer.parseInt(inStock) - Integer.parseInt(spinQuant);
            if (iStock < 0) {
                JOptionPane.showMessageDialog(frame, "You're Selling more than you have!");
                confirm.setVisible(true);
                spinner.setEnabled(true);
                iStock = Integer.parseInt(stock.getText());
                String spinnerResetS = "0";
                Object spinnerResetO = spinnerResetS;
                spinner.setValue(spinnerResetO);
            } else if (iStock > 0) {
                stock.setText(String.valueOf(iStock));
            }
            String INV = "";
            for (int i = 0; i < rList.size(); i++) {
                if (rList.get(i).getCallN().equals(barC.getText())) {
                    rList.get(i).setQuant(String.valueOf(iStock));
                    String sPrice;
                    double iPrice;
                    sPrice = rList.get(i).getPrice();
                    iPrice = Double.parseDouble(sPrice);
                    String outString = String.format("%.2f", iPrice);
                    //amount.setText(outString);

                    break;
                } else {
                }
            }
            Double dCost = Double.parseDouble(cost.getText());
            Double dPrice = Double.parseDouble(price.getText());
            Double profit = dPrice - dCost;
            INV = (barC.getText() + "," + comp.getText() + "," + title.getText() + "," + "-" + "," + price.getText()
                    + "," + "-" + "," + stock.getText() + "," + "-" + "," + cost.getText() + "," + spinQuant + "," + amount.getText());
            String sellString = (barC.getText() + "," + comp.getText() + "," + title.getText() + "," + stock.getText()
                    + "," + price.getText() + "," + spinQuant + "," + amount.getText() + "," + cost.getText() + "," + profit);
            try {
                //Export to CSV
                FileIO.PrintLogFile(Log, "Selling", INV);
            } catch (FileNotFoundException ex) {
            } catch (UnsupportedEncodingException ex) {
            } catch (IOException ex) {
            }
            try {
                FileIO.PrintFile(rList, fpo);
            } catch (FileNotFoundException | UnsupportedEncodingException ex) {
                Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                FileIO.PrintSale(Sale, sellString);
            } catch (FileNotFoundException | UnsupportedEncodingException ex) {
                Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
            }
            ArrayList<HashMap<String, String>> libHashList = null;
            try {
                libHashList = FileIO.readDataFromFile(fpi);
            } catch (IOException ex) {
                Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        if (E.getSource() == sellBP) {

            spinner.setEnabled(true);
            amount.setVisible(true);
            adjB.setVisible(false);
            amountLbl.setVisible(true);
            sellBP.setVisible(false);
            confirm.setVisible(true);
            quantLbl.setVisible(true);
            spinner.setVisible(true);
            comp.setVisible(true);
            String spinnerResetS = "1";
            Object spinnerResetO = spinnerResetS;
            spinner.setValue(spinnerResetO);
            for (int i = 0; i < rList.size(); i++) {
                if (rList.get(i).getCallN().equals(barC.getText())) {

                    title.setText(rList.get(i).getTitle());
                    stock.setText(rList.get(i).getQuant());
                    price.setText(rList.get(i).getPrice());
                    cost.setText(rList.get(i).getCost());
                    String sPrice;
                    double iPrice;
                    sPrice = rList.get(i).getPrice();
                    iPrice = Double.parseDouble(sPrice);
                    String outString = String.format("%.2f", iPrice);
                    amount.setText(outString);

                    break;
                } else {
                }
            }
        }
        if (E.getSource() == exit) {
            System.exit(0);
        }
        validate();
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        Gui aGui = new Gui();

    }
}
