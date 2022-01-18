package ke.hanan.onlinelibrarysystem.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;

@Table(name = "ONLINELIBRARYSYSTEM_SUBJECT")
@Entity(name = "onlinelibrarysystem_Subject")
@NamePattern("%s|description")
public class Subject extends StandardEntity {
    private static final long serialVersionUID = -7518921243920194898L;

    @Column(name = "SUBJECT_NAME")
    private String subjectName;

    @Column(name = "CODE")
    private String code;

    @Column(name = "DESCRIPTION")
    private String description;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "subject")
    private JournalArticle journalArticle;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public JournalArticle getJournalArticle() {
        return journalArticle;
    }

    public void setJournalArticle(JournalArticle journalArticle) {
        this.journalArticle = journalArticle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}