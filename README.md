# BookFinder
This server app talks to goodreads API to perform search function. The server is started with port 8080.

REST ENDPOINT for search:

GET localhost:8080/books?term=TERM&type=SEARCHTYPE&page=PAGENUMBER
TERM is the search keyword
SEARCHTYPE can be author, title or all, default is all
PAGENUMBER determins which page to return from results, default is 1

Authentication:
Username: dotdash
Password: password

Sample GET request: 
127.0.0.1:8080/books?term=zelazny&type=title&page=6
