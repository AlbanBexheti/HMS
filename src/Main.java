import java.time.LocalDate;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ServiceNotFound {
        System.out.println("=".repeat(60));
        System.out.println("ADVANCED HOTEL MANAGEMENT SYSTEM");
        System.out.println("=".repeat(60));

        Hotel hotel = new Hotel("Hotel | Park Plaza Belvedere");
        System.out.println("Hotel created: " + hotel.getHotelName() + "\n");

        System.out.println("ADDING ROOMS");
        hotel.addRoom(new Room(100, "Standard", new BigDecimal("120.00")));
        hotel.addRoom(new Room(101, "Standard", new BigDecimal("120.00")));
        hotel.addRoom(new Room(102, "Deluxe", new BigDecimal("180.00")));
        hotel.addRoom(new Room(103, "Suite", new BigDecimal("350.00")));
        hotel.addRoom(new Room(104, "Suite", new BigDecimal("350.00")));
        System.out.println();

        System.out.println("ADDING SERVICES");
        hotel.addService(new RoomService("RS001", "Breakfast in bed", 25.0, 2));
        hotel.addService(new RoomService("RS002", "Dinner service", 40.0, 3));
        hotel.addService(new SpaTreatment("SP001", "Relaxing massage", 80.0, 90, true));
        hotel.addService(new SpaTreatment("SP002", "Basic facial", 60.0, 60, false));
        hotel.addService(new LaundryService("LS001", "Express laundry", 8.0, 5, true));
        hotel.addService(new LaundryService("LS002", "Regular laundry", 6.0, 8, false));
        System.out.println();

        System.out.println("ADDING STAFF");
        hotel.addStaff(new FrontDeskStaff("FD001", "Jenifer Lawrence", true));
        hotel.addStaff(new FrontDeskStaff("FD002", "Will Smith", false));
        hotel.addStaff(new Housekeepingstaff("HK001", "William Shakespeare", 10));
        hotel.addStaff(new Housekeepingstaff("HK002", "Harry Kane", 8));
        hotel.addStaff(new Manager("MG001", "Alisha Lechmann", "General Manager", 15));
        hotel.addStaff(new Manager("MG002", "Angelina Jolie", "Assistant Manager", 8));
        System.out.println();

        Guest guest1 = new Guest(1, "Alban", "Bexheti", "bexhetialban@gmail.com");
        Guest guest2 = new Guest(2, "Sarah", "Johnson", "sarah.j@email.com");
        Guest guest3 = new Guest(3, "Mike", "Brown", "mike.brown@icloud.com");
        Guest guest4 = new Guest(4, "Emma", "Davis", "emma.davis@email.com");

        System.out.println("BOOKING OPERATIONS");

        try {
            hotel.makeBooking(guest1, 100, LocalDate.of(2025, 8, 1), LocalDate.of(2025, 8, 5));
            hotel.makeBooking(guest2, 101, LocalDate.of(2025, 8, 3), LocalDate.of(2025, 8, 7));

            SpaTreatment spa1 = (SpaTreatment) hotel.findServiceById("SP001");
            spa1.markAsBooked(LocalDate.of(2025, 8, 2), LocalDate.of(2025, 8, 2));
            System.out.println("Spa treatment " + spa1.getServiceId() + " booked successfully!");

            SpaTreatment spa2 = (SpaTreatment) hotel.findServiceById("SP002");
            spa2.markAsBooked(LocalDate.of(2025, 8, 4), LocalDate.of(2025, 8, 4));
            System.out.println("Spa treatment " + spa2.getServiceId() + " booked successfully!");

        } catch (InvalidBookingDatesExceptions | RoomUnavailableException | ServiceNotFound e) {
            System.out.println("Booking error: " + e.getMessage());
        }

        System.out.println("\nTesting Exception Handling");
        try {
            hotel.makeBooking(guest3, 100, LocalDate.of(2025, 8, 3), LocalDate.of(2025, 8, 2)); // Invalid dates
        } catch (InvalidBookingDatesExceptions e) {
            System.out.println("InvalidBookingDatesException caught: " + e.getMessage());
        } catch (RoomUnavailableException e) {
            System.out.println("RoomUnavailableException: " + e.getMessage());
        }

        try {
            hotel.makeBooking(guest4, 100, LocalDate.of(2025, 8, 2), LocalDate.of(2025, 8, 6)); // Room unavailable
        } catch (RoomUnavailableException e) {
            System.out.println("RoomUnavailableException caught: " + e.getMessage());
        } catch (InvalidBookingDatesExceptions e) {
            System.out.println("InvalidBookingDatesException: " + e.getMessage());
        }

        try {
            hotel.findServiceById("INVALID");
        } catch (ServiceNotFound e) {
            System.out.println("ServiceNotFoundException caught: " + e.getMessage());
        }
        System.out.println();

        System.out.println("DISPLAYING ALL INVENTORY");
        hotel.displayAllRooms();
        hotel.displayAllServices();
        hotel.displayAllStaff();

        System.out.println("Staff Performing Duties");
        for (Staff staff : hotel.getAllStaff()) {
            staff.performDuties();
        }
        System.out.println();

        System.out.println("Service Cost Calculations");
        for (HotelService service : hotel.getAllServices()) {
            System.out.println(service.getDescription() + ": $" + String.format("%.2f", service.calculateFinalCost()));
        }
        System.out.println();

        FrontDeskStaff frontDesk = (FrontDeskStaff) hotel.findStaffById("FD001");
        if (frontDesk != null) {
            System.out.println("Front Desk Staff:");
            frontDesk.greet();
            frontDesk.greet("Mr. Johnson");

            System.out.println("\nFront Desk Assistance:");
            frontDesk.assistGuest("Room key issue");
            frontDesk.assistGuest("Mrs. Smith", "Checkout assistance"); // Two parameters
            frontDesk.assistGuest(guest1, "Billing inquiry");
        }

        Housekeepingstaff housekeeper = (Housekeepingstaff) hotel.findStaffById("HK001");
        if (housekeeper != null) {
            System.out.println("\nHousekeeping Staff:");
            housekeeper.cleanRoom(101);
            housekeeper.cleanRoom(hotel.findRoomById(102));
        }

        Manager manager = (Manager) hotel.findStaffById("MG001");
        if (manager != null) {
            System.out.println("\nManager Meeting:");
            manager.conductMeeting();
            manager.conductMeeting("Staff Training");
            manager.conductMeeting("Budget Review", 60);
        }
        System.out.println();


        System.out.println("Financial Processing");
        List<Chargeable> chargeableItems = new ArrayList<>();


        for (HotelService service : hotel.getAllServices()) {
            chargeableItems.add((Chargeable) service);
        }


        Room room102 = hotel.findRoomById(102);
        if (room102 != null) {
            Booking sampleBooking = new Booking(999, room102, guest3,
                    LocalDate.of(2025, 8, 10), LocalDate.of(2025, 8, 15));


            try {
                sampleBooking.addService(hotel.findServiceById("RS001"));
                sampleBooking.addService(hotel.findServiceById("LS001"));
                chargeableItems.add(sampleBooking);
                System.out.println("Sample Booking " + sampleBooking);
            } catch (ServiceNotFound e) {
                System.out.println("Error adding services to booking: " + e.getMessage());
            }
        }

        BigDecimal totalCharges = hotel.calculateTotalCharges(chargeableItems);
        System.out.println("Total charges for all chargeable items: $" + totalCharges);
        System.out.println();


        System.out.println("Bookable Items");
        List<Bookable> bookableItems = new ArrayList<>();


        for (Room room : hotel.getAllRooms()) {
            bookableItems.add(room);
        }


        for (HotelService service : hotel.getAllServices()) {
            if (service instanceof Bookable) {
                bookableItems.add((Bookable) service);
            }
        }

        System.out.println("Found " + bookableItems.size() + " bookable items");
        hotel.processBookableItems(bookableItems,
                LocalDate.of(2025, 8, 2), LocalDate.of(2025, 8, 3));
        System.out.println();


        System.out.println("General Service");
        System.out.println("Processing different service types uniformly:");
        for (HotelService service : hotel.getAllServices()) {
            System.out.println("Service: " + service.getClass().getSimpleName() +
                    " | Description: " + service.getDescription() +
                    " | Cost: $" + String.format("%.2f", service.calculateFinalCost()));
        }
        System.out.println();

        System.out.println("General Staff");
        System.out.println("Processing different staff types uniformly:");
        for (Staff staff : hotel.getAllStaff()) {
            System.out.println("Staff Type: " + staff.getClass().getSimpleName() +
                    " | Name: " + staff.getNamePublic() +
                    " | Department: " + staff.getDepartmentPublic());
            staff.performDuties();
        }
        System.out.println();
        System.out.println();
        System.out.println();

        System.out.println("FINAL SYSTEM STATUS");
        hotel.displayAllBookings();
        System.out.println("Hotel Summary: " + hotel);

        System.out.println("ADVANCED HMS!");
    }
}