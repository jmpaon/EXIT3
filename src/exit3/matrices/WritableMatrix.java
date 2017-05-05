package exit3.matrices;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jmpaon
 * @param <V>
 */
public interface WritableMatrix<V> extends ReadableMatrix<V> {
    
    /**
     * Sets the value in matrix entry (row,column) to <b>value</b>
     * @param row Row index
     * @param column Column index
     * @param value New value
     */
    public void set(int row, int column, V value);
    
    /**
     * Returns a writing iterator for a writable matrix
     * @return A writing iterator
     */
    public SquareMatrix.WritingIterator<V> writingIterator();
    
}
