
package bookstore;

import java.util.HashSet;
import java.util.Set;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Mikołaj
 */
@XmlRootElement
public class Catalog {
    
    @XmlElement(required = true, name = "book")
    private Set<Book> booksCatalog = new HashSet<>();

    public Catalog() {
    }

    public Catalog(Set<Book> book) {
        this.booksCatalog = book;
    }

    public Set<Book> getBooks() {
        return booksCatalog;
    }

    public void setBooks(Set<Book> book) {
        this.booksCatalog = book;
    }
    
}
