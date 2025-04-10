package com.paw.fund.app.modules.license_management.repository.database.section;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "license_sections")
public class LicenseSectionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long licenseSectionId;

    @Column
    Long licenseId;

    @Column
    String sectionTitle;

    @Column(columnDefinition = "BLOB")
    byte[] sectionContent;

    @Column
    Boolean isRequired;

}
