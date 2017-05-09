/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exit3.computations;

import exit3.matrices.ImpactMatrix;
import exit3.matrices.ReadableMatrix;
import exit3.matrices.SquareMatrix;
import exit3.matrices.WritableMatrix;
import java.util.function.Function;

/**
 *
 * @author juha
 */
public class ImpactMatrixComputation {
    
    
    public static class normalize {

        public static SquareMatrix<Double> byDistanceFrom0(ImpactMatrix im) {
            SquareMatrix<Double> wm = im.copy();
            double ad = im.averageDistanceFromZero();
            for(int row=1;row<=im.varCount();row++)
                for(int col=1;col<=im.varCount();col++)
                    wm.set(row, col, im.get(row, col) / ad  );
            return wm;                  
        }
        
        
        
        

    }
    
    
    
    
    
    
}
