/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs475_assign3_topic5_hernandez;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Daniel Hernandez
 */
public class Tape {    
        
    public static ArrayList<Character> cells = new ArrayList<>();
    public static int headPosition;

    
    
    public Tape() {
        this.cells.add('$');  
        
        //Initialize with size of 
        for (int i = 1; i < 65; i++) {
            this.cells.add(' ');
        }
        
        this.headPosition = 0;
    }   

    public static ArrayList<Character> getCells() {
        return cells;
    }

    public void setCells(ArrayList<Character> cells) {
        this.cells = cells;
    }
    
    public void addCells(int headPosition, char charCell) {
        cells.add(headPosition, charCell);  
       
    }
    
    public void removeCells(int headPosition) {
        cells.remove(headPosition);        
    }

    public int getHeadPosition() {
        return headPosition;
    }

    public void setHeadPosition(int headPosition) {
        this.headPosition = headPosition;
    }
    
    public String toString(int headPosition) {
        return this.cells.get(headPosition).toString();
    }
    

     
   
    

}
