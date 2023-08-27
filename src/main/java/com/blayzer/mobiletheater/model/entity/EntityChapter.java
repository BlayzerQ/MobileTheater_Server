package com.blayzer.mobiletheater.model.entity;

import com.blayzer.mobiletheater.model.keys.EntityChapterKey;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "mt_chapter")
@IdClass(EntityChapterKey.class)
public class EntityChapter {

    @Column(name = "id")
    public Integer id;
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "spectacle_id", length = 64, insertable = false, updatable = false)
    public Integer spectacleId;
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chapter_id", length = 64)
    public Integer chapterId;
    @Id
    @Column(name = "chapter_path", length = 64)
    public Integer chapterPath;
    @Id
    @Column(name = "is_transit")
    public boolean isTransitPoint;

    @Column(name = "name", length = 64)
    public String name;
    @Column(name = "description", length = 512)
    public String description;
    @Column(name = "latitude", length = 64)
    public String latitude;
    @Column(name = "longitude", length = 64)
    public String longitude;
    @Column(name = "audio", length = 124)
    public String audio;
    @Column(name = "start_delay", length = 64)
    public String startDelay;
    @Column(name = "final_text", length = 124)
    public String finalText;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "spectacle_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private EntitySpectacle entitySpectacle;

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, mappedBy = "entityChapter")
    @Fetch(value = FetchMode.SUBSELECT)
    @JsonManagedReference
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    public List<EntityMedia> media;

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, mappedBy = "entityChapter")
    @Fetch(value = FetchMode.SUBSELECT)
    @JsonManagedReference
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    public List<EntityNotification> notifications;
}
