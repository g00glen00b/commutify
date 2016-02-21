package be.g00glen00b.commutify.service.impl;

import be.g00glen00b.commutify.dto.EntryDTO;
import be.g00glen00b.commutify.dto.ProfileDTO;
import be.g00glen00b.commutify.dto.TypeDTO;
import be.g00glen00b.commutify.dto.UserSignupDTO;
import be.g00glen00b.commutify.entity.CommutifyEntry;
import be.g00glen00b.commutify.entity.CommutifyProfile;
import be.g00glen00b.commutify.entity.CommutifyType;
import be.g00glen00b.commutify.repository.CommutifyEntryRepository;
import be.g00glen00b.commutify.repository.CommutifyProfileRepository;
import be.g00glen00b.commutify.repository.CommutifyTypeRepository;
import be.g00glen00b.commutify.service.InvalidEntryException;
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

import java.math.BigDecimal;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProfileServiceImpl implements ProfileService {
    @Autowired
    private CommutifyProfileRepository repository;
    @Autowired
    private CommutifyTypeRepository typeRepository;
    @Autowired
    private CommutifyEntryRepository entryRepository;
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
    @Transactional
    public ProfileDTO updateEntry(EntryDTO dto) {
        final CommutifyProfile entity = repository.findByEmail(getEmail());
        if (entity != null) {
            final CommutifyEntry commutifyEntry = entity.getEntries().stream()
                    .filter(entry -> entry.getDate().withTimeAtStartOfDay().isEqual(dto.getDate().withTimeAtStartOfDay()))
                    .findFirst()
                    .orElse(null);
            final CommutifyType type = typeRepository.findOne(dto.getType().getId());
            if (type != null) {
                if (commutifyEntry != null) {
                    entity.setSaved(getSafe(entity.getSaved()).subtract(getSavedFromEntry(commutifyEntry)));
                    commutifyEntry.setType(type);
                    commutifyEntry.setEmission(entity.getEmission());
                    commutifyEntry.setKm(dto.getDistance());
                    entity.setSaved(getSafe(entity.getSaved()).add(getSavedFromEntry(commutifyEntry)));
                } else {
                    final CommutifyEntry savedEntity = entryRepository.save(new CommutifyEntry.Builder()
                        .date(dto.getDate())
                        .type(type)
                        .emission(entity.getEmission())
                        .km(dto.getDistance())
                        .profile(entity)
                        .build());
                    entity.getEntries().add(savedEntity);
                    entity.setSaved(getSafe(entity.getSaved()).add(getSavedFromEntry(savedEntity)));
                }
                return map(entity);
            } else {
                throw new InvalidEntryException("Type does not exist");
            }
        } else {
            throw new InvalidProfileException("Profile does not exist");
        }
    }

    @Override
    public ProfileDTO deleteEntry(Long entryId) {
        final CommutifyProfile entity = repository.findByEmail(getEmail());
        final CommutifyEntry commutifyEntry = entity.getEntries().stream()
                .filter(entry -> Objects.equals(entry.getId(), entryId))
                .findFirst().orElse(null);
        if (commutifyEntry != null) {
            entity.setSaved(getSafe(entity.getSaved()).subtract(getSavedFromEntry(commutifyEntry)));
            entity.getEntries().remove(commutifyEntry);
            entryRepository.delete(commutifyEntry);
            return map(entity);
        } else {
            throw new InvalidEntryException("Entry does not exist");
        }
    }

    private BigDecimal getSavedFromEntry(CommutifyEntry entry) {
        final BigDecimal normalExhaust = getSafe(entry.getEmission()).multiply(getSafe(entry.getKm()));
        final BigDecimal currentExhaust = getSafe(entry.getType().getEmission()).multiply(getSafe(entry.getKm()));
        return normalExhaust.subtract(currentExhaust);
    }

    private BigDecimal getSafe(BigDecimal value) {
        if (value == null) {
            return new BigDecimal(0);
        } else {
            return value;
        }
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

    public ProfileDTO map(CommutifyProfile profile) {
        return new ProfileDTO.Builder()
            .id(profile.getId())
            .name(profile.getName())
            .firstName(profile.getFirstName())
            .avatar(getAvatar(profile))
            .emission(profile.getEmission())
            .averageKmDay(profile.getAverageKmDay())
            .entries(profile.getEntries().stream().map(this::map).collect(Collectors.toList()))
            .saved(getSafe(profile.getSaved()))
            .build();
    }

    public EntryDTO map(CommutifyEntry entry) {
        return new EntryDTO.Builder()
            .id(entry.getId())
            .date(entry.getDate())
            .distance(entry.getKm())
            .type(new TypeDTO(entry.getType().getId(), entry.getType().getName(), entry.getType().getEmission()))
            .build();
    }
}
