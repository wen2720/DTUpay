package model;
import java.util.*;
import contract.IToken;
import type.Token;
import type.Order;

public class EasyOrder extends Order {
    // empty constructor
    // initialize new token collection
    // tokens
    private Collection<IToken> tokens;
    public EasyOrder(Collection<IToken> tokens) {
        this.tokens = new ArrayList<IToken>();
    }
    @Override
    public Collection<IToken> fAddToken(IToken barcode) {
        tokens.add(barcode);
        return tokens;
    }
}