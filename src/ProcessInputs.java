

public class ProcessInputs 
{
	private String[] words; 

	public void splitSentence(String s)
	{
		words = s.split("\\s+");
		for (int i = 0; i < words.length; i++) 
		{
			System.out.println(words[i]);
		}
	}

	public ProcessInputs()
	{
		//....?
	}


	//Just a basic thing used to test if can access the input...
	public void changeWord()
	{
		words[0] = "NO";
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
	}

	
	
	//********************GETTERS AND SETTERS**************************
	public String[] getWords() {
		return words;
	}

	public void setWords(String[] words) {
		this.words = words;
	}
	
}
