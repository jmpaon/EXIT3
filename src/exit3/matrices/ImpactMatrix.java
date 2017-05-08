/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exit3.matrices;

import exit3.chains.VariableChain;
import exit3.matrices.ComputableMatrix;
import java.util.Collection;
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
public class ImpactMatrix extends NumberMatrix<Double> implements WritableMatrix<Double> {

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
    
    public double absRowSum(int row) {
        Collection<Double> entries = collect((ReadingIterator<Double> it, Double d) -> it.row() == row);
        return entries.stream().mapToDouble(x -> Math.abs(x)).sum();
    }
    
    public double absColumnSum(int column) {
        Collection<Double> entries = this.collect((ReadingIterator<Double> it, Double d) -> it.column() == column);
        return entries.stream().mapToDouble(x -> Math.abs(x)).sum();
    }
    
    public double min() { return this.flatValues().stream().min(Double::compareTo).get(); }
    
    public double max() { return this.flatValues().stream().max(Double::compareTo).get(); }
    
    public double absMax() { return this.flatValues().stream().map(x -> Math.abs(x)).max(Double::compareTo).get(); }
    
    public double mean() { return this.collect((it, d) -> it.row()!=it.column()).stream().mapToDouble(x->x).average().getAsDouble(); }
    
    
    public double averageDistanceFromZero() {
        double sum = 0;
        for(double d : flatValues()) sum+= Math.abs(d);
        return sum / (varCount*varCount-varCount);
    }
    
    public double averageDistanceFrom(double value) {
        return this.collect((it,d) -> it.row()!=it.column()).stream().mapToDouble(x->Math.abs(x-value)).average().getAsDouble();
    }
    
    public double stdev() {
        return this.collect((it,d)-> it.row() != it.column()).stream().mapToDouble(x->Math.pow(x-mean(),2)).map(Math::sqrt).average().getAsDouble();
    }
    
    
}
