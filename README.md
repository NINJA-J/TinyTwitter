# Tiny Twitter

Bonus! Bonus! Bonus!


## Architecture

- Spring boot framework with command line entrypoint and datasource auto-configuration
- Mybatis annotation for query and result mapping
- Independent menu control module to reuse menu structure and isolate service implement with user interface

## Feature
- Auto menu set up
  - if you haven't logged in, you can't access 'my blogs', 'my collections' and 'make comment' option
  - if you have written comment/blog, you can only 'update comment/blog' or 'delete comment/blog', otherwise you can only 'add comment/blog'
- Statistics
  - User information statistics about how many blogs and comments have been written by user
  - Blog information statistics about how many people liked or collected each blog
- Complex blog search function
  - Allow fuzzy search on author name, blog title and blog content
  - Allow conditions like whether blogs are liked or collected by user
  - User can combine different search condition

## How to run

1. Run the tiny_twitter.sql in mysql to initiate the database
2. Go to folder that contains tiny-twitter-0.0.1-SHANSHOT.jar
3. Run with the following command, change username and password to yours.
```shell
java -jar tiny-twitter-0.0.1-SHANSHOT.jar --db-username=<username> --db-password=<password>
```
4. Now you can explore the functions of tiny twitter!

If you want to run source code in IDE, add "--db-username=<username> --db-password=<password>" to your program start arguments