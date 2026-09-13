import java.util.Scanner;

public class Canteen {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // ----- Canteen Menu -----
        String[] itemNames = {
            "Burger",
            "Pizza",
            "Pasta",
            "Sandwich",
            "Milk Tea"
        };
        double[] itemPrices = {
            80.00,
            120.00,
            100.00,
            70.00,
            90.00
        };

        int totalQuantity = 0;
        double totalBeforeDeductions = 0.0;
        double totalDeduction = 0.0;
        double finalAmountToPay = 0.0;

        System.out.println("=====  M E N U  =====");
        for (int i = 0; i < itemNames.length; i++) {
            System.out.printf("%d. %-10s - $%.2f%n", i + 1, itemNames[i], itemPrices[i]);
        }

        boolean keepOrdering = true;

        while (keepOrdering) {
            System.out.println();
            System.out.print("Enter item number: ");
            int itemNumber = input.nextInt();

            System.out.print("Enter quantity: ");
            int quantity = input.nextInt();

            System.out.print("Are you a student? (Y/N): ");
            String studentAnswer = input.next().trim().toUpperCase();
            boolean isStudent = studentAnswer.equals("Y");

            boolean validItem = (itemNumber >= 1 && itemNumber <= itemNames.length);
            boolean validQuantity = (quantity >= 1 && quantity <= 10);

            if (validItem && validQuantity) {
  
                double itemPrice = itemPrices[itemNumber - 1];
                double orderAmount = itemPrice * quantity;

                double deductionRate;
                if (isStudent && orderAmount >= 500) {
                    deductionRate = 0.15;
                } else if (isStudent) {
                    deductionRate = 0.10;
                } else if (orderAmount >= 500) {
                    deductionRate = 0.05;
                } else {
                    deductionRate = 0.0;
                }

                double deductionAmount = orderAmount * deductionRate;
                double amountAfterDeduction = orderAmount - deductionAmount;

                totalQuantity += quantity;
                totalBeforeDeductions += orderAmount;
                totalDeduction += deductionAmount;
                finalAmountToPay += amountAfterDeduction;

                System.out.println();
                System.out.printf("Subtotal: $%.2f%n", orderAmount);
                System.out.printf("Discount: $%.2f%n", deductionAmount);
                System.out.printf("Order total: $%.2f%n", amountAfterDeduction);
            } else {
                System.out.println();
                System.out.println("Invalid order! Please enter a valid item and quantity.");
            }

            System.out.println();
            System.out.print("Do you want to order again? (Y/N): ");
            String again = input.next().trim().toUpperCase();

            if (!again.equals("Y")) {
                keepOrdering = false;
            }
        }

  
        System.out.println();
        System.out.println("=====  ORDER SUMMARY  =====");
        System.out.println("Total items: " + totalQuantity);
        System.out.printf("Total before discount: $%.2f%n", totalBeforeDeductions);
        System.out.printf("Total discount: $%.2f%n", totalDeduction);
        System.out.printf("Final amount: $%.2f%n", finalAmountToPay);
        System.out.println("Thank you for ordering!");

        input.close();
    }
}
