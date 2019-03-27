package com.assignment.htmscrapper.scrap.service;

import com.assignment.htmscrapper.scrap.pojo.Article;

public interface HtmlScrapperService {

    Set<String> getAuthors();

    List<Article> getArticleByTitleAndDesc(String title, String desc);

    List<Article> getArticlesByAuthors(String authorName);
}
