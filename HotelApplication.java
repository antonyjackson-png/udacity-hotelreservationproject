package menus;


import api.AdminResource;
import api.HotelResource;
import model.Customer;
import model.IRoom;
import model.Reservation;
import model.RoomType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;

import static java.lang.System.exit;

public class HotelApplication {
    public static void main(String[] args) {
        MainMenu mainMenu = MainMenu.getInstance();
        AdminMenu adminMenu = AdminMenu.getInstance();

        HotelResource hotelResource = HotelResource.getInstance();
        AdminResource adminResource = AdminResource.getInstance();

        Scanner scanner = new Scanner(System.in);
        boolean appRunning = true;

        while (appRunning) {
            HotelApplication.mainMenuChoice(scanner, mainMenu, adminMenu, hotelResource, adminResource);
        }

    }

    public static void mainMenuChoice(Scanner scanner, MainMenu mainMenu, AdminMenu adminMenu,
                                      HotelResource hotelResource, AdminResource adminResource) {
        int mainMenuChoice = mainMenu.mainMenuChoice(scanner);

        switch (mainMenuChoice) {
            case 1: {
                System.out.println("You chose option 1: Find and reserve a room" + "\n");
                scanner.nextLine();
                HotelApplication.findAndReserve(scanner, hotelResource);
                break;
            }
            case 2: {
                System.out.println("You chose option 2: See my reservations" + "\n");
                scanner.nextLine();
                HotelApplication.seeMyReservations(scanner, hotelResource);
                break;
            }
            case 3: {
                System.out.println("You chose option 3: Create an account" + "\n");
                scanner.nextLine();
                HotelApplication.createAnAccount(scanner, hotelResource);
                break;
            }
            case 4: {
                System.out.println("You chose option 4: Admin" + "\n");
                scanner.nextLine();
                HotelApplication.adminMenuChoice(scanner, mainMenu, adminMenu, adminResource, hotelResource);
                break;
            }
            case 5: {
                System.out.println("You chose option 5: Exit" + "\n" + "Have a good day!");
                scanner.close();
                exit(0);
            }
        }
    }

    public static void adminMenuChoice(Scanner scanner, MainMenu mainMenu, AdminMenu adminMenu,
                                       AdminResource adminResource, HotelResource hotelResource) {
        boolean adminMenuRunning = true;
        while (adminMenuRunning) {

            int adminMenuChoice = adminMenu.adminMenuChoice(scanner);

                switch (adminMenuChoice) {

                    case 1: {
                        System.out.println("You chose option 1: See all customers" + "\n");
                        scanner.nextLine();
                        HotelApplication.seeAllCustomers(adminResource);
                        break;
                    }
                    case 2: {
                        System.out.println("You chose option 2: See all rooms" + "\n");
                        scanner.nextLine();
                        HotelApplication.seeAllRooms(adminResource);
                        break;
                    }
                    case 3: {
                        System.out.println("You chose option 3: See all reservations" + "\n");
                        scanner.nextLine();
                        HotelApplication.seeAllReservations(adminResource);
                        break;
                    }
                    case 4: {
                        System.out.println("You chose option 4: Add a room" + "\n");
                        scanner.nextLine();
                        HotelApplication.addARoom(scanner, adminResource);
                        break;
                    }
                    case 5: {
                        System.out.println("You chose option 5: Create Test Data" + "\n");
                        scanner.nextLine();
                        HotelApplication.createTestData(hotelResource, adminResource);
                        break;
                    }
                    case 6: {
                        System.out.println("You chose option 6: Back to Main Menu" + "\n");
                        adminMenuRunning = false;
                    }

                }
        }
    }


