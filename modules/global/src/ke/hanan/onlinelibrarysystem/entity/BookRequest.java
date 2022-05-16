package ke.hanan.onlinelibrarysystem.entity;

import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "ONLINELIBRARYSYSTEM_BOOK_REQUEST")
@Entity(name = "onlinelibrarysystem_BookRequest")
public class BookRequest extends StandardEntity {
    private static final long serialVersionUID = -4322740254780529760L;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "BOOK_NAME")
    private String bookName;

    @Column(name = "AUTHOR")
    private String author;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}