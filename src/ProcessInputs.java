import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ProcessInputs 
{
	private String[] words; 
	public String[] stopWords = {"is","the"};

	public void splitSentence(String s)
	{
		words = s.split("\\s+");
		printSentence();
	}

	public ProcessInputs()
	{
		//....?
		 readStopWordsFile();
		
	}


	//Just a basic thing used to test if can access the input...
	public void changeWord()
	{
		words[0] = "NO";
		printSentence();
	}

	public void filterOutStopWords()  
	{
		
		/*
		
		*/
		for (int t  = 0; t < words.length; t++)
		{
			for(int i=0; i< stopWords.length ; i++)
			{
				//System.out.println("\t"+words[t]+"\n");
				if (words[t].contains(stopWords[i]))
				{
					words[t] = " "; 
					//System.out.println("WORKED");
				}
			}
		}
		printSentence();
	}
	
	public void readStopWordsFile()
	{
		File stopWordsFile = new File("stopwords.txt");
		String[] stopWords;
		String line;
		try 
		{
			FileReader fr = new FileReader(stopWordsFile);
			BufferedReader br = new BufferedReader(fr);
			
			for(int i = 0; i< 3; i++)
			{
				try 
				{
					line = br.readLine();
					stopWords = line.split(" ");
				} 
				catch (IOException e) 
				{
					System.out.println("\t\t[ERROR READING LINE " + i + " ]");
				}
			}
		} 
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void printSentence()
	{
		System.out.println("\n");
		for (int i = 0; i < words.length; i++) 
		{
			System.out.println(words[i]);
		}
	}


	//THIS WILL BE THE MAIN STEPS OF PROCESSING THAT OCCURS TO THE USER'S INPUT...
	public void process(String s)
	{
		splitSentence(s);
		//changeWord();
		filterOutStopWords();
	}

	
	//********************GETTERS AND SETTERS**************************
	public String[] getWords() 
	{
		return words;
	}

	public void setWords(String[] words) 
	{
		this.words = words;
	}
	
}