    public static void findAndReserve(Scanner scanner, HotelResource hotelResource) {
        Customer customer = null;
        String customerEmail = null;
        String roomNumber = null;
        IRoom room = null;
        Date todayDate = null;
        Date earliestCheckIn = null;
        Date earliestCheckOut = null;
        Date checkInDate = null;
        Date checkOutDate = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        boolean keepRunning = true;
        boolean collectingDates = true;
        boolean collectingRoomNumber = true;
        Calendar calendar = Calendar.getInstance();

        System.out.println("Enter customer email:");
        customerEmail = scanner.nextLine();
        customer = hotelResource.getCustomer(customerEmail);
        if (customer == null) {
            System.out.println("\n" + "No customer exists for this email!");
            System.out.println("Choose option 3 from the Main Menu to create an account" + "\n");
            return;
        }

        collectingDates = true;
        while (collectingDates) {
            todayDate = new Date();
            calendar.setTime(todayDate);
            todayDate = calendar.getTime();
            System.out.println("\n" + "The time now is: " + todayDate);

            earliestCheckIn = new Date();
            earliestCheckOut = new Date();

            if (calendar.get(Calendar.HOUR_OF_DAY) < 20) {
                calendar.set(Calendar.HOUR_OF_DAY, 14);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                earliestCheckIn = calendar.getTime();
            } else {
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                calendar.set(Calendar.DAY_OF_MONTH, day + 1);
                calendar.set(Calendar.HOUR_OF_DAY, 14);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                earliestCheckIn = calendar.getTime();
            }
            System.out.println("Check-in times are between 14:00 and 20:00");
            System.out.println("Therefore, the earliest check-in date is: " +
                    formatter.format(earliestCheckIn));
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            calendar.set(Calendar.DAY_OF_MONTH, day + 1);
            earliestCheckOut = calendar.getTime();
            System.out.println("      and the earliest check-out date is: " +
                    formatter.format(earliestCheckOut) + "\n");

            keepRunning = true;
            while (keepRunning) {
                System.out.println("Enter check-in date (yyyy-mm-dd):");
                String inDate = scanner.nextLine();

                try {
                    checkInDate = formatter.parse(inDate);
                    keepRunning = false;
                } catch (ParseException e) {
                    System.out.println("Invalid format for check-in date" + "\n");
                }
            }

            keepRunning = true;
            while (keepRunning) {
                System.out.println("Enter check-out date (yyyy-mm-dd):");
                String outDate = scanner.nextLine();

                try {
                    checkOutDate = formatter.parse(outDate);
                    keepRunning = false;
                } catch (ParseException e) {
                    System.out.println("Invalid format for check-out date" + "\n");
                }
            }

            calendar.setTime(checkInDate);
            calendar.add(Calendar.HOUR_OF_DAY, 14);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            checkInDate = calendar.getTime();

            calendar.setTime(checkOutDate);
            calendar.add(Calendar.HOUR_OF_DAY, 14);
            checkOutDate = calendar.getTime();

            if (checkInDate.before(earliestCheckIn)) {
                System.out.println("ERROR: check-in date must be on or after " +
                        formatter.format(earliestCheckIn));
                continue;
            } else if (checkOutDate.before(earliestCheckOut)) {
                System.out.println("ERROR: check-out date must be on or after " +
                        formatter.format(earliestCheckOut));
                continue;
            } else if (checkOutDate.equals(checkInDate) || checkOutDate.before(checkInDate)) {
                System.out.println("ERROR: check-out date must be after check in date");
                continue;
            }
            collectingDates = false;
        }

        System.out.println("Available rooms");
        System.out.println("---------------");
        System.out.println("Check-in date: " + formatter.format(checkInDate));
        System.out.println("Check-out date: " + formatter.format(checkOutDate) + "\n");

        Collection<IRoom> availableRooms = hotelResource.findARoom(checkInDate, checkOutDate);

        if (availableRooms.isEmpty()) {
            System.out.println("Sorry, there are no available rooms for these dates.");
            System.out.println("Checking instead for one week later..." + "\n");
            calendar.setTime(checkInDate);
            int day = calendar.get(Calendar.DAY_OF_MONTH) + 7;
            calendar.set(Calendar.DAY_OF_MONTH, day);
            checkInDate = calendar.getTime();
            calendar.setTime(checkOutDate);
            day = calendar.get(Calendar.DAY_OF_MONTH) + 7;
            calendar.set(Calendar.DAY_OF_MONTH, day);
            checkOutDate = calendar.getTime();
            System.out.println("The check-in date for one week later is: " +
                    formatter.format(checkInDate));
            day = calendar.get(Calendar.DAY_OF_MONTH);
            calendar.set(Calendar.DAY_OF_MONTH, day + 1);
            earliestCheckOut = calendar.getTime();
            System.out.println("and the check-out date for one week later is: " +
                    formatter.format(checkOutDate) + "\n");

            availableRooms = hotelResource.findARoom(checkInDate, checkOutDate);
        }

        if (availableRooms.isEmpty()) {
            System.out.println("Sorry, there are no rooms available for these dates, either.");
            System.out.println("Returning to Main Menu." + "\n");
            return;
        }

        for (IRoom availableRoom : availableRooms) {
            System.out.println(availableRoom);
          }
        System.out.println("\n");

        collectingRoomNumber = true;
        while (collectingRoomNumber) {
            System.out.println("Would you like to reserve one of these rooms (y/n)?");

            String answer = scanner.nextLine();
            if (answer.equals("y") || answer.equals("Y")) {
                boolean userConfirm = true;
                while (userConfirm) {
                    System.out.println("\n" + "Please enter one of the room numbers above to book your reservation:");
                    roomNumber = scanner.nextLine();

                    for (IRoom availableRoom : availableRooms) {
                        if (roomNumber.equals(availableRoom.getRoomNumber())) {
                            room = hotelResource.getRoom(roomNumber);
                            hotelResource.bookARoom(customerEmail, room, checkInDate, checkOutDate);
                            System.out.println("Reservation confirmed!" + "\n");
                            System.out.println("Returning to Main Menu" + "\n");
                            return;
                        }
                    }
                    System.out.println("Invalid room number" + "\n");
                }
            } else if (answer.equals("n") || answer.equals("N")) {
                System.out.println("OK, returning to Main Menu" + "\n");
                return;
            } else {
                System.out.println("Invalid entry, please enter y or n" + "\n");
                continue;
            }
        }
    }

