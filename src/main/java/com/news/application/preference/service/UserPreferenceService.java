package com.news.application.preference.service;

import com.news.application.preference.contract.PreferenceDto;
import com.news.application.preference.mapper.PreferenceMapper;
import com.news.infrastructure.dataprovider.repository.PreferenceRepository;
import com.news.infrastructure.dataprovider.repository.UserRepository;
import com.news.infrastructure.security.helper.UserContextHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserPreferenceService {

    private final PreferenceRepository preferenceRepository;

    private final UserRepository userRepository;

    private final UserContextHelper userContextHelper;

    public void saveUserPreference(PreferenceDto preferenceDto) {
        var user = userRepository.findById(userContextHelper.getUserIdFromContext()).orElseThrow(() -> new RuntimeException("User not found"));
        preferenceRepository.findByUserId(user.getId()).forEach(preferenceRepository::delete);
        var list = preferenceDto.getName()
                .stream()
                .map(preferenceName -> PreferenceMapper.toPreferenceEntity(preferenceName, user))
                .toList();
        preferenceRepository.saveAll(list);
    }

    public PreferenceDto getUserPreferences() {
        var preferenceEntities = preferenceRepository.findByUserId(userContextHelper.getUserIdFromContext());
        return PreferenceMapper.toPreferenceDto(preferenceEntities);
    }
}
