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
public interface ComputableChain {
    
    /**
     * Computes the impact of the impact chain.
     * @return Double: Impact value of the impact chain
     */
    public Double impact();
    
    /**
     * Returns a list of the possible 1-variable expansions of this impact chain
     * @return 
     */
    public List<ComputableChain> expansions();
    
    
    
}
