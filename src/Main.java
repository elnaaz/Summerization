import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import edu.smu.tspell.wordnet.Synset;
import edu.smu.tspell.wordnet.WordNetDatabase;

public class Main {
	
	static ArrayList<String> query=new ArrayList<String>();

	static ArrayList<String> inputlines_1;
	static ArrayList<Double> headerlines_1;
	static ArrayList<Double> stigmas_1;
	static ArrayList<Double> cue_1;
	static ArrayList<Integer> length_1;
	static ArrayList<Double> position_1;
	static ArrayList<Double> final_score_1;
	
	static ArrayList<String> inputlines_2;
	static ArrayList<Double> headerlines_2;
	static ArrayList<String> inputlines_3;
	static ArrayList<Double> headerlines_3;
	static ArrayList<String> inputlines_4;
	static ArrayList<Double> headerlines_4;
	static ArrayList<String> inputlines_5;
	static ArrayList<Double> headerlines_5;
	static ArrayList<String> inputlines_6;
	static ArrayList<Double> headerlines_6;
	static ArrayList<String> inputlines_7;
	static ArrayList<Double> headerlines_7;
	static ArrayList<String> inputlines_8;
	static ArrayList<Double> headerlines_8;
	static ArrayList<String> inputlines_9;
	static ArrayList<Double> headerlines_9;
	static ArrayList<String> inputlines_10;
	static ArrayList<Double> headerlines_10;

	public static void main(String[] args) throws IOException{

		System.out.println("################"+"Main Func"+"################");
		String inputfile= args[0];
		
		System.out.println(inputfile);
		
		inputlines_1=readFile(inputfile);
		headerlines_1=readFileHeader(inputfile);
		stigmas_1=readStigma(inputfile);
		cue_1=readCue(inputfile);
		position_1=readPosition(inputfile, headerlines_1);	
		
		inputfile=inputfile.replace('1', '2');
		System.out.println(inputfile);
		
		inputlines_2=readFile(inputfile);
		headerlines_2=readFileHeader(inputfile);
		inputfile=inputfile.replace("2", "3");
		System.out.println(inputfile);
		
		inputlines_3=readFile(inputfile);
		headerlines_3=readFileHeader(inputfile);
		inputfile=inputfile.replace("3", "4");
		System.out.println(inputfile);
		
		inputlines_4=readFile(inputfile);
		headerlines_4=readFileHeader(inputfile);
		inputfile=inputfile.replace("4", "5");
		System.out.println(inputfile);
		
		inputlines_5=readFile(inputfile);
		headerlines_5=readFileHeader(inputfile);
		inputfile=inputfile.replace("5", "6");
		System.out.println(inputfile);
		
		inputlines_6=readFile(inputfile);
		headerlines_6=readFileHeader(inputfile);
		inputfile=inputfile.replace("6", "7");
		System.out.println(inputfile);

		inputlines_7=readFile(inputfile);
		headerlines_7=readFileHeader(inputfile);
		inputfile=inputfile.replace("7", "8");
		System.out.println(inputfile);
		
		inputlines_8=readFile(inputfile);
		headerlines_8=readFileHeader(inputfile);
		inputfile=inputfile.replace("8", "9");
		System.out.println(inputfile);
		
		inputlines_9=readFile(inputfile);
		headerlines_9=readFileHeader(inputfile);
		inputfile=inputfile.replace("9", "10");
		System.out.println(inputfile);
		
		inputlines_10=readFile(inputfile);
		headerlines_10=readFileHeader(inputfile);
		
		
		System.out.print("Query words: ");
		for (int i = 1; i < args.length; i++){
	        System.out.print(args[i]+"\t");
	        query.add(args[i].toLowerCase());
		}
		System.out.println(inputfile);
		ArrayList<Integer> outputlines=summerize(inputlines_1, inputfile.substring(0, inputfile.lastIndexOf('.')));		
	}

	private static ArrayList<Integer> summerize(ArrayList<String> inputlines, String inputfile) throws IOException {

		System.out.println("################"+"summerize Func"+"################");
    	String outputfile=inputfile+"_summary_";
    	
    	ArrayList<String> score_2= score_module_score(inputlines);  
    	
    	writeSummarytoFile(score_2, outputfile+"_score.txt");
    	
    	ArrayList<String> score_1= score_module_rank(inputlines); 
    	
       	writeSummarytoFile(score_1, outputfile+"_rank.txt");
        
    	return null;
	}
	
	private static ArrayList<Integer> coherenceCheck(ArrayList<Integer> final_rank) {

		System.out.println("################"+"coherenceCheck Func"+"################");
    	
        
    	return null;
	}
	private static void writeSummarytoFile(ArrayList<String> score_2,String filename) throws IOException {
		System.out.println("################"+"writeSummarytoFile Func"+"################");
		FileWriter fstream = new FileWriter(filename);
        BufferedWriter out = new BufferedWriter(fstream);
        
        for(int m=0;m<score_2.size();m++){  	
        	out.write(score_2.get(m).trim());
        	out.write("\t");
        	out.write(final_score_1.get(m).toString());
        	out.write("\t");
        	out.write("\n");
        }
        out.flush();
        out.close();
		
	}

