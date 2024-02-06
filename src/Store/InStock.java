package Store;
import Shoes.Shoe;


public class InStock {

    private int id;
    private int amount;
    Shoe shoe;

    public InStock(int id, int amount, Shoe shoe) {
        this.id = id;
        this.amount = amount;
        this.shoe = shoe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Shoe getShoe() {
        return shoe;
    }

    public void setShoe(Shoe shoe) {
        this.shoe = shoe;
    }

}
