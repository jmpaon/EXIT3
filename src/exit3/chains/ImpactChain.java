/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exit3.chains;

import exit3.matrices.ComputableMatrix;
import static exit3.chains.VariableChain.range;
import exit3.matrices.ReadableMatrix;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 *
 * @author juha
 */
public class ImpactChain extends VariableChain {
    
    private final ReadableMatrix matrix;


    public ImpactChain(ReadableMatrix matrix, List<Integer> indices) {
        super(matrix, indices);
        this.matrix = matrix;
    }

    public ImpactChain(ReadableMatrix matrix, Integer[] indices) {
        super(matrix, indices);
        this.matrix = matrix;
    }
    

    public Double impact() {
        double relatingValue = 5d;
        double impact = 1;
        List<Double> directImpactValues = matrix.chainValues(this.indices);
        for(Double d : directImpactValues) impact *= ( d / relatingValue );
        return impact;
    } 
    
    
}
