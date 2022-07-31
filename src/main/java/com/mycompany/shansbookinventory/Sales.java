/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.shansbookinventory;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nano
 */
public class Sales extends javax.swing.JFrame {

    /**
     * Creates new form Sales
     */
//    static boolean isPurchaseComplete = true;
    static String updateQuantity, updateProductCode;

    public Sales() {
        initComponents();
        loadProducts();
        lblCashier.setText(MainMenu.name);
        lblSalesID.setText(String.valueOf(genSalesID()));
        updateSalesTable();
        cmbProduct.requestFocus();
        btnUpdate.setEnabled(false);
        btnDelete.setEnabled(false);
        jPanel1.setVisible(false);

    }

    Connection con = DBConnect.connect();
    PreparedStatement pst;
    ResultSet rs;
    DefaultTableModel defTable;

    void loadCustomers() {
        try {
            pst = con.prepareStatement("SELECT Name FROM Customers ORDER  BY Name ASC");
            rs = pst.executeQuery();
            Vector v = new Vector();

            while (rs.next()) {
                v.add(rs.getString("Name"));

                DefaultComboBoxModel defCmb = new DefaultComboBoxModel(v);
                cmbCustomers.setModel(defCmb);
                cmbCustomers.setSelectedItem(null);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Sales.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void loadProducts() {
        try {
            pst = con.prepareStatement("SELECT Name FROM products ORDER  BY Name ASC");
            rs = pst.executeQuery();
            Vector v = new Vector();

            while (rs.next()) {
                v.add(rs.getString("Name"));

                DefaultComboBoxModel defCmb = new DefaultComboBoxModel(v);
                cmbProduct.setModel(defCmb);
                cmbProduct.setSelectedItem(null);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
//            Logger.getLogger(Sales.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    int genSalesID() {
        int salesID = 0;
        try {
            pst = con.prepareStatement("SELECT MAX(SalesID) FROM sales");
            rs = pst.executeQuery();
            while (rs.next()) {
                salesID = rs.getInt("MAX(SalesID)");
                //JOptionPane.showMessageDialog(null,salesID );
            }
        } catch (SQLException ex) {
            Logger.getLogger(Sales.class.getName()).log(Level.SEVERE, null, ex);
        }
        return salesID + 1;
    }

    int getUserID() {
        int userID = 0;
        try {
            pst = con.prepareStatement("Select UserID from users where name=?");
            pst.setString(1, MainMenu.name);
//            pst = con.prepareStatement("Select UserID from users where username=?");
//            pst.setString(1, Login.username);
            rs = pst.executeQuery();
            while (rs.next()) {
                userID = rs.getInt("UserID");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Sales.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userID;
    }

    void updateSalesTable() {
        try {
            int salesID = genSalesID();
            int userID = getUserID();
            pst = con.prepareStatement("Insert into sales(SalesID,UserID) values(?,?)");
            pst.setString(1, String.valueOf(salesID));
            pst.setString(2, String.valueOf(userID));
            pst.executeUpdate();
//            JOptionPane.showMessageDialog(null, "salesTable updated");
        } catch (SQLException ex) {
            Logger.getLogger(Sales.class.getName()).log(Level.SEVERE, null, ex);
//            JOptionPane.showMessageDialog(null, ex);

        }
    }

    String getProductCode() {
        String productCode = null;
        try {
            pst = con.prepareStatement("select ProductCode from products where name=?");
            pst.setString(1, cmbProduct.getSelectedItem().toString());
            rs = pst.executeQuery();
            while (rs.next()) {
                productCode = rs.getString("ProductCode");
                //JOptionPane.showMessageDialog(null,SupplierID );
            }
        } catch (SQLException ex) {
            Logger.getLogger(Sales.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException ignored) {
        }

        return productCode;
    }

    boolean isOnSale() {
        boolean a = false;
        try {
            pst = con.prepareStatement("Select * from solditems where salesid=?");
            pst.setString(1, lblSalesID.getText());
            rs = pst.executeQuery();
            while (rs.next()) {
                a = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Sales.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }

    void stockDetails() {
        String stock = null;
        if (cmbProduct.getSelectedItem() != null) {
            try {
                String productCode = getProductCode();
                pst = con.prepareStatement("SELECT Stock FROM products WHERE ProductCode=?");
                pst.setString(1, productCode);
                rs = pst.executeQuery();
                while (rs.next()) {
                    stock = rs.getString("Stock");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Sales.class.getName()).log(Level.SEVERE, null, ex);
            }
            lblStock.setText(stock);
        } else {
            lblStock.setText("");
        }
    }

    float totalSale() {
        float total = 0.0f;
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            total += Float.parseFloat(jTable1.getValueAt(i, 4) + "");
        }
        lblTotalSale.setText("Rs. " + String.valueOf(total));
        return total;
    }

    void checkAvailability(String productCode) {
        try {
            int stock = 0;
            pst = con.prepareStatement("SELECT Stock FROM products WHERE ProductCode=?");
            pst.setString(1, productCode);
            rs = pst.executeQuery();
            while (rs.next()) {
                stock = rs.getInt("Stock");
            }
            if (stock == 0) {
                JOptionPane.showMessageDialog(null, "This Product Is Out Of Stock");
            } else if (Integer.parseInt(txtQuantity.getText()) > stock) {
                JOptionPane.showMessageDialog(null, "The Quantity Is Higher Than Availabe Stock");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Sales.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        lblSalesID = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblCashier = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cmbProduct = new javax.swing.JComboBox<>();
        txtQuantity = new javax.swing.JTextField();
        lblUnitPrice = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        btnCheckout = new javax.swing.JButton();
        btnHome = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        lblStock = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblTotalSale = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        cmbCustomers = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        txtPaid = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        lblBalance = new javax.swing.JLabel();
        btnCompleteSale = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jLabel1.setText("Sales ID:");

        jLabel3.setText("Cashier:");

        jLabel6.setText("Product:");

        jLabel7.setText("Quantity:");

        jLabel8.setText("Unit Price:");

        jLabel9.setText("Total:");

        cmbProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbProductActionPerformed(evt);
            }
        });

        txtQuantity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtQuantityKeyReleased(evt);
            }
        });

        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Poduct Name", "Quantity", "Unit Price", "Total"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel2.setText("Sold Items");

        btnCheckout.setText("Check Out");
        btnCheckout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckoutActionPerformed(evt);
            }
        });

        btnHome.setText("Home");
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        jLabel4.setText("Current Stock :");

        jLabel10.setText("Total Sale :");

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel5.setText("Customer:");

        jButton1.setText("Cancel");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel11.setText("Paid Amount");

        txtPaid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPaidKeyReleased(evt);
            }
        });

