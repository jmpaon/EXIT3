/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exit3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Predicate;

/**
 *
 * @author jmpaon
 * @param <V> Type of values in the matrix
 * @param <I> Type of identifiers in the matrix
 * 
 */
public class SquareMatrix<V> implements ReadableMatrix<V>{
    
    public final int varCount;
    private List<List<V>> values;
    protected final List<String> identifiers;
    protected final Predicate<V> valueValidator;
    
    
    public SquareMatrix(int varCount, List<String> identifiers, Predicate<V> valueValidator, List<List<V>> values) {
        
        if(varCount < 2) throw new IllegalArgumentException("Matrix must have at least 2 variables");
        if(identifiers == null) identifiers = generateIdentifiers(varCount);
        if(identifiers.size() < 2) throw new IllegalArgumentException("Matrix must have at least 2 variables; identifiers has only " + identifiers.size() + " variables");
        if(identifiers.stream().anyMatch(x -> Objects.isNull(x) || x.isEmpty() )) throw new IllegalArgumentException("Null-valued or 0-length identifier string");        
        
        this.varCount = identifiers.size();
        this.identifiers = identifiers;

        this.values = new ArrayList<>(varCount);
        for(int i=0;i<varCount;i++){
            List<V> list = new ArrayList<>();
            for(int ii=0;ii<varCount;ii++)
                list.add(null);
            this.values.add(list);
        }
        
        this.valueValidator = valueValidator;
        
        if(Objects.isNull(values)) return;
        
        if(values.size() != varCount || values.stream().anyMatch(col -> col.size() != varCount ))
            throw new IllegalArgumentException(String.format("Variable count does not match to input values row or column count"));
        
        for(int row=0;row<varCount;row++)
            for(int col=0;col<varCount;col++) {
                this.set(row+1, col+1, values.get(row).get(col));
            }
    }
    
    public SquareMatrix(List<String> identifiers, Predicate<V> valueValidator) {
        this(identifiers.size(), identifiers, valueValidator, null);
    }    
    
    public SquareMatrix(int varCount) {        
        this(generateIdentifiers(varCount), (Objects::nonNull));
    }
    
    public SquareMatrix(List<String> identifiers) {
        this(identifiers, (Objects::nonNull));
    }
    
    
    
    
    
    private static List<String> generateIdentifiers(int count) {
        List<String> ids = new ArrayList<>();
        int id_number=1;
        while(count-- > 0) ids.add("Variable " + id_number++);
        return ids;
    }
    
    public void set(Integer row, Integer column, V value) {
        Predicate<Integer> indexIsValid = ( x -> x >= 0 && x <= this.varCount);
        if(! indexIsValid.test(row) && indexIsValid.test(column)) throw new IllegalArgumentException("Invalid matrix entry: ["+row+","+column+"]");
        if( Objects.nonNull(this.valueValidator)) {
            if(! valueValidator.test(value)) throw new IllegalArgumentException("Invalid value inserted: " + value.toString() );
        }
        this.values.get(row-1).set(column-1, value);
    }
    
    protected void setAll(List<List<V>> values) {
        if(values.size() != this.varCount || values.stream().anyMatch(x -> x.size() != this.varCount))
            throw new IllegalArgumentException("Dimensions of the value array do not match the target matrix dimensions");
        
        if(values.stream().anyMatch(row -> row.stream().anyMatch(value -> !valueValidator.test(value))))
            throw new IllegalArgumentException(String.format("Input values are not valid"));

        for(int row=0;row<varCount;row++)
            for(int col=0;col<varCount;col++) {
                this.set(row+1, col+1, values.get(row).get(col));
            }
    }
    
    public V get(int row, int column) {
        Predicate<Integer> indexIsValid = ( x -> x >= 0 && x <= this.varCount);
        if(! indexIsValid.test(row) && indexIsValid.test(column)) throw new IllegalArgumentException("Invalid matrix entry: ["+row+","+column+"]");
        return this.values.get(row-1).get(column-1);
    }
    
    public String getId(int index) {
        return identifiers.get(index-1);
    }
    
    
    public boolean containsNullValues() {
        return values.stream().anyMatch(x -> x.isEmpty() || x.stream().anyMatch(xx -> Objects.isNull(xx)));
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<varCount;i++) {
            sb.append(getId(i+1)).append(" : ");
            for(V v : values.get(i)) sb.append(Objects.isNull(v) ? "[null]" :  v.toString()).append("; ");
            sb.append("\n");
        }
        return sb.toString();
    }

    public MatrixIterator<V> iterator() {
        return new MatrixIterator<V>(this);
    }

    @Override
    public List<V> chainValues(List<Integer> chainIndices) {
        List<V> chain = new LinkedList<V>();
        for(int i=0;i<chainIndices.size()-1;i++) {
            chain.add( this.get(chainIndices.get(i), chainIndices.get(i+1)) );
        }
        return chain;
    }
    
    public class MatrixIterator<V> implements Iterator<V> {
        int row;
        int col;
        SquareMatrix<V> matrix;
        
        public MatrixIterator(SquareMatrix<V> matrix) {
            this.row = 1;
            this.col = 0;
            this.matrix = matrix;
        }

        @Override
        public boolean hasNext() {
            return row < varCount || (row == varCount && col < varCount);
        }

        @Override
        public V next() {
            if(this.hasNext()) {
                if(col < varCount) col++;
                else { col = 1 ; row++ ; }
                return matrix.get(row, col);
            }
            throw new NoSuchElementException();
        }
        
        public void replace(V value) {
            if(col==0) throw new NoSuchElementException();
            matrix.set(this.row, this.col, value);
        }        
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
