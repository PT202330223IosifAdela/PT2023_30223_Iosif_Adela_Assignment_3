package presentation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//import static jdk.internal.misc.OSEnvironment.initialize;

public class View {
    private JFrame startFrame;
    private JFrame clientFrame;
    private JFrame productFrame;
    private JFrame orderFrame;
    private JTable clientTable;
    private JTable productTable;
    private DefaultTableModel clientTableModel;
    private DefaultTableModel productTableModel;

    public View() {
        initializare();
    }

    private void initializare() {
        // Create the client window
        clientFrame = new JFrame("Client Operations");
        clientFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        clientFrame.setSize(800, 600);

        // Create client table and model
        clientTableModel = new DefaultTableModel();
        clientTableModel.addColumn("id");
        clientTableModel.addColumn("name");
        clientTableModel.addColumn("phoneNumber");
        clientTable = new JTable(clientTableModel);
        JScrollPane clientScrollPane = new JScrollPane(clientTable);

        clientFrame.getContentPane().add(clientScrollPane);

        // Create client operation buttons
        JPanel clientButtonPanel = new JPanel();

        JButton addClientButton = new JButton("Add Client");
        addClientButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Add new client logic
            }
        });

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clientFrame.setVisible(false);
                startFrame.setVisible(true);
            }
        });
        clientButtonPanel.add(backButton);

        clientFrame.getContentPane().add(clientButtonPanel, BorderLayout.SOUTH);

        clientButtonPanel.add(addClientButton);

        JButton editClientButton = new JButton("Edit Client");
        editClientButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Edit client logic
            }
        });
        clientButtonPanel.add(editClientButton);

        JButton deleteClientButton = new JButton("Delete Client");
        deleteClientButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Delete client logic
            }
        });
        clientButtonPanel.add(deleteClientButton);

        clientFrame.getContentPane().add(clientButtonPanel, BorderLayout.SOUTH);

        // Create the product window
        productFrame = new JFrame("Product Operations");
        productFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        productFrame.setSize(800, 600);

        // Create product table and model
        productTableModel = new DefaultTableModel();
        productTableModel.addColumn("id");
        productTableModel.addColumn("name");
        productTableModel.addColumn("stock");
        productTable = new JTable(productTableModel);
        JScrollPane productScrollPane = new JScrollPane(productTable);

        productFrame.getContentPane().add(productScrollPane);

        // Create product operation buttons
        JPanel productButtonPanel = new JPanel();

        JButton addProductButton = new JButton("Add Product");
        addProductButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Add new product logic
            }
        });

        JButton backButton2 = new JButton("Back");
        backButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                productFrame.setVisible(false);
                startFrame.setVisible(true);
            }
        });
        productButtonPanel.add(backButton2);

        productFrame.getContentPane().add(productButtonPanel, BorderLayout.SOUTH);

        productButtonPanel.add(addProductButton);

        JButton editProductButton = new JButton("Edit Product");
        editProductButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Edit product logic
            }
        });
        productButtonPanel.add(editProductButton);

        JButton deleteProductButton = new JButton("Delete Product");
        deleteProductButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Delete product logic
            }
        });
        productButtonPanel.add(deleteProductButton);

        productFrame.getContentPane().add(productButtonPanel, BorderLayout.SOUTH);

        // Create the order window
        orderFrame = new JFrame("Create Product Order");
        orderFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        orderFrame.setSize(400, 200);

        JPanel orderPanel = new JPanel();

        JComboBox<String> productComboBox = new JComboBox<>();
        // Populate productComboBox with existing products

        JComboBox<String> clientComboBox = new JComboBox<>();
        // Populate clientComboBox with existing clients

        JTextField quantityTextField = new JTextField(10);

        JButton createOrderButton = new JButton("Create Order");
        createOrderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Create order logic
            }
        });


        JButton backButton3 = new JButton("Back");
        backButton3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                orderFrame.setVisible(false);
                startFrame.setVisible(true);
            }
        });
        orderPanel.add(backButton3);

        orderFrame.getContentPane().add(orderPanel);

        orderPanel.add(new JLabel("Product:"));
        orderPanel.add(productComboBox);
        orderPanel.add(new JLabel("Client:"));
        orderPanel.add(clientComboBox);
        orderPanel.add(new JLabel("Quantity:"));
        orderPanel.add(quantityTextField);
        orderPanel.add(createOrderButton);

        orderFrame.getContentPane().add(orderPanel);

        // Display the windows
        //clientFrame.setVisible(true);
        //productFrame.setVisible(true);
        //orderFrame.setVisible(true);

        //creare pagina de start
        startFrame = new JFrame("Start Page");
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startFrame.setSize(500, 500);

        startFrame.setLayout(new GridLayout(3, 1));

        JButton clientButton = new JButton("Clients");
        clientButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startFrame.dispose();
                clientFrame.setVisible(true);
            }
        });

        clientButton.setForeground(new Color(0, 25, 51));
        clientButton.setFont(new Font("Algerian", Font.BOLD, 16));
        clientButton.setBackground(new Color(164, 220, 213));
        startFrame.add(clientButton);

        JButton productButton = new JButton("Products");
        productButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startFrame.dispose();
                productFrame.setVisible(true);
            }
        });
        productButton.setForeground(new Color(0, 25, 51));
        productButton.setFont(new Font("Algerian", Font.BOLD, 16));
        productButton.setBackground(new Color(142, 232, 220));
        startFrame.add(productButton);

        JButton orderButton = new JButton("Orders");
        orderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startFrame.dispose();
                orderFrame.setVisible(true);
            }
        });
        orderButton.setForeground(new Color(0, 25, 51));
        orderButton.setFont(new Font("Algerian", Font.BOLD, 16));
        orderButton.setBackground(new Color(164, 220, 213));
        startFrame.add(orderButton);

        startFrame.setVisible(true);
    }

}

