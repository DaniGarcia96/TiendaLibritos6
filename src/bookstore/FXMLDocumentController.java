
package bookstore;

import java.io.File;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author Mikołaj
 */
public class FXMLDocumentController implements Initializable {
    final ObservableList<Book> booksCatalog = FXCollections.observableArrayList();
    
    @FXML
    TableView <Book> tableView ;
    @FXML
    TableColumn<Book, String> titleColumn;
    @FXML
    TableColumn<Book, String> authorColumn;
    @FXML
    TableColumn<Book, String> pagesColumn;
    @FXML
    TableColumn<Book, String> quantityColumn;
    @FXML
    TableColumn<Book, String> priceColumn;
    
    @FXML
    private TextField titleTextField;
    @FXML
    private TextField authorTextField;
    @FXML
    private TextField pagesTextField;
    @FXML
    private TextField quantityTextField;
    @FXML
    private TextField priceTextField;
    
    @FXML
    private TextField nameNewFile;
    @FXML
    private Label pathNewFile;
    
    
    @FXML
    private void addBookButtonAction(ActionEvent event) {
        
        String title = titleTextField.textProperty().getValue().toString();
        String author = authorTextField.textProperty().getValue().toString();
        String pages = pagesTextField.textProperty().getValue().toString();
        String quantity = quantityTextField.textProperty().getValue().toString();
        String price = priceTextField.textProperty().getValue().toString();
        
        if(!titleTextField.getText().equals("") && !authorTextField.getText().equals("") && !pagesTextField.getText().equals("") && !quantityTextField.getText().equals("") && !priceTextField.getText().equals("") )
        {
            Book book = new Book(title, author, pages, quantity, price);
            booksCatalog.add(book);
            titleTextField.setText("");
            authorTextField.setText("");
            pagesTextField.setText("");
            quantityTextField.setText("");
            priceTextField.setText("");
        }
        else
            System.err.println("All text fields must be completed");
       
    }
    
    @FXML
    private void deleteBookButtonAction(ActionEvent event) {
        booksCatalog.remove(tableView.getSelectionModel().getSelectedItem());
    }
    
    @FXML
    private void openXMLButtonAction(ActionEvent event) {
        
        try {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(null);
            if (file != null) {
                try {
                    JAXBContext context = JAXBContext.newInstance(ObjectFactory.class.getPackage().getName());
                    Unmarshaller unmarshaller = context.createUnmarshaller();
                    Catalog catalog = (Catalog) unmarshaller.unmarshal(file);
                    booksCatalog.clear();
                    booksCatalog.addAll(catalog.getBooks());
                } catch (JAXBException e) {
                    System.err.print("File Error: " + e);
                }
            }
        } catch (Exception e) {
            System.err.println("Wrong file location. Error: " + e);
        }
        
    }
    
    @FXML
    private void selectLocationButtonAction(ActionEvent event) {
        
        try {
            DirectoryChooser dir = new DirectoryChooser();
            File file = dir.showDialog(null);
            pathNewFile.setText(file.toPath().toString()); 
        } catch (Exception e) {
            System.err.print("Wrong location. Error: " + e);
        }
        
    }
    
    @FXML
    private void saveXMLButtonAction(ActionEvent event) {
        
        try {
            String path = pathNewFile.getText()+"\\"+nameNewFile.getText() + ".xml";
            File file = new File(path);
            System.out.println(path);
            JAXBContext context = JAXBContext.newInstance(ObjectFactory.class.getPackage().getName());
            Marshaller marshaller = context.createMarshaller();

            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            
            Catalog catalog = new Catalog();
            catalog.setBooks(new HashSet<>(booksCatalog));

            marshaller.marshal(catalog, file);
            marshaller.marshal(catalog, System.out);

            pathNewFile.setText("");
            nameNewFile.setText("");
        } catch (JAXBException e) {
            System.err.println("Write error");
        }

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        titleColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
        pagesColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("pages"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("quantity"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("price"));
        
        tableView.setItems(booksCatalog);
        tableView.setEditable(true);
        
        titleColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Book, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Book, String> event) {
                Book book = event.getRowValue();
                String newTitle = event.getNewValue();
                book.setTitle(newTitle);
            }
        });
        
        authorColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Book, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Book, String> event) {
                Book book = event.getRowValue();
                String newAuthor = event.getNewValue();
                book.setAuthor(newAuthor);
            }
        });
        
        pagesColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Book, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Book, String> event) {
                Book book = event.getRowValue();
                String newPages = event.getNewValue();
                book.setPages(newPages);
            }
        });
        
        quantityColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Book, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Book, String> event) {
                Book book = event.getRowValue();
                String newQuantity = event.getNewValue();
                book.setQuantity(newQuantity);
            }
        });
        
        priceColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Book, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Book, String> event) {
                Book book = event.getRowValue();
                String newPrice = event.getNewValue();
                book.setPrice(newPrice);
            }
        });
        
    }    
    
}
