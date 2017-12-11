package finalExam409;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class BayesClassifier {
	static double mean = 0.0;
	static double sd = 0.0;
	static ArrayList<String[]> trainData = new ArrayList<String[]>();
	static ArrayList<String[]> testData = new ArrayList<String[]>();
	static String[][] res = null;
	static String[][] tes = null;
	static int oneCounter = 0;
	static int twoCounter = 0;
	static int threeCounter = 0;
	static double oneMean = 0;
	static double twoMean = 0;
	static double threeMean = 0;
	static double oneSD;
	static double twoSD;
	static double threeSD;

	static public void main(String[] args) throws Exception {
		initialize();
		initializeTest();
		bc();

	}

	static void initialize() throws Exception{
		File file = new File("C:/Users/Adi/Desktop/409 Final/Ex2_train.txt");
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
	static void initializeTest() throws Exception{
		File file = new File("C:/Users/Adi/Desktop/409 Final/Ex2_test.txt");
		FileReader reader = new FileReader(file);
		BufferedReader buffReader = new BufferedReader(reader);
		String s = null;
		while((s = buffReader.readLine()) != null){
			String[] arr = s.split("[\n,]");
			testData.add(arr);
		}
		buffReader.close();

		tes = new String[testData.size()][testData.get(0).length];
		tes = testData.toArray(tes);

	}

	static void bc(){

		for (int i=0; i<res.length; i++){
			for (int j=0; j<res[i].length; j++){
				if(res[i][j].equals("1")){
					oneCounter++;
					oneMean += Double.parseDouble(res[i][j-1]);
				}
				else if(res[i][j].equals("2")){
					twoCounter++;
					twoMean += Double.parseDouble(res[i][j-1]);
				}
				else if((res[i][j].equals("3"))){
					threeCounter++;
					threeMean += Double.parseDouble(res[i][j-1]);
				}
				else{}
			}
		}

		oneMean = oneMean/oneCounter;
		twoMean = twoMean/twoCounter;
		threeMean = threeMean/threeCounter;
		oneSD = findSD(1);
		twoSD = findSD(2);
		threeSD = findSD(3);

		normalize();

//				System.out.println(oneCounter + twoCounter + threeCounter);
//				System.out.println(oneCounter+ " " + twoCounter+ " " + threeCounter);
//				System.out.println(oneMean+ " " + twoMean+ " " + threeMean);
//				System.out.println(oneSD+ " " + twoSD+ " " + threeSD);
	}

	static double findSD(double val){
		double sumofVals1 = 0;
		double sumofVals2 = 0;
		double sumofVals3 = 0;


		for (int i=0; i<res.length; i++){
			for (int j=0; j<res[i].length; j++){
				if(res[i][j].equals("1")){
					sumofVals1 += Math.pow((Double.parseDouble(res[i][j-1]) - oneMean),2);

				}
				else if(res[i][j].equals("2")){
					sumofVals2 += Math.pow((Double.parseDouble(res[i][j-1]) - twoMean),2);
				}
				else if(res[i][j].equals("3")){
					sumofVals3 += Math.pow((Double.parseDouble(res[i][j-1]) - threeMean),2);
				}
				else{}
			}
		}

		double sd1 = Math.sqrt((1.0)/(oneCounter-1.0) * sumofVals1);
		double sd2 = Math.sqrt((1.0)/(oneCounter-1.0) * sumofVals2);
		double sd3 = Math.sqrt((1.0)/(oneCounter-1.0) * sumofVals3);
		if(val == 1){
			return sd1;
		}
		else if(val == 2){
			return sd2;
		}
		else if(val == 3){
			return sd3;
		}
		else{return 0.0;}
	}

	static public void normalize(){
		int[] label = new int[tes.length];
		for (int i=1; i<tes.length; i++){

			double m = Double.parseDouble(tes[i][0]);




			double math1 = (Math.sqrt(2*Math.PI)*oneSD);
			double math2 = (Math.sqrt(2*Math.PI)*twoSD);
			double math3 = (Math.sqrt(2*Math.PI)*threeSD);

			double exp11 = -Math.pow((m-oneMean),2);
			double exp12 = 2*Math.pow(twoSD,2);
			double exp21 = -Math.pow((m-twoMean),2);
			double exp22 = 2*Math.pow(twoSD,2);
			double exp31 = -Math.pow((m-threeMean),2);
			double exp32 = 2*Math.pow(threeSD,2);

			double emath1 = Math.pow(Math.E,(exp11/exp12));
			double emath2 = Math.pow(Math.E,(exp21/exp22));
			double emath3 = Math.pow(Math.E,(exp31/exp32));

			double normalize1 = (1.0/math1)* emath1;
			double normalize2 = (1.0/math2) * emath2;
			double normalize3 = (1.0/math3) * emath3;

			System.out.println(normalize1+ " " + normalize2+ " " + normalize3);

			if(normalize1 > normalize2 && normalize1 > normalize3){
				label[i] = 1;
			}
			else if(normalize2 > normalize3 && normalize2 > normalize1){
				label[i] = 2;
			}
			else if(normalize3 > normalize2 && normalize3 > normalize1){
				label[i] = 3;
			}
			else if(normalize1 == normalize2){
				label[i] = (int) ( Math.random() * 2 + 1);
			}
			else if(normalize1 == normalize3){
				label[i] = (int) ( Math.random() * 2 + 1);
				if(label[i] == 2){
					label[i] = 3;
				}
			}
			else if(normalize2 == normalize3){
				label[i] = (int) ( Math.random() * 3 + 2);
			}
			else{
				label[i] = (int) ( Math.random() * 3 + 1);
			}
		}
		for (int j=1; j<tes.length; j++){
			System.out.println(tes[j][0] + " " + "Desired: " + tes[j][1] + " Calculated: " + label[j]);
		}
	}
}
