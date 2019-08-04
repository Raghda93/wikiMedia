# wikiMedia

Description : 
This project retrieves articles from Wikipedia website related to (Amman) or ( Jordan), then store the result in SQLite DB, and it provides a search feature that search stored articles based on snippet, and it represents the smallest size, largest size, median size, based on word count plus the total wordcount for all articles.

Problems and bugs :

Each hit will return only 10 articles records even if I was using "ailimit" as a query param.
I have a problem with HomePage.jsp, I can't handle the response from the REST request to represent it.
