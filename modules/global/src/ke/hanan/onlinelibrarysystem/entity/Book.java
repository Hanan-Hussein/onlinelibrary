package ke.hanan.onlinelibrarysystem.entity;

import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;
import java.util.Date;

@Table(name = "ONLINELIBRARYSYSTEM_BOOK")
@Entity(name = "onlinelibrarysystem_Book")
public class Book extends StandardEntity {
    private static final long serialVersionUID = 6722968359395068931L;

    @Column(name = "ISBN")
    private String isbn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SUBJECT_ID")
    private Subject subject;

    @JoinColumn(name = "AUTHORS_ID")
    @OneToOne(fetch = FetchType.LAZY)
    private Author authors;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PUBLISHER_ID")
    private Publisher publisher;

    @Column(name = "DOWNLOADS")
    private String downloads;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOOKFILE_ID")
    private FileDescriptor bookfile;

    @Column(name = "BOOK_TITLE")
    private String bookTitle;

    @Temporal(TemporalType.DATE)
    @Column(name = "YEAR_PUBLISHED")
    private Date year_published;

    @Column(name = "EDITION_NUMBER")
    private Integer editionNumber;

    @Lob
    @Column(name = "BOOK_SUMMARY")
    private String bookSummary;

    @Temporal(TemporalType.DATE)
    @Column(name = "RECIEVE_DATE")
    private Date receiveDate;

    public void setAuthors(Author authors) {
        this.authors = authors;
    }

    public Author getAuthors() {
        return authors;
    }

    public FileDescriptor getBookfile() {
        return bookfile;
    }

    public void setBookfile(FileDescriptor bookfile) {
        this.bookfile = bookfile;
    }

    public String getDownloads() {
        return downloads;
    }

    public void setDownloads(String downloads) {
        this.downloads = downloads;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getBookSummary() {
        return bookSummary;
    }

    public void setBookSummary(String bookSummary) {
        this.bookSummary = bookSummary;
    }

    public Integer getEditionNumber() {
        return editionNumber;
    }

    public void setEditionNumber(Integer editionNumber) {
        this.editionNumber = editionNumber;
    }

    public Date getYear_published() {
        return year_published;
    }

    public void setYear_published(Date year_published) {
        this.year_published = year_published;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}