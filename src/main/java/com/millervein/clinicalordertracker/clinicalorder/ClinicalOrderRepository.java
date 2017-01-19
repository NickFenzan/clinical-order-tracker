package com.millervein.clinicalordertracker.clinicalorder;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClinicalOrderRepository extends JpaRepository<ClinicalOrder, String> {
	public List<ClinicalOrder> findByClosed(boolean closed);
}
