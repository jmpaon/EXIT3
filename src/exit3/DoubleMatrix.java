/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exit3;

import java.util.List;
import java.util.function.Predicate;

/**
 *
 * @author jmpaon
 */
public class DoubleMatrix extends NumberMatrix<Double>{

    public DoubleMatrix(int varCount) {
        super(varCount);
    }

    public DoubleMatrix(List<String> identifiers) {
        super(identifiers);
    }

    public DoubleMatrix(List<String> identifiers, Predicate<Double> valueValidator) {
        super(identifiers, valueValidator);
    }

    public DoubleMatrix(int varCount, List<String> identifiers, Predicate<Double> valueValidator, List<List<Double>> values) {
        super(varCount, identifiers, valueValidator, values);
    }
    
    public DoubleMatrix normalize(double normalizationValue) {
        throw new UnsupportedOperationException();
    }
    
    public DoubleMatrix normalize() {
        throw new UnsupportedOperationException();
    }
    
    
    
    
}
