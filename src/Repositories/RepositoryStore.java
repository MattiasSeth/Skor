package Repositories;

import Customers.Customer;
import Customers.Location;
import Shoes.Shoe;
import Store.AllOrders;
import Store.CustomerOrder;
import Store.InStock;

import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class RepositoryStore {

    Properties p = new Properties();

    public RepositoryStore(){
        try (FileInputStream input = new FileInputStream("src/Settings.properties")) {
            p.load(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<InStock> getInStock(){
        RepositoryShoes repositoryShoes = new RepositoryShoes();
        List<Shoe> shoeList = repositoryShoes.getShoes();

        List<InStock> inStockList = new ArrayList<>();

        try  (Connection c = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));

              Statement stmt = c.createStatement();
              ResultSet rs = stmt.executeQuery("select id, antal, skoId from lager")
        ) {
            while (rs.next()){
                int id = rs.getInt("id");
                int amount = rs.getInt("antal");
                int shoeId = rs.getInt("skoId");

                int shoeIndex = shoeList.indexOf(shoeList.stream().filter(x -> x.getId() == shoeId).
                        findFirst().get());

                InStock temp = new InStock(id,amount, shoeList.get(shoeIndex));
                inStockList.add(temp);
            }
            return inStockList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<CustomerOrder> getCustomerOrders(){
        RepositoryCustomers repositoryCustomers = new RepositoryCustomers();
        List<Customer> customerList = repositoryCustomers.getCustomer();

        List<CustomerOrder> customerOrderList = new ArrayList<>();

        try  (Connection c = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));

              Statement stmt = c.createStatement();
              ResultSet rs = stmt.executeQuery("select id, kundId from kundOrder")
        ) {
            while (rs.next()){
                int id = rs.getInt("id");
                int customerId = rs.getInt("kundId");

                int customerIndex = customerList.indexOf(customerList.stream().filter(x -> x.getId() == customerId).
                        findFirst().get());

                CustomerOrder temp = new CustomerOrder(id, customerList.get(customerIndex));
                customerOrderList.add(temp);
            }
            return customerOrderList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<AllOrders> getAllOrders(){
        List<CustomerOrder> customerOrderList = getCustomerOrders();
        List<InStock> inStockList = getInStock();

        List<AllOrders> allOrdersList = new ArrayList<>();

        try  (Connection c = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));

              Statement stmt = c.createStatement();
              ResultSet rs = stmt.executeQuery("select antal, kundOrderId, lagerId from orderrader")
        ) {
            while (rs.next()){
                int amount = rs.getInt("antal");
                int customerId = rs.getInt("kundOrderId");
                int inStockId = rs.getInt("lagerId");

                int customerIndex = customerOrderList.indexOf(customerOrderList.stream().filter(x -> x.getId() == customerId).
                        findFirst().get());

                int inStockIndex = inStockList.indexOf(inStockList.stream().filter(x -> x.getId() == inStockId).
                        findFirst().get());

                AllOrders temp = new AllOrders(amount, customerOrderList.get(customerIndex),inStockList.get(inStockIndex));
                allOrdersList.add(temp);
            }
            return allOrdersList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean addToCart(int kundId, int kundOrderId, int lagerId){

        try (Connection con = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));

             CallableStatement stmt = con.prepareCall("call addToCart(?,?,?)")){

            stmt.setInt(1, kundId);
            stmt.setInt(2, kundOrderId);
            stmt.setInt(3,lagerId);
            boolean result = stmt.execute();

            return result;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
