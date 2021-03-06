

//IMPORT STUFF
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
//END OF IMPORTS

//*********************************************
//Malachi Gage Sanderson
//Java Chatbot GUI
//9-9-20
//Purpose: this class will handle the display of what the bot says and let the user input strings in a textbox to send to the bot.
//*********************************************
public class ChatbotGUI extends Application
{
	//SET UP PUBLIC STUFF
	public ImageView[] Images = new ImageView[2];
	public int userMultiplier = 0;
	public int bufordMultiplier = 0;
	
	Pane pane = new Pane();
	MenuBar menuBar = new MenuBar();
	BorderPane bp = new BorderPane();
	Scene scene = new Scene(bp,750,600);
	TextField tF = new TextField();
	public String inputtedMessage;
	public final static ProcessInputs pInputs = new ProcessInputs();
	public StackPane sPaneIn = new StackPane();
	public StackPane sPaneOut = new StackPane();
	

	//THIS DETERMINES WHAT SOUND EFFECT TO PLAY ON MESSAGE SENT...
	String audioFilePath = "src\\Files\\CoinSFX.wav";

	//CONSTRUCTOR...
	public ChatbotGUI()
	{
		//Basic set up stuff..
		pane.setStyle("-fx-background-color: black");
		makeNodes();
	}
	
	


	//MAKE BUTTONS (AND OTHER NODES?)...
	public void makeNodes()
	{
		//Text field stuff...
		tF.setPromptText("Hello. Input Something to say to the bot.");
		
		//Submit button stuff...
		
	/*	 ScrollBar s = new ScrollBar();  
	        s.setMin(0);  
	        s.setMax(1000);  
	        s.setValue(0);  
	        s.setOrientation(Orientation.VERTICAL);     //Couldn't get to work. This is Scrollbar stuff
	        s.setUnitIncrement(200);  
	        s.setBlockIncrement(10);
	        pane.getChildren().add(s); */
	        
	        
		Button submit = new Button("Submit");
		pane.getChildren().add(submit);
		submit.setLayoutX(scene.getWidth()-55); 
		submit.setLayoutY(scene.getHeight()-55);
		submit.setOnAction(e->
		{
			if(!tF.getText().trim().isEmpty()) 
			{
		//		pane.getChildren().remove(sPaneIn);
		//		pane.getChildren().remove(sPaneOut);
				sPaneIn = new StackPane();
				sPaneOut = new StackPane();
				
				imageConstructor();
				sendInputsToProcessor();
			}
			else
			{
				System.out.println("\t[Text Field was empty]");
			}
		});
		//Key Press Stuff...
		tF.setOnKeyPressed(event -> 
		{
			if (event.getCode().equals(KeyCode.ENTER))
			{
				submit.fire();
			}
		});
	}
	
	public void imageConstructor()
	{	
		Image bigBufordImage = new Image("bigbuford.png");
		Image userImage = new Image("user.png");
		Images[0] = new ImageView();
		Images[0].setImage(bigBufordImage);
	    Images[0].setFitHeight(50);
	    Images[0].setFitWidth(50);
	    Images[1] = new ImageView();
		Images[1].setImage(userImage);
	    Images[1].setFitHeight(50);
	    Images[1].setFitWidth(50);
	}

	//Method for actually sending Buford the input...
	public void sendInputsToProcessor()
	{
		inputtedMessage = tF.getText();
		sendInputToBot(inputtedMessage);
		displayInputedText(inputtedMessage,sPaneIn);
		//System.out.println("\t[TEXT WAS GATHERED]: " + inputtedMessage);

		playSound(audioFilePath);
		tF.clear();
		displayOutputedText(pInputs.getSentence(),sPaneOut);
	}

	//PLAY SOUND EFFECT...
	public void playSound(String soundLocation)
	{
		try
		{
			File soundPath = new File(soundLocation);
			if(soundPath.exists())
			{
				AudioInputStream audioInput = AudioSystem.getAudioInputStream(soundPath);
				Clip clip = AudioSystem.getClip();
				clip.open(audioInput);
				clip.start();
				//JOptionPane.showMessageDialog(null,"Press Okay to stop playing");
			}
			else 
			{
				System.out.println("[SOUND FILE DOES NOT EXIST]");
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}


	//SENDS INPUTS TO BOT FOR PROCESSING...
	public static void sendInputToBot(String input)
	{
		pInputs.process(input);
	}


	//Display Inputed Text...
	public void displayInputedText(String t, StackPane stackPane)
	{
		int multiplier = 100 * userMultiplier;
		
		stackPane.setStyle("-fx-background-color: black");
		pane.getChildren().add(stackPane);
		pane.getChildren().add(Images[1]);
		Text text = new Text("User: " + t);

		Rectangle rect = new Rectangle();
		stackPane.getChildren().addAll(rect,text);
		rect.setFill(Color.WHITE);
		rect.setWidth(text.getLayoutBounds().getMaxX()*1.5);
	
		
		
		
		rect.setHeight(30);

		stackPane.setLayoutY(10 + multiplier);
		stackPane.setLayoutX(600 - text.getLayoutBounds().getMaxX()* 1.4);
		Images[1].setLayoutY(10 + multiplier);
		Images[1].setLayoutX(650);
		
		userMultiplier++;
	}


	//METHOD FOR DISPLAYING INPUT AFTER IT'S PROCESSED BY BOT...
	public void displayOutputedText(String[] sentence, StackPane stackPane)
	{
		int multiplier = 100 * bufordMultiplier;
		String compiledSentence = "";
		for(int i = 0; i<sentence.length; i++)
		{
			compiledSentence = compiledSentence + " " + sentence[i];
		}
		
		stackPane.setStyle("-fx-background-color: black");
		pane.getChildren().add(stackPane);
		pane.getChildren().add(Images[0]);
		
		
		Text text = new Text("Buford: " + compiledSentence );

		Rectangle rect = new Rectangle();
		stackPane.getChildren().addAll(rect,text);
		rect.setFill(Color.LIME);
		rect.setWidth(text.getLayoutBounds().getMaxX()*1.5);
		rect.setHeight(30);

		stackPane.setLayoutY(55 + multiplier);
		stackPane.setLayoutX(65);
		Images[0].setLayoutY(40 + multiplier);
		Images[0].setLayoutX(10);
		
		bufordMultiplier++;
	}


	//[TODO] NEED TO MAKE METHOD FOR SCROLLING + PLACING NEW MESSAGE BUBBLES...
	public void placeNewMessageBubble()
	{
		//[TODO]...
	}
	



	//START...
	@Override
	public void start(Stage stage) 
	{

		bp.setCenter(pane);
		bp.setTop(menuBar);
		bp.setBottom(tF);
		stage.setScene(scene);
		stage.setTitle("BIG BUFORD V -1.0 ");
		stage.show();

	}


	//MAIN...
	public static void main(String[] args) 
	{
		launch(args);
	}

}





