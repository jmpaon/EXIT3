/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exit3;

import java.util.List;

/**
 *
 * @author jmpaon
 */
public class DoubleMatrix extends SquareMatrix<Number> {
    
    public DoubleMatrix(int varCount) {
        super(varCount);
    }

    public DoubleMatrix(List<String> identifiers) {
        super(identifiers);
    }
    
    public DoubleMatrix normalize(double NormalizationValue) {
        //DoubleMatrix dm = new DoubleMatrix(this.i)
        return null;
    }
    
    
    
}
