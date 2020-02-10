/**
 * This class holds information about a command that was issued by the user.
 * A command currently consists of two strings: a command word and a second
 * word (for example, if the command was "take map", then the two strings
 * obviously are "take" and "map").
 * 
 * The way this is used is: Commands are already checked for being valid
 * command words. If the user entered an invalid command (a word that is not
 * known) then the command word is <null>.
 *
 * If the command had only one word, then the second word is <null>.
 * 
 * @author  Majuth Kirakalaprathapan
 * @version 1.0 
 * @since   2018-06-11
 */

class Command{
 
	private String commandWord;
    private String secondWord;

    /**
     * Main constructor for command class. First and second word must be supplied, but
     * either one (or both) can be null. The command word should be null to
     * indicate that this was a command that is not recognized by this game.
     * @param  firstWord  The first word of the command is passed
     * @param  secondWrd  The second word of the command is passed
     */
    public Command(String firstWord, String secondWrd){
        commandWord = firstWord;
        secondWord = secondWrd;
    }

    /**
     * getCommanWord method returns the command word (the first word) of this 
     * command. If the command was not understood, the result is null.
     * @return  String  Returns the commandWord of the command
     */
    public String getCommandWord(){
        return commandWord;
    }

    /**
     * getSeconWord method Return the second word of this command. Returns 
     * null if there was no second word.
     * @return  String  Returns the second word of the command
     */
    public String getSecondWord(){
        return secondWord;
    }

    /**
     * isUnkown method returns true if this command was not understood.
     * @return  boolean  Returns result
     */
    public boolean isUnknown(){
        return (commandWord == null);
    }

    /**
     * hasSecondWord returns true if the command has a second word.
     * @return  boolean  Returns result
     */
    public boolean hasSecondWord(){
        return (secondWord != null);
    }
}