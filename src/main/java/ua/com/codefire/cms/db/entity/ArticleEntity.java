package ua.com.codefire.cms.db.entity;

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
    private Long Id;
    @Column(name = "article_title")
    private String Title;
    @Column(name = "article_content")
    private String Content;
    @Column(name = "article_date")
    private Timestamp Date;
    @Column(name = "article_authors")
    private String Authors;

    public ArticleEntity() {
    }

    public ArticleEntity(String title, String content, Timestamp date, String authors) {
        Title = title;
        Content = content;
        Date = date;
        Authors = authors;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }

    public Timestamp getDate() {
        return Date;
    }

    public void setDate(Timestamp Date) {
        this.Date = Date;
    }

    public String getAuthors() {
        return Authors;
    }

    public void setAuthors(String Authors) {
        this.Authors = Authors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArticleEntity articles = (ArticleEntity) o;

        if (Id != articles.Id) return false;
        if (Title != null ? !Title.equals(articles.Title) : articles.Title != null)
            return false;
        if (Content != null ? !Content.equals(articles.Content) : articles.Content != null)
            return false;
        if (Date != null ? !Date.equals(articles.Date) : articles.Date != null)
            return false;
        if (Authors != null ? !Authors.equals(articles.Authors) : articles.Authors != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        Long result = Id;
        result = 31 * result + (Title != null ? Title.hashCode() : 0);
        result = 31 * result + (Content != null ? Content.hashCode() : 0);
        result = 31 * result + (Date != null ? Date.hashCode() : 0);
        result = 31 * result + (Authors != null ? Authors.hashCode() : 0);
        return result.hashCode();
    }
}
