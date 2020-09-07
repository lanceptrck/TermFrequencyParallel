import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LanguageReader {

	private String fileName;
	private BufferedReader reader;
	private List<String> language;

	public LanguageReader(String fileName) {
		this.fileName = fileName;
		language = new ArrayList<String>();

		try {
			reader = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Given file cannot be found");
		}

		read();

	}

	private void read() {

		if (fileName != null && reader != null) {
			try {
				String line = reader.readLine();

				while (line != null) {
					if (!line.contains("//s+"))
						language.add(line);
					line = reader.readLine();
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException("Something wrong when reading the lines");
			}
		}

	}

	public List<String> getLanguage() {
		return new ArrayList<String>(language);
	}

}
