package blayzer.webservice.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String type;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String desciption;

    @Getter
    @Setter
    private String productVersion;

    @Getter
    @Setter
    private String mcVersion;

    @Getter
    @Setter
    private int price;

    @Getter
    @Setter
    private String changelog;

    public Product(String type, String name, String desciption, int price) {
        this.type = type;
        this.name = name;
        this.desciption = desciption;
        this.price = price;
    }

    public Product() {

    }
}
