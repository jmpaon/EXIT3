/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exit3.matrices;

import java.util.Collection;
import java.util.List;

/**
 *
 * @author jmpaon
 */
public interface ReadableMatrix<V> {
    /**
     * Read the value of a matrix entry
     * @param row Index of row of the entry
     * @param column Index of column of the entry
     * @return V: Value
     */
    public V get(int row, int column);
    
    /**
     * Get the identifier of a matrix variable
     * @param index Index of the variable
     * @return String: Name/identifier of the variable at index <code>index</code>
     */
    public String getId(int index);
    
    /**
     * 
     * @param chainIndices
     * @return 
     */
    public List<V> chainValues(List<Integer> chainIndices);
    
    public int varCount();
    
    /**
     * Tests the validity of <b>index</b> and throws <code>NoSuchElementException</code> if index is invalid
     * @param index 
     */
    public void testIndex(int index);
    
    /**
     * Returns a reading iterator for a readable matrix
     * @return A reading iterator
     */
    public SquareMatrix.ReadingIterator<V> readingIterator();
    
}
