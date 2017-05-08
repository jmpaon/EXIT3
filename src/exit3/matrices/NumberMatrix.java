/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exit3.matrices;


import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 *
 * @author jmpaon
 * @param <V> Type of Number (Integer, Long, Double) 
 */
public class NumberMatrix<V extends Number> extends SquareMatrix<V> {
    
    public NumberMatrix(int varCount) {
        super(varCount);
    }

    public NumberMatrix(List<String> identifiers) {
        super(identifiers);
    }

    public NumberMatrix(List<String> identifiers, Predicate<V> valueValidator) {
        super(identifiers, valueValidator);
    }

    public NumberMatrix(int varCount, List<String> identifiers, Predicate<V> valueValidator, List<List<V>> values) {
        super(varCount, identifiers, valueValidator, values);
    }
    
    public NumberMatrix<V> copy() {
        NumberMatrix copyMatrix = new NumberMatrix(this.identifiers, this.valueValidator);
        
        for(int row=1;row<=this.varCount;row++)
            for(int col=1;col<=this.varCount;col++){
                Number v = this.get(row, col);
                copyMatrix.set(row, col, v);
            }
        return copyMatrix;
    }
    
    public V summarize(Function<ReadableMatrix<V>, V> summarizer) {
        return summarizer.apply(this);
    }

    
}
