package com.news.infrastructure.dataprovider.repository;

import com.news.infrastructure.dataprovider.model.Preference;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PreferenceRepository extends JpaRepository<Preference, Long> {

    List<Preference> findByUserId(Long userId);

    void deleteByUserId(Long userId);
}
