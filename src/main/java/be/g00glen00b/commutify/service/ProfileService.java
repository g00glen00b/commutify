package be.g00glen00b.commutify.service;

import be.g00glen00b.commutify.dto.EntryDTO;
import be.g00glen00b.commutify.dto.ProfileCollectionDTO;
import be.g00glen00b.commutify.dto.ProfileDTO;
import be.g00glen00b.commutify.dto.UserSignupDTO;
import be.g00glen00b.commutify.entity.CommutifyProfile;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;

public interface ProfileService {
    ProfileDTO createProfile(UserSignupDTO dto);
    ProfileDTO updateProfile(ProfileDTO profile);
    ProfileDTO get(long id);
    ProfileDTO getSelf();
    ProfileDTO deleteEntry(Long entryId);
    ProfileCollectionDTO findAll(int offset, int limit);
    ProfileDTO map(CommutifyProfile profile);
    ProfileDTO updateEntry(EntryDTO entry);
}
