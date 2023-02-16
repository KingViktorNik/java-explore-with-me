package ru.practicum.ewm.model;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    /** Идентификатор */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** электронная почта */
    @Column(name = "email", length = 50, nullable = false, unique = true)
    private String email;

    /** имя пользователя */
    @Column(name = "name", length = 50, nullable = false)
    private String name;
}
