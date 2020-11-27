# Markdown Explorer
Markdown Explorer is a place to figure out how to implement a web service for 
serving Markdown documents as HTML. I have been using [JBake](https://jbake.org) for
several years to produce my website. While [AsciiDoc](https://asciidoctor.org) is a 
wonderful (I would say superior) format for creating online documentation, it is not 
the most popular. Markdown has become the de-facto standard for writing text and 
articles on the world wide web. 

## Dependencies
This project depends on [Flexmark](https://github.com/vsch/flexmark-java) to provide
translation from Markdown to HTML. I also use the YAML metadata extension for extraction
of author, title and other metadata from the source. 

The web service portion is build on [Spring Boot](https://spring.io/projects/spring-boot)
which provides way more infrastructure than I would be willing to write myself. 