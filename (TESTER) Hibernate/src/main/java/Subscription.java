import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "Subscriptions")
public class Subscription implements Serializable {

    @EmbeddedId
    private SubscriptionPK subscriptionPK;

    @Column(name = "subscription_date")
    private Timestamp subscriptionDate;

    @MapsId("studentId")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;

    @MapsId("courseId")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private Course course;



    public Timestamp getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Timestamp subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    public SubscriptionPK getSubscriptionPK() {
        return subscriptionPK;
    }

    public void setSubscriptionPK(SubscriptionPK subscriptionPK) {
        this.subscriptionPK = subscriptionPK;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
