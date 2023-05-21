package model;

public class Clients {
    private int id;
    private String name;
    private String phoneNumber;
    public int getClientId() {
        return id;
    }

    public void setClientId(int clientId) {
        this.id = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
