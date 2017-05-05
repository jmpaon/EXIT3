/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exit3.matrices;

import exit3.chains.VariableChain;
import java.util.List;

/**
 *
 * @author jmpaon
 */
public interface ComputableMatrix extends ReadableMatrix<Double> {

    public double impactOfChain(VariableChain chain);
    
    public double impactOfChain(List<Integer> chain);
    

    
}
