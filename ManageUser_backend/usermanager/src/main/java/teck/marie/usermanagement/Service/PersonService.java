package teck.marie.usermanagement.Service;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import teck.marie.usermanagement.DTO.RequestDTO;
import teck.marie.usermanagement.DTO.ResponseDTO;
import teck.marie.usermanagement.Entity.Person;
import teck.marie.usermanagement.Repository.PersonRepository;

import java.util.List;
import java.util.Optional;

@Service

public class PersonService {
    private final PersonRepository personRepository;

    // injection des dependances

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }



    // create the user

    public ResponseDTO CreateUser(RequestDTO requestDTO) {
        Person user = new Person();
        user.setUsername(requestDTO.getUsername());
        user.setPassword(requestDTO.getPassword());
        user.setEmail(requestDTO.getEmail());
        Person savedUser = personRepository.save(user);
        return mapToResponse(savedUser);

    }



    // select all the users

    public List<Person>getAllUser() {
       List<Person> listusers = personRepository.findAll();
       return listusers;

    }



    // method mapToResponse
    private ResponseDTO mapToResponse(Person user) {
        return new ResponseDTO(
                user.getUsername(),
                user.getEmail()

        );

    }


    // update user

    public  ResponseEntity <Person> updateUsers(@PathVariable Long id, RequestDTO requestDTO) {
        Optional<Person> user = personRepository.findById(id);

        if(user.isPresent()) {
            Person existingUser = user.get();
            existingUser.setUsername(requestDTO.getUsername());
            existingUser.setPassword(requestDTO.getPassword());
            existingUser.setEmail(requestDTO.getEmail());
            Person updatedUser = personRepository.save(existingUser);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    public void deleteUser(Long id) {
        personRepository.deleteById(id);
    }


    // delete  User






    /*public ResponseDTO updateUsers(Long id, RequestDTO requestDTO) {
        Optional<Users> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            Users user = optionalUser.get();
            user.setUsername(requestDTO.getUsername());
            user.setPassword(requestDTO.getPassword());
            user.setEmail(requestDTO.getEmail());

            Users updatedUser = userRepository.save(user);
            return mapToResponse(updatedUser);
        } else {
            // If the user is not found, returns a ResponseDTO object with an error message
            return new ResponseDTO("error","Utilisateur non trouvé", null);
        }*/






}
