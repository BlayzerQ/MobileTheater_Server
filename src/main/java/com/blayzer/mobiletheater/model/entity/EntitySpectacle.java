package com.blayzer.mobiletheater.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "mt_spectacle")
public class EntitySpectacle {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    @Column(name = "spectacle_id", length = 64)
    public Integer spectacleId;
    @Column(name = "name", length = 64, insertable=false, updatable=false)
    public String name;
    @Column(name = "short_desc", length = 124)
    public String shortdesc;
    @Column(name = "full_desc", length = 256)
    public String fulldesc;
    @Column(name = "authors", length = 256)
    public String authors;
    @Column(name = "image", length = 124)
    public String image;
    @Column(name = "image_full", length = 124)
    public String imageFull;
    @Column(name = "walking_time", length = 64)
    public String walkingTime;
    @Column(name = "is_linear")
    public boolean isLinear;
    @Column(name = "price")
    public Integer price;
    @Column(name = "fragment")
    public String fragment;
    @Column(name = "is_car_spectacle")
    public boolean isCarSpectacle;
    @Column(name = "is_purshased")
    public boolean isPurshased;
    @Column(name = "is_availible")
    public boolean isAvailible;
    @Column(name = "language")
    public String language;
    @Column(name = "age_rating")
    public String ageRating;
    @Column(name = "create_date")
    public String createDate;

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, mappedBy = "entitySpectacle")
    @JsonManagedReference
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    public List<EntityChapter> chapters;
}
