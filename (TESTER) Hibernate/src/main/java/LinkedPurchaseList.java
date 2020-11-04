import javax.persistence.*;

@Entity
@Table(name = "LinkedPurchaseList")
public class LinkedPurchaseList {

    @EmbeddedId
    private LinkedPurchaseListCK linkedPurchaseListCK;

    @MapsId("studentId")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;

    @MapsId("courseId")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private Course course;





    public LinkedPurchaseList() {}

    public LinkedPurchaseList(LinkedPurchaseListCK linkedPurchaseListCK, Student student, Course course) {
        this.linkedPurchaseListCK = linkedPurchaseListCK;
        this.student = student;
        this.course = course;
    }

    public LinkedPurchaseListCK getLinkedPurchaseListCK() {
        return linkedPurchaseListCK;
    }

    public void setLinkedPurchaseListCK(LinkedPurchaseListCK linkedPurchaseListCK) {
        this.linkedPurchaseListCK = linkedPurchaseListCK;
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
