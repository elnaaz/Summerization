import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Evaluate {
	
	public static void main(String[] args) throws IOException{

		ArrayList<String> summary_generated=readFile("C:\\Users\\Elnaz\\workspace\\Summerization\\results\\sum3_score_query2.txt");
		ArrayList<String> summary_ideal=readFile("C:\\Users\\Elnaz\\workspace\\Summerization\\results\\ideal3.txt");
	
		int C= find_common_sentences(summary_generated, summary_ideal);
		double Recall=(double)C/(double)summary_ideal.size();
		double Precision=(double)C/(double)summary_generated.size();

		System.out.println("Recall: "+Recall);
		System.out.println("Precision: "+Precision);
		
	}
	
	
	
	private static int find_common_sentences(ArrayList<String> sum1, ArrayList<String> sum2) {
		System.out.println(sum1.toString());
		System.out.println("* * *");
		System.out.println(sum2.toString());
		System.out.println(sum1.size());
		System.out.println("* * *");
		System.out.println(sum2.size());
		
		int count=0;
		for(int i=0;i<sum1.size();i++){
			for(int j=0;j<sum2.size();j++){
				if(sum1.get(i).trim().equals(sum2.get(j).trim())){
					count++;
					break;
				}
			}			
		}
		System.out.println("# sentences in common: "+count);
		if(count > sum1.size() && count > sum2.size()){
			System.out.print("This is an error");
			System.exit(1);
		}
		return count;
	}



	public static ArrayList<String> readFile(String rulefile) throws IOException{
    	FileInputStream fstream = new FileInputStream(rulefile);
    	DataInputStream in = new DataInputStream(fstream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        ArrayList<String> linelist=new ArrayList<String>();
        String strLine;	    
        while ((strLine = br.readLine()) != null)   {
        	linelist.add(strLine.toLowerCase());	
//        	System.out.println(strLine);
        }
  //      System.out.println();
        return linelist;
    }

}
