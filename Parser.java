import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * This parser reads user input and tries to interpret it as an "Adventure"
 * command. Every time it is called it reads a line from the terminal and
 * tries to interpret the line as a two word command. It returns the command
 * as an object of class Command.
 *
 * The parser has a set of known command words. It checks user input against
 * the known commands, and if the input is not one of the known commands, it
 * returns a command object that is marked as an unknown command.
 * 
 * @author  Majuth Kirakalaprathapan
 * @version 2.0
 * @since   2018-06-11
 */

public class Parser{
	
	// holds all valid command words
	private CommandWords commands;  
	
	/**
	 * Main constructor creates an instance of 
	 * command words to check input with
	 */
	public Parser(){
		commands = new CommandWords();
	}
	
	/**
	 * getCommand gets the users input and splits the input into two words
	 * @return  Command  Return command to use in the program
	 */
	public Command getCommand(){
		// will hold the full input line
		String inputLine = "";   
		String word1;
		String word2;
	
		// print prompt
		System.out.print("> ");     
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
		    inputLine = reader.readLine();
		}
		catch(java.io.IOException exc){
		    System.out.println ("There was an error during reading: " + exc.getMessage());
		}
		
		StringTokenizer tokenizer = new StringTokenizer(inputLine);
		
		if(tokenizer.hasMoreTokens()){
			// get first word
		    word1 = (tokenizer.nextToken()).toLowerCase();      
		}else{
		    word1 = null;
		}
			
		if(tokenizer.hasMoreTokens()){
			// get second word
		    word2 = (tokenizer.nextToken()).toLowerCase();      
		}else{
		    word2 = null;
		}
			
		
		// Check word is command or create null command
		
		if(commands.isCommand(word1)){
		    return new Command(word1, word2);
		}else{
		    return new Command(null, word2);
		}
	}
}