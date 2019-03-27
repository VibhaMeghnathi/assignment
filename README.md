# assignment
Handzap Assignment

Tools & Technology used : Java 8, Maven, Spring Boot, Jsoup, Gson;

* Java 8, used lambda expression to stream a list of links.
* Used Threads to resolve time complexity.
* JSoup used to connect with html pages and load attributes and elements information.
* Spring Boot is used to creste REST service to run 3 tasks seperataly.
* Maven used to download dependecny while deploy the application.
* Gson used to convert collection data to JSON data.

Intall steps: (1) import project in IDE (2) give "mvn install" on pom.xml (3) run "HtmScrapperApplication.java". (4) give below url into browser to test API

* localhost:8888/theHindu/authors
* localhost:8888/theHindu/articles/{author name}
* localhost:8888/theHindu/{title}/{description}


I used concurrentHashMap to stored approx 6k record for feb'2010 articles, spring boot application take 3 min approx to start, cause while starting application, all records got loaded and write into Map, which will used to fetch data in above three API's
