package lv.akurss.hibernatetest;

import javax.persistence.*;
import java.util.List;

@SequenceGenerator(name = "seq_gen", sequenceName = "users_seq")
@Entity
@Table(name = "users")



public class User {

    @Id
    @GeneratedValue(generator = "seq_gen", strategy = GenerationType.SEQUENCE)
    @Column(name = "id")

    private Long Id;

    @Column( name = "name")

    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "login")

    private String login;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Task> tasks;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
