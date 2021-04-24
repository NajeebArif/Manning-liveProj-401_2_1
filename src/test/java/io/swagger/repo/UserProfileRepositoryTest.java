package io.swagger.repo;

import io.swagger.model.entity.UserProfileEntity;
import io.swagger.service.UserProfileService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static io.swagger.model.viewobjects.User.*;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("User Profile Repository Sepcs:")
@SpringBootTest
class UserProfileRepositoryTest {

    public static final String JOHN_EMAIL = "john@email.com";
    public static final String ALICE_EMAIL = "alice@email.com";
    public static final String TEST_MGR_01_EMAIL = "testMgr01@email.com";
    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private UserProfileService userProfileService;


    @Test
    @DisplayName("should return all two rows.")
    public void testAddUser(){
        final var all = userProfileRepository.findAll();
        assertThat(all).isNotNull().isNotEmpty().hasSize(2);
    }

    @Test
    @DisplayName("should return the user info for email and role")
    public void testFindByEmailAndRole(){
        final var userProfileEntity = getUserProfileEntity(JOHN_EMAIL, RoleEnum.MGR);
        assertThat(userProfileEntity.getFirstName()).isNotNull().isEqualTo("John");
    }

    @Test
    @DisplayName("Adding a manager should be successful.")
    @Transactional
    public void addManager(){

        var john = getUserProfileEntity(JOHN_EMAIL, RoleEnum.MGR);
        var alice = getUserProfileEntity(ALICE_EMAIL, RoleEnum.REPORTEE);

//        userProfileService.addNewEmployee();

        final var testMgr = userProfileRepository.findByEmailAndRole(TEST_MGR_01_EMAIL, RoleEnum.MGR).orElseThrow(() -> new AssertionError("User name not found with the given role."));
        assertThat(testMgr.getManager()).isEqualTo(john);
        assertThat(testMgr.getReportee()).hasSize(1).containsExactly(alice);
    }

    private UserProfileEntity getUserProfileEntity(String email, RoleEnum role) {
        return userProfileRepository.findByEmailAndRole(email, role).orElseThrow(() -> new AssertionError("User name not found with the given role."));
    }
}