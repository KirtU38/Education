import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(length = 50)
    String name;
    int age;

    @Column(name = "messages_count")
    int messagesCount;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<Message> message;

    public User() {
    }

    public User(String name, int age, int messagesCount) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.messagesCount = messagesCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getMessagesCount() {
        return messagesCount;
    }

    public void setMessagesCount(int messagesCount) {
        this.messagesCount = messagesCount;
    }

    public List<Message> getMessage() {
        return message;
    }

    public void setMessage(List<Message> message) {
        this.message = message;
    }
}
