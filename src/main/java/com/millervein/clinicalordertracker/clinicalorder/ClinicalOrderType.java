package com.millervein.clinicalordertracker.clinicalorder;

public enum ClinicalOrderType {
	ABLATION, MP, EVCA;

	public static ClinicalOrderType fromString(String string) throws Exception {
		if (string.equals("PERCUTANEOUS ENDOVENOUS ABLATION THERAPY (PROC)")) {
			return ClinicalOrderType.ABLATION;
		} else if (string.equals("STAB PHLEBECTOMY OF VARICOSE VEINS, ONE EXTREMITY (PROC)")) {
			return ClinicalOrderType.MP;
		} else if (string.equals("INJECTION, SCLEROSING SOLUTION, SINGLE VEIN (PROC)")
				|| string.equals("INJECTION, SCLEROSING SOLUTION, MULTIPLE VEINS, SAME LEG (PROC)")) {
			return ClinicalOrderType.EVCA;
		} else {
			throw new Exception("Unsupported Order Type");
		}
	}
}
