/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GiftShop;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author Nathan
 */
public class FileIO {

    public static ArrayList<HashMap<String, String>> readDataFromFile(String file) throws FileNotFoundException, IOException {
        ArrayList<HashMap<String, String>> libInfo = new ArrayList();
        int error = 0;
        HashMap currentHash = new HashMap();
        String currentLineSplit[] = null;
        String currentLine;
        final int VALUE = 1;
        final int KEY = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            currentLine = br.readLine();
            while (currentLine != null) {

                //decide what to do based on info from current line in the file
                if (currentLine.equalsIgnoreCase("[Entry]")) {
                } else if (currentLine.equals("")) {
                    libInfo.add(currentHash);
                    currentHash = new HashMap();

                } else {//store the values found in the file
                    currentLineSplit = currentLine.split("=");
                    currentHash.put(currentLineSplit[KEY], currentLineSplit[VALUE]);
                }

                //prepare all local variables for next iteration of loop
                currentLine = br.readLine();
                currentLineSplit = null;
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            //System.out.println("Sorry, one of more entrys is corrupt. It will be deleted on close.");
            error++;
        }
        if (error == 0) {
            // System.out.println("File Read Succesfuly");
        }
        return libInfo;
    }

    public static Refrence convertHashMapToLib(HashMap<String, String> hash) {
        Refrence newEnt;
        Iterator it = hash.entrySet().iterator();
        newEnt = new Book();

        while (it.hasNext()) {

            Map.Entry hashEntry = (Map.Entry) it.next();

            switch (((String) hashEntry.getKey()).toLowerCase()) {
                case "type":
                    newEnt.setType((String) hashEntry.getValue());
                    break;
                case "barcode":
                    newEnt.setCallN((String) hashEntry.getValue());
                    break;
                case "description":
                    newEnt.setTitle((String) hashEntry.getValue());
                    break;
                case "quantity":
                    newEnt.setQuant((String) hashEntry.getValue());
                    break;
                case "price":
                    newEnt.setPrice((String) hashEntry.getValue());
                    break;
                case "cost":
                    newEnt.setCost((String) hashEntry.getValue());
                    break;
                case "comp":
                    newEnt.setComp((String) hashEntry.getValue());
                    break;
            }

        }
        return newEnt;
    }

    public static ArrayList<Refrence> convertHashMapListToLibList(ArrayList<HashMap<String, String>> hashList) {
        Iterator<HashMap<String, String>> it = hashList.iterator();
        ArrayList<Refrence> libList = new ArrayList();
        Refrence newEnt;

        while (it.hasNext()) {
            newEnt = convertHashMapToLib(it.next());
            libList.add(newEnt);
        }

        return libList;
    }

    /**
     * takes a File info and stores into in a hash map
     *
     * @param lib has all the library information
     * @return the hash map
     */
    public static HashMap<String, String> convertLibToHashMap(Refrence lib) {
        HashMap<String, String> hash = new HashMap<>();


        {
            hash.put("barcode", lib.getCallN());
            hash.put("comp", lib.getComp());
            hash.put("description", lib.getTitle());
            hash.put("quantity", lib.getQuant());
            hash.put("price", lib.getPrice());
            hash.put("cost", lib.getCost());
           

        }

        return hash;
    }

    /**
     * takes a list of Library and stores into in a list of hash maps
     *
     * @param libList holds all info for the library
     * @return the hashlist
     */
    public static ArrayList<HashMap<String, String>> convertLibListToHashMapList(ArrayList<Refrence> libList) {
        Iterator<Refrence> it = libList.iterator();
        ArrayList<HashMap<String, String>> hashList = new ArrayList<>();
        HashMap<String, String> currentHash;

        while (it.hasNext()) {
            currentHash = convertLibToHashMap(it.next());
            hashList.add(currentHash);
        }

        return hashList;
    }

    public static void PrintLogFile(String FilePath, String buttonP, String INV) throws FileNotFoundException, UnsupportedEncodingException, IOException {
        String timeStamp = new SimpleDateFormat("HH:mm:ss MM/dd/yyyy").format(Calendar.getInstance().getTime());

        try (PrintWriter writer = new PrintWriter(new FileWriter(FilePath, true))) {

            writer.print(buttonP + ",");
            writer.print(timeStamp);
            writer.println("," + INV);
            writer.close();
        }

    }

    public static void PrintSale(String FilePath, String INV) throws FileNotFoundException, UnsupportedEncodingException, IOException {
        String timeStamp = new SimpleDateFormat("HH:mm:ss MM/dd/yyyy").format(Calendar.getInstance().getTime());

        try (PrintWriter writer = new PrintWriter(new FileWriter(FilePath, true))) {

            writer.print(timeStamp);
            writer.println("," + INV);
            writer.close();
        }
    }

    public static void PrintIn(ArrayList<Refrence> rList) throws FileNotFoundException, UnsupportedEncodingException {
        try (PrintWriter writer = new PrintWriter("P:\\Gift Shop\\Data\\Inventory.csv", "UTF-8")) {
            writer.println("Barcode" + "," + "Company" + "," + "Description" + "," + "In stock" + "," + "Price" + "," + "Cost");

            //writer.println("Company" + "," + "Description" + "," + "In stock" + "," + "Price" + "," + "Cost");
            for (int i = 0; i < rList.size(); i++) {
                writer.print(rList.get(i).toStringI());
            }

            writer.close();
        }

    }

    public static int importI(File filePath) throws FileNotFoundException, IOException {
        String Barcode;
        String Company;
        String Description;
        String stock;
        String Price;
        String Cost;

        BufferedReader reader = null;
        int val = 1;
        reader = new BufferedReader(new FileReader(filePath));
        System.out.println(filePath);
        String text = null;
        text = reader.readLine();
        if (text.equalsIgnoreCase("Barcode" + "," + "Company" + "," + "Description" + "," + "In stock" + "," + "Price" + "," + "Cost")) {
            val = 0;
        }
        try (PrintWriter writer = new PrintWriter("P:\\Gift Shop\\Data\\Database.db", "UTF-8")) {
        while ((text = reader.readLine()) != null) {
            String[] result = text.split(",");
                      
            
                    

//            hash.put("barcode", lib.getCallN());
//            hash.put("company", lib.getComp());
//            hash.put("description", lib.getTitle());
//            hash.put("quantity", lib.getQuant());
//            hash.put("price", lib.getPrice());
//            hash.put("cost", lib.getCost());

              writer.println("[Entry]");
              
              
            writer.println("Barcode=" + result[0]);
            writer.println("Comp=" + result[2]);
            writer.println("Description=" + result[1]);
           writer.println("Quantity=" + result[3]);
            writer.println("Price=" + result[4]);
            writer.println("Cost=" + result[5]);
            writer.println("");
                         
            }
   writer.close();
        }
        

        

        return val;
    }

    public static void PrintFile(ArrayList<Refrence> rList, String FilePath) throws FileNotFoundException, UnsupportedEncodingException {
        try (PrintWriter writer = new PrintWriter(FilePath, "UTF-8")) {

            for (int i = 0; i < rList.size(); i++) {
                writer.println("[Entry]");
                {
                    writer.println("BarCode=" + rList.get(i).getCallN());
                    writer.println("Description=" + rList.get(i).getTitle());
                    writer.println("Quantity=" + rList.get(i).getQuant());
                    writer.println("Price=" + rList.get(i).getPrice());
                    writer.println("Cost=" + rList.get(i).getCost());
                    writer.println("Comp=" + rList.get(i).getComp());

                }
                writer.println("");
            }
            writer.close();
        }

    }

}
