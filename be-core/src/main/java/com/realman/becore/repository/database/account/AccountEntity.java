package com.realman.becore.repository.database.account;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.realman.becore.enums.EGender;
import com.realman.becore.enums.ERole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "account")
public class AccountEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;
    @Column(columnDefinition = "NVARCHAR(500)")
    private String firstName;
    @Column(columnDefinition = "NVARCHAR(500)")
    private String lastName;
    private String phone;
    @Column(columnDefinition = "NVARCHAR(500)")
    private String address;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dob;
    private EGender gender;
    private ERole role;
}
