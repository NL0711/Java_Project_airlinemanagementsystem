package src.airlinemanagementsystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

public class Cancel extends JFrame implements ActionListener{
    
    JTextField tfpnr;
    JLabel tfname, cancellationno, lbldate, lblfcode;
    JButton fetchButton, flight;
    
    public Cancel() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        Random random = new Random();
        
        JLabel heading = new JLabel("CANCELLATION");
        heading.setBounds(180, 20, 280, 35);
        heading.setForeground(Color.BLUE);
        heading.setFont(new Font("Times New Roman", Font.BOLD, 28));
        add(heading);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("src/airlinemanagementsystem/icons/icons/cancel.jpg"));
        Image i2 = i1.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel lblimage = new JLabel(i3);
        lblimage.setBounds(500, 100, 250, 250);
        add(lblimage);
 
        JLabel subheading = new JLabel("Boarding Pass");
        subheading.setBounds(220, 50, 300, 30);
        subheading.setFont(new Font("Tahoma", Font.PLAIN, 22));
        subheading.setForeground(Color.BLACK);
        add(subheading);
        
        JLabel lblpnr = new JLabel("PNR DETAILS");
        lblpnr.setBounds(80, 120, 150, 25);
        lblpnr.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblpnr);
        
        tfpnr = new JTextField();
        tfpnr.setBounds(190, 120, 150, 25);
        add(tfpnr);

        fetchButton = new JButton("Show Details");
        fetchButton.setFont(new Font("Tahoma", Font.PLAIN,12));
        fetchButton.setBackground(Color.BLACK);
        fetchButton.setForeground(Color.WHITE);
        fetchButton.setBounds(350, 120, 120, 25);
        fetchButton.addActionListener(this);
        add(fetchButton);
        

        JLabel lblname = new JLabel("NAME");
        lblname.setBounds(80, 160, 150, 25);
        lblname.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblname);
        
        tfname = new JLabel();
        tfname.setBounds(220, 160, 150, 25);
        add(tfname);
        
        JLabel lblcancellationno = new JLabel("Cancellation No");
        lblcancellationno.setBounds(80, 200, 150, 25);
        lblcancellationno.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblcancellationno);
        
        cancellationno = new JLabel(""+random.nextInt(1000000));
        cancellationno.setBounds(260, 200, 150, 25);
        add(cancellationno);
        

        JLabel lbldate = new JLabel("Date");
        lbldate.setBounds(80, 240, 150, 25);
        lbldate.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lbldate);
        
        lbldate = new JLabel();
        lbldate.setBounds(130, 240, 150, 25);
        add(lbldate);

        flight = new JButton("Cancel");
        flight.setBackground(Color.RED);
        flight.setForeground(Color.WHITE);
        flight.setBounds(240, 330, 120, 25);
        flight.addActionListener(this);
        add(flight);

        JLabel lblfcode = new JLabel("Flight Code");
        lblfcode.setBounds(380, 260, 150, 25);
        lblfcode.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblfcode);
        
        lblfcode = new JLabel();
        lblfcode.setBounds(540, 260, 150, 25);
        add(lblfcode);

        setSize(800, 450);
        setLocation(280, 150);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == fetchButton) {
        String pnr = tfpnr.getText();

        try {
            Conn conn = new Conn();

            String query = "select * from reservation where PNR = '"+pnr+"'";

            ResultSet rs = conn.s.executeQuery(query);

            if (rs.next()) {
                tfname.setText(rs.getString("name")); 
                lblfcode.setText(rs.getString("flightcode")); 
                lbldate.setText(rs.getString("ddate")); 
            } else {
                JOptionPane.showMessageDialog(null, "Please enter correct PNR");                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    } else if(ae.getSource()== flight) {
        String name =tfname.getText();
        String pnr = tfpnr.getText();
        String cancelno = cancellationno.getText();
        String fcode = lblfcode.getText();
        String date = lbldate.getText();
        try {
            Conn conn = new Conn();

            String query = "insert into cancel values('"+pnr+"','"+name+"','"+cancelno+"','"+fcode+"','"+date+"')";

            conn.s.executeUpdate(query);
            conn.s.executeUpdate("delete from reservation where PNR = '"+pnr+"'");

            JOptionPane.showMessageDialog(null, "Ticket Cancelled");                
            setVisible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }

    public static void main(String[] args) {
        new Cancel();
    }
}