/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exit3;

import exit3.matrices.*;
import exit3.computations.ImpactMatrixComputation;
import exit3.model.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;


/**
 *
 * @author jmpaon
 */
public class Exit3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        List<Integer> vals = Arrays.asList(1,2,3,3,2,3,2,1,1,1,2,3,4,5,6,3);
        p(vals.size());

        AbstractMatrix<Integer> ami = new AbstractMatrix<Integer>(4,4,vals);


        p(ami.values);
        p(ami.values.size());
        ami.show();

        ValidatedMatrix<Integer> vmi = new ValidatedMatrix<Integer>(4,4,vals);
        vmi.addValidityCondition(v -> Math.abs(v) <= 40, new ModelException("Value exceeds maximum value 40"));
        vmi.addValidityCondition(v -> v != 16, new ModelException("Value 16 is forbidded in this matrix"));



        vmi.set(1,2,17);
        vmi.set(2,1,27);
        vmi.set(3,2,37);
        vmi.set(4,3,40);
        vmi.set(2,4,33);

        p(vmi.validate());

                
    }

    public static boolean testNoSelfLoops(ValidatedMatrix<Integer> m) {
        for(int i=1;i<=m.rowCount;i++)
            if(Objects.nonNull(m.get(i,i)) && m.get(i,i) != 0) return false;
        return true;
    }


    public static void test1(String[] args) {
        List<List<Integer>> l1 = Arrays.asList(Arrays.asList(1,2,3), Arrays.asList(1,2,3), Arrays.asList(1,2,3) );
        List<List<Integer>> l2 = Arrays.asList(Arrays.asList(4,5,6), Arrays.asList(6,7,8), Arrays.asList(8,9,13) );
        List<List<Number>> ln = Arrays.asList(Arrays.asList(1.,2.,3.), Arrays.asList(1.,2.,3.), Arrays.asList(1.,2.,3.) );
        List<List<Number>> lin = Arrays.asList(Arrays.asList(1,2,3), Arrays.asList(1,2,3), Arrays.asList(1,2,3) );
        List<List<Double>> ld = Arrays.asList(Arrays.asList(1d,-2d,-3d), Arrays.asList(1d,2d,7d), Arrays.asList(1d,2d,3d) );
        List<List<Double>> ld5 = Arrays.asList(
                Arrays.asList( 0d, 1d, 1d, 9d, 1d),
                Arrays.asList(-1d, 0d, 1d, 1d, 1d),
                Arrays.asList(-2d, 1d, 0d, 2d, 1d),
                Arrays.asList(-3d, 1d, 1d, 0d, 1d),
                Arrays.asList(-7d, 1d, 1d, 1d, 0d));

        List<String> s1 = Arrays.asList("Mem","Moo","Mau");
        List<String> s5 = Arrays.asList("Mem","Moo","Mau","Mur","Miu");

        SquareMatrix<Integer> m  = new SquareMatrix<>(Arrays.asList("Mem","Moo","Mau"));
        SquareMatrix<Integer> m3 = new SquareMatrix<>(3, Arrays.asList("vara","varb","varc"), (x -> x <= 7), l1);
        SquareMatrix<Integer> m2 = new SquareMatrix<>(Arrays.asList("Mem","Moo","Mau"));

        ImpactMatrix dm = new ImpactMatrix(s5);
        dm.setAll(ld5);

        ReadableMatrix<Double> rm = dm.copy();
        p(rm);
        p(dm);

        p(dm.absMax(), dm.averageDistanceFromZero(), dm.averageDistanceFrom(-7)  );

        WritableMatrix wm = ImpactMatrixComputation.normalize.byDistanceFrom0(dm);
        p(wm);

    }

    
    public static void p(Object o) {
        System.out.println(o);
    }
    
    public static void p(Object... os) {
        for(Object o : os) p(o);
    }
    
}


