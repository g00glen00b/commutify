package be.g00glen00b.commutify.controller.api;

import be.g00glen00b.commutify.dto.ProfileDTO;
import be.g00glen00b.commutify.dto.UserSignupDTO;
import be.g00glen00b.commutify.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/profile")
public class ProfileRESTController {
    @Autowired
    private ProfileService service;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @PreAuthorize("isAnonymous()")
    public ProfileDTO register(@RequestBody @Valid UserSignupDTO dto) {
        return service.createProfile(dto);
    }

    @RequestMapping(value = "/my", method = RequestMethod.PUT)
    @PreAuthorize("isAuthenticated()")
    public ProfileDTO update(@RequestBody @Valid ProfileDTO dto) {
        return service.updateProfile(dto);
    }

    @RequestMapping(value = "/my", method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated()")
    public ProfileDTO myself() {
        return service.getSelf();
    }
}
