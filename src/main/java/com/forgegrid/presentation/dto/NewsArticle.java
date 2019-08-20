package com.forgegrid.presentation.dto;

import com.forgegrid.dal.entity.NewsEntity;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class NewsArticle {
    private final long id;
    private final String title;
    private final String content;
    private final String date;

    public NewsArticle(NewsEntity newsEntity) {
        this.id = newsEntity.getId();
        this.title = newsEntity.getTitle();
        this.content = newsEntity.getContent();
        this.date = newsEntity.getDate().toString();
    }
}
