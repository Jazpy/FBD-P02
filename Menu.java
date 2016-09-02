
package Taqueria;

public class Menu {
    
    private final int dishID;
    private final String dish;
    private double price;
    
    public Menu(int dishID, String dish, double price){
        this.dishID = dishID;
        this.dish = dish;
        this.price = price;
    }
    
    public int getDishID(){
        return this.dishID;
    }
    public String getDish(){
        return this.dish;
    }
    
    public double getPrice(){
        return this.price;
    }
    
    public void setPrice(double price){
        this.price = price;
    }
    
    @Override
    public String toString(){
        return dishID + "," + dish + "," + price;
    }
}
