package homework.nine.dictionary.model;

public class Entry implements Comparable<Entry> {

	private String word;
	private String translation;
	private String transcription;

	public Entry(String word, String translation, String transcription) {
		this.word = word;
		this.translation = translation;
		this.transcription = transcription;
	}

	public String getWord() {
		return word;
	}

	public String getTranslation() {
		return translation;
	}

	public String getTranscription() {
		return transcription;
	}

	@Override
	public int compareTo(Entry o) {
		return this.word.compareTo(o.word);
	}

}
