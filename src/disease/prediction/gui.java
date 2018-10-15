package disease.prediction;

import com.mysql.jdbc.PreparedStatement;
import java.awt.Component;
import java.util.*;
import javax.swing.*;
//import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;

interface Searchable<E, V>{
	public Collection<E> search(V value);
}

public class gui {
    
    private JComboBox jComboBox1;
    private static Connection conn;
    private static JTextArea symptoms;
    private static JButton select;
    public gui(){
        try{
        getConnection();
        init();
        }catch(Exception e){
            
        }
    }
    
    private static void getConnection() throws   ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","sonayuvan");
		System.out.println("Connected to MySql server");
	}
    public static void main(String a[]) throws Exception{
        gui g = new gui();
    }

    
    public void init() throws Exception{
        JFrame jframe = new JFrame();
        jframe.setSize(1200,800);
        jframe.setVisible(true);
        
        
        JLabel headingl = new JLabel();
        headingl.setFont(new java.awt.Font("Calibri", 3, 28));
        headingl.setBounds(500,10,300,30);
        headingl.setText("Disease Prediction");
        
        JLabel heading2 = new JLabel();
        heading2.setFont(new java.awt.Font("Calibri", 3, 24));
        heading2.setBounds(50,150,200,50);
        heading2.setText("Fill the details");
        
        JLabel firstnamel = new JLabel();
        firstnamel.setFont(new java.awt.Font("Calibri", 1, 20));
        firstnamel.setBounds(50,250,200,50);
        firstnamel.setText("First Name");
        
        JLabel lastnamel = new JLabel();
        lastnamel.setFont(new java.awt.Font("Calibri", 1, 20));
        lastnamel.setBounds(50,350,200,50);
        lastnamel.setText("Last Name");
        
        JLabel agel = new JLabel();
        agel.setFont(new java.awt.Font("Calibri", 1, 20));
        agel.setBounds(50,450,200,50);
        agel.setText("Age");
        
        JLabel genderl = new JLabel();
        genderl.setFont(new java.awt.Font("Calibri", 1, 20));
        genderl.setBounds(50,550,200,50);
        genderl.setText("Gender");
        
        JTextField firstnamet = new JTextField();
        firstnamet.setFont(new java.awt.Font("Calibri", 1, 20));
        firstnamet.setBounds(250,250,300,50);
        firstnamet.setText("");
        
        JTextField lastnamet = new JTextField();
        lastnamet.setFont(new java.awt.Font("Calibri", 1, 20));
        lastnamet.setBounds(250,350,300,50);
        lastnamet.setText("");
        
        JTextField aget = new JTextField();
        aget.setFont(new java.awt.Font("Calibri", 1, 20));
        aget.setBounds(250,450,300,50);
        aget.setText("");
       
        JTextField gendert = new JTextField();
        gendert.setFont(new java.awt.Font("Calibri", 1, 20));
        gendert.setBounds(250,550,300,50);
        gendert.setText("");
        
        JLabel heading3 = new JLabel();
        heading3.setFont(new java.awt.Font("Calibri", 3, 24));
        heading3.setBounds(700,150,300,50);
        heading3.setText("Select the symptoms");
        
        jComboBox1 =  new JComboBox();
        jComboBox1.setEditable(true);
        jComboBox1.setVisible(true);
        jComboBox1.setBounds(700,250,200,50);
        jframe.add(jComboBox1);
        
        JTextArea symptoms = new JTextArea();
        symptoms.setBounds(700,400,300,200);
        jframe.add(symptoms);
        
        JButton select = new JButton();
        select.setBounds(700,320,100,40);
        select.setText("Select");
        //select.addActionListener((ActionListener) this);
        jframe.add(select);
        
        JTextField jtextfield = (JTextField) jComboBox1.getEditor().getEditorComponent();
        jtextfield.addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent ke){
                SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    try {
                        comboFilter(jtextfield.getText());
                    } catch (SQLException ex) {
                        //Logger.getLogger(gui.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            }
        });
        
        
        jframe.add(headingl);
        jframe.add(heading2);
        jframe.add(firstnamel);
        jframe.add(lastnamel);
        jframe.add(agel);
        jframe.add(genderl);
        jframe.add(aget);
        jframe.add(firstnamet);
        jframe.add(lastnamet);
        jframe.add(aget);
        jframe.add(gendert);
        jframe.add(heading3);
       
        jframe.setResizable(false);
        jframe.setLayout(null);
        jframe.setVisible(true);
    }

    private void comboFilter(String text) throws SQLException {
        
        
        List<String> filterArray= new ArrayList<String>();

        PreparedStatement ps = (PreparedStatement) conn.prepareStatement("select * from sname");
        ResultSet rs = ps.executeQuery(); 
    
        while(rs.next()) {
            filterArray.add(rs.getString("sname"));
        }

        if (filterArray.size() > 0) {
        jComboBox1.setModel(new DefaultComboBoxModel(filterArray.toArray()));
        jComboBox1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                symptoms.append(text + ", ");
            }
        });
        jComboBox1.setSelectedItem(text);
        jComboBox1.showPopup();
    }
    else {
        jComboBox1.hidePopup();
    }
          
    
  }
}