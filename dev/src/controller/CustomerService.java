package controller;
import type.Service;
//service () to summarize the contracts(Interface, for code distribution) -> types(Abstract Class, for inheritance) -> model(Class, for modeling data)
public class CustomerService extends Service{
    // constructed by user,
    // initialize an order 
    private Iuser user;
    private IOrder order;
    public CustomerService(IUser user) {
        this.user = user;
    }

    // Use Token -> REST API POST ORDER

    // REST API POST ORDER
    // fCreateAnOrder
    // if User is NewCustomer then generate an customer uniqueID and add to the Order data set in the database.
    // if User is reigistered customer then use the customers name, id together with the id 

    // REST API DELETE ORDER
}
