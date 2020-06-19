# REST-E-Commerce-Website
E-commerce website created with Java Servlets and RESTful web services


The website has 6 pages:
            1) Home Page
            2) Product Description Page
            3) Cart Page 
            4) Checkout Page
            5) Order Details Page
            6) Content Credit Page


1. Home Page (index.jsp)
__________________________________________________________

	1. Home
	    Description: It provides a way to navigate through the home page via the nav bar on the top.
	    The nav bar has 4 buttons and a logo which helps the user navigate through the different sections on 
	    the home page.
	    There is a button that says "FIND YOUR CAR" which will scroll to the Cars for Sale Section.
	    The section in blue provides informational text and link to learn more which scrolls to the about 
	    section.

	2. Cars for Sale:

	   Description: Displays the cars we are selling in a grid layout with the car's name, color, milage, and 
	   price. When user hovers over a car the image zooms in.
	   When user clicks on the car, a new tab will open that will lead to a product description page that has 
     more details about the car and provides a way to order that car.

	3. Recently Viewed Cars:

	   Description: Displays the last five cars viewed by the user, but if the session is new, it does not 
     display any cars.

	4. About
	   Description: Displays informational text about our business and what our company does. This section 
     says that our company sells good quality used cars and provides shipping to our customers.

	5. Contact Us
	   Description: This section shows contact information.


2.  Product Description Page
___________________________________________________________

    This page provides more detailed information about the car user selected in the Cars for Sales section 
    in the home page. The page displays the car's name, color, mileage, features, specifications, and price. 
    The page has a larger image of the car and a carousel component that displays additional interior images 
    of the car. The page has a button which says "ADD TO CART". This button will add the car to the cart and 
    open a popup menu with a confirmation of successfully adding to cart and two button that say "GO TO CART" 
    or "CONTINUE SHOPPING".

    Descriptions.java, AddToCart.java


3.  Cart Page
____________________________________________________________
  	This page is accessible through the nav bar or when user adds an item to cart. On this page user can see a 
    list of all the items they have added to cart and from this page they can navigate to the checkout page where 
    users can add their information.
    
    Cart.java

        
4.  Checkout Page
____________________________________________________________

    This page is accessible through the cart page when user clicks "PROCEED TO CHECKOUT". When the page opens, 
    on the left side is a form which allows users to fill in their name, email, phone number, shipping address, 
    and billing information such as their credit card information.

    On the right user can see the items in their cart including  quantity and delivery. 
    Next there is a delivery option for 7-day ($3000), 14-day ($2000), and a month ($1000) delivery.
    Depending on the options user chooses (quantity and delivery) the page will calculate the total price plus 
    sales tax. The page will then display the total cost of their purchase.

    After user has completed the form and set the proper order options user can click the Checkout button to 
    submit. If the form on the left side is not fully filled out or has missing info the page will inform the 
    user and will not submit until the form is valid.

    Form.java, SubmitOrder.java


5.  Order Details Page
____________________________________________________________
  	This page shows up after clicking the Checkout button on the Checkout page and successfully adding the user's 
    information and order into the database. It then shows the details and summary of the order the user made.

  	OrderDetails.java


6. Content Credit 
____________________________________________________________

    This page is accessed through the footer on the right side that says Content Credit. When user clicks on 
    the link, it will lead to a new page that lists all the sources of our content such as our cover images, 
    car images, and the content details such as car features and specifications.
    
    credit.html


REST services that allow for interaction with the order and product resources stored in the application database:  


 	LOCATION: 
      	
  	CarService.java, CarResource.java,:
      	Get all cars
        i.    Method Type: GET

        ii.   Request URL: CarsLandRest/car

        iii.  Sample Response:
              [
                {
                  "features": "Cruise Control, Auxiliary Audio Input, Alloy Wheels, Overhead Airbags, 
                                Side Airbags, Air Conditioning",
                  "id": 0,
                  "image": "altima.jpg",
                  "image1": "altima1.jpg",
                  "image2": "altima2.jpg",
                  "image3": "altima3.jpg",
                  "make": "Nissan",
                  "mileage": "70K miles",
                  "model": "Altima S",
                  "modelId": "001",
                  "price": "$11,000",
                  "specs": "23/31 mpg, Black/Black, Automatic, 2WD, 2.5L, 4, 170 hp@5600rpm, 175 torque@3900rpm",
                  "year": "Black"
                }
              ]
        Get car by id
        i.    Method Type: GET

        ii.   Request URL: CarsLandRest/car/id

        iii.  Sample Response:
              [
                {
                  "features": "Cruise Control, Auxiliary Audio Input, Alloy Wheels, Overhead Airbags, 
                                Side Airbags, Air Conditioning",
                  "id": 0,
                  "image": "altima.jpg",
                  "image1": "altima1.jpg",
                  "image2": "altima2.jpg",
                  "image3": "altima3.jpg",
                  "make": "Nissan",
                  "mileage": "70K miles",
                  "model": "Altima S",
                  "modelId": "001",
                  "price": "$11,000",
                  "specs": "23/31 mpg, Black/Black, Automatic, 2WD, 2.5L, 4, 170 hp@5600rpm, 175 torque@3900rpm",
                  "year": "Black"
                }
              ]

	OrderService.java, OrderResource.java:
  		Get last order
        i.    Method Type: GET

        ii.   Request URL: CarsLandRest/order

        iii.  Sample Response:
              {
                  "addr": "1234 W 124TH ST",
                  "cardname": "Test Test",
                  "city": "Irvine",
                  "country": "U.S.A",
                  "date": "2020-06-07 00:00:00",
                  "delivery": "1-Month",
                  "email": "PANTEATER@UCI.EDU",
                  "fname": "Test",
                  "lname": "Test",
                  "orderId": 58,
                  "phone": "1234567890",
                  "state": "CA",
                  "total": 75000.0,
                  "zip": "92612"
              }
    	Get order by id
        i.    Method Type: GET

        ii.   Request URL: CarsLandRest/order/id

        iii.  Sample Response:
              {
                  "addr": "1234 W 124TH ST",
                  "cardname": "Test Test",
                  "city": "Irvine",
                  "country": "U.S.A",
                  "date": "2020-06-07 00:00:00",
                  "delivery": "1-Month",
                  "email": "PANTEATER@UCI.EDU",
                  "fname": "Test",
                  "lname": "Test",
                  "orderId": 58,
                  "phone": "1234567890",
                  "state": "CA",
                  "total": 75000.0,
                  "zip": "92612"
              }
    	Insert order
        i.    Method Type: POST

        ii.   Request URL: CarsLandRest/order

        iii.  Sample Response: ok

	TaxService.java, TaxResource.java:
    	Get tax rate
        i.    Method Type: GET

        ii.   Request URL: CarsLandRest/tax/92612

        iii.  Sample Response: 9.0

	ZipService.java, ZipResource.java:
    	Get city/state
        i.    Method Type: GET

        ii.   Request URL: CarsLandRest/zip

        iii.  Sample Response: 
              {
                  "city": "Irvine",
                  "state": "CA",
                  "zip": 92612
              }
                  


-------------------------------------------------------------------------------------------------------------------------

  



