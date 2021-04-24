package io.swagger.api;

import io.swagger.model.viewobjects.Leave;
import io.swagger.model.viewobjects.Leaves;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-04-23T14:46:36.951Z[GMT]")
@RestController
public class LeavesApiController implements LeavesApi {

    private static final Logger log = LoggerFactory.getLogger(LeavesApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public LeavesApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Boolean> approveLeaves(@Parameter(in = ParameterIn.PATH, description = "The id of the leave to retrieve", required=true, schema=@Schema()) @PathVariable("leaveId") String leaveId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Boolean>(objectMapper.readValue("false", Boolean.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Boolean>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Boolean>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Leaves> listLeaves(@Parameter(in = ParameterIn.QUERY, description = "status of the leave REQUESTED,APPROVED,DENIED" ,schema=@Schema(allowableValues={ "REQUESTED", "APPROVED", "DENIED" }
)) @Valid @RequestParam(value = "status", required = false) String status,@Parameter(in = ParameterIn.QUERY, description = "if true returns leaves of reportees by name for approval" ,schema=@Schema()) @Valid @RequestParam(value = "reportee", required = false) String reportee,@Parameter(in = ParameterIn.QUERY, description = "How many items to return at one time (max 100)" ,schema=@Schema()) @Valid @RequestParam(value = "limit", required = false) Integer limit,@Parameter(in = ParameterIn.QUERY, description = "What is the current pagination page" ,schema=@Schema()) @Valid @RequestParam(value = "page", required = false) Integer page) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Leaves>(objectMapper.readValue("[ {\n  \"reason\" : \"reason\",\n  \"endDate\" : \"2000-01-23\",\n  \"days\" : 1,\n  \"comment\" : \"comment\",\n  \"id\" : 0,\n  \"type\" : \"SICK_LEAVE\",\n  \"userId\" : 6,\n  \"startDate\" : \"2000-01-23\",\n  \"status\" : \"REQUESTED\"\n}, {\n  \"reason\" : \"reason\",\n  \"endDate\" : \"2000-01-23\",\n  \"days\" : 1,\n  \"comment\" : \"comment\",\n  \"id\" : 0,\n  \"type\" : \"SICK_LEAVE\",\n  \"userId\" : 6,\n  \"startDate\" : \"2000-01-23\",\n  \"status\" : \"REQUESTED\"\n} ]", Leaves.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Leaves>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Leaves>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> requestLeave(@Parameter(in = ParameterIn.DEFAULT, description = "Send the Leave Object", required=true, schema=@Schema()) @Valid @RequestBody Leave body) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Leave> showLeaveById(@Parameter(in = ParameterIn.PATH, description = "The id of the leave to retrieve", required=true, schema=@Schema()) @PathVariable("leaveId") String leaveId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Leave>(objectMapper.readValue("{\n  \"reason\" : \"reason\",\n  \"endDate\" : \"2000-01-23\",\n  \"days\" : 1,\n  \"comment\" : \"comment\",\n  \"id\" : 0,\n  \"type\" : \"SICK_LEAVE\",\n  \"userId\" : 6,\n  \"startDate\" : \"2000-01-23\",\n  \"status\" : \"REQUESTED\"\n}", Leave.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Leave>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Leave>(HttpStatus.NOT_IMPLEMENTED);
    }

}
