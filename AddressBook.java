//welcome to Addressbook Java probelm
package addressbook;
import java.io.File;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
class AddressBook
{
          //declaration with pattern
            static String firstNamePattern = "^[a-zA-Z][a-zA-Z ]*$";
	    static String lastNamePattern = "^[a-zA-Z][a-zA-Z ]*$";
	    static String addressPattern = "^[a-zA-Z0-9-,. ]+$";
	    static String cityPattern = "^[a-zA-Z][a-zA-Z ]*$";
	    static String statePattern = "^[a-zA-Z][a-zA-Z ]*$";
	    static String zipPattern = "^\\d{6}$";
	    static String phoneNumberPattern = "^\\d{10}$";
     //scanner variable is declared as static to use throughout the program
    static final Scanner scanner = new Scanner(System.in);
    //contains set of contacts which are empty(contact created and no details added)
    static Set<String> emptyContacts = new HashSet<>();
    //contains set of contacts which are non empty(contact created and details added)
    static Set<String> nonEmptyContacts = new HashSet<>();

    // instance methods
    //creates empty contact
    void createNewContact() throws Exception 
    {
        System.out.print("Enter name of the contact:");
        String contactName = scanner.nextLine();
        File file = new File(contactName);
        if (file.exists()) 
        {
            System.out.println("contact " + file.getName() + " already exists!");
        } 
        else 
        {
            if (file.createNewFile()) 
            {
                System.out.println("new contact " + file.getName() + " is created successfully");
                emptyContacts.add(file.getName());
            } 
            else 
            {
                System.out.println("file creation failed!");
            }
        }
    }
}	
	
