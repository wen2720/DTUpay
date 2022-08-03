package model;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import model.StringCalculator;
// TDD design, design for test, and before test.
public class TestStringCalculator {
  @Test
  public void fEveluateCalculatorSum() {
    StringCalculator Ccalculator = new StringCalculator();
    int Sum = Ccalculator.evaluate("1+2+3");
    assertEquals(6, Sum);
  }
}