	private static ArrayList<Double> score_module_Lexical_Chain(ArrayList<String> inputlines, ArrayList<String> sorted_words, ArrayList<Integer> length_12) {
		
		System.out.println("################"+"score_module_Lexical_Chain Func"+"################");
    	
		ArrayList<Double> score_2= new ArrayList<Double>();
		
		System.setProperty("wordnet.database.dir", "C:\\Program Files (x86)\\WordNet\\2.1\\dict\\");
		WordNetDatabase database = WordNetDatabase.getFileInstance(); 
				
		System.out.println("Number of sentences: "+inputlines.size());
		System.out.println("sorted_words: "+sorted_words.size());
		
		ArrayList<ArrayList<String>> sentence_words_synset= new ArrayList<ArrayList<String>>();
		
		for(int i=0;i<sorted_words.size();i++){	
			ArrayList<String> current_word_set= new ArrayList<String>();
		    	String wordForm = sorted_words.get(i);
		    	current_word_set.add(wordForm);
		    	Synset[] synsets = database.getSynsets(wordForm);
		    	//System.out.println("* current_word_setk : "+current_word_set.size());
		    	//System.out.println(current_word_set.toString());
		    	if (synsets.length > 0){
		    		 //System.out.println("The following synsets contain '" +	wordForm + "' or a possible base form of that text:");
		    		 for (int k = 0; k < synsets.length; k++)
		 			{
		    			 String[] wordForms = synsets[k].getWordForms();
		    				for (int j = 0; j < wordForms.length; j++)
		    				{
		    					//System.out.print( wordForms[j] + "\t");
		    					if( ! current_word_set.contains(wordForms[j]) ){
		   		    			 	current_word_set.add(wordForms[j]);
		   		    		 	}
		    				}
		    				
		    				//System.out.println();
		    			}
		    		 
		    		
		    	 }
		    	sentence_words_synset.add(current_word_set);
		    	//System.out.println(current_word_set.toString());
		    
		}
		System.out.println("* * * Just to check : "+sentence_words_synset.size());
		
		int[][] connectivity=new int[inputlines.size()][inputlines.size()];
		for(int i=0;i<inputlines.size();i++){
			for(int j=0;j<inputlines.size();j++){
				connectivity[i][j]=0;
			}
		}
		for(int i=0;i<sentence_words_synset.size();i++){	
				for(int j=0;j<inputlines.size();j++){
					for(int h=j;h<inputlines.size();h++){
						if(j != h){
							boolean found_j=false;
							boolean found_h=false;
							
							for(int k=0; k<sentence_words_synset.get(i).size();k++){
								String word=sentence_words_synset.get(i).get(k);
								if(inputlines.get(j).contains(word)){
									found_j=true;
								}
								if(inputlines.get(h).contains(word)){
									found_h=true;
								}
								if(found_h && found_j){
									//System.out.println(sentence_words_synset.get(i));
									//System.out.println(word +"\tin both "+j +"\t"+h +"\t");
									connectivity[h][j]=connectivity[h][j]+1;
									connectivity[j][h]=connectivity[j][h]+1;
									break;
								}
							}
						}
					}
				}
		}
		
		
		String output="";
		for(int j=0;j<inputlines.size();j++){
			double temp=0;
			for(int h=0;h<inputlines.size();h++){
				temp=temp+connectivity[j][h];
				output=output+"\t"+connectivity[j][h];
			}
			score_2.add( temp);
			output=output+"\t="+temp+"\t"+temp/length_12.get(j)+"\n\n";
		}
		System.out.println(output);
		
		return score_2;
	}
	private static ArrayList<String> score_module_score(ArrayList<String> inputlines) {
		
		System.out.println("################"+"Rank Based Integration Func"+"################");
    	
		TreeMap<String, Integer> word_count_1=term_freq_count(inputlines_1);
		TreeMap<String, Integer> word_count_2=term_freq_count(inputlines_2);
		TreeMap<String, Integer> word_count_3=term_freq_count(inputlines_3);
		TreeMap<String, Integer> word_count_4=term_freq_count(inputlines_4);
		TreeMap<String, Integer> word_count_5=term_freq_count(inputlines_5);
		TreeMap<String, Integer> word_count_6=term_freq_count(inputlines_6);
		TreeMap<String, Integer> word_count_7=term_freq_count(inputlines_7);
		TreeMap<String, Integer> word_count_8=term_freq_count(inputlines_8);
		TreeMap<String, Integer> word_count_9=term_freq_count(inputlines_9);
		TreeMap<String, Integer> word_count_10=term_freq_count(inputlines_10);
	
		ArrayList<TreeMap<String, Integer>> all=new ArrayList<TreeMap<String, Integer>>();
		all.add(word_count_1);
		all.add(word_count_2);
		all.add(word_count_3);
		all.add(word_count_4);
		all.add(word_count_5);
		all.add(word_count_6);
		all.add(word_count_7);
		all.add(word_count_8);
		all.add(word_count_9);
		all.add(word_count_10);
		
		int doc_size_1=doc_size(inputlines_1);
		length_1=sentence_length(inputlines_1);
		
		TreeMap<String, Double> word_tdf_1= term_tdf(word_count_1,doc_size_1);	
		TreeMap<String, Double> word_idf_1= term_idf(word_count_1, all);	
		TreeMap<String, Double> word_tf_idf_1= term_tdf_idf(word_tdf_1, word_idf_1);

		System.out.println("tf: "+word_tdf_1.toString());
		System.out.println("if: "+word_idf_1.toString());
		System.out.println("tf-if: "+word_tf_idf_1.toString());
		
		///////// tf-idf
		ArrayList<String> sorted_words_tf_idf=sort_words(word_tf_idf_1); 
		ArrayList<Double> sorted_scores_tf_idf=sort_scores(word_tf_idf_1); 

		ArrayList<Double> query_vector_tf_idf= make_query_vector( sorted_words_tf_idf, sorted_scores_tf_idf, inputlines);
		ArrayList<Double> average_vector_tf_idf= make_average_vector( sorted_words_tf_idf, sorted_scores_tf_idf, inputlines);
		ArrayList<ArrayList<Double>> sentence_vectors_tf_idf= make_sentence_vectors( sorted_words_tf_idf, sorted_scores_tf_idf, inputlines);

		ArrayList<Double> sentence_scores_tf_idf=calculate_sentence_score(sentence_vectors_tf_idf);
		ArrayList<Double> sentence_scores_tf_idf_normal=MathFuncs.normalizedArray(sentence_scores_tf_idf);
		
		ArrayList<Double> sentence_vectors_similarity_query_scores_tf_idf=calculate_similarity_score(sentence_vectors_tf_idf, query_vector_tf_idf);
		ArrayList<Double> sentence_vectors_similarity_query_scores_tf_idf_normal=MathFuncs.normalizedArray(sentence_vectors_similarity_query_scores_tf_idf);
		
		ArrayList<Double> sentence_vectors_similarity_average_scores_tf_idf=calculate_similarity_score(sentence_vectors_tf_idf, average_vector_tf_idf);
		ArrayList<Double> sentence_vectors_similarity_average_scores_tf_idf_normal=MathFuncs.normalizedArray(sentence_vectors_similarity_average_scores_tf_idf);
		
		System.out.println("\n1: sentence tf idf score:"+sentence_scores_tf_idf.toString());
		System.out.println("2: query sim:"+sentence_vectors_similarity_query_scores_tf_idf.toString());
		System.out.println("3: average sim:"+sentence_vectors_similarity_average_scores_tf_idf.toString());

		///////// Lexicon Chain
		
		ArrayList<Double> lexical_chain= score_module_Lexical_Chain(inputlines, sorted_words_tf_idf,length_1 );  
		ArrayList<Double> lexical_chain_normal=MathFuncs.normalizedArray(lexical_chain);
		
		ArrayList<Double> headerlines_1_normal=MathFuncs.normalizedArray(headerlines_1);
		ArrayList<Double> stigmas_1_normal=MathFuncs.normalizedArray(stigmas_1);
		ArrayList<Double> cue_1_normal=MathFuncs.normalizedArray(cue_1);
		ArrayList<Double> position_1_normal=MathFuncs.normalizedArray(position_1);
				
		////////
		ArrayList<ArrayList<Double>> all_scores= new ArrayList<ArrayList<Double>>();
		
		all_scores.add(sentence_scores_tf_idf_normal);
		System.out.println("sentence_scores_tf_idf_normal scores:\t\t"+sentence_scores_tf_idf_normal.toString());
		
		all_scores.add(sentence_vectors_similarity_query_scores_tf_idf_normal);
		System.out.println("sentence_vectors_similarity_query_scores_tf_idf_normal scores:\t\t"+sentence_vectors_similarity_query_scores_tf_idf_normal.toString());
		
		all_scores.add(sentence_vectors_similarity_average_scores_tf_idf_normal);
		System.out.println("sentence_vectors_similarity_average_scores_tf_idf_normal scores:\t\t"+sentence_vectors_similarity_average_scores_tf_idf_normal.toString());
		
		all_scores.add(lexical_chain_normal);
		System.out.println("lexical_chain_normal scores:\t\t"+lexical_chain_normal.toString());
		
		all_scores.add(headerlines_1_normal);
		System.out.println("headerlines_1_normal scores:\t\t"+headerlines_1_normal.toString());
		
		all_scores.add(stigmas_1_normal);
		System.out.println("stigmas_1_normal scores:\t\t"+stigmas_1_normal.toString());
		
		all_scores.add(cue_1_normal);
		System.out.println("cue_1_normal scores:\t\t"+cue_1_normal.toString());
		
		all_scores.add(position_1_normal);
		System.out.println("position_1_normal scores:\t\t"+position_1_normal.toString());
		
		ArrayList<Double> all_score_weights= new ArrayList<Double>();
		all_score_weights.add(2.0);
		all_score_weights.add(3.0);
		all_score_weights.add(2.0);
		all_score_weights.add(2.0);
		all_score_weights.add(3.0);
		all_score_weights.add(3.0);
		all_score_weights.add(2.0);
		all_score_weights.add(4.0);
	
		if(all_scores.size()!=all_score_weights.size())
			System.exit(1);
		ArrayList<Integer> final_rank= score_vote(all_scores,all_score_weights);
		coherenceCheck(final_rank);
		
		int number_of_lines_in_summary= (int)((double)inputlines.size()*0.3);
		ArrayList<String> summary= convert_rank_string(inputlines, number_of_lines_in_summary, final_rank);

		return summary;
	}
	

