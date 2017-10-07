package artificialIntelligenceCMSC;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Random;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DataGenerator {

	static double[] maleHeight = new double[2000];
	static double[] maleWeight = new double[2000];
	static double[] femaleHeight= new double[2000];
	static double[] femaleWeight= new double[2000];
	static int maleCounter = 0;
	static int femaleCounter = 0;
	private static String fileName = new String("/data.txt");
	static DecimalFormat df = new DecimalFormat("#.##");
	static BufferedWriter bw = null;
	static FileWriter fw = null;
	static int overallCounter = 0;
	static StringBuilder printed = new StringBuilder(); 
	static Random r = new Random();
	static int miss = 0;
	static int write = 0;
	
	public static double generateMaleHeight(){
	
		double vals = r.nextGaussian() * 2.9 + 69.1;
		return vals;
	}
	public static double generateFemaleHeight(){
		double vals = r.nextGaussian() * 2.7 + 63.7;
		return vals;
	}
	public static double generateLowerMaleWeight(){
		miss++;
		double vals = r.nextGaussian() * 8.515 + 142; // 5"6'
		return vals;
	}
	public static double generateMidMaleWeight(){
		
		double vals = r.nextGaussian() * 9.66 + 160; //5ft 9in
		return vals;
	}
	public static double generateHighMaleWeight(){
		
		double vals = r.nextGaussian() * 10.82 + 178; //6ft
		return vals;
	}
	public static double generateLowerFemaleWeight(){
		miss++;
		double vals = r.nextGaussian() * 6.49 + 105.5; // 5"1'
		return vals;
	}
	public static double generateMidFemaleWeight(){
		
		double vals = r.nextGaussian() * 7.07 + 115.5; // 5"3' 
		return vals;
	}
	public static double generateHighFemaleWeight(){
		
		double vals = r.nextGaussian() * 7.93 + 130; // 5"6'
		return vals;
	}

	public static void generator(){

		while(maleCounter < 2000 || femaleCounter < 2000){
			//System.out.println(maleCounter + " "+ femaleCounter);
			int genderVal = (int)(Math.random()*2);
			if(maleCounter == 2000){
				genderVal = 1;
			}
			if(femaleCounter == 2000){
				genderVal = 0;
			}
			if(genderVal == 0){
				maleHeight[maleCounter] = generateMaleHeight();
				double tempHeight = maleHeight[maleCounter];
				if(tempHeight < 66.2){
					maleWeight[maleCounter] = generateLowerMaleWeight();
				}
				else if(tempHeight>= 66.2 && tempHeight < 72){
					maleWeight[maleCounter] = generateMidMaleWeight();
				}
				else{
					maleWeight[maleCounter] = generateHighMaleWeight();
				}
					write++;
					tempHeight = Double.parseDouble(df.format(tempHeight));
					maleWeight[maleCounter] = Double.parseDouble(df.format(maleWeight[maleCounter]));
					printed.append(tempHeight + " " + maleWeight[maleCounter] + " 0\n");
				//System.out.println(tempHeight + " " + maleWeight[maleCounter]+ " 0");
				maleCounter++;
			}
			else{
				femaleHeight[femaleCounter]= generateFemaleHeight();
				double tempHeight = femaleHeight[femaleCounter];
				if(tempHeight < 61){
					femaleWeight[femaleCounter] = generateLowerFemaleWeight();
				}
				else if(tempHeight>= 61 && tempHeight < 66.4){
					femaleWeight[femaleCounter] = generateMidFemaleWeight();
				}
				else{
					femaleWeight[femaleCounter] = generateHighFemaleWeight();
				}
					write++;
					tempHeight = Double.parseDouble(df.format(tempHeight));
					femaleWeight[femaleCounter] = Double.parseDouble(df.format(femaleWeight[femaleCounter]));
					printed.append(tempHeight + " " + femaleWeight[femaleCounter] + " 1\n");
				//System.out.println(tempHeight + " " + femaleWeight[femaleCounter]+ " 1");
				femaleCounter++;
			}
		}
	}

	public static void main(String[] args){
		//		generateMaleHeight();
		//		generateFemaleHeight();
		generator();
		System.out.println(write);
		System.out.println(maleCounter + " " + femaleCounter);
		File file = new File(fileName);
		try {
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			bw.write(printed.toString());
			bw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("File written Successfully");
		System.out.println("done");
		
	}
}
