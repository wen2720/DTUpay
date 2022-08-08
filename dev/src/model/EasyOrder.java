package model;
import java.util.*;
import contract.IToken;
import type.Order;
// type EasyOrder = Collection<IToken>
public class EasyOrder extends Order {
    // empty constructor
    // initialize new token collection
    // tokens
    private Collection<IToken> tokens;
    public EasyOrder(Collection<IToken> tokens) {   //depedency injection
        this.tokens = tokens; 
    }
    // For forntend store token data in the heap, later convert to PUT API body to make order.
    @Override
    public Collection<IToken> fAddToken(IToken barcode) {
        tokens.add(barcode);
        return tokens;
    }
}