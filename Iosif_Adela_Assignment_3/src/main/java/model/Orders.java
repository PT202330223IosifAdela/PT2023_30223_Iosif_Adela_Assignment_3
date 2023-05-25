package model;

public class Orders {
    private int id;
    private int quantity;
    private int idClient;
    private int idProduct;

    public int getId() {
        return id;
    }

    public void setId(int orderId) {
        this.id = orderId;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }
}
