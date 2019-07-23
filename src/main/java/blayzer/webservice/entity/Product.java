package blayzer.webservice.entity;

import blayzer.webservice.entity.enums.ProductTypeEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private ProductTypeEnum type;
    private String name;
    private String description;
    private String productVersion;
    private String mcVersion;
    private String changelog;
    private int price;
}
