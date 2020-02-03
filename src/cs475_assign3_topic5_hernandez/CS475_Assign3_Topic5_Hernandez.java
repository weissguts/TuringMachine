/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs475_assign3_topic5_hernandez;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Main Class - Creates a Turing Machine from inputFile. Tests user input 
 * string to see if language is acceptable. Current max input allowed will be 
 * 64 bits. Max digits for acceptable States is two (example q1 or qA). 
 * @author Daniel Hernandez
 */
public class CS475_Assign3_Topic5_Hernandez {

    static OptionPane optionPane = new OptionPane();
    static String inputString;
    static boolean accepted = false;

    public static void main(String[] args) throws FileNotFoundException, IOException {

        TuringMachine turingMachine = new TuringMachine();
        //prompts the user for a file location (use JFileChooser)
        turingMachine.chooseFile();
        //reads a Turing machine (TM) from a text file (see format below),
        turingMachine.processFile();
        //displays to the user the alphabet associated with the TM that was read
        turingMachine.displayAlphabet();
        //prompts the user for an input string (use JOptionPane),
        inputString = optionPane.getInputString();
        accepted = turingMachine.run(inputString);
        //displays whether the TM accepts the user’s input string (use JOptionPane).
        optionPane.displayAccepted(accepted);
        //as Java comments give the Big-O analysis of the fileInput and run methods
        //as a separate file, submit the TM file you “unit” tested on.

    }

}
