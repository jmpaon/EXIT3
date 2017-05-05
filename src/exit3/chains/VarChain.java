/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exit3.chains;

import exit3.matrices.ReadableMatrix;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author juha
 */
public class VarChain extends LinkedList<Integer> {
    
    public final ReadableMatrix matrix;
    
    public VarChain(ReadableMatrix matrix, List<Integer> indices) {
        Objects.requireNonNull(matrix);
        Objects.requireNonNull(indices);
        indices.stream().forEach(x -> matrix.testIndex(x));
        if(new HashSet(indices).size() != indices.size()) throw new IllegalArgumentException("Duplicate values in indices");
        this.matrix = matrix;
        this.addAll(indices);
    }
    
    
    
    
}