	private static ArrayList<Integer> score_vote(ArrayList<ArrayList<Double>> all_scores, ArrayList<Double> all_score_weights) {
		
		System.out.println("################"+"Scores Integration Module Func"+"################");

		System.out.println(all_score_weights);
		ArrayList<Double> new_scores= new ArrayList<Double>();
		ArrayList<Double> c= all_scores.get(0);
		
		for(int h=0;h<c.size();h++)
			new_scores.add(0.0);
		
		for(int i=0;i<all_scores.size();i++){
			ArrayList<Double> current= all_scores.get(i);
			for(int j=0;j<current.size();j++){
				double new_val=current.get(j);
				double so_far=new_scores.get(j);
				new_scores.set(j, so_far+new_val*all_score_weights.get(i));
			}
		}	
		
		final_score_1=new ArrayList<Double>();
		for(int h=0;h<c.size();h++)
			final_score_1.add(new_scores.get(h));
		
		
		System.out.println("*&*&***********\n final sentence scores based on scores : "+ new_scores.toString() );
		// Convert Score to Rank 
		ArrayList<Integer> ranks=rank_by_score(new_scores);
		System.out.println(" final rank of sentences:\t\t"+ranks.toString());
		
		return ranks;	
	}

	private static ArrayList<String> convert_rank_string( ArrayList<String> inputlines, int number_of_lines_in_summary, ArrayList<Integer> final_rank) {

		ArrayList<String> summary=new ArrayList<String>();
		for(int a=0;a<number_of_lines_in_summary;a++){
			summary.add(inputlines.get(final_rank.get(a)));
		}
		System.out.println(summary.toString());
		return summary;
	}
	
	private static ArrayList<Integer> rank_by_score_increase(ArrayList<Integer> scores) {
		
		ArrayList<Integer> ranks=new ArrayList<Integer> ();
		for(int i=0;i<scores.size();i++)
			ranks.add(i);
		
		for(int i=0;i<scores.size();i++){
			for(int j=0;j<scores.size()-1;j++){
				if(scores.get(j)>scores.get(j+1)){
					int temp=scores.get(j);
					scores.set(j, scores.get(j+1));
					scores.set(j+1, temp);
					
					int tempr=ranks.get(j);
					ranks.set(j, ranks.get(j+1));
					ranks.set(j+1, tempr);
				}
			}	
		}
		return ranks;
	}

