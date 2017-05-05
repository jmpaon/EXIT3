/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exit3.chains;

import exit3.matrices.ReadableMatrix;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.IntStream;

/**
 *
 * @author jmpaon
 */
public class VariableChain {
    
    protected final List<Integer> indices;
    public final ReadableMatrix matrix;
    
    public VariableChain(ReadableMatrix matrix, List<Integer> indices) {
        Objects.requireNonNull(matrix, "matrix argument is null");
        Objects.requireNonNull(indices, "index list is null");
        if(new HashSet(indices).size() != indices.size()) throw new IllegalArgumentException("Indices contain duplicate index values: " + indices);
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
    
    public List<Integer> getChainValues() {
        return matrix.chainValues(indices);
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

    /**
     * Returns a list of the possible 1-variable expansions of this impact chain
     * @return
     *
    @Override
    */
    public List<VariableChain> expansions() {
        List<Integer> possibleIndices = range(matrix.varCount());
        possibleIndices.removeAll(this.indices);
        List<VariableChain> expandedByOne = new LinkedList<>();
        for(int addedIndex : possibleIndices) {
            List<Integer> expandedIndices = new LinkedList<>();
            for(Integer i : indices) expandedIndices.add(i);
            // Collections.copy(expandedIndices, indices);
            expandedIndices.add(expandedIndices.size()-1, addedIndex);
            expandedByOne.add(new VariableChain(this.matrix, expandedIndices));
        }
        return expandedByOne;
    }

    
}
