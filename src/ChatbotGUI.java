

//IMPORT STUFF
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
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
	Pane pane = new Pane();
	MenuBar menuBar = new MenuBar();
	BorderPane bp = new BorderPane();
	Scene scene = new Scene(bp,750,600);
	TextField tF = new TextField();
	public String inputtedMessage;
	public final static ProcessInputs pInputs = new ProcessInputs();

	//THIS DETERMINES WHAT SOUND EFFECT TO PLAY ON MESSAGE SENT...
	String audioFilePath = "src\\Sounds\\CoinSFX.wav";

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
		//Textfield stuff...
		tF.setPromptText("Hello. Input Something to say to the bot.");


		//Submit button stuff...
		Button submit = new Button("Submit");
		pane.getChildren().add(submit);
		submit.setLayoutX(scene.getWidth()-55); 
		submit.setLayoutY(scene.getHeight()-55);
		submit.setOnAction(e->
		{
			if(!tF.getText().trim().isEmpty()) 
			{
				inputtedMessage = tF.getText();
				sendInputToBot(inputtedMessage);

				displayInputedText(inputtedMessage);

				//System.out.println("\t[TEXT WAS GATHERED]: " + inputtedMessage);

				playSound(audioFilePath);
				tF.clear();
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
	public void displayInputedText(String t)
	{
		StackPane stackPane = new StackPane();
		stackPane.setStyle("-fx-background-color: black");
		pane.getChildren().add(stackPane);
		Text text = new Text("User: " + t);

		Rectangle rect = new Rectangle();
		stackPane.getChildren().addAll(rect,text);
		rect.setFill(Color.WHITE);
		rect.setWidth(text.getLayoutBounds().getMaxX()*1.5);
		rect.setHeight(30);



		stackPane.setLayoutY(10);
	}


	//[TODO] NEED TO MAKE METHOD FOR DISPLAYING INPUT AFTER IT'S PROCESSED BY BOT...
	public void displayOutputedText()
	{
		//[TODO]...
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
		stage.setTitle("Chatbot V 0.1 ");
		stage.show();

	}


	//MAIN...
	public static void main(String[] args) 
	{
		launch(args);
	}

}





