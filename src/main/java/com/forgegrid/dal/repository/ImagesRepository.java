package com.forgegrid.dal.repository;

import com.forgegrid.dal.entity.Image;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagesRepository extends CrudRepository<Image, Long> {

}
