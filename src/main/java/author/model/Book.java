package author.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {
    private long id;
    private String title;
    private String description;
    private double price;
    private int count;
    private Set<Author> authors;

}