    public static void seeMyReservations(Scanner scanner, HotelResource hotelResource) {
        String customerEmail = null;

        System.out.println("Enter customer email:");
        customerEmail = scanner.nextLine();

        Collection<Reservation> reservations = hotelResource.getCustomersReservations(customerEmail);
        if (reservations == null) {
            System.out.println("There are currently no reservations" + "\n");
        } else {
            for (Reservation reservation : reservations) {
                System.out.println(reservation);
                System.out.println("\n");
            }
        }
    }


    public static void createAnAccount(Scanner scanner, HotelResource hotelResource) {
        String firstName = "";
        String lastName = "";
        String email = "";

        boolean first = false;
        while (!first) {
            try {
                System.out.println("Enter first name:");
                firstName = scanner.nextLine();
                first = true;
            } catch (Exception ex) {
                System.out.println("Enter a valid first name");
            }
        }

        boolean last = false;
        while (!last) {
            try {
                System.out.println("Enter last name:");
                lastName = scanner.nextLine();
                 last = true;
            } catch (Exception ex) {
                System.out.println("Enter a valid last name");
            }
        }

        boolean eMail = false;
        while (!eMail) {
            try {
                System.out.println("Enter email:");
                email = scanner.nextLine();
                 eMail = true;
            } catch (Exception ex) {
                System.out.println("Enter a valid email");
            }
        }
        hotelResource.createACustomer(email, firstName, lastName);
        System.out.println("");
    }


    public static void seeAllCustomers(AdminResource adminResource) {
        Collection<Customer> customers = adminResource.getAllCustomers();
        if (customers.isEmpty()) {
            System.out.println("There are currently no customers" + "\n");
        } else {
            System.out.println("CUSTOMERS");
            System.out.println("---------");
            for (Customer customer : customers) {
                System.out.println(customer);
            }
            System.out.println("\n");
        }
    }

    public static void seeAllRooms(AdminResource adminResource) {
        Collection<IRoom> rooms = adminResource.getAllRooms();
        if (rooms.isEmpty()) {
            System.out.println("There are currently no rooms" + "\n");
        } else {
            System.out.println("ROOMS");
            System.out.println("-----");
            for (IRoom room : rooms) {
                System.out.println(room);
            }
            System.out.println("\n");
        }
    }

    public static void seeAllReservations(AdminResource adminResource) {
        adminResource.displayAllReservations();
    }

