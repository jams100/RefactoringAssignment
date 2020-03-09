# RefactoringAssignment

1. First task was to download the project and do an initial commit to GitHub.

2. I Removed most comments and unnecessary line breaks. If Code is written well enough
   comments are not needed to explain the purpose of it. Unnecessary line breaks were also
   removed to shorten code.
  
3. I removed the lodge money functionality to the customer deposit account. It makes sense
   to break up the code into different classes and encapsulate that logic. This again makes
   the code much easier to read/understand.
  
4. Again to seperate all the logic into different classes it makes sense to create a BankAdmin class.
   The bank charges were then removed from the Menu.java class to the BankAdmin class, once again 
   these changes would make it easier if you had to add code to the Menu.java class as you don't 
   have to worry about everything at once when your writing new code. 
   
5. Like step 3 & 4 logic all the customer functions were removed into the Customer.java class

6. I Removed a redundant constructor from the Customer class. Redundant code is no use as it will add extra unnecessary
   ambiguity and complexity to the code without providing and extra value. It will also add extra time to the compliliation
   process, as minuscule as it may be. (At next commit I realised it was actually needed)

7. I Created a new class called MenuTypes which in the next commit should handle the dialog for creating a new customer etc.
   I also removed an else if statement that was never used. 
  
7.1 I created the MenuTypes.java class to try make the code easier to understand

8. I went through the code and kept an eye out for any variables that were named poorly. Most of the varibales were 
   actually named ok, but some were named poorly, such as when declaring an ActionEvent, like so ActionEvent ae. 'e' is a poor
   variable name choice as it does not provide any hint to the person reading the code as to its purpose or relevance. 
   
9. Refactored a method that wasn't doing anything and changed it so that it now makes sense.

10. Added comments in the Menu classes to make it easier to find a particular method.





