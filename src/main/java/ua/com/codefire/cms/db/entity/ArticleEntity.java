package ua.com.codefire.cms.db.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by User on 10.12.2016.
 */
@Entity
@Table(name="articles")
public class ArticleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Long id;
    @NotEmpty
    @Column(name = "article_title")
    private String title;
    @NotEmpty
    @Column(name = "article_content")
    private String content;
    @Column(name = "article_date")
    private Timestamp date;
    @NotEmpty
    @Column(name = "article_authors")
    private String authors;

    public ArticleEntity() {
    }

    public ArticleEntity(String title, String content, Timestamp date, String authors) {
        this.title = title;
        this.content = content;
        this.date = date;
        this.authors = authors;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long Id) {
        this.id = Id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String Title) {
        this.title = Title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String Content) {
        this.content = Content;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp Date) {
        this.date = Date;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String Authors) {
        this.authors = Authors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArticleEntity articles = (ArticleEntity) o;

        if (id != articles.id) return false;
        if (title != null ? !title.equals(articles.title) : articles.title != null)
            return false;
        if (content != null ? !content.equals(articles.content) : articles.content != null)
            return false;
        if (date != null ? !date.equals(articles.date) : articles.date != null)
            return false;
        if (authors != null ? !authors.equals(articles.authors) : articles.authors != null)
            return false;

        return true;
    }
}
