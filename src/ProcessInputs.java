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
		readStopWordsFile();
	}


	//THIS WILL BE THE MAIN STEPS OF PROCESSING THAT OCCURS TO THE USER'S INPUT...
	public void process(String s)
	{
		splitSentence(s);
		//filterOutStopWords();
		uwuTwanslatow();
	}


	//Splits an inputed string into sentence[] and removes all non-letter characters and removes any capitalization...
	public void splitSentence(String s)
	{
		//System.out.println(s);
		sentence = s.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
		//By removing the unwanted characters before splitting, you avoid having to loop through the elements.
		printSentence();
	}


	//Splits an inputed string into sentence[] and removes all non-letter characters and removes any capitalization...
	public void splitSentenceNOCAP(String s)
	{
		//System.out.println(s);
		sentence = s.replaceAll("[^a-zA-Z ]", "").split("\\s+");
		//By removing the unwanted characters before splitting, you avoid having to loop through the elements.
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
		//System.out.println("\tPOST STOP WORD FILTERING...");
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
	public int countNumberOfLinesInFile(File file)	//[TODO] Figure out why the string array part doesn't work..
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
				compiledSentence = compiledSentence + " " + sentence[i];
			}
		}
		return compiledSentence;
	}


	//Cleans up sentence[] by removing empty spaces 
	public void cleanUpSentence()
	{
		splitSentence(returnSentenceArrayToString());
	}


	//***********************************FURRY SPEECH TRANSLATOR************************************
	public void uwuTwanslatow()
	{
		String word;

		if(0.1 > randomFrom(0,1))
		{
			splitSentence("OwO notices message " + returnSentenceArrayToString() );
		}

		for(int i = 0; i< sentence.length; i++)
		{
			if(sentence[i].contains("you"))
			{
				sentence[i] = "chu";
			}

			if(sentence[i].equals("hi")) 
			{
				sentence[i] = "hai";
			}

			if(sentence[i].contains("summer"))
			{
				sentence[i] = "Summy Wummy";
			}
			if(sentence[i].contains("zack"))
			{
				sentence[i] = "Zackie poo bear";
			}
			if(sentence[i].contains("gage"))
			{
				sentence[i] = "Gagie wagie";
			}
			if(sentence[i].contains("viv") || sentence[i].contains("vivian"))
			{
				sentence[i] = "Vivvy Wivvy";
			}
			if(sentence[i].contains("fuck"))
			{
				sentence[i] = "fluff";
			}
			if(sentence[i].contains("bad"))
			{
				sentence[i] = "naughty";
			}

			if(sentence[i].contains("the"))
			{
				sentence[i] = "teh";
			}
			else if(sentence[i].contains("to"))
			{
				sentence[i] = "tew";
			}
			else if(sentence[i].contains("th"))
			{
				word = sentence[i].replaceAll("th","f");
				sentence[i] = word;
			}
			else if(sentence[i].contains("t"))
			{
				word = sentence[i].replace('t', 'd');
				sentence[i] = word;
			}

			if(sentence[i].contains("r"))
			{
				word = sentence[i].replace('r', 'w');
				sentence[i] = word;
				//System.out.println("\t\tr or l detected");
			}
			if(sentence[i].contains("l"))
			{
				word = sentence[i].replace('l', 'w');
				sentence[i] = word;
				//System.out.println("\t\tr or l detected");
			}
			if(sentence[i].contains("ck"))
			{
				word = sentence[i].replaceAll("ck","k");
				sentence[i] = word;
			}

			if(sentence[i].contains("danny"))
			{
				sentence[i] = "Daddy";
			}
		}
		//printSentence();

		if(0.15 > randomFrom(0,1))
		{
			splitSentenceNOCAP("Beware the false prophet walks among us.");
			if(0.1 > randomFrom(0,1))
			{
				splitSentenceNOCAP(returnSentenceArrayToString() + " UwU~~");
			}
		}
		else if(0.4 > randomFrom(0,1))
		{
			splitSentenceNOCAP(returnSentenceArrayToString() + " UwU~~");
		}
		//sentence[sentence.length] = sentence[sentence.length] + " UwU~~" ; 
	}

	//**********************************END OF THE CURSED SECTION***********************************


	//Basic Random number Generator...
	public static double randomFrom (double low, double high) 
	{
		double randNum = 0;
		randNum = (Math.random()*(high-low) + low);
		return randNum;
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
