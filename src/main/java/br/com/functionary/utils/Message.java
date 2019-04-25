package br.com.functionary.utils;

public class Message {

    private String text;
    private Boolean result;
    private Long id;

    public Message(String text, Boolean result, Long id) {
        this.result = result;
        this.text = text;
        this.id = id;
    }

    public Message(String text, Boolean result) {
        this.result = result;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
