package src.airlinemanagementsystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import com.toedter.calendar.JDateChooser;
import java.util.*;

public class BookFlight extends JFrame implements ActionListener{
    
    JTextField tfaadhar;
    JLabel tfname, tfnationality, tfaddress,labelgender,labelfname,labelfcode;
    JButton bookflight,fetchButton,flight;
    Choice source,destination;
    JDateChooser dcdate;
    
    public BookFlight() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel heading = new JLabel("BOOK FLIGHT");
        heading.setBounds(180, 20, 500, 35);
        heading.setForeground(Color.RED);
        heading.setFont(new Font("Times New Roman", Font.PLAIN, 32));
        add(heading);

        JLabel lblaadhar = new JLabel("Aadhar Number: ");
        lblaadhar.setBounds(50,  100, 150, 25);
        lblaadhar.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblaadhar);
           
        tfaadhar = new JTextField();
        tfaadhar.setBounds(200, 100, 150, 25);
        add(tfaadhar);
        
        JLabel lblname = new JLabel("Name: ");
        lblname.setBounds(50, 140, 150, 25);
        lblname.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblname);

        tfname = new JLabel();
        tfname.setBounds(200, 140, 150, 25);
        add(tfname);

        JLabel lblgender = new JLabel("Gender: ");
        lblgender.setBounds(380, 140, 100, 25);
        lblgender.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblgender);
        
        labelgender = new JLabel();
        labelgender.setBounds(450, 140, 50, 25);
        add(labelgender);
     
        JLabel lblnationality = new JLabel("Nationality: ");
        lblnationality.setBounds(50, 180, 150, 25);
        lblnationality.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblnationality);
        
        tfnationality = new JLabel();
        tfnationality.setBounds(200, 180, 150, 25);
        add(tfnationality);

        JLabel lbladdress = new JLabel("Address: ");
        lbladdress.setBounds(50, 220, 150, 25);
        lbladdress.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lbladdress);
        
        tfaddress = new JLabel();
        tfaddress.setBounds(200, 220, 150, 25);
        add(tfaddress);
 
        JLabel lblfname = new JLabel("Flight Name: ");
        lblfname.setBounds(50, 260, 150, 25);
        lblfname.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblfname);
        
        labelfname = new JLabel();
        labelfname.setBounds(220, 260, 150, 25);
        add(labelfname);
        
        JLabel lblfcode = new JLabel("Flight Code: ");
        lblfcode.setBounds(380, 260, 150, 25);
        lblfcode.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblfcode);
            
        labelfcode = new JLabel();
        labelfcode.setBounds(540, 260, 150, 25);
        add(labelfcode);
        
        JLabel lblsrc = new JLabel("Source: ");
        lblsrc.setBounds(50, 300, 150, 25);
        lblsrc.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblsrc);
        
        source = new Choice();
        source.setBounds(200,300,150,25);
        add(source);
        
        JLabel lbldest= new JLabel("Destination: ");
        lbldest.setBounds(380, 300, 180, 25);
        lbldest.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lbldest);

        destination = new Choice();
        destination.setBounds(560,300,150,25);
        add(destination);
        
        try{
          Conn c = new Conn();
          String query = "select * from flight";
          ResultSet rs = c.s.executeQuery(query);

          while(rs.next()){
            source.add(rs.getString("source"));
            destination.add(rs.getString("destination"));
          }

        } catch(Exception e){
            e.printStackTrace();
        }

        flight= new JButton("Fetch Flight");
        flight.setBackground(Color.BLACK);
        flight.setForeground(Color.WHITE);
        flight.setBounds(320, 340, 120, 25);
        flight.addActionListener(this);
        add(flight);

        bookflight = new JButton("Book Flight");
        bookflight.setBackground(Color.BLUE);
        bookflight.setForeground(Color.WHITE);
        bookflight.setBounds(320, 440, 120, 25);
        bookflight.addActionListener(this);
        add(bookflight);

        JLabel lbldate = new JLabel("Date: ");
        lbldate.setBounds(50, 400, 150, 25);
        lbldate.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lbldate);
        
        dcdate = new JDateChooser();
        dcdate.setBounds(50, 400, 150, 25);
        add(dcdate);
          
        fetchButton = new JButton("Enter");
        fetchButton.setBackground(Color.BLACK);
        fetchButton.setForeground(Color.WHITE);
        fetchButton.setBounds(380, 100, 120, 25);
        fetchButton.addActionListener(this);
        add(fetchButton);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("src/airlinemanagementsystem/icons/icons/details.jpg"));
        Image i2 = i1.getImage().getScaledInstance(330, 210, Image.SCALE_DEFAULT);
        ImageIcon image = new ImageIcon(i2);
        JLabel lblimage = new JLabel(image);
        lblimage.setBounds(520, 40, 330, 210);
        add(lblimage);
        
        setSize(900, 600);
        setLocation(220, 100);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == fetchButton){
           String aadhar = tfaadhar.getText();
           
        try {

            Conn conn = new Conn();

            String query = "select * from passenger where aadhar = '"+aadhar+"'";
            ResultSet rs = conn.s.executeQuery(query);

            if (rs.next()) {
                tfname.setText(rs.getString("name")); 
                tfnationality.setText(rs.getString("nationality"));
                tfaddress.setText(rs.getString("address"));
                labelgender.setText(rs.getString("gender")); 
                labelfname.setText(rs.getString("flightname"));  
                labelfcode.setText(rs.getString("flightcode"));  
            } else {
                JOptionPane.showMessageDialog(null, "Please enter correct aadhar number");                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }else if(ae.getSource() == flight) {
        String src = source.getSelectedItem();
        String dest = destination.getSelectedItem();
           
        try {
            Conn conn = new Conn();

            String query = "select * from flight where source = '"+src+"' and destination = '"+dest+"'";
            ResultSet rs = conn.s.executeQuery(query);

            if (rs.next()) {
                labelfname.setText(rs.getString("flightname"));  
                labelfcode.setText(rs.getString("flightcode"));  
            } else {
                JOptionPane.showMessageDialog(null, "No Flights Found");                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    } else {
        Random random = new Random();
        String aadhar = tfaadhar.getText();
        String name = tfname.getText();
        String nationality = tfnationality.getText();
        String flightname = labelfname.getText();
        String flightcode = labelfcode.getText();
        String src = source.getSelectedItem();
        String des = destination.getSelectedItem();
        String ddate = ((JTextField)dcdate.getDateEditor().getUiComponent()).getText();

        try {
            Conn conn = new Conn();

           String query = "insert into reservation values('PNR-"+random.nextInt(1000000) + "', 'TIC-" + random.nextInt(10000) + "', '" + aadhar + "', '" + name + "', '" + nationality + "', '" + flightname + "', '" + flightcode + "', '" + src + "', '" + des + "', '" + ddate + "')";
           conn.s.executeUpdate(query);

              JOptionPane.showMessageDialog((Component)null, "Ticket Booked Successfully");
              setVisible(false);

        } catch (Exception e) {
           e.printStackTrace();
        }
    

    }

}

    public static void main(String[] args) {
        new BookFlight();
    }
}

