import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class ProcessInputs 
{
	private String[] sentence; 
	public String[] stopWords;

	//Constructor...
	public ProcessInputs()
	{
		//....?
		readStopWordsFile();

	}


	//Splits an inputed string into words[] 
	public void splitSentence(String s)
	{
		sentence = s.split("\\s+");
		printSentence();
	}


	//Will use the contents of the contents of stopWords[] to filter out stop words...
	public void filterOutStopWords()  
	{
		for (int t  = 0; t < sentence.length; t++)
		{
			for(int i=0; i< stopWords.length ; i++)
			{
				//System.out.println("\t"+words[t]+"\n");
				if (sentence[t].equals(stopWords[i]))
				{
					sentence[t] = " "; 
					//System.out.println("WORKED");
				}
			}
		}
		cleanUpSentence();
	}


	//Reads stopwords.txt file and set the string array stopWords[] to equal its contents.
	public void readStopWordsFile()
	{
		File stopWordsFile = new File("src\\Files\\stopwords.txt");
		stopWords = new String[ countNumberOfLinesInFile(stopWordsFile) ];
		//System.out.println("stopWords.length = " + stopWords.length);
		try
		{
			FileReader fr = new FileReader(stopWordsFile);
			BufferedReader br = new BufferedReader(fr);

			for(int i = 0; i < stopWords.length; i++)
			{
				stopWords[i] = br.readLine();
				//System.out.println("STOPWORDS.TXT [" + i + "] = "+ stopWords[i]);
			}
			br.close();
		}
		catch(Exception e)
		{
			System.out.println("\t\t[ERROR READING STOP WORDS FILE.]");
			e.printStackTrace();
		}	
	}

	
	//Counts how many lines are in a file so it can form a String[] of size x...
	public int countNumberOfLinesInFile(File file/*,String[] sArray*/)	//[TODO] Figure out why the string array part doesn't work..
	{
		int lines= 0;
		try 
		{
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			while( (br.readLine() )!=  null )
			{
				lines += 1;
			}
			//sArray = new String[lines];
			br.close();
		} 
		catch (Exception e) 
		{
			System.out.println("\t\t[ERROR COUNTING NUMBER OF LINES]");
		}
		return lines;
	}


	//Prints the contents of the sentence[]...
	public void printSentence()
	{
		System.out.println("\n");
		for (int i = 0; i < sentence.length; i++) 
		{
			System.out.println(sentence[i]);
		}
	}


	//THIS WILL BE THE MAIN STEPS OF PROCESSING THAT OCCURS TO THE USER'S INPUT...
	public void process(String s)
	{
		splitSentence(s);
		//changeWord();
		filterOutStopWords();
	}


	//Returns the sentence[] to a single string to be read easily...
	public String returnSentenceArrayToString()
	{
		String compiledSentence = "";
		for(int i = 0; i < sentence.length; i++)
		{
			if(i == 0)
			{
				compiledSentence = compiledSentence + sentence[i];
			}
			else
			{
				compiledSentence = compiledSentence + " " +sentence[i];
			}
		}
		return compiledSentence;
	}


	//Cleans up sentence[] by removing empty spaces 
	public void cleanUpSentence()
	{
		splitSentence(returnSentenceArrayToString());
	}



	//********************GETTERS AND SETTERS**************************
	public String[] getSentence() 
	{
		return sentence;
	}

	public void setSentence(String[] words) 
	{
		this.sentence = words;
	}

}
