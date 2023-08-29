package com.blayzer.mobiletheater.service;

import com.blayzer.mobiletheater.model.entity.EntityChapter;
import com.blayzer.mobiletheater.model.entity.EntityMedia;
import com.blayzer.mobiletheater.model.entity.EntityNotification;
import com.blayzer.mobiletheater.model.entity.EntitySpectacle;
import com.blayzer.mobiletheater.model.repository.SpectacleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SpectacleService {

    private final SpectacleRepository spectacleRepository;

    public Optional<EntitySpectacle> getByID(Integer id) {
        return spectacleRepository.findById(id);
    }

    public List<EntitySpectacle> getAll() {
        return spectacleRepository.findAll();
    }

    public void saveSpectacle(EntitySpectacle spectacle) {
        try {
            spectacleRepository.insertSpectacle(spectacle.id, spectacle.spectacleId, spectacle.name, spectacle.shortdesc, spectacle.fulldesc,
                    spectacle.authors, spectacle.image, spectacle.imageFull, spectacle.walkingTime, spectacle.isLinear, spectacle.price,
                    spectacle.fragment, spectacle.isCarSpectacle, spectacle.isPurshased, spectacle.isAvailible, spectacle.language,
                    spectacle.ageRating, "");
        } catch (Exception e) {

        }

        for(EntityChapter chapter : spectacle.chapters) {
            try {
                spectacleRepository.insertChapter(chapter.id, chapter.spectacleId, chapter.chapterId, chapter.chapterPath,
                        chapter.name, chapter.description, chapter.latitude, chapter.longitude, chapter.audio,
                        chapter.startDelay, chapter.finalText, chapter.isTransitPoint);
            } catch (Exception e) {

            }

            for(EntityMedia media : chapter.media) {
                try {
                    spectacleRepository.insertMedia(media.id, media.spectacleId, media.chapterId, media.chapterPath, media.type,
                            media.content, chapter.isTransitPoint, media.time);
                } catch (Exception e) {

                }
            }

            for(EntityNotification notification : chapter.notifications) {
                try {
                    spectacleRepository.insertNotification(notification.id, notification.spectacleId, notification.chapterId,
                            notification.title, notification.text, notification.startOnChapter, notification.silence,
                            notification.time, chapter.isTransitPoint);
                } catch (Exception e) {

                }
            }
        }
    }

}
