package com.blayzer.mobiletheater.model.repository;

import com.blayzer.mobiletheater.model.entity.EntitySpectacle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SpectacleRepository extends JpaRepository<EntitySpectacle, Integer> {

    @Query(value = "INSERT INTO public.mt_spectacle " +
            "(id, spectacle_id, name, short_desc, full_desc, authors, image, image_full, walking_time, is_linear, price, fragment," +
            " is_car_spectacle, is_purshased, is_availible, language, age_rating, creator_uuid) " +
            "VALUES(?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9, ?10, ?11, ?12, ?13, ?14, ?15, ?16, ?17, ?18)", nativeQuery = true)
    void insertSpectacle(Integer id, Integer spectacleId, String name, String shortDesc, String fullDesc, String authors,
                                String image, String imageFull, String walkingTime, boolean isLinear, Integer price, String fragment,
                                boolean isCarSpectacle, boolean isPurshased, boolean isAvailible, String language, String ageRating,
                                String creatorUUID);

    @Query(value = "INSERT INTO public.mt_chapter " +
            "(id, spectacle_id, chapter_id, chapter_path, name, description, latitude, longitude, audio, start_delay, final_text, is_transit) " +
            "VALUES(?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9, ?10, ?11, ?12)", nativeQuery = true)
    void insertChapter(Integer id, Integer spectacleId, Integer chapterId, Integer chapterPath, String name, String description,
                       String latitude, String longitude, String audio, String startDelay, String finalText, boolean isTransit);

    @Query(value = "INSERT INTO public.mt_media " +
            "(id, spectacle_id, chapter_id, chapter_path, type, content, is_transit, time) " +
            "VALUES(?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8)", nativeQuery = true)
    void insertMedia(Integer id, Integer spectacleId, Integer chapterId, Integer chapterPath, String type, String content,
                     boolean isTransit, Integer time);

    @Query(value = "INSERT INTO public.mt_notification " +
            "(id, spectacle_id, chapter_id, chapter_path, title, text, start_on_chapter, silence, time, is_transit) " +
            "VALUES(0, 0, 0, 0, '', '', 0, false, 0, false)", nativeQuery = true)
    void insertNotification(Integer id, Integer spectacleId, Integer chapterId, String title, String text,
                            Integer startOnChapter, boolean silence, Integer time, boolean isTransit);
}
