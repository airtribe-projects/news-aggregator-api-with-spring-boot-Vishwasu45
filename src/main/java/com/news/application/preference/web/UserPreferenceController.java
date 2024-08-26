package com.news.application.preference.web;

import com.news.application.preference.contract.PreferenceDto;
import com.news.application.preference.service.UserPreferenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user-preference")
@RequiredArgsConstructor
public class UserPreferenceController {

    private final UserPreferenceService userPreferenceService;

    @PostMapping("/save")
    public ResponseEntity<String> saveUserPreference(
            @RequestBody PreferenceDto preferenceDto) {
        userPreferenceService.saveUserPreference(preferenceDto);
        return ResponseEntity.ok("User preference saved successfully");
    }

    @GetMapping
    public ResponseEntity<PreferenceDto> getUserPreferences() {
        var dto = userPreferenceService.getUserPreferences();
        return ResponseEntity.ok(dto);
    }
}
