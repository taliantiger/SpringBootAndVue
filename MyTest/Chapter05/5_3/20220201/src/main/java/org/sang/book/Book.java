package org.sang.book;

import javax.persistence.*;

@Entity(name = "t_book")     // t_book 才是作为表的名称，  运行App.class, t_book表 自动生成
public class Book {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;


    @Column(name = "book_name", nullable = false)
    private String name;


    private String author;

    private Float price;

    @Transient
    private String description;



    public String toString(){
        return "Book{" +
                "id" + id +
                ", name" + name + '\'' +
                ", author" + author + '\'' +
                ", price" + price +
                ", description" + description + '\''+
                "}";
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
