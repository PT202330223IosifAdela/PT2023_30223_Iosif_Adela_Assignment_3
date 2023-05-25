package businessLayer;

import dataAccessLayer.ClientDAO;
import dataAccessLayer.ProductDAO;
import model.Clients;
import model.Products;

import java.util.List;

public class ProductBLL {
     private ProductDAO productdao = new ProductDAO();

    public void insertProduct(Products p) {
        productdao.insert(p);
    }
    public boolean deleteProduct(Integer id){
        return productdao.delete(id);
    }
    public void updateClient(String f, String val, Integer id){
        productdao.update(f, val, id);
    }

    public Products findById(int id){
        return productdao.findById(id);
    }
    public List<Products> findAll(){
        return productdao.findAll();
    }
    public void decrementStock(int cantitate, Products p) {
        productdao.decrementStock(cantitate, p);
    }
}
