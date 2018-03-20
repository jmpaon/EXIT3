package exit3.model;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class ValidatedMatrix<T> extends AbstractMatrix<T> {

    private List<Pair<Predicate<T>, ModelException>> valueValidityConditions;

    public ValidatedMatrix(int rowCount, int columnCount, Collection<T> values) throws ModelException {
        super(rowCount, columnCount, values);
        this.valueValidityConditions = new LinkedList<>();
        this.validate();
    }

    public boolean validate() throws ModelException {
        for(Pair<Predicate<T>, ModelException> test : valueValidityConditions) {
            if(! values.stream().anyMatch(test.left)) {
                throw test.right;
            }
        }
        return true;
    }

    public void addValidityCondition(Predicate<T> condition, ModelException conditionNotMetException) {
        assert Objects.nonNull(condition);
        if(Objects.isNull(conditionNotMetException)) conditionNotMetException = new ModelException("Value fails a defined test with no error message");
        Pair<Predicate<T>, ModelException> pair = new Pair<>(condition, conditionNotMetException);
        valueValidityConditions.add(pair);
    }

    @Override
    public void set(int row, int column, T value) {
        for (Pair<Predicate<T>, ModelException> p : valueValidityConditions) {
            assert p.left.test(value) : p.right;
        }
        super.set(row, column, value);
    }

}
