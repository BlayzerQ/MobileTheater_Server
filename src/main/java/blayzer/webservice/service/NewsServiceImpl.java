package blayzer.webservice.service;

import blayzer.webservice.entity.News;
import blayzer.webservice.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsRepository newsRepository;

    @Override
    public News getByID(Long id) {
        return newsRepository.findOne(id);
    }

    @Override
    public News addItem(News user) {
        return newsRepository.saveAndFlush(user);
    }

    @Override
    public News editItem(News user) {
        return newsRepository.saveAndFlush(user);
    }

    @Override
    public void deleteItem(Long id) {
        newsRepository.delete(id);
    }

    @Override
    public List<News> getAll() {
        return newsRepository.findAll();
    }

}
