package homework.nine.dictionary.controller;

import java.io.IOException;
import java.util.*;
import homework.nine.dictionary.model.Dictionary;
import homework.nine.dictionary.model.Entry;

public class ConsoleController {

	private Scanner sc;
	private Dictionary dictionary;

	private enum UserChoice {
		ADD_WORD,
		SEARCH,
		EXIT,
		INVALID,
		REMOVE,
		RETURN,
	}

	public void start() {

		sc = new Scanner(System.in);
		dictionary = new Dictionary();
		boolean shouldContinue = true;

		do {
			UserChoice choice = displayMenu();
			switch (choice) {
			case ADD_WORD:
				addWord();
				break;
			case SEARCH:
				search();
				break;
			case REMOVE:
				removeWord();
				break;
			case RETURN:
				getAllWords();
				break;
			case INVALID:
				System.out.println("Invalid option. Try again.");
				break;
			case EXIT:
				System.out.println("Thank you for using a dictionary.");
				shouldContinue = false;
				break;
			}

		} while (shouldContinue);
	}

	private UserChoice displayMenu() {
		System.out.println("--MENU--");
		System.out.println("1. Add word");
		System.out.println("2. Search word");
		System.out.println("3. Remove word");
		System.out.println("4 Show all words in the dictionary");
		System.out.println("5. Exit");
		System.out.print("Enter option: ");
		int choice = sc.nextInt();
		sc.nextLine();

		switch (choice) {
		case 1:
			return UserChoice.ADD_WORD;
		case 2:
			return UserChoice.SEARCH;
		case 3:
			return UserChoice.REMOVE;
		case 4:
			return UserChoice.RETURN;
		case 5:
			return UserChoice.EXIT;
		default:
			return UserChoice.INVALID;
		}
	}

	private void addWord() {
		System.out.println("Enter word: ");
		String word = sc.nextLine();
		System.out.println("Enter translation: ");
		String translation = sc.nextLine();
		System.out.println("Enter transcription: ");
		String transcription = sc.nextLine();

		Entry entry = new Entry(word, translation, transcription);
		try {
			dictionary.addEntry(entry);
		} catch (IOException e) {
			System.out.println("Error when adding");
		}
		System.out.println("Done");
	}

	private void search() {
		System.out.println("Enter search word: ");
		String word = sc.nextLine();
		Entry entry = dictionary.search(word);
		if (entry == null) {
			System.out.println("No such word exists.");
		} else {
			System.out.println("Translation is: " + entry.getTranslation());
			System.out.println("Transcription is: " + entry.getTranscription());
		}
	}

	private void removeWord() {
		System.out.println("Enter word for remove: ");
		String word = sc.nextLine();
		try {
			Entry entry = dictionary.removeEntry(word);
			if (entry == null) {
				System.out.println("No such word exists.");
			} else {
				System.out.println("Done");
			}
		} catch (IOException e) {
			System.out.println("Error.");
		}
	}

	public void getAllWords() {
		List<Entry> entries = dictionary.getSortedEntries();
		if (entries.isEmpty()) {
			System.out.println("Dictionary is empty");
		} else {
			System.out.println("Word - Transcription - Translation");
			for (Entry entry : entries) {
				System.out.println(entry.getWord() + " - " + entry.getTranscription() + " - " + entry.getTranslation());
			}
		}
	}
}
