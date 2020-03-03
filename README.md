# CryptoCurrency Wallet Simulator API

This is a simple currency wallet application providing a REST
API that allows operations over currency wallets.

REST API `https://min-api.cryptocompare.com/documentation` is used for obtaining the prices and available symbols of currencies.

## Install

    mvn clean install -DskipTests=true

## Run the app

    mvn spring-boot:run

Application is available via Swagger on http://localhost:8080/swagger-ui.html

# REST API

The REST API to the example app is described below.

## 1. Get all cryptocurrencies with prices

### Request

`GET /cryptocurrency`

    curl -i -H 'Accept: application/json' http://localhost:8080/cryptocurrency

### Response

```
[
  {
    "name": "string",
    "prices": [
      {
        "code": "string",
        "value": 0
      }
    ],
    "symbol": "string"
  }
]
```
