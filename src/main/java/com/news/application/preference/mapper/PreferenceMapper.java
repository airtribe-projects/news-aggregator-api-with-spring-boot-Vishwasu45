package com.news.application.preference.mapper;

import com.news.application.preference.contract.PreferenceDto;
import com.news.infrastructure.dataprovider.model.Preference;
import com.news.infrastructure.dataprovider.model.User;
import lombok.experimental.UtilityClass;

import java.util.Collections;
import java.util.List;

@UtilityClass
public class PreferenceMapper {

    public PreferenceDto toPreferenceDto(List<Preference> preferenceEntity) {
        PreferenceDto preferenceDto = new PreferenceDto();
        preferenceDto.setName(preferenceEntity.stream().map(Preference::getName).toList());
        return preferenceDto;
    }

    public Preference toPreferenceEntity(String preferenceName, User user) {
        Preference preferenceEntity = new Preference();
        preferenceEntity.setUser(user);
        preferenceEntity.setName(preferenceName);
        return preferenceEntity;
    }
}
