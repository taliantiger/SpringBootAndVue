package org.sang.model;

public class Book {
    private Integer id;
    private String name;
    private String author;

    /**
     * 书中没有提到，但是自己要重写每个Bean对象的toString()方法
     * 否则Bean对象会自动继承Object 类的toString() 方法
     * 最终导致返回的不是Json字符串，而变成内存地址————Book@4ea34343
     * @return
     */
    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                '}';
    }

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getAuthor(){
        return author;
    }

    public void setAuthor(String author){
        this.author = author;
    }

}
