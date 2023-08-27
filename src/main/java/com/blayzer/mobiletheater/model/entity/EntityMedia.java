package com.blayzer.mobiletheater.model.entity;

import com.blayzer.mobiletheater.model.keys.EntityMediaKey;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "mt_media")
@IdClass(EntityMediaKey.class)
public class EntityMedia {

    @Id
    @Column(name = "id", nullable = false)
    public Integer id;
    @Id
    @Column(name = "spectacle_id", insertable=false, updatable=false, length = 64)
    public Integer spectacleId;
    @Id
    @Column(name = "chapter_id", insertable=false, updatable=false, length = 64)
    public Integer chapterId;
    @Id
    @Column(name = "chapter_path", insertable=false, updatable=false, length = 64)
    public Integer chapterPath;
    @Column(name = "type", length = 64)
    public String type;
    @Column(name = "content", length = 64)
    public String content;
    @Column(name = "time")
    public int time;

    @ManyToOne
    @JsonBackReference
    @JoinColumns({
            @JoinColumn(name = "chapter_id", referencedColumnName = "chapter_id"),
            @JoinColumn(name = "chapter_path", referencedColumnName = "chapter_path"),
            @JoinColumn(name = "spectacle_id", referencedColumnName = "spectacle_id"),
            @JoinColumn(name = "is_transit", referencedColumnName = "is_transit")
    })
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private EntityChapter entityChapter;
}
