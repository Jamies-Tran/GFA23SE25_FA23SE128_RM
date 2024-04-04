package com.realman.becore.repository.database.branch;

import java.time.LocalTime;
import com.realman.becore.dto.enums.EBranchStatus;
import com.realman.becore.repository.database.auditable.Auditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Builder
@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "branch")
public class BranchEntity extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long branchId;
    private Long shopOwnerId;
    private String branchName;
    @Column(columnDefinition = "NVARCHAR(250)", name = "thumbnail_url")
    private String branchThumbnailUrl;
    private String branchThumbnailBase64Url;
    @Column(columnDefinition = "NVARCHAR(250)", name = "phone")
    private String branchHotline;
    @Column(columnDefinition = "NVARCHAR(500)", name = "address")
    @NotNull(message = "Nhập địa chỉ chi nhánh")
    private String branchAddress;
    private String city;
    private Double lat;
    private Double lng;
    @Column(name = "status")
    private EBranchStatus branchStatus;
    private Integer numberStaffs;
    @Temporal(TemporalType.TIME)
    private LocalTime open;
    @Temporal(TemporalType.TIME)
    private LocalTime close;
}
