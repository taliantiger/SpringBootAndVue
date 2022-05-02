package org.talian.model;

public class Chat {
    private String to;
    private String from;
    private String content;

    @Override
    public String toString(){
        return "{" +
                "from=" + from + '\'' +
                ",to=" + to + '\'' +
                ",content=" + content +
                "}";
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
