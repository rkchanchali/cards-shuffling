package com.nike.coding.sample.cardsshuffling.controller;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;

import static org.junit.Assert.*;

import com.nike.coding.sample.cardsshuffling.model.Deck;

public class CardsShufflingControllerTest {
	
	
	@Mock
	Map<String, Deck> mockDecks;
	
	
	CardsShufflingController controller = new CardsShufflingController();
	
	@Before
	public void setup() {
		mockDecks = new HashMap<String, Deck>();
		mockDecks.put("Deck1", new Deck("Deck1"));
		mockDecks.put("Deck2", new Deck("Deck2"));
		mockDecks.put("Deck3", new Deck("Deck3"));
		mockDecks.put("Deck4", new Deck("Deck4"));
		
		controller.setDecks(mockDecks);
		
	}
	
	@Test
	public void getListOfDecks() {
		assertEquals(HttpStatus.OK, controller.getListOfDecks().getStatusCode());
	}
	
	@Test
	public void getDeckOfCardsTest() {
		String deckName = "Deck1";
		assertEquals(HttpStatus.OK, controller.getDeckOfCards(deckName).getStatusCode());
		
	}
	
	@Test
	public void getDeckOfCardsNoDeckTest() {
		String deckName = "Deck";
		assertEquals(HttpStatus.NOT_FOUND, controller.getDeckOfCards(deckName).getStatusCode());
		
	}
	
	@Test
	public void addDeckOfCardsTest() {
		String deckName = "Deck5";
		controller.addDeckOfCards(deckName);
		mockDecks = controller.getDecks();
		assertNotNull(mockDecks.get(deckName));
	}
	
	@Test
	public void addDeckOfCardsAlreadyAddedTest() {
		String deckName = "Deck1";
		controller.addDeckOfCards(deckName);
		mockDecks = controller.getDecks();
		assertNotNull(mockDecks.get(deckName));
	}
	
	@Test
	public void deleteDeckOfCards() {
		String deckName = "Deck1";
		controller.deleteDeckOfCards(deckName);
		mockDecks = controller.getDecks();
		assertNull(mockDecks.get(deckName));
	}
	
	@Test
	public void shuffleRandomlyTest() {
		String deckName = "Deck2";
		controller.shuffleDeckOfCards(deckName);
		mockDecks = controller.getDecks();
		assertNotNull(mockDecks.get(deckName));
	}
	
	@Test
	public void shuffleHandShuffleTest() {
		String deckName = "Deck2";
		controller.setShuffle("hand-shuffle");
		controller.shuffleDeckOfCards(deckName);
		mockDecks = controller.getDecks();
		assertNotNull(mockDecks.get(deckName));
	}

}
