/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exit3;

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
public class ImpactChain implements ComputableChain {
    
    /* Will be abstract */
    
    private final List<Integer> indices;
    private final ComputableMatrix matrix;
    
    public ImpactChain(ComputableMatrix matrix, List<Integer> indices) {
        if(Objects.isNull(matrix) || Objects.isNull(indices) ) throw new NullPointerException();
        if(indices.isEmpty()) throw new IllegalArgumentException();
        
        this.matrix = matrix;
        this.indices = indices;
        
    }
    
    public ImpactChain(ComputableMatrix matrix, Integer[] indices) {
        this(matrix, Arrays.asList(indices) );
    }
    
    public ImpactChain(ComputableMatrix matrix, int... indices) {
        List<Integer> list = new LinkedList<Integer>();
        for(int index : indices) list.add(index);
        if(Objects.isNull(matrix) || Objects.isNull(indices) ) throw new NullPointerException();
        if(list.isEmpty()) throw new IllegalArgumentException();

        this.matrix = matrix;
        this.indices = list;
        
    }
    
    
    @Override
    public Double impact() {
        double relatingValue = matrix.relatingValue();
        double impact = 1;
        List<Double> directImpactValues = matrix.chainValues(this.indices);
        for(Double d : directImpactValues) impact *= ( d / relatingValue );
        return impact;
    }
    

    @Override
    public List<ComputableChain> expansions() {
        List<Integer> possibleIndices = range(matrix.varCount());
        possibleIndices.removeAll(this.indices);
        List<ComputableChain> expandedByOne = new LinkedList<>();
        for(int addedIndex : possibleIndices) {
            List<Integer> expandedIndices = new LinkedList<Integer>();
            for(Integer i : indices) expandedIndices.add(i);
            // Collections.copy(expandedIndices, indices);
            expandedIndices.add(expandedIndices.size()-1, addedIndex);
            expandedByOne.add(new ImpactChain(this.matrix, expandedIndices));
        }
        return expandedByOne;
    }
    
    
    @Override
    public String toString() {
        return "Impact chain with indices " + this.indices.toString();
    }
    
    
    static List<Integer> range(int to) {
        //return Arrays.asList(IntStream.range(1, to).toArray());
        List l = new LinkedList();
        int i=1;
        while(i++<to) l.add(i);
        return l;
    }
    
}
