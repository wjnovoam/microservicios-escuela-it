package com.wjnovoa.microservices.controller;

import com.wjnovoa.microservices.model.AccountDTO;
import com.wjnovoa.microservices.model.UserDTO;
import com.wjnovoa.microservices.service.UserService;
import com.wjnovoa.microservices.validators.GroupValidatorOnCreate;
import com.wjnovoa.microservices.validators.GroupValidatorOnUpdate;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * @author William Johan Novoa Melendrez
 * @date 16/08/2022
 */
@RestController
@RequestMapping(("/users"))
@Api(tags = "User API Rest")
public class UsersControllerRest {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    @ApiOperation(notes ="Retrieve one user system by id",value="Get user by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Response ok if the operation was successful"),
            @ApiResponse(code = 404,message = "Response not found if the resource could not be found")})
    public ResponseEntity<UserDTO> getUserById(@ApiParam(example = "1",value = "Identifier for User",
            allowableValues = "1,2,3,4", required = true) @PathVariable(name = "id") Long id){

        try {
            Optional<UserDTO> optUserDTO = userService.getUserById(id);
            UserDTO userDTO = optUserDTO.orElseThrow(NoSuchElementException::new);

            Link withSelfLink = linkTo(methodOn(UsersControllerRest.class)
                    .getUserById(userDTO.getId()))
                    .withSelfRel();
            userDTO.add(withSelfLink);

            return ResponseEntity.ok(userDTO);

        }catch (NoSuchElementException exception){

            return ResponseEntity.notFound().build();
        }


    }

    @GetMapping
    public ResponseEntity<CollectionModel<UserDTO>> listAllUsers(@PageableDefault(size = 3,
            sort = {"edad","name"},direction = Sort.Direction.ASC)Pageable pageable){
       List<UserDTO> users = userService.listAllUsers(pageable);

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

       Link link = linkTo(methodOn(UsersControllerRest.class).listAllUsers(pageable)).withSelfRel();
        CollectionModel<UserDTO> result = CollectionModel.of(users, link);
       return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<String> createUser(@Validated(value = GroupValidatorOnCreate.class) @RequestBody UserDTO userDTO) {
        System.out.println("Creating user "+ userDTO.getName());

        UserDTO saveUser = userService.saveUser(userDTO);

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
        userService.deleteById(id);

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