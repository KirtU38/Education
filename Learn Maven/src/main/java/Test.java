import java.util.Objects;

public class Test {

    Integer id = 1;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Test test = (Test) o;
        return id == test.id;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
