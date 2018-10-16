
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

public class Diseasefinal {
    
    private static JComboBox jComboBox1;
    private static Connection conn;
    private static JTextArea symptoms;
    private static JTextField firstnamet;
    private static JTextField lastnamet;
    private static JTextField aget;
    private static JTextField gendert;
    private static JButton submit;
    
    private static int[] sid = new int[100];
    private static int n = 0;

    public Diseasefinal(){
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
        Diseasefinal g = new Diseasefinal();
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
        
        firstnamet = new JTextField();
        firstnamet.setFont(new java.awt.Font("Calibri", 1, 20));
        firstnamet.setBounds(250,250,300,50);
        firstnamet.setText("");
        
        lastnamet = new JTextField();
        lastnamet.setFont(new java.awt.Font("Calibri", 1, 20));
        lastnamet.setBounds(250,350,300,50);
        lastnamet.setText("");
        
        aget = new JTextField();
        aget.setFont(new java.awt.Font("Calibri", 1, 20));
        aget.setBounds(250,450,300,50);
        aget.setText("");
       
        gendert = new JTextField();
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
        
        JLabel selected = new JLabel();
        selected.setText("Selected Symptoms");
        selected.setFont(new java.awt.Font("Calibri", 1, 20));
        selected.setBounds(700,300,200,50);
        selected.setForeground(new java.awt.Color(255, 0, 0));
        jframe.add(selected);
        
        symptoms = new JTextArea();
        symptoms.setBounds(700,350,300,200);
        symptoms.setFont(new java.awt.Font("Calibri", 1, 20));
        jframe.add(symptoms);
        
        submit = new JButton();
        submit.setBounds(700,590,100,40);
        submit.setText("Submit");
        //select.addActionListener((ActionListener) this);
        jframe.add(submit);
        
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
        
        jtextfield.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent k){
                if(k.getKeyCode() == KeyEvent.VK_ENTER){
                    String text = jComboBox1.getSelectedItem().toString();
                    symptoms.append(text + "\n");
                    jtextfield.setText("");
                }
            }
        });
        
        submit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
                try {
                    filterSym(symptoms);
                    predict(sid,n);
                } catch (SQLException ex) {
                    Logger.getLogger(Diseasefinal.class.getName()).log(Level.SEVERE, null, ex);
                }
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
            jComboBox1.setSelectedItem(text);
            jComboBox1.showPopup();
        }
        else {
        jComboBox1.hidePopup();
        }
    }
    
    public static void predict(int[] v, int n) throws SQLException {
		
		PreparedStatement ps = (PreparedStatement) conn.prepareStatement("select * from main");
		ResultSet rs = ps.executeQuery();
		int[] a = new int [100];
		int h = 0,t = 0;
		int[] r = new int[100];
		while(rs.next()) {
			r[t] = 0;
			StringTokenizer st2 = new StringTokenizer(rs.getString("sym"), ", ");
			while (st2.hasMoreElements()) {
				a[h]=Integer.parseInt(st2.nextToken());
				h += 1;
			}
			for(int i = 1; i < n; i++) {
				for(int j = 0; j <= h; j++) {
					if(v[i] == a[j]) {
						r[t] += 1;
					}
				}
			}
			t += 1;
			h = 0;
                }
	int max = r[0],m = 0;
	for(int i = 0;i < 10;i ++) {
		if(r[i] > max) {
			max = r[i];
			m = i;
		}
	}
	m = m + 1;
	PreparedStatement ps1 = (PreparedStatement) conn.prepareStatement("select * from dname");
	ResultSet rs1 = ps1.executeQuery();
	while(rs1.next()) {
		if(rs1.getInt("id") == m) {
			//System.out.println(rs1.getString("dname"));
                        
                        JPanel jpanel = new JPanel();
                        
                        JLabel name = new JLabel();
                        name.setText("Name: " + firstnamet.getText() + " " + lastnamet.getText());
                        name.setFont(new java.awt.Font("Calibri", 1, 20));
                        
                        JLabel age = new JLabel();
                        age.setText("Age: " + aget.getText());
                        age.setFont(new java.awt.Font("Calibri", 1, 20));
                        
                        JLabel gender = new JLabel();
                        gender.setText("Gender :" + gendert.getText());
                        gender.setFont(new java.awt.Font("Calibri", 1, 20));
                        
                        JLabel dis = new JLabel();
                        dis.setText("You may have '" + rs1.getString("dname") + "'");
                        dis.setFont(new java.awt.Font("Calibri", 1, 28));
                        dis.setForeground(new java.awt.Color(255, 0, 0));
                        
                        jpanel.add(name);
                        jpanel.add(age);
                        jpanel.add(gender);
                        jpanel.add(dis);
                        jpanel.setLayout(new BoxLayout(jpanel, BoxLayout.Y_AXIS));
                        
                        JOptionPane.showMessageDialog(null,jpanel,"Result",JOptionPane.WARNING_MESSAGE);
		}
	}
    }
    
    public void filterSym(JTextArea symptoms) throws SQLException{
        PreparedStatement ps = (PreparedStatement) conn.prepareStatement("select * from sname");
	ResultSet rs = ps.executeQuery();
        
        while(rs.next()){
            StringTokenizer st2 = new StringTokenizer(symptoms.getText().toString(), "\n");
            while(st2.hasMoreElements()){
                String str1 = rs.getString("sname").toString().trim();
                String str2 = st2.nextToken().toString().trim();
                
                if(str1.equals(str2)){
                    sid[n] = rs.getInt("id");
                    n++;
                }
            }
        }
    }   
}    

