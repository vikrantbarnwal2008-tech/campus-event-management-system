import java.util.Scanner;

public class Main {
    public static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        FileManager.loadAllData();

        while (true) {
            System.out.println("\n==============================================");
            System.out.println("       CAMPUSCONNECT - EVENT MANAGEMENT");
            System.out.println("==============================================");
            System.out.println("1. Student Portal");
            System.out.println("2. Organizer Portal");
            System.out.println("3. Reports");
            System.out.println("4. Save Data");
            System.out.println("5. Exit");
            System.out.println("----------------------------------------------");

            int choice = Validator.readInt(scanner, "Enter choice: ");

            switch (choice) {
                case 1 -> Student.showMenu(scanner);
                case 2 -> Organizer.showMenu(scanner);
                case 3 -> Report.showMenu(scanner);
                case 4 -> {
                    FileManager.saveAllData();
                    System.out.println("Data saved successfully.");
                }
                case 5 -> {
                    FileManager.saveAllData();
                    System.out.println("Thank you for using CampusConnect.");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Please enter a number from 1 to 5.");
            }
        }
    }
}
