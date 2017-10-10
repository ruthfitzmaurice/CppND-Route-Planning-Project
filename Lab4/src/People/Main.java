package People;

import ie.ucd.items.*;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {

		// Maker two people
		Drinker myDrinker = new Drinker("Pat", "A", 10);
		NotDrinker myNotDrinker = new NotDrinker("Ann", "B", 15);

		// Make drinks. Two alcoholic drinks and one non alcoholic drink
		Drink AlcoholicDrink1 = new Wine("Wine", 20.0, 1.2, WineType.White);
		Drink AlcoholicDrink2 = new Wine("More wine", 10.5, 4.5, WineType.Rose);
		Drink NonAlcoholicDrink = new Water("Water", 500);

		// Give the alcoholic drinker alcoholic drinks
		myDrinker.drink(AlcoholicDrink2);
		myDrinker.drink(AlcoholicDrink1);
		myDrinker.drink(AlcoholicDrink2);

		// Check if pat is drunk
		if (myDrinker.isDrunk()) {
			System.out.println(myDrinker.getFirstName() + " , you have passed your limit!");
		} else {
			System.out.println("You are not drunk, " + myDrinker.getFirstName());
		}

		// Give the alcoholic drinker a non alcoholic drink
		myDrinker.drink(NonAlcoholicDrink);

		// Test the non alcoholic drinker
		myNotDrinker.drink(AlcoholicDrink1); // Try giving a Ann an alcoholic drink
		myNotDrinker.drink(NonAlcoholicDrink); // Give her a non alcoholic drink

	}

}
