/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package salesforce;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterJob;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author NNAMDI
 */
public class ViewInvoice extends javax.swing.JFrame implements Printable {

    /**
     * Creates new form ViewInvoice
     */
    static String customer;
    static String dates;
    static String jobnumber;
    static JTable jtableview;
    static JTable testrun;
    JScrollPane testpane;
    JCheckBox jCheckbox1;
    JComboBox jCombobox1;
    double goods_value_final = 0.00;
    

    public double sumColumn(JTable jtableview, int column) {

        double total = 0.00;

        try {
            for (int i = 0; i < jtableview.getRowCount(); i++) {

                double amount = Double.parseDouble(jtableview.getValueAt(i, column).toString());
                total = amount + total;
                System.out.println(total);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return total;

    }
    
    


    public void load_jtable(JTable table) {
        
            //formatting numbers upto 2 decimal places in Java
        DecimalFormat df = new DecimalFormat("#,###,##0.00");
        System.out.println(df.format(364565.14));
        System.out.println(df.format(364565.1454));
     
        //formatting numbers upto 3 decimal places in Java
        df = new DecimalFormat("#,###,##0.000");
        System.out.println(df.format(364565.14));
        System.out.println(df.format(364565.1454));

        
        testrun = new JTable();
        
        jTable1.setShowGrid(false);
        jTable1.setShowVerticalLines(true);
        DefaultTableModel model = new DefaultTableModel();

        Object[] rowData;
        Object[] columnsName;

        columnsName = new Object[5];

        columnsName[0] = "S/N";
        columnsName[1] = "Description";
        columnsName[2] = "Quantity";
        columnsName[3] = "Unit Price";
        columnsName[4] = "Value";
        BigDecimal valuess;
        BigDecimal qty;
        BigDecimal unit_price;

        model.setColumnIdentifiers(columnsName);
        rowData = new Object[11];
        model.setRowCount(0);

        JTableHeader tableHeader = jTable1.getTableHeader();
        tableHeader.setBackground(Color.black);
        
         //formatting numbers upto 2 decimal places in Java
        DecimalFormat dfgs = new DecimalFormat("#,###,##0.00");
        System.out.println(dfgs.format(364565.14));
        System.out.println(dfgs.format(364565.1454));

        try {
            for (int i = 0; i < table.getRowCount(); i++) {
                
                 
       
                    
                rowData[0] = i + 1;
                rowData[1] = table.getValueAt(i, 2);
                
                if(jCombobox1.getSelectedItem().equals("None")){
                rowData[2] = table.getValueAt(i, 3)+" ";
                
                //rowData[2] = dfgs.format(table.getValueAt(i, 3));
                }
                else{rowData[2] = table.getValueAt(i, 3)+" "+jCombobox1.getSelectedItem();
                //rowData[2] = dfgs.format(table.getValueAt(i, 3))+" "+jCombobox1.getSelectedItem();
                }
               // rowData[3] = table.getValueAt(i, 4);
                
        

                jTable1.setValueAt(i + 1, i, 0); //S/N 
                jTable1.setValueAt(table.getValueAt(i, 2), i, 1); //Description  
                jTable1.setValueAt(table.getValueAt(i, 3), i, 2); //Quantity 
                jTable1.setValueAt(table.getValueAt(i, 4), i, 3); //unit price
                
                  double unit_prices = Double.parseDouble(table.getValueAt(i, 4).toString()); 
                jTable1.setValueAt(unit_prices, i, 3); //unit price 
                unit_price = new BigDecimal(unit_prices );
                unit_price.setScale(2, RoundingMode.HALF_EVEN);
                rowData[3] = unit_price.setScale(2, RoundingMode.HALF_EVEN).toString();
                
                double value = Double.parseDouble(table.getValueAt(i, 3).toString()) * Double.parseDouble(table.getValueAt(i, 4).toString());
                jTable1.setValueAt(value, i, 4); //value 
                
                
                 valuess = new BigDecimal(value);
                 valuess.setScale(2, RoundingMode.HALF_EVEN);
                 
        //formatting numbers upto 2 decimal places in Java
        DecimalFormat dfs = new DecimalFormat("#,###,##0.00");
        System.out.println(dfs.format(364565.14));
        System.out.println(dfs.format(364565.1454));
        
                goods_value_final = goods_value_final+ value;
               // rowData[4] = value;
                rowData[4] = dfs.format(value);
                
              // rowData[4] =valuess.setScale(2,RoundingMode.HALF_EVEN).toString();
                
                model.addRow(rowData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        
       
                rowData[0] = "";
                rowData[1] = "";
                rowData[2] = "";
                rowData[3] = "";
                rowData[4] = "";
                
      

        model.addRow(rowData);   //First pad row        
        model.addRow(rowData);    //Second pad row
        model.addRow(rowData); 
        model.addRow(rowData); 
        model.addRow(rowData); 
        model.addRow(rowData); 
        model.addRow(rowData); 
        model.addRow(rowData); 
        model.addRow(rowData); 
        
        jTable1.setShowGrid(false);
        testrun.setShowGrid(false);
        
        
        jTable1.setModel(model);
        testrun.setModel(model);
        
        jTable1.setShowGrid(false);
        testrun.setShowGrid(false);
        
        jTable1.setShowVerticalLines(true);
        testrun.setShowVerticalLines(true);
        //testpane.add(testrun);
        //jPanel2.add(testpane);
        
        Color ivory = new Color(255, 255, 208);
        jTable1.setOpaque(true);
        jTable1.setFillsViewportHeight(true);
        jTable1.setBackground(Color.white);
        
        jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(37);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(150);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(100);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(90);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(173);

        //JTableHeader tableHeader = jTable1.getTableHeader();
        //  tableHeader.setBackground(Color.black);
        Integer[][] data = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
      String[] cols = {"A", "B", "C"};

      JTable jtable = new JTable(data, cols);

      JTableHeader header = jtable.getTableHeader();
      header.setBackground(Color.black);
      header.setForeground(Color.yellow);

      
    }

    public ViewInvoice(JComboBox combobox,JCheckBox checkbox,String customer, String dates, String jobnumber, JTable jtableview, String comment2, String comment3) {
        initComponents();
        jCombobox1 = combobox;
        //jTable1.getTableHeader().setBackground(Color.PINK);
        //jTable1.getTableHeader().setForeground(Color.BLUE);
        jLabel16.setText(customer);
        jLabel37.setText(dates);
        jLabel38.setText(jobnumber);
        jLabel40.setText(comment2);
        jLabel41.setText(comment3);
        
        jCheckbox1 = checkbox;
        
        
        if(jtableview.getValueAt(0, 6).equals("isselected") ){
       // if(checkbox.isSelected() ){
           jLabel19.setVisible(true);
           jLabel23.setVisible(true);
        
        }
        else{jLabel19.setVisible(false);
           jLabel23.setVisible(false);
        }

        load_jtable(jtableview);
        
           //formatting numbers upto 2 decimal places in Java
        DecimalFormat dfa = new DecimalFormat("#,###,##0.00");
        System.out.println(dfa.format(364565.14));
        System.out.println(dfa.format(364565.1454));




        double goods_value = sumColumn(jTable1, 4);// Goods value*
        String hj = String.valueOf(goods_value);
        BigDecimal goods_values = new BigDecimal(goods_value);
        
       // jLabel17.setText(goods_values.setScale(2,RoundingMode.HALF_EVEN).toString());
        jLabel17.setText(dfa.format(goods_value_final));
        double vat = 0;
        if(jtableview.getValueAt(0, 6).equals("isselected")){
      
        vat = (0.05)* goods_value_final;
        BigDecimal vats = new BigDecimal(vat);
        jLabel23.setText(dfa.format(vat));
        
        }
        
        else{vat = 0;}
        
        double stamp = sumColumn(jtableview, 8);// stamp
        BigDecimal stamps = new BigDecimal(stamp);
        jLabel24.setText(stamps.setScale(2,RoundingMode.HALF_EVEN).toString());

        double delivery_charges = sumColumn(jtableview, 9);// delivery_charges
        BigDecimal delivery_chargess = new BigDecimal(delivery_charges);
        jLabel25.setText(delivery_chargess.setScale(2,RoundingMode.HALF_EVEN).toString());

        double other_charges = sumColumn(jtableview, 10);// other_charges
        BigDecimal other_chargess = new BigDecimal(other_charges);
        jLabel26.setText(other_chargess.setScale(2,RoundingMode.HALF_EVEN).toString());

        double total_charges = goods_value_final + vat + stamp + delivery_charges + other_charges; // total_charges
        BigDecimal total_chargess = new BigDecimal(total_charges);

           
        
        jLabel23.setText(dfa.format( vat));
        System.out.println("Goods Value: "+goods_value);
        jLabel28.setText(dfa.format( total_charges));
        
       // jLabel28.setText( total_chargess.setScale(2,RoundingMode.HALF_EVEN).toString());

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
        jLabel14 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sales Rep Print View");

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("Invoice View");

        jButton1.setText("Print");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(631, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(105, 105, 105))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(281, 281, 281)
                    .addComponent(jLabel14)
                    .addContainerGap(281, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(22, 22, 22))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(27, 27, 27)
                    .addComponent(jLabel14)
                    .addContainerGap(27, Short.MAX_VALUE)))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel15.setText("Customer:");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "S/NO", "DESCRIPTION", "QUANTITY", "UNIT PRICE", "VALUE"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel16.setText("Customer:");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel17.setText("Customer:");

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel18.setText("GOODS VALUE: NGN");

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel19.setText("VAT: NGN");

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel20.setText("STAMP DUTY: NGN");

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel21.setText("DELIVERY CHARGES: NGN");

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel22.setText("OTHER CHARGES: NGN");

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel23.setText("Customer:");

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel24.setText("Customer:");

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel25.setText("Customer:");

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel26.setText("Customer:");

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel27.setText("TOTAL: NGN");

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel28.setText("Customer:");

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel29.setText("FOR NSPM PLC");

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel30.setText("SIGN:");

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel31.setText("DATE:");

        jLabel1.setText("---------------------------");

        jLabel2.setText("---------------------------");

        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jLabel32.setText("CHEQUES/PAYMENTS SHOULD BE DRAWN IN");

        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jLabel33.setText("FAVOUR OF");

        jLabel34.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jLabel34.setText("THE NIGERIAN SECURITY PRINTING AND MINTING COMPANY");

        jLabel35.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel35.setText("Date:");

        jLabel36.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel36.setText("Job No:");

        jLabel37.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel37.setText("Customer:");

        jLabel38.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel38.setText("Customer:");

        jLabel39.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jLabel39.setText("PLC AND CROSSED A/C PAYEE ONLY");

        jLabel40.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel40.setText("Customer:");

        jLabel41.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel41.setText("Customer:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jSeparator1)
                                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel32)
                                    .addComponent(jLabel33)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel34, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel39)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel30, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel31, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(40, 40, 40)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))))
                        .addContainerGap(313, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel35)
                            .addComponent(jLabel36))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel38)
                            .addComponent(jLabel37))
                        .addGap(241, 241, 241))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(311, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel20)
                    .addComponent(jLabel19)
                    .addComponent(jLabel18)
                    .addComponent(jLabel22)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(276, 276, 276))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel15)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 659, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel37))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel36)
                            .addComponent(jLabel38))))
                .addGap(4, 4, 4)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel41)
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jLabel24))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jLabel25))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jLabel26))
                .addGap(7, 7, 7)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(jLabel28))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(35, 35, 35))
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel33)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel34)
                        .addGap(2, 2, 2)
                        .addComponent(jLabel39))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel32))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel30)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel31)
                            .addComponent(jLabel2))))
                .addGap(25, 25, 25))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(316, 316, 316))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public int print(Graphics g, PageFormat pf, int pageIndex) {

        if (pageIndex != 0) {
            return NO_SUCH_PAGE;
        }
        Graphics2D g2 = (Graphics2D) g;
        g2.translate(pf.getImageableX(), pf.getImageableY());
        g2.setFont(new Font("Serif", Font.PLAIN, 10));

        Color panel2 = jPanel2.getBackground();

        jPanel2.setBackground(Color.white);

        jPanel2.print(g2);

        jPanel2.setBackground(panel2);

        return PAGE_EXISTS;

    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        PrinterJob pj = PrinterJob.getPrinterJob();
        pj.setPrintable(this);

        if (pj.printDialog()) {
            try {
                pj.print();

            } catch (Exception hjl) {
                System.out.println(hjl);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(ViewInvoice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewInvoice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewInvoice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewInvoice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
      
    



        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                // new ViewInvoice().setVisible( customer, dates,jobnumber, jtableview);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    public static javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
