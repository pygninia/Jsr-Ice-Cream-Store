/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ice.cream.store;

import java.util.Scanner;

/**
 *
 * @author Billey
 */
public class IceCreamStore {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Welcome to JsR Ice Cream Store");
        Scanner scanner = new Scanner(System.in);

        double totalAmount = 0.0;
        int totalItems = 0;

        // Display menu
        System.out.println("1 Strawberry ----------- P15.10\n"
                + "2 Chocolate ------------ P13.30\n"
                + "3 Cookies and Cream ---- P12.25\n"
                + "4 Ube ------------------ P14.12\n"
                + "5 Mango ---------------- P14.22\n"
                + "6 Vanilla -------------- P12.25\n"
                + "7 Charcoal ------------- P10.15\n"
                + "8 Melon ---------------- P11.13");
        System.out.println("\nIf you buy 5 ice creams you'll have a 15% discount!!!");

        boolean choose = true;
        boolean hasTransaction = false;

        while (choose) {
            System.out.println("\nDo you want to continue shopping? (Yes or No)");
            String response = scanner.nextLine();

            // Validate response
            while (!response.equalsIgnoreCase("Yes") && !response.equalsIgnoreCase("No")) {
                System.out.println("Please respond with 'Yes' or 'No'");
                response = scanner.nextLine();
            }

            if (response.equalsIgnoreCase("No")) {
                if (!hasTransaction) { // Check if no items were purchased
                    double donation = -1;
                    while (donation < 1) {
                        System.out.print("\nInstead of not buying my product, donate any amount for charity: ");
                        donation = scanner.nextDouble();
                        scanner.nextLine(); // Consume newline

                        if (donation > 99) {
                            System.out.printf("The amount we received: P%.2f!\n", donation);
                            System.out.println("\nThank you so much, you have such a good heart!");
                            return;
                        } else if (donation > 0.99) {
                            System.out.printf("\nThank you for your donation of P%.2f!\n", donation);
                            return;
                        } else {
                            System.out.println("Please have a heart, please donate.");
                            donation = -1; // Reset donation to prompt again
                        }
                    }
                }
                break; // Exit the shopping loop
            }

            // Get the item choice
            System.out.print("Enter the number of the item you want to buy (0 for exit): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 0) {
                if (hasTransaction) {
                    System.out.println("\nYou can't leave now, you already have transactions.");
                    continue; // User cannot exit, continue prompting
                } else {
                    System.out.println("\nThank you for visiting and please come again.");
                    break; 
                }
            } else if (choice < 1 || choice > 8) {
                System.out.println("Please choose only 1-8.");
                continue; // Go back to the start of the loop
            }

            System.out.print("How many would you like to buy? ");
            int quantity = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            // Apply choice-based selection
            switch (choice) {
                case 1:
                    System.out.println("You selected " + quantity + " Strawberry for P15.10 each.");
                    totalAmount += 15.10 * quantity;
                    break;
                case 2:
                    System.out.println("You selected " + quantity + " Chocolate for P13.30 each.");
                    totalAmount += 13.30 * quantity;
                    break;
                case 3:
                    System.out.println("You selected " + quantity + " Cookies and Cream for P12.25 each.");
                    totalAmount += 12.25 * quantity;
                    break;
                case 4:
                    System.out.println("You selected " + quantity + " Ube for P14.12 each.");
                    totalAmount += 14.12 * quantity;
                    break;
                case 5:
                    System.out.println("You selected " + quantity + " Mango for P14.22 each.");
                    totalAmount += 14.22 * quantity;
                    break;
                case 6:
                    System.out.println("You selected " + quantity + " Vanilla for P12.25 each.");
                    totalAmount += 12.25 * quantity;
                    break;
                case 7:
                    System.out.println("You selected " + quantity + " Charcoal for P10.15 each.");
                    totalAmount += 10.15 * quantity;
                    break;
                case 8:
                    System.out.println("You selected " + quantity + " Melon for P11.13 each.");
                    totalAmount += 11.13 * quantity;
                    break;
                default:
                    System.out.println("Invalid number.");
                    continue; // Go back to the start of the loop
            }

            totalItems += quantity;
            hasTransaction = true; // Set transaction flag to true

            System.out.printf("\nCurrent Total Amount: P%.2f\n", totalAmount);

            // Apply 15% discount if total items reach 5 or more
            if (totalItems >= 5) {
                double discountedAmount = totalAmount * 0.85;
                System.out.printf("A 15%% discount has been applied for purchasing 5 items! Discounted Amount: P%.2f\n", discountedAmount);
                totalAmount = discountedAmount;
            }
        }

        // Payment process
        if (totalAmount > 0) { // Only proceed if there's an amount to pay
            double payment = -1;
            while (payment < totalAmount) {
                System.out.print("Enter the amount of money you are paying (with cents if needed): ");
                payment = scanner.nextDouble();

                if (payment == totalAmount) {
                    System.out.println("You don't have any change.");
                    System.out.println("\nThank you and come again!");
                    break; // Exit the payment loop
                } else if (payment > totalAmount) {
                    double change = payment - totalAmount;
                    System.out.printf("Your change: P%.2f\n", change);
                    System.out.println("\nThank you and come again!");
                    break; // Exit the payment loop
                } else {
                    System.out.printf("Insufficient amount, please try again. Missing P%.2f\n", totalAmount - payment);
                }
            }
        }
        scanner.close();
    }
}
        