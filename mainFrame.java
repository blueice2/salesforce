/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package salesforce;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author NNAMDI
 */
public  class mainFrame extends javax.swing.JFrame {

    /**
     * Creates new form mainFrame
     */
    int table_number = 0; // For tracking table entries
    static Connection conn; // Connection object to the database
    static ResultSet rs;  // Resultset object containing database returned data.
                          //The database is hakuna and table is salesforce.
    static mainFrame mainf = new mainFrame();
    
        // Creates the connection to the database
    public static void create_mysql_connection() {
        try {
            //Select a jdbc driver which was added to the class path
            Class.forName("com.mysql.jdbc.Driver");

            //select the database -hakuna
            //Office db
            //String myUrl = "jdbc:mysql://192.9.200.8/hakuna";
            // conn = DriverManager.getConnection(myUrl, "root", "karamazoff");
             
             //Local db
            String myUrl = "jdbc:mysql://localhost/hakuna";
            
            conn = DriverManager.getConnection(myUrl, "root", "");
            
              
        } catch (Exception e) {
            System.err.println("Got an exception");
            System.err.println(e.getMessage());
        }

    }// End of create_mysql_connection()
    
    
        // Closes the connection to the database
    public static void close_mysql_connection() {
        try {
            conn.close();
        } catch (Exception e) {

            System.err.println("Got an exception");
            System.err.println(e.getMessage());

        };
    }
    
    // to search out a database value
    public static ResultSet search(String invoice_number){
        
            try {
            //create a mysql connection
            create_mysql_connection();

            // Sql query
            String query = "SELECT * FROM `sales_force` where invoice_number = ?  ";

            // Use prepared statement to set the ball roling
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, invoice_number);
            rs = preparedStmt.executeQuery();
       
            

        } catch (Exception e) {

            System.err.println("Got an exception");
            System.err.println(e.getMessage());
          
        }
            DefaultTableModel model = new DefaultTableModel();
            Object[] rowData;
            Object[] columnsName;
            int ij = 0;
            

