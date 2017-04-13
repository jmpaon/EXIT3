/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exit3;

import java.util.List;

/**
 *
 * @author jmpaon
 */
public class ImpactChain implements ComputableChain {
    
    /* Will be abstract */
    
    private final List<Integer> indices;
    private final ComputableMatrix matrix;
    
    public ImpactChain() {
        throw new UnsupportedOperationException();
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
