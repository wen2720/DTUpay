package contract;
import java.util.*;
import contract.IToken;
public interface IOrder {
    public Collection<IToken> fAddToken(IToken barcode);
}