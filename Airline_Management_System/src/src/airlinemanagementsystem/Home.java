package src.airlinemanagementsystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Home extends JFrame implements ActionListener{

    public Home(){
        setLayout(null);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("src/airlinemanagementsystem/icons/icons/front.jpg"));
        JLabel lblimage = new JLabel(i1);
        lblimage.setBounds(0,0,1600,800);
        add(lblimage);

        JLabel heading = new JLabel("AIR INDIA WELCOMES YOU");
        heading.setBounds(400,50,1000,80);
        heading.setForeground(Color.RED);
        heading.setFont(new Font("Times New Roman", Font.BOLD, 40));
        lblimage.add(heading);

        JMenuBar menubar = new JMenuBar();
        setJMenuBar(menubar);

        JMenu details = new JMenu("Details");
        menubar.add(details);

        JMenuItem flightDetails = new JMenuItem("Flight Details");
        flightDetails.addActionListener(this);
        details.add(flightDetails);

        JMenuItem bookFlight = new JMenuItem("Book Flight");
        bookFlight.addActionListener(this);
        details.add(bookFlight);

        JMenuItem ticketCancellation = new JMenuItem("Cancel Ticket");
        ticketCancellation.addActionListener(this);
        details.add(ticketCancellation);

        JMenu ticket = new JMenu("Ticket");
        menubar.add(ticket);

        JMenuItem boardingPass = new JMenuItem("Boarding Pass");
        boardingPass.addActionListener(this);
        ticket.add(boardingPass);

     
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent ae) {
        String text = ae.getActionCommand();

        if(text.equals("Flight Details")) {
            new FlightInfo();
        }
        else if(text.equals("Boarding Pass")) {
            new BoardingPass();
        }
        else if(text.equals("Cancel Ticket")) {
            new Cancel();
        }
        else if(text.equals("Book Flight")) {
            new BookFlight();
        }

    }
    
    public static void main(String[] args) {
        new Home();
    }
}