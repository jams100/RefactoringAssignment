import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.Array;

public class Customer {

	String PPS ="";
	String surname = "";
	String firstName = "";
	String DOB ="";
	String customerID = "";
	String password = "";

	ArrayList<CustomerAccount> accounts = new ArrayList<CustomerAccount> ();

	public Customer(){
		this.PPS = "";
		this.surname = "";
		this.firstName = "";
		this.DOB = "";
		this.customerID = "";
		this.password = "";
		this.accounts = null;
	}

	public Customer(String PPS, String surname, String firstName, String DOB, String customerID, String password, ArrayList<CustomerAccount> accounts){
		this.PPS = PPS;
		this.surname = surname;
		this.firstName = firstName;
		this.DOB = DOB;
		this.customerID = customerID;
		this.password = password;;
		this.accounts = accounts;
	}

	public String getPPS(){
		return this.PPS;
	}

	public String getSurname(){
		return this.surname;
	}

	public String getFirstName(){
		return this.firstName;
	}

	public String getDOB(){
		return this.DOB;
	}

	public String getCustomerID(){
		return this.customerID;
	}

	public String getPassword(){
		return this.password;
	}

	public ArrayList<CustomerAccount> getAccounts(){
		return this.accounts;
	}

	public void setPPS(String PPS){
		this.PPS = PPS;
	}

	public void setSurname(String surname){
		this.surname = surname;
	}

	public void setFirstName(String firstName){
		this.firstName = firstName;
	}

	public void setDOB(String DOB){
		this.DOB = DOB;
	}

