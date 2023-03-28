
import csv

header = ['id', 'name', 'category', 'description', 'cost', 'image', 'brand']


chocolates = [
    {
        "id": 1,
        "name": "Kinder",
        "category": "milk",
        "description": "Round chocolate with white chocolate filling.",
        "cost": 2,
        "image": "../images/products/kinder.jpeg",
        "brand": "kinder"
    },
    {
        "id": 2,
        "name": "Chocolate bars",
        "category": "dark",
        "description": "Assorted chocolate bars",
        "cost": 3,
        "image": "../images/products/bars.jpg",
        "brand": "other"
    },
    {
        "id": 3,
        "name": "Ferrero By Piece",
        "category": "dark",
        "description": "Round chocolate truffles",
        "cost": 3,
        "image": "../images/products/ferrero.jpeg",
        "brand": "ferrero"
    },
    {
        "id": 4,
        "name": "Milka",
        "category": "milk",
        "description": "Normal Milk Chocolates",
        "cost": 4,
        "image": "../images/products/milka.jpeg",
        "brand": "milka"
    },
    {
        "id": 5,
        "name": "Assorted Sees Candy Box",
        "category": "dark",
        "description": "Box full of assorted Sees Candy chocolates",
        "cost": 30,
        "image": "../images/products/sees.jpg",
        "brand": "sees"
    },
    {
        "id": 6,
        "name": "Sees Candy - White Chocolate Box",
        "category": "white",
        "description": "20 pcs of Sees Candy white chocolate!",
        "cost": 35,
        "image": "../images/products/seeswhite.jpg",
        "brand": "sees"
    },
    {
        "id": 7,
        "name": "Lindt Lindor White Chocolate Truffle",
        "category": "white",
        "description": "A bag of the famous lindt lindor's white chocolate!",
        "cost": 14,
        "image": "../images/products/lindorwhite.jpg",
        "brand": "lindor"
    },
    {
        "id": 8,
        "name": "Godiva White Chocolate Assortment Gift Box",
        "category": "white",
        "description": "A box full of Godiva's white chocolate assortment",
        "cost": 30,
        "image": "../images/products/godivawhite.jpg",
        "brand": "godiva"
    },
    {
        "id": 9,
        "name": "Godiva Milk Chocolate Gift Box",
        "category": "milk",
        "description": "A box of Godiva's milk chocolate!",
        "cost": 28,
        "image": "../images/products/godiva.jpg",
        "brand": "godiva"
    },
    {
        "id": 10,
        "name": "Lindt Lindor Milk Chocolate Candy Truffles",
        "category": "milk",
        "description": "A bag of the famous lindt lindor's milk chocolate!",
        "cost": 14,
        "image": "../images/products/lindor.jpg",
        "brand": "lindor"
    },

    {
        "id": 11,
        "name": "Ferrero Assorti",
        "category": "milk",
        "description": "Rafaello and ferrero combined",
        "cost": 18,
        "image": "../images/products/ferreroAssorti.jpg",
        "brand": "ferrero"
    }
  ]

with open('chocolates.csv', 'w', encoding='UTF8', newline='') as f:
    writer = csv.writer(f)

    # write the header
    writer.writerow(header)

    # write the data
    for chocolate in chocolates:
        writer.writerow(chocolate.values())
