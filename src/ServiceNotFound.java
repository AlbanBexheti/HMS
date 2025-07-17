public class ServiceNotFound extends Exception {
    public ServiceNotFound() {
        super("Service not found");
    }

    public ServiceNotFound(String message) {
        super(message);
    }
}