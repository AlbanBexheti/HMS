public class RoomUnavailableException extends Exception {
    public RoomUnavailableException() {
        super("Room is not available for the requested dates!");
    }

    public RoomUnavailableException(String message) {
        super(message);
    }
}