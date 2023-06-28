package menus;

import api.AdminResource;
import api.HotelResource;

import java.util.Scanner;

public class MainMenu {

    AdminResource adminResource;
    HotelResource hotelResource;

    public static MainMenu mainMenu = new MainMenu();

    private MainMenu() {
        AdminResource adminResource = AdminResource.getInstance();
        this.adminResource = adminResource;
        HotelResource hotelResource = HotelResource.getInstance();
        this.hotelResource = hotelResource;
    }

    public static MainMenu getInstance() {
        return mainMenu;
    }

    public int mainMenuChoice(Scanner scanner) {
        boolean keepRunning = true;
        int choice = 0;

        while (keepRunning) {
            try {
                System.out.println("MAIN MENU");
                System.out.println("---------");
                System.out.println("Please choose one of the following options:" + "\n");
                System.out.println("1. Find and reserve a room");
                System.out.println("2. See my reservations");
                System.out.println("3. Create an account");
                System.out.println("4. Admin");
                System.out.println("5. Exit");
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
                    default: {
                        System.out.println("ERROR: Enter a number between 1 and 5. Returning to Main Menu." + "\n");
                    }
                }
            } catch (Exception ex) {
                System.out.println("Error, invalid input. Please enter a number between 1 and 5" + "\n");
                scanner.nextLine();
            }
        }
        return choice;

    }

}


