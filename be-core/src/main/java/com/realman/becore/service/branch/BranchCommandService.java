package com.realman.becore.service.branch;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import com.realman.becore.dto.branch.Branch;
import com.realman.becore.dto.branch.BranchId;
import com.realman.becore.dto.branch.BranchMapper;
import com.realman.becore.dto.goong.geocoding.AddressComponent;
import com.realman.becore.dto.goong.geocoding.Geometry;
import com.realman.becore.dto.goong.geocoding.GeometryProperties;
import com.realman.becore.dto.goong.geocoding.GeometryResponse;
import com.realman.becore.dto.goong.geocoding.Location;
import com.realman.becore.error_handlers.exceptions.ResourceNotFoundException;
import com.realman.becore.repository.database.branch.BranchEntity;
import com.realman.becore.repository.database.branch.BranchRepository;
import com.realman.becore.service.branch.display.BranchDisplayCommandService;
import com.realman.becore.service.branch.service.BranchServiceUseCaseService;
import com.realman.becore.service.goong.geocoding.GeocodingUseCaseService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BranchCommandService {
        @NonNull
        private final BranchRepository branchRepository;
        @NonNull
        private final BranchDisplayCommandService branchDisplayCommandService;
        @NonNull
        private final GeocodingUseCaseService geocodingUseCaseService;
        @NonNull
        private final BranchServiceUseCaseService branchServiceUseCaseService;
        @NonNull
        private final BranchMapper branchMapper;

        public void save(Branch branch) {
                GeometryResponse geo = geocodingUseCaseService.requestGeocoding(branch.branchAddress());
                Double lat = geo.results().stream().map(GeometryProperties::geometry).map(Geometry::location)
                                .mapToDouble(Location::lat).findAny().orElse(0);
                Double lng = geo.results().stream().map(GeometryProperties::geometry).map(Geometry::location)
                                .mapToDouble(Location::lng).findAny().orElse(0);
                List<AddressComponent> addressComponents = geo.results().stream()
                                .map(GeometryProperties::addressComponents)
                                .findAny().orElse(new ArrayList<>());
                String city = addressComponents.get(addressComponents.size() - 1).shortName();
                BranchEntity entity = branchMapper.toEntity(branch, city, lat, lng);
                BranchEntity savedEntity = branchRepository.save(entity);
                branchDisplayCommandService.save(branch.branchDisplayList(), savedEntity.getBranchId());
                branchServiceUseCaseService.save(savedEntity.getBranchId(), branch.branchServiceList());
        }

        public void update(BranchId branchId, Branch branch) {
                BranchEntity foundEntity = branchRepository.findById(branchId.value())
                                .orElseThrow(ResourceNotFoundException::new);
                branchMapper.updateEntity(foundEntity, branch);
                branchRepository.save(foundEntity);
                branchDisplayCommandService.update(branchId.value(), branch.branchDisplayList());
                branchServiceUseCaseService.update(branchId.value(), branch.branchServiceList());
        }

        public void delete(BranchId branchId) {
                branchRepository.deleteById(branchId.value());
                branchDisplayCommandService.delete(branchId.value());
        }
}
