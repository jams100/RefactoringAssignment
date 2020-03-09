import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import java.util.Date;

public class Menu extends JFrame {

	private ArrayList<Customer> customerList = new ArrayList<Customer>();
	private int position = 0;
	private String password;
	private Customer customer = null;
	private CustomerAccount acc = new CustomerAccount();
	JFrame f, f1;
	JLabel firstNameLabel, surnameLabel, pPPSLabel, dOBLabel;
	JTextField firstNameTextField, surnameTextField, pPSTextField, dOBTextField;
	JLabel customerIDLabel, passwordLabel;
	JTextField customerIDTextField, passwordTextField;
	Container content;
	Customer c;

	MenuTypes methods = new MenuTypes();
	JPanel panel2;
	JButton add;
	String PPS, firstName, surname, DOB, CustomerID;

	public static void main(String[] args) {
		Menu driver = new Menu();
		driver.menuStart();
	}

	public void menuStart() {
		/*The menuStart method asks the user if they are a new customer, an existing customer or an admin. It will then start the create customer process
		  if they are a new customer, or will ask them to log in if they are an existing customer or admin.*/

		f = new JFrame("User Type");
		f.setSize(400, 300);
		f.setLocation(200, 200);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});

		JPanel userTypePanel = new JPanel();
		final ButtonGroup userType = new ButtonGroup();
		JRadioButton radioButton;
		userTypePanel.add(radioButton = new JRadioButton("Existing Customer"));
		radioButton.setActionCommand("Customer");
		userType.add(radioButton);

		userTypePanel.add(radioButton = new JRadioButton("Administrator"));
		radioButton.setActionCommand("Administrator");
		userType.add(radioButton);

		userTypePanel.add(radioButton = new JRadioButton("New Customer"));
		radioButton.setActionCommand("New Customer");
		userType.add(radioButton);

		JPanel continuePanel = new JPanel();
		JButton continueButton = new JButton("Continue");
		continuePanel.add(continueButton);

		Container content = f.getContentPane();
		content.setLayout(new GridLayout(2, 1));
		content.add(userTypePanel);
		content.add(continuePanel);

		continueButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String user = userType.getSelection().getActionCommand();
				if(user.equalsIgnoreCase("New Customer")){
					menTypes.createNewCustomer(f, customerList);
				}
				else if(user.equalsIgnoreCase("Customer")){
					menTypes.selectCustomer(f, customerList);
				}
				else if(user.equalsIgnoreCase("Customer")){
					menTypes.selectAdmin(f, f1);
				}
			}
		});
		f.setVisible(true);
	}

	public void admin() {
		dispose();

		f = new JFrame("Administrator Menu");
		f.setSize(400, 400);
		f.setLocation(200, 200);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});
		f.setVisible(true);

		JPanel deleteCustomerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton deleteCustomer = new JButton("Delete Customer");
		deleteCustomer.setPreferredSize(new Dimension(250, 20));
		deleteCustomerPanel.add(deleteCustomer);

		JPanel deleteAccountPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton deleteAccount = new JButton("Delete Account");
		deleteAccount.setPreferredSize(new Dimension(250, 20));
		deleteAccountPanel.add(deleteAccount);

		JPanel bankChargesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton bankChargesButton = new JButton("Apply Bank Charges");
		bankChargesButton.setPreferredSize(new Dimension(250, 20));
		bankChargesPanel.add(bankChargesButton);

		JPanel interestPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton interestButton = new JButton("Apply Interest");
		interestPanel.add(interestButton);
		interestButton.setPreferredSize(new Dimension(250, 20));

		JPanel editCustomerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton editCustomerButton = new JButton("Edit existing Customer");
		editCustomerPanel.add(editCustomerButton);
		editCustomerButton.setPreferredSize(new Dimension(250, 20));

		JPanel navigatePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton navigateButton = new JButton("Navigate Customer Collection");
		navigatePanel.add(navigateButton);
		navigateButton.setPreferredSize(new Dimension(250, 20));

		JPanel summaryPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton summaryButton = new JButton("Display Summary Of All Accounts");
		summaryPanel.add(summaryButton);
		summaryButton.setPreferredSize(new Dimension(250, 20));

		JPanel accountPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton accountButton = new JButton("Add an Account to a Customer");
		accountPanel.add(accountButton);
		accountButton.setPreferredSize(new Dimension(250, 20));

		JPanel returnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton returnButton = new JButton("Exit Admin Menu");
		returnPanel.add(returnButton);

		JLabel label1 = new JLabel("Please select an option");

		content = f.getContentPane();
		content.setLayout(new GridLayout(9, 1));
		content.add(label1);
		content.add(accountPanel);
		content.add(bankChargesPanel);
		content.add(interestPanel);
		content.add(editCustomerPanel);
		content.add(navigatePanel);
		content.add(summaryPanel);
		content.add(deleteCustomerPanel);
		content.add(returnPanel);

		bankChargesButton.addActionListener(new ActionListener(  ) {
			public void actionPerformed(ActionEvent ae) {
				boolean loop = true;
				boolean found = false;

				if(customerList.isEmpty())
				{
					JOptionPane.showMessageDialog(f, "There are no customers yet!"  ,"Oops!",  JOptionPane.INFORMATION_MESSAGE);
					f.dispose();
					admin();
				}
				else
				{
					while(loop)
					{
						Object customerID = JOptionPane.showInputDialog(f, "Customer ID of Customer You Wish to Apply Charges to:");

						for (Customer aCustomer: customerList){

							if(aCustomer.getCustomerID().equals(customerID))
							{
								found = true;
								customer = aCustomer; 
								loop = false;
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
								admin();
							}
						}  
						else
						{
							f.dispose();
							f = new JFrame("Administrator Menu");
							f.setSize(400, 300);
							f.setLocation(200, 200);
							f.addWindowListener(new WindowAdapter() {
								public void windowClosing(WindowEvent we) { System.exit(0); }
							});          
							f.setVisible(true);

							JComboBox<String> box = new JComboBox<String>();
							for (int i =0; i < customer.getAccounts().size(); i++)
							{
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

							if(customer.getAccounts().isEmpty())
							{
								JOptionPane.showMessageDialog(f, "This customer has no accounts! \n The admin must add acounts to this customer."   ,"Oops!",  JOptionPane.INFORMATION_MESSAGE);
								f.dispose();
								admin();
							}
							else
							{
								for(int i = 0; i < customer.getAccounts().size(); i++)
								{
									if(customer.getAccounts().get(i).getNumber() == box.getSelectedItem() )
									{
										acc = customer.getAccounts().get(i);
									}
								}

								continueButton.addActionListener(new ActionListener(  ) {
									public void actionPerformed(ActionEvent ae) {
										String euro = "\u20ac";

										if(acc instanceof CustomerDepositAccount)
										{
											JOptionPane.showMessageDialog(f, "25" + euro + " deposit account fee aplied."  ,"",  JOptionPane.INFORMATION_MESSAGE);
											acc.setBalance(acc.getBalance()-25);
											JOptionPane.showMessageDialog(f, "New balance = " + acc.getBalance() ,"Success!",  JOptionPane.INFORMATION_MESSAGE);
										}
										if(acc instanceof CustomerCurrentAccount)
										{
											JOptionPane.showMessageDialog(f, "15" + euro + " current account fee aplied."  ,"",  JOptionPane.INFORMATION_MESSAGE);
											acc.setBalance(acc.getBalance()-25);
											JOptionPane.showMessageDialog(f, "New balance = " + acc.getBalance() ,"Success!",  JOptionPane.INFORMATION_MESSAGE);
										}

										f.dispose();				
										admin();				
									}		
								});
								returnButton.addActionListener(new ActionListener(  ) {
									public void actionPerformed(ActionEvent ae) {
										f.dispose();		
										menuStart();				
									}
								});	
							}
						}
					}
				}
			}		
		});

		interestButton.addActionListener(new ActionListener(  ) {
			public void actionPerformed(ActionEvent ae) {
				boolean loop = true;
				boolean found = false;

				if(customerList.isEmpty())
				{
					JOptionPane.showMessageDialog(f, "There are no customers yet!"  ,"Oops!",  JOptionPane.INFORMATION_MESSAGE);
					f.dispose();
					admin();
				}
				else
				{
					while(loop)
					{
						Object customerID = JOptionPane.showInputDialog(f, "Customer ID of Customer You Wish to Apply Interest to:");
						for (Customer aCustomer: customerList)
						{
							if(aCustomer.getCustomerID().equals(customerID))
							{
								found = true;
								customer = aCustomer; 
								loop = false;
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
								admin();
							}
						}  
						else
						{
							f.dispose();
							f = new JFrame("Administrator Menu");
							f.setSize(400, 300);
							f.setLocation(200, 200);
							f.addWindowListener(new WindowAdapter() {
								public void windowClosing(WindowEvent we) { System.exit(0); }
							});          
							f.setVisible(true);

							JComboBox<String> box = new JComboBox<String>();
							for (int i =0; i < customer.getAccounts().size(); i++)
							{
								box.addItem(customer.getAccounts().get(i).getNumber());
							}
							box.getSelectedItem();

							JPanel boxPanel = new JPanel();

							JLabel label = new JLabel("Select an account to apply interest to:");
							boxPanel.add(label);
							boxPanel.add(box);
							JPanel buttonPanel = new JPanel();
							JButton continueButton = new JButton("Apply Interest");
							JButton returnButton = new JButton("Return");
							buttonPanel.add(continueButton);
							buttonPanel.add(returnButton);
							Container content = f.getContentPane();
							content.setLayout(new GridLayout(2, 1));

							content.add(boxPanel);
							content.add(buttonPanel);

							if(customer.getAccounts().isEmpty())
							{
								JOptionPane.showMessageDialog(f, "This customer has no accounts! \n The admin must add acounts to this customer."   ,"Oops!",  JOptionPane.INFORMATION_MESSAGE);
								f.dispose();
								admin();
							}
							else
							{
								for(int i = 0; i < customer.getAccounts().size(); i++)
								{
									if(customer.getAccounts().get(i).getNumber() == box.getSelectedItem())
									{
										acc = customer.getAccounts().get(i);
									}
								}

								continueButton.addActionListener(new ActionListener(  ) {
									public void actionPerformed(ActionEvent ae) {
										String euro = "\u20ac";
										double interest = 0;
										boolean loop = true;

										while(loop)
										{
											String interestString = JOptionPane.showInputDialog(f, "Enter interest percentage you wish to apply: \n NOTE: Please enter a numerical value. (with no percentage sign) \n E.g: If you wish to apply 8% interest, enter '8'");//the isNumeric method tests to see if the string entered was numeric. 
											if(isNumeric(interestString))
											{
												interest = Double.parseDouble(interestString);
												loop = false;
												acc.setBalance(acc.getBalance() + (acc.getBalance() * (interest/100)));
												JOptionPane.showMessageDialog(f, interest + "% interest applied. \n new balance = " + acc.getBalance() + euro ,"Success!",  JOptionPane.INFORMATION_MESSAGE);
											}
											else
											{
												JOptionPane.showMessageDialog(f, "You must enter a numerical value!" ,"Oops!",  JOptionPane.INFORMATION_MESSAGE);
											}
										}
										f.dispose();				
										admin();				
									}		
								});

								returnButton.addActionListener(new ActionListener(  ) {
									public void actionPerformed(ActionEvent ae) {
										f.dispose();		
										menuStart();				
									}
								});	
							}
						}
					}
				}
			}	
		});

		editCustomerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				boolean loop = true;
				boolean found = false;

				if (customerList.isEmpty()) {
					JOptionPane.showMessageDialog(f, "There are no customers yet!", "Oops!", JOptionPane.INFORMATION_MESSAGE);
					f.dispose();
					admin();
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
								admin();
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

					firstNameLabel = new JLabel("First Name:", SwingConstants.LEFT);
					surnameLabel = new JLabel("Surname:", SwingConstants.LEFT);
					pPPSLabel = new JLabel("PPS Number:", SwingConstants.LEFT);
					dOBLabel = new JLabel("Date of birth", SwingConstants.LEFT);
					customerIDLabel = new JLabel("CustomerID:", SwingConstants.LEFT);
					passwordLabel = new JLabel("Password:", SwingConstants.LEFT);
					firstNameTextField = new JTextField(20);
					surnameTextField = new JTextField(20);
					pPSTextField = new JTextField(20);
					dOBTextField = new JTextField(20);
					customerIDTextField = new JTextField(20);
					passwordTextField = new JTextField(20);

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

					saveButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent ae) {
							customer.setFirstName(firstNameTextField.getText());
							customer.setSurname(surnameTextField.getText());
							customer.setPPS(pPSTextField.getText());
							customer.setDOB(dOBTextField.getText());
							customer.setCustomerID(customerIDTextField.getText());
							customer.setPassword(passwordTextField.getText());

							JOptionPane.showMessageDialog(null, "Changes Saved.");
						}
					});

					cancelButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent ae) {
							f.dispose();
							admin();
						}
					});
				}
			}
		});

		summaryButton.addActionListener(new ActionListener(  ) {
			public void actionPerformed(ActionEvent ae) {
				f.dispose();
				f = new JFrame("Summary of Transactions");
				f.setSize(400, 700);
				f.setLocation(200, 200);
				f.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent we) { System.exit(0); }
				});          
				f.setVisible(true);

				JLabel label1 = new JLabel("Summary of all transactions: ");

				JPanel returnPanel = new JPanel();
				JButton returnButton = new JButton("Return");
				returnPanel.add(returnButton);

				JPanel textPanel = new JPanel();
				textPanel.setLayout( new BorderLayout() );
				JTextArea textArea = new JTextArea(40, 20);
				textArea.setEditable(false);
				textPanel.add(label1, BorderLayout.NORTH);
				textPanel.add(textArea, BorderLayout.CENTER);
				textPanel.add(returnButton, BorderLayout.SOUTH);

				JScrollPane scrollPane = new JScrollPane(textArea);
				textPanel.add(scrollPane);

				for (int a = 0; a < customerList.size(); a++)//For each customer, for each account, it displays each transaction.
				{
					for (int b = 0; b < customerList.get(a).getAccounts().size(); b ++ )
					{
						acc = customerList.get(a).getAccounts().get(b);
						for (int c = 0; c < customerList.get(a).getAccounts().get(b).getTransactionList().size(); c++)
						{
							textArea.append(acc.getTransactionList().get(c).toString());
						}				
					}				
				}

				textPanel.add(textArea);
				content.removeAll();

				Container content = f.getContentPane();
				content.setLayout(new GridLayout(1, 1));
				content.add(textPanel);

				returnButton.addActionListener(new ActionListener( ) {
					public void actionPerformed(ActionEvent ae) {
						f.dispose();			
						admin();				
					}		
				});	
			}	
		});

		navigateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				f.dispose();

				if (customerList.isEmpty()) {
					JOptionPane.showMessageDialog(null, "There are currently no customers to display. ");
					admin();
				} else {

					JButton first, previous, next, last, cancel;
					JPanel gridPanel, buttonPanel, cancelPanel;

					Container content = getContentPane();
					content.setLayout(new BorderLayout());

					buttonPanel = new JPanel();
					gridPanel = new JPanel(new GridLayout(8, 2));
					cancelPanel = new JPanel();

					firstNameLabel = new JLabel("First Name:", SwingConstants.LEFT);
					surnameLabel = new JLabel("Surname:", SwingConstants.LEFT);
					pPPSLabel = new JLabel("PPS Number:", SwingConstants.LEFT);
					dOBLabel = new JLabel("Date of birth", SwingConstants.LEFT);
					customerIDLabel = new JLabel("CustomerID:", SwingConstants.LEFT);
					passwordLabel = new JLabel("Password:", SwingConstants.LEFT);
					firstNameTextField = new JTextField(20);
					surnameTextField = new JTextField(20);
					pPSTextField = new JTextField(20);
					dOBTextField = new JTextField(20);
					customerIDTextField = new JTextField(20);
					passwordTextField = new JTextField(20);

					first = new JButton("First");
					previous = new JButton("Previous");
					next = new JButton("Next");
					last = new JButton("Last");
					cancel = new JButton("Cancel");

					firstNameTextField.setText(customerList.get(0).getFirstName());
					surnameTextField.setText(customerList.get(0).getSurname());
					pPSTextField.setText(customerList.get(0).getPPS());
					dOBTextField.setText(customerList.get(0).getDOB());
					customerIDTextField.setText(customerList.get(0).getCustomerID());
					passwordTextField.setText(customerList.get(0).getPassword());

					firstNameTextField.setEditable(false);
					surnameTextField.setEditable(false);
					pPSTextField.setEditable(false);
					dOBTextField.setEditable(false);
					customerIDTextField.setEditable(false);
					passwordTextField.setEditable(false);

					gridPanel.add(firstNameLabel);
					gridPanel.add(firstNameTextField);
					gridPanel.add(surnameLabel);
					gridPanel.add(surnameTextField);
					gridPanel.add(pPPSLabel);
					gridPanel.add(pPSTextField);
					gridPanel.add(dOBLabel);
					gridPanel.add(dOBTextField);
					gridPanel.add(customerIDLabel);
					gridPanel.add(customerIDTextField);
					gridPanel.add(passwordLabel);
					gridPanel.add(passwordTextField);

					buttonPanel.add(first);
					buttonPanel.add(previous);
					buttonPanel.add(next);
					buttonPanel.add(last);
					cancelPanel.add(cancel);

					content.add(gridPanel, BorderLayout.NORTH);
					content.add(buttonPanel, BorderLayout.CENTER);
					content.add(cancelPanel, BorderLayout.AFTER_LAST_LINE);
					first.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent ae) {
							position = 0;
							firstNameTextField.setText(customerList.get(0).getFirstName());
							surnameTextField.setText(customerList.get(0).getSurname());
							pPSTextField.setText(customerList.get(0).getPPS());
							dOBTextField.setText(customerList.get(0).getDOB());
							customerIDTextField.setText(customerList.get(0).getCustomerID());
							passwordTextField.setText(customerList.get(0).getPassword());
						}
					});

					previous.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent ae) {
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
						}
					});

					next.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent ae) {
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
						}
					});

					last.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent ae) {
							position = customerList.size() - 1;
							firstNameTextField.setText(customerList.get(position).getFirstName());
							surnameTextField.setText(customerList.get(position).getSurname());
							pPSTextField.setText(customerList.get(position).getPPS());
							dOBTextField.setText(customerList.get(position).getDOB());
							customerIDTextField.setText(customerList.get(position).getCustomerID());
							passwordTextField.setText(customerList.get(position).getPassword());
						}
					});

					cancel.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent ae) {
							dispose();
							admin();
						}
					});
					setContentPane(content);
					setSize(400, 300);
					setVisible(true);
				}
			}
		});

		deleteCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				c.deleteCustomer(f, customerList);
			}
		});

		accountButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				c.viewAccount(f, customerList);
			}
		});

		deleteAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				boolean found = true, loop = true;

				{
					Object customerID = JOptionPane.showInputDialog(f, "Customer ID of Customer from which you wish to delete an account");

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
							admin();
						}
					}
				}
			}
		});
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				f.dispose();
				menuStart();
			}
		});

		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				f.dispose();
				menuStart();
			}
		});
	}

	public void customer(Customer e1) {
		f = new JFrame("Customer Menu");
		e1 = c;
		f.setSize(400, 300);
		f.setLocation(200, 200);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});
		f.setVisible(true);

		if (c.getAccounts().size() == 0) {
			JOptionPane.showMessageDialog(f, "This customer does not have any accounts yet. \n An admin must create an account for this customer \n for them to be able to use customer functionality. ", "Oops!", JOptionPane.INFORMATION_MESSAGE);
			f.dispose();
			menuStart();
		} else {
			JPanel buttonPanel = new JPanel();
			JPanel boxPanel = new JPanel();
			JPanel labelPanel = new JPanel();
			JLabel label = new JLabel("Select Account:");
			labelPanel.add(label);

			JButton returnButton = new JButton("Return");
			buttonPanel.add(returnButton);
			JButton continueButton = new JButton("Continue");
			buttonPanel.add(continueButton);

			JComboBox<String> box = new JComboBox<String>();
			for (int i = 0; i < c.getAccounts().size(); i++) {
				box.addItem(c.getAccounts().get(i).getNumber());
			}

			for (int i = 0; i < c.getAccounts().size(); i++) {
				if (c.getAccounts().get(i).getNumber() == box.getSelectedItem()) {
					acc = c.getAccounts().get(i);
				}
			}

			boxPanel.add(box);
			content = f.getContentPane();
			content.setLayout(new GridLayout(3, 1));
			content.add(labelPanel);
			content.add(boxPanel);
			content.add(buttonPanel);

			returnButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					f.dispose();
					menuStart();
				}
			});

			continueButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					f.dispose();
					f = new JFrame("Customer Menu");
					f.setSize(400, 300);
					f.setLocation(200, 200);
					f.addWindowListener(new WindowAdapter() {
						public void windowClosing(WindowEvent we) {
							System.exit(0);
						}
					});
					f.setVisible(true);

					JPanel statementPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
					JButton statementButton = new JButton("Display Bank Statement");
					statementButton.setPreferredSize(new Dimension(250, 20));

					statementPanel.add(statementButton);

					JPanel lodgementPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
					JButton lodgementButton = new JButton("Lodge money into account");
					lodgementPanel.add(lodgementButton);
					lodgementButton.setPreferredSize(new Dimension(250, 20));

					JPanel withdrawalPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
					JButton withdrawButton = new JButton("Withdraw money from account");
					withdrawalPanel.add(withdrawButton);
					withdrawButton.setPreferredSize(new Dimension(250, 20));

					JPanel returnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
					JButton returnButton = new JButton("Exit Customer Menu");
					returnPanel.add(returnButton);

					JLabel label1 = new JLabel("Please select an option");

					content = f.getContentPane();
					content.setLayout(new GridLayout(5, 1));
					content.add(label1);
					content.add(statementPanel);
					content.add(lodgementPanel);
					content.add(withdrawalPanel);
					content.add(returnPanel);

					statementButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent ae) {
							f.dispose();
							f = new JFrame("Customer Menu");
							f.setSize(400, 600);
							f.setLocation(200, 200);
							f.addWindowListener(new WindowAdapter() {
								public void windowClosing(WindowEvent we) {
									System.exit(0);
								}
							});
							f.setVisible(true);

							JLabel label1 = new JLabel("Summary of account transactions: ");
							JPanel returnPanel = new JPanel();
							JButton returnButton = new JButton("Return");
							returnPanel.add(returnButton);

							JPanel textPanel = new JPanel();
							textPanel.setLayout(new BorderLayout());
							JTextArea textArea = new JTextArea(40, 20);
							textArea.setEditable(false);
							textPanel.add(label1, BorderLayout.NORTH);
							textPanel.add(textArea, BorderLayout.CENTER);
							textPanel.add(returnButton, BorderLayout.SOUTH);

							JScrollPane scrollPane = new JScrollPane(textArea);
							textPanel.add(scrollPane);

							for (int i = 0; i < acc.getTransactionList().size(); i++) {
								textArea.append(acc.getTransactionList().get(i).toString());
							}

							textPanel.add(textArea);
							content.removeAll();

							Container content = f.getContentPane();
							content.setLayout(new GridLayout(1, 1));
							content.add(textPanel);

							returnButton.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent ae) {
									f.dispose();
									customer(c);
								}
							});
						}
					});

					lodgementButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent ae) {
							boolean loop = true;
							boolean on = true;
							double balance = 0;

							if (acc instanceof CustomerCurrentAccount) {
								int count = 3;
								int checkPin = ((CustomerCurrentAccount) acc).getAtm().getPin();
								loop = true;

								while (loop) {
									if (count == 0) {
										JOptionPane.showMessageDialog(f, "Pin entered incorrectly 3 times. ATM card locked.", "Pin", JOptionPane.INFORMATION_MESSAGE);
										((CustomerCurrentAccount) acc).getAtm().setValid(false);
										customer(c);
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
								String balanceTest = JOptionPane.showInputDialog(f, "Enter amount you wish to lodge:");//the isNumeric method tests to see if the string entered was numeric.
								if (isNumeric(balanceTest)) {
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
					});

					withdrawButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent ae) {
							boolean loop = true;
							boolean on = true;
							double withdraw = 0;

							if (acc instanceof CustomerCurrentAccount) {
								int count = 3;
								int checkPin = ((CustomerCurrentAccount) acc).getAtm().getPin();
								loop = true;

								while (loop) {
									if (count == 0) {
										JOptionPane.showMessageDialog(f, "Pin entered incorrectly 3 times. ATM card locked.", "Pin", JOptionPane.INFORMATION_MESSAGE);
										((CustomerCurrentAccount) acc).getAtm().setValid(false);
										customer(c);
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
								String balanceTest = JOptionPane.showInputDialog(f, "Enter amount you wish to withdraw (max 500):");
								if (isNumeric(balanceTest)) {
									withdraw = Double.parseDouble(balanceTest);
									loop = false;
								} else {
									JOptionPane.showMessageDialog(f, "You must enter a numerical value!", "Oops!", JOptionPane.INFORMATION_MESSAGE);
								}
								if (withdraw > 500) {
									JOptionPane.showMessageDialog(f, "500 is the maximum you can withdraw at a time.", "Oops!", JOptionPane.INFORMATION_MESSAGE);
									withdraw = 0;
								}
								if (withdraw > acc.getBalance()) {
									JOptionPane.showMessageDialog(f, "Insufficient funds.", "Oops!", JOptionPane.INFORMATION_MESSAGE);
									withdraw = 0;
								}

								String euro = "\u20ac";
								acc.setBalance(acc.getBalance() - withdraw);
								Date date = new Date();
								String date2 = date.toString();
								String type = "Withdraw";
								double amount = withdraw;

								AccountTransaction transaction = new AccountTransaction(date2, type, amount);
								acc.getTransactionList().add(transaction);

								JOptionPane.showMessageDialog(f, withdraw + euro + " withdrawn.", "Withdraw", JOptionPane.INFORMATION_MESSAGE);
								JOptionPane.showMessageDialog(f, "New balance = " + acc.getBalance() + euro, "Withdraw", JOptionPane.INFORMATION_MESSAGE);
							}
						}
					});

					returnButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent ae) {
							f.dispose();
							menuStart();
						}
					});
				}
			});
		}
	}

	public static boolean isNumeric(String str) { // a method that tests if a string is numeric{
		try {
			double d = Double.parseDouble(str);
		} catch (
				NumberFormatException nfe) {
			return false;
		}
		return true;
	}
}