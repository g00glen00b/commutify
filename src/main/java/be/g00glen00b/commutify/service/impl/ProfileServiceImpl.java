package be.g00glen00b.commutify.service.impl;

import be.g00glen00b.commutify.dto.ProfileDTO;
import be.g00glen00b.commutify.dto.UserSignupDTO;
import be.g00glen00b.commutify.entity.CommutifyProfile;
import be.g00glen00b.commutify.repository.CommutifyProfileRepository;
import be.g00glen00b.commutify.service.InvalidProfileException;
import be.g00glen00b.commutify.service.ProfileAlreadyExistsException;
import be.g00glen00b.commutify.service.ProfileService;
import org.apache.tomcat.util.codec.binary.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

@Service
public class ProfileServiceImpl implements ProfileService {
    @Autowired
    private CommutifyProfileRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private MessageSource msgSource;

    @Override
    @Transactional
    public ProfileDTO createProfile(UserSignupDTO dto) {
        final CommutifyProfile profile = repository.findByEmail(dto.getEmail());
        if (profile == null) {
            final CommutifyProfile entity = repository.save(new CommutifyProfile.Builder()
                    .email(dto.getEmail())
                    .password(passwordEncoder.encode(dto.getPassword()))
                    .firstName(dto.getFirstName())
                    .name(dto.getName())
                    .build());
            return map(entity);
        } else {
            throw new ProfileAlreadyExistsException("There is already a user registered with that e-mail address");
        }
    }

    @Override
    @Transactional
    public ProfileDTO updateProfile(ProfileDTO profile) {
        final CommutifyProfile entity = repository.findByEmail(getEmail());
        if (entity != null) {
            entity.setName(profile.getName());
            entity.setFirstName(profile.getFirstName());
            entity.setAverageKmDay(profile.getAverageKmDay());
            entity.setEmission(profile.getEmission());
            return map(entity);
        } else {
            throw new InvalidProfileException("Profile does not exist");
        }
    }

    @Override
    public ProfileDTO get(long id) {
        final CommutifyProfile entity = repository.findOne(id);
        if (entity != null) {
            return map(entity);
        } else {
            throw new InvalidProfileException("Profile does not exist");
        }
    }

    @Override
    public ProfileDTO getSelf() {
        final CommutifyProfile entity = repository.findByEmail(getEmail());
        if (entity != null) {
            return map(entity);
        } else {
            throw new InvalidProfileException("Profile does not exist");
        }
    }

    @Override
    public ProfileDTO map(CommutifyProfile profile) {
        return new ProfileDTO.Builder()
            .id(profile.getId())
            .name(profile.getName())
            .firstName(profile.getFirstName())
            .avatar(getAvatar(profile))
            .emission(profile.getEmission())
            .averageKmDay(profile.getAverageKmDay())
            .build();
    }

    private String getEmail() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            Object principal = auth.getPrincipal();
            if (principal instanceof String) {
                return (String) principal;
            }
        }
        return null;
    }

    private String getAvatar(CommutifyProfile profile) {
        final String digest = DigestUtils.md5DigestAsHex(StringUtils.getBytesUtf8(profile.getEmail()));
        return msgSource.getMessage("profile.avatar", new String[] { digest }, LocaleContextHolder.getLocale());
    }
}
