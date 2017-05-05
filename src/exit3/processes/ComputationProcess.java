/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exit3.processes;

import exit3.matrices.*;
import java.util.function.Function;

/**
 *
 * @author juha
 */
public interface ComputationProcess {
    public ComputationResult compute(ComputableMatrix matrix, Function<ComputableMatrix, SquareMatrix> computer);
}
