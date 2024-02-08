package Customers;

import Repositories.RepositoryCustomers;

import java.util.ArrayList;
import java.util.List;

public class CustomerDataStorage {

    private List<Customer> customerList;
    private List<Location> locationList;

    public CustomerDataStorage(){
        RepositoryCustomers repository = new RepositoryCustomers();
        customerList = repository.getCustomer();
        locationList = repository.getLocation();

        //locationList.forEach(x -> System.out.println(x.getName()+ " " + x.getId()));
        //customerList.forEach(x -> System.out.println(x.getLocation().getName()+ " " + x.getId() + " " + x.getPassword()));
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    public List<Location> getLocationList() {
        return locationList;
    }

    public void setLocationList(List<Location> locationList) {
        this.locationList = locationList;
    }

}
