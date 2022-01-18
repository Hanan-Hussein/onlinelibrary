package ke.hanan.onlinelibrarysystem.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Table(name = "ONLINELIBRARYSYSTEM_JOURNAL_ARTICLE")
@Entity(name = "onlinelibrarysystem_JournalArticle")
@NamePattern("%s|title")
public class JournalArticle extends StandardEntity {
    private static final long serialVersionUID = -8475203075901791132L;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "VOLUME")
    private String volume;

    @Column(name = "ISSUE")
    private String issue;

    @Column(name = "PAGES")
    private String pages;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_")
    private Date date;

    @Column(name = "SERIES")
    private String series;

    @Column(name = "DOI")
    private String doi;

    @Column(name = "URL")
    private String url;

    @Column(name = "DOWNLOADS")
    private String downloads;

    @OneToMany(mappedBy = "journalArticle")
    private List<Author> author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SUBJECT_ID")
    private Subject subject;

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public List<Author> getAuthor() {
        return author;
    }

    public void setAuthor(List<Author> author) {
        this.author = author;
    }

    public String getDownloads() {
        return downloads;
    }

    public void setDownloads(String downloads) {
        this.downloads = downloads;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}