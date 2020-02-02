/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs475_assign3_topic5_hernandez;

/**
 *
 * @author Daniel Hernandez
 */
public class Transition {
    private String fromState;
    private char inputSymbol;
    private char writeSymbol;
    private char direction;      
    private String toState;

    /**
     * Constructor
     *
     * @param fromState
     * @param inputSymbol
     * @param writeSymbol
     * @param direction
     * @param toState
     */
    public Transition(String fromState, String inputSymbol, String writeSymbol, 
            String direction, String toState) {
        this.fromState = fromState;
        this.inputSymbol = inputSymbol.toCharArray()[0];
        this.writeSymbol = writeSymbol.toCharArray()[0];
        this.direction = direction.toCharArray()[0];
        this.toState = toState;
    }

    public String getFromState() {
        return fromState;
    }

    public void setFromState(String fromState) {
        this.fromState = fromState;
    }

    public char getInputSymbol() {        
        return inputSymbol;
    }

    public void setInputSymbol(char inputSymbol) {        
        this.inputSymbol = inputSymbol;
    }

    public String getToState() {
        return toState;
    }

    public void setToState(String toState) {
        this.toState = toState;
    }
    
    public char getWriteSymbol() {
        return writeSymbol;
    }

    public void setWriteSymbol(char writeSymbol) {
        this.writeSymbol = writeSymbol;
    }  
    
    public char getDirection() {
        return direction;
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }

    public String toString() {
        return "FromState: " + this.fromState + ", (" + 
                this.inputSymbol + "," + this.writeSymbol + "," + 
                this.direction + "), " + "ToState: " + this.toState + ".";
    }
}
