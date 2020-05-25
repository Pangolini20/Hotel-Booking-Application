package Database;

public class Request {

    private String ID,owner,status="pending",customer;
    // status is pending,declined,confirmed,accepted;
    public Request()
    {}

    public Request(String ID, String owner, String customer) {
        this.ID = ID;
        this.owner = owner;
        this.customer = customer;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }
}
