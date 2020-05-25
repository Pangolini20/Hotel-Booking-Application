package Database;

public class Hotelroom {

    private String ID;
    private String owner;
    private String size,price,usr_occup;
    private String nr_pers;

    public Hotelroom() {
    }

    public String getNr_pers() {
        return nr_pers;
    }

    public void setNr_pers(String nr_pers) {
        this.nr_pers = nr_pers;
    }

    public String getUsr_occup() {
        return usr_occup;
    }

    public void setUsr_occup(String usr_occup) {
        this.usr_occup = usr_occup;
    }

    private boolean available=true;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Hotelroom(String ID, String owner, String size, String price, String nr_pers) {
        this.ID = ID;
        this.owner = owner;
        this.size = size;
        this.price = price;
        this.nr_pers = nr_pers;
    }

    @Override
    public String toString() {
        return "Hotelroom{" +
                "ID='" + ID + '\'' +
                ", owner='" + owner + '\'' +
                ", size='" + size + '\'' +
                ", price='" + price + '\'' +
                ", available=" + available +
                '}';
    }
}
