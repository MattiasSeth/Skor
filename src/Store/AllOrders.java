package Store;
public class AllOrders {

    private int amount;
    CustomerOrder customerOrder;
    InStock instock;

    public AllOrders(int amount, CustomerOrder customerOrder, InStock instock) {
        this.amount = amount;
        this.customerOrder = customerOrder;
        this.instock = instock;
    }
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public CustomerOrder getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(CustomerOrder customerOrder) {
        this.customerOrder = customerOrder;
    }

    public InStock getInstock() {
        return instock;
    }

    public void setInstock(InStock instock) {
        this.instock = instock;
    }

}
