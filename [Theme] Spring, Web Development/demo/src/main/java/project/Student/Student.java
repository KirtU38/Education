package project.Student;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;


/*
 * Из этого класса у нас сама сформировалась ДБ, мы вручную её не делали, только создали пустую через консоль и назвали её egor.
 * Это из-за того что мы написали аннотации @Entity и @Table и в файле application.properties указали на нашу ДБ
 * */


@Entity
@Table
public class Student {

    @Id
    @SequenceGenerator(                 // Это нужно для Postgres она работает с sequence'ами
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE) // Значит автоматом инкрементируется
    private Long id;
    private String name;
    private String email;
    private LocalDate dateOfBirth;
    @Transient               // Значит ДБ пропускает эту переменную,
    private Integer age;     // она нужна только Спрингу(age у нас высчитывается автоматом в методе get)

    public Student() {
    }

    public Student(Long id,
                   String name,
                   String email,
                   LocalDate dateOfBirth) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }

    public Student(String name,
                   String email,
                   LocalDate dateOfBirth) {
        this.name = name;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    // Здесь высчитываем возраст автоматически, не сохраняя в БД, возсраст считается как бы только при запросе автоматом
    public Integer getAge() {
        return Period.between(this.dateOfBirth, LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", age=" + age +
                '}';
    }
}
