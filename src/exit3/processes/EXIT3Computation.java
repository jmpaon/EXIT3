/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exit3.processes;

import exit3.matrices.ComputableMatrix;
import exit3.matrices.SquareMatrix;
import java.util.function.Function;

/**
 *
 * @author juha
 */
public class EXIT3Computation implements ComputationProcess {

    @Override
    public ComputationResult compute(ComputableMatrix inputMatrix, Function<ComputableMatrix, SquareMatrix> computer) {
        
        SquareMatrix resultMatrix = computer.apply(inputMatrix);
        return new ComputationResult(inputMatrix, resultMatrix, computer);
        
    }
    
    public ComputationResult compute(ComputableMatrix inputMatrix) {
        return compute(inputMatrix, new EXIT3Computation.Computer());
    }
    
    
    public class Computer implements Function<ComputableMatrix, SquareMatrix> {

        @Override
        public SquareMatrix apply(ComputableMatrix t) {
            
            
            throw new UnsupportedOperationException("Not supported yet.");
        }
        
    }
    
    
    
    
}
