package com.matchify.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.matchify.entity.ExtraUserInfo;
import com.matchify.repository.ExtraUserInfoRepository;

@Service
public class UserImageService {

    private ExtraUserInfoRepository extraUserInfoRepository;

	public UserImageService(ExtraUserInfoRepository extraUserInfoRepository) {
		super();
		this.extraUserInfoRepository = extraUserInfoRepository;
	}
    @Transactional
	public void uploadProfileImage(int userId,MultipartFile imFile) throws Exception {
		ExtraUserInfo userInfo=extraUserInfoRepository.findById(userId)
				               .orElseThrow(()->new RuntimeException("User not Found"));
		
		userInfo.setImage(imFile.getBytes());
		extraUserInfoRepository.save(userInfo);
	}
    public byte[] getProfileImage(int userId) {

        Optional<ExtraUserInfo> extraOpt = extraUserInfoRepository.findById(userId);

        if (extraOpt.isEmpty() || extraOpt.get().getImage() == null) {
            return null;
        }

        return extraOpt.get().getImage();
    }
}
