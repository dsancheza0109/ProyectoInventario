package com.cibertec.models.service;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.sf.jasperreports.engine.JasperCompileManager;


@Service
public class ReporteServiceImpl implements IReporteService {
	
    @Autowired
    private DataSource dataSource;

    public byte[] generateReport(String reportName, Date fechaInicio, Date fechaFin) throws Exception {
        // Obtener Fecha y Hora actual para el reporte
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy HH:mm:ss");
        String formattedDateTime = localDateTime.format(formatter);

        // Pasando parámetros al jasper
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("IMAGE_DIR", this.getClass().getResource("/static/img/").toString());
        parameters.put("REPOR_DIR", "classpath:/reportes/");
        parameters.put("FECHA_REPORTE", "Fecha de Reporte: Hoy, " + formattedDateTime);
        parameters.put("fechaInicio", fechaInicio);
        parameters.put("fechaFin", fechaFin);

        InputStream jasperStream = this.getClass().getResourceAsStream("/reportes/" + reportName + ".jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperStream);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource.getConnection());

        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

    public String generaReportetHtml(Date fechaInicio, Date fechaFin) throws Exception {
        // Filtrar productos por fecha (asume que el repositorio tiene el método adecuado)
        List<Producto> productos = productoRepository.findByFechaBetween(fechaInicio, fechaFin);

        // Load and compile .jrxml file
        InputStream reportStream = getClass().getResourceAsStream("/reports/productos_report.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

        // Parameters
        Map<String, Object> parameters = Map.of("fechaInicio", fechaInicio, "fechaFin", fechaFin);

        // Data source
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(productos);

        // Fill the report
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        // Export the report to HTML
        HtmlExporter exporter = new HtmlExporter();
        ByteArrayOutputStream htmlStream = new ByteArrayOutputStream();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleHtmlExporterOutput(htmlStream));

        SimpleHtmlReportConfiguration reportConfig = new SimpleHtmlReportConfiguration();
        SimpleHtmlExporterConfiguration exportConfig = new SimpleHtmlExporterConfiguration();

        exporter.setConfiguration(reportConfig);
        exporter.setConfiguration(exportConfig);

        exporter.exportReport();

        return htmlStream.toString("UTF-8");
    }
    
}
