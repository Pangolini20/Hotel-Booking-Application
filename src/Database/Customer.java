package Database;

public class Customer extends User implements java.io.Serializable{

    public Customer() {
    }

    public Customer(String username, String password, String full_name, String phone) {
        super(username, password, full_name, phone);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "username='" + getUsername() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", full_name='" + getFull_name() + '\'' +
                ", phone='" + getPhone() + '\'' +
                '}';
    }
}
