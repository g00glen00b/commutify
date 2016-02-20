package be.g00glen00b.commutify.service;

import be.g00glen00b.commutify.dto.ProfileDTO;
import be.g00glen00b.commutify.dto.UserSignupDTO;
import be.g00glen00b.commutify.entity.CommutifyProfile;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public interface ProfileService {
    ProfileDTO createProfile(UserSignupDTO dto);

    ProfileDTO get(long id);

    ProfileDTO getSelf();

    ProfileDTO map(CommutifyProfile profile);
}