	private static ArrayList<Integer> rank_by_score(ArrayList<Double> scores) {
		ArrayList<Integer> ranks=new ArrayList<Integer> ();
		for(int i=0;i<scores.size();i++)
			ranks.add(i);
		
		for(int i=0;i<scores.size();i++){
			for(int j=0;j<scores.size()-1;j++){
				if(scores.get(j)<scores.get(j+1)){
					double temp=scores.get(j);
					scores.set(j, scores.get(j+1));
					scores.set(j+1, temp);
					
					int tempr=ranks.get(j);
					ranks.set(j, ranks.get(j+1));
					ranks.set(j+1, tempr);
				}
			}	
		}
		return ranks;
	}
	private static String pre_process(String in) {
		System.out.println("################"+"pre_process strings Func"+"################");

		String process=in.replace(".", " ");
		process=process.replace(",", " ");
		process=process.replace(";", " ");
		process=process.replace(":", " ");
		process=process.replace("\"", " ");
		process=process.replace("(", " ");
		process=process.replace(")", " ");
		process=process.replace("[", " ");
		process=process.replace("]", " ");
		process=process.replace("\'s", " ");
		process=process.replace("s\'", " ");
		process=process.toLowerCase();
		return process;
 	}
	private static TreeMap<String, Integer> term_freq_count(ArrayList<String> inputlines) {
		
		System.out.println("################"+"term_freq_count Func"+"################");

		int document_size=0;
		TreeMap<String, Integer> word_count=new TreeMap<String, Integer>();
		for(int i=0;i<inputlines.size();i++){
			String process=pre_process(inputlines.get(i));
			String[] result = process.split("\\s");
			document_size=document_size+result.length;
			//System.out.println(process+"\n");
		    for (int x=0; x<result.length; x++){
		    	 result[x]=result[x].trim();
		    	 if(result[x].endsWith("s")){
		    		 String single=(String)result[x].subSequence(0, result[x].lastIndexOf("s"));
		    		 if(word_count.containsKey(single)){
		    			 int current= word_count.get(single);
			        	 word_count.put(single, current+1);
			        	 //result[x]=single;
			        	 continue;
		    		 }
		    	 }
		    	 if(result[x].endsWith("es")){
		    		 String single=(String)result[x].subSequence(0, result[x].lastIndexOf("s")-1);
		    		 if(word_count.containsKey(single)){
		    			 int current= word_count.get(single);
			        	 word_count.put(single, current+1);
			        	 //result[x]=single;
			        	 continue;
		    		 }
		    	 }
		         if(word_count.containsKey(result[x])){
		        	 int current= word_count.get(result[x]);
		        	 word_count.put(result[x], current+1);
		         }else{
		        	 word_count.put(result[x], 1);
		         }
		    }
		}
		word_count.remove("");
		System.out.println("*******************");
		System.out.println(word_count.size());
		System.out.println(word_count.toString());
		return word_count;
	}	
	
	private static int doc_size(ArrayList<String> inputlines) {		
		
		System.out.println("################"+" doc_size Func"+"################");

		int document_size=0;
		TreeMap<String, Integer> word_count=new TreeMap<String, Integer>();
		for(int i=0;i<inputlines.size();i++){
			String process=pre_process(inputlines.get(i));
			String[] result = process.split("\\s");
			document_size=document_size+result.length;
		}
		return document_size;
	}	
	
	private static ArrayList<Integer> sentence_length(ArrayList<String> inputlines) {		
		System.out.println("################"+" sentence_length Func"+"################");

		ArrayList<Integer> test = new ArrayList<Integer>();
		int document_size=0;
		TreeMap<String, Integer> word_count=new TreeMap<String, Integer>();
		for(int i=0;i<inputlines.size();i++){
			String process=pre_process(inputlines.get(i));
			String[] result = process.split("\\s");
			test.add(result.length);
		}
		return test;
	}

