Jason Bagley
Student Id: 012231026

Create a README file that includes notes describing where in the code to find the changes you made for each of parts
C to J. Each note should include the prompt, file name, line number, and change.


C. Customize the HTML user interface for your customer’s application. The user interface should include the shop name,
the product names, and the names of the parts.

src/main/resources/templates/mainscreen.html
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

src/main/resources/templates/about.html
    Entire file was added and revised.
    Copied head tags from mainscreen.html (for universal style)

src/main/resources/templates/mainscreen.html
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

src/main/java/com.example.demo/domain/InhousePart.java
    Lines 28-31
        Implemented new constructor for InhousePart with signature (String name, double price, int inv, int partId)

src/main/java/com.example.demo/domain/OutsourcedPart.java
    Lines 28-31
        Implemented new constructor for OutsourcedPart with signature (String name, double price, int inv, String companyName)

src/main/java/com.example.demo/domain/Product.java
    Lines 107-109
        Implemented new addParts method to add parts from a list.

src/main/java/com.example.demo/DemoApplication.java
    Lines 27-114
        Added a CommandLineRunner to add inventory when the database is empty.
        Look for PartE.Fix1: Refactored to fix issue with product and part lists (parts were not
            associated with product before).
        Look also for PartE.Fix2: Fixed an issue with parts not being associated with their products.


F.  Add a “Buy Now” button to your product list. Your “Buy Now” button must meet each of the following parameters:
•  The “Buy Now” button must be next to the buttons that update and delete products.
•  The button should decrement the inventory of that product by one. It should not affect the inventory of any of the associated parts.
•  Display a message that indicates the success or failure of a purchase.

src/main/resources/templates/mainscreen.html
    Line 93
        Added HTML code for Buy Now button.

src/main/java/com.example.demo/controllers/AddProductController.java
    Lines 177-191
        Added the code for "Buy Now" to check product inventory and decrement the inventory for purchase.

src/main/resources/templates/purchaseproductconfirmation.html
    Entire file was added and revised.
    Copied from negativeerror.html and then revised on Line 8 (for consistency across the site).

src/main/resources/templates/purchaseproductfailure.html
    Entire file was added and revised.
    Copied from negativeerror.html and then revised on Line 8 (for consistency across the site).


G.  Modify the parts to track maximum and minimum inventory by doing the following:
•  Add additional fields to the part entity for maximum and minimum inventory.
•  Modify the sample inventory to include the maximum and minimum fields.
•  Add to the InhousePartForm and OutsourcedPartForm forms additional text inputs for the inventory so the user can set the maximum and minimum values.
•  Rename the file the persistent storage is saved to.
•  Modify the code to enforce that the inventory is between or at the minimum and maximum value.

src/main/java/com.example.demo/domain/Part.java
    Lines 33-36
        Added minInv and maxInv fields to the abstract class.
        Added minimum value validator for minimum inventory (set to 0 minimum).
        Added minimum value validator for maximum inventory (set to 1 minimum).
    Lines 59-65
        Added new constructor to include minInv and maxInv.
    Lines 125-139
        Added getters and setters for these fields.
    Line 23
        Added a ValidMaxInv custom validator to ensure that the maximum inventory value is greater
            than the minimum inventory value (see below for new files related to this validator)

src/main/java/com.example.demo/domain/InhousePart.java
    Line 33-36
        Added new constructor to include minInv and maxInv.

src/main/java/com.example.demo/domain/OutsourcedPart.java
    Line 33-36
        Added new constructor to include minInv and maxInv.

src/main/java/com.example.demo/DemoApplication.java
    Lines 36, 39. 42. 45, 48, 51, 64, and 57
        Added values for minInv and maxInv to each instantiation.

src/main/resources/templates/InhousePartForm.html
    Lines 16-52
        Made a table for form input to improve organization and allow text labels before the inputs
            (in replacement of placeholders)
    Lines 53-57
        Added a catch for the validator error on Part (described in MaxInvValidator).

src/main/resources/templates/OutsourcedPartForm.html
    Lines 16-53
        Made a table for form input to improve organization and allow text labels before the inputs
            (in replacement of placeholders)

src/main/java/com.example.demo/validators/ValidMaxInv.java
    Entire file
        Created with some help from Google's Generative AI.  I wanted to find a way to put a constraint on
            the maximum inventory value so that it must be greater than the minimum inventory value.

src/main/java/com.example.demo/validators/MaxInvValidator.java
    Entire file
        Created with some help from Google's Generative AI.  This file implements the logic for the
            validator.
        Validator obtains the indicated fields of the class object, checks if either is null, then
            proceeds to check that the max field value is greater than the dependent (minimum) value.
        I learned that this validator had to be implemented on the class level so that it could access
            both fields.
