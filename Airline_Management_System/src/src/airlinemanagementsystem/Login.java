package src.airlinemanagementsystem;
import javax.naming.spi.DirStateFactory.Result;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener{

    JButton close, reset, submit;
    JTextField tfusername;
    JPasswordField tfpassword;

    public Login(){
    
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel lblusername = new JLabel("Username");
        lblusername.setBounds(150,80,100,20);
        add(lblusername);

        JLabel lblpassword = new JLabel("Password");
        lblpassword.setBounds(150,120,100,20);
        add(lblpassword);

        tfusername = new JTextField();
        tfusername.setBounds(220,80,200,20);
        add(tfusername);

        tfpassword = new JPasswordField();
        tfpassword.setBounds(220,120,200,20);
        add(tfpassword);
           
        close = new JButton("close");
        close.setBounds(150,170,70,20);
        close.addActionListener(this);
        add(close);

        reset = new JButton("reset");
        reset.setBounds(250,170,70,20);
        reset.addActionListener(this);
        add(reset);

        submit = new JButton("submit");
        submit.setBounds(350,170,80,20);
        submit.addActionListener(this);
        add(submit);
     
        setSize(600, 300);
        setLocation(350, 220);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent ae) {

        if(ae.getSource() == close) {
            setVisible(false);
        } else if(ae.getSource() == reset) {
            tfusername.setText("");
            tfpassword.setText("");
        } else if(ae.getSource() == submit) {
            String username = tfusername.getText();
            String password = tfpassword.getText();
        
            try {
                Conn c = new Conn();

                String query = "select * from login where username = '"+username+"' and password = '"+password+"'";
                Statement stmt1 = c.c.createStatement();
                ResultSet rs = stmt1.executeQuery(query);

                if(rs.next()) {
                   new AdminHome();
                   setVisible(false);
                   rs.close();
                   stmt1.close();
                } else {
                   
                   String customer_query =  "select * from customers_login where username = '"+username+"' and password = '"+password+"'";
                   Statement stmt2 = c.c.createStatement();
                   ResultSet customer_rs = stmt2.executeQuery(customer_query);
               
                   if(customer_rs.next()) {
                    new Home();
                    setVisible(false);
                    customer_rs.close();
                   } else {
                    JOptionPane.showMessageDialog(null, "Invalid Username or Password");
                    setVisible(false);
                   }
                   customer_rs.close();
                   stmt2.close();
                }
            } catch(Exception e) {
              e.printStackTrace();
            }
        }

    }
    
    public static void main(String[] args) {
        new Login();
    }
}