    public static void addARoom(Scanner scanner, AdminResource adminResource) {
        String roomNumber = null;
        Double price = null;
        RoomType enumeration = null;

        boolean number = false;
        while (!number) {
            try {
                System.out.println("Enter room number:");
                roomNumber = scanner.nextLine();
                number = true;
            } catch (Exception ex) {
                System.out.println("Enter a valid room number" + "\n");
            }
        }

        boolean pr = false;
        while (!pr) {
            try {
                System.out.println("Enter room price:");
                price = scanner.nextDouble();
                if (price < 0.0) {
                    System.out.println("Price cannot be smaller than zero!" + "\n");
                    continue;
                }
                pr = true;
            } catch (Exception ex) {
                System.out.println("Enter a valid room price" + "\n");
                scanner.nextLine();
            }
        }

        boolean type = false;
        while (!type) {
            try {
                System.out.println("Enter room type:");
                System.out.println("1 for SINGLE");
                System.out.println("2 for DOUBLE");
                int temp = scanner.nextInt();
                if (temp == 1) {
                    enumeration = RoomType.SINGLE;
                    System.out.println("");
                    type = true;
                } else if (temp == 2) {
                    enumeration = RoomType.DOUBLE;
                    System.out.println("");
                    type = true;
                } else {
                    System.out.println("Please enter either 1 or 2" + "\n");
                }
            } catch (Exception ex) {
                System.out.println("Enter a valid room type" + "\n");
                scanner.nextLine();
            }
        }
        adminResource.addRoom(roomNumber, price, enumeration);
    }

