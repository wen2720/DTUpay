package dtustudent.dtupay.exception;

public class TokenNotFoundException extends RuntimeException {

  public TokenNotFoundException(String id) {
    super("Could not find token " + id);
  }
}