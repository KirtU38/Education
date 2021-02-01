package project.Student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student egor = new Student(
                    "Egor",
                    "bel@ya.ru",
                    LocalDate.of(1994, Month.DECEMBER, 12));

            Student tasia = new Student(
                    "Tasia",
                    "tas@ya.ru",
                    LocalDate.of(2000, Month.MAY, 30));

            Student alex = new Student(
                    "Alex",
                    "alex@ya.ru",
                    LocalDate.of(1987, Month.JUNE, 7));

            repository.saveAll(List.of(egor, tasia, alex));
        };
    }
}
