/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exit3;

import java.util.List;
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
    
    public ImpactMatrix normalize(double normalizationValue) {
        
        Function<ReadableMatrix, ImpactMatrix> normalizationFunction = new Function<ReadableMatrix, ImpactMatrix>() {
            @Override
            public ImpactMatrix apply(ReadableMatrix t) {
                
                
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        };
        
        /*
        ImpactMatrix normalizedMatrix = new ImpactMatrix(identifiers, valueValidator);
        MatrixIterator<Double> it = iterator();
        while(it.hasNext()) {
            double value = it.next() / normalizationValue;
            normalizedMatrix.set(it.row(), it.column(), value);
        }
        return normalizedMatrix; */
    }
    
    public ImpactMatrix normalize() { return normalize(relatingValue()); }
    
    public ImpactMatrix normalize(Function<ReadableMatrix, ImpactMatrix> normalizationFunction) {
        return normalizationFunction.apply(this);
    }

    @Override
    public Double relatingValue() {
        // TODO implement
        return 5d;
    }
    
    
    
    
}
