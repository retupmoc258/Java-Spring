Jason Bagley
Student Id: 012231026

Create a README file that includes notes describing where in the code to find the changes you made for each of parts
C to J. Each note should include the prompt, file name, line number, and change.


C. Customize the HTML user interface for your customer’s application. The user interface should include the shop name,
the product names, and the names of the parts.

src/main/resources/mainscreen.html
    Line 14
        Changed title to "Frank's Computer Store"
    Line 19
        Changed "Shop" to "Frank's Computer Store".
        Centered text.
    Line 22
        Changed "Parts" to "Computer Parts"
    Line 54
        Changed "Products" to "Computers for Sale"


D. Add an “About” page to the application to describe your chosen customer’s company to web viewers and include
navigation to and from the “About” page and the main screen.

src/main/resources/about.html
    Entire file was added and revised.
    Copied head tags from mainscreen.html (for universal style)

src/main/resources/mainscreen.html
    Lines 22-28
        Added a navigation bar (similar one on about.html)

src/main/java/com.example.demo/controller/MainScreenControllerr.java
    Lines 56-59
        Added navigation control for about.html


E. Add a sample inventory appropriate for your chosen store to the application. You should have five parts and five
products in your sample inventory and should not overwrite existing data in the database.

Note: Make sure the sample inventory is added only when both the part and product lists are empty. When adding
the sample inventory appropriate for the store, the inventory is stored in a set so duplicate items
cannot be added to your products. When duplicate items are added, make a “multi-pack” part.

src/main/java/com.example.demo/domain/Product.java
    Lines 108-115
        Implemented new constructor for Product with signature (String name, double price, int inv, Collection<Part> parts)

src/main/java/com.example.demo/domain/InhousePart.java
    Lines 28-31
        Implemented new constructor for InhousePart with signature (String name, double price, int inv, int partId)

src/main/java/com.example.demo/domain/OutsourcedPart.java
    Lines 28-31
        Implemented new constructor for OutsourcedPart with signature (String name, double price, int inv, String companyName)

