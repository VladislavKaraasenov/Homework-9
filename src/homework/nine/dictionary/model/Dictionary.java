package homework.nine.dictionary.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import homework.nine.dictionary.helpers.PersistenceHelpers;

public class Dictionary {

	private Map<String, Entry> entries;

	public Dictionary() {
		try {
			Collection<Entry> loadedEntries = PersistenceHelpers.load();
			entries = new HashMap<>();
			for (Entry entry : loadedEntries) {
				entries.put(entry.getWord(), entry);
			}
		} catch (FileNotFoundException e) {
			entries = new HashMap<>();
		}
	}

	public void addEntry(Entry entry) throws IOException {
		entries.put(entry.getWord(), entry);
		save();
	}

	public Entry search(String word) {
		return entries.get(word);
	}

	public Entry removeEntry(String word) throws IOException {
		Entry entry = entries.remove(word);
		save();
		return entry;
	}

	public List<Entry> getSortedEntries() {
		List<Entry> sortedEntries = new ArrayList<>();
		for (Entry entry : entries.values()) {
			sortedEntries.add(entry);
		}
		Collections.sort(sortedEntries);
		return sortedEntries;
	}

	private void save() throws IOException {
		PersistenceHelpers.save(entries.values());
	}
}
