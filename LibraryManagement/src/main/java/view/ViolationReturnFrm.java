/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import dao.ErrorBookDAO;
import dao.ExportFile;
import dao.ViolationDAO;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.BorrowReturn1;
import model.ErrorBook;
import model.ErrorBookReturn;
import model.User1;
import model.Violation;

/**
 *
 * @author Admin
 */
public class ViolationReturnFrm extends javax.swing.JFrame {

    /**
     * Creates new form ViolationReturnFrm
     */
    private User1 user;
    private User1 reader;
    private List<BorrowReturn1> listreturn;
    private List<ErrorBook> listerror;
    DefaultTableModel modelBook;
    DefaultTableModel modelError;
    private int selectIndexBook;
    private int selectIndexError;
    private int amount;
    private BorrowReturn1 borrowReturn;
    private List<BorrowReturn1> listreturnviolation;
    private List<ErrorBookReturn> listerrorviolation;
    public ViolationReturnFrm(User1 user, User1 reader, List<BorrowReturn1> listreturn) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.user = user;
        this.reader = reader;
        amount = 0;
        this.listreturn = listreturn;
        try {
            this.listerror = new ErrorBookDAO().getAllErrorBook();
        } catch (SQLException ex) {
            Logger.getLogger(ViolationReturnFrm.class.getName()).log(Level.SEVERE, null, ex);
        }
        listreturnviolation = new ArrayList<>();
        listerrorviolation = new ArrayList<>();
        borrowReturn = null;
        labelNameReader.setText(reader.getFullname());
        labelReaderId.setText(reader.getUserid());
        labelAmount.setText("0");
        modelBook = (DefaultTableModel) tableChooseBook.getModel();
        modelError = (DefaultTableModel) tableChooseError.getModel();
        textViolation.setEditable(false);
        textViolation.append("\t THÔNG TIN VI PHẠM\n\n");
        textViolation.append("  Tên bạn đọc: " + reader.getFullname() + "\n");
        textViolation.append("  Mã bạn đọc: " + reader.getUserid()+ "\n");
        textViolation.append("\n");
        textViolation.append("\t Thông tin sách vi phạm\n");
        showTableBook();
        ShowTableError();
        
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
        buttonExit = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        buttonExport = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableChooseBook = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableChooseError = new javax.swing.JTable();
        jLabel18 = new javax.swing.JLabel();
        buttonChooseError = new javax.swing.JButton();
        buttonAddError = new javax.swing.JButton();
        buttonAddViolation = new javax.swing.JButton();
        labelReaderId = new javax.swing.JLabel();
        labelNameReader = new javax.swing.JLabel();
        buttonReset = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        textViolation = new javax.swing.JTextArea();
        jScrollPane6 = new javax.swing.JScrollPane();
        textDes = new javax.swing.JTextArea();
        jLabel19 = new javax.swing.JLabel();
        buttonChooseBook = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        labelAmount = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        buttonExit.setBackground(new java.awt.Color(0, 102, 0));
        buttonExit.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        buttonExit.setForeground(new java.awt.Color(255, 255, 255));
        buttonExit.setText("Thoát");
        buttonExit.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        buttonExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonExitActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 102, 0));
        jLabel10.setText("Mã độc giả");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 102, 0));
        jLabel12.setText("Tên độc giả");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 102, 0));
        jLabel16.setText("Chọn sách vi phạm");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 102, 0));
        jLabel17.setText("Mô tả");

        buttonExport.setBackground(new java.awt.Color(0, 102, 0));
        buttonExport.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        buttonExport.setForeground(new java.awt.Color(255, 255, 255));
        buttonExport.setText("Xuất");
        buttonExport.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        buttonExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonExportActionPerformed(evt);
            }
        });

        tableChooseBook.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã sách", "Tên sách"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tableChooseBook);
        if (tableChooseBook.getColumnModel().getColumnCount() > 0) {
            tableChooseBook.getColumnModel().getColumn(0).setMinWidth(35);
            tableChooseBook.getColumnModel().getColumn(0).setMaxWidth(50);
            tableChooseBook.getColumnModel().getColumn(1).setMinWidth(75);
            tableChooseBook.getColumnModel().getColumn(1).setMaxWidth(100);
        }

        tableChooseError.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Tên loại", "Phần trăm"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tableChooseError);
        if (tableChooseError.getColumnModel().getColumnCount() > 0) {
            tableChooseError.getColumnModel().getColumn(0).setMinWidth(35);
            tableChooseError.getColumnModel().getColumn(0).setMaxWidth(50);
            tableChooseError.getColumnModel().getColumn(2).setMinWidth(100);
            tableChooseError.getColumnModel().getColumn(2).setMaxWidth(120);
        }

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 102, 0));
        jLabel18.setText("Chọn loại vi phạm");

        buttonChooseError.setBackground(new java.awt.Color(0, 102, 0));
        buttonChooseError.setForeground(new java.awt.Color(255, 255, 255));
        buttonChooseError.setText("Chọn");
        buttonChooseError.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonChooseErrorActionPerformed(evt);
            }
        });

        buttonAddError.setBackground(new java.awt.Color(0, 102, 0));
        buttonAddError.setForeground(new java.awt.Color(255, 255, 255));
        buttonAddError.setText("Thêm");
        buttonAddError.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddErrorActionPerformed(evt);
            }
        });

        buttonAddViolation.setBackground(new java.awt.Color(0, 102, 0));
        buttonAddViolation.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        buttonAddViolation.setForeground(new java.awt.Color(255, 255, 255));
        buttonAddViolation.setText("Lưu");
        buttonAddViolation.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        buttonAddViolation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddViolationActionPerformed(evt);
            }
        });

        labelReaderId.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        labelReaderId.setText("jLabel2");

        labelNameReader.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        labelNameReader.setText("jLabel3");

        buttonReset.setBackground(new java.awt.Color(0, 102, 0));
        buttonReset.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        buttonReset.setForeground(new java.awt.Color(255, 255, 255));
        buttonReset.setText("Nhập lại");
        buttonReset.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        buttonReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonResetActionPerformed(evt);
            }
        });

        textViolation.setColumns(20);
        textViolation.setRows(5);
        jScrollPane1.setViewportView(textViolation);

        textDes.setColumns(20);
        textDes.setRows(5);
        jScrollPane6.setViewportView(textDes);

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        buttonChooseBook.setBackground(new java.awt.Color(0, 102, 0));
        buttonChooseBook.setForeground(new java.awt.Color(255, 255, 255));
        buttonChooseBook.setText("Chọn");
        buttonChooseBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonChooseBookActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 102, 0));
        jLabel20.setText("Tổng tiền phạt: ");

        labelAmount.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        labelAmount.setText("jLabel2");

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 102, 0));
        jLabel21.setText("VND");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(507, 507, 507)
                        .addComponent(jLabel19))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel17)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(buttonChooseBook)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(buttonAddError)
                                                .addGap(27, 27, 27)
                                                .addComponent(buttonChooseError)))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(jLabel10)
                                .addGap(28, 28, 28)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jLabel16))
                                    .addComponent(labelReaderId, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel18)
                                .addGap(148, 148, 148)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(labelNameReader, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jLabel20)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(labelAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(buttonAddViolation, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(buttonReset, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(58, 58, 58)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(buttonExport, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(buttonExit, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(23, 23, 23)
                                        .addComponent(jLabel21)))))))
                .addGap(30, 30, 30))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(labelReaderId)
                    .addComponent(jLabel12)
                    .addComponent(labelNameReader))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(65, 65, 65)
                                .addComponent(jLabel19))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel16)
                                .addGap(12, 12, 12)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(buttonChooseBook)
                        .addGap(21, 21, 21)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(labelAmount)
                    .addComponent(jLabel21)
                    .addComponent(buttonChooseError)
                    .addComponent(buttonAddError))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buttonExport, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonReset, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buttonExit, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonAddViolation, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel17)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(0, 102, 0));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Vi phạm");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 914, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExportActionPerformed
        // TODO add your handling code here:
        if(!listreturnviolation.contains(borrowReturn)){
            borrowReturn.setErrorBookReturn(listerrorviolation);
            listreturnviolation.add(borrowReturn);
        }
        Violation v = new Violation();
        v.setUser(user);
        v.setReader(reader);
        v.setDate(listreturn.get(0).getReturndate());
        v.setNote(textDes.getText());
        v.setBorrowReturn(listreturnviolation);
        if(listreturnviolation.isEmpty() || textDes.getText().isEmpty()){
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa lập biên bản!");
        }
        else {
            new ExportFile().exportViolation(v);
            JOptionPane.showMessageDialog(rootPane, "Đã xuất biên bản thành công!");
        }
    }//GEN-LAST:event_buttonExportActionPerformed

    private void buttonAddViolationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddViolationActionPerformed
        // TODO add your handling code here:
        if(!listreturnviolation.contains(borrowReturn)){
            borrowReturn.setErrorBookReturn(listerrorviolation);
            listreturnviolation.add(borrowReturn);
        }
        Violation v = new Violation();
        v.setUser(user);
        v.setReader(reader);
        v.setDate(new Date((new java.util.Date()).getTime()));
        v.setNote(textDes.getText());
        v.setBorrowReturn(listreturnviolation);
        if(listreturnviolation.isEmpty() || textDes.getText().isEmpty()){
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa lập biên bản!");
        }
        else {
            try {
                new ViolationDAO().addViolation(v);
                JOptionPane.showMessageDialog(rootPane, "Đã lưu biên bản thành công!");
                ManageReturnFrm frm = new ManageReturnFrm(user);
                this.setVisible(false);
                frm.setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(ViolationReturnFrm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_buttonAddViolationActionPerformed

    private void buttonResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonResetActionPerformed
        // TODO add your handling code here:
        textViolation.setText("");
        labelAmount.setText("0");
        textViolation.append("\t THÔNG TIN VI PHẠM\n\n");
        textViolation.append("  Tên bạn đọc: " + reader.getFullname() + "\n");
        textViolation.append("  Mã bạn đọc: " + reader.getUserid() + "\n");
        textViolation.append("\n");
        textViolation.append("\t Thông tin sách vi phạm\n");
        listreturnviolation = new ArrayList<>();
        listerrorviolation = new ArrayList<>();
        this.borrowReturn = null;
        this.amount = 0;
    }//GEN-LAST:event_buttonResetActionPerformed

    private void buttonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExitActionPerformed
        // TODO add your handling code here:
        ManageReturnFrm frm = new ManageReturnFrm(user);
        this.setVisible(false);
        frm.setVisible(true);
    }//GEN-LAST:event_buttonExitActionPerformed

    private void buttonChooseBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonChooseBookActionPerformed
        // TODO add your handling code here:
        selectIndexBook = tableChooseBook.getSelectedRow();
        if(selectIndexBook == -1){
            JOptionPane.showMessageDialog(rootPane, "Hãy chọn một hàng trong bảng!");
        }
        else {
            BorrowReturn1 br = listreturn.get(selectIndexBook);
            addInforBook(br);
        }
    }//GEN-LAST:event_buttonChooseBookActionPerformed

    private void buttonChooseErrorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonChooseErrorActionPerformed
        // TODO add your handling code here:
        selectIndexError = tableChooseError.getSelectedRow();
        if(selectIndexError == -1){
            JOptionPane.showMessageDialog(rootPane, "Hãy chọn một hàng trong bảng!");
        }
        else {
            ErrorBook eb = listerror.get(selectIndexError);
            addInforError(eb);
        }
    }//GEN-LAST:event_buttonChooseErrorActionPerformed

    private void buttonAddErrorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddErrorActionPerformed
        // TODO add your handling code here:
        AddErrorBookViolationFrm frm = new AddErrorBookViolationFrm(this, rootPaneCheckingEnabled);
        frm.setVisible(true);
    }//GEN-LAST:event_buttonAddErrorActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(ViolationReturnFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(ViolationReturnFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(ViolationReturnFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(ViolationReturnFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new ViolationReturnFrm().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAddError;
    private javax.swing.JButton buttonAddViolation;
    private javax.swing.JButton buttonChooseBook;
    private javax.swing.JButton buttonChooseError;
    private javax.swing.JButton buttonExit;
    private javax.swing.JButton buttonExport;
    private javax.swing.JButton buttonReset;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JLabel labelAmount;
    private javax.swing.JLabel labelNameReader;
    private javax.swing.JLabel labelReaderId;
    private javax.swing.JTable tableChooseBook;
    private javax.swing.JTable tableChooseError;
    private javax.swing.JTextArea textDes;
    private javax.swing.JTextArea textViolation;
    // End of variables declaration//GEN-END:variables

    private void showTableBook() {
        modelBook.setRowCount(0);
        int index = 1;
        for(BorrowReturn1 br : listreturn){
            modelBook.addRow(new Object[]{
                index++, br.getAbook().getAbookid(), br.getAbook().getBook().getName()
            });
        }
    }

    private void ShowTableError() {
        modelError.setRowCount(0);
        int index = 1;
        for(ErrorBook eb : listerror){
            modelError.addRow(new Object[]{
                index++, eb.getName(), eb.getPercentage()
            });
        }
    }

    private void addInforBook(BorrowReturn1 br) {
        textViolation.append("\n");
        textViolation.append("  Mã sách: " + br.getAbook().getAbookid() + "\n");
        textViolation.append("  Tên sách: " + br.getAbook().getBook().getName() + "\n");
        textViolation.append("  Ngày mượn: " + br.getBorrowdate() + "\n");
        textViolation.append("  Hạn trả: " + br.getDuedate() + "\n");
        textViolation.append("  Ngày trả: " + br.getReturndate() + "\n");
        textViolation.append("  Lỗi vi phạm: \n");
        
        
        if(borrowReturn == null){
            borrowReturn = br;
        }
        else if(listerrorviolation.size() == 0){
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa chọn lỗi!");
        }
        else {
            borrowReturn.setErrorBookReturn(listerrorviolation);
            listreturnviolation.add(borrowReturn);
            borrowReturn = br;
            listerrorviolation = new ArrayList<>();
        }
    }

    private void addInforError(ErrorBook eb) {
        int price = listreturn.get(selectIndexBook).getAbook().getBook().getPrice() * eb.getPercentage() / 100;
        amount += price;
        textViolation.append("      " + eb.getName() + " - Phạt: " + String.format("%,d", price) + " VND \n");
        labelAmount.setText(String.format("%,d", amount));
        listerrorviolation.add(new ErrorBookReturn(eb, price));
    }

    void updateTableError() {
        try {
            this.listerror = new ErrorBookDAO().getAllErrorBook();
            ShowTableError();
        } catch (SQLException ex) {
            Logger.getLogger(ViolationReturnFrm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
