package io.swagger.repo;

import io.swagger.model.entity.UserProfileEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

import static io.swagger.model.viewobjects.User.RoleEnum;

@Repository
public interface UserProfileRepository extends PagingAndSortingRepository<UserProfileEntity, Integer> {

    Optional<UserProfileEntity> findByEmail(String email);

    Optional<UserProfileEntity> findByEmailAndRole(String email, RoleEnum role);
    Set<UserProfileEntity> findAllByRoleAndEmailIn(RoleEnum role, Set<String> emails);
}
