package Repositories;

import Customers.Location;
import Shoes.*;

import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class RepositoryShoes {

    Properties p = new Properties();

    public RepositoryShoes(){
        try (FileInputStream input = new FileInputStream("src/Settings.properties")) {
            p.load(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Brand> getBrand(){
        List<Brand> brandList = new ArrayList<>();
        try  (Connection c = DriverManager.getConnection(
            p.getProperty("connectionString"),
            p.getProperty("name"),
            p.getProperty("password"));

            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("select id, namn from märke")
        ) {
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("namn");

                Brand temp = new Brand(id,name);
                brandList.add(temp);
            }
            return brandList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Category> getCategory(){
        List<Category> categoryList = new ArrayList<>();
        try  (Connection c = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));

              Statement stmt = c.createStatement();
              ResultSet rs = stmt.executeQuery("select id, namn from kategori")
        ) {
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("namn");

                Category temp = new Category(id,name);
                categoryList.add(temp);
            }
            return categoryList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Colors> getColor(){
        List<Colors> colorsList = new ArrayList<>();
        try  (Connection c = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));

              Statement stmt = c.createStatement();
              ResultSet rs = stmt.executeQuery("select id, namn from färg")
        ) {
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("namn");

                Colors temp = new Colors(id,name);
                colorsList.add(temp);
            }
            return colorsList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Size> getSize(){
        List<Size> sizeList = new ArrayList<>();
        try  (Connection c = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));

              Statement stmt = c.createStatement();
              ResultSet rs = stmt.executeQuery("select id, värde from storlek")
        ) {
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("värde");

                Size temp = new Size(id,name);
                sizeList.add(temp);
            }
            return sizeList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Shoe> getShoes(){
        List<Size> sizeList = getSize();
        List<Colors> colorsList = getColor();
        List<Brand> brandList = getBrand();

        List<Shoe> shoeList = new ArrayList<>();
        try  (Connection c = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));

              Statement stmt = c.createStatement();
              ResultSet rs = stmt.executeQuery("select id, pris, FärgId, StorlekId, MärkeId from sko")
        ) {
            while (rs.next()){
                int id = rs.getInt("id");
                Double price = rs.getDouble("pris");
                int colorId = rs.getInt("FärgId");
                int sizeId = rs.getInt("StorlekId");
                int brandId = rs.getInt("MärkeId");

                int colorIndex = colorsList.indexOf(colorsList.stream().filter(x -> x.getId() == colorId).findFirst().get());
                int sizeIndex = sizeList.indexOf(sizeList.stream().filter(x -> x.getId() == sizeId).findFirst().get());
                int brandIndex = brandList.indexOf(brandList.stream().filter(x -> x.getId() == brandId).findFirst().get());

                Shoe temp = new Shoe(colorsList.get(colorIndex)
                        ,brandList.get(brandIndex)
                        ,sizeList.get(sizeIndex),id ,price);
                shoeList.add(temp);
            }
            return shoeList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<ShoeCategories> getShoeCategories(){
        List<Category> categoryList = getCategory();
        List<Shoe> shoeList = getShoes();

        List<ShoeCategories> shoeCategoriesList = new ArrayList<>();

        try  (Connection c = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));

              Statement stmt = c.createStatement();
              ResultSet rs = stmt.executeQuery("select skoId, kategoriId from KategoriTabell")
        ) {
            while (rs.next()){
                int shoeId = rs.getInt("skoId");
                int categoryId = rs.getInt("kategoriId");

                int shoeIndex = shoeList.indexOf(shoeList.stream().filter(x -> x.getId() == shoeId).findFirst().get());
                int categoryIndex = categoryList.indexOf(categoryList.stream().filter(x -> x.getId() == categoryId).findFirst().get());

                ShoeCategories temp = new ShoeCategories(shoeList.get(shoeIndex),categoryList.get(categoryIndex));
                shoeCategoriesList.add(temp);
            }
            return shoeCategoriesList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {

    }
}
