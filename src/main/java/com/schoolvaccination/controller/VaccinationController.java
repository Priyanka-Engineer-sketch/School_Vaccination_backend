package com.schoolvaccination.controller;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.schoolvaccination.dto.DashboardStatsDTO;
import com.schoolvaccination.dto.UpcomingDriveDTO;
import com.schoolvaccination.entity.FrontLiner;
import com.schoolvaccination.entity.FrontLinerCategory;
import com.schoolvaccination.entity.Registration;
import com.schoolvaccination.entity.Student;
import com.schoolvaccination.entity.VaccinationCenter;
import com.schoolvaccination.entity.VaccinationRecord;
import com.schoolvaccination.entity.VaccinationSchedule;
import com.schoolvaccination.entity.VaccineInfo;
import com.vaccination.repository.FrontLinerCategoryRepository;
import com.vaccination.repository.FrontLinerRepository;
import com.vaccination.repository.RegistrationRepository;
import com.vaccination.repository.StudentRepository;
import com.vaccination.repository.VaccinationCenterRepository;
import com.vaccination.repository.VaccinationRecordRepository;
import com.vaccination.repository.VaccinationScheduleRepository;
import com.vaccination.repository.VaccineInfoRepository;
import com.vaccination.repository.mongo.FrontLinerCategoryMongoRepository;
import com.vaccination.repository.mongo.FrontLinerMongoRepository;
import com.vaccination.repository.mongo.RegistrationMongoRepository;
import com.vaccination.repository.mongo.StudentMongoRepository;
import com.vaccination.repository.mongo.VaccinationCenterMongoRepository;
import com.vaccination.repository.mongo.VaccinationRecordMongoRepository;
import com.vaccination.repository.mongo.VaccinationScheduleMongoRepository;
import com.vaccination.repository.mongo.VaccineInfoMongoRepository;

@RestController
@RequestMapping("/api/v1")
public class VaccinationController {

	private static final Logger logger = LoggerFactory.getLogger(VaccinationController.class);

	private final StudentRepository studentRepository;
	private final StudentMongoRepository studentMongoRepository;
	private final FrontLinerRepository frontLinerRepository;
	private final FrontLinerMongoRepository frontLinerMongoRepository;
	private final FrontLinerCategoryRepository frontLinerCategoryRepository;
	private final FrontLinerCategoryMongoRepository frontLinerCategoryMongoRepository;
	private final VaccinationCenterRepository vaccinationCenterRepository;
	private final VaccinationCenterMongoRepository vaccinationCenterMongoRepository;
	private final VaccinationRecordRepository vaccinationRecordRepository;
	private final VaccinationRecordMongoRepository vaccinationRecordMongoRepository;
	private final VaccinationScheduleRepository vaccinationScheduleRepository;
	private final VaccinationScheduleMongoRepository vaccinationScheduleMongoRepository;
	private final VaccineInfoRepository vaccineInfoRepository;
	private final VaccineInfoMongoRepository vaccineInfoMongoRepository;
	private final RegistrationRepository registrationRepository;
	private final RegistrationMongoRepository registrationMongoRepository;

