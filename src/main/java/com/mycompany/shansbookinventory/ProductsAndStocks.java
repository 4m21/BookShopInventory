/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.shansbookinventory;

import java.sql.*;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nano
 */
public class ProductsAndStocks extends javax.swing.JFrame {

    Connection con = DBConnect.connect();
    PreparedStatement pst;
    ResultSet rs;
    DefaultTableModel defTable;

    /**
     * Creates new form ProductsAndStocks
     */
    public ProductsAndStocks() {
        initComponents();
        loadSuppliers();
        loadSuppliersFilter();
        loadProducts();
        clearAll();
        cmbSuppliersFilter.setSelectedItem(null);
        loadTable();
        cmbProducts.setSelectedItem(null);
    }

    void loadSuppliers() {
        try {
            pst = con.prepareStatement("SELECT Name FROM Suppliers ORDER  BY Name ASC");
            rs = pst.executeQuery();
            Vector v = new Vector();

            while (rs.next()) {
                v.add(rs.getString("Name"));
            }
            DefaultComboBoxModel defCmb = new DefaultComboBoxModel(v);
            cmbSuppliers.setModel(defCmb);
            /* Alternative Method
DefaultComboBoxModel defCmb= (DefaultComboBoxModel) cmbSuppliers.getModel();
defCmb.addAll(v);
             */

        } catch (SQLException ex) {
            Logger.getLogger(ProductsAndStocks.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void loadSuppliersFilter() {
        try {
            pst = con.prepareStatement("SELECT Name FROM Suppliers ORDER  BY Name ASC");
            rs = pst.executeQuery();
            Vector v = new Vector();

            while (rs.next()) {
                v.add(rs.getString("Name"));
            }
            DefaultComboBoxModel defCmb = new DefaultComboBoxModel(v);
            cmbSuppliersFilter.setModel(defCmb);

        } catch (SQLException ex) {
            Logger.getLogger(ProductsAndStocks.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void loadProducts() {
        try {
            pst = con.prepareStatement("SELECT Name FROM products ORDER  BY Name ASC");
            rs = pst.executeQuery();
            Vector v = new Vector();

            while (rs.next()) {
                v.add(rs.getString("Name"));
            }
            DefaultComboBoxModel defCmb = new DefaultComboBoxModel(v);
            cmbProducts.setModel(defCmb);

        } catch (SQLException ex) {
            Logger.getLogger(ProductsAndStocks.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    String getSupplierID() {
        String supplierID = null;
        try {
            pst = con.prepareStatement("select SupplierID from Suppliers where name=?");
            pst.setString(1, cmbSuppliers.getSelectedItem().toString());
            rs = pst.executeQuery();
            while (rs.next()) {
                supplierID = rs.getString("SupplierID");
            }
            return supplierID;
        } catch (SQLException ex) {
            Logger.getLogger(ProductsAndStocks.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    String getSupplierIDforFilter() {
        String supplierID = null;
        try {
            pst = con.prepareStatement("select SupplierID from Suppliers where name=?");
            pst.setString(1, cmbSuppliersFilter.getSelectedItem().toString());
            rs = pst.executeQuery();
            while (rs.next()) {
                supplierID = rs.getString("SupplierID");
            }
            return supplierID;
        } catch (SQLException ex) {
            Logger.getLogger(ProductsAndStocks.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void loadTable() {
        try {
            pst = con.prepareStatement("SELECT products.ProductID,products.Name as ProductName,products.ProductCode,suppliers.Name as SupplierName,products.Stock,products.CostPrice,products.SellingPrice from products,suppliers where products.SupplierID=suppliers.SupplierID ORDER  BY ProductName ASC");
            rs = pst.executeQuery();
            ResultSetMetaData rss = rs.getMetaData();
            int noOfColumn = rss.getColumnCount();
            defTable = (DefaultTableModel) jTable1.getModel();
            defTable.setRowCount(0);
            while (rs.next()) {
                Vector vec = new Vector();
                for (int a = 1; a <= noOfColumn; a++) {
                    vec.add(rs.getString("ProductID"));
                    vec.add(rs.getString("ProductName"));
                    vec.add(rs.getString("ProductCode"));
                    vec.add(rs.getString("SupplierName"));
                    vec.add(rs.getString("Stock"));
                    vec.add(rs.getString("CostPrice"));
                    vec.add(rs.getString("SellingPrice"));

                }
                defTable.addRow(vec);
            }
            lblText.setText("List Of All Products");

        } catch (SQLException ex) {
            Logger.getLogger(ManageUsers.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void clear() {
        txtCostPrice.setText("");
        txtProductCode.setText("");
        txtProductName.setText("");
        txtQuantity.setText("");
        txtSellingPrice.setText("");
        btnEdit.setEnabled(false);
        btnDelete.setEnabled(false);
        btnAdd.setEnabled(true);
        //cmbSuppliers.setSelectedItem(null);
    }

    void clearAll() {
        txtCostPrice.setText("");
        txtProductCode.setText("");
        txtProductName.setText("");
        txtQuantity.setText("");
        txtSellingPrice.setText("");
        btnEdit.setEnabled(false);
        btnDelete.setEnabled(false);
        btnAdd.setEnabled(true);
        cmbSuppliers.setSelectedItem(null);
        jTable1.getSelectionModel().clearSelection();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cmbSuppliers = new javax.swing.JComboBox<>();
        txtProductName = new javax.swing.JTextField();
        txtProductCode = new javax.swing.JTextField();
        txtQuantity = new javax.swing.JTextField();
        txtCostPrice = new javax.swing.JTextField();
        txtSellingPrice = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        cmbSuppliersFilter = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnShowAll = new javax.swing.JButton();
        lblText = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cmbProducts = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtNewStock = new javax.swing.JTextField();
        btnStockUpdate = new javax.swing.JButton();
        lblProductCode = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setText("Add Products");

        jLabel2.setText("Supplier");

        jLabel3.setText("Product Name");

        jLabel4.setText("Product Code");

        jLabel5.setText("Quantity");

        jLabel6.setText("Cost Price");

        jLabel7.setText("Selling Price");

        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnEdit.setText("Update");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        jButton1.setText("Home");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(204, 204, 204)));

        jLabel12.setText("filter products");

        jLabel13.setText("By Suppliers :");

        cmbSuppliersFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSuppliersFilterActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product ID", "Product Name", "Product Code", "Supplier Name", "Current Stock", "Cost Price", "Selling Price"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setMinWidth(0);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(0);
            jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
        }

        btnShowAll.setText("Show All Products");
        btnShowAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowAllActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 716, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(cmbSuppliersFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnShowAll)
                        .addGap(20, 20, 20))))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(242, 242, 242)
                .addComponent(lblText, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addGap(30, 30, 30)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbSuppliersFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnShowAll, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(lblText, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(184, 184, 184)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(btnAdd)
                                        .addGap(82, 82, 82)
                                        .addComponent(btnEdit)
                                        .addGap(79, 79, 79)
                                        .addComponent(btnDelete))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtCostPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(18, 18, 18)
                                        .addComponent(cmbSuppliers, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(34, 34, 34)
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtProductName, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(33, 33, 33)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel7)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtSellingPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel4)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txtProductCode, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel5)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(95, 95, 95)
                                        .addComponent(btnClear)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(17, 17, 17))))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(cmbSuppliers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtProductName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtProductCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(txtCostPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSellingPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnEdit)
                    .addComponent(btnDelete)
                    .addComponent(btnClear)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(26, 26, 26))
        );

        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel9.setText("Select Product");

        jLabel8.setText("Update Stocks");

        cmbProducts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbProductsActionPerformed(evt);
            }
        });

        jLabel10.setText("Product Code");

        jLabel11.setText("Number Of The Products to Add");

        btnStockUpdate.setText("Add To Stock");
        btnStockUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStockUpdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(53, 53, 53)
                                    .addComponent(jLabel8))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(cmbProducts, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel9))
                                    .addGap(39, 39, 39)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addGap(10, 10, 10)
                                            .addComponent(lblProductCode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addComponent(jLabel10))))
                            .addGap(52, 52, 52))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(21, 21, 21)
                            .addComponent(jLabel11)
                            .addGap(18, 18, 18)
                            .addComponent(txtNewStock)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(114, 114, 114)
                        .addComponent(btnStockUpdate)))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addGap(43, 43, 43)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbProducts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblProductCode, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtNewStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addComponent(btnStockUpdate)
                .addContainerGap(269, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        if (txtProductName.getText().isBlank() || txtProductCode.getText().isBlank() || txtCostPrice.getText().isBlank() || txtSellingPrice.getText().isBlank() || txtQuantity.getText().isBlank()) {
            JOptionPane.showMessageDialog(null, "Please provide all details");
        } else {
            try {
                String supplierID = getSupplierID();
                pst = con.prepareStatement("insert into products(Name,ProductCode,SupplierID,Stock,CostPrice,SellingPrice) values(?,?,?,?,?,?)");
                pst.setString(1, txtProductName.getText());
                pst.setString(2, txtProductCode.getText());
                pst.setString(3, supplierID);
                pst.setString(4, txtQuantity.getText());
                pst.setString(5, txtCostPrice.getText());
                pst.setString(6, txtSellingPrice.getText());
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Product Successfully Added");
                loadTable();
                clear();
                loadProducts();
            } catch (SQLException ex) {
                if (ex instanceof java.sql.SQLIntegrityConstraintViolationException) {
                    JOptionPane.showMessageDialog(null, "This Product Code Is Already In Use");
                } else if (ex instanceof com.mysql.cj.jdbc.exceptions.MysqlDataTruncation) {
                    JOptionPane.showMessageDialog(null, "Something Error In Given Details." + "\nPlease Recheck And FIll All Fields");

                } else {
                    Logger.getLogger(ProductsAndStocks.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (NullPointerException ex) {
                JOptionPane.showMessageDialog(null, "Please Select A Supplier");
                cmbSuppliers.requestFocus();
            }
        }

    }//GEN-LAST:event_btnAddActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int selectedRow = jTable1.getSelectedRow();
        int id = Integer.parseInt(defTable.getValueAt(selectedRow, 0).toString());
        txtProductName.setText(defTable.getValueAt(selectedRow, 1).toString());
        txtProductCode.setText(defTable.getValueAt(selectedRow, 2).toString());
        cmbSuppliers.setSelectedItem(defTable.getValueAt(selectedRow, 3));
        txtQuantity.setText(defTable.getValueAt(selectedRow, 4).toString());
        txtCostPrice.setText(defTable.getValueAt(selectedRow, 5).toString());
        txtSellingPrice.setText(defTable.getValueAt(selectedRow, 6).toString());

        btnAdd.setEnabled(false);
        btnEdit.setEnabled(true);
        btnDelete.setEnabled(true);
    }//GEN-LAST:event_jTable1MouseClicked

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        clearAll();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        if (txtProductName.getText().isBlank() || txtProductCode.getText().isBlank() || txtCostPrice.getText().isBlank() || txtSellingPrice.getText().isBlank() || txtQuantity.getText().isBlank()) {
            JOptionPane.showMessageDialog(null, "Please provide all details");
        } else {
            try {
                String supplierID = getSupplierID();
                int selectedRow = jTable1.getSelectedRow();
                int id = Integer.parseInt(defTable.getValueAt(selectedRow, 0).toString());
                pst = con.prepareStatement("Update products set Name=?,ProductCode=?,SupplierID=?,Stock=?,CostPrice=?,SellingPrice=? where ProductID=?");
                pst.setString(1, txtProductName.getText());
                pst.setString(2, txtProductCode.getText());
                pst.setString(3, supplierID);
                pst.setString(4, txtQuantity.getText());
                pst.setString(5, txtCostPrice.getText());
                pst.setString(6, txtSellingPrice.getText());
                pst.setInt(7, id);

                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Product Successfully Updated");
                loadTable();
                clearAll();
                loadProducts();

            } catch (SQLException ex) {
                if (ex instanceof java.sql.SQLIntegrityConstraintViolationException) {
                    JOptionPane.showMessageDialog(null, "This Product Code Is Already In Use");
                } else if (ex instanceof com.mysql.cj.jdbc.exceptions.MysqlDataTruncation) {
                    JOptionPane.showMessageDialog(null, "Something Error In Given Details." + "\nPlease Recheck And FIll All Fields");

                } else {
                    Logger.getLogger(ProductsAndStocks.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (NullPointerException ex) {
                JOptionPane.showMessageDialog(null, "Please Select A Supplier");
            }
        }

    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int selectedRow = jTable1.getSelectedRow();
        int id = Integer.parseInt(defTable.getValueAt(selectedRow, 0).toString());

        int dialogResult = JOptionPane.showConfirmDialog(null, "Do you want to Delete this Product?", "Warning", JOptionPane.YES_NO_OPTION);
        if (dialogResult == JOptionPane.YES_OPTION) {
            try {
                pst = con.prepareStatement("Delete from products where ProductID=?");
                pst.setInt(1, id);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Product Deleted");
                loadTable();
                clearAll();
                loadProducts();

            } catch (SQLException ex) {
                if (ex instanceof java.sql.SQLIntegrityConstraintViolationException) {
                    JOptionPane.showMessageDialog(null, "Unable to to Delete\n" + "This product's details are used somewhere in this program");
                } else {
                    Logger.getLogger(ProductsAndStocks.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void cmbProductsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbProductsActionPerformed

        try {
            pst = con.prepareStatement("Select ProductCode from products where name=?");
            String product = cmbProducts.getSelectedItem().toString();
            pst.setString(1, product);
            rs = pst.executeQuery();
            while (rs.next()) {
                lblProductCode.setText(rs.getString("ProductCode"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductsAndStocks.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException ignored) {
        }
    }//GEN-LAST:event_cmbProductsActionPerformed

    private void btnStockUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStockUpdateActionPerformed
        try {
            pst = con.prepareStatement("update products set stock=stock+? where ProductCode=?");
            pst.setString(1, txtNewStock.getText());
            pst.setString(2, lblProductCode.getText());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Stock Updated");
            loadTable();
            txtNewStock.setText("");
            lblProductCode.setText("");
            cmbProducts.setSelectedItem(null);
        } catch (SQLException ex) {
            Logger.getLogger(ProductsAndStocks.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnStockUpdateActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new MainMenu(MainMenu.name, MainMenu.role).setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cmbSuppliersFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSuppliersFilterActionPerformed
        try {
            String supplierID = getSupplierIDforFilter();
            pst = con.prepareStatement("SELECT products.ProductID,products.Name as ProductName,products.ProductCode,suppliers.Name as SupplierName,products.Stock,products.CostPrice,products.SellingPrice from products,suppliers where products.SupplierID=suppliers.SupplierID AND products.SupplierID = ? ORDER  BY ProductName ASC;");
            pst.setString(1, supplierID);
            rs = pst.executeQuery();
            ResultSetMetaData rss = rs.getMetaData();
            int noOfColumn = rss.getColumnCount();
            defTable = (DefaultTableModel) jTable1.getModel();
            defTable.setRowCount(0);
            while (rs.next()) {
                Vector vec = new Vector();
                for (int a = 1; a <= noOfColumn; a++) {
                    vec.add(rs.getString("ProductID"));
                    vec.add(rs.getString("ProductName"));
                    vec.add(rs.getString("ProductCode"));
                    vec.add(rs.getString("SupplierName"));
                    vec.add(rs.getString("Stock"));
                    vec.add(rs.getString("CostPrice"));
                    vec.add(rs.getString("SellingPrice"));

                }
                defTable.addRow(vec);
            }
            lblText.setText("Products From " + cmbSuppliersFilter.getSelectedItem());
        } catch (SQLException ex) {
            Logger.getLogger(ProductsAndStocks.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException ignored) {
        }
    }//GEN-LAST:event_cmbSuppliersFilterActionPerformed

    private void btnShowAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowAllActionPerformed
        loadTable();
        cmbSuppliersFilter.setSelectedItem(null);
    }//GEN-LAST:event_btnShowAllActionPerformed

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
            java.util.logging.Logger.getLogger(ProductsAndStocks.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProductsAndStocks.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProductsAndStocks.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProductsAndStocks.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProductsAndStocks().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnShowAll;
    private javax.swing.JButton btnStockUpdate;
    private javax.swing.JComboBox<String> cmbProducts;
    private javax.swing.JComboBox<String> cmbSuppliers;
    private javax.swing.JComboBox<String> cmbSuppliersFilter;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblProductCode;
    private javax.swing.JLabel lblText;
    private javax.swing.JTextField txtCostPrice;
    private javax.swing.JTextField txtNewStock;
    private javax.swing.JTextField txtProductCode;
    private javax.swing.JTextField txtProductName;
    private javax.swing.JTextField txtQuantity;
    private javax.swing.JTextField txtSellingPrice;
    // End of variables declaration//GEN-END:variables
}
