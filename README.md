# wikiMedia

Discription :
This project retrieves articles from wikipedia website related to (Amman) or ( Jordan), then store the result in SQLite DB, and
it provides a search feature that search stored articles based on snippet, and it represents the smallest size, largest size, 
median size, based on wordcount plus the total wordcount for all articles.

Problems and bugs :
 - Each hit will return only 10 articles records even if I was using "ailimit" as a query param.
 - I have a problem in HomePage.jsp, I can't handle the response for the REST request to represent it.
