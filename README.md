# cards-shuffling
Micro service to Create, get and shuffle deck of cards.

This Microservice exposes 5 operations.

Operation name : getListOfDecks()
Method : GET
Path : /decks
URL : http://localhost:8080/decks

Operation name : getDeckOfCards(@PathVariable("deckName") String deckName)
Method : GET
Path : /decks/{deckName}
URL : http://localhost:8080/decks/{deckName}

Operation name : addDeckOfCards(@PathVariable("deckName") String deckName)
Method : PUT
Path : /decks/{deckName}
URL : http://localhost:8080/decks/{deckName}

Operation name : deleteDeckOfCards(@PathVariable("deckName") String deckName)
Method : DELETE
Path : /decks/{deckName}
URL : http://localhost:8080/decks/{deckName}

Operation name : shuffleDeckOfCards(@PathVariable("deckName") String deckName)
Method : POST
Path : /decks/{deckName}
URL : http://localhost:8080/decks/{deckName}

How to run :

Clone the project in to local machine using below command.
git clone https://github.com/rkchanchali/cards-shuffling.git

It will download the source code. Step in to cards-shuffling directory.

Use below command to run the springboot application.

mvn spring-boot:run

Use below command to run the springboot application and pass the type of shuffle during deployment.
If the value is other than hand-shuffle, it is set to random shuffling by default.

mvn spring-boot:run -Dshuffle.type=hand-shuffle  

How to test:
This service can be tested by using postman or SoapUI by hitting the URLs mentioned above.     
