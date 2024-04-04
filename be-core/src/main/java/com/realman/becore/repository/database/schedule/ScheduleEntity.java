package com.realman.becore.repository.database.schedule;

import java.time.LocalDate;

import com.realman.becore.dto.enums.EScheduleStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "schedule")
public class ScheduleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleId;
    private Long staffId;
    private Long shiftId;
    @Temporal(TemporalType.DATE)
    private LocalDate workingDate;
    private EScheduleStatus scheduleStatus;
}
