package com.cibertec.models.service;

import java.util.Date;
import java.util.Map;

public interface IReporteService {
	public byte[] generaReporte(Date fechaInicio, Date fechaFin) throws Exception;

	public String generaReportetHtml(String reportName, Map<String, Object> parameters) throws Exception;
}
