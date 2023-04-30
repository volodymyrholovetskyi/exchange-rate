package ua.vholovetskyi.commons.jpa;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

import static jakarta.persistence.GenerationType.IDENTITY;

@MappedSuperclass
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
@EqualsAndHashCode(of = "uuid")
public class EntityBase {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    protected Long id;
    protected String uuid = UUID.randomUUID().toString();

    @Version
    protected long version;
}
