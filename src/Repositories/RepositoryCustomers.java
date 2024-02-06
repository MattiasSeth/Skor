package Repositories;

import Customers.Customer;
import Customers.Location;
import Shoes.Brand;
import Shoes.Colors;
import Shoes.Shoe;
import Shoes.Size;

import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class RepositoryCustomers {

    Properties p = new Properties();
    public RepositoryCustomers(){
        try (FileInputStream input = new FileInputStream("src/Settings.properties")) {
            p.load(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<Location> getLocation(){
        List<Location> locationList = new ArrayList<>();
        try  (Connection c = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));

              Statement stmt = c.createStatement();
              ResultSet rs = stmt.executeQuery("select id, namn from ort")
        ) {
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("namn");

                Location temp = new Location(id,name);
                locationList.add(temp);
            }
            return locationList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Customer> getCustomer(){
        List<Location> locationList = getLocation();
        List<Customer> customerList = new ArrayList<>();

        try  (Connection c = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));

              Statement stmt = c.createStatement();
              ResultSet rs = stmt.executeQuery("select id, namn, lösenord, ortId from kund")
        ) {
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("namn");
                String password = rs.getString("lösenord");
                int locationId = rs.getInt("ortId");


                int locationIndex = locationList.indexOf(locationList.stream().filter(x -> x.getId() == locationId).
                        findFirst().get());


                Customer temp = new Customer(name,id,password,locationList.get(locationIndex));
                customerList.add(temp);
            }
            return customerList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) {

    }
}
