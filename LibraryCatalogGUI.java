package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LibraryCatalogGUI extends JFrame {
    LibraryCatalog[] catalog = new LibraryCatalog[10];
    LibraryCatalog[] searchcatalog = new LibraryCatalog[10];
    Reservation [] reservecatalog = new Reservation[10];
    private final DefaultTableModel tableModel;
    private DefaultTableModel searchTableModel;
    private DefaultTableModel reserveTableModel;
    private String id, title, author, pdate, genre;
    private String reserveTitle, reserveName, reserveDate, returnDate;
    private int amount;
    //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//Library catalog GUI
    public LibraryCatalogGUI() {
        super("Library Catalog");
        super.setSize(800, 600);
        super.setLocationRelativeTo(null);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //initializing array catalog[0] - catalog[4] with sample values
        catalog[0] = new LibraryCatalog("001","Mathematics", "James", "20/10/2023", "Academics", 10);
        catalog[1] = new LibraryCatalog("002","Problem Solving", "Thames", "22/10/2023", "Academics", 5);
        catalog[2] = new LibraryCatalog("003","Operating Systems", "Wames", "22/10/2023", "Academics", 5);
        catalog[3] = new LibraryCatalog("004","Network Security", "Sames", "20/10/2023", "Academics", 1);
        catalog[4] = new LibraryCatalog("005","Moral and Life", "Lames", "21/10/2023", "Romance", 1);
        catalog[5] = new LibraryCatalog("006", "Physics", "Smith", "25/10/2023", "Academics", 3);
        catalog[6] = new LibraryCatalog("007", "Literature", "Davis", "28/10/2023", "Fiction", 4);
        catalog[7] = new LibraryCatalog("008", "History", "Wilson", "29/10/2023", "Non-Fiction", 6);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton addButton = new JButton("Add Catalog");
        JButton deleteButton = new JButton("Delete Catalog");
        JButton reserveButton = new JButton("Book Reservation");
        JButton searchButton = new JButton("Search");
        JButton sortButton = new JButton("Sort Catalog");

        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(reserveButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(sortButton);

        addButton.addActionListener((ActionEvent add) -> {
            openAddCatalogGUI();
            displayCatalog();
        });

        deleteButton.addActionListener((ActionEvent delete) -> {
            deleteCatalogGUI();
            displayCatalog();
        });

        reserveButton.addActionListener((ActionEvent reserve) -> {
            openReservationGUI();
            displayCatalog();
        });

        searchButton.addActionListener((ActionEvent search)->{
            openSearchGUI();
        });

        sortButton.addActionListener((ActionEvent sort) -> {
            openSortCatalogGUI();
            displayCatalog();
        });

        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Title");
        tableModel.addColumn("Author");
        tableModel.addColumn("Publication Date");
        tableModel.addColumn("Genre");
        tableModel.addColumn("Amount");
        tableModel.addColumn("Availability");

        JTable catalogTable = new JTable(tableModel);
        catalogTable.setEnabled(false);

        JScrollPane scrollPane = new JScrollPane(catalogTable);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        Container container = getContentPane();
        container.setLayout(new BorderLayout(10, 10));
        container.add(buttonPanel, BorderLayout.SOUTH);
        container.add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
        displayCatalog();
    }
    //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //opens addcatalog GUI when called
    public void openAddCatalogGUI(){

        JFrame addCatalogFrame = new JFrame("Add Catalog");
        addCatalogFrame.setSize(400, 300);
        addCatalogFrame.setLocationRelativeTo(null);
        addCatalogFrame.setLayout(new BorderLayout(10, 10));

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 5, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel idLabel = new JLabel("ID:");
        JTextField idField = new JTextField(20);
        JLabel titleLabel = new JLabel("Title:");
        JTextField titleField = new JTextField(20);
        JLabel authorLabel = new JLabel("Author:");
        JTextField authorField = new JTextField(20);
        JLabel pdateLabel = new JLabel("Publication Date:");
        JTextField pdateField = new JTextField(20);
        JLabel genreLabel = new JLabel("Genre:");
        JTextField genreField = new JTextField(20);
        JLabel amountLabel = new JLabel("Amount:");
        JTextField amountField = new JTextField(20);

        formPanel.add(idLabel);
        formPanel.add(idField);
        formPanel.add(titleLabel);
        formPanel.add(titleField);
        formPanel.add(authorLabel);
        formPanel.add(authorField);
        formPanel.add(pdateLabel);
        formPanel.add(pdateField);
        formPanel.add(genreLabel);
        formPanel.add(genreField);
        formPanel.add(amountLabel);
        formPanel.add(amountField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton addButton = new JButton("Add");
        buttonPanel.add(addButton);

        addButton.addActionListener((ActionEvent e) -> {
            try {
                for (int i = 0; i < 10; i++) {
                    if (catalog[i] == null) {
                        id = idField.getText();
                        title = titleField.getText();
                        author = authorField.getText();
                        pdate = pdateField.getText();
                        genre = genreField.getText();
                        amount = Integer.parseInt(amountField.getText());
                        if (id.isEmpty() || title.isEmpty() || author.isEmpty() || pdate.isEmpty() || genre.isEmpty()) {
                            throw new IllegalArgumentException("Please enter all fields.");
                        }
                        catalog[i] = new LibraryCatalog(id, title, author, pdate, genre, amount);
                        displayCatalog();
                        break;
                    }
                }
                addCatalogFrame.dispose();
            } catch (NumberFormatException e1) {
                JOptionPane.showMessageDialog(null, "Invalid amount. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            catch (IllegalArgumentException e2){
                JOptionPane.showMessageDialog(null, e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }

        });

        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        addCatalogFrame.getContentPane().add(mainPanel);
        addCatalogFrame.setResizable(false);
        addCatalogFrame.setVisible(true);
    }
    //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //open Reservation GUI when called
    public void openReservationGUI() {
        JFrame reservationFrame = new JFrame("Reservation");
        reservationFrame.setSize(400, 300);
        reservationFrame.setLocationRelativeTo(null);
        reservationFrame.setLayout(new BorderLayout(10, 10));

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 5, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Title:");
        JTextField titleField = new JTextField(20);
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(20);
        JLabel reserveLabel = new JLabel("Reservation Date:");
        JTextField reserveField = new JTextField(20);
        JLabel returnLabel = new JLabel("Return Date:");
        JTextField returnField = new JTextField(20);

        formPanel.add(titleLabel);
        formPanel.add(titleField);
        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(reserveLabel);
        formPanel.add(reserveField);
        formPanel.add(returnLabel);
        formPanel.add(returnField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton reserveButton = new JButton("Reserve");
        JButton reserveListButton = new JButton("Reserve List");
        buttonPanel.add(reserveButton);
        buttonPanel.add(reserveListButton);

        reserveButton.addActionListener((ActionEvent e) -> {
            try {
                Boolean ValidTitle = false;
                for (int i = 0; i < 10; i++) {
                    if (reservecatalog[i] == null) {
                        reserveTitle = titleField.getText();
                        reserveName = nameField.getText();
                        reserveDate = reserveField.getText();
                        returnDate = returnField.getText();
                        if (reserveTitle.isEmpty() || reserveName.isEmpty() || reserveDate.isEmpty() || returnDate.isEmpty()) {
                            throw new IllegalArgumentException("Please enter all fields.");
                        }
                        for (int j = 0; j < 10; j++) {
                            if (catalog[j] != null && catalog[j].getTitle().equals(reserveTitle)) {
                                ValidTitle = true;
                                break;
                            }
                        }
                        if (!ValidTitle) {
                            throw new IllegalArgumentException("Invalid title. Please enter a valid title from the catalog.");
                        }
                        reservecatalog[i] = new Reservation(reserveTitle, reserveName, reserveDate, returnDate);
                        if (returnDate.compareTo(reserveDate) < 0) {
                            throw new IllegalArgumentException("Return date must be greater than or equal to the reservation date.");
                        }
                        break;
                    }
                }
                reservationFrame.dispose();
                ReserveCatalogFrame();
                reservation(reserveTitle);
            }
            catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        reserveListButton.addActionListener((ActionEvent e) -> {
            ReserveCatalogFrame();
        });

        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        reservationFrame.getContentPane().add(mainPanel);
        reservationFrame.setResizable(false);
        reservationFrame.setVisible(true);
    }
    //This method is the frame for reserve catalog
    public void ReserveCatalogFrame() {
        JFrame ReserveCatalogFrame = new JFrame("Reservation List");
        ReserveCatalogFrame.setSize(600, 400);
        ReserveCatalogFrame.setLocationRelativeTo(null);
        ReserveCatalogFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        reserveTableModel = new DefaultTableModel(); // Create a new DefaultTableModel for search results
        reserveTableModel.addColumn("Title");
        reserveTableModel.addColumn("Name");
        reserveTableModel.addColumn("Reserve Date");
        reserveTableModel.addColumn("Return Date");


        JTable reserveCatalogTable = new JTable(reserveTableModel);
        reserveCatalogTable.setEnabled(false);

        JScrollPane scrollPane = new JScrollPane(reserveCatalogTable);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        Container container = ReserveCatalogFrame.getContentPane();
        container.setLayout(new BorderLayout(10, 10));
        container.add(scrollPane, BorderLayout.CENTER);

        displayReserveCatalog(reservecatalog);

        ReserveCatalogFrame.setVisible(true);
    }
    /*
     This method will display reserve catalog in a table
     */
    public final void displayReserveCatalog(Reservation [] reservecatalog){
        for (Reservation catalogItem : reservecatalog) {
            if (catalogItem != null) {
                Object[] rowData = {
                        catalogItem.getTitle(),
                        catalogItem.getName(),
                        catalogItem.getReserveDate(),
                        catalogItem.getReturnDate(),
                };
                reserveTableModel.addRow(rowData);
            }
        }
    }

    /*
   Will create a reservation when called.
   This method will deduct 1 from the amount when reservation is successful. it will also determine the availability based on amount.
   */
    private void reservation(String title) {
        boolean found = false;
        for (LibraryCatalog catalog1 : catalog) {
            if (catalog1 != null && ((catalog1.getTitle().equalsIgnoreCase(title)) || (catalog1.getID().equalsIgnoreCase(title)))) {
                if (catalog1.getAvailability()) {
                    catalog1.setAmount(catalog1.getAmount() - 1);
                    catalog1.setAvailability(catalog1.getAmount() > 0);
                    JOptionPane.showMessageDialog(null, "Success! " + catalog1.getTitle() + " is reserved", "Reservation Result", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Failed! " + catalog1.getTitle() + " is already reserved", "Reservation Result", JOptionPane.ERROR_MESSAGE);
                }
                found = true;
                displayCatalog();
                break;
            }
        }

        if (!found) {
            JOptionPane.showMessageDialog(null, "Book not found", "Reservation Result", JOptionPane.ERROR_MESSAGE);
        }
    }
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    //open deleteCatalogGUI when called.
    public void deleteCatalogGUI() {
        JFrame deleteCatalogFrame = new JFrame("Delete Catalog");
        deleteCatalogFrame.setSize(450, 100);
        deleteCatalogFrame.setLocationRelativeTo(null);
        deleteCatalogFrame.setLayout(new BorderLayout(10, 10));

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JLabel titleLabel = new JLabel("ID or Title:");
        JTextField titleField = new JTextField(20);
        JButton deleteButton = new JButton("Delete");

        panel.add(titleLabel);
        panel.add(titleField);
        panel.add(deleteButton);

        deleteButton.addActionListener((ActionEvent e) -> {
            String search = titleField.getText();
            for (int i = 0; i < catalog.length; i++) {
                if (catalog[i] != null && ((catalog[i].getTitle().equalsIgnoreCase(search)) || catalog[i].getID().equalsIgnoreCase(search))) {
                    JOptionPane.showMessageDialog(null, "Are you sure to delete this catalog? :", "Confirmation", JOptionPane.OK_CANCEL_OPTION);
                    catalog[i].delCatalog();
                    catalog[i] = null;
                    displayCatalog();
                }
            }
            deleteCatalogFrame.dispose();
        });

        deleteCatalogFrame.add(panel, BorderLayout.CENTER);

        deleteCatalogFrame.setVisible(true);
    }
    //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    /*
    This method will open openSortCatalogGUI when called.
    this method will call a suitable sort method based on the user's criteria.
    */
    public void openSortCatalogGUI() {
        JFrame sortCatalogFrame = new JFrame("Sort Catalog");
        sortCatalogFrame.setSize(500, 150);
        sortCatalogFrame.setLocationRelativeTo(null);
        sortCatalogFrame.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JLabel sortLabel = new JLabel("Sort by:");
        JComboBox<String> sortComboBox = new JComboBox<>(new String[]{"ID", "Title", "Publishing Date", "Amount", "Genre"});
        JButton sortButton = new JButton("Sort");

        sortCatalogFrame.add(sortLabel);
        sortCatalogFrame.add(sortComboBox);
        sortCatalogFrame.add(sortButton);

        sortButton.addActionListener((ActionEvent e) -> {
            String selectedOption = (String) sortComboBox.getSelectedItem();
            switch(selectedOption){
                case "ID" -> sortCatalogByID();
                case "Title" -> sortCatalogByTitle();
                case "Publishing Date" -> sortCatalogByPublishingDate();
                case "Amount" -> sortCatalogByAmount();
                case "Genre" -> sortCatalogByGenre();
            }

            sortCatalogFrame.dispose();
        });

        sortCatalogFrame.setVisible(true);
    }

//BUBBLE SORTING ALGORITHMS
//The name for each method below is self explainatory.

    public void sortCatalogByID() {
        for (int i = 0; i < catalog.length - 1; i++) {
            for (int j = i + 1; j < catalog.length; j++) {
                if (catalog[i] != null && catalog[j] != null) {
                    if (catalog[i].getID().compareTo(catalog[j].getID()) > 0) {
                        LibraryCatalog temp = catalog[i];
                        catalog[i] = catalog[j];
                        catalog[j] = temp;
                        displayCatalog();
                    }
                }
            }
        }
    }
    public void sortCatalogByTitle() {
        for (int i = 0; i < catalog.length - 1; i++) {
            for (int j = i + 1; j < catalog.length; j++) {
                if (catalog[i] != null && catalog[j] != null) {
                    if (catalog[i].getTitle().compareTo(catalog[j].getTitle()) > 0) {
                        LibraryCatalog temp = catalog[i];
                        catalog[i] = catalog[j];
                        catalog[j] = temp;
                        displayCatalog();
                    }
                }
            }
        }
    }
    public void sortCatalogByPublishingDate() {
        for (int i = 0; i < catalog.length - 1; i++) {
            for (int j = i + 1; j < catalog.length; j++) {
                if (catalog[i] != null && catalog[j] != null) {
                    if (catalog[i].getPDate().compareTo(catalog[j].getPDate()) > 0) {
                        LibraryCatalog temp = catalog[i];
                        catalog[i] = catalog[j];
                        catalog[j] = temp;
                        displayCatalog();
                    }
                }
            }
        }
    }
    public void sortCatalogByAmount() {
        for (int i = 0; i < catalog.length - 1; i++) {
            for (int j = i + 1; j < catalog.length; j++) {
                if (catalog[i] != null && catalog[j] != null) {
                    if (catalog[i].getAmount() > catalog[j].getAmount()) {
                        LibraryCatalog temp = catalog[i];
                        catalog[i] = catalog[j];
                        catalog[j] = temp;
                        displayCatalog();
                    }
                }
            }
        }
    }

    //sort catalog by Genre method when called.
    public void sortCatalogByGenre() {
        for (int i = 0; i < catalog.length - 1; i++) {
            for (int j = i + 1; j < catalog.length; j++) {
                if (catalog[i] != null && catalog[j] != null) {
                    if (catalog[i].getGenre().compareToIgnoreCase(catalog[j].getGenre()) > 0) {
                        LibraryCatalog temp = catalog[i];
                        catalog[i] = catalog[j];
                        catalog[j] = temp;
                        displayCatalog();
                    }
                }
            }
        }
    }

//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public void openSearchGUI() {
        JFrame searchCatalogFrame = new JFrame("Search");
        searchCatalogFrame.setSize(400, 150);
        searchCatalogFrame.setLocationRelativeTo(null);
        searchCatalogFrame.setLayout(new BorderLayout(10, 10));

        JPanel searchPanel = new JPanel(new GridLayout(2, 2, 10, 10));

        JLabel searchLabel = new JLabel("Search by:");
        JComboBox<String> searchComboBox = new JComboBox<>(new String[]{"Title", "Author", "Genre"});
        JLabel searchFieldLabel = new JLabel("Search keywords:");
        JTextField searchField = new JTextField();

        searchPanel.add(searchLabel);
        searchPanel.add(searchComboBox);
        searchPanel.add(searchFieldLabel);
        searchPanel.add(searchField);

        JPanel buttonPanel = new JPanel();
        JButton searchButton = new JButton("Search");
        buttonPanel.add(searchButton);

        searchButton.addActionListener((ActionEvent e) -> {
            try {
                String selectedOption = (String) searchComboBox.getSelectedItem();
                String search = searchField.getText();
                if (search.isEmpty()) {
                    throw new IllegalArgumentException("Please enter your search keywords.");
                }
                switch (selectedOption) {
                    case "Title" -> searchCatalogByTitle(search);
                    case "Author" -> searchCatalogByAuthor(search);
                    case "Genre" -> searchCatalogByGenre(search);
                }
                searchCatalogFrame.dispose();
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        searchCatalogFrame.add(searchPanel, BorderLayout.CENTER);
        searchCatalogFrame.add(buttonPanel, BorderLayout.SOUTH);

        searchCatalogFrame.setVisible(true);
    }
    public void searchCatalogByTitle(String search) {
        for (int i = 0; i < catalog.length; i++) {
            if (catalog[i] != null && catalog[i].getTitle().equalsIgnoreCase(search)) {
                searchcatalog[i] = catalog[i];
            }
        }
        SearchCatalogFrame();
    }
    public void searchCatalogByAuthor(String search) {
        for (int i = 0; i < catalog.length; i++) {
            if (catalog[i] != null && catalog[i].getAuthor().equalsIgnoreCase(search)) {
                searchcatalog[i] = catalog[i];
            }
        }
        SearchCatalogFrame();
    }
    //sort catalog by Genre method when called.
    public void searchCatalogByGenre(String search) {
        for (int i = 0; i < catalog.length; i++) {
            if (catalog[i] != null && catalog[i].getGenre().equalsIgnoreCase(search)) {
                searchcatalog[i] = catalog[i];
            }
        }
        SearchCatalogFrame();
    }

    public void SearchCatalogFrame() {
        JFrame searchCatalogFrame = new JFrame("Search Results");
        searchCatalogFrame.setSize(600, 400);
        searchCatalogFrame.setLocationRelativeTo(null);
        searchCatalogFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        searchTableModel = new DefaultTableModel(); // Create a new DefaultTableModel for search results
        searchTableModel.addColumn("ID");
        searchTableModel.addColumn("Title");
        searchTableModel.addColumn("Author");
        searchTableModel.addColumn("Publication Date");
        searchTableModel.addColumn("Genre");
        searchTableModel.addColumn("Amount");
        searchTableModel.addColumn("Availability");

        JTable searchCatalogTable = new JTable(searchTableModel);
        searchCatalogTable.setEnabled(false);

        JScrollPane scrollPane = new JScrollPane(searchCatalogTable);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        Container container = searchCatalogFrame.getContentPane();
        container.setLayout(new BorderLayout(10, 10));
        container.add(scrollPane, BorderLayout.CENTER);

        displaySearchCatalog(searchcatalog);
        delSearchCatalog();
        searchCatalogFrame.setVisible(true);
    }

    public final void displaySearchCatalog(LibraryCatalog [] searchcatalog){
        for (LibraryCatalog catalogItem : searchcatalog) {
            if (catalogItem != null) {
                Object[] rowData = {
                        catalogItem.getID(),
                        catalogItem.getTitle(),
                        catalogItem.getAuthor(),
                        catalogItem.getPDate(),
                        catalogItem.getGenre(),
                        catalogItem.getAmount(),
                        catalogItem.getAvailability()
                };
                searchTableModel.addRow(rowData);
            }
        }
    }
    public void delSearchCatalog(){
        for (int i = 0; i < 10; i++){
            searchcatalog[i] = null;
        }
    }


    //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//DISPLAY SEGMENT (TABLE)
//This method will display all available catalogs.
    public final void displayCatalog() {
        tableModel.setRowCount(0);
        for (LibraryCatalog catalogItem : catalog) {
            if (catalogItem != null) {
                Object[] row = {
                        catalogItem.getID(),
                        catalogItem.getTitle(),
                        catalogItem.getAuthor(),
                        catalogItem.getPDate(),
                        catalogItem.getGenre(),
                        catalogItem.getAmount(),
                        catalogItem.getAvailability()
                };
                tableModel.addRow(row);
            }
        }
    }

}

