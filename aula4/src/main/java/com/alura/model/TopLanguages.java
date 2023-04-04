package com.alura.model;


import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Entity
@Builder
public class TopLanguages {

    @Id
    private long id;
    @Column
    private String title;
    @Column
    private String image;
    @Column
    private int ranking;
}
