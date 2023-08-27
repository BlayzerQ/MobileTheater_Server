package com.blayzer.mobiletheater.model.keys;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
public class EntityNotificationKey implements Serializable {
    private Integer id;
    private Integer spectacleId;
    private Integer chapterId;
    private Integer chapterPath;
}