package preprocess;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class People implements node {
	private HashMap<Integer, Double> storRepo;
	private final int id;
	private int numOfRepo;
	private String name;
	private HashSet <Integer> countLikes;

	public People(int id, String name) {
		this.id = id;
		storRepo= new HashMap<Integer, Double>();
		numOfRepo = 0;
		this.name = name;
		countLikes = new HashSet<Integer>();
	}

	public void add(int repoId, String eventType) {
		if(eventType.equals("PullRequestEvent")||eventType.equals("PushEvent")) {
		Double score = this.calculateSocre(eventType);
		if (!storRepo.containsKey(repoId))
			storRepo.put(repoId, score);
		else {
			Double newScore = storRepo.get(repoId) + score;
			storRepo.put(repoId, newScore);
		}
	}
		else if (eventType.equals("WatchEvent")) {
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
	public String toString() {
		String a = " is type people with name " +this.name+"\n"+"associate repo: "
	+this.superToString();
		return a;
	}
	*/
	
	public String toString() {
		String a = this.name + ",people";
		return a;
	}
	
	public String superToString() {
		//Set a = storRepo.entrySet();
		String p = "";
		for(Entry<Integer, Double> entry:storRepo.entrySet()) {
			p+= "Repo Id: "+ entry.getKey()+" got contribution from this person with value "+entry.getValue() +"\n";
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
		double totalScore =0;
		for(Entry<Integer, Double> entry:storRepo.entrySet()) {
			totalScore+=entry.getValue();
		}
		if(totalScore<800)return false;
		return true;
	}

	@Override
	public String type() {
		
		return "People";
	}
}
