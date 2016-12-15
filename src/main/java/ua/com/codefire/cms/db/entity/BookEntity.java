package ua.com.codefire.cms.db.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by User on 10.12.2016.
 */
@Entity
@Table(name="books")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;

    @Basic
    @Column(name = "book_name")
    private String name;

    @Basic
    @Column(name = "book_authors")
    private String authors;

    @Basic
    @Column(name = "book_date")
    private Timestamp date;

    @Basic
    @Column(name = "book_pages_amount")
    private Integer pagesAmount;

    @Basic
    @Column(name = "book_text")
    private String bookText;

    public BookEntity() {
    }

    public BookEntity(String name, String authors, Timestamp date, Integer pagesAmount, String bookText) {
        this.name = name;
        this.authors = authors;
        this.date = date;
        this.pagesAmount = pagesAmount;
        this.bookText = bookText;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long bookId) {
        this.id = bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String bookName) {
        this.name = bookName;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String bookAuthors) {
        this.authors = bookAuthors;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp bookDate) {
        this.date = bookDate;
    }

    public Integer getPagesAmount() {
        return pagesAmount;
    }

    public void setPagesAmount(Integer bookPagesAmount) {
        this.pagesAmount = bookPagesAmount;
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

        BookEntity books = (BookEntity) o;

        if (id != books.id) return false;
        if (name != null ? !name.equals(books.name) : books.name != null) return false;
        if (authors != null ? !authors.equals(books.authors) : books.authors != null) return false;
        if (date != null ? !date.equals(books.date) : books.date != null) return false;
        if (pagesAmount != null ? !pagesAmount.equals(books.pagesAmount) : books.pagesAmount != null)
            return false;
        if (bookText != null ? !bookText.equals(books.bookText) : books.bookText != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        Long result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (authors != null ? authors.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (pagesAmount != null ? pagesAmount.hashCode() : 0);
        result = 31 * result + (bookText != null ? bookText.hashCode() : 0);
        return result.hashCode();
    }
}
