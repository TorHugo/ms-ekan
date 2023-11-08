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
@Table(name = "document_tb")
public class DocumentModel extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "document_id")
    private Long documentId;
    private Long beneficiaryId;
    private String typeDocument;
    private String description;
    private boolean inActive;
}
