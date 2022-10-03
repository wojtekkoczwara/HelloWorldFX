package Challenge;

import Challenge.datamodel.Contact;
import Challenge.datamodel.ContactData;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.Optional;

public class Controller {

    public ContactData contactData;
    private static ObservableList<Contact> contacts;
    @FXML
    private TableColumn firstNameRow;
    @FXML
    private TableColumn lastNameRow;
    @FXML
    private TableColumn phoneNumberRow;
    @FXML
    private TableColumn notesRow;

    @FXML
    private TableView<Contact> tableView;

    @FXML
    private BorderPane mainBorderPane;


    public void initialize(){
        System.out.println("Initialized");
//        Contact contact1 = new Contact("Wojciech", "Koczwara", "234232342", "ten co szyby tlucze");
//        Contact contact2 = new Contact("Marian", "Pazdioch", "234232342", "menda i oszust");
//        Contact contact3 = new Contact("Ferdek", "Kiepski", "234232342", "pijak i len jeden");
//        Contact contact4 = new Contact("Arnold", "Boczek", "234232342", "tlusty obżartuch");
//        Contact contact5 = new Contact("Halinka", "Kiepska", "234232342", "pani jelopowa");
//        contacts = FXCollections.observableArrayList(contact1, contact2, contact3, contact4, contact5);
//        contacts.addAll(contact1, contact2, contact3, contact4, contact5);
//        contactData = new ContactData();
//        contactData.addContact(contact1);
//        contactData.addContact(contact2);
//        contactData.addContact(contact3);
//        contactData.addContact(contact4);
//        contactData.addContact(contact5);
//
        contactData = new ContactData();

        contactData.loadContacts();
        contacts = FXCollections.observableArrayList(contactData.getContacts());
//
        tableView.setEditable(true);

        firstNameRow.setCellValueFactory(new PropertyValueFactory<Contact, String>("firstName"));
        lastNameRow.setCellValueFactory(new PropertyValueFactory<Contact, String>("lastName"));
        phoneNumberRow.setCellValueFactory(new PropertyValueFactory<Contact, String>("phoneNumber"));
        notesRow.setCellValueFactory(new PropertyValueFactory<Contact, String>("notes"));
        tableView.setItems(contacts);
        editCellfirstName();
        editCelllastName();
        editCellNotes();
        editCellphoneNumber();
        contactData.saveContacts();
    }

    @FXML
    public void showNewContactDialog(){
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        dialog.setTitle("add new contact");
        dialog.setHeaderText("Use this dialog to create a new contact");

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("addContactDialog.fxml"));

        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e){
            System.out.println("Couldn't load the dialog");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.APPLY, ButtonType.CANCEL);
        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.APPLY){
            AddContactController addContactController = fxmlLoader.getController();
            Contact contact = addContactController.processResult();
            tableView.getSelectionModel().select(contact);
            contactData.addContact(contact);
        } else {

        }
    }

    @FXML
    public void deleteContact(){
        Contact contact = tableView.getSelectionModel().getSelectedItem();
        Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
        if(contact != null){
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setContentText("Please choose contact");
        }
        alert.setTitle("Delete contact");
        alert.setHeaderText("Delete contact: " + contact.getFirstName() + " " + contact.getLastName() + "?");
        alert.setContentText("Are you sure? Press OK to confirm, or cancel to Back out.");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            contactData.deleteContact(contact);
            contactData.saveContacts();
            tableView.setItems(contacts = FXCollections.observableArrayList(contactData.getContacts()));
            tableView.refresh();
        }
    }

    public static ObservableList<Contact> getContacts(){
        return contacts;
    }

    @FXML
    public void handleExit(){
        Platform.exit();
        contactData.saveContacts();
    }

    public void editCellfirstName(){
        firstNameRow.setCellFactory(TextFieldTableCell.forTableColumn());
        firstNameRow.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Contact, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent <Contact, String> event) {
                        ((Contact) event.getTableView().getItems().
                                get(event.getTablePosition().getRow())).setFirstName(event.getNewValue());
                    }
                }
        );
    }

    public void editCelllastName(){
        lastNameRow.setCellFactory(TextFieldTableCell.forTableColumn());
        lastNameRow.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Contact, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent <Contact, String> event) {
                        ((Contact) event.getTableView().getItems().
                                get(event.getTablePosition().getRow())).setLastName(event.getNewValue());
                    }
                }
        );
    }

    public void editCellphoneNumber(){
        phoneNumberRow.setCellFactory(TextFieldTableCell.forTableColumn());
        phoneNumberRow.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Contact, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent <Contact, String> event) {
                        ((Contact) event.getTableView().getItems().
                                get(event.getTablePosition().getRow())).setPhoneNumber(event.getNewValue());
                    }
                }
        );
    }

    public void editCellNotes(){
        notesRow.setCellFactory(TextFieldTableCell.forTableColumn());
        notesRow.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Contact, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent <Contact, String> event) {
                        ((Contact) event.getTableView().getItems().
                                get(event.getTablePosition().getRow())).setNotes(event.getNewValue());
                    }
                }
        );
    }
}
