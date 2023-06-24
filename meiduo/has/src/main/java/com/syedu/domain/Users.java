package com.syedu.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class Users {

    private Integer id ;
    private String password ;
    private LocalDateTime lastLogin ;
    private Integer isSuperuser ;
    private String username ;
    private String firstName ;
    private String lastName ;
    private String email ;
    private Integer isStaff ;
    private Integer isActive ;
    private LocalDateTime dateJoined ;
    private String mobile ;
    private Integer emailActive ;
    private Integer defaultAddressId  ;
}
