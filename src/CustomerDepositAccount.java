import java.util.ArrayList;
import javax.swing.*;
import java.util.Date;

public class CustomerDepositAccount extends CustomerAccount
{
	double interestRate;

	public CustomerDepositAccount(){
		super();
		this.interestRate = 0;
	}

	public CustomerDepositAccount(double interestRate, String number, double balance, ArrayList<AccountTransaction> transactionList){
		super(number, balance, transactionList);	
		this.interestRate = interestRate;
	}

	public double getInterestRate(){
		return this.interestRate;
	}

	public void setInterestRate(double interestRate){
		this.interestRate = interestRate;
	}

	public void lodge(CustomerAccount acc, Customer e,JFrame f) {
		boolean loop = true;
		boolean on = true;
		double balance = 0;
		Menu m = new Menu();

		if (acc instanceof CustomerCurrentAccount) {
			int count = 3;
			int checkPin = ((CustomerCurrentAccount) acc).getAtm().getPin();
			loop = true;

			while (loop) {
				if (count == 0) {
					JOptionPane.showMessageDialog(f, "Pin entered incorrectly 3 times. ATM card locked.", "Pin", JOptionPane.INFORMATION_MESSAGE);
					((CustomerCurrentAccount) acc).getAtm().setValid(false);
					m.customer(e);
					loop = false;
					on = false;
				}

				String Pin = JOptionPane.showInputDialog(f, "Enter 4 digit PIN;");
				int i = Integer.parseInt(Pin);

				if (on) {
					if (checkPin == i) {
						loop = false;
						JOptionPane.showMessageDialog(f, "Pin entry successful", "Pin", JOptionPane.INFORMATION_MESSAGE);
					} else {
						count--;
						JOptionPane.showMessageDialog(f, "Incorrect pin. " + count + " attempts remaining.", "Pin", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		}
		if (on == true) {
			String balanceTest = JOptionPane.showInputDialog(f, "Enter amount you wish to lodge:");
			//This Numeric method tests to see if the string entered was numeric.
			if (m.isNumeric(balanceTest)) {
				balance = Double.parseDouble(balanceTest);
				loop = false;

			} else {
				JOptionPane.showMessageDialog(f, "You must enter a numerical value!", "Oops!", JOptionPane.INFORMATION_MESSAGE);
			}

			String euro = "\u20ac";
			acc.setBalance(acc.getBalance() + balance);
			Date date = new Date();
			String date2 = date.toString();
			String type = "Lodgement";
			double amount = balance;

			AccountTransaction transaction = new AccountTransaction(date2, type, amount);
			acc.getTransactionList().add(transaction);

			JOptionPane.showMessageDialog(f, balance + euro + " added do you account!", "Lodgement", JOptionPane.INFORMATION_MESSAGE);
			JOptionPane.showMessageDialog(f, "New balance = " + acc.getBalance() + euro, "Lodgement", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}