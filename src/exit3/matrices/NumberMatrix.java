/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exit3.matrices;


import java.text.DecimalFormat;
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
        
        WritingIterator wit = new WritingIterator(copyMatrix);
        ReadingIterator rit = new ReadingIterator(this);
        
        while(rit.hasNext() && wit.hasNext()) {
            rit.next();
            wit.next();
            
            wit.replace(rit.get());

        }
        
        assert wit.hasNext() == rit.hasNext();

        return copyMatrix;
    }
    
    public V summarize(Function<ReadableMatrix<V>, V> summarizer) {
        return summarizer.apply(this);
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        java.text.DecimalFormat df = new DecimalFormat("+##.##;-##.##");
        int id_index = 0;
        
        for(List<? extends Number> l : values) {
            sb.append(this.getId(++id_index)).append(":\t");
            for(Number i : l) {
                sb.append(df.format(i.doubleValue()));
                sb.append("\t");
            }
            sb.append("\n");
        }
        
        return sb.toString();
    }

    
}
