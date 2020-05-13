package Database;

public class Customer implements java.io.Serializable{

    private String username,password,full_name,phone,role;

    public Customer(String username, String password, String full_name, String phone) {
        this.username = username;
        this.password = password;
        this.full_name = full_name;
        this.phone = phone;
    }

    public Customer()
    {

    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Client{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", full_name='" + full_name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
