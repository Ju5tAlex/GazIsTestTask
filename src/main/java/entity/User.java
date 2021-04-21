package entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private long id;
    private String username;
    private String firstname;
    private String lastname;
    private short age;
    private String phoneNumber;

}