	private static ArrayList<String> score_module_rank(ArrayList<String> inputlines) {		
		
		System.out.println("################"+" Score Integration Based  On Scores Func"+"################");

		TreeMap<String, Integer> word_count_1=term_freq_count(inputlines_1);
		TreeMap<String, Integer> word_count_2=term_freq_count(inputlines_2);
		TreeMap<String, Integer> word_count_3=term_freq_count(inputlines_3);
		TreeMap<String, Integer> word_count_4=term_freq_count(inputlines_4);
		TreeMap<String, Integer> word_count_5=term_freq_count(inputlines_5);
		TreeMap<String, Integer> word_count_6=term_freq_count(inputlines_6);
		TreeMap<String, Integer> word_count_7=term_freq_count(inputlines_7);
		TreeMap<String, Integer> word_count_8=term_freq_count(inputlines_8);
		TreeMap<String, Integer> word_count_9=term_freq_count(inputlines_9);
		TreeMap<String, Integer> word_count_10=term_freq_count(inputlines_10);
	
		ArrayList<TreeMap<String, Integer>> all=new ArrayList<TreeMap<String, Integer>>();
		all.add(word_count_1);
		all.add(word_count_2);
		all.add(word_count_3);
		all.add(word_count_4);
		all.add(word_count_5);
		all.add(word_count_6);
		all.add(word_count_7);
		all.add(word_count_8);
		all.add(word_count_9);
		all.add(word_count_10);
		
		int doc_size_1=doc_size(inputlines_1);
		length_1=sentence_length(inputlines_1);
		/*int doc_size_2=doc_size(inputlines_2);
		int doc_size_3=doc_size(inputlines_3);
		int doc_size_4=doc_size(inputlines_4);
		int doc_size_5=doc_size(inputlines_5);
		int doc_size_6=doc_size(inputlines_6);
		int doc_size_7=doc_size(inputlines_7);
		int doc_size_8=doc_size(inputlines_8);
		int doc_size_9=doc_size(inputlines_9);
		int doc_size_10=doc_size(inputlines_10);
*/
		TreeMap<String, Double> word_tdf_1= term_tdf(word_count_1,doc_size_1);
		/*TreeMap<String, Double> word_tdf_2= term_tdf(word_count_2,doc_size_2);
		TreeMap<String, Double> word_tdf_3= term_tdf(word_count_3,doc_size_3);
		TreeMap<String, Double> word_tdf_4= term_tdf(word_count_4,doc_size_4);
		TreeMap<String, Double> word_tdf_5= term_tdf(word_count_5,doc_size_5);
		TreeMap<String, Double> word_tdf_6= term_tdf(word_count_6,doc_size_6);
		TreeMap<String, Double> word_tdf_7= term_tdf(word_count_7,doc_size_7);
		TreeMap<String, Double> word_tdf_8= term_tdf(word_count_8,doc_size_8);
		TreeMap<String, Double> word_tdf_9= term_tdf(word_count_9,doc_size_9);
		TreeMap<String, Double> word_tdf_10= term_tdf(word_count_10,doc_size_10);
*/
		TreeMap<String, Double> word_idf_1= term_idf(word_count_1, all);
		/*TreeMap<String, Double> word_idf_2= term_idf(word_count_2, all);
		TreeMap<String, Double> word_idf_3= term_idf(word_count_3, all);
		TreeMap<String, Double> word_idf_4= term_idf(word_count_4, all);
		TreeMap<String, Double> word_idf_5= term_idf(word_count_5, all);
		TreeMap<String, Double> word_idf_6= term_idf(word_count_6, all);
		TreeMap<String, Double> word_idf_7= term_idf(word_count_7, all);
		TreeMap<String, Double> word_idf_8= term_idf(word_count_8, all);
		TreeMap<String, Double> word_idf_9= term_idf(word_count_9, all);
		TreeMap<String, Double> word_idf_10= term_idf(word_count_10, all);
*/
		TreeMap<String, Double> word_tf_idf_1= term_tdf_idf(word_tdf_1, word_idf_1);
		/*TreeMap<String, Double> word_tf_idf_2= term_tdf_idf(word_tdf_2, word_idf_2);
		TreeMap<String, Double> word_tf_idf_3= term_tdf_idf(word_tdf_3, word_idf_3);
		TreeMap<String, Double> word_tf_idf_4= term_tdf_idf(word_tdf_4, word_idf_4);
		TreeMap<String, Double> word_tf_idf_5= term_tdf_idf(word_tdf_5, word_idf_5);
		TreeMap<String, Double> word_tf_idf_6= term_tdf_idf(word_tdf_6, word_idf_6);
		TreeMap<String, Double> word_tf_idf_7= term_tdf_idf(word_tdf_7, word_idf_7);
		TreeMap<String, Double> word_tf_idf_8= term_tdf_idf(word_tdf_8, word_idf_8);
		TreeMap<String, Double> word_tf_idf_9= term_tdf_idf(word_tdf_9, word_idf_9);
		TreeMap<String, Double> word_tf_idf_10= term_tdf_idf(word_tdf_10, word_idf_10);
*/
		System.out.println("tf: "+word_tdf_1.toString());
		System.out.println("if: "+word_idf_1.toString());
		System.out.println("tf-if: "+word_tf_idf_1.toString());
	
		////////// tf
		ArrayList<String> sorted_words=sort_words(word_tdf_1); 
		ArrayList<Double> sorted_scores=sort_scores(word_tdf_1); 

		ArrayList<Double> query_vector= make_query_vector( sorted_words, sorted_scores, inputlines);
		ArrayList<Double> average_vector= make_average_vector( sorted_words, sorted_scores, inputlines);
		ArrayList<ArrayList<Double>> sentence_vectors= make_sentence_vectors( sorted_words, sorted_scores, inputlines);

		ArrayList<Double> sentence_scores=calculate_sentence_score(sentence_vectors);
		ArrayList<Integer> rank_1= rank_by_score(sentence_scores);
		ArrayList<Double> sentence_vectors_similarity_query_scores=calculate_similarity_score(sentence_vectors, query_vector);
		ArrayList<Integer> rank_2= rank_by_score(sentence_vectors_similarity_query_scores);
		ArrayList<Double> sentence_vectors_similarity_average_scores=calculate_similarity_score(sentence_vectors, average_vector);
		ArrayList<Integer> rank_3= rank_by_score(sentence_vectors_similarity_average_scores);

		System.out.println("\n1: sentence tf score:"+sentence_scores.toString());
		System.out.println("2: query sim:"+sentence_vectors_similarity_query_scores.toString());
		System.out.println("3: average sim:"+sentence_vectors_similarity_average_scores.toString());
		
		///////// tf-idf
		ArrayList<String> sorted_words_tf_idf=sort_words(word_tf_idf_1); 
		ArrayList<Double> sorted_scores_tf_idf=sort_scores(word_tf_idf_1); 

		ArrayList<Double> query_vector_tf_idf= make_query_vector( sorted_words_tf_idf, sorted_scores_tf_idf, inputlines);
		ArrayList<Double> average_vector_tf_idf= make_average_vector( sorted_words_tf_idf, sorted_scores_tf_idf, inputlines);
		ArrayList<ArrayList<Double>> sentence_vectors_tf_idf= make_sentence_vectors( sorted_words_tf_idf, sorted_scores_tf_idf, inputlines);

		ArrayList<Double> sentence_scores_tf_idf=calculate_sentence_score(sentence_vectors_tf_idf);
		ArrayList<Integer> rank_4= rank_by_score(sentence_scores_tf_idf);
		ArrayList<Double> sentence_vectors_similarity_query_scores_tf_idf=calculate_similarity_score(sentence_vectors_tf_idf, query_vector_tf_idf);
		ArrayList<Integer> rank_5= rank_by_score(sentence_vectors_similarity_query_scores_tf_idf);
		ArrayList<Double> sentence_vectors_similarity_average_scores_tf_idf=calculate_similarity_score(sentence_vectors_tf_idf, average_vector_tf_idf);
		ArrayList<Integer> rank_6= rank_by_score(sentence_vectors_similarity_average_scores_tf_idf);
	
		System.out.println("\n1: sentence tf idf score:"+sentence_scores_tf_idf.toString());
		System.out.println("2: query sim:"+sentence_vectors_similarity_query_scores_tf_idf.toString());
		System.out.println("3: average sim:"+sentence_vectors_similarity_average_scores_tf_idf.toString());

		///////// Lexicon Chain
		
		ArrayList<Double> lexical_chain= score_module_Lexical_Chain(inputlines, sorted_words_tf_idf,length_1 );  
		ArrayList<Integer> rank_7= rank_by_score(lexical_chain);
		
		ArrayList<Integer> rank_8= rank_by_score(headerlines_1);
		ArrayList<Integer> rank_9= rank_by_score(stigmas_1);
		ArrayList<Integer> rank_10= rank_by_score(cue_1);
		ArrayList<Integer> rank_11= rank_by_score(position_1);
		
		
		////////
		System.out.println("sentence tf:\t\t"+rank_1.toString());
		System.out.println("query sim tf:\t\t"+rank_2.toString());
		System.out.println("average sim tf:\t\t"+rank_3.toString());
		System.out.println("sentence tf idf:\t\t"+rank_4.toString());
		System.out.println("query sim tf idf:\t\t"+rank_5.toString());
		System.out.println("average sim tf idf:\t\t"+rank_6.toString());
		System.out.println("lexical chain:\t\t"+rank_7.toString());
		System.out.println("headline:\t\t"+rank_8.toString());
		System.out.println("stigma:\t\t"+rank_9.toString());
		System.out.println("cue:\t\t"+rank_10.toString());
		System.out.println("position:\t\t"+rank_11.toString());
		
		////////
		ArrayList<ArrayList<Integer>> all_ranks= new ArrayList<ArrayList<Integer>>();
		all_ranks.add(rank_1);
		all_ranks.add(rank_2);
		all_ranks.add(rank_3);
		all_ranks.add(rank_4);
		all_ranks.add(rank_5);
		all_ranks.add(rank_6);
		all_ranks.add(rank_7);
		all_ranks.add(rank_8);
		all_ranks.add(rank_9);
		all_ranks.add(rank_10);
		all_ranks.add(rank_11);
		ArrayList<Integer> all_rank_weights= new ArrayList<Integer>();
		all_rank_weights.add(2);
		all_rank_weights.add(2);
		all_rank_weights.add(2);
		all_rank_weights.add(2);
		all_rank_weights.add(6);
		all_rank_weights.add(2);
		all_rank_weights.add(2);
		all_rank_weights.add(2);
		all_rank_weights.add(3);
		all_rank_weights.add(2);
		all_rank_weights.add(6);
	
		ArrayList<Integer> final_rank= rank_vote(all_ranks,all_rank_weights);
		System.out.println("*** final_rank:\t\t"+final_rank.toString());
		coherenceCheck(final_rank);
		
		int number_of_lines_in_summary= (int)((double)inputlines.size()*0.3);
		ArrayList<String> summary= convert_rank_string(inputlines, number_of_lines_in_summary, final_rank);

		return summary;
	}

