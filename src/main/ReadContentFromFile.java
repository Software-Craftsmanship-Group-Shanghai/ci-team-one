import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ReadContentFromFile {
	public Map<String, String> readFileContentAsMap(String fileName) {
		Map <String,String> userInfo = new HashMap<String, String>();
		File file = new File(fileName);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			while ((tempString = reader.readLine()) != null) {
				userInfo.put(new Integer(line).toString(), tempString);
				line++;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return userInfo;
	}
	
	public static void main(String[] args) {
		ReadContentFromFile ReadContentFromFile = new ReadContentFromFile();
		Map  m = ReadContentFromFile.readFileContentAsMap("C:\\Users\\Administrator\\Desktop\\user.txt");
		System.out.println(m.size());
	}
}
