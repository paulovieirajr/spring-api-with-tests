package io.github.vieirajunior90.apiwithtests.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserDto implements Serializable {
    private Integer id;
    private String name;
    private String email;

    @JsonIgnore
    private String password;
}
