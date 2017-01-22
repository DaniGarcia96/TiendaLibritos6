
package bookstore;

import javafx.beans.property.SimpleStringProperty;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Mikołaj
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(name = "book", propOrder = {"title", "author", "pages", "quantity", "price"})
public class Book {
    private SimpleStringProperty title;
    private SimpleStringProperty author;
    private SimpleStringProperty pages;
    private SimpleStringProperty quantity;
    private SimpleStringProperty price;
    
    
    public Book()
    {
        title = new SimpleStringProperty();
        author = new SimpleStringProperty();
        pages = new SimpleStringProperty();
        quantity = new SimpleStringProperty();
        price = new SimpleStringProperty();
    }
    
    public Book(String title, String author, String pages, String quantity, String price)
    {
        this.title = new SimpleStringProperty(title);
        this.author = new SimpleStringProperty(author);
        this.pages = new SimpleStringProperty(pages);
        this.quantity = new SimpleStringProperty(quantity);
        this.price = new SimpleStringProperty(price);
    }

    public String getTitle() {
        return title.get();
    }
    
    @XmlAttribute
    public void setTitle(String title) {
        this.title.set(title);
    }
    
    public String getAuthor() {
        return author.get();
    }
    
    @XmlAttribute
    public void setAuthor(String author) {
        this.author.set(author);
    }

    public String getPages() {
        return pages.get();
    }
    
    @XmlAttribute
    public void setPages(String pages) {
        this.pages.set(pages);
        
    }

    public String getQuantity() {
        return quantity.get();
    }
    
    @XmlAttribute
    public void setQuantity(String quantity) {
        this.quantity.set(quantity);
    }

    public String getPrice() {
        return price.get();
    }
    
    @XmlAttribute
    public void setPrice(String price) {
        this.price.set(price);
    }
    
    
}
