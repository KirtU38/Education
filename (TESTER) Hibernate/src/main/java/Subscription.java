import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@IdClass(SubscriptionPK.class)
@Table(name = "Subscriptions")
public class Subscription implements Serializable {

    @Id
    private int student_id;

    @Id
    private int course_id;

    @Column(name = "subscription_date")
    private Timestamp subscriptionDate;


    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public Timestamp getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Timestamp subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }
}
