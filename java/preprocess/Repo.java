package preprocess;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;






public class Repo implements node{
private int id;
private String name;
private int count;
private HashMap <Integer, Double> countPeople;
//private HashSet <Integer> countLikes;

public Repo(int id, String name)  {
	this.id = id;
	countPeople = new  HashMap <Integer, Double>();
	//countLikes = new  HashSet <Integer>();
	this.name = name;
	count =0;
}

public int getCount() {
	return count;
}

public void add(int peopleId, String eventType) {
	if(!eventType.equals("WatchEvent")) {
	if (countPeople.containsKey(id)) {
		Double count = countPeople.get(id)+this.calculateSocre( eventType);
		countPeople.put(id, count);
	}
	else countPeople.put(id, (this.calculateSocre(eventType)));
}
	else {
		
		//System.out.println("----------------------------------------");
		count++;
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
	String a = this.name + " is type repo with id: "+ this.id+"\n"
			+this.superToString();
	return a;
}
*/
public String toString() {
	String a = this.name + ",repo";
	return a;
}

public String superToString() {
	String p = "";
	//Iterator<Integer> a = countLikes.iterator();
	//while(a.hasNext()) {
	//	p+= "with associate people  People id: "+a.next()+ " likes Id:"+this.id+" ,name: "+this.name + "\n";
	//}
	return p;
	
}
/*
 */
public Double calculateSocre( String eventType) {
	if(eventType.equals("PushEvent"))
	return 10.0;
	else if(eventType.equals("PullRequestEvent")){
		return 7.0;
	}
	else return 0.0;
}
public boolean evaluate() {
	double totalScore = 0;
	int totalLikes =0;
	for(Entry<Integer, Double> entry:countPeople.entrySet()) {
		totalScore+=entry.getValue();
	}
//	Iterator<Integer> a = countLikes.iterator();
//	while(a.hasNext()) {
//	count++;
//	}
	if(count>800||totalScore>800)return true;
	//if(totalScore>50)return true;
	return false;
	
}

@Override
public String type() {

	return "Repo";
}
}
