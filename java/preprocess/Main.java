package preprocess;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		Runtime rt = Runtime.getRuntime();
		String cmd="";
		Process pr = null;
		for(int z=2015; z<=2015;z++) {
		for(int j=1; j<=6; j++) {
			String m;
			if(j<10) {
				 m = "0";
			}
			else  m ="";
		for (int i = 10; i <= 31; i++) {
			for(int t =1; t<=23; t++ )
			{
			if (i < 10) {
				cmd="wget http://data.gharchive.org/"+z+"-"+m+j+"-0" + i + "-"+t+".json.gz";
			} else {
				cmd="wget http://data.gharchive.org/"+z+"-"+m+j+"-" + i + "-"+t+".json.gz";
			}
			
			System.out.println(cmd);
			Process proc = Runtime.getRuntime().exec(new String[] { "bash", "-c", cmd } );
			proc.waitFor();   
			BufferedReader reader =  
		              new BufferedReader(new InputStreamReader(proc.getInputStream()));

		        String line = "";
		        while((line = reader.readLine()) != null) {
		            //System.out.print(line + "\n");
		        }
		        reader.close();
			}
		}
	}
	}
	}

}
