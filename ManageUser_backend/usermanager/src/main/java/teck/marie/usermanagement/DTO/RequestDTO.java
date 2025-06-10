package teck.marie.usermanagement.DTO;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestDTO {

    private String username;

    private String email;

    private String password;
}
