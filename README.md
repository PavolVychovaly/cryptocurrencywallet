# CryptoCurrency Wallet Simulator API

This is a simple currency wallet application providing a REST API that allows operations over currency wallets.

REST API `https://min-api.cryptocompare.com/documentation` is used for obtaining the prices and available symbols of currencies.

The prices in JSON response are configurable via property `price.list` in `application.properties` file.

The cryptocurrencies in JSON response of `Get All CryptoCurrencies Service` are configurable via property `cryptoCurrencySymbol.list` in `application.properties` file.

On every create and update wallet, buy currency and transfer values for two wallets service is called remote REST API from `https://min-api.cryptocompare.com/documentation` to calculate prices of specified currency and they are returned in JSON response.

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

## 2. Create a new wallet

### Request

`POST /wallet/`

    curl --header "Content-Type: application/json" --request POST --data '{"name": "Bitcoin Wallet","currencySymbol": "BTC","amount":   "10"}' http://localhost:8080/wallet

    curl --header "Content-Type: application/json" --request POST --data '{"name": "Ripple Wallet","currencySymbol": "XRP","amount": "200"}' http://localhost:8080/wallet
    
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

## 4. Update wallet

### Request

`PUT /wallet/id`

    curl --header "Content-Type: application/json" --request PUT --data '{"name": "Bitcoin Wallet with new name","currencySymbol": "ETH","amount": "300"}' http://localhost:8080/wallet/1
    
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

## 5. Remove wallet

### Request

`DELETE /wallet/id`

    curl --header "Content-Type: application/json" --request DELETE http://localhost:8080/wallet/1
    
### Response

```
Wallet with ID XY successfully deleted.
```

## 6. Get all wallets

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

## 7. Buy currency from wallet

### Request

`POST /wallet/buyCurrency`

    curl --header "Content-Type: application/json" --request POST --data '{"walletId": "1","amount": "100","currencyOfAmount": "ETH"}' http://localhost:8080/wallet/buyCurrency
    
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

## 8. Transfer values for two wallets

### Request

`POST /wallet/transferValues`

    curl --header "Content-Type: application/json" --request POST --data '{"fromWalletId": "1","toWalletId": "2","amount": "100","currencyOfAmount": "ETH"}' http://localhost:8080/wallet/transferValues
