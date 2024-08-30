book-controller
================================================================
RES END POINTS:
GET
/api/books/{id}

DELETE
/api/books/{id}

GET
/api/books

GET
/api/books/genre/{id}

GET
/api/books/author/{id}

GET
/api/books/library-branch/{id}

GET
/api/books/borrower/{id}

POST: /api/books
{
"book_name":"Mahabharatham"
}

POST: /api/books/author/{id}/  @RequestBody author information
{
"author_name":"Elango"
}

POST: /api/books/genre/{id}/  @RequestBody genre information
{
"genre_name":"history"
}

POST: /api/books/librarybranch/{id} @RequestBody librarybranch information
{
"libraryBranchName":"Ghandhi National Library",
 "libraryBranchLocation":"Chennai",
  "workingHours":"Mon-Fri 9AM-6PM"
}

PUT: /api/books/{id}
{
"book_name":"Mahabharatham"
}


author-controller
================================================================
GET
/api/authors/{id}

PUT
/api/authors/{id}

DELETE
/api/authors/{id}

GET
/api/authors

POST
/api/authors

POST
/api/authors/book/{id}/
{
"book_name":"Kalki"
}


POST: /api/authors
{
"author_name":"Kalki"
}
POST: /api/authors/book/{id}
{
"book_name":"Ponniyin Selvan"
}
PUT: /api/authors/{id}
{
"author_name":"Kalki V1.2"
}

borrower-controller
================================================================
GET
/api/borrowers/{id}

PUT
/api/borrowers/{id}
{
  "borrowerName": "string",
  "borrowerEmail": "string",
  "borrowerDOB": "string",
  "borrowerPhone": "string"
}

DELETE
/api/borrowers/{id}

GET
/api/borrowers

POST
/api/borrowers
{
  "borrowerName": "string",
  "borrowerEmail": "string",
  "borrowerDOB": "string",
  "borrowerPhone": "string"
}
POST
/api/borrowers/book/{id}
{
"book_name": "string"
}

genre-controller
================================================================
GET
/api/genres/{id}

PUT
/api/genres/{id}
{
"genre_name": "string"
}

DELETE
/api/genres/{id}

POST
/api/genres/
{
"genre_name": "string"
}
POST
/api/genres/book/{id}
{
"book_name": "string"
}
GET
/api/genres


library-branches-controller
================================================================
GET
/api/library-branches/{id}

PUT
/api/library-branches/{id}
{
   "libraryBranchName": "string",
  "libraryBranchLocation": "string",
  "workingHours": "string"
  }

DELETE
/api/library-branches/{id}

GET
/api/library-branches

POST
/api/library-branches
{
   "libraryBranchName": "string",
  "libraryBranchLocation": "string",
  "workingHours": "string"
  }

POST
/api/library-branches/book/{id}
{
"book_name":"Ponniyin Selvan v1.2"
}
