# Wallet Service (Spring Boot Backend Project)

## Overview

Wallet Service is a backend REST API application that simulates a digital wallet system. It supports wallet creation, credit/debit operations, and transaction tracking.

## Features

* Create wallet for users
* Credit and debit balance
* Transaction history storage
* Exception handling for insufficient balance
* PostgreSQL database integration
* REST API architecture

## Tech Stack

* Java 17
* Spring Boot
* Spring Data JPA
* PostgreSQL
* IntelliJ IDEA
* Postman (API testing)

## API Endpoints

Create wallet:
POST /wallet/create/{userId}

Credit:
POST /wallet/credit/{userId}/{amount}

Debit:
POST /wallet/debit/{userId}/{amount}

Get wallet:
GET /wallet/{userId}

## How to Run

1. Clone repository
2. Configure PostgreSQL in application.properties
3. Run WalletServiceApplication
4. Test APIs using Postman

## Author
Neha Lodhe




Your Name
