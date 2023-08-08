# Projeto tem o intuito de calcular proporcionalmente acréscimos e descontos em contas compartilhadas

## Tecnologias utilizadas
- Java 17
- Spring boot 3.2.1


## Payload request (exemplo)

```json
{
  "totalAmount": 50.00,
  "discount": 20.00,
  "increase": 8.00,
  "orders": [
    {
      "friendName": "Lucas",
      "items": [
        {
          "name": "hamburguer",
          "price": 40.00
        },
        {
          "name": "sobremesa",
          "price": 2.00
        }
      ]
    },
    {
      "friendName": "João",
      "items": [
        {
          "name": "sanduiche",
          "price": 8.00
        }
      ]
    }
  ]
}
```


## Response

```json
{
  "paymentsLink": [
    {
      "proportionalValueToPay": 31.92,
      "paymentLink": "https://picpay.com/2c68499b-9d96-4763-9b0d-5519e1ec8ed1",
      "friendName": "Lucas"
    },
    {
      "proportionalValueToPay": 6.08,
      "paymentLink": "https://picpay.com/3eabacac-9f53-4c7f-9431-8cd38bde9580",
      "friendName": "João"
    }
  ]
}
```

## Curl da request
```
curl --location 'localhost:8080/payments/calculate-proportional' \
--header 'Content-Type: application/json' \
--data '{
    "totalAmount": 50.00,
    "discount": 20.00,
    "increase": 8.00,
    "orders": [
        {
            "friendName": "Lucas",
            "items": [
                {
                    "name": "hamburguer",
                    "price": 40.00
                },
                {
                    "name": "sobremesa",
                    "price": 2.00
                }
            ]
        },
        {
            "friendName": "João",
            "items": [
                {
                    "name": "sanduiche",
                    "price": 8.00
                }
            ]
        }
    ]
}'
```
