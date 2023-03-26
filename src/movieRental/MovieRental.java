package movieRental;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class MovieRental {

	Scanner scan = new Scanner(System.in);
	Random rand = new Random();

	String title;
	String genre;
	int rating;
	int addPrice = 0;
	int totalPrice;

	ArrayList<String> titleList = new ArrayList<String>();
	ArrayList<String> genreList = new ArrayList<String>();
	ArrayList<Integer> ratingList = new ArrayList<Integer>();
	ArrayList<Integer> priceList = new ArrayList<Integer>();
	ArrayList<String> IDList = new ArrayList<String>();

	public MovieRental() {
		Menu();
	}

	public void Menu() {

		int choose =0;

		do {
			try {
				System.out.println("Movie Rental");
				System.out.println("============");
				System.out.println("1. Add new Movie");
				System.out.println("2. View Movie (Sort by Title Ascending)");
				System.out.println("3. Rent Movie");
				System.out.println("4. EXIT");
				System.out.print(">> ");
				choose = scan.nextInt();
				if (choose == 1) {
					add();
				} else if (choose == 2) {
					view();
				} else if (choose == 3) {
					rent();
				} else if (choose == 4) {
					System.exit(0);
				}
			} catch (Exception e) {
				choose = Integer.MIN_VALUE;
			} scan.nextLine();
		} while (choose > 4 || choose < 1);


	}

	public void add() {

		do {
			System.out.print("Input Movie Title [Length Must be 3 - 20 chars] : ");
			title = scan.nextLine();
		} while (title.length() > 20 || title.length() < 3);

		do {
			System.out.print("Input Movie Genre [Horror | Comedy | Action] : ");
			genre = scan.nextLine();
		} while (!genre.equals("Horror") && !genre.equals("Comedy") && !genre.equals("Action"));

		do {
			System.out.print("Input Movie Rating [1-10] : ");
			rating = scan.nextInt();
		} while (rating < 1 || rating > 10);

		if (genre.equals("Comedy")) {
			addPrice = 3000;
		} else if (genre.equals("Action")) {
			addPrice = 4000;
		} else if (genre.equals("Horror")) {
			addPrice = 5000;
		}
		
		totalPrice = (title.length() * 500) + addPrice;
		
		priceList.add(totalPrice);
		
		String id = "";
		char randomChar = (char) ('A' + rand.nextInt(26));
		char randomChar2 = (char) ('A' + rand.nextInt(26));
		int randomNumber = rand.nextInt(10);
		int randomNumber2 = rand.nextInt(10);
		int randomNumber3 = rand.nextInt(10);
		id += (randomChar + "");
		id += (randomChar2 + "");
		id += (randomNumber + "");
		id += (randomNumber2 + "");
		id += (randomNumber3 + "");
		System.out.println("Generated MovieID: " + id);

		titleList.add(title);
		genreList.add(genre);
		ratingList.add(rating);
		IDList.add(id);

		System.out.println();
		System.out.println("Insert Success!");
		System.out.println();

		Menu();
	}


	public void view() {
		for (int i = 0; i < titleList.size(); i++) {
			for (int j = 0; j < titleList.size() - 1; j++) {
				if (titleList.get(j).compareTo(titleList.get(j + 1)) > 0) {
					String temp;
					int temporary;
					
					temp = titleList.get(j);
					titleList.set(j, titleList.get(j + 1));
					titleList.set((j + 1), temp);
					
					temp = genreList.get(j);
					genreList.set(j, genreList.get(j + 1));
					genreList.set((j + 1), temp);
					
					temporary = ratingList.get(j);
					ratingList.set(j, ratingList.get(j + 1));
					ratingList.set((j + 1), temporary);
					
					temporary = priceList.get(j);
					priceList.set(j, priceList.get(j + 1));
					priceList.set((j + 1), temporary);
					
					temp = IDList.get(j);
					IDList.set(j, IDList.get(j + 1));
					IDList.set((j + 1), temp);
				}
			}
		}
		
		if (titleList.isEmpty()) {
			System.out.println("No Movie Found");
			System.out.println();
			Menu();
		} else {
			System.out.println("===========================================================================");
			System.out.printf("| %-3s | %-6s | %-30s | %-10s | %-4s | %-10s | \n", "NO", "ID", "Title", "Genre", "Rating", "Price");
			System.out.println("===========================================================================");
			for (int i = 0; i < titleList.size(); i++) {
				System.out.printf("| %-3d | %-6s | %-30s | %-10s | %-4d | %-10d |\n", (i+1), IDList.get(i), titleList.get(i), genreList.get(i),  ratingList.get(i), priceList.get(i));
			}
			System.out.println("===========================================================================");
			
			System.out.println();
			Menu();
			
		}



	}

	public void rent() {

		Integer index = 0;
		int money = 0;
		int change = 0;
		
		if (titleList.isEmpty()) {
			System.out.println("No Movie Found");
			System.out.println();
			Menu();
		} else {
			System.out.println("=======================================================");
			System.out.printf("| %-3s | %-6s | %-30s | %-10s | %-4s | %-10s | \n", "NO", "ID", "Title", "Genre", "Rating", "Price");
			System.out.println("=======================================================");
			for (int i = 0; i < titleList.size(); i++) {
				System.out.printf("| %-3d | %-6s | %-30s | %-10s | %-4d | %-10d |\n", (i+1), IDList.get(i), titleList.get(i), genreList.get(i),  ratingList.get(i), priceList.get(i));
			}
			System.out.println("=======================================================");
			
			System.out.println();
			
		}
			
			do {
				try {
					System.out.print("Choose Movie index [1-" + IDList.size() + "]: ");
					index = scan.nextInt(); 			
				} catch (Exception e) {
					System.out.println("Input must be numeric");
					index = Integer.MIN_VALUE;
				}
				scan.nextLine();
					
			} while (index < 0 || index > IDList.size());
		
			do {
				System.out.print("Input money to Rent [MIN " + priceList.get(index-1) + "]: ");
				money = scan.nextInt(); scan.nextLine();
			} while (money <= priceList.get(index-1));
			
			change = money - priceList.get(index-1);
			
			System.out.println("Pay Rent Successful with " + change + " Change");
			Menu();
		}
	

	public static void main(String[] args) {
		new MovieRental();

	}

}
