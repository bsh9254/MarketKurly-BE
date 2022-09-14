package com.weekclone.marketkurlyclone.repository;

import com.weekclone.marketkurlyclone.model.Imgfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImgfileRepository extends JpaRepository<Imgfile, Long> {
}