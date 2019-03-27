package com.assignment.htmscrapper.scrap.service.impl;

import com.assignment.htmscrapper.scrap.pojo.Article;
import com.assignment.htmscrapper.scrap.service.HtmlScrapperService;
import com.assignment.htmscrapper.scrap.util.HtmlProcessing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class HtmlScrapperImpl implements HtmlScrapperService {

    private Map<String, List<Article>> map = null;
    
    @PostConstruct
    public void fetchDataMap() {
        map = HtmlProcessing.getMap();
    }
    
    public Set<String> getAuthors() {
        return map.keySet();
    }
    
    public List<Article> getArticleByTitleAndDesc(String title, String desc) {
        Set<Map.Entry<String, List<Article>>> set = map.entrySet();
        List<Article> articleList = new ArrayList();
        for (Map.Entry e : set) {
            List<Article> list = (List<Article>) e.getValue();
            for (Article a : list) {
                if ((a.getTitle().contains(title) && title != null && title != "") || (a.getDescription().contains(desc) && desc != null && desc != "")) {
                    articleList.add(a);
                }
            }
        }
        return articleList;
    }
    
    public List<Article> getArticlesByAuthors(String authorName) {
        return map.containsKey(authorName) ? map.get(authorName) : null;
    }
 }
