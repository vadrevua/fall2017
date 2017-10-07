/*
 * Program to calculate true positive and negative and false positive and negative for the height/weight data set
 */

import java.io.*;

public class ErrorCalculator {
  
  public static void main(String[] args){
    
    System.out.println("*** SCENARIO A ***");
    height();
    System.out.println("*** SCENARIO B ***");
    heightAndWeight();
    
  }
  
  public static void heightAndWeight() {
    try{
    BufferedReader br = new BufferedReader(new FileReader("C:/Users/Barack/Documents/GitHub/409Assignment1/data.txt"));
    br.readLine();
    double tp = 0;
    double tn = 0;
    double fp = 0;
    double fn = 0;
    String line;
    while((line = br.readLine()) != null) {
      String[] row = line.split(",");
      if(Double.parseDouble(row[1]) >= (Double.parseDouble(row[0]) * 1.6666666666667) + 31.5 && Integer.parseInt(row[2]) == 0) tp++;
      if(Double.parseDouble(row[1]) < (Double.parseDouble(row[0]) * 1.6666666666667) + 31.5 && Integer.parseInt(row[2]) == 1) tn++;
      if(Double.parseDouble(row[1]) >= (Double.parseDouble(row[0]) * 1.6666666666667) + 31.5 && Integer.parseInt(row[2]) == 1) fp++;
      if(Double.parseDouble(row[1]) < (Double.parseDouble(row[0]) * 1.6666666666667) + 31.5 && Integer.parseInt(row[2]) == 0) fn++;
    }
    
    System.out.println("True positives = " + tp);
    System.out.println("True negatives = " + tn);
    System.out.println("False positives = " + fp);
    System.out.println("False negatives = " + fn);
    System.out.println();
    
    double acc = (tp + tn) / (tp + tn + fp + fn);
    double tpr = tp / (tp + fn);
    double tnr = tn / (fp + tn);
    double fpr = fp / (fp + tn);
    double fnr = fn / (tp + fn);
    
    System.out.println("Error = " + (1-acc));
    System.out.println("Accuracy = " + acc);
    System.out.println("True positive rate = " + tpr);
    System.out.println("True negative rate = " + tnr);
    System.out.println("False positive rate = " + fpr);
    System.out.println("False negative rate = " + fnr);
    System.out.println();
    
    br.close();
    }
    catch(IOException e){
      e.printStackTrace();
    }
    
  }
  
  public static void height() {
    try{
    BufferedReader br = new BufferedReader(new FileReader("C:/Users/Barack/Documents/GitHub/409Assignment1/data.txt"));
    br.readLine();
    double tp = 0;
    double tn = 0;
    double fp = 0;
    double fn = 0;
    String line;
    while((line = br.readLine()) != null) {
      String[] row = line.split(",");
      if(Double.parseDouble(row[0]) >= 66.25 && Integer.parseInt(row[2]) == 0) tp++;
      if(Double.parseDouble(row[0]) < 66.25 && Integer.parseInt(row[2]) == 1) tn++;
      if(Double.parseDouble(row[0]) >= 66.25 && Integer.parseInt(row[2]) == 1) fp++;
      if(Double.parseDouble(row[0]) < 66.25 && Integer.parseInt(row[2]) == 0) fn++;
    }
    
    System.out.println("True positives = " + tp);
    System.out.println("True negatives = " + tn);
    System.out.println("False positives = " + fp);
    System.out.println("False negatives = " + fn);
    System.out.println();
    
    double acc = (tp + tn) / (tp + tn + fp + fn);
    double tpr = tp / (tp + fn);
    double tnr = tn / (fp + tn);
    double fpr = fp / (fp + tn);
    double fnr = fn / (tp + fn);
    
    System.out.println("Error = " + (1-acc));
    System.out.println("Accuracy = " + acc);
    System.out.println("True positive rate = " + tpr);
    System.out.println("True negative rate = " + tnr);
    System.out.println("False positive rate = " + fpr);
    System.out.println("False negative rate = " + fnr);
    System.out.println();
    
    br.close();
    }
    catch(IOException e){
      e.printStackTrace();
    }
  }
  
}