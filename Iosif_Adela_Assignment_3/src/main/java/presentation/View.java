package presentation;

import dataAccessLayer.ClientDAO;
import model.Clients;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

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
        //fereastra pt client
        clientFrame = new JFrame("Client Operations");
        clientFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        clientFrame.setSize(800, 600);

        //Create client table and model
        clientTableModel = new DefaultTableModel();
        clientTableModel.addColumn("id");
        clientTableModel.addColumn("name");
        clientTableModel.addColumn("phoneNumber");
        clientTable = new JTable(clientTableModel);
        JScrollPane clientScrollPane = new JScrollPane(clientTable);

        clientFrame.getContentPane().add(clientScrollPane);

        //Butoane pt operatii pe clienti
        JPanel clientButtonPanel = new JPanel();

        JButton addClientButton = new JButton("Add Client");
        addClientButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog(clientFrame, "Enter client name:");
                String phoneNumber = JOptionPane.showInputDialog(clientFrame, "Enter client phone number:");

                //operatii baza de date
                ClientDAO cl = new ClientDAO();

                Clients c1 = new Clients();
                c1.setName(name);
                c1.setPhoneNumber(phoneNumber);
                cl.insert(c1);

                //adaugare client in lista
                clientTableModel.addRow(new Object[]{clientTableModel.getRowCount() + 1, name, phoneNumber});
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
                //update client
                int selectedRowIndex = clientTable.getSelectedRow();

                //verificam daca s-a selectat un client
                if (selectedRowIndex != -1) {
                    //ia valorile din randul curent selectat
                    int clientId = (int) clientTableModel.getValueAt(selectedRowIndex, 0);
                    String currentName = (String) clientTableModel.getValueAt(selectedRowIndex, 1);
                    String currentPhoneNumber = (String) clientTableModel.getValueAt(selectedRowIndex, 2);

                    //ferestre pentru introducere update pt nume si nr de tel
                    String updatedName = JOptionPane.showInputDialog(clientFrame, "Enter updated name:", currentName);
                    String updatedPhoneNumber = JOptionPane.showInputDialog(clientFrame, "Enter updated phone number:", currentPhoneNumber);

                    //actualizare tabel
                    clientTableModel.setValueAt(updatedName, selectedRowIndex, 1);
                    clientTableModel.setValueAt(updatedPhoneNumber, selectedRowIndex, 2);

                    //update in database
                    ClientDAO cl = new ClientDAO();

                    //update e doar pe un camp odata
                    cl.update("name", updatedName, clientId);
                    cl.update("phoneNumber", updatedPhoneNumber, clientId);

                    JOptionPane.showMessageDialog(clientFrame, "Update client done!");
                } else {
                    JOptionPane.showMessageDialog(clientFrame, "Va rog selectati un client!");
                }
            }
        });
        clientButtonPanel.add(editClientButton);

        JButton deleteClientButton = new JButton("Delete Client");
        deleteClientButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //delete client
                int selectedRowIndex = clientTable.getSelectedRow();

                //verificam daca s-a selectat un client
                if (selectedRowIndex != -1) {
                    //ia id-ul clientului din randul curent selectat
                    int clientId = (int) clientTableModel.getValueAt(selectedRowIndex, 0);

                    //stergere din interfata
                    clientTableModel.removeRow(selectedRowIndex);

                    //stergere in database
                    ClientDAO cl = new ClientDAO();
                    cl.delete(clientId);

                    JOptionPane.showMessageDialog(clientFrame, "Stergerea clientului a fost facuta cu succes!");
                } else {
                    JOptionPane.showMessageDialog(clientFrame, "Va rog selectati un client!");
                }
            }
        });
        clientButtonPanel.add(deleteClientButton);

        clientFrame.getContentPane().add(clientButtonPanel, BorderLayout.SOUTH);

        //fereastra produse
        productFrame = new JFrame("Product Operations");
        productFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        productFrame.setSize(800, 600);

        //Create product table and model
        productTableModel = new DefaultTableModel();
        productTableModel.addColumn("id");
        productTableModel.addColumn("name");
        productTableModel.addColumn("stock");
        productTable = new JTable(productTableModel);
        JScrollPane productScrollPane = new JScrollPane(productTable);

        productFrame.getContentPane().add(productScrollPane);

        //Butoane operatii produse
        JPanel productButtonPanel = new JPanel();

        JButton addProductButton = new JButton("Add Product");
        addProductButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //to doo
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
                //to doooo
            }
        });
        productButtonPanel.add(editProductButton);

        JButton deleteProductButton = new JButton("Delete Product");
        deleteProductButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //deleeteeee??
            }
        });
        productButtonPanel.add(deleteProductButton);

        productFrame.getContentPane().add(productButtonPanel, BorderLayout.SOUTH);

        //fereastra orders
        orderFrame = new JFrame("Create Product Order");
        orderFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        orderFrame.setSize(600, 400);

        JPanel orderPanel = new JPanel();

        JComboBox<String> productComboBox = new JComboBox<>();
        //populare productComboBox cu produse existente

        JComboBox<String> clientComboBox = new JComboBox<>();
        //populare clientComboBox cu clienti existenti

        JTextField quantityTextField = new JTextField(10);

        JButton createOrderButton = new JButton("Create Order");
        createOrderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //to do
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

