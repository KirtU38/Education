import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "PurchaseList")
public class PurchaseList {

    @EmbeddedId
    private PurchaseListCK purchaseListCK;

    private  int price;

    @Column(name = "subscription_date")
    private Timestamp subscriptionDate;

    @MapsId("courseName")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "course_name", referencedColumnName = "name")
    private Course course;

    @MapsId("studentName")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "student_name", referencedColumnName = "name")
    private Student student;




    public PurchaseList() {}

    public PurchaseList(PurchaseListCK purchaseListCK) {
        this.purchaseListCK = purchaseListCK;
    }

    public PurchaseListCK getPurchaseListCK() {
        return purchaseListCK;
    }

    public void setPurchaseListCK(PurchaseListCK purchaseListCK) {
        this.purchaseListCK = purchaseListCK;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Timestamp getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Timestamp subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
