package com.assignment.htmscrapper.scrap.util;
   
import com.assignment.htmscrapper.scrap.pojo.Article;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.List;
import java.util.Map;

public class HtmlParseUtil {

    private static final Logger log = LoggerFactory.getLogger(HtmlParseUtil.class);
    public static Map<String, List<Article>> authorMap = null;
    public static int count = 0;
    
    public static Map getMap() {
        if (authorMap == null) {
            authorMap = new ConcurrentHashMap<String, List<Article>>();
            readHtml();
        }
        return authorMap;
    }
    
    public static void readHtml() {
        try {
            Elements dayList = Jsoup.connect("https://www.thehindu.com/archive/web/2010/02").timeout(10 * 10000000)
                    .get().select("a.ui-state-default");
            dayList.parallelStream().forEach(day -> {
                new Thread(new readDays(day.attr("href"))).start();
            });
        } catch (Exception e) {
            log.error("# Unable to read month link !!! : {}"+e);
        }
    }
    
    static class readDays implements Runnable {
        String dayLink = null;
        readDays(String dayLink) {
            this.dayLink = dayLink;
        }
        @Override
        public void run() {
            try {
            (Jsoup.connect(dayLink).timeout(10 * 10000000).get()).select("ul.archive-list li a").stream()
                        .forEach(articles -> {
                            try {
                                Elements article = (Jsoup.connect(articles.attr("href")).timeout(10 * 10000000).get())
                                        .select("div.article div");
                                String title = article.select("h1.title").text();
                                String description = article.select("h2.intro").text();
                                String author = article.select("a.auth-nm").text();
                                if (authorMap.containsKey(author)) {
                                    List<Article> list = authorMap.get(author);
                                    list.add(new Article(title, description));
                                    authorMap.put(author, list);
                                } else {
                                    List<Article> list = new ArrayList();
                                    list.add(new Article(title, description));
                                    authorMap.put(author, list);
                                }
                                log.info("{} Record Count : {}  Author : {}  Title :  {} ",Thread.currentThread().getName(),count++,author,title);
                            } catch (IOException e) {
                                log.error("# Failed to read article link : {}",e);
                            }
                        });
            } catch (IOException e) {
                log.error("# failed to read Date article : {}",e);
            }
        }

    }
  }
