package com.assignment.htmscrapper.scrap.ctrl;

import com.assignment.htmscrapper.scrap.service.HtmlScrapperService;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/theHindu")
public class HtmlScrapperController {

private HtmlScrapperService htmlScraperService;

    /**
     * We are using constructor based dependecny injection.
     * @param htmlScraperService
     */
    public HtmlScrapperController(HtmlScrapperService htmlScraperService) {
        this.htmlScraperService = htmlScraperService;
    }
    
    @RequestMapping(value = "/authors", method = RequestMethod.GET)
    public ResponseEntity<String> getAvailableAuthor() {
        return new ResponseEntity(new Gson().toJson(htmlScraperService.getAuthors()), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/articles/{author}", method = RequestMethod.PUT)
    public ResponseEntity<String> getArticlesByAuthors(@PathVariable("author") String authorName) {
        return new ResponseEntity(new Gson().toJson(htmlScraperService.getArticlesByAuthors(authorName)), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/articles/{title}/{desc}", method = RequestMethod.PUT)
    public ResponseEntity<String> getArticleByTitleAndDecs(@PathVariable("title") String title, @PathVariable("desc") String desc) {
        return new ResponseEntity(new Gson().toJson(htmlScraperService.getArticleByTitleAndDesc(title, desc)), HttpStatus.OK);
    }
  }
