import java.util.ArrayList;
import java.time.LocalDate;
import java.math.BigDecimal;
import java.util.List;

public class Hotel {
    private String hotelName;
    private ArrayList<Room> rooms;
    private ArrayList<Booking> bookings;
    private ArrayList<HotelService> services;
    private ArrayList<Staff> staff;
    private int nextBookingId;

    public Hotel(String hotelName) {
        this.hotelName = hotelName;
        this.rooms = new ArrayList<>();
        this.bookings = new ArrayList<>();
        this.services = new ArrayList<>();
        this.staff = new ArrayList<>();
        this.nextBookingId = 1;
    }

    public void addService(HotelService service) {
        services.add(service);
        System.out.println("Service " + service.getServiceId() + " (" + service.getDescription() + ") added to " + hotelName);
    }

    public HotelService findServiceById(String serviceId) throws ServiceNotFound {
        for (HotelService service : services) {
            if (service.getServiceId().equals(serviceId)) {
                return service;
            }
        }
        throw new ServiceNotFound("Service with ID " + serviceId + " not found");
    }

    public void displayAllServices() {
        System.out.println("\nALL SERVICES IN " + hotelName.toUpperCase());
        if (services.isEmpty()) {
            System.out.println("No services available");
        } else {
            for (HotelService service : services) {
                System.out.println(service.toString());
            }
        }
        System.out.println();
    }

    public ArrayList<HotelService> getAllServices() {
        return new ArrayList<>(services);
    }

    public void addStaff(Staff staffMember) {
        staff.add(staffMember);
        System.out.println("Staff member " + staffMember.getNamePublic() + " (ID: " + staffMember.getStaffIdPublic() + ") added to " + hotelName);
    }

    public Staff findStaffById(String staffId) {
        for (Staff staffMember : staff) {
            if (staffMember.getStaffIdPublic().equals(staffId)) {
                return staffMember;
            }
        }
        return null;
    }

    public void displayAllStaff() {
        System.out.println("\nALL STAFF IN " + hotelName.toUpperCase());
        if (staff.isEmpty()) {
            System.out.println("No staff members found");
        } else {
            for (Staff staffMember : staff) {
                System.out.println(staffMember.toString());
            }
        }
        System.out.println();
    }

    public ArrayList<Staff> getAllStaff() {
        return new ArrayList<>(staff);
    }

    public BigDecimal calculateTotalCharges(List<Chargeable> chargeableItems) {
        BigDecimal total = BigDecimal.ZERO;
        for (Chargeable item : chargeableItems) {
            total = total.add(item.getCost());
        }
        return total;
    }

