package org.talian.model;

public class Message {
    private String name;
    private String content;

    @Override
    public String toString(){
        return "{" +
                "name=" + '\'' +
                ", content=" + '\'' +
                "}";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
