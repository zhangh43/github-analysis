package preprocess;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Scanner;

public class Processing {

	public static void main(String[] args) throws FileNotFoundException {
		int num = 0;
		int countP = 0;
		int countR = 0;
		HashMap<Integer, node> map = new HashMap<Integer, node>();
		int year = 2015;
		File git2015 = new File("edge.txt");
		PrintWriter pr = new PrintWriter(git2015);
		HashMap<Integer, Integer> storeNode = new HashMap<Integer, Integer>();
		int countLine = 0;
		Scanner sc = null;
		File git = null;
		while (year <= 2018) {
			git = new File("github" + year + ".txt");
			sc = new Scanner(git);
			year++;
			while (sc.hasNextLine()) {
				int id = -1;
				int rId = -1;
				String checkNode = sc.nextLine();
				if (checkNode.split(",").length == 5) {
					id = Integer.parseInt(checkNode.split(",")[1]);
					rId = Integer.parseInt(checkNode.split(",")[3]);
				} else
					System.out.println(checkNode);
				if (checkNode.split(",").length == 5) {
					if (storeNode.containsKey(id)) {
						int count = storeNode.get(id) + 1;
						storeNode.put(id, count);
					} else
						storeNode.put(id, 1);
					if (storeNode.containsKey(rId)) {
						int rcount = storeNode.get(rId) + 1;
						storeNode.put(rId, rcount);
					} else
						storeNode.put(rId, 1);
				}

			}
			sc.close();
		}

		year = 2015;
		while (year <= 2018) {
			git = new File("github" + year + ".txt");
			sc = new Scanner(git);
			year++;
			while (sc.hasNextLine()) {
				String input = sc.nextLine();
				countLine++;
				if (input.split(",").length < 5) {
				} else {
					String peopleName = input.split(",")[2];
					Integer peopleId = Integer.parseInt(input.split(",")[1]);
					Integer repoId = Integer.parseInt(input.split(",")[3]);
					String eventType = input.split(",")[0];
					String repoName = input.split(",")[4];
					if (storeNode.get(peopleId) > 960 && storeNode.get(repoId) > 1600) {
						if (eventType.equals("PushEvent") || eventType.equals("PullRequestEvent")) {
							if (!map.containsKey(peopleId)) {
								map.put(peopleId, new People(peopleId, peopleName));
							}
							if (!map.containsKey(repoId)) {
								map.put(repoId, new Repo(repoId, repoName));
							}
							map.get(peopleId).add(repoId, eventType);
							map.get(repoId).add(peopleId, eventType);
						} else if (eventType.equals("WatchEvent")) {
							if (!map.containsKey(repoId)) {
								map.put(repoId, new Repo(repoId, repoName));
							}
							if (!map.containsKey(peopleId)) {
								map.put(peopleId, new People(peopleId, peopleName));
							}
							map.get(peopleId).add(repoId, eventType);
							map.get(repoId).add(peopleId, eventType);
						}
					}
					if (countLine % 1000000 == 0) {
						System.out.println(countLine);
					}
				}
			}
			System.out.println("==================");
			sc.close();
		}

		HashMap hm = Processing.getMap();
		for (Map.Entry<Integer, node> entry : map.entrySet()) {
			if (entry.getValue().evaluate()) {

				if (entry.getValue().type().equals("People")) {
					if (!entry.getValue().toString().toLowerCase().contains("bot")) {
						num++;
						countP++;
					}
				} else {
					num++;
					countR++;
					// pr.println("" + num + "," + entry.getKey() + "," + entry.getValue());
				}
			}
			pr.print((entry.getValue().edge(hm)));

		}

		pr.close();

		System.out.println("Done People: " + countP + " Repo: " + countR);

	}

	private static HashMap<Integer, String> getMap() throws FileNotFoundException {
		File git = new File("node.txt");
		Scanner sc = new Scanner(git);
		HashMap<Integer, String> node = new HashMap<Integer, String>();
		while (sc.hasNextLine()) {
			String input = sc.nextLine();
			node.put(Integer.parseInt(input.split(",")[1]), input.split(",")[2]);
		}
		return node;

	}

}
