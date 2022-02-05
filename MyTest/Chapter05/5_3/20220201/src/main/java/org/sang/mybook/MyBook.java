package org.sang.mybook;

public class MyBook {
    private String name;
    private String author;

    @Override
    public String toString(){
        return "MyBook{" +
                ", name" + name + '\'' +     //    \  代表转义字符，表示想要输出符号  '
                ", author" + author + '\'' +
                "}";

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
}
