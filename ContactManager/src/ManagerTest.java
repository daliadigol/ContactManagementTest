import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/*
* Unit test class for the Manager class in Contact Management System.
*/
public class ManagerTest {
    private Manager manager;
    private ArrayList<Contact> contacts;
    private DefaultListModel<Contact> listModel;
    private JList<Contact> contactList;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private JTextField nameCreateField, phoneCreateField, emailCreateField;
    private JButton saveButton, cancelButton, addContactButton, addViewDetailsButton;

    @BeforeEach /*Runs before each test to initialize components*/
    void setup() {
        manager = new Manager();
        contacts = new ArrayList<>();
        listModel = new DefaultListModel<>();
        contactList = new JList<>();
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

//initialize text fields
        nameCreateField = new JTextField();
        phoneCreateField = new JTextField();
        emailCreateField = new JTextField();

//initialize buttons
        saveButton = new JButton();
        cancelButton = new JButton();
        addContactButton = new JButton();
        addViewDetailsButton = new JButton();
    }


    @Test
    void TestCreatingNewContact() {
        Contact contact = new Contact("Enock","0780967454", "Enock@gmail.com");
        contacts.add(contact); //add contact to list
        listModel.addElement(contact); //adds contact to Jlist model

        //Verify if the contact was successfully added
        assertEquals(1, contacts.size(),"Contact should be added to list.");
        assertEquals("Enock", contacts.get(0).getName(),"Name should be the same");
        assertEquals("0780967454", contacts.get(0).getPhone(),"Phone should match.");
        assertEquals("Enock@gmail.com", contacts.get(0).getEmail(),"Email should match.");

    }
    @Test
    void testEmptyContactField() {
        //Create a contact with empty values
        Contact contact = new Contact("","","");
        //Validate that the fields are indeed empty
        assertTrue(contact.getName().isEmpty(),"Name should be empty.");
        assertTrue(contact.getPhone().isEmpty(),"Phone should be empty.");
        assertTrue(contact.getEmail().isEmpty(),"Email should be empty.");

    }
    //Test to verify that the application correctly handles null values when creating a contact.
    @Test
    void testWrongContact() {
        //Create a contact with null values.
        Contact contact = new Contact(null,null,null);

        assertNull(contact.getName(), "Name should remain null");
        assertNull(contact.getPhone(),"Phone should remain null");
        assertNull(contact.getEmail(),"Email should remain null.");

    }
    @Test //test to ensure CardLayout navigation is working correctly.
    void testCardLayoutNav() {

        //stimulate Navigation to Contact Creation Form
        cardLayout.show(mainPanel,"CONTACT_CREATION");
        assertEquals("CONTACT_CREATION",getCurrentCard(),"Should navigate to Contact Creation Form.");

        //stimulate Navigation to Contact Details View
        cardLayout.show(mainPanel,"CONTACT_DETAILS");
        assertEquals("CONTACT_DETAILS", getCurrentCard(),"Should navigate to Contact Details View.");

        //stimulate Navigation to Contact List View
        cardLayout.show(mainPanel,"CONTACT_LIST");
        assertEquals("CONTACT_LIST", getCurrentCard(),"Should Navigate to Contact List View.");
    }

    //Helper method to determine which card is currently visible.
    private String getCurrentCard() {
        for (Component comp : mainPanel.getComponents()){
            if (comp.isVisible()) {
                return mainPanel.getClientProperty("CARD").toString(); //The Name of currently displayed card
            }
        }
        return null;
    }

}