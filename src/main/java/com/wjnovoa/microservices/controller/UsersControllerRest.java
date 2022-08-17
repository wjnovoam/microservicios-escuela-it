package com.wjnovoa.microservices.controller;

import com.wjnovoa.microservices.model.AccountDTO;
import com.wjnovoa.microservices.model.UserDTO;
import com.wjnovoa.microservices.validators.GroupValidatorOnCreate;
import com.wjnovoa.microservices.validators.GroupValidatorOnUpdate;
import io.swagger.annotations.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.hateoas.Link;

import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

/**
 * @author William Johan Novoa Melendrez
 * @date 16/08/2022
 */
@RestController
@RequestMapping(("/users"))
@Api(tags = "User API Rest")
public class UsersControllerRest {

    @GetMapping("/{id}")
    @ApiOperation(notes ="Retrieve one user system by id",value="Get user by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Response ok if the operation was successful"),
            @ApiResponse(code = 404,message = "Response not found if the resource could not be found")})
    public ResponseEntity<UserDTO> getUserById(@ApiParam(example = "1",value = "Identifier for User",
            allowableValues = "1,2,3,4", required = true) @PathVariable(name = "id") Long id){

        System.out.println("Recovery user by id "+ id);

        UserDTO userDTO = new UserDTO(1L, "William");
        userDTO.setEdad(23);
        userDTO.setLastname("Novoa");

        Link withSelfLink = linkTo(methodOn(UsersControllerRest.class)
                .getUserById(userDTO.getId()))
                .withSelfRel();
        userDTO.add(withSelfLink);

        return ResponseEntity.ok(userDTO);
    }

    @GetMapping
    public ResponseEntity<CollectionModel<UserDTO>> listAllUsers(){
       List<UserDTO> users = List.of(
               new UserDTO(1L, "william"),
               new UserDTO(2L, "Johan"),
               new UserDTO(3L, "william")
       );

        for (UserDTO userDTO: users) {
            Link withSelfLink = linkTo(methodOn(UsersControllerRest.class)
                    .getUserById(userDTO.getId()))
                    .withSelfRel();
            userDTO.add(withSelfLink);

            Link accountRel = linkTo(methodOn(UsersControllerRest.class)
                    .getUserAccounts(userDTO.getId()))
                    .withRel("accounts");
            userDTO.add(accountRel);
        }

       Link link = linkTo(methodOn(UsersControllerRest.class).listAllUsers()).withSelfRel();
        CollectionModel<UserDTO> result = CollectionModel.of(users, link);
       return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<String> createUser(@Validated(value = GroupValidatorOnCreate.class) @RequestBody UserDTO userDTO) {
        System.out.println("Creating user "+ userDTO.getName());

        URI location = ServletUriComponentsBuilder.
                fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userDTO.getId())
                .toUri();


        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<UserDTO> updateUser(@Validated(value = GroupValidatorOnUpdate.class) @RequestBody UserDTO userDTO){
        System.out.println("Updating data");

        return ResponseEntity.ok(userDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable(name = "id") Long id){
        System.out.println("Delete user by id "+ id);

        return ResponseEntity.ok(null);
    }

    @GetMapping("/{id}/accounts")
    public ResponseEntity<List<AccountDTO>> getUserAccounts(@PathVariable Long id){
        List<AccountDTO> list = List.of(
                new AccountDTO("Google"),
                new AccountDTO("twitter"),
                new AccountDTO("escuelaIT"));

        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}/accounts/{idAccount}")
    public ResponseEntity<AccountDTO> getUserAccountById(@PathVariable(name = "id") Long id,
                                                               @PathVariable(name = "idAccount") Long idAccount){

        return ResponseEntity.ok(new AccountDTO("Google"));
    }
}