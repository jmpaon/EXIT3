package exit3.model;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

public class AbstractMatrix<T> {

    public final int rowCount;
    public final int columnCount;
    public final List<T> values;


    public AbstractMatrix(int rowCount,
                          int columnCount,
                          Collection<T> values)
    {

        assert rowCount > 0;
        assert columnCount > 0;
        assert values.size() == rowCount*columnCount;

        this.rowCount = rowCount;
        this.columnCount = columnCount;
        this.values = new ArrayList(values);

    }

    /**
     * Print the matrix contents to stdout.
     */
    public void show() {
        StringBuilder s = new StringBuilder();
        s.append(String.format("Matrix with %d rows and %d columns\n", rowCount, columnCount));
        s.append(values.toString()).append("\n");


        int currentColumn = 0;
        for(T t : values) {
            if(++currentColumn > columnCount) {
                s.append("\n");
                currentColumn=1;
            }
            if(t != null) {s.append(t.toString()); }
            else          {s.append("--");}
            s.append("\t");
        }
        System.out.println(s.toString());
    }


    /**
     * Gets the value in matrix entry (row,column)
     * @param row Row of entry
     * @param column Column of entry
     * @return entry at (row,column)
     */
    public T get(int row, int column) {
        return values.get((row-1)*columnCount + (column-1));
    }


    /**
     * Sets the value in entry(<b>row,column</b>) to <b>value</b>
     * @param row
     * @param column
     * @param value
     */
    public void set(int row, int column, T value) {
        values.set((row-1)*columnCount + (column-1), value);
    }

}
