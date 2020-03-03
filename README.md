# CryptoCurrency Wallet Simulator API

This is a simple currency wallet application providing a REST
API that allows operations over currency wallets.

REST API `https://min-api.cryptocompare.com/documentation` is used for obtaining the prices and available symbols of currencies.

## Install

    mvn clean install -DskipTests=true

## Run the app

    mvn spring-boot:run

Application is available via Swagger on http://localhost:8080/swagger-ui.html

# REST API services

## 1. Get all cryptocurrencies with prices

### Request

`GET /cryptocurrency`

    curl -i -H 'Accept: application/json' http://localhost:8080/cryptocurrency

### Response

```
[
  {
    "symbol": "string",
    "name": "string",
    "prices": [
      {
        "code": "string",
        "value": 0
      }
    ]
  }
]
```

## 2. Get all wallets

### Request

`GET /wallet`

    curl -i -H 'Accept: application/json' http://localhost:8080/wallet

### Response

```
[
  {
    "id": 0,
    "name": "string",
    "amount": 0,
    "cryptoCurrency": {
      "symbol": "string",    
      "name": "string",
      "prices": [
        {
          "code": "string",
          "value": 0
        }
      ]
    }
  }
]
```

## 3. Create a new wallet

### Request

`POST /wallet/`

    curl --header "Content-Type: application/json" 
    --request POST 
    --data '{"name": "Bitcoin Wallet","currencySymbol": "BTC","amount": "10"}' http://localhost:8080/wallet

    curl --header "Content-Type: application/json" 
    --request POST 
    --data '{"name": "Ripple Wallet","currencySymbol": "XRP","amount": "200"}' http://localhost:8080/wallet
    
### Response

```
{
  "id": 0,
  "name": "string",
  "amount": 0,
  "cryptoCurrency": {
    "name": "string",
    "symbol": "string",
    "prices": [
      {
        "code": "string",
        "value": 0
      }
    ]
  }
}
```

## 3. Get wallet

### Request

`GET /wallet/id`

    curl -i -H 'Accept: application/json' http://localhost:8080/wallet/1
    
### Response

```
{
  "id": 0,
  "name": "string",
  "amount": 0,
  "cryptoCurrency": {
    "symbol": "string",
    "name": "string",
    "prices": [
      {
        "code": "string",
        "value": 0
      }
    ]
  }
}
```

