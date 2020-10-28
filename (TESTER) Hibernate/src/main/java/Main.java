import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Main {

    public static void main(String[] args) {

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        Session session = sessionFactory.openSession();

        /*Course course = session.get(Course.class, 3);
        String courseName = course.getName();
        int courseStudentsCount = course.getStudentCount();
        System.out.printf("Курс \"%s\" - Обучается %d студентов", courseName, courseStudentsCount);

        Student student = session.get(Student.class, 1);
        String studentName = student.getName();
        int age = student.getAge();
        String studentRegDate = student.getRegistrationDate().toString();
        System.out.printf("%s - %d лет - Дата регистрации %s",studentName, age, studentRegDate);*/

        Transaction transaction = session.beginTransaction();

        Course course = session.get(Course.class, 1);
        String teacher_name = course.getTeacher().getName();
        System.out.println(teacher_name);


        transaction.commit();
        sessionFactory.close();
    }
}
