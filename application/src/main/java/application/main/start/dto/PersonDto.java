package application.main.start.dto;

import application.main.start.model.Article;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@RequiredArgsConstructor
@NoArgsConstructor
public class PersonDto {
    @NonNull
    private Integer id;
    @Nullable
    private String firstName;
    @Nullable
    private String lastName;
    @Nullable
    private Date birthDay;
    @Nullable
    private List<Article> articles;
}
