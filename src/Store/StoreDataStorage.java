package Store;

import Repositories.RepositoryStore;

import java.util.ArrayList;
import java.util.List;

public class StoreDataStorage {
    private List<InStock> inStockList;
    private List<CustomerOrder> customerOrderList;
    private List<AllOrders> allOrdersList;

    public StoreDataStorage(){
        RepositoryStore repository = new RepositoryStore();
        inStockList = repository.getInStock();
        customerOrderList = repository.getCustomerOrders();
        allOrdersList = repository.getAllOrders();

        /*
        allOrdersList.forEach(x -> System.out.println(x.getAmount() + " " +x.getInstock().getShoe().getPrice() +" " +
                x.getCustomerOrder().getCustomer().getName()));
        customerOrderList.forEach(x -> System.out.println(x.getId() + " " + x.getCustomer().getName() + " " +
                x.getCustomer().getPassword() + " " + x.getCustomer().getLocation().getName() ));
        inStockList.forEach(x -> System.out.println(x.getId() + " " + x.getAmount() +" "+ x.getShoe().getSize().getValue() + " " +
                x.getShoe().getPrice() + " " + x.getShoe().getShoecolor().getName() +" " + x.getShoe().getBrand().getName()));

         */
    }

    public List<InStock> getInStockList() {
        return inStockList;
    }

    public void setInStockList(List<InStock> inStockList) {
        this.inStockList = inStockList;
    }

    public List<CustomerOrder> getCustomerOrderList() {
        return customerOrderList;
    }

    public void setCustomerOrderList(List<CustomerOrder> customerOrderList) {
        this.customerOrderList = customerOrderList;
    }

    public List<AllOrders> getAllOrdersList() {
        return allOrdersList;
    }

    public void setAllOrdersList(List<AllOrders> allOrdersList) {
        this.allOrdersList = allOrdersList;
    }

    public static void main(String[] args) {

    }
}
