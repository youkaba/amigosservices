package com.amigoscode.customer.model;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {
    @Id
    @SequenceGenerator(
            name = "customer_id_sequence",
            sequenceName = "customer_id_sequence"
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "customer_id_sequence"
    )
    private Integer id;

    private String firstName;
    private String lastName;
    private String email;
}
