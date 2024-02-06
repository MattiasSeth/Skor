package Shoes;

public class ShoeCategories {

    Shoe shoe;
    Category category;

    public ShoeCategories(Shoe shoe, Category category) {
        this.shoe = shoe;
        this.category = category;
    }

    public Shoe getShoe() {
        return shoe;
    }

    public void setShoe(Shoe shoe) {
        this.shoe = shoe;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


}
