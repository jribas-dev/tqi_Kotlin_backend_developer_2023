## TQI KOTLIN BACKEND DEVELOPER

### Start App

- localhost:8080

### pgAdmin

- localhost:5050

### Entity definition

![](/assets/entity_definition.png)

### Category API

- Post ( /api/category ) - save new category
- Delete ( /api/category/ {id} ) - delete a category without childs
- Get 
  - ( /api/category/ {id} ) - show category by id
  - ( /api/category/list ) - show categories list
  - ( /api/category/list/ {id} ) - show categories by parentId

### Product API

- Post ( /api/product ) - save new product
- Get
    - ( /api/product/list ) - list all products
    - ( /api/prodcut/list/ {category_id} ) - list all products by category

### Cart API

- Post ( /api/cart ) - add new item on cart
- Get  ( /api/cart/new ) - open new cart

### Sale / Checkout API

- Post ( /api/checkout ) - start new sale checkout (requestParam = cartCode)
- Post ( /api/payment ) - start sale payment (requestparam = cartCode, totalPaid)

---
[Github](https://github.com/jribas-dev) | 
[LinkedIn](https://www.linkedin.com/in/jribas-dev/)