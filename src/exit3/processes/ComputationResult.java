/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exit3.processes;

import exit3.matrices.*;
import java.util.function.Function;

/**
 *
 * @author juha
 */
public class ComputationResult {
    
    ComputableMatrix inputMatrix;
    SquareMatrix resultMatrix;
    Function<ComputableMatrix,SquareMatrix> computation;

    public ComputationResult(ComputableMatrix inputMatrix, SquareMatrix resultMatrix, Function<ComputableMatrix, SquareMatrix> computation) {
        this.inputMatrix = inputMatrix;
        this.resultMatrix = resultMatrix;
        this.computation = computation;
    }
    
    public String toString() {
        return computation.toString() + "\n" + inputMatrix.toString() + "\n" + resultMatrix.toString() + "\n";
    }
    
    public String toXML() {
        throw new UnsupportedOperationException();
    }
    
    
}
