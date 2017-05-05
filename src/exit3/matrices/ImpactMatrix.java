/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exit3.matrices;

import exit3.chains.VariableChain;
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
    
    public ImpactMatrix(SquareMatrix matrix) {
        super(matrix.varCount, matrix.identifiers, matrix.valueValidator, matrix.values);
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
    public double relatingValue() {
        // TODO implement
        return 5d;
    }

    @Override
    public double impactOfChain(VariableChain chain) {
        
        if(chain.matrix != this) throw new IllegalArgumentException("variable chain refers to different matrix");
        
        
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
