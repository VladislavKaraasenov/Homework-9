package homework.nine.dictionary.helpers;

import java.io.*;
import java.util.*;
import homework.nine.dictionary.model.Entry;

public class PersistenceHelpers {

	private static final String SEPARATOR = "=";
	private static final String FILE_NAME = "Words/words.txt";

	public static void save(Collection<Entry> entries) throws IOException {
		try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(FILE_NAME)))) {
			
			for (Entry entry : entries) {
				String s = createString(entry);
				writer.println(s);
			}
		}
	}

	public static Collection<Entry> load() throws FileNotFoundException {
		List<Entry> entries = new ArrayList<>();
		try (Scanner sc = new Scanner(new BufferedInputStream(new FileInputStream(FILE_NAME)))) {
			
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				Entry entry = createEntry(line);
				entries.add(entry);
			}
			return entries;
		}
	}

	private static String createString(Entry entry) {
		StringBuilder builder = new StringBuilder();
		builder.append(entry.getWord());
		builder.append(SEPARATOR);
		builder.append(entry.getTranslation());
		builder.append(SEPARATOR);
		builder.append(entry.getTranscription());

		return builder.toString();
	}

	private static Entry createEntry(String line) {
		String[] elements = line.split(SEPARATOR);
		return new Entry(elements[0], elements[1], elements[2]);
	}
}
