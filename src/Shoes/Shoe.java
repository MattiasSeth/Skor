package Shoes;

public class Shoe {

    Colors shoecolor;
    Brand brand;
    Size size;
    private int id;
    private double price;

    public Shoe(Colors shoecolor, Brand brand, Size size, int id, double price) {
        this.shoecolor = shoecolor;
        this.brand = brand;
        this.size = size;
        this.id = id;
        this.price = price;
    }

    public Colors getShoecolor() {
        return shoecolor;
    }

    public void setShoecolor(Colors shoecolor) {
        this.shoecolor = shoecolor;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


}
