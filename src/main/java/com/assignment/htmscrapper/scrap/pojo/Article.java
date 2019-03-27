package com.assignment.htmscrapper.scrap.pojo;

public class Article {

    public String title;
    public String description;

    public Article(String title, String description) {
        super();
        this.title = title;
        this.description = description;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    @Override
    public String toString() {
        return "Article [title=" + title + ", description=" + description + "]";
    }

}
