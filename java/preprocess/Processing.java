package preprocess;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Scanner;

public class Processing {

	public static void main(String[] args) throws FileNotFoundException {
		int num = 0;
		int countP=0;
		int countR=0;
		HashMap<Integer, node> map = new HashMap<Integer, node>();
		File git = new File("github2015.txt");
		Scanner sc = new Scanner(git);
		// boolean notFinished = true;
		while (sc.hasNextLine()) {
			String input = sc.nextLine();
			if( input.split(",").length<5) {
				//System.out.println(input);
			}
			else {
			String peopleName = input.split(",")[2];
			Integer peopleId = Integer.parseInt(input.split(",")[1]);
			Integer repoId = Integer.parseInt(input.split(",")[3]);
			String eventType = input.split(",")[0];
			String repoName = input.split(",")[4];
			if (eventType.equals("PushEvent") || eventType.equals("PullRequestEvent")) {
				if (!map.containsKey(peopleId)) {
					map.put(peopleId, new People(peopleId, peopleName));
				}
				if (!map.containsKey(repoId)) {
					map.put(repoId, new Repo(repoId, repoName));
				}
				map.get(peopleId).add(repoId, eventType);
				map.get(repoId).add(peopleId, eventType);
			}
			else if (eventType.equals("WatchEvent")) {
				if (!map.containsKey(repoId)) {
					map.put(repoId, new Repo(repoId, repoName));
				}
				if(!map.containsKey(peopleId)) {
					map.put(peopleId, new People(peopleId, peopleName));
				}
				map.get(peopleId).add(repoId, eventType);
				map.get(repoId).add(peopleId, eventType);
			}
			}
		}
		
		for (Map.Entry<Integer, node> entry : map.entrySet()) {
			//System.out.println("==========================================");
			if(entry.getValue().evaluate()) {
				num++;
				if(entry.getValue().type().equals("People"))
					countP++;
				else countR++;
			System.out.println(""+num+","+entry.getKey()+"," + entry.getValue());
		
			}
		}
		
		/*Iterator it = map.entrySet().iterator();
		while(it.hasNext()) {
			System.out.println(it.next()+"\n");
			System.out.flush();
		}*/
		System.out.println("Done People: "+ countP + " Repo: "+countR);
		sc.close();
	}

}
