package io.swagger.service;

import io.swagger.model.viewobjects.User;
import io.swagger.model.entity.UserCredentialsEntity;
import io.swagger.model.entity.UserProfileEntity;
import io.swagger.repo.UserProfileRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;
    private final PasswordEncoder passwordEncoder;

    public UserProfileService(UserProfileRepository userProfileRepository, PasswordEncoder passwordEncoder) {
        this.userProfileRepository = userProfileRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAllUserProfiles(Integer pageNumber, Integer pageSize){
        Pageable pageable = PageRequest.of(pageNumber.intValue(),pageSize.intValue(), Sort.by("id").descending());
        final var userProfileEntities = userProfileRepository.findAll(pageable).getContent();
        return userProfileEntities.stream().map(UserProfileEntity::mapToUser).collect(Collectors.toList());
    }

    public UserProfileEntity saveUser(final User user, Optional<String> managerEmail, Optional<List<String>> reportee){
        final var userProfileEntity = mapUserToUserProfile(user);
        managerEmail.ifPresent(mgrEmail-> userProfileRepository.findByEmailAndRole(mgrEmail, User.RoleEnum.MGR).ifPresent(userProfileEntity::setManager));
        reportee.ifPresent(emailList-> findAllReportee(emailList).forEach(userProfileEntity::addReportee));
        return userProfileRepository.save(userProfileEntity);
    }
    //org.hibernate.exception.ConstraintViolationException
    public Set<UserProfileEntity> findAllReportee(List<String> emails){
        return userProfileRepository.findAllByRoleAndEmailIn(User.RoleEnum.REPORTEE, Set.copyOf(emails));
    }

    private UserProfileEntity mapUserToUserProfile(final User user){
        final var newUser = new UserProfileEntity();
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setRole(user.getRole());
        newUser.setEmail(user.getEmail());
        newUser.setTag(user.getTag());
        newUser.setAddress(user.getAddress());
        newUser.setPhone(user.getPhone());
        newUser.setAge(user.getAge());
        final UserCredentialsEntity newUserCredentials = getUserCredentialsEntity(user);
        newUser.addUserCredential(newUserCredentials);
        return newUser;
    }

    private UserCredentialsEntity getUserCredentialsEntity(User user) {
        final var newUserCredentials = new UserCredentialsEntity();
        newUserCredentials.setEnabled(true);
        newUserCredentials.setPassword(passwordEncoder.encode(user.getPassword()));
        newUserCredentials.setUsername(user.getEmail());
        return newUserCredentials;
    }
}
