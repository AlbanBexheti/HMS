import java.io.PrintStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public class Hotel {
    private final String hotelName;
    private List<Room> rooms;
    private ArrayList<Booking> bookings;
    private ArrayList<HotelService> services;
    private ArrayList<Staff> staff;
    private int nextBookingId;
    private Set<Guest> registeredGuests;
    private Map<String, Booking> bookingsMap;
    private BookingManager bookingManager;

    public Hotel(String hotelName) throws InvalidBookingDatesExceptions {
        this.hotelName = hotelName;
        this.rooms = new ArrayList<>();
        this.bookings = new ArrayList<>();
        this.services = new ArrayList<>();
        this.staff = new ArrayList<>();
        this.nextBookingId = 1;
        this.registeredGuests = new HashSet<>();
        this.bookingsMap = new HashMap<>();
        this.bookingManager = new BookingManager();
    }

    public void addService(HotelService service) {
        this.services.add(service);
        System.out.println("Service " + service.getServiceId() + " (" + service.getDescription() + ") added to " + this.hotelName);
    }

    public HotelService findServiceById(String serviceId) throws ServiceNotFound {
        for(HotelService service : this.services) {
            if (service.getServiceId().equals(serviceId)) {
                return service;
            }
        }
        throw new ServiceNotFound("Service with ID " + serviceId + " not found");
    }

    public void displayAllServices() {
        System.out.println("\nALL SERVICES IN " + this.hotelName.toUpperCase());
        if (this.services.isEmpty()) {
            System.out.println("No services available");
        } else {
            for(HotelService service : this.services) {
                System.out.println(service.toString());
            }
        }
        System.out.println();
    }

    public ArrayList<HotelService> getAllServices() {
        return new ArrayList<>(this.services);
    }

    public void addStaff(Staff staffMember) {
        this.staff.add(staffMember);
        System.out.println("Staff member " + staffMember.getNamePublic() + " (ID: " + staffMember.getStaffIdPublic() + ") added to " + this.hotelName);
    }

    public Staff findStaffById(String staffId) {
        for(Staff staffMember : this.staff) {
            if (staffMember.getStaffIdPublic().equals(staffId)) {
                return staffMember;
            }
        }
        return null;
    }

    public void displayAllStaff() {
        System.out.println("\nALL STAFF IN " + this.hotelName.toUpperCase());
        if (this.staff.isEmpty()) {
            System.out.println("No staff members found");
        } else {
            for(Staff staffMember : this.staff) {
                System.out.println(staffMember.toString());
            }
        }
        System.out.println();
    }

    public ArrayList<Staff> getAllStaff() {
        return new ArrayList<>(this.staff);
    }

    public Map<Staff, Integer> getStaffTaskCounts() {
        Map<Staff, Integer> taskCounts = new HashMap<>();
        for(Staff staff : this.staff) {
            taskCounts.put(staff, staff.getTasksCompleted());
        }
        return taskCounts;
    }

    public BigDecimal calculateTotalCharges(List<Chargeable> chargeableItems) {
        BigDecimal total = BigDecimal.ZERO;
        for(Chargeable item : chargeableItems) {
            total = total.add(item.getCost());
        }
        return total;
    }

    public void processBookableItems(List<Bookable> bookableItems, LocalDate startDate, LocalDate endDate) {
        System.out.println("Processing bookable items from " + startDate + " to " + endDate + ":");
        int itemNumber = 1;

        for(Bookable item : bookableItems) {
            String itemDescription = this.getBookableItemDescription(item);
            if (item.isAvailable(startDate, endDate)) {
                System.out.println("  " + itemNumber + ". " + itemDescription + " - AVAILABLE");
            } else {
                System.out.println("  " + itemNumber + ". " + itemDescription + " - NOT AVAILABLE");
            }
            itemNumber++;
        }
    }

    private String getBookableItemDescription(Bookable item) {
        if (item instanceof Room) {
            Room room = (Room) item;
            return "Room " + room.getRoomId() + " (" + room.getRoomType() + ")";
        } else if (item instanceof SpaTreatment) {
            SpaTreatment spa = (SpaTreatment) item;
            return "Spa Treatment " + spa.getServiceId() + " (" + spa.getDescription() + ")";
        } else {
            return "Unknown bookable item";
        }
    }

    public void addRoom(Room room) {
        this.rooms.add(room);
        this.bookingManager.addRoom(room);
        System.out.println("Room " + room.getRoomId() + " added to " + this.hotelName);
    }

    public Room findRoomById(int roomId) {
        for(Room room : this.rooms) {
            if (room.getRoomId() == roomId) {
                return room;
            }
        }
        return null;
    }

    public Room findRoomByNumber(String roomNumber) {
        try {
            int roomId = Integer.parseInt(roomNumber);
            return this.findRoomById(roomId);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private boolean isRoomAvailableForDates(Room room, LocalDate checkInDate, LocalDate checkOutDate) {
        for(Booking booking : this.bookings) {
            if (booking.getRoom().equals(room) &&
                    !checkOutDate.isBefore(booking.getCheckInDate()) &&
                    !checkInDate.isAfter(booking.getCheckOutDate()) &&
                    !checkInDate.isEqual(booking.getCheckOutDate()) &&
                    !checkOutDate.isEqual(booking.getCheckInDate())) {
                return false;
            }
        }
        return true;
    }

    public List<Room> getAllAvailableRooms() {
        List<Room> availableRooms = new ArrayList<>();
        for(Room room : this.rooms) {
            if (room.isAvailable()) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    public List<Room> getRoomsByType(String roomType) {
        List<Room> filteredRooms = new ArrayList<>();
        for(Room room : this.rooms) {
            if (room.getRoomType().equalsIgnoreCase(roomType)) {
                filteredRooms.add(room);
            }
        }
        return filteredRooms;
    }

    public ArrayList<Room> getAvailableRooms(LocalDate checkInDate, LocalDate checkOutDate) {
        ArrayList<Room> availableRooms = new ArrayList<>();
        for(Room room : this.rooms) {
            if (this.isRoomAvailableForDates(room, checkInDate, checkOutDate)) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    public ArrayList<Room> getAllRooms() {
        return new ArrayList<>(this.rooms);
    }

    public boolean registerGuest(Guest guest) {
        return this.registeredGuests.add(guest);
    }

    public int getTotalNumberOfGuests() {
        return this.registeredGuests.size();
    }

    public List<String> getAllGuestNames() {
        List<String> names = new ArrayList<>();
        for(Guest guest : this.registeredGuests) {
            names.add(guest.getFullName());
        }
        return names;
    }

    public boolean makeBooking(Guest guest, int roomId, LocalDate checkInDate, LocalDate checkOutDate) throws InvalidBookingDatesExceptions, RoomUnavailableException {
        if (!checkOutDate.isAfter(checkInDate)) {
            throw new InvalidBookingDatesExceptions("Check-out date must be after check-in date");
        }

        Room room = this.findRoomById(roomId);
        if (room == null) {
            throw new RoomUnavailableException("Room " + roomId + " not found");
        }

        if (!this.isRoomAvailableForDates(room, checkInDate, checkOutDate)) {
            throw new RoomUnavailableException("Room " + roomId + " is not available for the requested dates");
        }

        this.registerGuest(guest);
        Booking newBooking = new Booking(this.nextBookingId++, room, guest, checkInDate, checkOutDate);
        this.bookings.add(newBooking);
        this.bookingsMap.put(String.valueOf(newBooking.getBookingId()), newBooking);
        this.bookingManager.addBooking(newBooking);

        System.out.println("Booking successful! Booking ID: " + newBooking.getBookingId() + " | Total Cost: $" + newBooking.calculateTotalCost());
        return true;
    }

    public boolean makeBooking(Guest guest, String roomNumber, LocalDate checkInDate, LocalDate checkOutDate) throws InvalidBookingDatesExceptions, RoomUnavailableException {
        try {
            int roomId = Integer.parseInt(roomNumber);
            return this.makeBooking(guest, roomId, checkInDate, checkOutDate);
        } catch (NumberFormatException e) {
            throw new RoomUnavailableException("Invalid room number: " + roomNumber);
        }
    }

    public Booking getBookingById(String bookingId) {
        return this.bookingsMap.get(bookingId);
    }

    public double calculateTotalRevenue() {
        double total = 0.0;
        for(Booking booking : this.bookings) {
            total += booking.calculateTotalCost().doubleValue();
        }
        return total;
    }

    public Map<Guest, List<Booking>> getBookingsByGuest() {
        Map<Guest, List<Booking>> guestBookings = new HashMap<>();
        for(Booking booking : this.bookings) {
            Guest guest = booking.getGuest();
            if (!guestBookings.containsKey(guest)) {
                guestBookings.put(guest, new ArrayList<>());
            }
            guestBookings.get(guest).add(booking);
        }
        return guestBookings;
    }

    public String getMostFrequentRoomTypeBooked() {
        Map<String, Integer> typeCounts = new HashMap<>();
        for(Booking booking : this.bookings) {
            String roomType = booking.getRoom().getRoomType();
            if (typeCounts.containsKey(roomType)) {
                typeCounts.put(roomType, typeCounts.get(roomType) + 1);
            } else {
                typeCounts.put(roomType, 1);
            }
        }

        String mostFrequent = null;
        int maxCount = 0;
        for(String roomType : typeCounts.keySet()) {
            if (typeCounts.get(roomType) > maxCount) {
                mostFrequent = roomType;
                maxCount = typeCounts.get(roomType);
            }
        }
        return mostFrequent;
    }

    public Set<Guest> getGuestsWithMultipleBookings() {
        Set<Guest> guests = new HashSet<>();
        Map<Guest, List<Booking>> guestBookings = this.getBookingsByGuest();
        for(Guest guest : guestBookings.keySet()) {
            if (guestBookings.get(guest).size() > 1) {
                guests.add(guest);
            }
        }
        return guests;
    }

    public List<Room> getRoomsWithNoBookings() {
        List<Room> unbookedRooms = new ArrayList<>();
        for(Room room : this.rooms) {
            boolean hasBooking = false;
            for(Booking booking : this.bookings) {
                if (booking.getRoom().equals(room)) {
                    hasBooking = true;
                    break;
                }
            }
            if (!hasBooking) {
                unbookedRooms.add(room);
            }
        }
        return unbookedRooms;
    }

    public boolean cancelBooking(int bookingId) {
        for(int i = 0; i < this.bookings.size(); i++) {
            Booking booking = this.bookings.get(i);
            if (booking.getBookingId() == bookingId) {
                this.bookings.remove(i);
                this.bookingsMap.remove(String.valueOf(bookingId));
                System.out.println("Booking " + bookingId + " cancelled successfully");
                return true;
            }
        }
        System.out.println("Error: Booking " + bookingId + " not found");
        return false;
    }

    public void displayAllRooms() {
        System.out.println("\nALL ROOMS IN " + this.hotelName.toUpperCase());
        if (this.rooms.isEmpty()) {
            System.out.println("No rooms available");
        } else {
            for(Room room : this.rooms) {
                System.out.println(room);
            }
        }
        System.out.println();
    }

    public void displayAllBookings() {
        System.out.println("\nALL BOOKINGS IN " + this.hotelName.toUpperCase());
        if (this.bookings.isEmpty()) {
            System.out.println("No bookings found");
        } else {
            for(Booking booking : this.bookings) {
                System.out.println(booking);
            }
        }
        System.out.println();
    }

    public void displayAvailableRooms(LocalDate checkInDate, LocalDate checkOutDate) {
        System.out.println("\n AVAILABLE ROOMS FROM " + checkInDate + " TO " + checkOutDate);
        ArrayList<Room> availableRooms = this.getAvailableRooms(checkInDate, checkOutDate);
        if (availableRooms.isEmpty()) {
            System.out.println("No rooms available for the requested dates");
        } else {
            for(Room room : availableRooms) {
                System.out.println(room);
            }
        }
        System.out.println();
    }

    public String getHotelName() {
        return this.hotelName;
    }

    public BigDecimal getTotalRevenue() {
        BigDecimal totalRevenue = BigDecimal.ZERO;
        for(Booking booking : this.bookings) {
            totalRevenue = totalRevenue.add(booking.calculateTotalCost());
        }
        return totalRevenue;
    }

    public String toString() {
        return "Hotel{Name='" + this.hotelName + "', Rooms=" + this.rooms.size() +
                ", Services=" + this.services.size() + ", Staff=" + this.staff.size() +
                ", Bookings=" + this.bookings.size() + ", Total Revenue=$" + this.getTotalRevenue() + "}";
    }

    public void checkForDuplicateRoom(Room room) throws DuplicateRoomException {
        if (room != null) {
            for(Room existingRoom : this.rooms) {
                if (existingRoom.getRoomId() == room.getRoomId()) {
                    throw new DuplicateRoomException("Room with ID " + room.getRoomId() + " already exists");
                }
            }
        }
    }

    public void checkForInvalidPricing(Room[] roomsToCheck) {
        if (roomsToCheck != null) {
            for(Room room : roomsToCheck) {
                if (room.getNightlyRate().compareTo(BigDecimal.ZERO) <= 0) {
                    System.out.println("Room " + room.getRoomId() + " has invalid pricing: " + room.getNightlyRate());
                }
            }
        }
    }

    public void printBookedRoomsForGuest(Guest guest) {
        if (guest != null) {
            System.out.println("Booked rooms for guest " + guest.getFullName() + ":");
            for(Booking booking : this.bookings) {
                if (booking.getGuest().equals(guest)) {
                    System.out.println("Room " + booking.getRoom().getRoomId() +
                            " from " + booking.getCheckInDate() + " to " + booking.getCheckOutDate());
                }
            }
        }
    }

    public Room findFirstUnbookedRoom(Room[] roomsToCheck) {
        if (roomsToCheck == null) {
            return null;
        }

        for(Room room : roomsToCheck) {
            if (room.isAvailable()) {
                return room;
            }
        }

        System.out.println("All rooms are occupied");
        return null;
    }

    public BigDecimal calculateTotalServiceCostsForGuest(Guest guest) {
        if (guest == null) {
            return BigDecimal.ZERO;
        }

        BigDecimal total = BigDecimal.ZERO;
        for(Booking booking : this.bookings) {
            if (booking.getGuest().equals(guest)) {
                List<HotelService> services = booking.getServices();
                for(HotelService service : services) {
                    total = total.add(service.getCost());
                }
            }
        }
        return total;
    }

    public void checkRoomCapacities(Room[] roomsToCheck, int maxCapacity) throws RoomCapacityExceededException {
        if (roomsToCheck != null) {
            for(Room room : roomsToCheck) {
                int roomCapacity = 2;
                if (room.getRoomType().equals("Deluxe")) {
                    roomCapacity = 4;
                } else if (room.getRoomType().equals("Suite")) {
                    roomCapacity = 6;
                }

                if (roomCapacity > maxCapacity) {
                    throw new RoomCapacityExceededException("Room " + room.getRoomId() +
                            " exceeds maximum allowed capacity (" + roomCapacity + " > " + maxCapacity + ")");
                }
            }
        }
    }

    public void printBookedRoomUsers() {
        for(Booking booking : this.bookings) {
            System.out.println("Room " + booking.getRoom().getRoomId() + " booked by " + booking.getGuest().getFullName());
        }
    }

    public void validateBookingDates(String[] checkInDates, String[] checkOutDates) throws InvalidBookingDatesExceptions {
        if (checkInDates == null || checkOutDates == null || checkInDates.length != checkOutDates.length) {
            throw new IllegalArgumentException("Input arrays must be non-null and of equal length");
        }

        for(int i = 0; i < checkInDates.length; i++) {
            LocalDate checkIn = LocalDate.parse(checkInDates[i]);
            LocalDate checkOut = LocalDate.parse(checkOutDates[i]);
            if (!checkOut.isAfter(checkIn)) {
                throw new InvalidBookingDatesExceptions("Invalid dates at index " + i + ": Check-out must be after check-in");
            }
        }
    }

    public void checkForDuplicateGuestBookings() throws DuplicateGuestBookingException {
        Set<Integer> guestIds = new HashSet<>();
        for(Booking booking : this.bookings) {
            int guestId = booking.getGuest().getGuestId();
            if (guestIds.contains(guestId)) {
                throw new DuplicateGuestBookingException("Guest with ID " + guestId + " has multiple bookings");
            }
            guestIds.add(guestId);
        }
        System.out.println("No duplicate guest bookings found");
    }
}