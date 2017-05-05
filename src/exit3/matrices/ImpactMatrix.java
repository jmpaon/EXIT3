/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exit3.matrices;

import exit3.matrices.ComputableMatrix;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 *
 * @author jmpaon
 */
public class ImpactMatrix extends NumberMatrix<Double> implements ComputableMatrix {

    public ImpactMatrix(int varCount) {
        super(varCount);
    }

    public ImpactMatrix(List<String> identifiers) {
        super(identifiers);
    }

    public ImpactMatrix(List<String> identifiers, Predicate<Double> valueValidator) {
        super(identifiers, valueValidator);
    }

    public ImpactMatrix(int varCount, List<String> identifiers, Predicate<Double> valueValidator, List<List<Double>> values) {
        super(varCount, identifiers, valueValidator, values);
    }
    
    @Override
    public void set(int row, int column, Double value) {
        Objects.requireNonNull(value, "Null impact value");
        if( row == column && value != 0 ) throw new IllegalArgumentException("row index is equal to column index; variables cannot have impacts on themselves");
        super.set(row, column, value);
    }
    
    public ComputableMatrix normalize(double normalizationValue) {
        
        throw new UnsupportedOperationException();
    }


    @Override
    public Double relatingValue() {
        // TODO implement
        return 5d;
    }
    
    
    
}
