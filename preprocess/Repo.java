package preprocess;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Repo implements node {
	private int id;
	private String name;
	private int count;
	private HashMap<Integer, Double> countPeople;
	private HashSet<Integer> countLikes;

	public Repo(int id, String name) {
		this.id = id;
		countPeople = new HashMap<Integer, Double>();
		countLikes = new HashSet<Integer>();
		this.name = name;
		count = 0;
	}

	public int getCount() {
		return count;
	}

	public void add(int peopleId, String eventType) {
		if (eventType.equals("PushEvent") || eventType.equals("PullRequestEvent")) {
			if (countPeople.containsKey(peopleId)) {
				Double count = countPeople.get(peopleId) + this.calculateSocre(eventType);
				countPeople.put(peopleId, count);
			} else
				countPeople.put(peopleId, (this.calculateSocre(eventType)));
		} else if (eventType.equals("WatchEvent")) {

			// System.out.println("----------------------------------------");
			if (!countLikes.contains(peopleId)) {
				countLikes.add(peopleId);
				count++;
			}
		}
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	/*
	 * public String toString() { String a = this.name + " is type repo with id: "+
	 * this.id+"\n" +this.superToString(); return a; }
	 */
	public String toString() {
		String a = this.name + ",repo";
		return a;
	}

	public String superToString() {
		String p = "";
		// Iterator<Integer> a = countLikes.iterator();
		// while(a.hasNext()) {
		// p+= "with associate people People id: "+a.next()+ " likes Id:"+this.id+"
		// ,name: "+this.name + "\n";
		// }
		return p;

	}

	/*
	 */
	public Double calculateSocre(String eventType) {
		if (eventType.equals("PushEvent"))
			return 10.0;
		else if (eventType.equals("PullRequestEvent")) {
			return 7.0;
		} else
			return 0.0;
	}

	public boolean evaluate() {
		double totalScore = 0;
		// int totalLikes = 0;
		for (Entry<Integer, Double> entry : countPeople.entrySet()) {
			totalScore += entry.getValue();
		}
//	Iterator<Integer> a = countLikes.iterator();
//	while(a.hasNext()) {
//	count++;
//	}
		if (count > 4000 || totalScore > 8000)
			return true;
		// if(totalScore>50)return true;
		return false;

	}

	public double totalScore() {
		double totalScore = 0;

		return totalScore;
	}

	@Override
	public String type() {

		return "Repo";
	}

	public String edge(HashMap b) {
		String edge = "";
		Iterator<Integer> a = countLikes.iterator();
		if (count >= 500) {
			while (a.hasNext()) {
				int i = a.next();
				if (b.containsKey(i))
					edge += i + "," + this.id + "\n";
			}
		}
		for (Entry<Integer, Double> entry : countPeople.entrySet()) {
			if (b.containsKey(entry.getKey())) {
				if (entry.getValue() > 139 && entry.getValue() <= 770) {
					edge += entry.getKey() + "," + this.id + "\n";
				} else if (entry.getValue() > 770 && entry.getValue() <= 1540) {
					edge += entry.getKey() + "," + this.id + "\n";
					edge += entry.getKey() + "," + this.id + "\n";
				} else if (entry.getValue() > 1540 && entry.getValue() <= 2310) {
					edge += entry.getKey() + "," + this.id + "\n";
					edge += entry.getKey() + "," + this.id + "\n";
					edge += entry.getKey() + "," + this.id + "\n";
				} else if (entry.getValue() > 2310 && entry.getValue() <= 3080) {
					edge += entry.getKey() + "," + this.id + "\n";
					edge += entry.getKey() + "," + this.id + "\n";
					edge += entry.getKey() + "," + this.id + "\n";
					edge += entry.getKey() + "," + this.id + "\n";
				} else if (entry.getValue() > 3080 && entry.getValue() <= 6160) {
					edge += entry.getKey() + "," + this.id + "\n";
					edge += entry.getKey() + "," + this.id + "\n";
					edge += entry.getKey() + "," + this.id + "\n";
					edge += entry.getKey() + "," + this.id + "\n";
					edge += entry.getKey() + "," + this.id + "\n";
				} else if (entry.getValue() > 6160 && entry.getValue() <= 12320) {
					edge += entry.getKey() + "," + this.id + "\n";
					edge += entry.getKey() + "," + this.id + "\n";
					edge += entry.getKey() + "," + this.id + "\n";
					edge += entry.getKey() + "," + this.id + "\n";
					edge += entry.getKey() + "," + this.id + "\n";
					edge += entry.getKey() + "," + this.id + "\n";
				} else if (entry.getValue() > 12320 && entry.getValue() <= 24640) {
					edge += entry.getKey() + "," + this.id + "\n";
					edge += entry.getKey() + "," + this.id + "\n";
					edge += entry.getKey() + "," + this.id + "\n";
					edge += entry.getKey() + "," + this.id + "\n";
					edge += entry.getKey() + "," + this.id + "\n";
					edge += entry.getKey() + "," + this.id + "\n";
					edge += entry.getKey() + "," + this.id + "\n";
				} else if (entry.getValue() > 24640) {
					edge += entry.getKey() + "," + this.id + "\n";
					edge += entry.getKey() + "," + this.id + "\n";
					edge += entry.getKey() + "," + this.id + "\n";
					edge += entry.getKey() + "," + this.id + "\n";
					edge += entry.getKey() + "," + this.id + "\n";
					edge += entry.getKey() + "," + this.id + "\n";
					edge += entry.getKey() + "," + this.id + "\n";
					edge += entry.getKey() + "," + this.id + "\n";
				}
			}
		}
		return edge;
	}

}
