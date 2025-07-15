package PACKAGE_NAME;

public class ServiceNotFound extends RuntimeException {
  public ServiceNotFound(String message) {
    super(message);
  }
}
