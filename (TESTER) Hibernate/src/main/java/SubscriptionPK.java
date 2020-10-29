import java.io.Serializable;
import java.util.Objects;

public class SubscriptionPK implements Serializable {

    protected int student_id;
    protected int course_id;

    public SubscriptionPK() {}

    public SubscriptionPK(int student_id, int course_id) {
        this.student_id = student_id;
        this.course_id = course_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubscriptionPK that = (SubscriptionPK) o;
        return student_id == that.student_id &&
                course_id == that.course_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(student_id, course_id);
    }
}
