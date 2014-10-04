package GiftShop;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Nathan
 */
public class Book extends Refrence {

    public Refrence bAdd(ArrayList<Refrence> rList) {
        int dup = 0;
        Scanner in = new Scanner(System.in, "UTF-8");
        System.out.print("What is the Call Number?: ");
        Refrence ent = new Book();
        ent.setType("Book");
        input = in.nextLine().trim();
        if ("".equals(input)) {
            while ("".equals(input)) {
                System.out.println("You need to enter a Call Number");
                input = in.nextLine().trim();
            }
        }
        ent.setCallN(input);
        //Duplicate check 1
        for (int i = 0; i < rList.size(); i++) {
            if (rList.get(i).getCallN().equalsIgnoreCase(input)) {
                dup++;
            }
        }
        System.out.print("Who is (are) the Authors?: ");
        input = in.nextLine().trim();
        if (input.equals("")) {
            input = "N/A";
        }
        ent.setAuthor(input);
        System.out.print("What is the title?: ");
        input = in.nextLine().trim();
        if ("".equals(input)) {
            while ("".equals(input)) {
                System.out.println("You need to enter a Title");
                input = in.nextLine().trim();
            }
        }
        ent.setTitle(input);
        System.out.print("Who is the publisher?: ");
        input = in.nextLine().trim();
        if (input.equals("")) {
            input = "N/A";
        }
        ent.setPublish(input);
        System.out.print("What is the year of Publication?: ");
        input = in.nextLine().trim();
        if ("".equals(input)) {
            while ("".equals(input)) {
                System.out.println("You need to enter a Year");
                input = in.nextLine().trim();
            }
        }
        //Duplicate for callNumber and Year
        if (dup >= 2) {
            System.out.println();
            System.out.println("Seems like you have a duplicate! \n");
            //If no problems, add to the arrayList
        } else {
            rList.add(ent);
        }
        return ent;
    }
}
