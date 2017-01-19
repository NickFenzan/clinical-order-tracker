package com.millervein.clinicalordertracker.clinicalorder;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.millervein.clinicalordertracker.appointment.Appointment;
import com.millervein.clinicalordertracker.appointment.AppointmentRepository;

@Service
public class ClinicalOrderStateUpdater {
	private AppointmentRepository appointmentRepo;
	private ClinicalOrderRepository clinicalOrderRepo;

	@Autowired
	public ClinicalOrderStateUpdater(AppointmentRepository appointmentRepo, ClinicalOrderRepository clinicalOrderRepo) {
		super();
		this.appointmentRepo = appointmentRepo;
		this.clinicalOrderRepo = clinicalOrderRepo;
	}

	public void run() throws Exception {
		List<ClinicalOrder> openOrders = clinicalOrderRepo.findByClosed(false);
		for (ClinicalOrder order : openOrders) {
			if (!order.isScheduled()) {
				Appointment possibleAppointment = null;
				switch(order.getType()){
				case ABLATION:
					possibleAppointment = appointmentRepo.findPossibleAblationAppointment(order.getPatientId(), order.getDate().toString());
					break;
				case MP:
					possibleAppointment = appointmentRepo.findPossibleMPAppointment(order.getPatientId(), order.getDate().toString());
					break;
				case EVCA:
					possibleAppointment = appointmentRepo.findPossibleEVCAAppointment(order.getPatientId(), order.getDate().toString());
					break;
				}
				if(possibleAppointment != null){
					order.linkAppointment(possibleAppointment);
					clinicalOrderRepo.saveAndFlush(order);
				}
			}
		}
	}
}
