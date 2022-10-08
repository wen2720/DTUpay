package dtustudent.dtupay.exception;

public class CustomerNotFoundException extends RuntimeException {

  public CustomerNotFoundException(String id) {
    super("Could not find employee " + id);
  }
}