	public VaccinationController(StudentRepository studentRepository, StudentMongoRepository studentMongoRepository,
			FrontLinerRepository frontLinerRepository, FrontLinerMongoRepository frontLinerMongoRepository,
			FrontLinerCategoryRepository frontLinerCategoryRepository,
			FrontLinerCategoryMongoRepository frontLinerCategoryMongoRepository,
			VaccinationCenterRepository vaccinationCenterRepository,
			VaccinationCenterMongoRepository vaccinationCenterMongoRepository,
			VaccinationRecordRepository vaccinationRecordRepository,
			VaccinationRecordMongoRepository vaccinationRecordMongoRepository,
			VaccinationScheduleRepository vaccinationScheduleRepository,
			VaccinationScheduleMongoRepository vaccinationScheduleMongoRepository,
			VaccineInfoRepository vaccineInfoRepository, VaccineInfoMongoRepository vaccineInfoMongoRepository,
			RegistrationRepository registrationRepository, RegistrationMongoRepository registrationMongoRepository) {
		this.studentRepository = studentRepository;
		this.studentMongoRepository = studentMongoRepository;
		this.frontLinerRepository = frontLinerRepository;
		this.frontLinerMongoRepository = frontLinerMongoRepository;
		this.frontLinerCategoryRepository = frontLinerCategoryRepository;
		this.frontLinerCategoryMongoRepository = frontLinerCategoryMongoRepository;
		this.vaccinationCenterRepository = vaccinationCenterRepository;
		this.vaccinationCenterMongoRepository = vaccinationCenterMongoRepository;
		this.vaccinationRecordRepository = vaccinationRecordRepository;
		this.vaccinationRecordMongoRepository = vaccinationRecordMongoRepository;
		this.vaccinationScheduleRepository = vaccinationScheduleRepository;
		this.vaccinationScheduleMongoRepository = vaccinationScheduleMongoRepository;
		this.vaccineInfoRepository = vaccineInfoRepository;
		this.vaccineInfoMongoRepository = vaccineInfoMongoRepository;
		this.registrationRepository = registrationRepository;
		this.registrationMongoRepository = registrationMongoRepository;
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> handleInvalidDbParam(IllegalArgumentException e) {
		return ResponseEntity.badRequest().body(e.getMessage());
	}

	@PostMapping("/vaccines")
	public ResponseEntity<List<VaccineInfo>> createVaccines(@RequestBody List<VaccineInfo> vaccines) {
		logger.info("Creating batch of vaccine info: {}", vaccines.size());
		List<VaccineInfo> savedJpa = vaccineInfoRepository.saveAll(vaccines);
		vaccineInfoMongoRepository.saveAll(vaccines);
		return ResponseEntity.ok(savedJpa);
	}

	@PutMapping("/vaccines/{id}")
	public ResponseEntity<VaccineInfo> updateVaccine(@PathVariable Long id, @RequestBody VaccineInfo vaccineInfo) {
		logger.info("Updating vaccine info with id {}: {}", id, vaccineInfo);
		vaccineInfo.setVaccineId(id);
		VaccineInfo updatedJpa = vaccineInfoRepository.save(vaccineInfo);
		vaccineInfoMongoRepository.save(vaccineInfo);
		return ResponseEntity.ok(updatedJpa);
	}

	@DeleteMapping("/vaccines/{id}")
	public ResponseEntity<Void> deleteVaccine(@PathVariable Long id) {
		logger.info("Deleting vaccine info with id: {}", id);
		vaccineInfoRepository.deleteById(id);
		vaccineInfoMongoRepository.deleteById(String.valueOf(id));
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/frontliners")
	public ResponseEntity<List<FrontLiner>> createFrontLiners(@RequestBody List<FrontLiner> frontLiners) {
		logger.info("Creating batch of frontliners: {}", frontLiners.size());
		List<FrontLiner> savedJpa = frontLinerRepository.saveAll(frontLiners);
		frontLinerMongoRepository.saveAll(frontLiners);
		return ResponseEntity.ok(savedJpa);
	}

	@PutMapping("/frontliners/{id}")
	public ResponseEntity<FrontLiner> updateFrontLiner(@PathVariable Long id, @RequestBody FrontLiner frontLiner) {
		logger.info("Updating frontliner with id {}: {}", id, frontLiner);
		frontLiner.setFrontLinerId(id);
		FrontLiner updatedJpa = frontLinerRepository.save(frontLiner);
		frontLinerMongoRepository.save(frontLiner);
		return ResponseEntity.ok(updatedJpa);
	}

	@DeleteMapping("/frontliners/{id}")
	public ResponseEntity<Void> deleteFrontLiner(@PathVariable Long id) {
		logger.info("Deleting frontliner with id: {}", id);
		frontLinerRepository.deleteById(id);
		frontLinerMongoRepository.deleteById(String.valueOf(id));
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/schedules")
	public ResponseEntity<List<VaccinationSchedule>> createSchedules(@RequestBody List<VaccinationSchedule> schedules) {
		logger.info("Creating batch of schedules: {}", schedules.size());
		List<VaccinationSchedule> savedJpa = vaccinationScheduleRepository.saveAll(schedules);
		vaccinationScheduleMongoRepository.saveAll(schedules);
		return ResponseEntity.ok(savedJpa);
	}

	@PutMapping("/schedules/{id}")
	public ResponseEntity<VaccinationSchedule> updateSchedule(@PathVariable Long id,
			@RequestBody VaccinationSchedule schedule) {
		logger.info("Updating schedule with id {}: {}", id, schedule);
		schedule.setScheduleId(id);
		VaccinationSchedule updatedJpa = vaccinationScheduleRepository.save(schedule);
		vaccinationScheduleMongoRepository.save(schedule);
		return ResponseEntity.ok(updatedJpa);
	}

	@DeleteMapping("/schedules/{id}")
	public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {
		logger.info("Deleting schedule with id: {}", id);
		vaccinationScheduleRepository.deleteById(id);
		vaccinationScheduleMongoRepository.deleteById(String.valueOf(id));
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/records")
	public ResponseEntity<List<VaccinationRecord>> createRecords(@RequestBody List<VaccinationRecord> records) {
		logger.info("Creating batch of vaccination records: {}", records.size());
		List<VaccinationRecord> savedJpa = vaccinationRecordRepository.saveAll(records);
		vaccinationRecordMongoRepository.saveAll(records);
		return ResponseEntity.ok(savedJpa);
	}

	@PutMapping("/records/{id}")
	public ResponseEntity<VaccinationRecord> updateRecord(@PathVariable Long id,
			@RequestBody VaccinationRecord record) {
		logger.info("Updating vaccination record with id {}: {}", id, record);
		record.setRecordId(id);
		VaccinationRecord updatedJpa = vaccinationRecordRepository.save(record);
		vaccinationRecordMongoRepository.save(record);
		return ResponseEntity.ok(updatedJpa);
	}

	@DeleteMapping("/records/{id}")
	public ResponseEntity<Void> deleteRecord(@PathVariable Long id) {
		logger.info("Deleting vaccination record with id: {}", id);
		vaccinationRecordRepository.deleteById(id);
		vaccinationRecordMongoRepository.deleteById(String.valueOf(id));
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/students")
	public ResponseEntity<List<Student>> createStudents(@RequestBody List<Student> students) {
		logger.info("Creating batch of students: {}", students.size());
		List<Student> savedJpa = studentRepository.saveAll(students);
		studentMongoRepository.saveAll(students);
		return ResponseEntity.ok(savedJpa);
	}

	@PutMapping("/students/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
		logger.info("Updating student with id {}: {}", id, student);
		student.setStudentId(id);
		Student updatedJpa = studentRepository.save(student);
		studentMongoRepository.save(student);
		return ResponseEntity.ok(updatedJpa);
	}

	@DeleteMapping("/students/{id}")
	public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
		logger.info("Deleting student with id: {}", id);
		studentRepository.deleteById(id);
		studentMongoRepository.deleteById(String.valueOf(id));
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/vaccination-centers")
	public ResponseEntity<List<VaccinationCenter>> createCenters(@RequestBody List<VaccinationCenter> centers) {
		logger.info("Creating batch of vaccination centers: {}", centers.size());
		List<VaccinationCenter> savedJpa = vaccinationCenterRepository.saveAll(centers);
		vaccinationCenterMongoRepository.saveAll(centers);
		return ResponseEntity.ok(savedJpa);
	}

	@PutMapping("/vaccination-centers/{id}")
	public ResponseEntity<VaccinationCenter> updateCenter(@PathVariable Long id,
			@RequestBody VaccinationCenter center) {
		logger.info("Updating vaccination center with id {}: {}", id, center);
		center.setCenterId(id);
		VaccinationCenter updatedJpa = vaccinationCenterRepository.save(center);
		vaccinationCenterMongoRepository.save(center);
		return ResponseEntity.ok(updatedJpa);
	}

	@DeleteMapping("/vaccination-centers/{id}")
	public ResponseEntity<Void> deleteCenter(@PathVariable Long id) {
		logger.info("Deleting vaccination center with id: {}", id);
		vaccinationCenterRepository.deleteById(id);
		vaccinationCenterMongoRepository.deleteById(String.valueOf(id));
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/students")
	public ResponseEntity<List<Student>> getAllStudents(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		logger.info("Fetching all students with pagination");
		Pageable pageable = PageRequest.of(page, size);
		return ResponseEntity.ok(studentRepository.findAll(pageable).getContent());
	}

	@GetMapping("/frontliners")
	public ResponseEntity<List<FrontLiner>> getAllFrontLiners(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		logger.info("Fetching all frontliners with pagination");
		Pageable pageable = PageRequest.of(page, size);
		return ResponseEntity.ok(frontLinerRepository.findAll(pageable).getContent());
	}

	@GetMapping("/schedules")
	public ResponseEntity<List<VaccinationSchedule>> getAllSchedules(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		logger.info("Fetching all schedules with pagination");
		Pageable pageable = PageRequest.of(page, size);
		return ResponseEntity.ok(vaccinationScheduleRepository.findAll(pageable).getContent());
	}

	@GetMapping("/records")
	public ResponseEntity<List<VaccinationRecord>> getAllRecords(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		logger.info("Fetching all vaccination records with pagination");
		Pageable pageable = PageRequest.of(page, size);
		return ResponseEntity.ok(vaccinationRecordRepository.findAll(pageable).getContent());
	}

	@GetMapping("/vaccination-centers")
	public ResponseEntity<List<VaccinationCenter>> getAllCenters(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		logger.info("Fetching all vaccination centers with pagination");
		Pageable pageable = PageRequest.of(page, size);
		return ResponseEntity.ok(vaccinationCenterRepository.findAll(pageable).getContent());
	}

	@GetMapping("/vaccines")
	public ResponseEntity<List<VaccineInfo>> getAllVaccines(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		logger.info("Fetching all vaccines with pagination");
		Pageable pageable = PageRequest.of(page, size);
		return ResponseEntity.ok(vaccineInfoRepository.findAll(pageable).getContent());
	}

	@GetMapping("/frontliner-categories")
	public ResponseEntity<List<FrontLinerCategory>> getAllFrontLinerCategories(
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		logger.info("Fetching all frontliner categories with pagination");
		Pageable pageable = PageRequest.of(page, size);
		return ResponseEntity.ok(frontLinerCategoryRepository.findAll(pageable).getContent());
	}

	@GetMapping("/registrations")
	public ResponseEntity<List<Registration>> getAllRegistrations(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		logger.info("Fetching all registrations with pagination");
		Pageable pageable = PageRequest.of(page, size);
		return ResponseEntity.ok(registrationRepository.findAll(pageable).getContent());
	}

	@DeleteMapping("/vaccines")
	public ResponseEntity<Void> deleteAllVaccines() {
		logger.info("Deleting all vaccine info records");
		vaccineInfoRepository.deleteAll();
		vaccineInfoMongoRepository.deleteAll();
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/frontliners")
	public ResponseEntity<Void> deleteAllFrontLiners() {
		logger.info("Deleting all frontliner records");
		frontLinerRepository.deleteAll();
		frontLinerMongoRepository.deleteAll();
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/schedules")
	public ResponseEntity<Void> deleteAllSchedules() {
		logger.info("Deleting all schedules");
		vaccinationScheduleRepository.deleteAll();
		vaccinationScheduleMongoRepository.deleteAll();
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/records")
	public ResponseEntity<Void> deleteAllRecords() {
		logger.info("Deleting all vaccination records");
		vaccinationRecordRepository.deleteAll();
		vaccinationRecordMongoRepository.deleteAll();
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/students")
	public ResponseEntity<Void> deleteAllStudents() {
		logger.info("Deleting all student records");
		studentRepository.deleteAll();
		studentMongoRepository.deleteAll();
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/vaccination-centers")
	public ResponseEntity<Void> deleteAllCenters() {
		logger.info("Deleting all vaccination centers");
		vaccinationCenterRepository.deleteAll();
		vaccinationCenterMongoRepository.deleteAll();
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/frontliner-categories")
	public ResponseEntity<Void> deleteAllFrontlinerCategories() {
		logger.info("Deleting all frontliner categories");
		frontLinerCategoryRepository.deleteAll();
		frontLinerCategoryMongoRepository.deleteAll();
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/registrations")
	public ResponseEntity<Void> deleteAllRegistrations() {
		logger.info("Deleting all registrations");
		registrationRepository.deleteAll();
		registrationMongoRepository.deleteAll();
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/dashboard/stats")
	public ResponseEntity<DashboardStatsDTO> getDashboardStats() {
		long totalStudents = studentRepository.count();
		long vaccinatedStudents = studentRepository.countByVaccinatedTrue();
		double vaccinatedPercentage = totalStudents > 0 ? ((double) vaccinatedStudents / totalStudents) * 100 : 0;

		LocalDate today = LocalDate.now();
		LocalDate endDate = today.plusDays(30);
		List<VaccinationSchedule> upcoming = vaccinationScheduleRepository.findByScheduledDateBetween(today, endDate);

		List<UpcomingDriveDTO> drives = upcoming.stream().map(schedule -> new UpcomingDriveDTO(schedule.getDate(),
				schedule.getTimeStart(), schedule.getTimeEnd(), schedule.getVaccinationCenter().getCenterName()))
				.toList();

		DashboardStatsDTO stats = new DashboardStatsDTO();
		stats.setTotalStudents(totalStudents);
		stats.setVaccinatedStudents(vaccinatedStudents);
		stats.setVaccinatedPercentage(vaccinatedPercentage);
		stats.setUpcomingDrives(drives);

		return ResponseEntity.ok(stats);
	}

}
