package Customers;

public class Customer {

    private String name;
    private int id;
    private String password;
    Location location;

    public Customer(String name, int id, String password, Location location) {
        this.name = name;
        this.id = id;
        this.password = password;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

}
