/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ApplicationLayer.Utilities;

import java.util.Scanner;

/**
 *
 * @author tanng
 */
public class DataInput {
    public static int getIntegerNumber(String displayMessage) throws Exception {
        int number = 0;
        String s;
        System.out.print(displayMessage);
        Scanner sc = new Scanner(System.in);
        s = sc.nextLine();
        if (!s.matches("\\d{1,10}")) {
            throw new Exception("Data invalid.");
        } else {
            number = Integer.parseInt(s);
        }
        return number;
    }

    public static int getIntegerNumber() throws Exception {
        int number = 0;
        String s;
        Scanner sc = new Scanner(System.in);
        s = sc.nextLine();
        if (s.matches("\\d{1,10}") == false) {
            throw new Exception("Data invalid.");
        } else {
            number = Integer.parseInt(s);
        }
        return number;
    }

    //--------------------------------------------------
    public static String getString(String displayMessage) {
        String s;
        Scanner sc = new Scanner(System.in);
        System.out.print(displayMessage);
        s = sc.nextLine();
        return s;
    }

    //---------------------------------------------------
    public static String getString() {
        String s;
        Scanner sc = new Scanner(System.in);
        s = sc.nextLine();
        return s;
    }
}
