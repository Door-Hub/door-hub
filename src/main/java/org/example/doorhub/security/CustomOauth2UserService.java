package org.example.doorhub.security;

import lombok.RequiredArgsConstructor;
import org.example.doorhub.user.UserRepository;
import org.example.doorhub.user.entity.User;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomOauth2UserService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);

        Map<String, Object> attributes = oAuth2User.getAttributes();

        String email = (String) attributes.get("email");
        String picture = (String) attributes.get("picture");
        String avatarUrl = (String) attributes.get("avatar_url");
        String givenName = (String) attributes.get("given_name");
        String family_name = (String) attributes.get("family_name");
        String name = (String) attributes.get("name");
        LocalDate birthdate = (LocalDate) attributes.get("birthdate");
        String gender = (String) attributes.get("gender");
        String phoneNumber = (String) attributes.get("phoneNumber");


        System.out.println("attributes = " + attributes);
        Optional<User> user = userRepository.findUserByEmail(email);
        if (user.isEmpty()){
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setFirstname(givenName);
            newUser.setLastname(family_name);
            newUser.setFirstname(name);
            newUser.setAvatar(avatarUrl);
            newUser.setAvatar(picture);
            newUser.setPhoneNumber(phoneNumber);
            newUser.setGender(gender);
            newUser.setBirthDate(birthdate);
            userRepository.save(newUser);
        }
        return oAuth2User;
    }
}