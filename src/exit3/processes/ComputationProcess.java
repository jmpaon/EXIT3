/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exit3.processes;

import exit3.matrices.ComputableMatrix;

/**
 *
 * @author juha
 */
public interface ComputationProcess {
    public ComputationResult compute(ComputableMatrix matrix);
}
