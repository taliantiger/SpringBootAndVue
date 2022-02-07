package org.sang.mybook;


public class MyBook {
    private Integer id;

    private String author;

    private String name;

    @Override
    public String toString(){
        return "Book{" +
                "id" + id +
                ", name" + name + '\'' +
                ", author" + author + '\'' +
                "}";
     }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
