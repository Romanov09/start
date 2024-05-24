package application.main.start.dto;

import application.main.start.users.Person;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.time.LocalDate;

@Setter
@Getter
@RequiredArgsConstructor
@NoArgsConstructor
public class ArticleDto {
    @NonNull
    private int id;
    @NonNull
    @Nullable
    private String header;
    @NonNull
    @Nullable
    private String text;
    @NonNull
    @Nullable
    private LocalDate date;
    @NonNull
    private Person person;
}
