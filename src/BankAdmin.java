import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class BankAdmin {

	Menu m = new Menu();
	public BankAdmin() {
	}

	public void bankCharges(CustomerAccount acc, ArrayList<Customer> customerList, JFrame f, Customer customer){
		boolean loop = true;
		boolean found = false;

		if (customerList.isEmpty()) {
			JOptionPane.showMessageDialog(f, "There are no customers yet!", "Oops!", JOptionPane.INFORMATION_MESSAGE);
			f.dispose();
			m.admin();

		} else {
			while (loop) {
				Object customerID = JOptionPane.showInputDialog(f, "Customer ID of Customer You Wish to Apply Charges to:");

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
					f.dispose();
					f = new JFrame("Administrator Menu");
					f.setSize(400, 300);
					f.setLocation(200, 200);
					f.addWindowListener(new WindowAdapter() {
						public void windowClosing(WindowEvent we) {
							System.exit(0);
						}
					});
					f.setVisible(true);

					JComboBox<String> box = new JComboBox<String>();
					for (int i = 0; i < customer.getAccounts().size(); i++) {
						box.addItem(customer.getAccounts().get(i).getNumber());
					}

					box.getSelectedItem();

					JPanel boxPanel = new JPanel();
					boxPanel.add(box);

					JPanel buttonPanel = new JPanel();
					JButton continueButton = new JButton("Apply Charge");
					JButton returnButton = new JButton("Return");
					buttonPanel.add(continueButton);
					buttonPanel.add(returnButton);
					Container content = f.getContentPane();
					content.setLayout(new GridLayout(2, 1));
					content.add(boxPanel);
					content.add(buttonPanel);

					if (customer.getAccounts().isEmpty()) {
						JOptionPane.showMessageDialog(f, "This customer has no accounts! \n The admin must add acounts to this customer.", "Oops!", JOptionPane.INFORMATION_MESSAGE);
						f.dispose();
						m.admin();
					} else {
						for (int i = 0; i < customer.getAccounts().size(); i++) {
							if (customer.getAccounts().get(i).getNumber() == box.getSelectedItem()) {
								acc = customer.getAccounts().get(i);
							}
						}

						CustomerAccount finalAcc = acc;
						JFrame finalF = f;
						continueButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent ae) {
								String euro = "\u20ac";
								if (finalAcc instanceof CustomerDepositAccount) {
									JOptionPane.showMessageDialog(finalF, "25" + euro + " deposit account fee aplied.", "", JOptionPane.INFORMATION_MESSAGE);
									finalAcc.setBalance(finalAcc.getBalance() - 25);
									JOptionPane.showMessageDialog(finalF, "New balance = " + finalAcc.getBalance(), "Success!", JOptionPane.INFORMATION_MESSAGE);
								}

								if (finalAcc instanceof CustomerCurrentAccount) {
									JOptionPane.showMessageDialog(finalF, "15" + euro + " current account fee aplied.", "", JOptionPane.INFORMATION_MESSAGE);
									finalAcc.setBalance(finalAcc.getBalance() - 25);
									JOptionPane.showMessageDialog(finalF, "New balance = " + finalAcc.getBalance(), "Success!", JOptionPane.INFORMATION_MESSAGE);
								}
								finalF.dispose();
								m.admin();
							}
						});

						JFrame finalF1 = f;
						returnButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent ae) {
								finalF1.dispose();
								m.menuStart();
							}
						});
					}
				}
			}
		}
	}
}