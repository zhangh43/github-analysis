package preprocess;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class evaluate {

	public static void main(String[] args) throws FileNotFoundException {
		File git = new File("git2015all.txt");
		Scanner sc = new Scanner(git);
		HashMap<Integer, String> node = new HashMap<Integer, String>();
		while (sc.hasNextLine()) {
			String input = sc.nextLine();
			node.put(Integer.parseInt(input.split(",")[1]), input.split(",")[2]);
		}

	}

}
