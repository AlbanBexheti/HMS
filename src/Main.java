import java.io.PrintStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws InvalidBookingDatesExceptions {
        System.out.println("=".repeat(80));
        System.out.println("                    GRAND HOTEL MANAGEMENT SYSTEM");
        System.out.println("                         Premium Edition 2025");
        System.out.println("=".repeat(80));

        Hotel hotel = new Hotel("Grand Palace Resort & Spa");
        System.out.println("Hotel created: " + hotel.getHotelName());
        System.out.println("System initialization date: " + LocalDate.now());
        System.out.println();

        System.out.println("ADDING ROOMS TO HOTEL");
        System.out.println("-".repeat(50));

        // Standard Rooms (Floor 1-2)
        hotel.addRoom(new Room(101, "Standard", new BigDecimal("95.00")));
        hotel.addRoom(new Room(102, "Standard", new BigDecimal("95.00")));
        hotel.addRoom(new Room(103, "Standard", new BigDecimal("95.00")));
        hotel.addRoom(new Room(104, "Standard", new BigDecimal("95.00")));
        hotel.addRoom(new Room(201, "Standard", new BigDecimal("110.00")));
        hotel.addRoom(new Room(202, "Standard", new BigDecimal("110.00")));
        hotel.addRoom(new Room(203, "Standard", new BigDecimal("110.00")));
        hotel.addRoom(new Room(204, "Standard", new BigDecimal("110.00")));

        // Deluxe Rooms (Floor 3-4)
        hotel.addRoom(new Room(301, "Deluxe", new BigDecimal("175.00")));
        hotel.addRoom(new Room(302, "Deluxe", new BigDecimal("175.00")));
        hotel.addRoom(new Room(303, "Deluxe", new BigDecimal("180.00")));
        hotel.addRoom(new Room(304, "Deluxe", new BigDecimal("180.00")));
        hotel.addRoom(new Room(401, "Deluxe", new BigDecimal("190.00")));
        hotel.addRoom(new Room(402, "Deluxe", new BigDecimal("190.00")));

        // Premium Suites (Floor 5)
        hotel.addRoom(new Room(501, "Suite", new BigDecimal("350.00")));
        hotel.addRoom(new Room(502, "Suite", new BigDecimal("375.00")));
        hotel.addRoom(new Room(503, "Suite", new BigDecimal("400.00")));
        hotel.addRoom(new Room(504, "Suite", new BigDecimal("450.00")));

        // Presidential Suite (Penthouse)
        hotel.addRoom(new Room(601, "Suite", new BigDecimal("750.00")));

        System.out.println(" Total rooms added: " + hotel.getAllRooms().size());
        System.out.println();

        System.out.println("ADDING COMPREHENSIVE SERVICES");
        System.out.println("-".repeat(50));

        System.out.println(" ");
        hotel.addService(new RoomService("RS001", "Continental Breakfast", 18.0, 1));
        hotel.addService(new RoomService("RS002", "Full American Breakfast", 28.0, 1));
        hotel.addService(new RoomService("RS003", "Lunch Menu", 35.0, 1));
        hotel.addService(new RoomService("RS004", "Gourmet Dinner", 55.0, 1));
        hotel.addService(new RoomService("RS005", "Late Night Snacks", 22.0, 1));
        hotel.addService(new RoomService("RS006", "Champagne & Strawberries", 65.0, 1));

        System.out.println(" ");
        hotel.addService(new SpaTreatment("SP001", "Swedish Massage", 85.0, 60, false));
        hotel.addService(new SpaTreatment("SP002", "Deep Tissue Massage", 95.0, 75, true));
        hotel.addService(new SpaTreatment("SP003", "Hot Stone Therapy", 110.0, 90, true));
        hotel.addService(new SpaTreatment("SP004", "Couples Massage", 180.0, 90, true));
        hotel.addService(new SpaTreatment("SP005", "Facial Treatment", 65.0, 45, false));
        hotel.addService(new SpaTreatment("SP006", "Body Wrap Treatment", 75.0, 60, false));
        hotel.addService(new SpaTreatment("SP007", "Manicure & Pedicure", 50.0, 75, false));

        System.out.println(" ");
        hotel.addService(new LaundryService("LS001", "Same Day Laundry", 12.0, 5, true));
        hotel.addService(new LaundryService("LS002", "Express Dry Cleaning", 15.0, 3, true));
        hotel.addService(new LaundryService("LS003", "Regular Laundry", 8.0, 10, false));
        hotel.addService(new LaundryService("LS004", "Shoe Cleaning", 10.0, 2, false));

        System.out.println("Total services added: " + hotel.getAllServices().size());
        System.out.println();

        System.out.println("HIRING PROFESSIONAL STAFF");
        System.out.println("-".repeat(50));


        hotel.addStaff(new FrontDeskStaff("FD001", "Emma Thompson", true));
        hotel.addStaff(new FrontDeskStaff("FD002", "James Rodriguez", true));
        hotel.addStaff(new FrontDeskStaff("FD003", "Sofia Chen", false));
        hotel.addStaff(new FrontDeskStaff("FD004", "Michael Johnson", true));
        hotel.addStaff(new FrontDeskStaff("FD005", "Isabella Garcia", false));


        hotel.addStaff(new HouseKeepingStaff("HK001", "Maria Santos", 8));
        hotel.addStaff(new HouseKeepingStaff("HK002", "David Kim", 10));
        hotel.addStaff(new HouseKeepingStaff("HK003", "Anna Petrov", 7));
        hotel.addStaff(new HouseKeepingStaff("HK004", "Carlos Martinez", 9));
        hotel.addStaff(new HouseKeepingStaff("HK005", "Lisa Wang", 6));
        hotel.addStaff(new HouseKeepingStaff("HK006", "Ahmed Hassan", 8));


        hotel.addStaff(new Manager("MG001", "Robert Sterling", "General Manager", 20));
        hotel.addStaff(new Manager("MG002", "Victoria Adams", "Assistant Manager", 12));
        hotel.addStaff(new Manager("MG003", "Thomas Anderson", "Operations Manager", 15));
        hotel.addStaff(new Manager("MG004", "Sarah Williams", "Guest Relations Manager", 8));

        System.out.println("Total staff hired: " + hotel.getAllStaff().size());
        System.out.println();


        Guest guest1 = new Guest(1, "Alexander", "Morrison", "a.morrison@techcorp.com");
        Guest guest2 = new Guest(2, "Jennifer", "Walsh", "j.walsh@consulting.com");
        Guest guest3 = new Guest(3, "Hiroshi", "Tanaka", "h.tanaka@tokyo-industries.jp");


        Guest guest4 = new Guest(4, "Marco", "Rossi", "marco.rossi@email.it");
        Guest guest5 = new Guest(5, "Sophie", "Dubois", "sophie.dubois@gmail.com");
        Guest guest6 = new Guest(6, "Elena", "Kowalski", "e.kowalski@outlook.com");


        Guest guest7 = new Guest(7, "William", "Thompson", "w.thompson@family.com");
        Guest guest8 = new Guest(8, "Isabella", "Rodriguez", "bella.rodriguez@yahoo.com");
        Guest guest9 = new Guest(9, "Oliver", "Schmidt", "o.schmidt@deutschland.de");


        Guest guest10 = new Guest(10, "Catherine", "Blackwood", "c.blackwood@luxury.com");
        Guest guest11 = new Guest(11, "Sebastian", "Van Der Berg", "s.vandeberg@diamond.nl");
        Guest guest12 = new Guest(12, "Anastasia", "Volkov", "a.volkov@platinum.ru");


        Guest guest13 = new Guest(13, "Zara", "Ahmed", "zara.ahmed@startup.com");
        Guest guest14 = new Guest(14, "Lucas", "Silva", "lucas.silva@digital.br");
        Guest guest15 = new Guest(15, "Priya", "Sharma", "priya.sharma@tech.in");


        System.out.println();

        System.out.println("PROCESSING HOTEL RESERVATIONS");
        System.out.println("-".repeat(50));

        try {

            hotel.makeBooking(guest1, 501, LocalDate.of(2025, 8, 1), LocalDate.of(2025, 8, 4));
            hotel.makeBooking(guest2, 302, LocalDate.of(2025, 8, 1), LocalDate.of(2025, 8, 3));
            hotel.makeBooking(guest3, 401, LocalDate.of(2025, 8, 2), LocalDate.of(2025, 8, 5));


            hotel.makeBooking(guest4, 503, LocalDate.of(2025, 8, 7), LocalDate.of(2025, 8, 12));
            hotel.makeBooking(guest5, 301, LocalDate.of(2025, 8, 8), LocalDate.of(2025, 8, 11));
            hotel.makeBooking(guest6, 201, LocalDate.of(2025, 8, 9), LocalDate.of(2025, 8, 13));
            hotel.makeBooking(guest7, 502, LocalDate.of(2025, 8, 10), LocalDate.of(2025, 8, 15));


            hotel.makeBooking(guest8, 303, LocalDate.of(2025, 8, 15), LocalDate.of(2025, 8, 18));
            hotel.makeBooking(guest9, 402, LocalDate.of(2025, 8, 16), LocalDate.of(2025, 8, 20));
            hotel.makeBooking(guest10, 601, LocalDate.of(2025, 8, 18), LocalDate.of(2025, 8, 22));


            hotel.makeBooking(guest11, 504, LocalDate.of(2025, 8, 22), LocalDate.of(2025, 8, 28));
            hotel.makeBooking(guest12, 304, LocalDate.of(2025, 8, 23), LocalDate.of(2025, 8, 26));


            hotel.makeBooking(guest1, 202, LocalDate.of(2025, 9, 1), LocalDate.of(2025, 9, 4));
            hotel.makeBooking(guest13, 102, LocalDate.of(2025, 9, 5), LocalDate.of(2025, 9, 8));
            hotel.makeBooking(guest14, 103, LocalDate.of(2025, 9, 10), LocalDate.of(2025, 9, 12));
            hotel.makeBooking(guest15, 203, LocalDate.of(2025, 9, 15), LocalDate.of(2025, 9, 18));

            System.out.println("All reservations processed successfully!");


            SpaTreatment massage1 = (SpaTreatment) hotel.findServiceById("SP001");
            massage1.markAsBooked(LocalDate.of(2025, 8, 2), LocalDate.of(2025, 8, 2));

            SpaTreatment couples = (SpaTreatment) hotel.findServiceById("SP004");
            couples.markAsBooked(LocalDate.of(2025, 8, 8), LocalDate.of(2025, 8, 8));

            SpaTreatment hotstone = (SpaTreatment) hotel.findServiceById("SP003");
            hotstone.markAsBooked(LocalDate.of(2025, 8, 19), LocalDate.of(2025, 8, 19));

            System.out.println("Spa treatments scheduled successfully!");

        } catch (RoomUnavailableException | ServiceNotFound | InvalidBookingDatesExceptions e) {
            System.out.println("Booking error: " + e.getMessage());
        }

        System.out.println();


        try {
            hotel.makeBooking(guest13, 501, LocalDate.of(2025, 8, 3), LocalDate.of(2025, 8, 2));
        } catch (InvalidBookingDatesExceptions | RoomUnavailableException e) {
            System.out.println("Date validation working: " + e.getMessage());
        }

        try {
            hotel.makeBooking(guest14, 601, LocalDate.of(2025, 8, 20), LocalDate.of(2025, 8, 23));
        } catch (RoomUnavailableException e) {
            System.out.println("Room availability checking: " + e.getMessage());
        }

        try {
            hotel.findServiceById("INVALID_SERVICE");
        } catch (ServiceNotFound e) {
            System.out.println("Service validation working: " + e.getMessage());
        }

        System.out.println();
        System.out.println("STAFF OPERATIONS DEMONSTRATION");
        System.out.println("-".repeat(50));


        System.out.println("Daily Staff Performance:");
        for (Staff staff : hotel.getAllStaff()) {
            staff.performDuties();
            staff.incrementTasksCompleted();
        }


        System.out.println("\nGuest Services in Action:");
        FrontDeskStaff frontDesk = (FrontDeskStaff) hotel.findStaffById("FD001");
        if (frontDesk != null) {
            frontDesk.greet();
            frontDesk.assistGuest(guest10, "VIP check-in assistance");
            frontDesk.assistGuest("Mr. Tanaka", "Business center access");

            String[] complaints = {"wifi", "cleanliness", "noise", "temperature"};
            frontDesk.handleComplaints(complaints);
        }


        System.out.println("\nHousekeeping Services:");
        HouseKeepingStaff housekeeper = (HouseKeepingStaff) hotel.findStaffById("HK001");
        if (housekeeper != null) {
            housekeeper.cleanRoom(601);
            housekeeper.cleanRoom(hotel.findRoomById(501));

            char[] roomStatus = {'C', 'D', 'C', 'D', 'D', 'C', 'D'};
            int dirtyRooms = housekeeper.countRoomsNeedingCleaning(roomStatus);
            System.out.println("Rooms needing cleaning today: " + dirtyRooms);
        }


        System.out.println("\nExecutive Management:");
        Manager manager = (Manager) hotel.findStaffById("MG001");
        if (manager != null) {
            manager.conductMeeting("Weekly Revenue Review");
            manager.conductMeeting("Staff Performance Evaluation", 90);
            manager.conductMeeting("Guest Satisfaction Analysis", 45);
        }

        System.out.println();

        System.out.println("Available Rooms Analysis:");
        List<Room> availableRooms = hotel.getAllAvailableRooms();
        System.out.println("Total available rooms: " + availableRooms.size());
        for (Room room : availableRooms) {
            System.out.println("  " + room);
        }

        System.out.println("\nPremium Room Categories:");
        String[] roomTypes = {"Standard", "Deluxe", "Suite"};
        for (String type : roomTypes) {
            List<Room> typeRooms = hotel.getRoomsByType(type);
            System.out.println(type + " rooms (" + typeRooms.size() + " total):");
            for (Room room : typeRooms) {
                System.out.println("  Room " + room.getRoomId() + " - $" + room.getNightlyRate());
            }
        }

        System.out.println("\nGuest Registry:");
        System.out.println("Total registered guests: " + hotel.getTotalNumberOfGuests());
        List<String> guestNames = hotel.getAllGuestNames();
        System.out.println("Guest directory:");
        for (String name : guestNames) {
            System.out.println("  ✓ " + name);
        }

        System.out.println("\nBooking Lookup Service:");
        for (int i = 1; i <= 5; i++) {
            Booking booking = hotel.getBookingById(String.valueOf(i));
            if (booking != null) {
                System.out.println("Booking #" + i + ": " + booking.getGuest().getFullName() +
                        " in Room " + booking.getRoom().getRoomId());
            }
        }
        System.out.println(" ");

        System.out.println("Financial Analytics:");
        System.out.printf("Total hotel revenue: $%.2f%n", hotel.calculateTotalRevenue());
        System.out.printf("Average booking value: $%.2f%n",
                hotel.calculateTotalRevenue() / hotel.getTotalNumberOfGuests());

        System.out.println("\nPremium Room Filter (Above $200):");
        List<Room> premiumRooms = Room.getRoomsAboveRate(hotel.getAllRooms(), 200.0);
        System.out.println("Premium rooms count: " + premiumRooms.size());
        for (Room room : premiumRooms) {
            System.out.println("  " + room);
        }

        System.out.println("\nGuest Booking Distribution:");
        Map<Guest, List<Booking>> guestBookings = hotel.getBookingsByGuest();
        for (Guest guest : guestBookings.keySet()) {
            List<Booking> bookings = guestBookings.get(guest);
            System.out.println(guest.getFullName() + ": " + bookings.size() + " booking(s)");
        }

        System.out.println(" ");
        System.out.println("Market Analysis:");
        String mostPopular = hotel.getMostFrequentRoomTypeBooked();
        System.out.println("Most popular room type: " + mostPopular);

        System.out.println("\nVIP Guest Identification:");
        Set<Guest> vipGuests = hotel.getGuestsWithMultipleBookings();
        System.out.println("Guests with multiple bookings (VIP status):");
        for (Guest guest : vipGuests) {
            System.out.println(guest.getFullName());
        }

        System.out.println("\nInventory Management:");
        List<Room> unbookedRooms = hotel.getRoomsWithNoBookings();
        System.out.println("Rooms with no current bookings: " + unbookedRooms.size());
        for (Room room : unbookedRooms) {
            System.out.println("  Available: " + room);
        }

        System.out.println("\nStaff Performance Tracking:");
        Map<Staff, Integer> staffTasks = hotel.getStaffTaskCounts();
        System.out.println("Staff productivity report:");
        for (Staff staff : staffTasks.keySet()) {
            System.out.println("  " + staff.getNamePublic() + " (" + staff.getDepartmentPublic() +
                    "): " + staffTasks.get(staff) + " tasks completed");
        }

        System.out.println("");

        System.out.println("🎟Service Pricing Demonstration:");
        try {
            HotelService breakfast = hotel.findServiceById("RS001");
            System.out.println("Original breakfast price: $" + breakfast.calculateFinalCost());
            breakfast.applyDiscount("SUMMER20");
            System.out.println("After summer discount: $" + breakfast.calculateFinalCost());

            HotelService massage = hotel.findServiceById("SP002");
            System.out.println("Original massage price: $" + massage.calculateFinalCost());
            massage.applyDiscount("VIP10");
            System.out.println("After VIP discount: $" + massage.calculateFinalCost());

        } catch (ServiceNotFound e) {
            System.out.println("Service pricing test failed: " + e.getMessage());
        }

        System.out.println("\nService Portfolio Analysis:");
        System.out.println("Complete service catalog:");
        for (HotelService service : hotel.getAllServices()) {
            System.out.printf("  %-25s | %-20s | $%.2f%n",
                    service.getDescription(),
                    service.getClass().getSimpleName(),
                    service.calculateFinalCost());
        }

        System.out.println("\nADVANCED FEATURES DEMONSTRATION");
        System.out.println("-".repeat(50));


        System.out.println("Personalized Guest Communications:");
        char[] template1 = {'H', 'e', 'l', 'l', 'o', ' ', '*', '.', ' ', '#', '!', ' ', '@'};
        System.out.println("Template result for " + guest10.getFullName() + ": " +
                guest10.buildGreetingMessage(template1));


        System.out.println("\nRoom Occupancy Analytics:");
        Room testRoom = hotel.findRoomById(301);
        if (testRoom != null) {
            boolean[] weeklyOccupancy = {true, true, false, false, false, true, true};
            boolean wasVacant = testRoom.wasVacantForThreeConsecutiveDays(weeklyOccupancy);
            System.out.println("Room 301 had 3+ consecutive vacant days: " + wasVacant);
        }


        System.out.println("\n Staff Task Management:");
        Staff testStaff = hotel.findStaffById("FD001");
        if (testStaff != null) {
            int[] taskPriorities = {3, 1, 5, 2, 4};
            int urgentTask = testStaff.findHighestPriorityTask(taskPriorities);
            System.out.println("Highest priority task index: " + urgentTask);
        }

        System.out.println("\n FINAL SYSTEM STATUS REPORT");
        System.out.println("=".repeat(70));


        System.out.println("Hotel Overview:");
        System.out.println("  • Hotel Name: " + hotel.getHotelName());
        System.out.println("  • Total Rooms: " + hotel.getAllRooms().size());
        System.out.println("  • Total Staff: " + hotel.getAllStaff().size());
        System.out.println("  • Total Services: " + hotel.getAllServices().size());
        System.out.println("  • Registered Guests: " + hotel.getTotalNumberOfGuests());
        System.out.printf("  • Total Revenue: $%.2f%n", hotel.calculateTotalRevenue());
        System.out.println("  • Available Rooms: " + hotel.getAllAvailableRooms().size());

        System.out.println("\nOccupancy Statistics:");
        int totalRooms = hotel.getAllRooms().size();
        int bookedRooms = totalRooms - hotel.getAllAvailableRooms().size();
        double occupancyRate = (double) bookedRooms / totalRooms * 100;
        System.out.printf("  • Occupancy Rate: %.1f%% (%d/%d rooms)%n",
                occupancyRate, bookedRooms, totalRooms);


        System.out.println("\n" + "".repeat(35));
        System.out.println("GRAND HOTEL MANAGEMENT SYSTEM - FULLY OPERATIONAL! ");
    }
}