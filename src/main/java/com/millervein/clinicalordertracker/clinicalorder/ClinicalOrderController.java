package com.millervein.clinicalordertracker.clinicalorder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clinical-orders")
public class ClinicalOrderController {

	private ClinicalOrderRepository orderRepo;

	@Autowired
	public ClinicalOrderController(ClinicalOrderRepository orderRepo) {
		super();
		this.orderRepo = orderRepo;
	}

	@RequestMapping(value = "/need-scheduling", params = { "department" }, method = RequestMethod.GET)
	public List<ClinicalOrder> needsScheduling(@RequestParam String department) {
		return orderRepo.findNeedSchedulingByDepartment(department);
	}

	@RequestMapping(value = "/{orderId}/scheduled", method = RequestMethod.POST)
	public void orderScheduled(@PathVariable String orderId) throws Exception {
		ClinicalOrder order = getOrderFromPath(orderId);
		order.markAsScheduled();
		orderRepo.saveAndFlush(order);
	}

	@RequestMapping(value = "/close-reasons", method = RequestMethod.GET)
	public List<String> getClinicalOrderCloseReasons() {
		return Arrays.asList(ClinicalOrderCloseReason.values()).stream().map(r -> r.toString())
				.collect(Collectors.toList());
	}

	static class CloseOrderRequest {public String reason;CloseOrderRequest() {}}
	@RequestMapping(value = "/{orderId}/close", method = RequestMethod.POST)
	public void closeOrder(@PathVariable String orderId, @RequestBody CloseOrderRequest request) throws Exception {
		ClinicalOrder order = getOrderFromPath(orderId);
		order.close(ClinicalOrderCloseReason.valueOf(request.reason));
		orderRepo.saveAndFlush(order);
	}

	@RequestMapping("/test")
	public String test() {
		SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().forEach(a->System.out.println(a));
		return "{\"result\":\"Success!\"}";
	}
	
	private ClinicalOrder getOrderFromPath(String orderId) throws Exception {
		ClinicalOrder order = orderRepo.findOne(orderId);
		if (order == null) {
			throw new Exception("Could not find clinical order " + orderId);
		} else {
			return order;
		}
	}

}
