package preprocess;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

public class People implements node {
	private HashMap<Integer, Double> storRepo;
	private final int id;
	private int numOfRepo;
	private String name;
	private HashSet<Integer> countLikes;

	public People(int id, String name) {
		this.id = id;
		storRepo = new HashMap<Integer, Double>();
		numOfRepo = 0;
		this.name = name;
		countLikes = new HashSet<Integer>();
	}

	public void add(int repoId, String eventType) {
		if (eventType.equals("PullRequestEvent") || eventType.equals("PushEvent")) {
			Double score = this.calculateSocre(eventType);
			if (!storRepo.containsKey(repoId))
				storRepo.put(repoId, score);
			else {
				Double newScore = storRepo.get(repoId) + score;
				storRepo.put(repoId, newScore);
			}
		} else if (eventType.equals("WatchEvent")) {
			countLikes.add(repoId);
		}
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	/*
	 * public String toString() { String a = " is type people with name "
	 * +this.name+"\n"+"associate repo: " +this.superToString(); return a; }
	 */

	public String toString() {
		String a = this.name + ",people";
		return a;
	}

	public String superToString() {
		// Set a = storRepo.entrySet();
		String p = "";
		for (Entry<Integer, Double> entry : storRepo.entrySet()) {
			p += "Repo Id: " + entry.getKey() + " got contribution from this person with value " + entry.getValue()
					+ "\n";
		}
		return p;
	}

	/*
	 * if push event, +10, if pullrequest, +7;
	 */
	public Double calculateSocre(String eventType) {
		if (eventType.equals("PushEvent"))
			return 10.0;
		else if (eventType.equals("PullRequestEvent"))
			return 7.0;
		else
			return 0.0;
	}

	public boolean evaluate() {
		double totalScore = 0;
		for (Entry<Integer, Double> entry : storRepo.entrySet()) {
			totalScore += entry.getValue();
		}
		if (totalScore < 8000)
			return false;
		return true;
	}

	@Override
	public String type() {

		return "People";
	}

	public String edge(HashMap a) {
		String edge = "";

		for (Entry<Integer, Double> entry : storRepo.entrySet()) {
			if (a.containsKey(entry.getKey())) {
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
