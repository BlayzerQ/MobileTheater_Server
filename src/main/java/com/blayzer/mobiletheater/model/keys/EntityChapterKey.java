package com.blayzer.mobiletheater.model.keys;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
public class EntityChapterKey implements Serializable {
    private Integer spectacleId;
    private Integer chapterId;
    private Integer chapterPath;
    private boolean isTransitPoint;
}