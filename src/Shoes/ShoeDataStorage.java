package Shoes;

import Repositories.RepositoryShoes;

import java.util.ArrayList;
import java.util.List;

public class ShoeDataStorage {
    private List<Brand> brandList;
    private List<Category> categoryList;
    private List<Colors> colorsList;
    private List<Shoe> shoeList;
    private List<ShoeCategories> shoeCategoriesList;
    private List<Size> sizeList;

    public ShoeDataStorage(){
        RepositoryShoes repositoryShoes = new RepositoryShoes();
        brandList = repositoryShoes.getBrand();
        categoryList = repositoryShoes.getCategory();
        colorsList = repositoryShoes.getColor();
        shoeList = repositoryShoes.getShoes();
        shoeCategoriesList = repositoryShoes.getShoeCategories();
        sizeList = repositoryShoes.getSize();


        brandList.forEach(x -> System.out.println(x.getName()+ " " + x.getId()));
        categoryList.forEach(x -> System.out.println(x.getName()+ " " + x.getId()));
        colorsList.forEach(x -> System.out.println(x.getName()+ " " + x.getId()));
        shoeList.forEach(x -> System.out.println(x.getPrice()+ " " + x.getId() + " " + x.getBrand().getName() + " " +
                x.getShoecolor().getName() + " " + x.getSize().getValue()));
        shoeCategoriesList.forEach(x -> System.out.println(x.getCategory().getName() + " " + x.getShoe().getBrand().getName() +" " +
                x.getShoe().getShoecolor().getName() + " " + x.getShoe().getPrice() + " " + x.getShoe().getSize().getValue()));
        sizeList.forEach(x -> System.out.println(x.getValue()+ " " + x.getId()));

    }

    public List<Brand> getBrandList() {
        return brandList;
    }

    public void setBrandList(List<Brand> brandList) {
        this.brandList = brandList;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public List<Colors> getColorsList() {
        return colorsList;
    }

    public void setColorsList(List<Colors> colorsList) {
        this.colorsList = colorsList;
    }

    public List<Shoe> getShoeList() {
        return shoeList;
    }

    public void setShoeList(List<Shoe> shoeList) {
        this.shoeList = shoeList;
    }

    public List<ShoeCategories> getShoeCategoriesList() {
        return shoeCategoriesList;
    }

    public void setShoeCategoriesList(List<ShoeCategories> shoeCategoriesList) {
        this.shoeCategoriesList = shoeCategoriesList;
    }

    public List<Size> getSizeList() {
        return sizeList;
    }

    public void setSizeList(List<Size> sizeList) {
        this.sizeList = sizeList;
    }

    public static void main(String[] args) {
        ShoeDataStorage s = new ShoeDataStorage();

    }
}
