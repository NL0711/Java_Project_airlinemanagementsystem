package src.airlinemanagementsystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AdminHome extends JFrame implements ActionListener {

    public AdminHome() {
        setLayout(null);
        setTitle("Airline Management Admin Homepage");
        setSize(1600, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel heading = new JLabel("ADMIN CONTROLS");
        heading.setBounds(400,50,1000,80);
        heading.setForeground(Color.RED);
        heading.setFont(new Font("Times New Roman", Font.BOLD, 40));
        add(heading);

        JMenuBar menubar = new JMenuBar();
        setJMenuBar(menubar);

        JMenu flightMenu = new JMenu("Flight Schedules");
        menubar.add(flightMenu);
        JMenuItem viewFlights = new JMenuItem("View Flights");
        flightMenu.add(viewFlights);
        viewFlights.addActionListener(this);
        JMenuItem addFlight = new JMenuItem("Add Flight");
        flightMenu.add(addFlight);
        addFlight.addActionListener(this);

        JMenu customerMenu = new JMenu("Customer Accounts");
        menubar.add(customerMenu);
        JMenuItem viewCustomers = new JMenuItem("View Customers");
        customerMenu.add(viewCustomers);
        viewCustomers.addActionListener(this);
        JMenuItem addCustomers = new JMenuItem("Add Customers");
        customerMenu.add(addCustomers);
        addCustomers.addActionListener(this);


        JMenu aircraftMenu = new JMenu("Aircraft Management");
        menubar.add(aircraftMenu);
        JMenuItem addAircraft = new JMenuItem("Add Aircraft");
        aircraftMenu.add(addAircraft);
        addAircraft.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        String text = ae.getActionCommand();

        if (text.equals("View Flights")) {
           new FlightInfo();
        } else if (text.equals("Add Flight")) {
            JOptionPane.showMessageDialog(this, "Adding a new flight");
        } else if (text.equals("View Customers")) {
            JOptionPane.showMessageDialog(this, "Viewing customer accounts");
        } else if (text.equals("Add Customers")) {
           new AddCustomer();
        } else if (text.equals("Add Aircraft")) {
            JOptionPane.showMessageDialog(this, "Adding a new aircraft");
        }
    }

    public static void main(String[] args) {
        new AdminHome();
    }
}
