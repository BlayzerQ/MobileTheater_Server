package blayzer.webservice.service;

import blayzer.webservice.entities.News;

import java.util.List;

public interface NewsService {

    News getByID(Long id);
    News addItem(News news);
    News editItem(News news);
    void deleteItem(Long id);
    List<News> getAll();
}
