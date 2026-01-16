# Bank Management System (Java + PostgreSQL)

## Project Overview
This is a simple Java project demonstrating how to work with `Bank`, `Customer`, and `VIPCustomer` objects while connecting to a PostgreSQL database.  
The program allows you to:
- Add regular and VIP customers
- View all customers
- Sort customers by balance
- Search customers by name
- Store and manage data in PostgreSQL
- Perform basic CRUD operations (Create, Read, Update, Delete)

## Requirements
- Java 11 or higher
- PostgreSQL database
- PostgreSQL JDBC driver
- IntelliJ IDEA (recommended)

## Setup
1. Install PostgreSQL and create a database called `bankdb`.
2. Create tables using pgAdmin or SQL:

```sql
CREATE TABLE bank_account (
    account_number VARCHAR(50) PRIMARY KEY,
    balance DOUBLE PRECISION
);

CREATE TABLE customer (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    account_number VARCHAR(50),
    is_vip BOOLEAN,
    bonus_rate DOUBLE PRECISION,
    FOREIGN KEY (account_number) REFERENCES bank_account(account_number)
);