        jLabel12.setText("Balance :");

        btnCompleteSale.setText("Complete Sale");
        btnCompleteSale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCompleteSaleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel12)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(14, 14, 14)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel11)
                                .addComponent(jLabel5))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(cmbCustomers, 0, 155, Short.MAX_VALUE)
                                .addComponent(txtPaid)
                                .addComponent(lblBalance, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(btnCompleteSale)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cmbCustomers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtPaid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(lblBalance, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(btnCompleteSale)
                .addGap(28, 28, 28)
                .addComponent(jButton1)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(484, 484, 484)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnCheckout)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(72, 72, 72)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lblStock, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(152, 152, 152)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblSalesID, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(124, 124, 124)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblCashier, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(20, 20, 20))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(123, 123, 123)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblUnitPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(78, 78, 78)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(129, 129, 129)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(lblTotalSale, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(btnUpdate)
                        .addGap(78, 78, 78)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnHome, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32))))
            .addGroup(layout.createSequentialGroup()
                .addGap(134, 134, 134)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(cmbProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblSalesID, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)))
                    .addComponent(lblCashier, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(cmbProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(lblStock, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(lblUnitPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(lblTotalSale, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnUpdate)
                    .addComponent(btnDelete)
                    .addComponent(btnHome, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClear))
                .addGap(2, 2, 2)
                .addComponent(jLabel2)
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCheckout)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(66, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        if (isOnSale()) {
            int dialogResult = JOptionPane.showConfirmDialog(null, "This sale is not completed, are you sure you want to cancel this sale?", "Warning", JOptionPane.YES_NO_OPTION);
            if (dialogResult == JOptionPane.YES_OPTION) {
                try {

                    pst = con.prepareStatement("select * from solditems where salesid=?");
                    pst.setString(1, lblSalesID.getText());
                    rs = pst.executeQuery();
                    while (rs.next()) {
///                       update the stocks to previous state
                        int stock = rs.getInt("Quantity");
                        int productcode = rs.getInt("ProductCode");
                        pst = con.prepareStatement("Update products set stock=stock+? where productcode=?");
                        pst.setInt(1, stock);
                        pst.setInt(2, productcode);
                        pst.executeUpdate();
//                        JOptionPane.showMessageDialog(null, "Stocks updated to previous state");
                    }
                    pst = con.prepareStatement("Delete from solditems where SalesID=?");
                    pst.setString(1, lblSalesID.getText());
                    pst.executeUpdate();
//                    JOptionPane.showMessageDialog(null, "SoldItems successfully deleted");

                    pst = con.prepareStatement("Delete from sales where SalesID=?");
                    pst.setString(1, lblSalesID.getText());
                    pst.executeUpdate();
//                    JOptionPane.showMessageDialog(null, "SalesID successfully deleted");

                    this.setVisible(false);
                    new MainMenu(MainMenu.name, MainMenu.role).setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Sales.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            try {
                pst = con.prepareStatement("Delete from sales where SalesID=?");
                pst.setString(1, lblSalesID.getText());
                pst.executeUpdate();
//                JOptionPane.showMessageDialog(null, "SalesID successfully deleted");
                this.setVisible(false);
                new MainMenu(MainMenu.name, MainMenu.role).setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(Sales.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnHomeActionPerformed

    private void txtQuantityKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQuantityKeyReleased
        if (txtQuantity.getText().isEmpty() || cmbProduct.getSelectedItem() == null) {
            lblTotal.setText("");
        } else {
            try {
                pst = con.prepareStatement("select SellingPrice from products where name=?");
                pst.setString(1, cmbProduct.getSelectedItem().toString());
                rs = pst.executeQuery();
                while (rs.next()) {
                    float sellingPrice = rs.getFloat("SellingPrice");
                    lblUnitPrice.setText(String.valueOf("Rs. " + sellingPrice));
                    float totalPrice = Integer.parseInt(txtQuantity.getText()) * sellingPrice;
                    lblTotal.setText(String.valueOf("Rs. " + totalPrice));
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
//                Logger.getLogger(Sales.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Only numbers are allowed");
            }
        }
    }//GEN-LAST:event_txtQuantityKeyReleased

    private void cmbProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbProductActionPerformed
        if (cmbProduct.getSelectedItem() != null && txtQuantity.getText().isBlank()) {
            try {
                pst = con.prepareStatement("select SellingPrice from products where name=?");
                pst.setString(1, cmbProduct.getSelectedItem().toString());
                rs = pst.executeQuery();
                while (rs.next()) {
                    float sellingPrice = rs.getFloat("SellingPrice");
                    lblUnitPrice.setText("Rs. " + String.valueOf(rs.getFloat("SellingPrice")));
                    /*float totalPrice = Integer.parseInt(txtQuantity.getText()) * sellingPrice;
                    lblTotal.setText(String.valueOf(totalPrice));*/
                    stockDetails();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Sales.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (cmbProduct.getSelectedItem() != null && txtQuantity.getText().isBlank() == false) {
            try {
                pst = con.prepareStatement("select SellingPrice from products where name=?");
                pst.setString(1, cmbProduct.getSelectedItem().toString());
                rs = pst.executeQuery();
                while (rs.next()) {
                    float sellingPrice = rs.getFloat("SellingPrice");
                    lblUnitPrice.setText("Rs. " + String.valueOf(rs.getFloat("SellingPrice")));
                    float totalPrice = Integer.parseInt(txtQuantity.getText()) * sellingPrice;
                    lblTotal.setText("Rs. " + String.valueOf(totalPrice));

                    stockDetails();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Sales.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_cmbProductActionPerformed

    boolean checkDuplication() {
        boolean a = false;
        try {
            String productCode = getProductCode();
            pst = con.prepareStatement("select ProductCode from solditems where ProductCode=? and SalesID=?");
            pst.setString(1, productCode);
            pst.setInt(2, Integer.parseInt(lblSalesID.getText()));
            rs = pst.executeQuery();
            while (rs.next()) {
                a = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Sales.class.getName()).log(Level.SEVERE, null, ex);
        }

        return a;

    }

    boolean checkDuplicationForUpdate() {
        boolean a = false;
        try {
            String productCode = getProductCode();
            int selectedRow = jTable1.getSelectedRow();
            int id = Integer.parseInt(defTable.getValueAt(selectedRow, 0).toString());
            pst = con.prepareStatement("select ProductCode from solditems where ProductCode=? and SalesID=? and id<>?");
            pst.setString(1, productCode);
            pst.setInt(2, Integer.parseInt(lblSalesID.getText()));
            pst.setInt(3, id);

            rs = pst.executeQuery();
            while (rs.next()) {
                a = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Sales.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;

    }


    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        if (getProductCode() == null) {
            JOptionPane.showMessageDialog(null, "Please Select A Product");
        } else if (txtQuantity.getText().isBlank()) {
            JOptionPane.showMessageDialog(null, "Please Enter the Quantity");
        } else if (checkDuplication()) {
            JOptionPane.showMessageDialog(null, "This Product Already Added To The Cart");

        } else {
            try {

                String productCode = getProductCode();
//                checkAvailability(productCode);
                int stock = 0;
                pst = con.prepareStatement("SELECT Stock FROM products WHERE ProductCode=?");
                pst.setString(1, productCode);
                rs = pst.executeQuery();
                while (rs.next()) {
                    stock = rs.getInt("Stock");
                }
                if (stock == 0) {
                    JOptionPane.showMessageDialog(null, "This Product Is Out Of Stock");
                } else if (Integer.parseInt(txtQuantity.getText()) > stock) {
                    JOptionPane.showMessageDialog(null, "The Quantity Is Higher Than Availabe Stock");
                } else {
                    pst = con.prepareStatement("Insert into solditems (SalesID,ProductCode,Quantity) values (?,?,?)");
                    pst.setInt(1, Integer.parseInt(lblSalesID.getText()));
                    pst.setString(2, productCode);
                    pst.setString(3, txtQuantity.getText());
                    pst.executeUpdate();

                    pst = con.prepareStatement("update products set stock=stock-? where ProductCode=?");
                    pst.setString(1, txtQuantity.getText());
                    pst.setString(2, productCode);
                    pst.executeUpdate();
                    cmbProduct.setSelectedItem(null);
                    txtQuantity.setText("");
                    loadTable();
                    totalSale();
                    cmbProduct.requestFocus();
                    lblUnitPrice.setText("");
                    lblTotal.setText("");
                    lblStock.setText("");
                }
            } catch (SQLException ex) {
//                if (ex instanceof MysqlDataTruncation)
//                JOptionPane.showMessageDialog(null, "Please enter the Quantity");
                Logger.getLogger(Sales.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnAddActionPerformed

    void loadTable() {
        try {
            pst = con.prepareStatement("SELECT solditems.ID,products.Name,solditems.Quantity,products.SellingPrice,solditems.Quantity*products.SellingPrice AS Total FROM solditems,products WHERE solditems.ProductCode=products.ProductCode and  SalesID=?");
            pst.setInt(1, Integer.valueOf(lblSalesID.getText()));
            rs = pst.executeQuery();
            ResultSetMetaData rss = rs.getMetaData();
            int noOfColumn = rss.getColumnCount();
            defTable = (DefaultTableModel) jTable1.getModel();

            defTable.setRowCount(0);
            while (rs.next()) {
                Vector vec = new Vector();
                for (int a = 1; a <= noOfColumn; a++) {
                    vec.add(rs.getString("ID"));
                    vec.add(rs.getString("Name"));
                    vec.add(rs.getString("Quantity"));
                    vec.add(rs.getString("SellingPrice"));
                    vec.add(rs.getString("Total"));

                }
                defTable.addRow(vec);

            }
        } catch (SQLException ex) {
            Logger.getLogger(Sales.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        cmbProduct.setSelectedItem(null);
        txtQuantity.setText("");
        lblStock.setText("");
        lblUnitPrice.setText("");
        lblTotal.setText("");
        btnAdd.setEnabled(true);
        btnUpdate.setEnabled(false);
        btnDelete.setEnabled(false);
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnCheckoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckoutActionPerformed
        loadCustomers();
        jPanel1.setVisible(true);
        cmbCustomers.requestFocus();

//        isPurchaseComplete = true;
    }//GEN-LAST:event_btnCheckoutActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

        int selectedRow = jTable1.getSelectedRow();
//        int id = Integer.parseInt(defTable.getValueAt(selectedRow, 0).toString());
        cmbProduct.setSelectedItem(defTable.getValueAt(selectedRow, 1));
        txtQuantity.setText(defTable.getValueAt(selectedRow, 2).toString());
        btnUpdate.setEnabled(true);
        btnDelete.setEnabled(true);
        btnAdd.setEnabled(false);

        updateQuantity = String.valueOf(defTable.getValueAt(selectedRow, 2));
        updateProductCode = getProductCode();
//            pst = con.prepareStatement("Update products set stock=stock+? where productcode=?");
//            pst.setString(1, updateQuantity);
//            pst.setString(2, productCode);
//            pst.executeUpdate();

    }//GEN-LAST:event_jTable1MouseClicked

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed

        if (checkDuplicationForUpdate()) {
            JOptionPane.showMessageDialog(null, "This Product Already In Cart");
        } else {
            try {
                String productCode = getProductCode();
                pst = con.prepareStatement("Update products set stock=stock+? where productcode=?");
                pst.setString(1, updateQuantity);
                pst.setString(2, updateProductCode);
                pst.executeUpdate();

                //START OF NEWLY ADDED LINE 
                int stock = 0;
                pst = con.prepareStatement("SELECT Stock FROM products WHERE ProductCode=?");
                pst.setString(1, productCode);
                rs = pst.executeQuery();
                while (rs.next()) {
                    stock = rs.getInt("Stock");
                }
                if (stock == 0) {
                    JOptionPane.showMessageDialog(null, "This Product Is Out Of Stock");
                    // * BRING BACK THE STOCKS TO PREVIOUS STATE DUE TO THE ERROR
                    pst = con.prepareStatement("Update products set stock=stock-? where productcode=?");
                    pst.setString(1, updateQuantity);
                    pst.setString(2, updateProductCode);
                    pst.executeUpdate();
                } else if (Integer.parseInt(txtQuantity.getText()) > stock) {
                    JOptionPane.showMessageDialog(null, "The Quantity Is Higher Than Availabe Stock");
                    // * BRING BACK THE STOCKS TO PREVIOUS STATE DUE TO THE ERROR
                    pst = con.prepareStatement("Update products set stock=stock-? where productcode=?");
                    pst.setString(1, updateQuantity);
                    pst.setString(2, updateProductCode);
                    pst.executeUpdate();

                } else {
                    //END OF NEWLY ADDED LINE

                    int selectedRow = jTable1.getSelectedRow();
                    int id = Integer.parseInt(defTable.getValueAt(selectedRow, 0).toString());
                    pst = con.prepareStatement("Update solditems set ProductCode=?,Quantity=? where ID=?");
                    pst.setString(1, productCode);
                    pst.setString(2, txtQuantity.getText());
                    pst.setInt(3, id);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "updated");
                    loadTable();

                    pst = con.prepareStatement("update products set stock=stock-? where ProductCode=?");
                    pst.setString(1, txtQuantity.getText());
                    pst.setString(2, productCode);
                    pst.executeUpdate();

                    txtQuantity.setText("");
                    lblUnitPrice.setText("");
                    lblTotal.setText("");
                    cmbProduct.setSelectedItem(null);
                    cmbProduct.requestFocus();
                    btnAdd.setEnabled(true);
                    btnUpdate.setEnabled(false);
                    btnDelete.setEnabled(false);
                    lblStock.setText("");
                    totalSale();
                }

            } catch (SQLException ex) {
                Logger.getLogger(Sales.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        try {
            int selectedRow = jTable1.getSelectedRow();
            int id = Integer.parseInt(defTable.getValueAt(selectedRow, 0).toString());
            pst = con.prepareStatement("Update products set stock=stock+? where name=?");
            pst.setString(1, defTable.getValueAt(selectedRow, 2).toString());
            pst.setString(2, defTable.getValueAt(selectedRow, 1).toString());
            pst.executeUpdate();
            pst = con.prepareStatement("Delete from solditems where ID=?");
            pst.setInt(1, id);
            pst.executeUpdate();
            loadTable();
            JOptionPane.showMessageDialog(null, "Product Deleted From Cart");
            cmbProduct.setSelectedItem(null);
            txtQuantity.setText("");
            btnUpdate.setEnabled(false);
            btnDelete.setEnabled(false);
            btnAdd.setEnabled(true);
            lblStock.setText("");
            lblUnitPrice.setText("");
            lblTotal.setText("");
            totalSale();
        } catch (SQLException ex) {
            Logger.getLogger(Sales.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnDeleteActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jPanel1.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtPaidKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPaidKeyReleased
        float balance = Float.parseFloat(txtPaid.getText()) - totalSale();
        lblBalance.setText("Rs. " + String.valueOf(balance));
    }//GEN-LAST:event_txtPaidKeyReleased

    String getCustomerID() {
        String customerID = null;
        try {
            pst = con.prepareStatement("select CustomerID from Customers where name=?");
            pst.setString(1, cmbCustomers.getSelectedItem().toString());
            rs = pst.executeQuery();
            while (rs.next()) {
                customerID = rs.getString("CustomerID");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Sales.class.getName()).log(Level.SEVERE, null, ex);
        }
        return customerID;
    }

    private void btnCompleteSaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompleteSaleActionPerformed
        if (cmbCustomers.getSelectedItem() == null || txtPaid.getText().isBlank()) {
            JOptionPane.showMessageDialog(null, "Please Provide All Details");
        } else {
            try {
                java.util.Date objDate = new java.util.Date();
                java.text.SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
                String strDate = date.format(objDate);

                String customerID = getCustomerID();
                pst = con.prepareStatement("Update sales set CustomerID=?,Total=?,Paid=?,Date=? where SalesID=?");
                pst.setString(1, customerID);
                pst.setFloat(2, totalSale());
                pst.setString(3, txtPaid.getText());
                pst.setString(4, strDate);
                pst.setString(5, lblSalesID.getText());
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Sales Successfully Completed");
            } catch (SQLException ex) {
                Logger.getLogger(Sales.class.getName()).log(Level.SEVERE, null, ex);
            }
            new Sales().setVisible(true);
            this.dispose();
        }

    }//GEN-LAST:event_btnCompleteSaleActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Sales.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Sales.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Sales.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Sales.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Sales().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCheckout;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnCompleteSale;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cmbCustomers;
    private javax.swing.JComboBox<String> cmbProduct;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblBalance;
    private javax.swing.JLabel lblCashier;
    private javax.swing.JLabel lblSalesID;
    private javax.swing.JLabel lblStock;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblTotalSale;
    private javax.swing.JLabel lblUnitPrice;
    private javax.swing.JTextField txtPaid;
    private javax.swing.JTextField txtQuantity;
    // End of variables declaration//GEN-END:variables
}