    return rs;
    }
    
    
    //premanent delete
    
    public static void perm_delete(String date, String job_number,
            String description, String quantity, String unit_price,
            String goods_value, String vat , String total_goods_value,
            String stamp, String delivery, String other_charges , String invoice_number, String customer){
     try {
            //create a mysql connection
            create_mysql_connection();

            
            // Sql query
            String query = "DELETE from hakuna.sales_force where  date =? and job_number =? and"
                    + " description =? and quantity = ? and unit_price =? and goods_value=? and vat=? and total_goods_value =?"
                    + "and stamp= ? and delivery =? and other_charges =? and invoice_number= ? and customer =? limit 1"
                   ;

            // Use prepared statement to set the ball roling
            PreparedStatement preparedStmt = conn.prepareStatement(query);

            preparedStmt.setString(1, date);
            preparedStmt.setString(2, job_number);
            preparedStmt.setString(3, description);
            preparedStmt.setString(4, quantity);
            preparedStmt.setString(5, unit_price);
            
             preparedStmt.setString(6, goods_value);
            preparedStmt.setString(7, vat);
            preparedStmt.setString(8, total_goods_value);
            preparedStmt.setString(9, stamp);
            preparedStmt.setString(10, delivery);

            preparedStmt.setString(11, other_charges );
            preparedStmt.setString(12, invoice_number);
            preparedStmt.setString(13, customer);
            
            preparedStmt.execute();
           JOptionPane.showMessageDialog(null, "Data Deleted");

   

        } catch (Exception e) {

            System.err.println("Got an exception");
            System.err.println(e.getMessage());
            close_mysql_connection();

           
        }
    
    }
    
        //insert values into the sales force database  
    public static void insert_sales_force(String date, String job_number,
            String description, String quantity, String unit_price,
            String goods_value, String vat , String total_goods_value,
            String stamp, String delivery, String other_charges , String invoice_number, String customer) {

        try {
            //create a mysql connection
            create_mysql_connection();

            
            // Sql query
            String query = "insert into sales_force(date,job_number,description,quantity,unit_price,"
                    + "goods_value,vat,total_goods_value,stamp,delivery,other_charges,invoice_number,customer)"
                    + "values (?,?,?,?,?,?,?,?,?,?,?,?,?)";

            // Use prepared statement to set the ball roling
            PreparedStatement preparedStmt = conn.prepareStatement(query);

            preparedStmt.setString(1, date);
            preparedStmt.setString(2, job_number);
            preparedStmt.setString(3, description);
            preparedStmt.setString(4, quantity);
            preparedStmt.setString(5, unit_price);
            
             preparedStmt.setString(6, goods_value);
            preparedStmt.setString(7, vat);
            preparedStmt.setString(8, total_goods_value);
            preparedStmt.setString(9, stamp);
            preparedStmt.setString(10, delivery);

            preparedStmt.setString(11, other_charges );
            preparedStmt.setString(12, invoice_number);
            preparedStmt.setString(13, customer);
            
            preparedStmt.execute();
           JOptionPane.showMessageDialog(null, "Data Saved");

   

        } catch (Exception e) {

            System.err.println("Got an exception");
            System.err.println(e.getMessage());
            close_mysql_connection();

           
        }
    
    } //End of insert_sales_value
    
    public mainFrame() {
        initComponents();
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
         jTextField4.setText("0.0");
         jTextField5.setText("0.00");
         jTextField6.setText("0.00");
         jTextField7.setText("0.00");
         jTextField8.setText("0.00");
         jTextField9.setText("0.00");
         jTextField9.setVisible(false);
         
         jLabel15.setText("Hello");
         jTextField10.setText("");
         jTextField11.setText("");
         
         jComboBox1.removeAllItems();
         jComboBox1.addItem("None");
         jComboBox1.addItem("Singles");
         jComboBox1.addItem("Copies");
         jComboBox1.addItem("Books");
        
         
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sales Rep");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Date");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Job No:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Invoice No:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Item Desc:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Quantity:");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Unit Price:");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("Stamp Duty:");

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setText("Delivery Charge:");

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel18.setText("Other Charge:");

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel20.setText("VAT:");

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel22.setText("Customer:");

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        jTextField1.setText("jTextField1");
        jTextField1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextField1MousePressed(evt);
            }
        });

        jTextField2.setText("jTextField1");
        jTextField2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextField2MousePressed(evt);
            }
        });

        jTextField3.setText("jTextField1");
        jTextField3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextField3MousePressed(evt);
            }
        });

        jTextField4.setText("jTextField1");
        jTextField4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextField4MousePressed(evt);
            }
        });

        jTextField5.setText("jTextField1");
        jTextField5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextField5MousePressed(evt);
            }
        });

        jTextField6.setText("jTextField1");
        jTextField6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextField6MousePressed(evt);
            }
        });

        jTextField7.setText("jTextField1");
        jTextField7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextField7MousePressed(evt);
            }
        });

        jTextField8.setText("jTextField1");
        jTextField8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextField8MousePressed(evt);
            }
        });

        jTextField9.setText("jTextField1");
        jTextField9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextField9MousePressed(evt);
            }
        });

        jLabel1.setText("Unit");

        jButton2.setText("Search");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setText("Stamp Duty:");

        jTextField10.setText("jTextField1");
        jTextField10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextField10MousePressed(evt);
            }
        });

        jTextField11.setText("jTextField1");
        jTextField11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextField11MousePressed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(276, 661, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel22)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(jLabel15))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addGap(27, 27, 27))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(18, 18, 18)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton2))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel20))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jCheckBox1)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel14))
                                .addGap(34, 34, 34)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel8)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel10)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel1)
                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel22)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Date", "Job Number", "Line Item Desc", "Qty", "Unit Price", "Goods Value", "VAT", "Total Goods Value", "Stamp Duty", "Deivery Charges", "Other Charges"
            }
        ));
        jScrollPane3.setViewportView(jTable1);

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jButton1.setText("Add");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton1MousePressed(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setText("Delete");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Cancel");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("View Invoice");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Save");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Close");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("Perm. Delete");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addGap(18, 18, 18)
                .addComponent(jButton8)
                .addGap(32, 32, 32)
                .addComponent(jButton4)
                .addGap(18, 18, 18)
                .addComponent(jButton5)
                .addGap(18, 18, 18)
                .addComponent(jButton6)
                .addGap(18, 18, 18)
                .addComponent(jButton7)
                .addContainerGap(85, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton5)
                    .addComponent(jButton6)
                    .addComponent(jButton7)
                    .addComponent(jButton8))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 8, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField4MousePressed
