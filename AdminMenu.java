package menus;

import api.AdminResource;
import api.HotelResource;

import java.util.Scanner;

public class AdminMenu {

    AdminResource adminResource;
    HotelResource hotelResource;

    public static AdminMenu adminMenu = new AdminMenu();

    private AdminMenu() {
        AdminResource adminResource = AdminResource.getInstance();
        this.adminResource = adminResource;
        HotelResource hotelResource = HotelResource.getInstance();
        this.hotelResource = hotelResource;
    }

    public static AdminMenu getInstance() {
        return adminMenu;
    }

    public int adminMenuChoice(Scanner scanner) {
        boolean keepRunning = true;
        int choice = 0;

        while (keepRunning) {
            try {
                System.out.println("ADMIN MENU");
                System.out.println("----------");
                System.out.println("Please choose one of the following options:");
                System.out.println("\n" + "1. See all Customers");
                System.out.println("2. See all Rooms");
                System.out.println("3. See all Reservations");
                System.out.println("4. Add a Room");
                System.out.println("5. Create Test Data");
                System.out.println("6. Back to Main Menu");
                choice = scanner.nextInt();

                switch(choice) {
                    case 1: {
                        keepRunning = false;
                    }
                    case 2: {
                        keepRunning = false;
                    }
                    case 3: {
                        keepRunning = false;
                    }
                    case 4: {
                        keepRunning = false;
                    }
                    case 5: {
                        keepRunning = false;
                    }
                    case 6: {
                        keepRunning = false;
                    }
                    default: {
                        System.out.println("ERROR: Enter a number between 1 and 6. Returning to Admin Menu." + "\n");
                    }
                }
            } catch (Exception ex) {
                System.out.println("\n" + "Error, invalid input. Please enter a number between 1 and 6" + "\n");
                scanner.nextLine();
            }
        }
        return choice;
    }

}


