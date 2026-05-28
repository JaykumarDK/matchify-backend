package com.matchify.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.matchify.dto.MatchResponse;
import com.matchify.entity.*;
import com.matchify.repository.*;

@Service
public class MatchService {

    private final PersonalInfoRepository personalRepo;
    private final ExtraUserInfoRepository extraRepo;
    private final EducationRepository eduRepo;

    public MatchService(
            PersonalInfoRepository personalRepo,
            ExtraUserInfoRepository extraRepo,
            EducationRepository eduRepo) {

        this.personalRepo = personalRepo;
        this.extraRepo = extraRepo;
        this.eduRepo = eduRepo;
    }

    public List<MatchResponse> getMatches(int userId) {

        PersonalInfo currentUser = personalRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        ExtraUserInfo currentExtra = extraRepo.findById(userId).orElse(null);
        Education currentEdu = eduRepo.findById(userId).orElse(null);

        if (currentExtra == null || currentEdu == null)
            return new ArrayList<>();

        String targetGender =
                currentUser.getGender().equalsIgnoreCase("Male") ? "Female" : "Male";

        List<MatchResponse> results = new ArrayList<>();

        for (PersonalInfo p : personalRepo.findAll()) {

            if (p.getU_ID() == userId) continue;
            if (!p.getGender().equalsIgnoreCase(targetGender)) continue;

            ExtraUserInfo ex = extraRepo.findById(p.getU_ID()).orElse(null);
            Education edu = eduRepo.findById(p.getU_ID()).orElse(null);

            if (ex == null || edu == null) continue;

            int score = calculateScore(currentExtra, currentEdu, ex, edu);

            if (score > 0) {

                MatchResponse dto = new MatchResponse();

                dto.setUserId(p.getU_ID());
                dto.setName(p.getName());
                dto.setAge(ex.getAge());
                dto.setCaste(p.getCaste());
                dto.setEducation(edu.getHigherEducation());
                dto.setJobLocation(edu.getJob_location());
                dto.setAnnualIncome(edu.getAnnualIncome());
                dto.setHeight(ex.getHeight());
                dto.setScore(score);

                results.add(dto);
            }
        }

        return results;
    }
    public List<MatchResponse> getFilteredMatches(
            int userId,
            String caste,
            String education,
            String jobLocation,
            int minAge,
            int maxAge,
            int minIncome,
            int minHeight,
            int maxHeight,
            String sortBy) {

        return buildMatches(userId).stream()

                .filter(m -> caste == null || caste.isBlank()
                        || caste.equalsIgnoreCase(m.caste))

                .filter(m -> education == null || education.isBlank()
                        || education.equalsIgnoreCase(m.education))

                .filter(m -> jobLocation == null || jobLocation.isBlank()
                        || jobLocation.equalsIgnoreCase(m.jobLocation))

                .filter(m -> m.age >= minAge && m.age <= maxAge)

                .filter(m -> m.annualIncome >= minIncome)

                .filter(m -> m.height >= minHeight && m.height <= maxHeight)

                .sorted(getComparator(sortBy))

                .collect(Collectors.toList());
    }

    // ---------- CORE MATCH BUILDER ----------
    private List<MatchResponse> buildMatches(int userId) {

        PersonalInfo currentUser = personalRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        ExtraUserInfo currentExtra = extraRepo.findById(userId).orElse(null);
        Education currentEdu = eduRepo.findById(userId).orElse(null);

        if (currentExtra == null || currentEdu == null) {
            return new ArrayList<>();
        }

        String targetGender =
                currentUser.getGender().equalsIgnoreCase("Male") ? "Female" : "Male";

        List<MatchResponse> results = new ArrayList<>();

        for (PersonalInfo p : personalRepo.findAll()) {

            if (p.getU_ID() == userId) continue;
            if (!p.getGender().equalsIgnoreCase(targetGender)) continue;

            ExtraUserInfo ex = extraRepo.findById(p.getU_ID()).orElse(null);
            Education edu = eduRepo.findById(p.getU_ID()).orElse(null);

            if (ex == null || edu == null) continue;

            int score = calculateScore(currentExtra, currentEdu, ex, edu);

            if (score > 0) {
                results.add(new MatchResponse(
                        p.getU_ID(),
                        p.getName(),
                        ex.getAge(),
                        p.getCaste(),
                        edu.getHigherEducation(),
                        edu.getJob_location(),
                        edu.getAnnualIncome(),
                        ex.getHeight(),
                        score
                ));
            }
        }
        return results;
    }

    // ---------- SCORE ----------
    private int calculateScore(
            ExtraUserInfo cEx, Education cEdu,
            ExtraUserInfo mEx, Education mEdu) {

        double score = 0;

        if (cEx.getMotherTongue() != null &&
            cEx.getMotherTongue().equalsIgnoreCase(mEx.getMotherTongue()))
            score += 2;

        if (cEdu.getHigherEducation() != null &&
            cEdu.getHigherEducation().equalsIgnoreCase(mEdu.getHigherEducation()))
            score += 2;

        if (cEdu.getJob_location() != null &&
            cEdu.getJob_location().equalsIgnoreCase(mEdu.getJob_location()))
            score += 1.5;

        if (Math.abs(cEdu.getAnnualIncome() - mEdu.getAnnualIncome()) < 100000)
            score += 1;

        if (Math.abs(cEx.getAge() - mEx.getAge()) <= 3)
            score += 1.5;

        return (int) Math.min(score, 10);
    }
    // ---------- SORT ----------
    private Comparator<MatchResponse> getComparator(String sortBy) {

        if ("age".equalsIgnoreCase(sortBy)) {
            return Comparator.comparingInt((MatchResponse m) -> m.age);
        }

        if ("income".equalsIgnoreCase(sortBy)) {
            return Comparator.comparingInt((MatchResponse m) -> m.annualIncome)
                             .reversed();
        }

        // default → sort by score (descending)
        return Comparator.comparingInt((MatchResponse m) -> m.score)
                         .reversed();
    }

}
