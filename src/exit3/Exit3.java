/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exit3;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;


/**
 *
 * @author jmpaon
 */
public class Exit3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        List<List<Integer>> l1 = Arrays.asList(Arrays.asList(1,2,3), Arrays.asList(1,2,3), Arrays.asList(1,2,3) );
        List<List<Integer>> l2 = Arrays.asList(Arrays.asList(4,5,6), Arrays.asList(6,7,8), Arrays.asList(8,9,13) );
        List<List<Number>> ln = Arrays.asList(Arrays.asList(1.,2.,3.), Arrays.asList(1.,2.,3.), Arrays.asList(1.,2.,3.) );
        List<List<Number>> lin = Arrays.asList(Arrays.asList(1,2,3), Arrays.asList(1,2,3), Arrays.asList(1,2,3) );
        List<List<Double>> ld = Arrays.asList(Arrays.asList(1d,-2d,-3d), Arrays.asList(1d,2d,7d), Arrays.asList(1d,2d,3d) );
        List<List<Double>> ld5 = Arrays.asList(
                Arrays.asList(1d,-2d,-3d,2d,4d),
                Arrays.asList(1d,-2d,-3d,2d,4d),
                Arrays.asList(1d,-2d,-3d,2d,4d),
                Arrays.asList(1d,-2d,-3d,2d,4d),
                Arrays.asList(1d,-2d,-3d,2d,4d));
        
        List<String> s1 = Arrays.asList("Mem","Moo","Mau");
        List<String> s5 = Arrays.asList("Mem","Moo","Mau","Mur","Miu");
        
        SquareMatrix<Integer> m  = new SquareMatrix<>(Arrays.asList("Mem","Moo","Mau"));
        SquareMatrix<Integer> m3 = new SquareMatrix<>(3, Arrays.asList("vara","varb","varc"), (x -> x <= 7), l1);
        SquareMatrix<Integer> m2 = new SquareMatrix<>(Arrays.asList("Mem","Moo","Mau"));
        
        DoubleMatrix dm = new DoubleMatrix(s5);
        dm.setAll(ld5);
        p(dm);
        
        ReadableMatrix rm = dm;
        ComputableMatrix cm = dm;
        ImpactChain ic = new ImpactChain(cm, 1,2);
        p(ic);
        p(ic.impact());
        p(ic.expansions());
        

                
    }
    
    public static void test1() {
        
        List<List<Integer>> l1 = Arrays.asList(Arrays.asList(1,2,3), Arrays.asList(1,2,3), Arrays.asList(1,2,3) );
        List<List<Integer>> l2 = Arrays.asList(Arrays.asList(4,5,6), Arrays.asList(6,7,8), Arrays.asList(8,9,13) );
        List<List<Number>> ln = Arrays.asList(Arrays.asList(1.,2.,3.), Arrays.asList(1.,2.,3.), Arrays.asList(1.,2.,3.) );
        List<List<Number>> lin = Arrays.asList(Arrays.asList(1,2,3), Arrays.asList(1,2,3), Arrays.asList(1,2,3) );
        List<List<Double>> ld = Arrays.asList(Arrays.asList(1d,-2d,-3d), Arrays.asList(1d,2d,7d), Arrays.asList(1d,2d,3d) );
        
        List<String> s1 = Arrays.asList("Mem","Moo","Mau");
        
        SquareMatrix<Integer> m  = new SquareMatrix<>(Arrays.asList("Mem","Moo","Mau"));
        SquareMatrix<Integer> m3 = new SquareMatrix<>(3, Arrays.asList("vara","varb","varc"), (x -> x <= 7), l1);
        SquareMatrix<Integer> m2 = new SquareMatrix<>(Arrays.asList("Mem","Moo","Mau"));
        
        DoubleMatrix dm = new DoubleMatrix(s1);
        dm.setAll(ld);
        p(dm);
        
        ReadableMatrix rm = dm;
        p("Readable matrix : " + rm.get(1, 1));
        p("Values of chainIndices: " + rm.chainValues(Arrays.asList(1,2,3)));
        
        // System.out.println(m3);
        
        m3.set(1, 1, 4);
        m3.set(1, 2, -4);
        
        m3.identifiers.set(2, "purr");
        
        NumberMatrix<Double> d1 = new NumberMatrix<Double>(3, s1, x -> Math.abs((Double) x) <= 7., ld);
        NumberMatrix<Double> d2 = d1.copy();
        
        Function<List<Double>,Double> f = new Function<List<Double>, Double>() {
            @Override
            public Double apply(List<Double> t) {
                double sum=0;
                for(double x : t) sum += x;
                return sum;
            }
        };
        
        
        
        Double ddd = d2.summary(f);
        double dd2 = d2.summary( (List<Double> x) -> { double sum=0; for(double xx : x) sum += Math.abs(xx); return sum; } );     
        p(d2);
        p(dd2);        
    }
    
    public static void p(Object o) {
        System.out.println(o);
    }
    
}
