package Database;

public class Hotelroom {

    private double size,price;
    boolean available;

    public Hotelroom(double size, double price, boolean is_avalaible) {
        this.size = size;
        this.price = price;
        this.available = is_avalaible;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }
}
