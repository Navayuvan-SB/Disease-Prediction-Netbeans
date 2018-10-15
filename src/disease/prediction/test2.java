/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package disease.prediction;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

class test2 extends javax.swing.JFrame {

public test2() {
    initComponents();
    final JTextField textfield = (JTextField)     
    jComboBox1.getEditor().getEditorComponent();
    textfield.addKeyListener(new KeyAdapter() {
        public void keyReleased(KeyEvent ke) {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    comboFilter(textfield.getText());
                }
            });
        }
    });
}



public void comboFilter(String enteredText) {
    List<String> filterArray= new ArrayList<String>();

            String str1="";

    filterArray.add("bike");
    filterArray.add("car");
    filterArray.add("cap");
    filterArray.add("cape");
    filterArray.add("canadian");
    filterArray.add("caprecious");
    filterArray.add("catepult");
    filterArray.add("bike");
    filterArray.add("car");
    filterArray.add("cap");
    filterArray.add("cape");
    filterArray.add("canadian");
    filterArray.add("caprecious");
    filterArray.add("catepult");
    filterArray.add("bike");
    filterArray.add("car");
    filterArray.add("cap");
    filterArray.add("cape");
    filterArray.add("canadian");
    filterArray.add("caprecious");
    filterArray.add("catepult");


    if (filterArray.size() > 0) {
        jComboBox1.setModel(new DefaultComboBoxModel(filterArray.toArray()));
        jComboBox1.setSelectedItem(enteredText);
        jComboBox1.showPopup();
    }
    else {
        jComboBox1.hidePopup();
    }
}

@SuppressWarnings("unchecked")
// <editor-fold defaultstate="collapsed" desc="Generated Code">
private void initComponents() {

    jComboBox1 = new javax.swing.JComboBox();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    jComboBox1.setEditable(true);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addGap(87, 87, 87)
            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(98, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addGap(108, 108, 108)
            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(172, Short.MAX_VALUE))
    );
   
    pack();
}// </editor-fold>

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
        java.util.logging.Logger.getLogger(test2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
        java.util.logging.Logger.getLogger(test2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
        java.util.logging.Logger.getLogger(test2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
        java.util.logging.Logger.getLogger(test2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    //</editor-fold>

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
            new test2().setVisible(true);
        }
    });
}
// Variables declaration - do not modify
private javax.swing.JComboBox jComboBox1;
// End of variables declaration
}