package Database;

public class HotelOwner extends User {

    private String EIN,address,facilities;

    public HotelOwner(String username, String password, String full_name, String phone,String ein,String address,String facilities)
    {
        super(username,password,full_name,phone);
        this.EIN = ein;
        this.address = address;
        this.facilities = facilities;
    }

    public HotelOwner(){}

    @Override
    public String toString() {
        return "HotelOwner{" + super.toString() +
                "EIN='" + EIN + '\'' +
                ", address='" + address + '\'' +
                ", facilities='" + facilities + '\'' +
                '}';
    }

    public String getEIN() {
        return EIN;
    }

    public void setEIN(String EIN) {
        this.EIN = EIN;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFacilities() {
        return facilities;
    }

    public void setFacilities(String facilities) {
        this.facilities = facilities;
    }
}
