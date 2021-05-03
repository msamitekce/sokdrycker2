# Sokdrycker Application

## Start

This project is made by Sami Tekce as Server Programming course final project.

Main goals were:
- a RESTful application
- having email verification at registery
- having different level of users
- creating very basic API keys to use data on other applications and restrict access
- practice Thymeleaf
- combining multiple search parameters
- deploying the application to AWS EC2

### Note: 

For testing you can use  
username : sami  
password : sami


Currently email service is limited to my own email addresses therefor I recieve all registery emails.  
But you can verify your account by following steps:  

1) GET http://13.51.108.218/adv/user  

2) Find your user and copy your API key

3) Edit this URL accordingly http://13.51.108.218/verify/YOURAPIKEY  

4) Your account is verified and you can login

## Data

The data mainly consists of 2 tables:  
1) Product  
    EAN (unique id number)  
    Name  
    Sugar amount  
2) Ecode  
    Code  
    Description  

---

## PRODUCT REST API

> Users can find their API key on their profile page.

### Find all products

```
http://13.51.108.218/api/product?apikey=XXX
```

### Find all products and order by ean, name or sugar

> options for "order" are: ean | name | sugar

```
http://13.51.108.218/api/product?apikey=XXX&order=sugar
```
### Find product by EAN

```
http://13.51.108.218/api/product?apikey=XXX&ean=4027752000795
```
### Find products by ingridient (ecode) if it contains or not

> options for "contains" are: 1 | 0

```
http://13.51.108.218/api/product?apikey=XXX&ecode=e101&contains=0
```


### Find products by ingridient (ecode) if it contains or not then order by ean, name or sugar

> options for "contains" are: 1 | 0
> options for "order" are: ean | name | sugar

```
http://13.51.108.218/api/product?apikey=XXX&ecode=e101&contains=0&order=name
```

### Find products by amount of sugar is equal or less

> options for "sugar" are: any double number

```
http://13.51.108.218/api/product?apikey=XXX&sugar=6.4
```
---

## NON-USER REST API


### Find all users

```
http://13.51.108.218/adv/user 
```

### Find a user by ID

```
http://13.51.108.218/adv/user/{id}
```

### Find all ecodes
```
http://13.51.108.218/adv/ecode
```

### Find an ecode by code
```
http://13.51.108.218/adv/ecode/{code}
```
### Find all products
```
http://13.51.108.218/adv/product
```
