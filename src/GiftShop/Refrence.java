/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GiftShop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Nathan
 */
public class Refrence {

    public String search;
    public String choice;
    public String input;
    public String[] searchParts = new String[2];
    public String found = "";
    public String[] st;
    public String titleSearch[];
    public String comp;
    public char n4;
    public char n;
    public String callNum;
    /**
     * <p>
     * Call number</p>
     */
    public String title;
    /**
     *  * <p>
     * Title.</p>
     */
    public String year;
    /**
     *  * <p>
     * Year</p>
     */
    public String barC;

    public String org;

    /**
     *  * <p>
     * Organization</p>
     *
     */
    public String publish;

    /**
     *  * <p>
     * Publisher</p>
     *
     */
    public String author;

    public String type;
    private String quant;
    private String price;
    private String cost;

    void setType(String str) {
        this.type = str;
    }
       void setComp(String str) {
        this.comp = str;
    }

    public String getType() {
        return this.type;
    }

    public String getPrice() {
        return this.price;
    }
       public String getCost() {
        return this.cost;
    }

    public String getBarC() {
        return this.barC;
    }

    void setYear(String str) {
        if (str == null) {
            str = "";
        }
        this.year = str;
    }
        void setCost(String str) {
        if (str == null) {
            str = "";
        }
        this.cost = str;
    }

    /**
     * Sets the Year for this element
     *
     * @param str
     */
    public void setOrg(String str) {
        if (str == null) {
            str = "";
        }
        this.org = str;
    }

    public void setPrice(String str) {
        if (str == null) {
            str = "";
        }
        this.price = str;
    }

    /**
     * Sets the organization for this element
     *
     * @param str
     */
    public void setTitle(String str) {
        if (str == null) {
            str = "";
        }
        this.title = str;
    }

    /**
     * Sets the title for this element
     *
     * @param str
     */
    public void setCallN(String str) {
        if (str == null) {
            str = "";
        }
        this.callNum = str;
    }

    public void setQuant(String str) {
        if (str == null) {
            str = "";
        }
        this.quant = str;
    }

    /**
     * Gets the current title
     *
     * @return current title
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Gets the current organization
     *
     * @return the current organization
     */
    public String getOrg() {
        return this.org;
    }
       public String getComp() {
        return this.comp;
    }

    /**
     * Gets the current year
     *
     * @return the current Year
     */
    public String getYear() {
        return this.year;
    }

    /**
     * Gets the current call number
     *
     * @return the current Call number
     */
    public String getCallN() {
        return this.callNum;
    }

    public String getQuant() {
        return this.quant;
    }

    /**
     * Prints the arrayList
     *
     * @return prints the arrayList
     */
    void setAuthor(String str) {
        if (str == null) {
            str = "";
        }
        this.author = str;
    }

    /**
     * Sets the author for this element
     *
     * @param str sets the publisher
     */
    public void setPublish(String str) {
        if (str == null) {
            str = "";
        }
        this.publish = str;
    }

    /**
     *
     * @return gets the current publisher
     */
    public String getPub() {
        return this.publish;
    }

    /**
     *
     * @eturn gets the current author
     */
    public String getAuthor() {
        return this.author;
    }

    /**
     *
     * @return Prints out the arrayList
     */

    public String toStringI() {
        //print name and age
        String value;
        
           value = (callNum + ","+comp+"," + title + ","+ quant + "," + price + ","+cost+"\r\n");
           if(Integer.parseInt(quant)==0)
           {
               value="";
           }
        //value = (comp+"," + title + ","+ quant + "," + price + ","+cost);
            
        
        return value;
    }
    @Override
        public String toString() {
        //print name and age
        String value;
        
            value = ("Bar Code: " + callNum + "\n"+"Company: "+comp+"\n"
                    + "Description : " + title + "\n" + "Quantity : " + quant + "\n" + "Price: " + price + "\n"+"Cost: "+cost+"\n\n");
        
        return value;
    }

    public void Search(ArrayList<Refrence> rList, Map lMap) {
        Map kMap = new HashMap();

        Scanner in = new Scanner(System.in, "UTF-8");
        System.out.println("Title: ");
        search = in.nextLine().trim();
        searchParts[0] = search;
        System.out.println("Call Number:");
        search = in.nextLine().trim();
        searchParts[1] = search;
        System.out.println("Year:");
        search = in.nextLine().trim();
        //Split year string to usable info
        if (search == null) {
            search = "";
        }
        st = search.split("\\p{Punct}");

        if (search.length() < 5 && !"".equals(search)) {
            n4 = 'n';
            n = search.charAt(0);

        } else if (!"".equals(search)) {
            n4 = search.charAt(4);
            n = search.charAt(0);
        } else {
            n4 = 'n';
            n = 'n';
        }
        //Set start and end year variables based on string
        String killMe = "NO";
        //Reset the found check
        String patternString;
        Pattern pattern;
        search = "(?i)";
        titleSearch = searchParts[0].split(" ");
        //Start RegEx compare string

        String keyMap[] = searchParts[0].split(" ");
        for (int searchCount = 0; searchCount < titleSearch.length; searchCount++) {
            search = search + ".*(.*\\b" + titleSearch[searchCount] + "\\b.*).*";
        }
        for (int i = 0; i < rList.size(); i++) {
            patternString = (search);
            pattern = Pattern.compile(patternString);
            Matcher matcher = pattern.matcher(rList.get(i).getTitle());
            boolean matches = matcher.matches();

            found = "NULL";
            if (matches == true) {
                found = "Found";
                killMe = "Yes";
            } else if (!searchParts[0].equals("")) {
                found = "NULL";
            }
            if (rList.get(i).getCallN().equalsIgnoreCase(searchParts[1])) {
                found = "Found";
                killMe = "Yes";
            } else if (!searchParts[1].equals("")) {
                found = "NULL";
            }
            //Fix This....
            //If found matches entered keywords, youre solid!
            if ("Found".equals(found)) {
                System.out.println();
                System.out.println(rList.get(i).toString());

            }
        }
        if (killMe.equalsIgnoreCase("NO")) {
            System.out.println("Not Found");
        }

    }
}
