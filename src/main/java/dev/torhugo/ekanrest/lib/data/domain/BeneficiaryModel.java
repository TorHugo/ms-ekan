package dev.torhugo.ekanrest.lib.data.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "beneficiary_tb")
public class BeneficiaryModel extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "beneficiary_id")
    private Long beneficiaryId;
    private String name;
    private String phoneNumber;
    private LocalDate birthDate;
    private Boolean inActive;
}
