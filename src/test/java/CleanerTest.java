import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class CleanerTest {

	private List<String> wordList;
	private TFIDFCalculator calculator;

	@Before
	public void setup() {
		wordList = new ArrayList<String>();
		calculator = new TFIDFCalculator();
	}

	public List<String> getWordsFromArticle(String name) {
		LanguageReader lr = new LanguageReader(name);
		return process(lr.getLanguage());
	}

	private List<String> process(List<String> raw) {
		List<String> wordList = new ArrayList<String>();

		for (String str : raw) {
			str = str.replaceAll("[^a-zA-Z0-9-%]", " ");
			str = str.replaceAll("^ +| +$|( )+", "$1");

			String words[] = str.split("\\s+");
			for (int i = 0; i < words.length; i++) {
				wordList.add(words[i]);
			}
		}
		return wordList;
	}

	@Test
	public void test() {
			List<String> strings = Arrays.asList("COVID-19    ", "hotdog hatdog", "(100%)");
			
			System.out.println(process(strings));
			
	}

}
