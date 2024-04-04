package com.realman.becore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.realman.becore.service.category.CategoryUseCaseService;
import com.realman.becore.service.itimacy.level.ItimacyLevelUsecaseService;
import com.realman.becore.service.shift.ShiftUseCaseService;
import com.realman.becore.service.timeslot.TimeSlotUseCaseService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class BeCoreApplication {
	@NonNull
	private final ItimacyLevelUsecaseService itimacyLevelUsecaseService;
	@NonNull
	private final CategoryUseCaseService categoryUseCaseService;
	@NonNull
	private final ShiftUseCaseService shiftUseCaseService;
	@NonNull
	private final TimeSlotUseCaseService timeSlotUseCaseService;

	public static void main(String[] args) {
		SpringApplication.run(BeCoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner() {
		return (String... args) -> {
			itimacyLevelUsecaseService.saveDefault();
			shiftUseCaseService.saveDefault();
			categoryUseCaseService.saveDefault();
			timeSlotUseCaseService.saveDefault();
		};
	}
}
