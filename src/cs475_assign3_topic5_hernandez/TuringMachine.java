/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs475_assign3_topic5_hernandez;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents acceptable Turing Machine.
 * 
 * @author Daniel Hernandez
 */
public class TuringMachine {
    static Tape stack = new Tape();

    private static String startState;
    private static List<String> acceptStates = new ArrayList<>();
    private static ArrayList<String> states = new ArrayList<>();

    static BufferedReader bufferedReader = null;

    static List<Transition> transitions = new ArrayList<>();
    private static String currentState;
    static List<Character> inputAlphabet = new ArrayList<>();
    static List<Character> tapeAlphabet = new ArrayList<>();

    /**
     * Pop up box to user to select file for DPA guidelines.
     *
     * @throws FileNotFoundException
     */
    public static void chooseFile() throws FileNotFoundException {
        //read file
        FileChooser fcs = new FileChooser();
        bufferedReader = new BufferedReader(new FileReader(fcs.Chooser()));
    }

    /**
     * processFile - Big O for fileInput and processFile is O(n) since we are
     * just iterating through the while loop and there is nothing nested inside.
     *
     * Sets the DPA guidelines to test against user input string.
     *
     * @throws IOException
     */
    public static void processFile() throws IOException {

        //read startState
        String firstLine = bufferedReader.readLine();
        if (firstLine != null) {
            startState(firstLine);
        }

        //read acceptStates
        String secondLine = bufferedReader.readLine();
        if (secondLine != null) {
            readAcceptStates(secondLine);
        }

        //read transition
        String nextLine = bufferedReader.readLine();
        while (nextLine != null) {
            // Loop transitions
            readTransition(nextLine);
            nextLine = bufferedReader.readLine();
        }
    }

    /**
     * Sets start state to first line from DPA guidelines file.
     *
     * @param firstLine
     */
    public static void startState(String firstLine) {
        startState = firstLine;
    }

    /**
     * Uses regex to filter out the states and adds them to a list.
     *
     * @param secondLine
     */
    public static void readAcceptStates(String secondLine) {
        acceptStates = Arrays.asList(secondLine.split(" "));
    }

    /**
     * Reads transition line updates states. 
     * @param nextLine
     */
    public static void readTransition(String nextLine) {
        //split line
        String[] lineString = nextLine.split("");      
        String fromState = lineString[0].concat(lineString[1]);
        String inputSymbol = lineString[4];
        String writeSymbol = lineString[6];
        String direction = lineString[8];
        String toState = lineString[11].concat(lineString[12]);
        //create transition
        Transition transition = new Transition(fromState, inputSymbol, writeSymbol,
                direction, toState);
        transitions.add(transition);        
        
        //add to inputAlphabet unless input is 'e'. 'e' is for empty input.
        if (!inputAlphabet.contains(transition.getInputSymbol())
                && (Character.isDigit(transition.getInputSymbol()) )){
            inputAlphabet.add(transition.getInputSymbol());
        }
        //add to States
        if (!states.contains(fromState)) {
            states.add(fromState);
        }
        if (!states.contains(toState)) {
            states.add(toState);
        }

    }

    /**
     * run - Big O for run method is O(N^3) where "N" is input string length,
     * number of transitions, and number of stackOperation Strings we convert
     * to charArrays. - 3 loops should be O(N^3).
     *
     * Compares user input String char by char against acceptable DPA
     * guidelines.
     *
     * @param input
     * @return
     */


    /**
     * Gives the user the acceptable inputAlphabet parameters from the DPA
     * guidelines file.
     *
     * @return
     */
    public String displayAlphabet() {
        System.out.println("\u03A3 = " + inputAlphabet);
        return null;
    }

    public static ArrayList<String> states() {
        return states;
    }
}