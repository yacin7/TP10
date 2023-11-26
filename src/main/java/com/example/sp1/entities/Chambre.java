package com.example.sp1.entities;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Set;
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Chambre implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Long idChambre;
     long numeroChambre;
     long capacite;
    @Enumerated(EnumType.STRING)
     TypeChambre typeC;
    @OneToMany(cascade = CascadeType.ALL)
     Set<Reservation>reservations;
    @ManyToOne
     Bloc bloc;

    public long getCapacite() {
        return capacite;
    }
}
