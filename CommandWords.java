/**
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognize commands as they are typed in.
 *
 * @author  Majuth Kirakalaprathapan
 * @version 2.0 
 * @since   2018-06-11
 */

class CommandWords{
    // a constant array that holds all valid command words
    private static final String validCommands[] = {"go", "quit", "help", "look", "take", "check", "use", "run"};

    /**
     * Main constructor initializes the command words
     */
    public CommandWords(){
        // just creates an instance of commandwords class
    }

    //Check whether a given String is a valid command word.Return true if it is, false if it isn't.
   /**
    * isCommand method Check whether a given String is a valid 
    * command word. Return true if it is, false if it isn't.
    * @param  aString  Gets the word to see if it is a command
    * @return boolean  Returns whether the string is a command
    */
    public boolean isCommand(String aString){
        for(int i = 0; i < validCommands.length; i++) {
            if(validCommands[i].equals(aString)){
                return true;
            }
        }
        // if we get here, the string was not found in the commands
        return false;
    }

}