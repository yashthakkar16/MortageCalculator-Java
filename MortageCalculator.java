
import java.text.NumberFormat;
import java.util.Scanner;

public class MortageCalculator {
    final static byte Month_in_year = 12;
    final static byte Percentage = 100;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int princicpal = (int) readNumber("Principal: ", 1000, 2_00_000);
        float annualIntrest = (float) readNumber("Annual Intrest: ", 1, 30);
        int Period = (int) readNumber("Perios (Years): ", 1, 30);

        diplayMortgage(princicpal, annualIntrest, Period);
        displayPaymentsSchedule(princicpal, annualIntrest, Period);
    }

    private static void diplayMortgage(int princicpal, float annualIntrest, int Period) {
        double Mortage = getMortage(princicpal, annualIntrest, Period);
        String mortageFormatted = NumberFormat.getCurrencyInstance().format(Mortage);
        System.out.println();
        System.out.println("MORTAGE");
        System.out.println("------------------");
        System.out.print("Monthly Payments: " + mortageFormatted);
    }

    private static void displayPaymentsSchedule(int princicpal, float annualIntrest, int Period) {
        System.out.println();
        System.out.println("PAYMENTS SCHEDULE");
        System.out.println("-----------------------");
        for (short month = 1; month <= Period * Month_in_year; month++) {

            double payments = getPayment(princicpal, annualIntrest, Period, month);

            System.out.println(NumberFormat.getCurrencyInstance().format(payments));

        }
    }

    public static double readNumber(String prompt, double min, double max) {
        // Scanner scanner = new Scanner(System.in);
        double value;
        while (true) {
            System.out.print(prompt);
            value = scanner.nextDouble();
            if (value >= min && value <= max)
                break;
            System.out.println("Enter the value between" + min + " and " + max);
        }
        return value;
    }

    public static double getPayment(int princicpal, float annualIntrest, int Period, short numberOfPaymentsMade) {

        float monthlyIntrest = annualIntrest / Month_in_year / Percentage;
        int numberOfPayments = Period * Month_in_year;

        double payments = princicpal
                * ((Math.pow(1 + monthlyIntrest, numberOfPayments) - Math.pow(1 + monthlyIntrest, numberOfPaymentsMade))
                        / (Math.pow(1 + monthlyIntrest, numberOfPayments) - 1));

        return payments;
    }

    public static double getMortage(int princicpal, float annualIntrest, int Period) {

        float monthlyIntrest = annualIntrest / Month_in_year / Percentage;
        int numberOfPayments = Period * Month_in_year;

        double Mortage = princicpal * (monthlyIntrest * Math.pow(1 + monthlyIntrest, numberOfPayments))
                / (Math.pow(1 + monthlyIntrest, numberOfPayments) - 1);

        return Mortage;
    }
}
