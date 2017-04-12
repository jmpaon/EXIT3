/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exit3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

/**
 *
 * @author jmpaon
 * @param <V> Type of values in the matrix
 * @param <I> Type of identifiers in the matrix
 * 
 */
public class SquareMatrix<V> {
    
    public final int varCount;
    public List<List<V>> values;
    public List<String> identifiers;
    private Predicate<V> valueValidator;
    
    public SquareMatrix(int varCount, List<String> identifiers, Predicate<V> valueValidator) {
        if(varCount < 2) throw new IllegalArgumentException("Matrix must have at least 2 variables");
        if(identifiers == null) identifiers = generateIdentifiers(varCount);
        if(identifiers.size() < 2) throw new IllegalArgumentException("Matrix must have at least 2 variables; identifiers has only " + identifiers.size() + " variables");
        if(identifiers.stream().anyMatch(x -> Objects.isNull(x) || x.isEmpty() )) throw new IllegalArgumentException("Null-valued or 0-length identifier string");
        
        this.varCount = identifiers.size();
        this.identifiers = identifiers;

        this.values = new ArrayList<>(varCount);
        this.values.stream().forEach(x -> x = new ArrayList<>(varCount));
        
    }
    
    public SquareMatrix(int varCount, List<String> identifiers, Predicate<V> valueValidator, List<List<V>> values) {
        this(varCount, identifiers, valueValidator);
        
        if(values.size() != varCount || values.stream().anyMatch(col -> col.size() != varCount ))
            throw new IllegalArgumentException(String.format("Variable count does not match to input values row or column count"));
        
        for(int row=0;row<varCount;row++)
            for(int col=0;col<varCount;col++)
                this.put(row, col, values.get(row-1).get(col-1));
        
        
    }
    
    
    public SquareMatrix(int varCount) {        
        this(varCount, generateIdentifiers(varCount), (x -> true));
    }
    
    public SquareMatrix(List<String> identifiers) {
        this(identifiers.size(), identifiers, ( x -> true ));
    }
    
    private static List<String> generateIdentifiers(int count) {
        List<String> ids = new ArrayList<>();
        int id_number=1;
        while(count-- > 0) ids.add("Variable " + id_number++);
        return ids;
    }
    
    public void put(Integer row, Integer column, V value) {
        Predicate<Integer> indexIsValid = ( x -> x >= 0 && x <= this.varCount);
        if(! indexIsValid.test(row) && indexIsValid.test(column)) throw new IllegalArgumentException("Invalid matrix entry: ["+row+","+column+"]");
        if(! valueValidator.test(value)) throw new IllegalArgumentException("Invalid value inserted: " + value.toString() );
        this.values.get(row-1).set(column-1, value);
    }
    
    public V get(Integer row, Integer column) {
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
            for(V v : values.get(i+1)) sb.append(v.toString()).append(";") ;
        }
        return sb.toString();
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
}