	private static ArrayList<Integer> rank_vote( ArrayList<ArrayList<Integer>> all_ranks,	ArrayList<Integer> all_rank_weights) {
		System.out.println("################"+" Rank Vote Integration Based  On Scores Func"+"################");
		System.out.println(all_rank_weights);
		ArrayList<Integer> new_scores= new ArrayList<Integer>();
		ArrayList<Integer> c= all_ranks.get(0);
		
		for(int h=0;h<c.size();h++)
			new_scores.add(0);
		
		for(int i=0;i<all_ranks.size();i++){
			ArrayList<Integer> current= all_ranks.get(i);
			for(int j=0;j<current.size();j++){
				int which_sentence=current.get(j);
				int sentence_score=new_scores.get(which_sentence);
				new_scores.set(which_sentence, sentence_score+j*all_rank_weights.get(i));
			}
		}
		
		System.out.println(" final sentence scores based on ranks : "+ new_scores.toString() );
		// Convert Score to Rank 
		ArrayList<Integer> ranks=rank_by_score_increase(new_scores);
		
		System.out.println(" final rank of sentences:\t\t"+ranks.toString());
		return ranks;		
	}

	private static ArrayList<Double> calculate_sentence_score( ArrayList<ArrayList<Double>> sentence_vectors) {

		System.out.println("################"+" calculate_sentence_score Func"+"################");

		ArrayList<Double> scores=	new ArrayList<Double>();
		for(int sentence_index=0;sentence_index<sentence_vectors.size();sentence_index++){
			ArrayList<Double> sentence_normal=sentence_vectors.get(sentence_index);
			double temp=0;
			for(int i=0;i<sentence_normal.size();i++){
				temp=temp+sentence_normal.get(i);				
			}
			scores.add(temp);
		}
		return scores;
	}
	static ArrayList<Double> make_query_vector(ArrayList<String> sorted_words, ArrayList<Double> sorted_scores, ArrayList<String> inputlines){
		System.out.println("################"+" make_query_vector Func"+"################");

		ArrayList<Double> query_vector=	new ArrayList<Double>();
				for(int term=0;term<sorted_words.size();term++){
			if(query.contains(sorted_words.get(term))){
				query_vector.add(sorted_scores.get(term));
			}else{
				query_vector.add(0.0);
			}
		}
		return query_vector;
	}

	static ArrayList<Double> make_average_vector(ArrayList<String> sorted_words, ArrayList<Double> sorted_scores, ArrayList<String> inputlines){

		System.out.println("################"+" make_average_vector Func"+"################");

		ArrayList<Double> average_vector=new ArrayList<Double>();
		for(int term=0;term<sorted_words.size();term++){
			average_vector.add(0.0);
		}
		ArrayList<ArrayList<Double>> sentence_vectors=new ArrayList<ArrayList<Double>>();
		for(int sentence_index=0;sentence_index<inputlines.size();sentence_index++){
			ArrayList<Double> sentence=	new ArrayList<Double>();		
			for(int term=0;term<sorted_words.size();term++){
				if( (inputlines.get(sentence_index)).contains(sorted_words.get(term))){
					sentence.add(sorted_scores.get(term));
					double current=average_vector.get(term)*(sentence_index);
					average_vector.set(term, ((current+sorted_scores.get(term))/(sentence_index+1)));
				}else{
					sentence.add(0.0);
				}
			}
			sentence_vectors.add(sentence);
		}	
		return average_vector;
	}

