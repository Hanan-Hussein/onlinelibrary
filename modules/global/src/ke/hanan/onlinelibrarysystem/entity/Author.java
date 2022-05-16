package ke.hanan.onlinelibrarysystem.entity;

import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;
import java.util.List;

@Table(name = "ONLINELIBRARYSYSTEM_AUTHOR")
@Entity(name = "onlinelibrarysystem_Author")
public class Author extends StandardEntity {
    private static final long serialVersionUID = -1851160076386668329L;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "ADDRESS")
    private String address;

    @JoinTable(name = "ONLINELIBRARYSYSTEM_BOOK_AUTHOR_LINK",
            joinColumns = @JoinColumn(name = "AUTHOR_ID"),
            inverseJoinColumns = @JoinColumn(name = "BOOK_ID"))
    @ManyToMany
    private List<Book> books;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "JOURNAL_ARTICLE_ID")
    private JournalArticle journalArticle;

    public JournalArticle getJournalArticle() {
        return journalArticle;
    }

    public void setJournalArticle(JournalArticle journalArticle) {
        this.journalArticle = journalArticle;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}