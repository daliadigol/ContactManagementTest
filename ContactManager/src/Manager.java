import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Manager {
    private ArrayList<Contact> contacts;
    private DefaultListModel<Contact> listModel;
    private JList<Contact> contactList;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private JButton addContactButton;
    private JButton addViewDetailsButton;
    private JButton saveButton;
    private JButton cancelButton;
    private JLabel nameLabel;
    private JLabel phoneLabel;
    private JLabel emailLabel;
    private JTextField nameCreateField;
    private JTextField phoneCreateField;
    private JTextField emailCreateField;

    public Manager(){
        contacts = new ArrayList<>();
        createGUI();
    }

    private Font font(){
        return new Font("Arial", Font.PLAIN, 30);
    }

    private JFrame createGUI() {

        JFrame mainFrame = new JFrame();
        listModel = new DefaultListModel<>();
        contactList = new JList<>(listModel);
        addContactButton = new JButton("New Contact");
        addContactButton.setFont(new Font("Arial", Font.PLAIN, 30));
        addContactButton.setFocusPainted(false);

        addViewDetailsButton = new JButton("View Details");
        addViewDetailsButton.setFont(new Font("Arial", Font.PLAIN, 30));
        addViewDetailsButton.setFocusPainted(false);

        saveButton = new JButton("Save");
        saveButton.setFont(new Font("Arial", Font.PLAIN, 30));
        saveButton.setFocusPainted(false);

        cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Arial", Font.PLAIN, 30));
        cancelButton.setBackground(Color.RED);
        cancelButton.setForeground(Color.white);
        cancelButton.setFocusPainted(false);

        nameLabel = new JLabel("Name:");
        nameLabel.setFont(font());
        phoneLabel = new JLabel("Phone:");
        phoneLabel.setFont(font());
        emailLabel = new JLabel("Email:");
        emailLabel.setFont(font());

        nameCreateField = new JTextField();
        nameCreateField.setFont(new Font("Arial", Font.PLAIN, 30));
        phoneCreateField = new JTextField();
        phoneCreateField.setFont(new Font("Arial", Font.PLAIN, 30));
        emailCreateField = new JTextField();
        emailCreateField.setFont(new Font("Arial", Font.PLAIN, 30));

        mainPanel = new JPanel();
        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);
        mainPanel.add(contactListView(), "CONTACT_LIST");
        mainPanel.add(contactDetailsView(), "CONTACT_DETAILS");
        mainPanel.add(contactCreationForm(), "CONTACT_CREATION");



        addContactButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "CONTACT_CREATION");
            }
        });


        addViewDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = contactList.getSelectedIndex();
                if (selectedIndex != -1) {
                    Contact selectedContact = contacts.get(selectedIndex);
                    nameLabel.setText("Name:       " + selectedContact.getName());
                    phoneLabel.setText("Phone:       " + selectedContact.getPhone());
                    emailLabel.setText("Email:       " + selectedContact.getEmail());
                    cardLayout.show(mainPanel, "CONTACT_DETAILS");
                }
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameCreateField.getText();
                String phone = phoneCreateField.getText();
                String email = emailCreateField.getText();
                Contact newContact = new Contact(name, phone, email);
                contacts.add(newContact);
                listModel.addElement(newContact);
                cardLayout.show(mainPanel, "CONTACT_LIST");
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "CONTACT_LIST");
            }
        });

        mainFrame.setTitle("SocialBook");
        ImageIcon icon = new ImageIcon("src/main/java/icon.png");
        mainFrame.setIconImage(icon.getImage());
        mainFrame.add(mainPanel);
        mainFrame.setSize(400, 300);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
        mainFrame.pack();

        return mainFrame;
    }
    private JPanel contactListView() {
        JPanel contactListView = new JPanel();
        contactListView.setLayout(new BorderLayout());

        JPanel view = new JPanel();
        view.setLayout(new BorderLayout());

        listModel = new DefaultListModel<>();
        contactList = new JList<>(listModel);
        contactList.setFont(new Font("Arial", Font.PLAIN, 30));
        contactList.setBorder(BorderFactory.createEmptyBorder(30, 150, 0, 150));

        JScrollPane listScrollPane = new JScrollPane(contactList);
        listScrollPane.setPreferredSize(new Dimension(700, 500));

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());

        centerPanel.add(view);


        contactListView.add(centerPanel, BorderLayout.CENTER);
        contactListView.add(buttonPanel(), BorderLayout.SOUTH);

        view.add(listScrollPane, BorderLayout.CENTER);

        return contactListView;
    }




    private JPanel buttonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 60, 0));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 30, 0));
        buttonPanel.add(addContactButton);
        buttonPanel.add(addViewDetailsButton);
        return buttonPanel;
    }







    private JPanel contactDetailsView() {
        JPanel contactDetailsView = new JPanel();
        contactDetailsView.setLayout(new BorderLayout());

        JPanel details = new JPanel();
        details.setLayout(new GridLayout(3, 1));

        details.add(nameLabel);
        details.add(phoneLabel);
        details.add(emailLabel);

        details.setPreferredSize(new Dimension(600, 400));

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());
        centerPanel.add(details);
        JPanel southPanel = new JPanel();
        southPanel.add(backButton()).setFont(font());
        contactDetailsView.add(centerPanel, BorderLayout.CENTER);
        contactDetailsView.add(southPanel, BorderLayout.SOUTH);

        return contactDetailsView;
    }


    private JButton backButton() {
        JButton backButton = new JButton("Back");
        backButton.setSize(100, 0);
        backButton.setFocusPainted(false);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "CONTACT_LIST");
            }
        });
        return backButton;
    }



    private JPanel contactCreationForm() {
        JPanel contactCreationForm = new JPanel();
        contactCreationForm.setLayout(new BorderLayout());

        JPanel fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new GridLayout(3, 2, 10, 30));

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(font());
        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setFont(font());
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(font());

        fieldsPanel.add(nameLabel);
        fieldsPanel.add(nameCreateField);
        fieldsPanel.add(phoneLabel);
        fieldsPanel.add(phoneCreateField);
        fieldsPanel.add(emailLabel);
        fieldsPanel.add(emailCreateField);

        fieldsPanel.setPreferredSize(new Dimension(800, 200));

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());
        centerPanel.add(fieldsPanel);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 60, 0));
        buttonsPanel.add(saveButton);
        buttonsPanel.add(cancelButton);

        contactCreationForm.add(centerPanel, BorderLayout.CENTER);
        contactCreationForm.add(buttonsPanel, BorderLayout.SOUTH);

        return contactCreationForm;
    }

}