	static ArrayList<ArrayList<Double>> make_sentence_vectors(ArrayList<String> sorted_words, ArrayList<Double> sorted_scores, ArrayList<String> inputlines){
		
		System.out.println("################"+" make_sentence_vectors Func"+"################");

		ArrayList<Double> average_vector=new ArrayList<Double>();

		for(int term=0;term<sorted_words.size();term++){
			average_vector.add(0.0);
		}
		ArrayList<ArrayList<Double>> sentence_vectors=new ArrayList<ArrayList<Double>>();
		for(int sentence_index=0;sentence_index<inputlines.size();sentence_index++){
			ArrayList<Double> sentence=	new ArrayList<Double>();		
			for(int term=0;term<sorted_words.size();term++){
				if( (inputlines.get(sentence_index)).contains(sorted_words.get(term))){
					sentence.add(sorted_scores.get(term));
					double current=average_vector.get(term)*(sentence_index);
					average_vector.set(term, ((current+sorted_scores.get(term))/(sentence_index+1)));
				}else{
					sentence.add(0.0);
				}
			}
			sentence_vectors.add(sentence);
		}		
		return sentence_vectors;
	}

	private static double getVectorSize(ArrayList<Double> the_vector){
		double size_the_vec=0;
		for(int i=0;i<the_vector.size();i++){
			size_the_vec=size_the_vec+the_vector.get(i)*the_vector.get(i);
		}
		size_the_vec=Math.sqrt(size_the_vec);
		return size_the_vec;
	}
	
	private static ArrayList<Double> getNormalVec(ArrayList<Double> the_vector){
		System.out.println("################"+" getNormalVec Func"+"################");

		double size_the_vec=getVectorSize(the_vector);
		ArrayList<Double> normal=new ArrayList<Double>();
		for(int i=0;i<the_vector.size();i++){
			double temp=the_vector.get(i);
			normal.add(temp/size_the_vec);
		}
		return normal;
	}

	private static ArrayList<Double> calculate_similarity_score( ArrayList<ArrayList<Double>> sentence_vectors, ArrayList<Double> the_vector) {
		
		System.out.println("################"+" calculate_similarity_score Func"+"################");

		
		ArrayList<Double> scores=new ArrayList<Double>();
		ArrayList<Double> the_vector_normal=getNormalVec(the_vector);

		for(int sentence_index=0;sentence_index<sentence_vectors.size();sentence_index++){
			ArrayList<Double> sentence_normal=getNormalVec(sentence_vectors.get(sentence_index));
			double temp=0;
			for(int i=0;i<the_vector_normal.size();i++){
				temp=temp+the_vector_normal.get(i)*sentence_normal.get(i);				
			}
			scores.add(temp);
		}
		return scores;
	}
	
	private static ArrayList<Double> sort_scores(TreeMap<String, Double> map) {
		
		System.out.println("################"+" sort_scores Func"+"################");

		ArrayList<Double> sorted_scores=new ArrayList<Double>();
		Set keys=map.keySet();
		for(Iterator i=keys.iterator();i.hasNext();){
			String key= (String) i.next();
			sorted_scores.add(map.get(key));
		}
		for(int j=0; j<sorted_scores.size()-1; j++){
			for(int i=0; i<sorted_scores.size()-1; i++){
				if(sorted_scores.get(i)>sorted_scores.get(i+1)){
					double temp=sorted_scores.get(i);
					sorted_scores.set(i,sorted_scores.get(i+1));
					sorted_scores.set(i+1,temp);	
				}
			}
		}
		return sorted_scores;
	}
	private static ArrayList<String> sort_words(TreeMap<String, Double> map) {
		
		System.out.println("################"+" sort_words Func"+"################");

		ArrayList<String> sorted_words=new ArrayList<String>();
		ArrayList<Double> sorted_scores=new ArrayList<Double>();
		Set keys=map.keySet();
		for(Iterator i=keys.iterator();i.hasNext();){
			String key= (String) i.next();
			sorted_words.add(key);
			sorted_scores.add(map.get(key));
		}		
		for(int j=0; j<sorted_scores.size()-1; j++){
			for(int i=0; i<sorted_scores.size()-1; i++){
				if(sorted_scores.get(i)>sorted_scores.get(i+1)){
					double temp=sorted_scores.get(i);
					sorted_scores.set(i,sorted_scores.get(i+1));
					sorted_scores.set(i+1,temp);
					
					String temps=sorted_words.get(i);
					sorted_words.set(i,sorted_words.get(i+1));
					sorted_words.set(i+1,temps);
				}
			}
		}
		return sorted_words;
	}
	private static TreeMap<String, Double> term_tdf_idf( TreeMap<String, Double> word_tdf_1, TreeMap<String, Double> word_idf_1) {

		System.out.println("################"+" term_tdf_idf Func"+"################");

		
		if(word_tdf_1.size() != word_idf_1.size()){
			System.out.println("This is a problem now!");
			System.exit(1);
		}
		TreeMap<String, Double> new_map_tf_idf=new TreeMap<String, Double>();
		Set keys=word_tdf_1.keySet();
		for(Iterator i=keys.iterator();i.hasNext();){
			String key= (String) i.next();
			double tf_idf=-1;
			tf_idf= word_tdf_1.get(key)*word_idf_1.get(key);
			new_map_tf_idf.put(key, tf_idf);
		}
		return new_map_tf_idf;		
	}
	private static TreeMap<String, Double> term_idf( TreeMap<String, Integer> map, ArrayList<TreeMap<String, Integer>> all) {
		
		System.out.println("################"+" term_idf Func"+"################");

		
		TreeMap<String, Double> new_map_idf=new TreeMap<String, Double>();
		Set keys=map.keySet();
		for(Iterator i=keys.iterator();i.hasNext();){
			String key= (String) i.next();
			double idf=1;
			for (int j=0;j<all.size();j++){
				if(all.get(j).containsKey(key)){
					idf=idf+1;
				}
			}
			double idf_value=Math.log(((double)(all.size()))/idf);
			new_map_idf.put(key, idf_value);
		}
		return new_map_idf;
	}
	private static TreeMap<String, Double> term_tdf( TreeMap<String, Integer> map, int doc_size_1) {
		
		System.out.println("################"+" term_tdf Func"+"################");

		
		TreeMap<String, Double> new_map_tf=new TreeMap<String, Double>();
		Set keys=map.keySet();
		for(Iterator i=keys.iterator();i.hasNext();){
			String key= (String) i.next();
			double tf= (double)( (double) map.get(key) )/ (double)(doc_size_1);
			new_map_tf.put(key, tf);
		}
		return new_map_tf;
	}
	public static ArrayList<String> readFile(String rulefile) throws IOException{
    	
		FileInputStream fstream = new FileInputStream(rulefile);
    	DataInputStream in = new DataInputStream(fstream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        ArrayList<String> linelist=new ArrayList<String>();
        String strLine;	    
        
        //System.out.println("Come here");
        while ((strLine = br.readLine() ) != null)   {
        	strLine=strLine.toLowerCase();
        	if(strLine.contains("<h>")){
        		strLine=strLine.replace("<h>", "");
        		strLine=strLine.replace("</h>", "");
        		
        	}if(strLine.contains("<h1>")){
        		strLine=strLine.replace("<h1>", "");
        		strLine=strLine.replace("</h1>", "");
        		
        	}if(strLine.contains("<h2>")){
        		strLine=strLine.replace("<h2>", "");
        		strLine=strLine.replace("</h2>", "");
        		
        	}if(strLine.contains("<h3>")){
        		strLine=strLine.replace("<h3>", "");
        		strLine=strLine.replace("</h3>", "");
        		
        	}if(strLine.contains("<p>")){
        		strLine=strLine.replace("<p>", "");
        		strLine=strLine.replace("</p>", "");	
        	}
        	linelist.add(strLine.toLowerCase());	
        	//System.out.println(strLine);
        }
        System.out.println();
        return linelist;
    }
	
	public static ArrayList<Double> readFileHeader(String rulefile) throws IOException{
		//System.out.println("Come here 2");
		FileInputStream fstream = new FileInputStream(rulefile);
    	DataInputStream in = new DataInputStream(fstream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));    
        ArrayList<Double> headerlist=new ArrayList<Double>();
        String strLine;	    
        while ((strLine = (br.readLine()) ) != null)   {
        	strLine=strLine.toLowerCase();
        	if(strLine.contains("<h>")){
        		headerlist.add(10.0);
        	}else if(strLine.contains("<h1>")){
        		headerlist.add(5.0);
        	}else if(strLine.contains("<h2>")){
        		headerlist.add(4.0);        	
        	}else if(strLine.contains("<h3>")){
        		headerlist.add(3.0);           	
        	}else if(strLine.contains("<p>")){
        		headerlist.add(2.0);          	
        	}else{
        		headerlist.add(0.0);
        	}
        }
        return headerlist;
    }
	
