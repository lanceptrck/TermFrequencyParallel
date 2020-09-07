import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriteUtils {

	private FileWriteUtils() {

	}

	public static void write(String fileName, String data) throws IOException {
		File file = new File(fileName);
		FileWriter fr = new FileWriter(file, true);
		BufferedWriter br = new BufferedWriter(fr);

		System.out.println(data);
		br.write(data + "\n");

		br.close();
		fr.close();
	}

}
