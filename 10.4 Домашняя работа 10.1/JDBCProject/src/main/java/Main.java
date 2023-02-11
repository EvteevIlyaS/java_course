import org.hibernate.NonUniqueObjectException;
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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        String url = "jdbc:mysql://localhost:3306/JavaMySQL";
//        String user = "root";
//        String pass = "88508850";
//
//        try {
//            Connection connection = DriverManager.getConnection(url, user, pass);
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery("select course_name, count(1) / " +
//                    "(month(max(subscription_date)) - month(min(subscription_date)) + 1)\n" +
//                    "from PurchaseList group by course_name;");
//            while (resultSet.next()) {
//                System.out.printf("%s - %s\n", resultSet.getString(1), resultSet.getString(2));
//            }
//            connection.close();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        Session session = sessionFactory.openSession();

//        Course course = session.get(Course.class, 1);
//        System.out.println(course.getTeacher().getName());

//        Student student = session.get(Student.class, 78);
//        System.out.println(student.getName() + " " + student.getAge());

//        Teacher teacher = session.get(Teacher.class, 10);
//        System.out.println(teacher.getName());
//        System.out.println(teacher.getCourses().size());
//        teacher.getCourses().forEach(el -> System.out.println(el.getName()));

//        PurchaseList purchaseList = session.get(PurchaseList.class,
//                new PurchaseList("Амбражевич Порфирий", "Веб-разработчик c 0 до PRO"));
//        System.out.println(purchaseList.getPrice());

//        Subscription subscription = session.get(Subscription.class, new Subscription(2, 1));
//        System.out.println(subscription.getSubscriptionDate());


        Transaction transaction = session.beginTransaction();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<PurchaseList> query = builder.createQuery(PurchaseList.class);
        Root<PurchaseList> root = query.from(PurchaseList.class);
        query.select(root);
        List<PurchaseList> purchaseList = session.createQuery(query).getResultList();
        purchaseList.forEach(el -> System.out.println(el.getCourseName() + " " + el.getStudentName()));

        List<Integer> studentIds = new ArrayList<>();
        List<Integer> courseIds = new ArrayList<>();

        CriteriaQuery<Student> queryStudent = builder.createQuery(Student.class);
        Root<Student> rootStudent = queryStudent.from(Student.class);

        CriteriaQuery<Course> queryCourse = builder.createQuery(Course.class);
        Root<Course> rootCourse = queryCourse.from(Course.class);

//        purchaseList.forEach(el -> System.out.println(el.getCourseName() + " " + el.getStudentName()));

        purchaseList.forEach(el -> {
            queryStudent.select(rootStudent).where(builder.equal(rootStudent.get("name"), el.getStudentName()));
            studentIds.add(session.createQuery(queryStudent).getResultList().get(0).getId());

            queryCourse.select(rootCourse).where(builder.equal(rootCourse.get("name"), el.getCourseName()));
            courseIds.add(session.createQuery(queryCourse).getResultList().get(0).getId());
        });

//        studentIds.forEach(System.out::println);
//        System.out.println();
//        courseIds.forEach(System.out::println);
//
//        System.out.println(studentIds.size() + " " + courseIds.size());

        LinkedPurchaseListKey linkedPurchaseListKey;
        LinkedPurchaseList linkedPurchaseList;

        for (int i = 0; i < studentIds.size(); i++) {
            linkedPurchaseListKey = new LinkedPurchaseListKey();
            linkedPurchaseList = new LinkedPurchaseList();

            linkedPurchaseListKey.setCourseId(courseIds.get(i));
            linkedPurchaseListKey.setStudentId(studentIds.get(i));

            linkedPurchaseList.setId(linkedPurchaseListKey);

//            System.out.println(courseIds.get(i) + " " + studentIds.get(i));
            try {
                session.save(linkedPurchaseList);
            } catch (NonUniqueObjectException ex){
                ex.printStackTrace();
            }
        }

        transaction.commit();

        sessionFactory.close();
    }
}
