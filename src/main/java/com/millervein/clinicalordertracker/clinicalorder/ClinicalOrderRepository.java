package com.millervein.clinicalordertracker.clinicalorder;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClinicalOrderRepository extends JpaRepository<ClinicalOrder, String> {
	public List<ClinicalOrder> findByClosed(boolean closed);
	
	public List<ClinicalOrder> findByScheduledAndClosed(boolean scheduled, boolean closed);
	
	/**
	 * This query is long because it aims to group all of the patients orders together, but put patients
	 * with the most recent orders first. It takes a department then looks for open and not scheduled appts.
	 * @param department
	 * @return
	 */
	@Query(value = "SELECT c1.* FROM "
			+ "(SELECT patient_id, MAX(date) "
			+ "FROM `clinical_order` "
			+ "WHERE department = :department AND scheduled = 0 AND closed = 0 "
			+ "GROUP BY patient_id ORDER BY date DESC) c "
			+ "LEFT JOIN clinical_order c1 ON c.patient_id = c1.patient_id "
			+ "WHERE department = :department AND scheduled = 0 AND closed = 0", nativeQuery = true)
	public List<ClinicalOrder> findNeedSchedulingByDepartment(@Param("department") String department);
}
