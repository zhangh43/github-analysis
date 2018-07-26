package preprocess;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;

public interface node {
	public int getId();

	public String getName();

	public void add(int a, String b);

	public boolean evaluate();

	public String type();

	public String edge(HashMap a);
}
