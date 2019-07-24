package blayzer.webservice.entity;

import blayzer.webservice.entity.enums.ProductTypeEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "products")
@Data
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
    private String developer;
    private int price;
}
