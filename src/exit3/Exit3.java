/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exit3;

import java.util.Arrays;

/**
 *
 * @author jmpaon
 */
public class Exit3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SquareMatrix<Integer> m = new SquareMatrix<>(Arrays.asList("Mem","Moo","Mau"));
        System.out.println(m.identifiers.toString());
        m.values.stream().forEach(x -> System.out.println(x));
        System.out.println(m.values.size());
        // System.out.println(m.toString());
        
    }
    
}
