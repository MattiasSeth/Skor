import Customers.Customer;
import Customers.CustomerDataStorage;
import Repositories.RepositoryStore;
import Shoes.ShoeCategories;
import Shoes.ShoeDataStorage;
import Store.CustomerOrder;
import Store.InStock;
import Store.StoreDataStorage;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class runShop {
    CustomerDataStorage customerData = new CustomerDataStorage();
    ShoeDataStorage shoeData = new ShoeDataStorage();
    StoreDataStorage storeData = new StoreDataStorage();
    public runShop (){
        Scanner sc = new Scanner(System.in);
        int userId;
        int userOrderId;

        List<InStock> shoesInStock = storeData.getInStockList();
        List<ShoeCategories> shoeCategoriesList = shoeData.getShoeCategoriesList();
        List<Customer> customerList = customerData.getCustomerList();
        List<CustomerOrder> customerOrders = storeData.getCustomerOrderList();

        while (true) {
            System.out.println("Ange användarnamn: ");
            String tempUsername = sc.nextLine();

            System.out.println("Ange lösenord: ");
            String tempPassword = sc.nextLine();
            boolean checkPassword = false;

            boolean checkUsername = customerList.stream().anyMatch(x -> x.getName().equalsIgnoreCase(tempUsername));
            if (checkUsername){
                int customerIndex = IntStream.range(0, customerList.size())
                        .filter(x -> customerList.get(x).getName().equalsIgnoreCase(tempUsername))
                        .findFirst()
                        .getAsInt();
                if (customerList.get(customerIndex).getPassword().equals(tempPassword)){
                    checkPassword = true;
                }
            }

            if(checkUsername && checkPassword){
                userId = customerList.stream().filter(x -> x.getName().equalsIgnoreCase(tempUsername))
                        .findFirst().get().getId();
                userOrderId = customerOrders.stream().filter(y -> y.getCustomer().getId() == userId).findFirst().get().getId();
                break;
            } else
                System.out.println("Fel användarnamn eller lösenord");
        }

        System.out.println("Skor i lager");
        //shoesInStock.forEach(x -> System.out.println(x.getId() + ". Size: " +x.getShoe().getSize().getValue() + " " +
        //        x.getShoe().getBrand().getName() + " \n   Color: " + x.getShoe().getShoecolor().getName() +
        //        " Price: " + x.getShoe().getPrice()+"Kr" ));

        Map<Integer, List<ShoeCategories>> shoeCategoriesByInStockId = shoeCategoriesList.stream()
                .collect(Collectors.groupingBy(s -> s.getShoe().getId()));

        shoesInStock.forEach(inStock -> {
            int inStockId = inStock.getId();
            List<ShoeCategories> matchingCategories = shoeCategoriesByInStockId.getOrDefault(inStockId, List.of());

            matchingCategories.forEach(category -> {
                System.out.println(inStockId +
                        ". Size: " + inStock.getShoe().getSize().getValue() +
                        " " + inStock.getShoe().getBrand().getName() + " " +category.getCategory().getName() +
                        "\n   Color: " + inStock.getShoe().getShoecolor().getName() +
                        " Price: " + inStock.getShoe().getPrice() + "Kr");
            });
        });

        System.out.println("Ange numret på den sko du vill ha (1-" +shoesInStock.size()+"):" );
        String temp = sc.nextLine();
        int shoeId = Integer.parseInt(temp);

        RepositoryStore repoStore = new RepositoryStore();
        boolean result = repoStore.addToCart(userId,userOrderId,shoeId);

        if(result){
            System.out.println("Din beställning är lagd");
        } else
            System.out.println("Något gick fel");
    }
    public static void main(String[] args) {
        runShop rs = new runShop();
    }
}
