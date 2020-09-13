

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
		
	}


	//Just a basic thing used to test if can access the input...
	public void changeWord()
	{
		words[0] = "NO";
		printSentence();
	}

	public void readStopWords()  
	{
		
		/*
		File myObj = new File("stopwords.txt");
		Scanner sr = null;
		try 
		{
			sr = new Scanner(myObj);
		} 
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String[] stopWords = null;
		for(int i = 0; i< 3; i++)
		{
			stopWords[i]  = sr.toString();
			sr.next();
		}
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
		readStopWords();
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
