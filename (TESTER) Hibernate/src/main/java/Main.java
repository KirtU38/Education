import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        //session.createSQLQuery("ALTER TABLE Students MODIFY name VARCHAR(500)").executeUpdate(); // Это чтобы PurchaseList.student_name привязалось к Students.name

        // Вариант 1   - Заполнить таблицу LinkedPurchaseList через PurchaseList
        /*CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<PurchaseList> query = builder.createQuery(PurchaseList.class);
        Root<PurchaseList> root = query.from(PurchaseList.class);
        query.select(root);
        List<PurchaseList> purchaseListResultSet = session.createQuery(query).getResultList();

        for (PurchaseList purchase : purchaseListResultSet) {
            int studentId = purchase.getStudent().getId();
            int courseId = purchase.getCourse().getId();
            Student student = purchase.getStudent();
            Course course = purchase.getCourse();

            LinkedPurchaseList plist = new LinkedPurchaseList(
                    new LinkedPurchaseListCK(studentId, courseId),student,course);
            session.save(plist);
        }*/


        // Вариант 2   - Заполнить таблицу LinkedPurchaseList через PurchaseList
        /*session.createSQLQuery("INSERT INTO LinkedPurchaseList(course_id, student_id)\n" +
                "SELECT Students.id as student_id, Courses.id as course_id \n" +
                "FROM Courses\n" +
                "JOIN PurchaseList ON PurchaseList.course_name = Courses.name\n" +
                "JOIN Students ON Students.name = PurchaseList.student_name").executeUpdate();*/



        PurchaseListCK purchaseListCK = new PurchaseListCK("Абакумов Казимир", "Figma");
        PurchaseList purchaseList = session.get(PurchaseList.class, purchaseListCK);
        System.out.println(purchaseList.getPurchaseListCK().getCourseName() + " - "
                + purchaseList.getPurchaseListCK().getStudentName() + " - " + purchaseList.getPrice() + " рублей");
        System.out.println("Запрос к PurchaseList вывести кто, за сколько и какой курс купил");

        SubscriptionCK subscriptionCK = new SubscriptionCK(2, 11);
        Subscription subscription = session.get(Subscription.class, subscriptionCK);
        System.out.println(subscription.getStudent().getName() + " - " + subscription.getCourse().getName());
        System.out.println("Запрос к Subscription вывести кто и на какой курс подписан (ManyToOne к Students и Courses)");

        Student student = session.get(Student.class, 1);
        List<Subscription> subscriptions = student.getSubscriptions();
        for (Subscription sub : subscriptions) {
            System.out.println(sub.getCourse().getName());
        }
        System.out.println("Запрос к Student вывести все курсы на которых учится студент(OneToMany к Subscription)");

        Teacher teacher = session.get(Teacher.class, 10);
        List<Course> courses = teacher.getCourses();
        for (Course course : courses) {
            System.out.println(course.getName());
        }
        System.out.println("Запрос к Teacher вывести список курсов которые он ведет(OneToMane к Course)");

        PurchaseListCK pListCK = new PurchaseListCK("Абакумов Казимир", "Figma");
        PurchaseList pList = session.get(PurchaseList.class, pListCK);
        System.out.println("Курс - " + pList.getCourse().getId() + " Студент - " + pList.getStudent().getId());
        System.out.println("Запрос к PurchaseList вывести ID купленного курса и ID купившего студента" +
                "(ManyToOne к Course и Student)");

        transaction.commit();
        sessionFactory.close();
    }
}
