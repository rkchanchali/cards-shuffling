package com.nike.coding.sample.cardsshuffling.model;

import java.util.ArrayList;
import java.util.List;

public class Deck {

	private String name;

	private List<String> cards;

	public Deck(String name) {
		super();
		this.name = name;

		String[] SUITS = { "Clubs", "Diamonds", "Hearts", "Spades" };

		String[] RANKS = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace" };

		// initialize cards
		cards = new ArrayList<String>();
		for (int i = 0; i < SUITS.length; i++) {
			for (int j = 0; j < RANKS.length; j++) {
				//cards[RANKS.length * i + j] = RANKS[j] + "-" + SUITS[i];
				cards.add(RANKS[j] + "-" + SUITS[i]);
			}
		}
	}

	public String getName() {
		return name;
	}

	public List<String> getCards() {
		return cards;
	}

	public void setCards(List<String> cards) {
		this.cards = cards;
	}

	
	
	

}
