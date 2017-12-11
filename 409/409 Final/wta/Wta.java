package finalExam409;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Wta {
	static ArrayList<String[]> trainData = new ArrayList<String[]>();
	static String[][] res = null;


	static void initialize() throws Exception{
		File file = new File("C:/Users/Adi/Desktop/409 Final/Ex1_data.txt");
		FileReader reader = new FileReader(file);
		BufferedReader buffReader = new BufferedReader(reader);
		String s = null;
		while((s = buffReader.readLine()) != null){
			String[] arr = s.split("[\n,]");
			trainData.add(arr);
		}
		buffReader.close();

		res = new String[trainData.size()][trainData.get(0).length];
		res = trainData.toArray(res);

	}

	static void cluster(){
		//use formula Wk = (mWk+aX) / (m+1)
		// interate through res[][] to set COG
		// update weights while adding new values
		// if value is too far from threshold then create new cluster
		// continue updating COG of clusters
	}


	public static void main(String[] args) throws Exception{
		initialize();

	}

}
