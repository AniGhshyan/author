package author.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Author  {
    private long id;
    private String name;
    private String surname;
    private String email;
    private int age;
    private Gender gender;
    private Date dateOfBirth;

}
