/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exit3.chains;

import exit3.matrices.ComputableMatrix;
import static exit3.chains.VariableChain.range;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author juha
 */
public class ImpactChain extends VariableChain implements ComputableChain {
    
    private ComputableMatrix matrix;

    public ImpactChain(ComputableMatrix matrix, List<Integer> indices) {
        super(matrix, indices);
        this.matrix = matrix;
    }

    public ImpactChain(ComputableMatrix matrix, Integer[] indices) {
        super(matrix, indices);
        this.matrix = matrix;
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
            List<Integer> expandedIndices = new LinkedList<>();
            for(Integer i : indices) expandedIndices.add(i);
            // Collections.copy(expandedIndices, indices);
            expandedIndices.add(expandedIndices.size()-1, addedIndex);
            expandedByOne.add(new ImpactChain(this.matrix, expandedIndices));
        }
        return expandedByOne;
    }
    
    
}
