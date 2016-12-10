package ua.com.codefire.cms.db.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by User on 10.12.2016.
 */
@Entity
@Table(name="books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long Id;

    @Basic
    @Column(name = "book_name")
    private String Name;

    @Basic
    @Column(name = "book_authors")
    private String Authors;

    @Basic
    @Column(name = "book_date")
    private Timestamp Date;

    @Basic
    @Column(name = "book_pages_amount")
    private Integer PagesAmount;

    @Basic
    @Column(name = "book_text")
    private String bookText;

    public Book() {
    }

    public Book(String name, String authors, Timestamp date, Integer pagesAmount, String bookText) {
        Name = name;
        Authors = authors;
        Date = date;
        PagesAmount = pagesAmount;
        this.bookText = bookText;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long bookId) {
        this.Id = bookId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String bookName) {
        this.Name = bookName;
    }

    public String getAuthors() {
        return Authors;
    }

    public void setAuthors(String bookAuthors) {
        this.Authors = bookAuthors;
    }

    public Timestamp getDate() {
        return Date;
    }

    public void setDate(Timestamp bookDate) {
        this.Date = bookDate;
    }

    public Integer getPagesAmount() {
        return PagesAmount;
    }

    public void setPagesAmount(Integer bookPagesAmount) {
        this.PagesAmount = bookPagesAmount;
    }

    public String getBookText() {
        return bookText;
    }

    public void setBookText(String bookText) {
        this.bookText = bookText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book books = (Book) o;

        if (Id != books.Id) return false;
        if (Name != null ? !Name.equals(books.Name) : books.Name != null) return false;
        if (Authors != null ? !Authors.equals(books.Authors) : books.Authors != null) return false;
        if (Date != null ? !Date.equals(books.Date) : books.Date != null) return false;
        if (PagesAmount != null ? !PagesAmount.equals(books.PagesAmount) : books.PagesAmount != null)
            return false;
        if (bookText != null ? !bookText.equals(books.bookText) : books.bookText != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        Long result = Id;
        result = 31 * result + (Name != null ? Name.hashCode() : 0);
        result = 31 * result + (Authors != null ? Authors.hashCode() : 0);
        result = 31 * result + (Date != null ? Date.hashCode() : 0);
        result = 31 * result + (PagesAmount != null ? PagesAmount.hashCode() : 0);
        result = 31 * result + (bookText != null ? bookText.hashCode() : 0);
        return result.hashCode();
    }
}
