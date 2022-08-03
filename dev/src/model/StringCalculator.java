
package model;
public class StringCalculator {
    public int evaluate(String expression) {
        int sum = 0;
        for (String summand: expression.split("\\+"))   //escape \ and + with \, so <StringPattern> = "\\+"
        sum += Integer.valueOf(summand);
        return sum;
    }
}

