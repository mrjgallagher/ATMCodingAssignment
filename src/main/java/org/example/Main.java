package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Atm atm = new Atm(3, 5);
        while (true) {
            Scanner sc = new Scanner(System.in);
            displayMainManu();

            int choice = sc.nextInt();
            switch (choice) {
                case 1 -> {
                    System.out.println("");
                    atm.showBalance();
                }
                case 2 -> {
                    System.out.print("Set Machine Balance ");
                    System.out.print("Enter amount of $50 Notes to Insert : ");
                    int fifty = sc.nextInt();
                    System.out.print("Enter amount of $20 Notes to Insert : ");
                    int twenty = sc.nextInt();
                    atm = new Atm(fifty, twenty);
                    System.out.println("");
                    atm.showBalance();
                    System.out.println("Your Money has been successfully Deposited ");
                    System.out.println("");
                }
                case 3 -> {
                    System.out.print("Enter Amount to Withdraw : ");
                    int requestAmount = sc.nextInt();
                    Withdrawl withdraw = atm.withdrawMoney(requestAmount);
                    if (withdraw != null && withdraw.checkNotesMatchRequestAmount()) {
                        System.out.println("");
                        System.out.println("Your Money has been successfully Withdrawn");
                        withdraw.showBalance();
                        atm.showBalance();
                    } else {
                        System.out.println("");
                        System.out.println("Transaction Cannot be Completed.");
                    }
                }
                case 4 -> System.exit(0);
            }
        }
    }

    private static void displayMainManu() {
        System.out.println("");
        System.out.println("--- Automated Teller Machine ---");
        System.out.println("Select 1 : Check Machine Balance");
        System.out.println("Select 2 : Set Machine Balance");
        System.out.println("Select 3 : Withdraw Cash");
        System.out.println("Select 4 : EXIT");
        System.out.print("Select the operation you want to perform:");
    }
}