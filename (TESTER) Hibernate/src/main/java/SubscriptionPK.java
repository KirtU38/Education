import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SubscriptionPK implements Serializable {

    @Column(name = "student_id")
    protected int studentId;

    @Column(name = "course_id")
    protected int courseId;

    public SubscriptionPK() {}

    public SubscriptionPK(int studentId, int courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubscriptionPK that = (SubscriptionPK) o;
        return studentId == that.studentId &&
                courseId == that.courseId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, courseId);
    }
}
