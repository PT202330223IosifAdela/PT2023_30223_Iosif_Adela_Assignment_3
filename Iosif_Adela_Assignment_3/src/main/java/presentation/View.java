package presentation;

import dataAccessLayer.ClientDAO;
import dataAccessLayer.OrderDAO;
import dataAccessLayer.ProductDAO;
import businessLayer.Reflection;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.Clients;
import model.Orders;
import model.Products;

public class View {
    private JFrame startFrame;
    private JFrame clientFrame;
    private JFrame productFrame;
    private JFrame orderFrame;

    private JFrame order2Frame;
    private JTable clientTable;
    private JTable productTable;
    private JTable orderTable;
    private DefaultTableModel clientTableModel;
    private DefaultTableModel productTableModel;
    private DefaultTableModel orderTableModel;

    public View() {
        this.initializare();
    }

    private void initializare() {
        this.clientFrame = new JFrame("Client Operations");
        this.clientFrame.setDefaultCloseOperation(3);
        this.clientFrame.setSize(800, 600);
        String[] columns = {"id", "name", "phoneNumber"};

        ClientDAO clientDAO = new ClientDAO();

        this.clientTable = new JTable();
        Reflection tableR = new Reflection();

        List<Clients> clientList1 = clientDAO.findAll();
        JTable aux = tableR.create1(clientList1);

        //this.clientTableModel = new DefaultTableModel(columns, clientList1.toArray());
        clientTable.setModel(aux.getModel());

        //this.clientTableModel.addColumn("id");
        //this.clientTableModel.addColumn("name");
        //this.clientTableModel.addColumn("phoneNumber");
        //this.clientTable = new JTable(this.clientTableModel);
        JScrollPane clientScrollPane = new JScrollPane(this.clientTable);
        this.clientFrame.getContentPane().add(clientScrollPane);
        JPanel clientButtonPanel = new JPanel();
        JButton addClientButton = new JButton("Add Client");
        addClientButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog(View.this.clientFrame, "Enter client name:");
                String phoneNumber = JOptionPane.showInputDialog(View.this.clientFrame, "Enter client phone number:");
                ClientDAO cl = new ClientDAO();
                Clients c1 = new Clients();
                c1.setName(name);
                c1.setPhoneNumber(phoneNumber);
                cl.insert(c1);
                View.this.clientTableModel.addRow(new Object[]{View.this.clientTableModel.getRowCount() + 1, name, phoneNumber});
            }
        });
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                View.this.clientFrame.setVisible(false);
                View.this.startFrame.setVisible(true);
            }
        });
        clientButtonPanel.add(backButton);
        this.clientFrame.getContentPane().add(clientButtonPanel, "South");
        clientButtonPanel.add(addClientButton);
        JButton editClientButton = new JButton("Edit Client");
        editClientButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRowIndex = View.this.clientTable.getSelectedRow();
                if (selectedRowIndex != -1) {
                    int clientId = (Integer)View.this.clientTableModel.getValueAt(selectedRowIndex, 0);
                    String currentName = (String)View.this.clientTableModel.getValueAt(selectedRowIndex, 1);
                    String currentPhoneNumber = (String)View.this.clientTableModel.getValueAt(selectedRowIndex, 2);
                    String updatedName = JOptionPane.showInputDialog(View.this.clientFrame, "Enter updated name:", currentName);
                    String updatedPhoneNumber = JOptionPane.showInputDialog(View.this.clientFrame, "Enter updated phone number:", currentPhoneNumber);
                    View.this.clientTableModel.setValueAt(updatedName, selectedRowIndex, 1);
                    View.this.clientTableModel.setValueAt(updatedPhoneNumber, selectedRowIndex, 2);
                    ClientDAO cl = new ClientDAO();
                    cl.update("name", updatedName, clientId);
                    cl.update("phoneNumber", updatedPhoneNumber, clientId);
                    JOptionPane.showMessageDialog(View.this.clientFrame, "Update client done!");
                } else {
                    JOptionPane.showMessageDialog(View.this.clientFrame, "Va rog selectati un client!");
                }

            }
        });
        clientButtonPanel.add(editClientButton);
        JButton deleteClientButton = new JButton("Delete Client");
        deleteClientButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRowIndex = View.this.clientTable.getSelectedRow();
                if (selectedRowIndex != -1) {
                    int clientId = (Integer)View.this.clientTableModel.getValueAt(selectedRowIndex, 0);
                    View.this.clientTableModel.removeRow(selectedRowIndex);
                    ClientDAO cl = new ClientDAO();
                    cl.delete(clientId);
                    JOptionPane.showMessageDialog(View.this.clientFrame, "Stergerea clientului a fost facuta cu succes!");
                } else {
                    JOptionPane.showMessageDialog(View.this.clientFrame, "Va rog selectati un client!");
                }

            }
        });
        clientButtonPanel.add(deleteClientButton);
        this.clientFrame.getContentPane().add(clientButtonPanel, "South");
        this.productFrame = new JFrame("Product Operations");
        this.productFrame.setDefaultCloseOperation(3);
        this.productFrame.setSize(800, 600);
        this.productTableModel = new DefaultTableModel();


        ProductDAO productDAO = new ProductDAO();
        this.productTable = new JTable();
        Reflection tableR2 = new Reflection();

        List<Products> prodList1 = productDAO.findAll();
        JTable aux2 = tableR2.create1(prodList1);
        productTable.setModel(aux2.getModel());


        /*this.productTableModel.addColumn("id");
        this.productTableModel.addColumn("name");
        this.productTableModel.addColumn("stock");
        this.productTable = new JTable(this.productTableModel);*/
        JScrollPane productScrollPane = new JScrollPane(this.productTable);
        this.productFrame.getContentPane().add(productScrollPane);
        JPanel productButtonPanel = new JPanel();
        JButton addProductButton = new JButton("Add Product");
        addProductButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog(View.this.clientFrame, "Enter product name:");
                Integer stock = Integer.valueOf(JOptionPane.showInputDialog(View.this.clientFrame, "Enter product stock:"));
                ProductDAO pr = new ProductDAO();
                Products p1 = new Products();
                p1.setName(name);
                p1.setStock(stock);
                pr.insert(p1);
                View.this.productTableModel.addRow(new Object[]{View.this.productTableModel.getRowCount() + 1, name, stock});
            }
        });
        JButton backButton2 = new JButton("Back");
        backButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                View.this.productFrame.setVisible(false);
                View.this.startFrame.setVisible(true);
            }
        });
        productButtonPanel.add(backButton2);
        this.productFrame.getContentPane().add(productButtonPanel, "South");
        productButtonPanel.add(addProductButton);
        JButton editProductButton = new JButton("Edit Product");
        editProductButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRowIndex = View.this.productTable.getSelectedRow();
                if (selectedRowIndex != -1) {
                    Integer productId = (Integer)View.this.productTableModel.getValueAt(selectedRowIndex, 0);
                    String currentName = (String)View.this.productTableModel.getValueAt(selectedRowIndex, 1);
                    Integer currentStock = (Integer)View.this.productTableModel.getValueAt(selectedRowIndex, 2);
                    String updatedName = JOptionPane.showInputDialog(View.this.productFrame, "Enter updated name:", currentName);
                    String updatedStock = JOptionPane.showInputDialog(View.this.productFrame, "Enter updated stock:", currentStock);
                    View.this.productTableModel.setValueAt(updatedName, selectedRowIndex, 1);
                    View.this.productTableModel.setValueAt(updatedStock, selectedRowIndex, 2);
                    ProductDAO pr = new ProductDAO();
                    pr.update("name", updatedName, productId);
                    pr.update("stock", updatedStock, productId);
                    JOptionPane.showMessageDialog(View.this.productFrame, "Update produs done!");
                } else {
                    JOptionPane.showMessageDialog(View.this.productFrame, "Va rog selectati un produs!");
                }

            }
        });
        productButtonPanel.add(editProductButton);
        JButton deleteProductButton = new JButton("Delete Product");
        deleteProductButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRowIndex = View.this.productTable.getSelectedRow();
                if (selectedRowIndex != -1) {
                    Integer productId = (Integer)View.this.productTableModel.getValueAt(selectedRowIndex, 0);
                    View.this.productTableModel.removeRow(selectedRowIndex);
                    ProductDAO pr = new ProductDAO();
                    pr.delete(productId);
                    JOptionPane.showMessageDialog(View.this.productFrame, "Stergerea produsului a fost facuta cu succes!");
                } else {
                    JOptionPane.showMessageDialog(View.this.productFrame, "Va rog selectati un produs!");
                }

            }
        });
        productButtonPanel.add(deleteProductButton);
        this.productFrame.getContentPane().add(productButtonPanel, "South");
        this.orderFrame = new JFrame("Create Product Order");
        this.orderFrame.setDefaultCloseOperation(3);
        this.orderFrame.setSize(800, 600);
        JPanel orderPanel = new JPanel();

       /* this.orderTable = new JTable();
        Reflection tableR1 = new Reflection();

        OrderDAO orderDao = new OrderDAO();
        List<Orders> orderList1 = orderDao.findAll();
        JTable aux1 = tableR1.create1(orderList1);

        //this.clientTableModel = new DefaultTableModel(columns, clientList1.toArray());
        orderTable.setModel(aux1.getModel());*/

        //this.orderTableModel.addColumn("id");
        //this.orderTableModel.addColumn("quantity");
        //this.orderTableModel.addColumn("idClient");
        //this.orderTableModel.addColumn("idProduct");
        //this.orderTable = new JTable(this.orderTableModel);

        ///fereastra noua pt orders
        this.order2Frame = new JFrame("Orders interface");
        this.order2Frame.setDefaultCloseOperation(3);
        this.order2Frame.setSize(600, 600);

        this.orderTable = new JTable();
        Reflection tableR1 = new Reflection();
        OrderDAO orderDao = new OrderDAO();
        List<Orders> orderList1 = orderDao.findAll();
        JTable aux1 = tableR1.create1(orderList1);
        orderTable.setModel(aux1.getModel());

        JScrollPane orderScrollPane = new JScrollPane(this.orderTable);
        this.order2Frame.getContentPane().add(orderScrollPane);
        ///

        final JComboBox<String> productComboBox = new JComboBox();
        final JComboBox<String> clientComboBox = new JComboBox();
        final JTextField quantityTextField = new JTextField(10);
        final List<Integer> idClients = new ArrayList();
        final List<Integer> idProducts = new ArrayList();
        List<Integer> quantityO = new ArrayList();
        final ProductDAO pr = new ProductDAO();
        ClientDAO cl = new ClientDAO();
        List<Clients> clientList = cl.findAll();
        List<Products> productList = pr.findAll();

        clientComboBox.addItem("None");
        Iterator var25 = clientList.iterator();

        while(var25.hasNext()) {
            Clients clients = (Clients)var25.next();
            idClients.add(clients.getId());
            clientComboBox.addItem(clients.getName());
        }

        productComboBox.addItem("None");
        var25 = productList.iterator();

        while(var25.hasNext()) {
            Products prod = (Products)var25.next();
            idProducts.add(prod.getId());
            quantityO.add(prod.getStock());
            productComboBox.addItem(prod.getName());
        }

        JButton createOrderButton = new JButton("Create Order");
        createOrderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Integer quantity = Integer.valueOf(quantityTextField.getText());
                Integer clientId = (Integer)idClients.get(clientComboBox.getSelectedIndex() - 1);
                Integer productId = (Integer)idProducts.get(productComboBox.getSelectedIndex() - 1);
                Products p = (Products)pr.findById(productId);
                if (p.getStock() - quantity >= 0) {
                    pr.decrementStock(quantity, p);
                    OrderDAO ord = new OrderDAO();
                    Orders o1 = new Orders();
                    o1.setQuantity(quantity);
                    o1.setIdClient(clientId);
                    o1.setIdProduct(productId);
                    ord.insert(o1);
                    //View.this.orderTableModel.addRow(new Object[]{View.this.orderTableModel.getRowCount() + 1, quantity, clientId, productId});


                    //fereastra noua pt afisare
                    order2Frame.setVisible(true);


                } else {
                    JOptionPane.showMessageDialog((Component)null, "Stoc insuficient!", "Error", 0);
                }

            }
        });
        JButton backButton3 = new JButton("Back");
        backButton3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                View.this.orderFrame.setVisible(false);
                View.this.startFrame.setVisible(true);
            }
        });
        orderPanel.add(backButton3);
        this.orderFrame.getContentPane().add(orderPanel);
        this.orderFrame.getContentPane();
        orderPanel.add(new JLabel("Product:"));
        orderPanel.add(productComboBox);
        orderPanel.add(new JLabel("Client:"));
        orderPanel.add(clientComboBox);
        orderPanel.add(new JLabel("Quantity:"));
        orderPanel.add(quantityTextField);
        orderPanel.add(createOrderButton);
        this.orderFrame.getContentPane().add(orderPanel);

        this.startFrame = new JFrame("Start Page");
        this.startFrame.setDefaultCloseOperation(3);
        this.startFrame.setSize(500, 500);
        this.startFrame.setLayout(new GridLayout(3, 1));
        JButton clientButton = new JButton("Clients");
        clientButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                View.this.startFrame.dispose();
                View.this.clientFrame.setVisible(true);
            }
        });
        clientButton.setForeground(new Color(0, 25, 51));
        clientButton.setFont(new Font("Algerian", 1, 16));
        clientButton.setBackground(new Color(164, 220, 213));
        this.startFrame.add(clientButton);
        JButton productButton = new JButton("Products");
        productButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                View.this.startFrame.dispose();
                View.this.productFrame.setVisible(true);
            }
        });
        productButton.setForeground(new Color(0, 25, 51));
        productButton.setFont(new Font("Algerian", 1, 16));
        productButton.setBackground(new Color(142, 232, 220));
        this.startFrame.add(productButton);
        JButton orderButton = new JButton("Orders");
        orderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                View.this.startFrame.dispose();
                View.this.orderFrame.setVisible(true);
            }
        });
        orderButton.setForeground(new Color(0, 25, 51));
        orderButton.setFont(new Font("Algerian", 1, 16));
        orderButton.setBackground(new Color(164, 220, 213));
        this.startFrame.add(orderButton);
        this.startFrame.setVisible(true);

    }
}