    public void processBookableItems(List<Bookable> bookableItems, LocalDate startDate, LocalDate endDate) {
        System.out.println("Processing bookable items from " + startDate + " to " + endDate + ":");
        int itemNumber = 1;

        for (Bookable item : bookableItems) {
            String itemDescription = getBookableItemDescription(item);

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
        rooms.add(room);
        System.out.println("Room " + room.getRoomId() + " added to " + hotelName);
    }

    public Room findRoomById(int roomId) {
        for (Room room : rooms) {
            if (room.getRoomId() == roomId) {
                return room;
            }
        }
        return null;
    }

    public Room findRoomByNumber(String roomNumber) {
        try {
            int roomId = Integer.parseInt(roomNumber);
            return findRoomById(roomId);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private boolean isRoomAvailableForDates(Room room, LocalDate checkInDate, LocalDate checkOutDate) {
        for (Booking booking : bookings) {
            if (booking.getRoom().equals(room)) {
                if (!(checkOutDate.isBefore(booking.getCheckInDate()) ||
                        checkInDate.isAfter(booking.getCheckOutDate()) ||
                        checkInDate.isEqual(booking.getCheckOutDate()) ||
                        checkOutDate.isEqual(booking.getCheckInDate()))) {
                    return false;
                }
            }
        }
        return true;
    }

    public ArrayList<Room> getAvailableRooms(LocalDate checkInDate, LocalDate checkOutDate) {
        ArrayList<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (isRoomAvailableForDates(room, checkInDate, checkOutDate)) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    public ArrayList<Room> getAllRooms() {
        return new ArrayList<>(rooms);
    }

    public boolean makeBooking(Guest guest, int roomId, LocalDate checkInDate, LocalDate checkOutDate)
            throws InvalidBookingDatesExceptions, RoomUnavailableException {

        if (checkOutDate.isBefore(checkInDate) || checkOutDate.isEqual(checkInDate)) {
            throw new InvalidBookingDatesExceptions("Check-out date must be after check-in date");
        }

        Room room = findRoomById(roomId);
        if (room == null) {
            throw new RoomUnavailableException("Room " + roomId + " not found");
        }

        if (!isRoomAvailableForDates(room, checkInDate, checkOutDate)) {
            throw new RoomUnavailableException("Room " + roomId + " is not available for the requested dates");
        }

        Booking newBooking = new Booking(nextBookingId++, room, guest, checkInDate, checkOutDate);
        bookings.add(newBooking);

        System.out.println("Booking successful! Booking ID: " + newBooking.getBookingId() +
                " | Total Cost: $" + newBooking.calculateTotalCost());
        return true;
    }

    public boolean makeBooking(Guest guest, String roomNumber, LocalDate checkInDate, LocalDate checkOutDate)
            throws InvalidBookingDatesExceptions, RoomUnavailableException {
        try {
            int roomId = Integer.parseInt(roomNumber);
            return makeBooking(guest, roomId, checkInDate, checkOutDate);
        } catch (NumberFormatException e) {
            throw new RoomUnavailableException("Invalid room number: " + roomNumber);
        }
    }

    public boolean cancelBooking(int bookingId) {
        for (int i = 0; i < bookings.size(); i++) {
            Booking booking = bookings.get(i);
            if (booking.getBookingId() == bookingId) {
                bookings.remove(i);
                System.out.println("Booking " + bookingId + " cancelled successfully");
                return true;
            }
        }
        System.out.println("Error: Booking " + bookingId + " not found");
        return false;
    }

    public void displayAllRooms() {
        System.out.println("\nALL ROOMS IN " + hotelName.toUpperCase());
        if (rooms.isEmpty()) {
            System.out.println("No rooms available");
        } else {
            for (Room room : rooms) {
                System.out.println(room);
            }
        }
        System.out.println();
    }

    public void displayAllBookings() {
        System.out.println("\nALL BOOKINGS IN " + hotelName.toUpperCase());
        if (bookings.isEmpty()) {
            System.out.println("No bookings found");
        } else {
            for (Booking booking : bookings) {
                System.out.println(booking);
            }
        }
        System.out.println();
    }

    public void displayAvailableRooms(LocalDate checkInDate, LocalDate checkOutDate) {
        System.out.println("\n AVAILABLE ROOMS FROM " + checkInDate + " TO " + checkOutDate);
        ArrayList<Room> availableRooms = getAvailableRooms(checkInDate, checkOutDate);
        if (availableRooms.isEmpty()) {
            System.out.println("No rooms available for the requested dates");
        } else {
            for (Room room : availableRooms) {
                System.out.println(room);
            }
        }
        System.out.println();
    }

    public String getHotelName() {
        return hotelName;
    }

    public BigDecimal getTotalRevenue() {
        BigDecimal totalRevenue = BigDecimal.ZERO;
        for (Booking booking : bookings) {
            totalRevenue = totalRevenue.add(booking.calculateTotalCost());
        }
        return totalRevenue;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "Name='" + hotelName + '\'' +
                ", Rooms=" + rooms.size() +
                ", Services=" + services.size() +
                ", Staff=" + staff.size() +
                ", Bookings=" + bookings.size() +
                ", Total Revenue=$" + getTotalRevenue() +
                '}';
    }
}