package blayzer.webservice.bussines.service;

import blayzer.webservice.dal.entity.NewsEntity;

import java.util.List;

public interface NewsService {

    NewsEntity getByID(Long id);
    NewsEntity addItem(NewsEntity news);
    NewsEntity editItem(NewsEntity news);
    void deleteItem(Long id);
    List<NewsEntity> getAll();
}
