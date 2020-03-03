# OnLine Meal Ordering System

 Link to the deployed system in Heroku: 
 **[View deployed System](http://onlinemealordering.herokuapp.com)**

## Provided account:
### customer account:
1. username: heroku password: 1234

2. username: tt password: tt

Note: you can also register a new customer account
### manager account:
1. username: admin  password: admin  status: menu added and dish created

2. username: manager  password: manager  status: brand new account

Note: you cannot register as a manager, so you could only login as a manager by the provided account.

## Suggested operation procedure and test scenarios:
These test scenarios only cover the functions in feature B
### customer login
1. Click customer login button on the left top corner on the page, then you could forward to custome login page.(As image 1)
![image 1](https://github.com/wxn0738xx/OnlineMealOrderingSystem/blob/master/readme_img/1.jpeg)
image 1

### customer make order
1. After login, it will forward to customer's home page.(As image 2)
2. Then you could select one resturant and view its menu(recommend "Mr Kitchen"). Then it will forward to corresponding resturant's menu page(As image 3)
3. Then you could click "Add to Order" button at the bottom of each dishes.There will be an alert after every click, if you confirm, 
it will forward to shopping cart(As image 4). 
You could(1) remove item in the shopping cart by clicking "Remove" button
         (2) continue ordering by clicking "Continue Ordering" button
         (3) enter your address and confirm order by clicking "Confirm Order" button

![image 2](https://github.com/wxn0738xx/OnlineMealOrderingSystem/blob/master/readme_img/3.jpeg)
image 2

![image 3](https://github.com/wxn0738xx/OnlineMealOrderingSystem/blob/master/readme_img/4.jpeg)
image 3

![image 4](https://github.com/wxn0738xx/OnlineMealOrderingSystem/blob/master/readme_img/5.jpeg)
image 4

### customer register 
1. Click Register button on the left top corner of the page, then you could forward to customer register page.

![image 1](https://github.com/wxn0738xx/OnlineMealOrderingSystem/blob/master/readme_img/1.jpeg)

![image 2](https://github.com/wxn0738xx/OnlineMealOrderingSystem/blob/master/readme_img/2.jpeg)

2. Click Register button on the left top corner of the page, then you could forward to customer register page.
Once customer is register successfully, it will forward to customer login page. you need to enter the username and password you registed.

###  manager view order
1. Login with a manager account -> username: admin  password: admin 
Note: must to be this account, since other manager's menu are not created
2.  You will view as image 6 below, click "View Order" on the top right corner
![image 6](https://github.com/wxn0738xx/OnlineMealOrderingSystem/blob/master/readme_img/6.jpeg)
image 6
3. Then you will view the order for your resturant as image 7
![image 7](https://github.com/wxn0738xx/OnlineMealOrderingSystem/blob/master/readme_img/7.jpeg)
image 7
## Things to know 
1. As a customer, if your shopping cart has remainning dishes, you cannot enter any resturant. In this case, you can only click "shopping cart" 
at the top bar, enter the shopping cart and confirm dish/remove all dishes.