	public void setCustomerID(String customerID){
		this.customerID = customerID;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public void setAccounts(ArrayList<CustomerAccount> accounts){
		this.accounts = accounts;
	}
	
	public void addCustomer(JFrame f1, JFrame f, ArrayList<Customer> customerList, String CustomerID){
		f1.dispose();
		Menu m = new Menu();

		boolean loop = true;
		while(loop){
			password = JOptionPane.showInputDialog(f, "Enter 7 character Password;");

			if(password.length() != 7)
			{
				JOptionPane.showMessageDialog(null, null, "Password must be 7 charatcers long", JOptionPane.OK_OPTION);
			}
			else
			{
				loop = false;
			}
		}
		ArrayList<CustomerAccount> accounts = new ArrayList<CustomerAccount> ();
	    Customer customer = new Customer(PPS, surname, firstName, DOB, CustomerID, password, accounts);
	    customerList.add(customer);

	    JOptionPane.showMessageDialog(f, "CustomerID = " + CustomerID +"\n Password = " + password  ,"Customer created.",  JOptionPane.INFORMATION_MESSAGE);
	    m.menuStart();
	    f.dispose();
	}
	
	public void viewAccount(JFrame f, ArrayList<Customer> customerList){
        Menu m = new Menu();
        Customer customer = new Customer();
        f.dispose();

        if(customerList.isEmpty())
        {
            JOptionPane.showMessageDialog(f, "There are no customers yet!"  ,"Oops!",  JOptionPane.INFORMATION_MESSAGE);
            f.dispose();
            m.admin();
        }
        else
        {
            boolean loop = true;
            boolean found = false;

            while(loop)
            {
                Object customerID = JOptionPane.showInputDialog(f, "Customer ID of Customer You Wish to Add an Account to:");

                for (Customer aCustomer: customerList){
                    if(aCustomer.getCustomerID().equals(customerID))
                    {
                        found = true;
                        customer = aCustomer;
                    }
                }

                if(found == false)
                {
                    int reply  = JOptionPane.showConfirmDialog(null, null, "User not found. Try again?", JOptionPane.YES_NO_OPTION);
                    if (reply == JOptionPane.YES_OPTION) {
                        loop = true;
                    }
                    else if(reply == JOptionPane.NO_OPTION)
                    {
                        f.dispose();
                        loop = false;
                        m.admin();
                    }
                }
                else
                {
                    loop = false;
                    //A Combo box in an dialog box that asks the Admin what type of account they wish to create
                    String[] choices = { "Current Account", "Deposit Account" };
                    String account = (String) JOptionPane.showInputDialog(null, "Please choose account type",
                            "Account Type", JOptionPane.QUESTION_MESSAGE, null, choices, choices[1]);

                    if(account.equals("Current Account"))
                    {
                        boolean valid = true;
                        double balance = 0;
                        String number = String.valueOf("C" + (customerList.indexOf(customer)+1) * 10 + (customer.getAccounts().size()+1));//this simple algorithm generates the account number
                        ArrayList<AccountTransaction> transactionList = new ArrayList<AccountTransaction>();
                        int randomPIN = (int)(Math.random()*9000)+1000;
                        String pin = String.valueOf(randomPIN);

                        ATMCard atm = new ATMCard(randomPIN, valid);

                        CustomerCurrentAccount current = new CustomerCurrentAccount(atm, number, balance, transactionList);

                        customer.getAccounts().add(current);
                        JOptionPane.showMessageDialog(f, "Account number = " + number +"\n PIN = " + pin  ,"Account created.",  JOptionPane.INFORMATION_MESSAGE);

                        f.dispose();
                        m.admin();
                    }

                    if(account.equals("Deposit Account"))
                    {
                        double balance = 0, interest = 0;
                        String number = String.valueOf("D" + (customerList.indexOf(customer)+1) * 10 + (customer.getAccounts().size()+1));//this simple algorithm generates the account number
                        ArrayList<AccountTransaction> transactionList = new ArrayList<AccountTransaction>();

                        CustomerDepositAccount deposit = new CustomerDepositAccount(interest, number, balance, transactionList);

                        customer.getAccounts().add(deposit);
                        JOptionPane.showMessageDialog(f, "Account number = " + number ,"Account created.",  JOptionPane.INFORMATION_MESSAGE);
                        f.dispose();
                        m.admin();
                    }
                }
            }
        }
    }
	
	public void deleteCustomer(JFrame f, ArrayList<Customer> customerList){
        Customer customer = new Customer();
        Menu m = new Menu();
        boolean found = true, loop = true;

        if (customerList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "There are currently no customers to display. ");
            m.dispose();
            m.admin();
        } else {
            {
                Object customerID = JOptionPane.showInputDialog(f, "Customer ID of Customer You Wish to Delete:");
                for (Customer aCustomer : customerList) {

                    if (aCustomer.getCustomerID().equals(customerID)) {
                        found = true;
                        customer = aCustomer;
                        loop = false;
                    }
                }

                if (found == false) {
                    int reply = JOptionPane.showConfirmDialog(null, null, "User not found. Try again?", JOptionPane.YES_NO_OPTION);
                    if (reply == JOptionPane.YES_OPTION) {
                        loop = true;
                    } else if (reply == JOptionPane.NO_OPTION) {
                        f.dispose();
                        loop = false;
                        m.admin();
                    }
                } else {
                    if (customer.getAccounts().size() > 0) {
                        JOptionPane.showMessageDialog(f, "This customer has accounts. \n You must delete a customer's accounts before deleting a customer ", "Oops!", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        customerList.remove(customer);
                        JOptionPane.showMessageDialog(f, "Customer Deleted ", "Success.", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        }
    }
	
	public void EditCust(JFrame f, ArrayList<Customer> customerList) {
        boolean loop = true;
        Menu m = new Menu();
        boolean found = false;
        Customer customer = new Customer();

        if (customerList.isEmpty()) {
            JOptionPane.showMessageDialog(f, "There are no customers yet!", "Oops!", JOptionPane.INFORMATION_MESSAGE);
            f.dispose();
            m.admin();

        } else {

            while (loop) {
                Object customerID = JOptionPane.showInputDialog(f, "Enter Customer ID:");
                for (Customer aCustomer : customerList) {
                    if (aCustomer.getCustomerID().equals(customerID)) {
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
                        m.admin();
                    }
                } else {
                    loop = false;
                }

            }

            f.dispose();
            f.dispose();
            f = new JFrame("Administrator Menu");
            f.setSize(400, 300);
            f.setLocation(200, 200);
            f.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent we) {
                    System.exit(0);
                }
            });

            JLabel firstNameLabel = new JLabel("First Name:", SwingConstants.LEFT);
            JLabel surnameLabel = new JLabel("Surname:", SwingConstants.LEFT);
            JLabel pPPSLabel = new JLabel("PPS Number:", SwingConstants.LEFT);
            JLabel dOBLabel = new JLabel("Date of birth", SwingConstants.LEFT);
            JLabel  customerIDLabel = new JLabel("CustomerID:", SwingConstants.LEFT);
            JLabel passwordLabel = new JLabel("Password:", SwingConstants.LEFT);
            JTextField firstNameTextField = new JTextField(20);
            JTextField surnameTextField = new JTextField(20);
            JTextField pPSTextField = new JTextField(20);
            JTextField dOBTextField = new JTextField(20);
            JTextField customerIDTextField = new JTextField(20);
            JTextField passwordTextField = new JTextField(20);

            JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

            JPanel cancelPanel = new JPanel();

            textPanel.add(firstNameLabel);
            textPanel.add(firstNameTextField);
            textPanel.add(surnameLabel);
            textPanel.add(surnameTextField);
            textPanel.add(pPPSLabel);
            textPanel.add(pPSTextField);
            textPanel.add(dOBLabel);
            textPanel.add(dOBTextField);
            textPanel.add(customerIDLabel);
            textPanel.add(customerIDTextField);
            textPanel.add(passwordLabel);
            textPanel.add(passwordTextField);

            firstNameTextField.setText(customer.getFirstName());
            surnameTextField.setText(customer.getSurname());
            pPSTextField.setText(customer.getPPS());
            dOBTextField.setText(customer.getDOB());
            customerIDTextField.setText(customer.getCustomerID());
            passwordTextField.setText(customer.getPassword());

            JButton saveButton = new JButton("Save");
            JButton cancelButton = new JButton("Exit");

            cancelPanel.add(cancelButton, BorderLayout.SOUTH);
            cancelPanel.add(saveButton, BorderLayout.SOUTH);
            Container content = f.getContentPane();
            content.setLayout(new GridLayout(2, 1));
            content.add(textPanel, BorderLayout.NORTH);
            content.add(cancelPanel, BorderLayout.SOUTH);

            f.setContentPane(content);
            f.setSize(340, 350);
            f.setLocation(200, 200);
            f.setVisible(true);
            f.setResizable(false);

            Customer finalCustomer = customer;
            saveButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    finalCustomer.setFirstName(firstNameTextField.getText());
                    finalCustomer.setSurname(surnameTextField.getText());
                    finalCustomer.setPPS(pPSTextField.getText());
                    finalCustomer.setDOB(dOBTextField.getText());
                    finalCustomer.setCustomerID(customerIDTextField.getText());
                    finalCustomer.setPassword(passwordTextField.getText());
                    JOptionPane.showMessageDialog(null, "Changes Saved.");
                }
            });

            JFrame finalF = f;
            cancelButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    finalF.dispose();
                    m.admin();
                }
            });
        }
    }
	
	public void getAtPosition(ArrayList<Customer> customerList, int position, JTextField firstNameTextField, JTextField surnameTextField, JTextField pPSTextField, JTextField dOBTextField, JTextField customerIDTextField, JTextField passwordTextField, String type) {
        if (type.equals("next")) {
            if (position == customerList.size() - 1) {
            } else {
                position = position + 1;
                firstNameTextField.setText(customerList.get(position).getFirstName());
                surnameTextField.setText(customerList.get(position).getSurname());
                pPSTextField.setText(customerList.get(position).getPPS());
                dOBTextField.setText(customerList.get(position).getDOB());
                customerIDTextField.setText(customerList.get(position).getCustomerID());
                passwordTextField.setText(customerList.get(position).getPassword());
            }
        } else if (type.equals("previous")) {
            if (position < 1) {    
            } else {
                position = position - 1;
                firstNameTextField.setText(customerList.get(position).getFirstName());
                surnameTextField.setText(customerList.get(position).getSurname());
                pPSTextField.setText(customerList.get(position).getPPS());
                dOBTextField.setText(customerList.get(position).getDOB());
                customerIDTextField.setText(customerList.get(position).getCustomerID());
                passwordTextField.setText(customerList.get(position).getPassword());
            }

        } else if (type.equals("first")) {
            if (position < 1) {
            } else {
                position = position - 1;
                firstNameTextField.setText(customerList.get(position).getFirstName());
                surnameTextField.setText(customerList.get(position).getSurname());
                pPSTextField.setText(customerList.get(position).getPPS());
                dOBTextField.setText(customerList.get(position).getDOB());
                customerIDTextField.setText(customerList.get(position).getCustomerID());
                passwordTextField.setText(customerList.get(position).getPassword());
            }
        } else if (type.equals("last")) {
            position = customerList.size() - 1;
            firstNameTextField.setText(customerList.get(position).getFirstName());
            surnameTextField.setText(customerList.get(position).getSurname());
            pPSTextField.setText(customerList.get(position).getPPS());
            dOBTextField.setText(customerList.get(position).getDOB());
            customerIDTextField.setText(customerList.get(position).getCustomerID());
            passwordTextField.setText(customerList.get(position).getPassword());
        }
    }
	
	public String toString(){
		return "PPS number = " + this.PPS + "\n" + "Surname = " + this.surname + "\n" + "First Name = " + this.firstName + "\n"
				+ "Date of Birth = " + this.DOB + "\n" + "Customer ID = " + this.customerID;
	}
}