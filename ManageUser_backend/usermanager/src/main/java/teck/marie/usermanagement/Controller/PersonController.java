package teck.marie.usermanagement.Controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import teck.marie.usermanagement.DTO.RequestDTO;
import teck.marie.usermanagement.DTO.ResponseDTO;
import teck.marie.usermanagement.Entity.Person;
import teck.marie.usermanagement.Service.PersonService;

import java.util.List;

@RestController
@Tag(name = "Users", description = "a user who can register and authenticate")
@RequestMapping(path = "/person")

public class PersonController {

    private final PersonService personService;

    // injection of service Layer

    public PersonController(PersonService personService) {
        this.personService = personService;
    }


    // create the users
    @Operation(summary = "create user")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping

   public ResponseEntity<ResponseDTO> createUser(@RequestBody RequestDTO requestDTO) {
        return new ResponseEntity<>(personService.CreateUser(requestDTO),HttpStatus.CREATED);
    }




    // select the user
    @Operation(summary = "view the user List")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping

    public ResponseEntity<List<Person>> getAllUsers() {
        return ResponseEntity.ok(personService.getAllUser());
    }



    // Update the users
      @Operation(summary = "Update a User")
      @ResponseStatus(HttpStatus.OK)
      @PutMapping(path = "/{id}")

      public ResponseEntity<Person> updateUser(@PathVariable Long id, @RequestBody RequestDTO requestDTO) {
        ResponseEntity<Person> updated = personService.updateUsers(id, requestDTO);
        return updated;


      }



    // delete the user
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "Delete a User")
    @DeleteMapping(path = "/{id}")

    public ResponseEntity<ResponseDTO> deleteUsers(@PathVariable Long id) {
        personService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }


}

