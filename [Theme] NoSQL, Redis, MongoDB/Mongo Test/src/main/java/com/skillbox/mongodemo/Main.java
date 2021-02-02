package com.skillbox.mongodemo;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.BsonDocument;
import org.bson.Document;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Main {

    public static final String PATH = "src/main/resources/mongo.csv";

    public static void main(String[] args) throws IOException {

        MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);
        MongoDatabase database = mongoClient.getDatabase("local");
        MongoCollection<Document> collection = database.getCollection("Students");
        BsonDocument query;

        collection.drop();

        // Парсим из файла в лист студентов
        List<Student> students = parseStudents();

        // Из листа студентов в MongoDB
        students.forEach(s -> {
            Document studentDocument = new Document()
                    .append("name", s.getName())
                    .append("age", s.getAge())
                    .append("courses", s.getCourses());

            collection.insertOne(studentDocument);
        });

        // Общее количество студентов в базе
        long numberOfStudents = collection.countDocuments();
        System.out.println("Общее число студентов: " + numberOfStudents);

        // Студенты за 40
        System.out.println("Студенты за 40:");
        query = BsonDocument.parse("{age: {$gt: 40}}");

        collection.find(query).forEach((Consumer<Document>) document -> {
            System.out.println("Имя - " + document.get("name") + ", Возраст - " + document.get("age"));
        });

        // Самый молодой студент
        System.out.println("Самый молодой студент:");
        query = BsonDocument.parse("{age: 1}");
        FindIterable<Document> youngestStudent = collection.find().sort(query).limit(1);

        youngestStudent.forEach((Consumer<Document>) document -> {
            System.out.println("Имя - " + document.get("name") + ", Возраст - " + document.get("age"));
        });

        // Список курсов самого старого студента
        System.out.println("Самый старый студент:");
        query = BsonDocument.parse("{age: -1}");
        FindIterable<Document> oldestStudent = collection.find().sort(query).limit(1);

        oldestStudent.forEach((Consumer<Document>) document -> {
            System.out.println("Имя - " + document.get("name") + ", Возраст - " + document.get("age") +
                    ", Учится на курсах - " + document.get("courses"));
        });
    }

    private static List<Student> parseStudents() throws IOException {

        List<String> lines = Files.readAllLines(Path.of(PATH));
        List<Student> students = new ArrayList<>();

        lines.forEach(l -> {
            String[] nodes = l.split(",", 3);
            String[] coursesArr = nodes[2].replaceAll("\"", "").split(",");

            String name = nodes[0];
            int age = Integer.parseInt(nodes[1]);
            List<String> listOfCourses = new ArrayList<>(Arrays.asList(coursesArr));

            students.add(new Student(name, age, listOfCourses));
        });
        return students;
    }
}
