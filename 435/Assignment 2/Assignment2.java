package dataScienceAssignment2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class Assignment2 {
	private static final String MISSING004 = "assignment2_dataset_missing004.csv";
	private static final String MISSING20 = "assignment2_dataset_missing20.csv";
	private static final String COMMA_DELIMITER = ",";
	static String[][] completeData = new String[3588][85];
	static String[][] array004 = new String[3588][85];
	static String[][] arrayImp004 = new String[3588][85];
	static String[][] arrayImpC004 = new String[3588][85];
	static String[][] array20 = new String[3588][85];
	static String[][] arrayImp20 = new String[3588][85];
	static String[][] arrayImpC20 = new String[3588][85];
	static String[][] arrayHD004 = new String[3588][85];
	static String[][] arrayHDC004 = new String[3588][85];
	static String[][] arrayHD20 = new String[3588][85];
	static String[][] arrayHDC20 = new String[3588][85];
	static int count = 1;
	static int countC = 1;
	static int countF = 1;

	public static void initComplete(){
		String line = null;
		BufferedReader br = null;

		int counter = 0;
		try
		{
			br = new BufferedReader(new FileReader("assignment2_dataset_complete"));

			while ((line = br.readLine()) != null) 
			{
				completeData[counter] = line.split(COMMA_DELIMITER);
				//System.out.println(counter);
				if(completeData.length < 0 ){
					System.out.println("sup");
				}
				counter++;
			}

		}catch(Exception ee){
			ee.printStackTrace();
		}finally{
			try{
				br.close();
			}catch(IOException ie){
				System.out.println("Error occured while closing the BufferedReader");
				ie.printStackTrace();
			}
		}		
	}
	
	public static void initalize004(){
		String line = null;
		BufferedReader br = null;

		int counter = 0;
		try
		{
			br = new BufferedReader(new FileReader(MISSING004));

			while ((line = br.readLine()) != null) 
			{
				array004[counter] = line.split(COMMA_DELIMITER);
				//System.out.println(counter);
				if(array004.length < 0 ){
					System.out.println("sup");
				}
				counter++;
			}

			for(int y = 0; y<85;y++){
				for(int x =0; x<3588; x++){
					arrayImp004[x][y] = array004[x][y];
					arrayImpC004[x][y] = array004[x][y];
					arrayHD004[x][y] = array004[x][y];
					arrayHDC004[x][y] = array004[x][y];
				}
			}


		}catch(Exception ee){
			ee.printStackTrace();
		}finally{
			try{
				br.close();
			}catch(IOException ie){
				System.out.println("Error occured while closing the BufferedReader");
				ie.printStackTrace();
			}
		}		
	}
	public static void initalize20(){
		String line = null;
		BufferedReader br = null;

		int counter = 0;
		try
		{
			br = new BufferedReader(new FileReader(MISSING20));

			while ((line = br.readLine()) != null) 
			{
				array20[counter] = line.split(COMMA_DELIMITER);
				//System.out.println(counter);
				if(array20.length < 0 ){
					System.out.println("sup");
				}
				counter++;
			}

			for(int y = 0; y<85;y++){
				for(int x =0; x<3588; x++){
					arrayImp20[x][y] = array20[x][y];
					arrayImpC20[x][y] = array20[x][y];
					arrayHD20[x][y] = array20[x][y];
					arrayHDC20[x][y] = array20[x][y];
				}
			}


		}catch(Exception ee){
			ee.printStackTrace();
		}finally{
			try{
				br.close();
			}catch(IOException ie){
				System.out.println("Error occured while closing the BufferedReader");
				ie.printStackTrace();
			}
		}		
	}

	public static void calculateImputedMean004(){
		double vals = 0;
		int totalNum= 0;
		boolean complete = false;
		System.out.println("Calculating Imputed Mean 004");
		for(int y = 0; y<85;y++){
			for(int x =0; x<3588; x++){
				if(arrayImp004[x][y].charAt(0) == '?'){
					if(complete == false){
						while(count<3557){
							if((arrayImp004[count][y].charAt(0)) != '?'){
								vals += Double.parseDouble((arrayImp004[count][y]));
								totalNum++;
								count++;
							}
							else{
								count++;
							}
						}
						vals = vals/totalNum;
						complete = true;
						arrayImp004[x][y] = String.valueOf(vals);
					}
					else{
						arrayImp004[x][y] = String.valueOf(vals);
					}
				}

			}
			vals = 0;
			count = 1;
			totalNum = 0;
			complete = false;
		}


		BufferedWriter bufferedWriter = null;

		try {

			File file = new File("V00666733_a2_missing004_imputed_mean.csv");
			if(!file.exists()){
				file.createNewFile();
			}

			FileWriter fileWriter = new FileWriter(file);
			bufferedWriter = new BufferedWriter(fileWriter);
			for(int x =1; x<3588; x++){
				for(int y = 0; y<84;y++){
					bufferedWriter.write(arrayImp004[x][y]);
					bufferedWriter.write(",");
				}
				bufferedWriter.newLine();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedWriter != null){
					bufferedWriter.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	}


	public static void calculateImputedMeanConditional004(){
		double valsC = 0;
		int totalNumC= 0;
		double valsF = 0;
		int totalNumF= 0;
		boolean completeC = false;
		boolean completeF = false;
		System.out.println("Calculating Imputed Mean Conditional 004");
		for(int y = 0; y<85;y++){
			for(int x =0; x<3588; x++){
				if(arrayImpC004[x][y].charAt(0) == '?'){
					if(arrayImpC004[x][84].charAt(0) == 'C'){
						if(completeC == false){
							while(countC<3557){
								if((arrayImpC004[countC][y].charAt(0)) != '?' && arrayImpC004[countC][84].charAt(0) == 'C'){
									valsC += Double.parseDouble((arrayImpC004[countC][y]));
									totalNumC++;
									countC++;
								}
								else{
									countC++;
								}
							}
							valsC = valsC/totalNumC;
							countC = 1;
							totalNumC = 0;
							completeC = true;
							arrayImpC004[x][y] = String.valueOf(valsC);
						}
						else{
							arrayImpC004[x][y] = String.valueOf(valsC);
						}
					}

					else{
						if(arrayImpC004[x][y].charAt(0) == '?'){
							if(completeF == false){
								while(countF<3557){
									if((arrayImpC004[countF][y].charAt(0)) != '?' && arrayImpC004[countC][84].charAt(0) == 'F'){
										valsF += Double.parseDouble((arrayImpC004[countF][y]));
										totalNumF++;
										countF++;
									}
									else{
										countF++;
									}
								}
								valsF = valsF/totalNumF;
								countF = 1;
								totalNumF = 0;
								completeF = true;
								arrayImpC004[x][y] = String.valueOf(valsF);
							}
							else{
								arrayImpC004[x][y] = String.valueOf(valsF);
							}
						}
					}
				}
			}
			valsC = 0;
			totalNumC= 0;
			valsF = 0;
			totalNumF= 0;
			completeC = false;
			completeF = false;
			countF = 1;
			countC = 1;
		}

		BufferedWriter bufferedWriter = null;

		try {

			File file = new File("V00666733_a2_missing004_imputed_mean_conditional.csv");
			if(!file.exists()){
				file.createNewFile();
			}

			FileWriter fileWriter = new FileWriter(file);
			bufferedWriter = new BufferedWriter(fileWriter);
			for(int x =1; x<3588; x++){
				for(int y = 0; y<85;y++){
					bufferedWriter.write(arrayImpC004[x][y]);
					bufferedWriter.write(",");
				}
				bufferedWriter.newLine();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedWriter != null){
					bufferedWriter.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	}
	public static void calculateImputedMean20(){
		double vals = 0;
		int totalNum= 0;
		boolean complete = false;
		System.out.println("Calculating Imputed Mean 20");
		for(int y = 0; y<85;y++){
			for(int x =0; x<3588; x++){
				if(arrayImp20[x][y].charAt(0) == '?'){
					if(complete == false){
						while(count<3557){
							if((arrayImp20[count][y].charAt(0)) != '?'){
								vals += Double.parseDouble((arrayImp20[count][y]));
								totalNum++;
								count++;
							}
							else{
								count++;
							}
						}
						vals = vals/totalNum;
						complete = true;
						arrayImp20[x][y] = String.valueOf(vals);
					}
					else{
						arrayImp20[x][y] = String.valueOf(vals);
					}
				}

			}
			vals = 0;
			count = 1;
			totalNum = 0;
			complete = false;
		}


		BufferedWriter bufferedWriter = null;

		try {

			File file = new File("V00666733_a2_missing20_imputed_mean.csv");
			if(!file.exists()){
				file.createNewFile();
			}

			FileWriter fileWriter = new FileWriter(file);
			bufferedWriter = new BufferedWriter(fileWriter);
			for(int x =1; x<3588; x++){
				for(int y = 0; y<84;y++){
					bufferedWriter.write(arrayImp20[x][y]);
					bufferedWriter.write(",");
				}
				bufferedWriter.newLine();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedWriter != null){
					bufferedWriter.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	}


	public static void calculateImputedMeanConditional20(){
		double valsC = 0;
		int totalNumC= 0;
		double valsF = 0;
		int totalNumF= 0;
		boolean completeC = false;
		boolean completeF = false;
		System.out.println("Calculating Imputed Mean Conditional 20");
		for(int y = 0; y<85;y++){
			for(int x =0; x<3588; x++){
				if(arrayImpC004[x][y].charAt(0) == '?'){
					if(arrayImpC004[x][84].charAt(0) == 'C'){
						if(completeC == false){
							while(countC<3557){
								if((arrayImpC004[countC][y].charAt(0)) != '?' && arrayImpC004[countC][84].charAt(0) == 'C'){
									valsC += Double.parseDouble((arrayImpC004[countC][y]));
									totalNumC++;
									countC++;
								}
								else{
									countC++;
								}
							}
							valsC = valsC/totalNumC;
							countC = 1;
							totalNumC = 0;
							completeC = true;
							arrayImpC004[x][y] = String.valueOf(valsC);
						}
						else{
							arrayImpC004[x][y] = String.valueOf(valsC);
						}
					}

					else{
						if(arrayImpC004[x][y].charAt(0) == '?'){
							if(completeF == false){
								while(countF<3557){
									if((arrayImpC004[countF][y].charAt(0)) != '?' && arrayImpC004[countC][84].charAt(0) == 'F'){
										valsF += Double.parseDouble((arrayImpC004[countF][y]));
										totalNumF++;
										countF++;
									}
									else{
										countF++;
									}
								}
								valsF = valsF/totalNumF;
								countF = 1;
								totalNumF = 0;
								completeF = true;
								arrayImpC004[x][y] = String.valueOf(valsF);
							}
							else{
								arrayImpC004[x][y] = String.valueOf(valsF);
							}
						}
					}
				}
			}
			valsC = 0;
			totalNumC= 0;
			valsF = 0;
			totalNumF= 0;
			completeC = false;
			completeF = false;
			countF = 1;
			countC = 1;
		}

		BufferedWriter bufferedWriter = null;

		try {

			File file = new File("V00666733_a2_missing20_imputed_mean_conditional.csv");
			if(!file.exists()){
				file.createNewFile();
			}

			FileWriter fileWriter = new FileWriter(file);
			bufferedWriter = new BufferedWriter(fileWriter);
			for(int x =1; x<3588; x++){
				for(int y = 0; y<85;y++){
					bufferedWriter.write(arrayImpC004[x][y]);
					bufferedWriter.write(",");
				}
				bufferedWriter.newLine();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedWriter != null){
					bufferedWriter.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	}

	public static void calculateHD004(){
		double lowestVal = 9999.0;
		double lowest2Val = 9999.0;
		double sum = 0.0;
		double currentVal = 0.0;
		double lowestLocation = 999999.0;
		double lowest2Location = 99999.0;
		double currentLocation = 0.0;
		System.out.println("Calculating HD004");
		for(int y = 0; y<85;y++){
			for(int x =0; x<3588; x++){
				if(arrayHD004[x][y].charAt(0) == '?'){
					for(int z = 1; z<3588; z++){
						for(int a= 0; a<85; a++){
							
							//System.out.println(arrayHD004[z][a].charAt(0) + " " + arrayHD004[a][y].charAt(0));
							if(arrayHD004[z][a].charAt(0) == '0' && arrayHD004[a][y].charAt(0) == '0'){
								sum += Math.pow((Double.parseDouble(arrayHD004[z][a]) - Double.parseDouble(arrayHD004[a][y])),2);
							}
							else if(arrayHD004[z][a].charAt(0) == '?' || arrayHD004[a][y].charAt(0) == '?'){
								sum += 1;
							}
							else{}
						}
						
						currentVal = Math.sqrt(sum);
						sum = 0;
						currentLocation = z;
					}
					if(currentVal < lowestVal){
						lowest2Val = lowestVal;
						lowestVal = currentVal;
						lowest2Location = (lowestLocation);
						lowestLocation = (currentLocation);
					}
					if(arrayHD004[(int) lowestLocation][y].charAt(0) != '?'){
					arrayHD004[x][y] = arrayHD004[(int) lowestLocation][y];
				}
					else{
						arrayHD004[x][y] = arrayHD004[(int) lowest2Location][y];
					}
				}
			}	
			lowestVal = 9999.0;
			lowest2Val = 9999.0;
			sum = 0.0;
			currentVal = 0.0;
			lowestLocation = 999999.0;
			lowest2Location = 99999.0;
			currentLocation = 0.0;
		}
		
		BufferedWriter bufferedWriter = null;

		try {

			File file = new File("V00666733_a2_missing004_hd.csv");
			if(!file.exists()){
				file.createNewFile();
			}

			FileWriter fileWriter = new FileWriter(file);
			bufferedWriter = new BufferedWriter(fileWriter);
			for(int x =1; x<3588; x++){
				for(int y = 0; y<85;y++){
					bufferedWriter.write(arrayHD004[x][y]);
					bufferedWriter.write(",");
				}
				bufferedWriter.newLine();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedWriter != null){
					bufferedWriter.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		System.out.println("Finished Calculating HD004");
	}
//	public static void calculateHD004Conditional(){
//		double lowestVal = 9999.0;
//		double lowest2Val = 9999.0;
//		double sum = 0.0;
//		double currentVal = 0.0;
//		double lowestLocation = 999999.0;
//		double lowest2Location = 99999.0;
//		double currentLocation = 0.0;
//		System.out.println("Calculating HD004 Conditional");
//		for(int y = 0; y<85;y++){
//			for(int x =0; x<3588; x++){
//				if(arrayHDC004[x][y].charAt(0) == '?' && arrayHDC004[x][84].charAt(0) == 'F'){
//					for(int z = 1; z<3588; z++){
//						for(int a= 0; a<85; a++){
//								if(arrayHDC004[z][a].charAt(0) == '0' && arrayHDC004[a][y].charAt(0) == '0'){
//								sum += Math.pow((Double.parseDouble(arrayHDC004[z][a]) - Double.parseDouble(arrayHDC004[a][y])),2);
//							}
//							else if(arrayHDC004[z][a].charAt(0) == '?' || arrayHDC004[a][y].charAt(0) == '?'){
//								sum += 1;
//							}
//							else{}
//						}
//						
//						currentVal = Math.sqrt(sum);
//						sum = 0;
//						currentLocation = z;
//					}
//					if(currentVal < lowestVal){
//						lowest2Val = lowestVal;
//						lowestVal = currentVal;
//						lowest2Location = (lowestLocation);
//						lowestLocation = (currentLocation);
//					}
//					if(arrayHDC004[(int) lowestLocation][y].charAt(0) != '?'){
//						arrayHDC004[x][y] = arrayHDC004[(int) lowestLocation][y];
//				}
//					else{
//						arrayHDC004[x][y] = arrayHDC004[(int) lowest2Location][y];
//					}
//				}
//				else{
//					for(int z = 1; z<3588; z++){
//						for(int a= 0; a<85; a++){
//								if(arrayHDC004[z][a].charAt(0) == '0' && arrayHDC004[a][y].charAt(0) == '0'){
//								sum += Math.pow((Double.parseDouble(arrayHDC004[z][a]) - Double.parseDouble(arrayHDC004[a][y])),2);
//							}
//							else if(arrayHDC004[z][a].charAt(0) == '?' || arrayHDC004[a][y].charAt(0) == '?'){
//								sum += 1;
//							}
//							else{}
//						}
//						
//						currentVal = Math.sqrt(sum);
//						sum = 0;
//						currentLocation = z;
//					}
//					if(currentVal < lowestVal){
//						lowest2Val = lowestVal;
//						lowestVal = currentVal;
//						lowest2Location = (lowestLocation);
//						lowestLocation = (currentLocation);
//					}
//					if(arrayHDC004[(int) lowestLocation][y].charAt(0) != '?'){
//						arrayHDC004[x][y] = arrayHDC004[(int) lowestLocation][y];
//				}
//					else{
//						arrayHDC004[x][y] = arrayHDC004[(int) lowest2Location][y];
//					}
//				}
//			}	
//			lowestVal = 9999.0;
//			lowest2Val = 9999.0;
//			sum = 0.0;
//			currentVal = 0.0;
//			lowestLocation = 999999.0;
//			lowest2Location = 99999.0;
//			currentLocation = 0.0;
//		}
//		System.out.println("reached");
//		BufferedWriter bufferedWriter = null;
//
//		try {
//			System.out.println("writing");
//			File file = new File("V00666733_a2_missing004_hd_conditional.csv");
//			if(!file.exists()){
//				file.createNewFile();
//			}
//
//			FileWriter fileWriter = new FileWriter(file);
//			bufferedWriter = new BufferedWriter(fileWriter);
//			for(int x =1; x<3588; x++){
//				for(int y = 0; y<85;y++){
//					bufferedWriter.write(arrayHDC004[x][y]);
//					bufferedWriter.write(",");
//				}
//				bufferedWriter.newLine();
//			}
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (bufferedWriter != null){
//					bufferedWriter.close();
//				}
//			} catch (IOException ex) {
//				ex.printStackTrace();
//			}
//		}
//		System.out.println("Finished Calculating HD004 Conditional");
//	}
//	public static void calculateHD20(){
//		double lowestVal = 9999.0;
//		double lowest2Val = 9999.0;
//		double sum = 0.0;
//		double currentVal = 0.0;
//		double lowestLocation = 999999.0;
//		double lowest2Location = 99999.0;
//		double currentLocation = 0.0;
//		int count= 0;
//		System.out.println("Calculating HD20");
//		for(int y = 0; y<85;y++){
//			for(int x =0; x<3588; x++){
//				if(arrayHD20[x][y].charAt(0) == '?'){
//					for(int z = 1; z<3588; z++){
//						for(int a= 0; a<85; a++){
//
//							//System.out.println(arrayHD004[z][a].charAt(0) + " " + arrayHD004[a][y].charAt(0));
//							if(arrayHD20[z][a].charAt(0) == '0' && arrayHD20[a][y].charAt(0) == '0'){
//								sum += Math.pow((Double.parseDouble(arrayHD20[z][a]) - Double.parseDouble(arrayHD20[a][y])),2);
//							}
//							else if(arrayHD20[z][a].charAt(0) == '?' || arrayHD20[a][y].charAt(0) == '?'){
//								sum += 1;
//							}
//							else{}
//						}
//
//						currentVal = Math.sqrt(sum);
//						sum = 0;
//						currentLocation = z;
//					}
//					if(currentVal < lowestVal){
//						lowest2Val = lowestVal;
//						lowestVal = currentVal;
//						lowest2Location = (lowestLocation);
//						lowestLocation = (currentLocation);
//					}
//					if(arrayHD20[(int) lowestLocation][y].charAt(0) != '?'){
//						arrayHD20[x][y] = arrayHD20[(int) lowestLocation][y];
//
//					}
//					else if(lowestLocation +1 < 3588){
//						arrayHD20[x][y] = arrayHD20[(int) lowestLocation+1][y];
//					}
//					else if(lowestLocation - 1 >0){
//						arrayHD20[x][y] = arrayHD20[(int) lowestLocation-1][y];
//					}
//					else{arrayHD20[x][y] = "0";}
//				}
//				
//			}	
//			lowestVal = 9999.0;
//			lowest2Val = 9999.0;
//			sum = 0.0;
//			currentVal = 0.0;
//			lowestLocation = 999999.0;
//			lowest2Location = 99999.0;
//			currentLocation = 0.0;
//		}
//		
//		BufferedWriter bufferedWriter = null;
//
//		try {
//
//			File file = new File("V00666733_a2_missing20_hd.csv");
//			if(!file.exists()){
//				file.createNewFile();
//			}
//
//			FileWriter fileWriter = new FileWriter(file);
//			bufferedWriter = new BufferedWriter(fileWriter);
//			for(int x =1; x<3588; x++){
//				for(int y = 0; y<85;y++){
//					bufferedWriter.write(arrayHD20[x][y]);
//					bufferedWriter.write(",");
//				}
//				bufferedWriter.newLine();
//			}
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (bufferedWriter != null){
//					bufferedWriter.close();
//				}
//			} catch (IOException ex) {
//				ex.printStackTrace();
//			}
//		}
//		System.out.println("Finished Calculating HD20");
//	}

//	public static void calculateHD20(){
//	double lowestVal = 9999.0;
//	double lowest2Val = 9999.0;
//	double sum = 0.0;
//	double currentVal = 0.0;
//	double lowestLocation = 999999.0;
//	double lowest2Location = 99999.0;
//	double currentLocation = 0.0;
//	int count= 0;
//	System.out.println("Calculating HD20Conditional");
//	for(int y = 0; y<85;y++){
//		for(int x =0; x<3588; x++){
//			if(arrayHD20C[x][y].charAt(0) == '?'){
//				for(int z = 1; z<3588; z++){
//					for(int a= 0; a<85; a++){
//
//						//System.out.println(arrayHD20C[z][a].charAt(0) + " " + arrayHD20C[a][y].charAt(0));
//						if(arrayHD20C[z][a].charAt(0) == '0' && arrayHD20C[a][y].charAt(0) == '0'){
//							sum += Math.pow((Double.parseDouble(arrayHD20C[z][a]) - Double.parseDouble(arrayHD20C[a][y])),2);
//						}
//						else if(arrayHD20C[z][a].charAt(0) == '?' || arrayHD20C[a][y].charAt(0) == '?'){
//							sum += 1;
//						}
//						else{}
//					}
//
//					currentVal = Math.sqrt(sum);
//					sum = 0;
//					currentLocation = z;
//				}
//				if(currentVal < lowestVal){
//					lowest2Val = lowestVal;
//					lowestVal = currentVal;
//					lowest2Location = (lowestLocation);
//					lowestLocation = (currentLocation);
//				}
//				if(arrayHD20C[(int) lowestLocation][y].charAt(0) != '?'){
//					arrayHD20C[x][y] = arrayHD20[(int) lowestLocation][y];
//
//				}
//				else if(lowestLocation +1 < 3588){
//					arrayHD20C[x][y] = arrayHD20C[(int) lowestLocation+1][y];
//				}
//				else if(lowestLocation - 1 >0){
//					arrayHD20C[x][y] = arrayHD20C[(int) lowestLocation-1][y];
//				}
//				else{arrayHD20C[x][y] = "0";}
//			}
//			
//		}	
//		lowestVal = 9999.0;
//		lowest2Val = 9999.0;
//		sum = 0.0;
//		currentVal = 0.0;
//		lowestLocation = 999999.0;
//		lowest2Location = 99999.0;
//		currentLocation = 0.0;
//	}
//	
//	BufferedWriter bufferedWriter = null;
//
//	try {
//
//		File file = new File("V00666733_a2_missing20_hd_conditional.csv");
//		if(!file.exists()){
//			file.createNewFile();
//		}
//
//		FileWriter fileWriter = new FileWriter(file);
//		bufferedWriter = new BufferedWriter(fileWriter);
//		for(int x =1; x<3588; x++){
//			for(int y = 0; y<85;y++){
//				bufferedWriter.write(arrayHD20C[x][y]);
//				bufferedWriter.write(",");
//			}
//			bufferedWriter.newLine();
//		}
//
//	} catch (IOException e) {
//		e.printStackTrace();
//	} finally {
//		try {
//			if (bufferedWriter != null){
//				bufferedWriter.close();
//			}
//		} catch (IOException ex) {
//			ex.printStackTrace();
//		}
//	}
//	System.out.println("Finished Calculating HD20 Conditional");
//}
	public static void calculateMAE(){
		initComplete();
		
	}
	public static void main(String[] args) throws IOException {
		initalize004();
		initalize20();
		calculateImputedMean004();
		calculateImputedMeanConditional004();
		calculateImputedMean20();
		calculateImputedMeanConditional20();
		calculateHD004();
		calculateMAE();
//		calculateHD004Conditional();
//		calculateHD20();
//		calculateHD20Conditional();
	}

}