jTextField4.setText("");        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4MousePressed

    private void jTextField5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField5MousePressed
     jTextField5.setText("");    // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5MousePressed

    private void jTextField6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField6MousePressed
        jTextField6.setText("");  // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6MousePressed

    private void jTextField7MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField7MousePressed
       jTextField7.setText("");  // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7MousePressed

    private void jTextField8MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField8MousePressed
 jTextField8.setText("");        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField8MousePressed

    private void jTextField9MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField9MousePressed
 jTextField9.setText("");        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField9MousePressed

    private void jTextField3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField3MousePressed
 jTextField3.setText("");        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3MousePressed

    private void jTextField2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField2MousePressed
 jTextField2.setText("");        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2MousePressed

    private void jTextField1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField1MousePressed
 jTextField1.setText("");        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1MousePressed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
               jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
         jTextField4.setText("0.0");
         jTextField5.setText("0.00");
         jTextField6.setText("0.00");
         jTextField7.setText("0.00");
         jTextField8.setText("0.00");
         jTextField9.setText("0.00");
         jTextArea1.setText("");
         jTextArea2.setText("");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
System.exit(0);// TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1MousePressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        double goods_value = Double.parseDouble(jTextField4.getText())*Double.parseDouble(jTextField4.getText());
        //double total_value = 0;
        
        double total_value = goods_value + Double.parseDouble(jTextField9.getText()) + Double.parseDouble(jTextField6.getText()) 
                +Double.parseDouble(jTextField7.getText()) + Double.parseDouble(jTextField8.getText());
        
        jTable1.setValueAt(jTextField1.getText(), table_number, 0);// date
        jTable1.setValueAt(jTextField2.getText(), table_number, 1);//Job Number
        jTable1.setValueAt(jTextArea1.getText(), table_number, 2); // Line Item Desc
        jTable1.setValueAt(jTextField4.getText(), table_number, 3); //Qty
        jTable1.setValueAt(jTextField5.getText(), table_number, 4);// Unit Price
        jTable1.setValueAt(goods_value, table_number, 5);// Goods value*
        jTable1.setValueAt(jTextField9.getText(), table_number, 6);//VAT 
        jTable1.setValueAt(total_value, table_number, 7);//Total goods value (Goods value + VAT)*
        jTable1.setValueAt(jTextField6.getText(), table_number, 8);//Stamp Duty
        jTable1.setValueAt(jTextField7.getText(), table_number, 9);//Deliver Charge
        jTable1.setValueAt(jTextField8.getText(), table_number, 10);//Other Charges
        
        table_number++;
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
        int selected_row = jTable1.getSelectedRow();
        model.removeRow(jTable1.getSelectedRow());
        jTable1.repaint();
        table_number--;
        model.setRowCount(11);
        for (int i = 0; i< selected_row ; i++)
        {
           // model.removeRow(jTable1.getSelectedRow());
        
        }
        
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
       // ViewInvoice view = new ViewInvoice(String customer,String date,String jobnumber,JTable jtableview);
       
        ViewInvoice view = new ViewInvoice(jComboBox1,jCheckBox1,jTextArea2.getText(), jTextField1.getText(), jTextField2.getText(), jTable1, jTextField10.getText(), jTextField11.getText());
        view.setDefaultCloseOperation(HIDE_ON_CLOSE);
        view.setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        //To save to database
        String goods ="";
        
        
                try{
  for(int i = 0; i<jTable1.getRowCount(); i++){
      
     String date = jTable1.getValueAt(i, 0).toString();
     String  job_number = jTable1.getValueAt(i, 1).toString();
     String description = jTable1.getValueAt(i, 2).toString();
     String quantity = jTable1.getValueAt(i, 3).toString();
     String unit_price= jTable1.getValueAt(i, 4).toString();
     
     String  goods_value = jTable1.getValueAt(i, 5).toString();
     goods =  goods_value;
     String  vat = jTable1.getValueAt(i, 6).toString();
     
     if(jCheckBox1.isSelected()){  vat = "isselected";}
     else {vat = "unselected";}
     
     String total_goods_value = jTable1.getValueAt(i, 7).toString();
     String stamp = jTable1.getValueAt(i, 8).toString();
     String delivery= jTable1.getValueAt(i, 9).toString();
     
     String  other_charges = jTable1.getValueAt(i, 10).toString();
     String   invoice_number = jTextField3.getText();
     String customer = jTextArea2.getText();

              
    insert_sales_force(date,  job_number,
             description,  quantity,  unit_price,
            goods_value,  vat ,  total_goods_value,
             stamp,  delivery,  other_charges ,  invoice_number, customer);
    
 jLabel15.setText("Content of table saved");
 close_mysql_connection();
    }
  
  
        }catch(Exception e){e.printStackTrace();
       
        }
  
     
     
     
      
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        // TO search for an invoice
        jLabel15.setText("You are currently in search mode");
      ResultSet rs =  search( jTextField3.getText());
      
      int ij = 0;
      Object[] rowData;
      Object[] columnsName;
      
        columnsName = new Object[11];

        columnsName[0] = "Date";
        columnsName[1] = "Job Number";
        columnsName[2] = "Line Item Description";
        columnsName[3] = "Quantity";
        columnsName[4] = "Unit Price";
        columnsName[5] = "Goods Value";
        columnsName[6] = "VAT";
        columnsName[7] = "Total Goods Value";
        columnsName[8] = "Stamp Duty";
        columnsName[9] = "Delivery Charges";
        columnsName[10] = "Other Charges";
        
        
        
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnsName);
        rowData = new Object[11];
        model.setRowCount(0);
       
      
                    try{
                while (rs.next()) {
                 
                rowData[0] = rs.getString("date");
                jTextField1.setText(rs.getString("date"));
                rowData[1] = rs.getString("job_number");
                jTextField2.setText(rs.getString("job_number"));
                rowData[2] = rs.getString("description");
                rowData[3] = rs.getString("quantity");
                rowData[4] = rs.getString("unit_price");
                rowData[5] = rs.getString("goods_value");
                rowData[6] = rs.getString("vat");
                rowData[7] = rs.getString("total_goods_value");
                rowData[8] = rs.getString("stamp");
                
                rowData[9] = rs.getString("delivery");
                rowData[10] = rs.getString("other_charges");
                
                model.addRow(rowData);
                ij++;
                
                 jTextArea2.setText(rs.getString("customer"));
                 jTextField3.setText(rs.getString("invoice_number"));
               
              

            }
                   //jTable1.setModel(model);
             //jTable1.revalidate();
             close_mysql_connection();
            }
            catch(Exception e){e.printStackTrace();}
                    jTable1.setShowGrid(false);
                    jTable1.setModel(model);
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField10MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField10MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField10MousePressed

    private void jTextField11MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField11MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField11MousePressed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here: 
         String date = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString();
         String job_number = jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString();
         String description = jTable1.getValueAt(jTable1.getSelectedRow(), 2).toString();
         String quantity = jTable1.getValueAt(jTable1.getSelectedRow(), 3).toString();
         String unit_price = jTable1.getValueAt(jTable1.getSelectedRow(), 4).toString();
         String goods_value = jTable1.getValueAt(jTable1.getSelectedRow(), 5).toString();
         String vat = jTable1.getValueAt(jTable1.getSelectedRow(), 6).toString();
         String total_goods_value = jTable1.getValueAt(jTable1.getSelectedRow(), 7).toString();
         String stamp = jTable1.getValueAt(jTable1.getSelectedRow(), 8).toString();
         String delivery = jTable1.getValueAt(jTable1.getSelectedRow(), 9).toString();
         String other_charges = jTable1.getValueAt(jTable1.getSelectedRow(), 10).toString();
         String invoice_number = jTextField3.getText();
         String customer = jTextArea2.getText();
         try{
       perm_delete(date, job_number, description, quantity, unit_price, goods_value, vat, total_goods_value, stamp, delivery, other_charges, invoice_number, customer);
       
         }
         catch(Exception e){e.printStackTrace();
         close_mysql_connection();
         }
         close_mysql_connection();
    }//GEN-LAST:event_jButton8ActionPerformed

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
            java.util.logging.Logger.getLogger(mainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mainFrame().setVisible(true);
               //mainf.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private static javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
}
