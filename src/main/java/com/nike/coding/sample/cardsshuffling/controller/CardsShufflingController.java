package com.nike.coding.sample.cardsshuffling.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nike.coding.sample.cardsshuffling.model.Deck;

@RestController
public class CardsShufflingController {

	// Storing the decks in a Map.
	Map<String, Deck> decks = new HashMap<String, Deck>();

	//Reading the type of shuffle from command line during deployment.
	//Default value is random which is for Random shuffling.
	//IF the value is "hand-shuffle", shuffling algorithm changes 
	@Value("${shuffle.type:random}")
	String shuffle;

	/**
	 * Method returns the list of decks already saved.
	 * 
	 * @return Collection of Decks
	 */
	@RequestMapping(value = "/decks", method = RequestMethod.GET)
	public ResponseEntity<Collection<Deck>> getListOfDecks() {

		return ResponseEntity.ok(decks.values());

	}

	/**
	 * Method returns the deck of cards with the name specified.
	 * @param deckName - Path Parameter
	 * @return Deck
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/decks/{deckName}", method = RequestMethod.GET)
	public ResponseEntity getDeckOfCards(@PathVariable("deckName") String deckName) {

		//Check if the deck with name already exists. If present, return the deck.
		//else, return not found code. 
		if (decks.containsKey(deckName)) {

			return ResponseEntity.ok(decks.get(deckName));

		} else {
			return ResponseEntity.notFound().build();

		}

	}
	

	/**
	 * Method adds the deck of cards if it is not present already
	 * @param deckName - Path parameter
	 */
	@RequestMapping(value = "/decks/{deckName}", method = RequestMethod.PUT)
	public void addDeckOfCards(@PathVariable("deckName") String deckName) {

		//Check whether deck is already present. If not present, add the deck to the list.
		//else, dont add.
		if (!decks.containsKey(deckName)) {
			decks.put(deckName, new Deck(deckName));
		}

	}

	/**
	 * Method deletes the already existing deck from the list.
	 * @param deckName - PAth Parameter
	 */
	@RequestMapping(value = "/decks/{deckName}", method = RequestMethod.DELETE)
	public void deleteDeckOfCards(@PathVariable("deckName") String deckName) {

		decks.remove(deckName);
	}

	/**
	 * Method posts a request to shuffle the deck of cards.
	 * @param deckName - Path Parameter.
	 */
	@RequestMapping(value = "/decks/{deckName}", method = RequestMethod.POST)
	public void shuffleDeckOfCards(@PathVariable("deckName") String deckName) {

		//Read the value of shuffle from command line which is passed during the deployment.
		if("hand-shuffle".equals(shuffle)) {
			Deck deck = decks.get(deckName);
			List<String> shuffledDeck = deck.getCards();
			
			//Repeat the process 3 times.
			int count = 3;
			while(count >0) {
				//Get the first hald of the deck.
				List<String> firstHalf = shuffledDeck.subList(0, (shuffledDeck.size()/2));
				//Get the second half of the deck
				List<String> secondHalf = shuffledDeck.subList((shuffledDeck.size()/2), shuffledDeck.size());
				List<String> mergeList = new ArrayList<String>();
				
				//Shuffle first half
				Collections.shuffle(firstHalf);
				//Shuffle second Half
				Collections.shuffle(secondHalf);
				
				//Merge both the halves and form a deck.
				mergeList.addAll(firstHalf);
				mergeList.addAll(secondHalf);
				//Shuffle the merged deck again, so that in next iteration each will have different set of cards.
				Collections.shuffle(mergeList);
				shuffledDeck = mergeList;
				count--;
			}

	        deck.setCards(shuffledDeck);
	        decks.put(deckName, deck);
		}
		//Shuffling the cards randomly.
		else {
			Deck deck = decks.get(deckName);
			List<String> cardsList = deck.getCards();
			Collections.shuffle(cardsList);
			deck.setCards(cardsList);
			decks.put(deckName, deck);
		}

	}

	public Map<String, Deck> getDecks() {
		return decks;
	}

	public void setDecks(Map<String, Deck> decks) {
		this.decks = decks;
	}

	public String getShuffle() {
		return shuffle;
	}

	public void setShuffle(String shuffle) {
		this.shuffle = shuffle;
	}

}
