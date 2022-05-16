package ke.hanan.onlinelibrarysystem.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "ONLINELIBRARYSYSTEM_PUBLISHER")
@Entity(name = "onlinelibrarysystem_Publisher")
@NamePattern("%s|description")
public class Publisher extends StandardEntity {
    private static final long serialVersionUID = -6284183743863772936L;

    @Column(name = "PUBLISHER_NAME")
    private String publisherName;

    @Column(name = "CODE")
    private String code;

    @Column(name = "DESCRIPTION")
    private String description;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }
}