# About this project

The objective is to create a program that takes a website URL, checks all the links contained in that website, and returns the ones that are broken (HTTP status 4XX - Client Error, 5XX - Server Error).

These are the steps that I followed so far:

- Install SCALA/SBT. Write Scala "Hello World" program.
- Create program that takes a URL, downloads the web-page, and prints \<TITLE> found on the page.
 - Extend this program to "grab" each link (\<a>) on the page and "print" its HREF value.
- Extend program to:
 - Expand each path (from HREF) to a full URL,
 - Discard HREF value that is not a path/location or a full URL, 
 - Check each URL, presenting HTTP response code.
- Learn more about HTTP Protocol
- Learn about databases. Tinker with SQL. 
- Define database schema on paper. Install PostgreSQL, create schema in Postgres.

As I mentioned previously, this is a work in progress. 

Currently I'm working on how to connect the database to the scala program in order to store inputs/outputs.

The file **CheckWebpage.scala** contains the core of my program. 
