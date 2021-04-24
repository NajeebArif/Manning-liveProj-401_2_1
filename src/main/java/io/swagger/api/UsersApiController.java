package io.swagger.api;

import io.swagger.model.viewobjects.User;
import io.swagger.model.viewobjects.Users;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.service.UserProfileService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-04-23T14:46:36.951Z[GMT]")
@RestController
public class UsersApiController implements UsersApi {

    private static final Logger log = LoggerFactory.getLogger(UsersApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    private final UserProfileService userProfileService;

    @org.springframework.beans.factory.annotation.Autowired
    public UsersApiController(ObjectMapper objectMapper, HttpServletRequest request, UserProfileService userProfileService) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.userProfileService = userProfileService;
    }

    public ResponseEntity<Void> createUser(@Parameter(in = ParameterIn.DEFAULT, description = "Send the User Object", required=true, schema=@Schema())
                                           @Valid @RequestBody User body) {
        final var userProfileEntity = userProfileService.saveUser(body, Optional.ofNullable(body.getManagerName()), Optional.empty());
        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<Users> listUsers(@Parameter(in = ParameterIn.QUERY, description = "How many items to return at one time (max 100)" ,schema=@Schema())
                                               @RequestParam Optional<Integer> limit,
                                           @Parameter(in = ParameterIn.QUERY, description = "What is the current pagination page" ,schema=@Schema())
                                               @RequestParam Optional<Integer> page) {
        final var pageNumber = page.map(Integer::new).orElse(0);
        final var pageSize = limit.map(Integer::new).orElse(100);
        final var allUserProfiles = userProfileService.getAllUserProfiles(pageNumber, pageSize);
        final var users = new Users();
        users.addAll(allUserProfiles);
        return ResponseEntity.ok(users);
    }

    public ResponseEntity<User> showUserById(@Parameter(in = ParameterIn.PATH, description = "The id of the user to retrieve", required=true, schema=@Schema()) @PathVariable("userId") String userId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<User>(objectMapper.readValue("{\n  \"firstName\" : \"firstName\",\n  \"lastName\" : \"lastName\",\n  \"password\" : \"password\",\n  \"role\" : \"MGR\",\n  \"address\" : \"address\",\n  \"phone\" : \"phone\",\n  \"id\" : 0,\n  \"tag\" : \"tag\",\n  \"managerName\" : \"managerName\",\n  \"email\" : \"email\",\n  \"age\" : 6\n}", User.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<User>(HttpStatus.NOT_IMPLEMENTED);
    }

}
