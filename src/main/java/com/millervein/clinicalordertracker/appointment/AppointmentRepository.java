package com.millervein.clinicalordertracker.appointment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AppointmentRepository extends JpaRepository<Appointment, String> {

	@Query(value = "SELECT * FROM appointment a "
			+ "WHERE a.patient_id = :patientId "
			+ "AND a.start > :date "
			+ "AND a.type IN ('ABLATION', 'ABLATION_MP') "
			+ "AND a.status != 'CANCELLED' "
			+ "AND a.id NOT IN "
			+ "(SELECT o.linked_appointment_id FROM "
			+ "clinical_order o "
			+ "WHERE a.patient_id = o.patient_id "
			+ "AND o.linked_appointment_id IS NOT NULL "
			+ "AND o.type IN ('ABLATION'))  "
			+ "ORDER BY a.start ASC LIMIT 1", nativeQuery = true)
	public Appointment findPossibleAblationAppointment(@Param("patientId") String patientId,
			@Param("date") String date);
	
	@Query(value = "SELECT * FROM appointment a "
			+ "WHERE a.patient_id = :patientId "
			+ "AND a.start > :date "
			+ "AND a.type IN ('ABLATION_MP','MP') "
			+ "AND a.status != 'CANCELLED' "
			+ "AND a.id NOT IN "
			+ "(SELECT o.linked_appointment_id FROM "
			+ "clinical_order o "
			+ "WHERE a.patient_id = o.patient_id "
			+ "AND o.linked_appointment_id IS NOT NULL "
			+ "AND o.type IN ('MP'))  "
			+ "ORDER BY a.start ASC LIMIT 1", nativeQuery = true)
	public Appointment findPossibleMPAppointment(@Param("patientId") String patientId,
			@Param("date") String date);
	
	@Query(value = "SELECT * FROM appointment a "
			+ "WHERE a.patient_id = :patientId "
			+ "AND a.start > :date "
			+ "AND a.type IN ('EVCA') "
			+ "AND a.status != 'CANCELLED' "
			+ "AND a.id NOT IN "
			+ "(SELECT o.linked_appointment_id FROM "
			+ "clinical_order o "
			+ "WHERE a.patient_id = o.patient_id "
			+ "AND o.linked_appointment_id IS NOT NULL "
			+ "AND o.type IN ('EVCA'))  "
			+ "ORDER BY a.start ASC LIMIT 1", nativeQuery = true)
	public Appointment findPossibleEVCAAppointment(@Param("patientId") String patientId,
			@Param("date") String date);
}
