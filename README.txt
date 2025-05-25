This project requires a Java SDK.  Target version was Java 17, but it would likely work with older versions and 
should definitely work with newer versions.  To run, you will need to open in an IDE and navigate to
src/main/java/com/example/demo/DemoApplication.java
(Note: Depending on your IDE, it may appear as src/main/java/com.example.demo/DemoApplication.java)

Run this file through the IDE.  You do not need to install Spring for this to run--it is self-contained.
The program will start a server on a localhost port (mine was 8080, but yours could be different).

The goal of the program is to create an interactive website that utilizes a database for permanent storage.
The application uses Thymeleaf for HTML templates, H2 (Hibernate) as a database, JPA (Java Persistence API)
as a wrapper for the API calls to the database, and Spring Boot to bootstrap and run the web server.

Some of the code was given as a base template, but most of the website HTML has been modified by me.  I have added the following functionality:
 * Automatic insertion of new Products and Parts into the database on first load (when the database is empty) using a command line runner.
 * A "Buy Now" button that will decrease the amount of products by 1 when activated.
 * Added a min and max inventory value to the class fields for Parts along with code to enforce these requirements.
 * Presents error messages when there's low inventory, when making a product would lower inventory below the minimum, or when parts is greater than maximum.
 * Two unit tests are created to verify the getters/setters for the new min/max value fields.

A goal of the project is to also work with data validators in Java to handle the validation necessary for the objects instantiated
as part of the program.  This also enforces those requirements on the database when it is instantiated by H2.
