/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exit3.chains;

import exit3.matrices.ReadableMatrix;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

/**
 *
 * @author jmpaon
 */
public class VariableChain {
    
    /* Will be abstract */
    
    protected final List<Integer> indices;
    private final ReadableMatrix matrix;
    
    public VariableChain(ReadableMatrix matrix, List<Integer> indices) {
        Objects.requireNonNull(matrix, "matrix argument is null");
        Objects.requireNonNull(indices, "index list is null");
        // if(indices.isEmpty()) throw new IllegalArgumentException();
        indices.stream().forEach((Integer x) -> Objects.requireNonNull(x));
        indices.stream().forEach((Integer x) -> matrix.testIndex(x));
        
        this.matrix = matrix;
        this.indices = Collections.unmodifiableList(indices);
        
    }
    
    public VariableChain(ReadableMatrix matrix, Integer[] indices) {
        this(matrix, Arrays.asList(indices) );
    }
    
    public int getVariableIndexAt(int index) {
        return indices.get(index-1);
    }
    
    
    @Override
    public String toString() {
        return "Variable chain with indices " + this.indices.toString();
    }
    
    
    static List<Integer> range(int to) {
        // return Arrays.asList(IntStream.range(1, to).toArray());
        List l = new LinkedList();
        int i=1;
        while(i++<to) l.add(i);
        return l;
    }
    
}
