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

    private static String startState;
    private static List<String> acceptStates = new ArrayList<>();
    private static ArrayList<String> states = new ArrayList<>();

    static BufferedReader bufferedReader = null;

    static List<Transition> transitions = new ArrayList<>();
    private static String currentState;
    static List<Character> inputAlphabet = new ArrayList<>();
    static List<Character> tapeAlphabet = new ArrayList<>();

    /**
     * Pop up box to user to select file for Turing Machine guidelines.
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
     * Sets the Turing Machine guidelines to test against user input string.
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
     * Sets start state to first line from Turing Machine guidelines file.
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
     * Reads transition line updates states. Currently will only work up to
     * state q9 or a combo q+letter (qA); 2 digits. Will need extra logic coded
     * in future for states q10 and up. Reminder to myself to also rework this
     * with Java Regex rather than .split().
     *
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

        //add to inputAlphabet unless input a non integer or repeated integer.
        if (!inputAlphabet.contains(transition.getInputSymbol())
                && (Character.isDigit(transition.getInputSymbol()))) {
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
     * run - Big O for run method is O(N^3 * M^2) ) where "N" is input string
     * length, number of transitions, and number of times we update Tape
     * tapeList. 
     * (If needed) --> "M" is the extra while Loop we had after we do our first
     * run through the Turing Machine. It also is the extra For Loop as well to
     * rerun through updated transitions. 
     * This will continue to loop until tapeList is
     * empty. - 3 initial loops should be O(N^3) * 2 extra loops(M^2).
     *
     * **When run first goes through it's initial loops O(N^3); it is also
     * updating the transitions with Turing Machine logic for empty spaces.
     * (Example: ' ' -> 'B'.
     *
     * ***After this initial run we now need to continuously run through the
     * Turing Machine until
     *
     * @param input
     * @return
     */
    public static boolean run(String input) {
        currentState = startState;
        Tape tapeList = new Tape();
        int headPosition = 1;

        //Add input to TapeCell based on head position.
        for (char inputChar : input.toCharArray()) {
            System.out.println("Current state is "
                    + currentState + ".");
            tapeList.addCells(headPosition, inputChar);
            if (!inputAlphabet.contains(inputChar)) {
                return false;
            }

            //Lookup transitions for current state.
            for (Transition transition : transitions) {
                //If transition.getInputSymbol is blank in the inputTextFile
                //Convert to 'B';
                if (transition.getInputSymbol() == ' ') {
                    transition.setInputSymbol('B');
                }
                //If transition.getWriteSymbol is blank in the inputTextFile
                //Convert to 'B';
                if (transition.getWriteSymbol() == ' ') {
                    transition.setWriteSymbol('B');
                }

                String tapeHead = tapeList.toString(headPosition);
                char inputSymbolChar = transition.getInputSymbol();
                String inputSymbolString = String.valueOf(inputSymbolChar);

                boolean stateCheck = (currentState.equals(transition.getFromState()));
                boolean boolHead = (tapeHead.equals(inputSymbolString));

                System.out.println(transition.toString());

                while (boolHead && stateCheck) {
                    currentState = transition.getToState();
                    tapeList.removeCells(headPosition);
                    tapeList.addCells(headPosition, transition.getWriteSymbol());

                    if (transition.getDirection() == 'R') {
                        headPosition++;
                        break;
                    } else if (transition.getDirection() == 'L') {
                        headPosition--;
                        break;
                    }

                }
            }
        }
        System.out.println("current state is "
                + currentState);

        //Break out of run() if currentHead is empty.
        if (tapeList.checkBasePosition(headPosition)) {
            return false;
        }
        System.out.println("Initial run over. More transitions found based "
                + "on current headPosition. Continuining run():  ");
        
        //Run Tape through Transitions(updated) again if tapeList[0] == $. 
        while (tapeList.checkBasePosition(0)) {
            System.out.println("Current state is "
                    + currentState + ".");

            for (Transition transition : transitions) {
                String tapeHead = tapeList.toString(headPosition);
                char inputSymbolChar = transition.getInputSymbol();
                String inputSymbolString = String.valueOf(inputSymbolChar);

                boolean stateCheck = (currentState.equals(transition.getFromState()));
                boolean boolHead = (tapeHead.equals(inputSymbolString));

                System.out.println(transition.toString() + "Current head is: "
                        + tapeHead + ".");

                while (boolHead && stateCheck) {
                    currentState = transition.getToState();
                    tapeList.removeCells(headPosition);
                    tapeList.addCells(headPosition, transition.getWriteSymbol());

                    if (transition.getDirection() == 'R') {
                        headPosition++;
                        break;
                    } else if (transition.getDirection() == 'L') {
                        headPosition--;
                        break;
                    }
                }

            }
        }

        System.out.println("current state is "
                + currentState);
        if (!acceptStates.contains(currentState)) {
            return false;
        }

        return true;
    }

    /**
     * Gives the user the acceptable inputAlphabet parameters from the Turing
     * Machine guidelines file.
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
