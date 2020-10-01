import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProcessUtils {

	private ProcessUtils() {

	}

	public static List<String> getWordsFromArticle(String name) {
		LanguageReader lr = new LanguageReader(name);
		return process(lr.getLanguage());
	}

	private static List<String> process(List<String> raw) {
		List<String> wordList = new ArrayList<String>();

		for (String str : raw) {
			str = str.replaceAll("[^a-zA-Z0-9-%]", " ");
			str = str.replaceAll("^ +| +$|( )+", "$1");

			String words[] = str.split("\\s+");
			for (int i = 0; i < words.length; i++) {
				if (!words[i].isEmpty())
					wordList.add(words[i]);
			}
		}
		return wordList;
	}

	public static void printLinesSeparately(List<String> lines, String fileName){
		for(String str : lines){
			try {
				FileWriteUtils.write(fileName, str);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
