package com.wjnovoa.microservices.model;

import com.wjnovoa.microservices.validators.CIF;
import com.wjnovoa.microservices.validators.GroupValidatorOnCreate;
import com.wjnovoa.microservices.validators.GroupValidatorOnUpdate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.time.LocalDate;

/**
 * @author William Johan Novoa Melendrez
 * @date 16/08/2022
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
@ApiModel(description = "System user")
@Entity(name = "ms_users")
public class UserDTO extends RepresentationModel<UserDTO> {

    @NonNull
    @NotNull
    @ApiModelProperty(notes = "Unique identifier of the User.", example = "1", required = true, position = 0)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NonNull
    @NotBlank
    private String name;

    @NotNull
    @Size(min = 6, max = 20)
    private String lastname;

    //@Positive
    @Min(18)
    @Max(90)
    @ToString.Exclude
    private int edad;

    @Email
    @ApiModelProperty(example = "string@gmail.com")
    private String email;

    @AssertTrue(message = "{app.field.active.error}", groups = GroupValidatorOnCreate.class)
    @AssertFalse(message = "{app.field.active.error}", groups = GroupValidatorOnUpdate.class)
    private boolean active;

    //@FutureOrPresent
    //@Future
    @Past(message = "{app.field.birth_day.error}")
    @ApiModelProperty(example = "2025-01-01")
    private LocalDate birthDay;

    @CIF
    private String cif;

    private String title;

    private String body;
}