package myproject;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

public class Book {
    private String name;
    private String author;

    @JsonIgnore
    private Float price;

//    需要严格按照  yyyy-MM-dd 的小写大写小写的顺序格式。
//    月份"MM"记得一定要大写，不然会导致月份数字大于12而出错
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date publicationDate;


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

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }
}
