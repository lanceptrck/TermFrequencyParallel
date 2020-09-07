import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ThreadTest {

	private List<String> documentNames;
	private List<List<String>> documents;

	@Before
	public void setup() {
		documentNames = new ArrayList<String>();
		documents = new ArrayList<List<String>>();

		for (int i = 1; i < 11; i++) {
			documentNames.add("article_" + i + ".txt");
			documents.add(getWordsFromArticle("article_" + i + ".txt"));
		}

	}

	public List<String> getWordsFromArticle(String name) {
		LanguageReader lr = new LanguageReader(name);
		return process(lr.getLanguage());
	}

	private List<String> process(List<String> raw) {
		List<String> wordList = new ArrayList<String>();

		for (String str : raw) {
			str = str.replaceAll("[^a-zA-Z0-9]", " ");
			str = str.replaceAll("^ +| +$|( )+", "$1");

			String words[] = str.split("\\s+");
			for (int i = 0; i < words.length; i++) {
				words[i] = words[i].replaceAll("[^\\w]", "");
				wordList.add(words[i]);
			}
		}
		return wordList;
	}

	@Test
	public void test() {

		List<TFIDFThreadVersionA> pandemicThread = new ArrayList<TFIDFThreadVersionA>();

		/*
		 * String keyword1 = "health"; TFIDFThread myThread = new
		 * TFIDFThread(documents.get(0), documents, documentNames.get(0), keyword1);
		 * myThread.start();
		 * 
		 * TFIDFThread myThread1 = new TFIDFThread(documents.get(1), documents,
		 * documentNames.get(1), keyword1); myThread1.start();
		 * 
		 * TFIDFThread myThread2 = new TFIDFThread(documents.get(2), documents,
		 * documentNames.get(2), keyword1); myThread2.start();
		 * 
		 * TFIDFThread myThread3 = new TFIDFThread(documents.get(3), documents,
		 * documentNames.get(3), keyword1); myThread3.start();
		 * 
		 * TFIDFThread myThread4 = new TFIDFThread(documents.get(4), documents,
		 * documentNames.get(4), keyword1); myThread4.start();
		 * 
		 * TFIDFThread myThread5 = new TFIDFThread(documents.get(5), documents,
		 * documentNames.get(5), keyword1); myThread5.start();
		 */

		String keyword = "pandemic";
		for (int i = 0; i < documents.size(); i++) {
			pandemicThread.add(new TFIDFThreadVersionA(documents.get(i), documents, documentNames.get(i), keyword));
		}
		
		for(TFIDFThreadVersionA thread : pandemicThread)
			thread.start();

	}
}
