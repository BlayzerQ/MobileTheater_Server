package com.forgegrid.controllers.rest;

import com.forgegrid.bussines.service.NewsService;
import com.forgegrid.dal.entity.NewsEntity;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class NewsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NewsService newsService;

    @Test
    public void getAllNewsArticlesStatusCodeTest() throws Exception {
        this.mockMvc.perform(get("/rest/news"))
                .andExpect(status().isOk());
    }

    @Test
    public void getAllNewsArticlesMediaTypeTest() throws Exception {
        this.mockMvc.perform(get("/rest/news"))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void getAllNewsContentTest() throws Exception {
        Long id = 1L;
        String title = "Some title";
        String content = "Some news article content";
        Date creationDate = new Date();
        when(newsService.getAll()).thenReturn(Lists.list(new NewsEntity(id, title, content, creationDate)));
        this.mockMvc.perform(get("/rest/news"))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].title", is(title)))
                .andExpect(jsonPath("$[0].content", is(content)))
                .andExpect(jsonPath("$[0].date", is(creationDate.toString())));
    }

    @Test
    public void getNewsArticleByIdStatusCodeTest() throws Exception {
        long id = 1;
        when(newsService.getByID(id)).thenReturn(Optional.of(new NewsEntity(id, null, null, new Date())));
        this.mockMvc.perform(get("/rest/news/" + id))
                .andExpect(status().isOk());
    }

    @Test
    public void getNewsArticleByIdMediaTypeTest() throws Exception {
        long id = 1;
        when(newsService.getByID(id)).thenReturn(Optional.of(new NewsEntity(id, null, null, new Date())));
        this.mockMvc.perform(get("/rest/news/" + id))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void getNewsArticleByIdContentTest() throws Exception {
        long id = 1L;
        String title = "Some title";
        String content = "Some news article content";
        Date creationDate = new Date();
        when(newsService.getByID(id)).thenReturn(Optional.of(new NewsEntity(id, title, content, creationDate)));
        this.mockMvc.perform(get("/rest/news/" + id))
                .andExpect(jsonPath("$.title", is(title)))
                .andExpect(jsonPath("$.content", is(content)))
                .andExpect(jsonPath("$.date", is(creationDate.toString())));
    }

    @Test
    public void getNewsArticleByNonExistentIdTest() throws Exception {
        this.mockMvc.perform(get("/rest/news/" + 999))
                .andExpect(status().isNotFound())
                .andExpect(status().reason("News article not found"));
    }
}
