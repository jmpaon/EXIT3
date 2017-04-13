/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exit3;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 *
 * @author jmpaon
 * @param <T> Type of Number (Integer, Long, Double) 
 */
public class NumberMatrix<T extends Number> extends SquareMatrix<T> {
    
    public NumberMatrix(int varCount) {
        super(varCount);
    }

    public NumberMatrix(List<String> identifiers) {
        super(identifiers);
    }

    public NumberMatrix(List<String> identifiers, Predicate<T> valueValidator) {
        super(identifiers, valueValidator);
    }

    public NumberMatrix(int varCount, List<String> identifiers, Predicate<T> valueValidator, List<List<T>> values) {
        super(varCount, identifiers, valueValidator, values);
    }
    
    public NumberMatrix<T> copy() {
        NumberMatrix copyMatrix = new NumberMatrix(this.identifiers, this.valueValidator);
        
        for(int row=1;row<=this.varCount;row++)
            for(int col=1;col<=this.varCount;col++){
                Number v = this.get(row, col);
                copyMatrix.set(row, col, v);
            }
        return copyMatrix;
    }
    
    NumberMatrix<T> transform(Function<NumberMatrix<T>, NumberMatrix<T>> transformer) {
        return transformer.apply(this);
    }
    
    public T summary(Function<List<T>, T> summaryOperation) {
        return summaryOperation.apply(flatValues());
    }
    
    public List<T> flatValues() {
        List<T> valuesCopy = new LinkedList<>();
        Iterator<T> it = this.iterator();
        while(it.hasNext()) valuesCopy.add(it.next());
        return valuesCopy;
    }
    
    
    
    
    
}
