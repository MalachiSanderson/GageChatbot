import java.io.File;
import java.util.Scanner;

//*********************************************
//ZaCK Hagerty
//Java Chatbot GUI
//9-13-20
//Purpose: This method will receive user input, scan for stop words, and remove them, then return user input
//*********************************************
public class StopWords {

	
	public void readStopWords()  //<----- Will import String
	{
		File myObj = new File("stopwords.txt");
	  	Scanner sr = new Scanner(myObj);
	  	
	  	String[] stopWords;
	  	for(int i = 0; i<20; i++)
	  	{
	  		 
	  		stopWords[i] =sr.next();
	  	
	  	}
	  	
	  	for(int i=0; i< 20 ; i++)
	  	{
	  		if (stopWords[i] == // sentence[i])
	  		{
	  		//	sentence[i] = null; 	
	  		}
	  		
	  	}
	
	

