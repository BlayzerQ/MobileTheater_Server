package com.blayzer.mobiletheater.model.entity;

import com.blayzer.mobiletheater.model.keys.EntityNotificationKey;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "mt_notification")
@IdClass(EntityNotificationKey.class)
public class EntityNotification {

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
    @Column(name = "title", length = 64)
    public String title;
    @Column(name = "text", length = 128)
    public String text;
    @Column(name = "start_on_chapter")
    public int startOnChapter;
    @Column(name = "silence")
    public boolean silence;
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
