package by.egor.inmemorybroker;

import lombok.*;

@Getter
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
public class Phrase {
    @NonNull
    private String value;
}