    public static void createTestData(HotelResource hotelResource, AdminResource adminResource){
        // Create five new customers
        hotelResource.createACustomer("anthony.jones@gmail.com", "Anthony", "Jones");
        hotelResource.createACustomer("brad.smith@gmail.com", "Brad", "Smith");
        hotelResource.createACustomer("camilla.romero@gmail.com", "Camilla", "Romero");
        hotelResource.createACustomer("daniella.lipa@gmail.com", "Daniella", "Lipa");
        hotelResource.createACustomer("emiliano.cantona@gmail.com", "Emiliano", "Cantona");

        //Create twenty new rooms
        adminResource.addRoom("01", 30.0, RoomType.SINGLE);
        adminResource.addRoom("02", 30.0, RoomType.SINGLE);
        adminResource.addRoom("03", 30.0, RoomType.SINGLE);
        adminResource.addRoom("04", 30.0, RoomType.SINGLE);
        adminResource.addRoom("05", 30.0, RoomType.SINGLE);
        adminResource.addRoom("06", 40.0, RoomType.DOUBLE);
        adminResource.addRoom("07", 40.0, RoomType.DOUBLE);
        adminResource.addRoom("08", 40.0, RoomType.DOUBLE);
        adminResource.addRoom("09", 40.0, RoomType.DOUBLE);
        adminResource.addRoom("10", 40.0, RoomType.DOUBLE);
        adminResource.addRoom("11", 50.0, RoomType.SINGLE);
        adminResource.addRoom("12", 50.0, RoomType.SINGLE);
        adminResource.addRoom("13", 50.0, RoomType.SINGLE);
        adminResource.addRoom("14", 50.0, RoomType.SINGLE);
        adminResource.addRoom("15", 50.0, RoomType.SINGLE);
        adminResource.addRoom("16", 60.0, RoomType.DOUBLE);
        adminResource.addRoom("17", 60.0, RoomType.DOUBLE);
        adminResource.addRoom("18", 60.0, RoomType.DOUBLE);
        adminResource.addRoom("19", 60.0, RoomType.DOUBLE);
        adminResource.addRoom("20", 60.0, RoomType.DOUBLE);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date checkInDate = null;
        Date checkOutDate = null;
        IRoom room = null;
        String customerEmail = null;

        // Reserve a room for Anthony Jones
        try {
            checkInDate = formatter.parse("2023-06-01");
        } catch (ParseException e) {}
        try {
            checkOutDate = formatter.parse("2023-06-08");
        } catch (ParseException e) {}
        calendar.setTime(checkInDate);
        calendar.add(Calendar.HOUR_OF_DAY, 14);
        checkInDate = calendar.getTime();
        calendar.setTime(checkOutDate);
        calendar.add(Calendar.HOUR_OF_DAY, 14);
        checkOutDate = calendar.getTime();
        customerEmail = "anthony.jones@gmail.com";
        room = hotelResource.getRoom("01");
        hotelResource.bookARoom(customerEmail, room, checkInDate, checkOutDate);

        // Reserve a room for Brad Smith
        try {
            checkInDate = formatter.parse("2023-06-04");
        } catch (ParseException e) {}
        try {
            checkOutDate = formatter.parse("2023-06-18");
        } catch (ParseException e) {}
        calendar.setTime(checkInDate);
        calendar.add(Calendar.HOUR_OF_DAY, 14);
        checkInDate = calendar.getTime();
        calendar.setTime(checkOutDate);
        calendar.add(Calendar.HOUR_OF_DAY, 14);
        checkOutDate = calendar.getTime();
        customerEmail = "brad.smith@gmail.com";
        room = hotelResource.getRoom("03");
        hotelResource.bookARoom(customerEmail, room, checkInDate, checkOutDate);

        // Reserve a room for Camilla Romero
        try {
            checkInDate = formatter.parse("2023-07-11");
        } catch (ParseException e) {}
        try {
            checkOutDate = formatter.parse("2023-07-15");
        } catch (ParseException e) {}
        calendar.setTime(checkInDate);
        calendar.add(Calendar.HOUR_OF_DAY, 14);
        checkInDate = calendar.getTime();
        calendar.setTime(checkOutDate);
        calendar.add(Calendar.HOUR_OF_DAY, 14);
        checkOutDate = calendar.getTime();
        customerEmail = "camilla.romero@gmail.com";
        room = hotelResource.getRoom("05");
        hotelResource.bookARoom(customerEmail, room, checkInDate, checkOutDate);

        // Reserve two rooms for Daniella Lipa
        try {
            checkInDate = formatter.parse("2023-07-14");
        } catch (ParseException e) {}
        try {
            checkOutDate = formatter.parse("2023-07-21");
        } catch (ParseException e) {}
        calendar.setTime(checkInDate);
        calendar.add(Calendar.HOUR_OF_DAY, 14);
        checkInDate = calendar.getTime();
        calendar.setTime(checkOutDate);
        calendar.add(Calendar.HOUR_OF_DAY, 14);
        checkOutDate = calendar.getTime();
        customerEmail = "daniella.lipa@gmail.com";
        room = hotelResource.getRoom("11");
        hotelResource.bookARoom(customerEmail, room, checkInDate, checkOutDate);

        try {
            checkInDate = formatter.parse("2023-07-14");
        } catch (ParseException e) {}
        try {
            checkOutDate = formatter.parse("2023-07-21");
        } catch (ParseException e) {}
        calendar.setTime(checkInDate);
        calendar.add(Calendar.HOUR_OF_DAY, 14);
        checkInDate = calendar.getTime();
        calendar.setTime(checkOutDate);
        calendar.add(Calendar.HOUR_OF_DAY, 14);
        checkOutDate = calendar.getTime();
        customerEmail = "daniella.lipa@gmail.com";
        room = hotelResource.getRoom("12");
        hotelResource.bookARoom(customerEmail, room, checkInDate, checkOutDate);

        // Reserve a room for Emiliano Cantona
        try {
            checkInDate = formatter.parse("2023-08-01");
        } catch (ParseException e) {}
        try {
            checkOutDate = formatter.parse("2023-08-02");
        } catch (ParseException e) {}
        calendar.setTime(checkInDate);
        calendar.add(Calendar.HOUR_OF_DAY, 14);
        checkInDate = calendar.getTime();
        calendar.setTime(checkOutDate);
        calendar.add(Calendar.HOUR_OF_DAY, 14);
        checkOutDate = calendar.getTime();
        customerEmail = "emiliano.cantona@gmail.com";
        room = hotelResource.getRoom("10");
        hotelResource.bookARoom(customerEmail, room, checkInDate, checkOutDate);
    }
    

}

