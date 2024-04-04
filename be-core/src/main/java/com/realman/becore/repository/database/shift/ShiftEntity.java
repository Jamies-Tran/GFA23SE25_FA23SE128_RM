package com.realman.becore.repository.database.shift;

import java.time.LocalTime;

import com.realman.becore.dto.enums.EShift;

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
@Table(name = "shift")
public class ShiftEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shiftId;
    private EShift shift;
    @Temporal(TemporalType.TIME)
    private LocalTime startShift;
    @Temporal(TemporalType.TIME)
    private LocalTime endShift;
}
