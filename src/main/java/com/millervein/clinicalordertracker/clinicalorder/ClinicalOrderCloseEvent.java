package com.millervein.clinicalordertracker.clinicalorder;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ClinicalOrderCloseEvent {
	@Id
	@GeneratedValue
	private int id;
	@ManyToOne
	private ClinicalOrder clinicalOrder;
	@Enumerated(EnumType.STRING)
	private ClinicalOrderCloseReason reason;
	private LocalDateTime start;

	protected ClinicalOrderCloseEvent() {
	}

	public ClinicalOrderCloseEvent(ClinicalOrder clinicalOrder, ClinicalOrderCloseReason reason, LocalDateTime start) {
		super();
		this.clinicalOrder = clinicalOrder;
		this.reason = reason;
		this.start = start;
	}

	public int getId() {
		return id;
	}

	public ClinicalOrder getClinicalOrder() {
		return clinicalOrder;
	}

	public ClinicalOrderCloseReason getReason() {
		return reason;
	}

	public LocalDateTime getStart() {
		return start;
	}

	@Override
	public String toString() {
		return "ClinicalOrderCloseEvent [id=" + id + ", clinicalOrder=" + clinicalOrder.getId() + ", reason=" + reason
				+ ", start=" + start + "]";
	}

}
