package blayzer.webservice.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private String creator;

    @Getter
    @Setter
    private String link;

    @Getter
    @Setter
    private int price;

    Task(String title, String description, String creator, int price) {
        this.title = title;
        this.description = description;
        this.creator = creator;
        this.price = price;
    }

    Task() {

    }
}
