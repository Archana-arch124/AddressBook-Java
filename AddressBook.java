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
	void writeFile(String fileName,String content)throws Exception
    {
        FileWriter fw = new FileWriter(fileName);
        fw.write(content);
        fw.close();
        nonEmptyContacts.add(fileName);
    }
   
    Boolean validate(String name, String regex) 
    {
        return Pattern.matches(regex, name);
    }

    String takeInput(String field, String pattern) 
    {
        String input;
        do 
        {
            System.out.print("enter " + field + ":");
            input = scanner.nextLine();
        } while (!validate(input, pattern));
        return input;
    }

    //given fields are added into contact
    void fillContactDetails()throws Exception
    {
        System.out.print("enter empty contact name which is going to be filed:");
        String contactName = scanner.nextLine();
        if (emptyContacts.contains(contactName)) 
        {
            String details = "";
            details += takeInput("first name", firstNamePattern) + "\n";
            details += takeInput("last name", lastNamePattern) + "\n";
            details += takeInput("address", addressPattern) + "\n";
            details += takeInput("city", cityPattern) + "\n";
            details += takeInput("state", statePattern) + "\n";
            details += takeInput("zip code", zipPattern) + "\n";
            details += takeInput("phone number", phoneNumberPattern) + "\n";
            writeFile(contactName, details);
            emptyContacts.remove(contactName);
            System.out.println("the given fields are successfully added in " + contactName);
        }
        else 
        {
            System.out.println(contactName + " is not empty contact or it is not created");
            System.out.println("use other option c to create new contact or option e to edit already created one");
        }
    }
	void displayAllContacts() 
    {
        boolean flag = false;
        if (emptyContacts.size() != 0) 
        {
            System.out.println("the empty contacts are:");
            for (String contact : emptyContacts) 
            {
                System.out.println(contact);
            }
            flag=true;
        }
        if (nonEmptyContacts.size() != 0) 
        {
            System.out.println("the non empty contacts are:");
            for (String contact : nonEmptyContacts) 
            {
                System.out.println(contact);
            }
            flag=true;
        } 
        if(!flag)
        {
            System.out.println("no contacts are created yet");
        }

    }
    //prints the given file content
    void readFile(String fileName)throws Exception
    {
        FileReader fileReader = new FileReader(fileName);
        int character;
        while ((character = fileReader.read()) != -1) 
        {
            System.out.print((char) character);
        }
        fileReader.close();
    }
    //displays all fields in the given contact
    void viewContactInfo()throws Exception
    {
        System.out.print("enter name of the contact to view:");
        String contactName = scanner.nextLine();
        if(emptyContacts.contains(contactName))
        {
            System.out.println("pleast fill the contact "+ contactName+ " before viewing it");
            return;
        }
        else if(!nonEmptyContacts.contains(contactName))
        {
            System.out.println("please create the contact "+contactName+" before viewing it");
            return;
        }
        System.out.println("the content of " + contactName + " is:");
        readFile(contactName);
    }
}	
	