	 private static ArrayList<Double> readStigma(String inputfile) throws IOException {
		 //System.out.println("Come here 3");
	    	FileInputStream fstream = new FileInputStream(inputfile);
	    	DataInputStream in = new DataInputStream(fstream);
	        BufferedReader br = new BufferedReader(new InputStreamReader(in));    
	        ArrayList<Double> stigmalist=new ArrayList<Double>();
	        String strLine;	    
	        while ((strLine = (br.readLine()) ) != null)   {
	        	strLine=strLine.toLowerCase();
	        	if(strLine.contains("but") || strLine.contains("example")){
	        		stigmalist.add(1.0);
	        	}else if(strLine.contains("\"")){
	        		stigmalist.add(2.0);
	        	}else if(strLine.contains("say") || strLine.contains("said")|| strLine.contains("says") || strLine.contains("announces") || strLine.contains("told") || strLine.contains("tells") || strLine.contains("announced") || strLine.contains("announce") || strLine.contains("declare")){
	        		stigmalist.add(3.0);        	
	        	}else if(strLine.contains("however") || strLine.contains("therefor")|| strLine.contains("but") || strLine.contains("although")){
	        		stigmalist.add(1.0);           	
	        	}else{
	        		stigmalist.add(10.0);
	        	}
	        }
	        return stigmalist;
		}
	
	 private static ArrayList<Double> readCue(String inputfile) throws IOException {
	    	//System.out.println("Come here 6");
	    	FileInputStream fstream = new FileInputStream(inputfile);
	    	DataInputStream in = new DataInputStream(fstream);
	        BufferedReader br = new BufferedReader(new InputStreamReader(in));    
	        ArrayList<Double> cuelist=new ArrayList<Double>();
	        String strLine;	    
	        while ((strLine = (br.readLine()) ) != null)   {
	        	strLine=strLine.toLowerCase();
	        	if(strLine.contains("important")){
	        		cuelist.add(4.0);
	        	}else if(strLine.contains("significant")){
	        		cuelist.add(2.0);
	        	}else if(strLine.contains("conclusion") || strLine.contains("sum up")|| strLine.contains("finally") || strLine.contains("because") ){
	        		cuelist.add(3.0);        	
	        	}else if(strLine.contains("however") || strLine.contains("therefore")|| strLine.contains("but") || strLine.contains("although")){
	        		cuelist.add(1.0);           	
	        	}else{
	        		cuelist.add(0.0);
	        	}
	        }
	        
	        return cuelist;
		}

    
    private static ArrayList<Double> readPosition(String inputfile, ArrayList<Double> headerlines) throws IOException {
    	//System.out.println("Come here 4");
    	FileInputStream fstream = new FileInputStream(inputfile);
    	DataInputStream in = new DataInputStream(fstream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));    
        ArrayList<Double> poslist=new ArrayList<Double>();
        String strLine;
        int position=0;
        while ((strLine = (br.readLine()) ) != null)   {
        	position++;
        	if(headerlines.get(position-1)>1){
        	poslist.add((double)headerlines.size()-position+5);
        	}else{
        		poslist.add((double)headerlines.size()-position);
        	}
        }
        return poslist;
	}
}	