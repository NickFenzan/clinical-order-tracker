package com.millervein.clinicalordertracker.clinicalorder;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClinicalOrderRepository extends JpaRepository<ClinicalOrder, String> {
	public List<ClinicalOrder> findByClosed(boolean closed);
	
	public List<ClinicalOrder> findByScheduledAndClosed(boolean scheduled, boolean closed);
	
	@Query("SELECT o FROM ClinicalOrder o WHERE o.scheduled=false AND o.closed=false AND department = :department")
	public List<ClinicalOrder> findNeedSchedulingByDepartment(@Param("department") String department);
}
