/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exit3.matrices;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 *
 * @author jmpaon
 * @param <V> Type of values in the matrix
 * 
 */
public class SquareMatrix<V> implements WritableMatrix<V> {
    
    public final int varCount;
    protected List<List<V>> values;
    protected final List<String> identifiers;
    protected final Predicate<V> valueValidator;
    
    /**
     * Constructor
     * @param varCount Number of variables in the matrix
     * @param identifiers List of variable names or identifiers
     * @param valueValidator Function that tests the validity of matrix values and returns true if value is valid for this matrix
     * @param values Values in the matrix: 
     * <table style="border-collapse:collapse;border-spacing:0"><tr><th style="font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal"></th><th style="font-family:Arial, sans-serif;font-size:14px;font-weight:bold;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal">A</th><th style="font-family:Arial, sans-serif;font-size:14px;font-weight:bold;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal">B</th></tr><tr><td style="font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;font-weight:bold">A</td><td style="font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal">11</td><td style="font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal">12</td></tr><tr><td style="font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;font-weight:bold">B</td><td style="font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal">21</td><td style="font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal">22</td></tr></table>
     * should be entered as <code>List(List(11,12),List(21,22))</code>
     */
    public SquareMatrix(int varCount, List<String> identifiers, Predicate<V> valueValidator, List<List<V>> values) {
        
        if(varCount < 2) throw new IllegalArgumentException("Matrix must have at least 2 variables");
        if(Objects.isNull(identifiers)) {
            identifiers = generateIdentifiers(varCount);
        } else {
            if(identifiers.size() < 2) throw new IllegalArgumentException("Matrix must have at least 2 variables; identifiers has only " + identifiers.size() + " variables");
            if(identifiers.size() != varCount) throw new IllegalArgumentException("Identifier count is not equal to variable count");
            if(identifiers.stream().anyMatch(x -> Objects.isNull(x) || x.isEmpty() )) throw new IllegalArgumentException("Null-valued or 0-length identifier string");
            if(identifiers.stream().distinct().count() != identifiers.size()) throw new IllegalArgumentException("Duplicate identifiers: " + identifiers);
        }
        
        this.identifiers = Collections.unmodifiableList(identifiers);
        this.varCount = varCount;
        this.valueValidator = valueValidator;
        
        this.values = new ArrayList<>(varCount);
        for(int i=0;i<varCount;i++){
            List<V> list = new ArrayList<>();
            for(int ii=0;ii<varCount;ii++)
                list.add(null);
            this.values.add(list);
        }
        
        if(Objects.isNull(values)) return;
        setAll(values);

    }
    
    
    /**
     * Constructor.
     * Initializes matrix values to <b>null</b>.
     * @param identifiers List of variable names or identifiers
     * @param valueValidator Function that tests the validity of matrix values and returns true if value is valid for this matrix
     */
    public SquareMatrix(List<String> identifiers, Predicate<V> valueValidator) {
        this(identifiers.size(), identifiers, valueValidator, null);
    }    
    
    
    /**
     * Constructor.
     * Initializes matrix values to <b>null</b> and generates numbered identifiers for matrix variables.
     * @param varCount Number of variables in the matrix
     */
    public SquareMatrix(int varCount) {        
        this(generateIdentifiers(varCount), (Objects::nonNull));
    }
    
    
    /**
     * Constructor.
     * Initializes matrix values to <b>null</b>.
     * @param identifiers List of variable names or identifiers
     */
    public SquareMatrix(List<String> identifiers) {
        this(identifiers, (Objects::nonNull));
    }
    
    
    /**
     * Generates a list of identifiers
     * @param count Number of identifiers needed
     * @return List of identifiers
     */
    private static List<String> generateIdentifiers(int count) {
        List<String> ids = new ArrayList<>();
        int id_number=1;
        while(count-- > 0) ids.add("Variable " + id_number++);
        return ids;
    }
    
    
    /**
     * Sets the value at the matrix entry (row,column).
     * If <b>valueValidator</b> is set, the value is tested with it and <code>NoSuchElementException</code>.
     * will be thrown if test fails.
     * @param row Row of entry
     * @param column Column of entry
     * @param value New value
     */
    @Override
    public void set(int row, int column, V value) {
        testIndex(row, String.format("Illegal row value (%d) for matrix with %d variables", row, varCount ));
        testIndex(column, String.format("Illegal column value (%d) for matrix with %d variables", column, varCount ));
        if( Objects.nonNull(this.valueValidator)) {
            if(! valueValidator.test(value)) throw new IllegalArgumentException("Invalid value inserted: " + value.toString() );
        }
        this.values.get(row-1).set(column-1, value);
    }
    
    
    /**
     * Sets all values in the matrix.
     * @param values List of rows represented by list of row values
     */
    public final void setAll(List<List<V>> values) {
        if(values.size() != this.varCount || values.stream().anyMatch(x -> x.size() != this.varCount))
            throw new IllegalArgumentException("Dimensions of the value array do not match the target matrix dimensions");
        
        if(values.stream().anyMatch(row -> row.stream().anyMatch(value -> !valueValidator.test(value))))
            throw new IllegalArgumentException(String.format("Input values are not valid"));

        for(int row=0;row<varCount;row++)
            for(int col=0;col<varCount;col++) {
                this.set(row+1, col+1, values.get(row).get(col));
            }
    }
    
    
    /**
     * @return Variable count of the matrix
     */
    @Override
    public int varCount() { return this.varCount; }    
    
    
    /**
     * Returns the value in matrix entry <b>(row,column)</b>.
     * @param row Row index
     * @param column Column index
     * @return Value in matrix entry <b>(row,column)</b>.
     */
    @Override
    public V get(int row, int column) {
        testIndex(row, String.format("Illegal row value (%d) for matrix with %d variables", row, varCount ));
        testIndex(column, String.format("Illegal column value (%d) for matrix with %d variables", column, varCount ));
        return this.values.get(row-1).get(column-1);
    }
    
    
    /**
     * Returns the identifier of variable with index <b>index</b>.
     * @param index Variable index
     * @return Identifier of variable with index <b>index</b>.
     */
    @Override
    public String getId(int index) {
        return identifiers.get(index-1);
    }
    
    
    /** @return <code>true</code> if matrix has null values, <code>false</code> otherwise. */
    public boolean containsNullValues() {
        return values.stream().anyMatch(x -> x.isEmpty() || x.stream().anyMatch(xx -> Objects.isNull(xx)));
    }
    
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<varCount;i++) {
            sb.append(getId(i+1)).append(" : ");
            for(V v : values.get(i)) sb.append(Objects.isNull(v) ? "[null]" :  v.toString()).append("; ");
            sb.append("\n");
        }
        return sb.toString();
    }

    
    /**
     * Returns a list of values from the matrix, with the logic that
     * <b>chainIndices</b> with values (1,2,3) will return a list of values in matrix entries
     * (1,2) and (2,3).
     * <b>chainIndices</b> with values (3,6,2,4) returns a list of values in matrix entries
     * (3,6), (6,2) and (2,4).
     * @param chainIndices Indices of variables in the returned value chain
     * @return Requested matrix entry values in a chain
     */
    @Override
    public List<V> chainValues(List<Integer> chainIndices) {
        List<V> chain = new LinkedList<>();
        for(int i=0;i<chainIndices.size()-1;i++) {
            chain.add( this.get(chainIndices.get(i), chainIndices.get(i+1)) );
        }
        return chain;
    }
    
    /**
     * Transforms the matrix using <b>transformer</b> function.
     * @param transformer Function returning a <code>WritableMatrix</code>.
     * @return 
     */
    public WritableMatrix<V> transform(Function<ReadableMatrix<V>, WritableMatrix<V>> transformer) {
        return transformer.apply(this);
    }
    
    
    /**
     * Returns a flat list of all values in the matrix.
     * @return List of values in the matrix.
     */
    public List<V> flatValues() {
        return this.collect((x,y)-> true );
    }
    
    
    /**
     * 
     * @param condition
     * @return 
     */
    public List<V> collect(BiPredicate<ReadingIterator<V>, V> condition) {
        ReadingIterator<V> it = readingIterator();
        List<V> collected = new LinkedList();
        while(it.hasNext()) {
            V v = it.next();
            if(condition.test(it, v)) collected.add(v);
        }
        return collected;
    }        
    
    
    /**
     * Tests validity of an index.
     * Throws <code>NoSuchElementException</code> if index is not valid.
     * @param index 
     */
    @Override
    public void testIndex(int index) {
        testIndex(index, String.format("Matrix variable count is %d and used index is %d", varCount, index));
    }
    
    /**
     * Tests validity of an index.
     * Throws <code>NoSuchElementException</code> with error message <b>errorMessage</b> if index is not valid.
     * @param index 
     * @param errorMessage Error message for case of invalid index
     */    
    public void testIndex(int index, String errorMessage) {
        if(index <= 0 || index > varCount) throw new NoSuchElementException(
                String.format("Matrix variable count is %d and used index is %d", varCount, index));        
    }

    
    /**
     * Tests validity of variable indices
     * @param indices Collection of indices to test
     */
    public void testIndex(Collection<Integer> indices) {indices.forEach(x -> testIndex(x));}
    public void testIndex(Integer[] indices) { testIndex(Arrays.asList(indices));}
    public void testIndex(int[] indices) { for(int x : indices) testIndex(x);}    
    
    
    /**
     * Returns an iterator that can read matrix values.
     * Values are iterated so that all values of each row are returned before moving to the next row.
     * @return A reading iterator
     */
    @Override
    public ReadingIterator readingIterator() { return new ReadingIterator(this); }
    
    
    /**
     * Returns an iterator with replace capability for matrix 
     * @return 
     */
    @Override
    public WritingIterator writingIterator() { return new WritingIterator(this); }
    

    /**
     * An iterator that can only read matrix values.
     * @param <V> Value type of matrix
     */
    public class ReadingIterator<V> implements Iterator<V> {

        private int row;
        private int column;
        private final ReadableMatrix<V> matrix;
        
        ReadingIterator(ReadableMatrix<V> matrix) {
            Objects.requireNonNull(matrix, "matrix argument is null");
            this.row = 1;
            this.column = 0;
            this.matrix = matrix;
        }
        
        /** @return Current matrix row of the iterator */
        public int row() { return row; }
        
        /** @return Current matrix column of the iterator */
        public int column() { return column; }        
        
        @Override
        public String toString() {return String.format("Reading iterator for %d-variable matrix at position (%d,%d)",matrix.varCount(),row(),column());}
        
        @Override
        public boolean hasNext() {
            return row < matrix.varCount() || (row == matrix.varCount() && column < matrix.varCount());
        }

        @Override
        public V next() {
            if(this.hasNext()) {
                if(column < matrix.varCount()) column++;
                else { column = 1 ; row++ ; }
                return matrix.get(row, column);
            }
            throw new NoSuchElementException();
        }
        
        /**
         * Returns the matrix value in the current position of the iterator
         * @return Value at the current position of iterator
         */
        public V get() {
            return matrix.get(row,column);
        }
        
    }
    
    /**
     * An iterator that can read and write matrix values
     * @param <V> Value type of matrix
     */
    public class WritingIterator<V> extends ReadingIterator<V> {
        
        private int row;
        private int column;
        private final WritableMatrix<V> matrix;
        
        public WritingIterator(WritableMatrix<V> matrix) {
            super(matrix);
            this.row = 1;
            this.column = 0;
            this.matrix = matrix;
        }
        
        @Override
        public String toString() {return String.format("Writing iterator for %d-variable matrix at position (%d,%d)",matrix.varCount(),row(),column());}


        /**
         * Replaces the value at the matrix entry currently iterated
         * @param value New value
         */
        public void replace(V value) {
            this.toString();
            if(this.column() < 1 || this.column() > matrix.varCount()  ) throw new NoSuchElementException();
            this.matrix.set(this.row(), this.column(), value);
        }
        
    }
    
    
}
