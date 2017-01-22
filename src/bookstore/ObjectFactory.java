
package bookstore;

import javax.xml.bind.annotation.XmlRegistry;

/**
 *
 * @author Mikołaj
 */
@XmlRegistry
public class ObjectFactory {
    
    public ObjectFactory() {
    }

    public Catalog createCatalog() {
        return new Catalog();
    }

    public Book createBook() {
        return new Book();
    }
    
}
