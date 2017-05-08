/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exit3.computations;

import exit3.matrices.ImpactMatrix;
import exit3.matrices.ReadableMatrix;
import exit3.matrices.WritableMatrix;
import java.util.function.Function;

/**
 *
 * @author juha
 */
public class ImpactMatrixComputation {
    
    
    
    
    
    public static class normalizeByDistanceFrom0 implements Function<ImpactMatrix, WritableMatrix<Double> > {

        @Override
        public WritableMatrix<Double> apply(ImpactMatrix im) {
            WritableMatrix<Double> wm = new ImpactMatrix(null)
            for(int row=1;row<=im.varCount();row++)
                for(int col=1;col<=im.varCount();col++)
                    im.set(row, col, im.get(row, col) / im.averageDistanceFromZero()  );
            return wm;            
        }
    }
    
    
    
    
}
