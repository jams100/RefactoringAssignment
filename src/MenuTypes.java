import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

public class MenuTypes {
    Menu menu = new Menu();

    public void createNewCustomer(JFrame f, ArrayList<Customer> customerList) {
        f.dispose();
        JFrame f1 = new JFrame("Create New Customer");
        f1.setSize(400, 300);
        f1.setLocation(200, 200);
        f1.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
        Container content = f1.getContentPane();
        content.setLayout(new BorderLayout());

        JLabel firstNameLabel = new JLabel("First Name:", SwingConstants.RIGHT);
        JLabel surnameLabel = new JLabel("Surname:", SwingConstants.RIGHT);
        JLabel pPPSLabel = new JLabel("PPS Number:", SwingConstants.RIGHT);
        JLabel dOBLabel = new JLabel("Date of birth", SwingConstants.RIGHT);
        JTextField firstNameTextField = new JTextField(20);
        JTextField surnameTextField = new JTextField(20);
        JTextField pPSTextField = new JTextField(20);
        JTextField dOBTextField = new JTextField(20);
        JPanel panel = new JPanel(new GridLayout(6, 2));
        panel.add(firstNameLabel);
        panel.add(firstNameTextField);
        panel.add(surnameLabel);
        panel.add(surnameTextField);
        panel.add(pPPSLabel);
        panel.add(pPSTextField);
        panel.add(dOBLabel);
        panel.add(dOBTextField);

        JPanel panel2 = new JPanel();
        JButton add = new JButton("Add");

        add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String PPS = pPSTextField.getText();
                String firstName = firstNameTextField.getText();
                String surname = surnameTextField.getText();
                String DOB = dOBTextField.getText();
                final String[] password = {""};
                String CustomerID = "ID" + PPS;

                add.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        f1.dispose();
                        boolean loop = true;
                        while (loop) {
                            password[0] = JOptionPane.showInputDialog(f, "Enter 7 character Password;");
                            if (password[0].length() != 7)
                            {
                                JOptionPane.showMessageDialog(null, null, "Password must be 7 charatcers long", JOptionPane.OK_OPTION);
                            } else {
                                loop = false;
                            }
                        }
                        ArrayList<CustomerAccount> accounts = new ArrayList<CustomerAccount>();
                        Customer customer = new Customer(PPS, surname, firstName, DOB, CustomerID, password[0], accounts);
                        customerList.add(customer);

                        JOptionPane.showMessageDialog(f, "CustomerID = " + CustomerID + "\n Password = " + password[0], "Customer created.", JOptionPane.INFORMATION_MESSAGE);
                        menu.menuStart();
                        f.dispose();
                    }
                });
            }
        });
        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f1.dispose();
                menu.menuStart();
            }
        });
        panel2.add(add);
        panel2.add(cancel);

        content.add(panel, BorderLayout.CENTER);
        content.add(panel2, BorderLayout.SOUTH);
    }

    
	//*************************************************************************************************************************
	//*********************************************SELECT EXISTING CUSTOMER*************************************************************
	//*************************************************************************************************************************
    public void selectCustomer(JFrame f,  ArrayList<Customer> customerList) {
        boolean loop = true, loop2 = true;
        boolean cont = false;
        boolean found = false;
        Customer customer = null;
        while (loop) {
            Object customerID = JOptionPane.showInputDialog(f, "Enter Customer ID:");

            for (Customer aCustomer : customerList) {
                if (aCustomer.getCustomerID().equals(customerID))//search customer list for matching customer ID
                {
                    found = true;
                    customer = aCustomer;
                }
            }

            if (found == false) {
                int reply = JOptionPane.showConfirmDialog(null, null, "User not found. Try again?", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    loop = true;
                } else if (reply == JOptionPane.NO_OPTION) {
                    f.dispose();
                    loop = false;
                    loop2 = false;
                    menu.menuStart();
                }
            } else {
                loop = false;
            }
        }

        while (loop2) {
            Object customerPassword = JOptionPane.showInputDialog(f, "Enter Customer Password;");
            if (!customer.getPassword().equals(customerPassword))//check if customer password is correct
            {
                int reply = JOptionPane.showConfirmDialog(null, null, "Incorrect password. Try again?", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {

                } else if (reply == JOptionPane.NO_OPTION) {
                    f.dispose();
                    loop2 = false;
                    menu.menuStart();
                }
            } else {
                loop2 = false;
                cont = true;
            }
        }
        if (cont) {
            f.dispose();
            menu.customer(customer);
        }
    }
    
	//*************************************************************************************************************************
	//*********************************************SELECT ADMIN******************************************************************
	//*************************************************************************************************************************
    public void selectAdmin(JFrame f, JFrame f1) {
        boolean loop = true, loop2 = true;
        boolean cont = false;
        while (loop) {
            Object adminUsername = JOptionPane.showInputDialog(f, "Enter Administrator Username:");

            if (!adminUsername.equals("admin"))
            {
                int reply = JOptionPane.showConfirmDialog(null, null, "Incorrect Username. Try again?", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    loop = true;
                } else if (reply == JOptionPane.NO_OPTION) {
                    loop = false;
                    loop2 = false;
                    menu.menuStart();
                }
            } else {
                loop = false;
            }
        }

        while (loop2) {
            Object adminPassword = JOptionPane.showInputDialog(f, "Enter Administrator Password;");
            if (!adminPassword.equals("admin11"))
            {
                int reply = JOptionPane.showConfirmDialog(null, null, "Incorrect Password. Try again?", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                } else if (reply == JOptionPane.NO_OPTION) {
                    f1.dispose();
                    loop2 = false;
                    menu.menuStart();
                }
            } else {
                loop2 = false;
                cont = true;
            }
        }
        if (cont) {
            menu.admin();
        }
    }
}