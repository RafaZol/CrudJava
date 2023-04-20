//package testeRelatorios;
//
//import java.io.File;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.HashMap;
//import PessoasTools.Desktop;
//
///**
// *
// * @author Rafael
// */
//public class RelPessoas {
//
//    public void abrePdf(String path, HashMap<String, Object> parameters, String sql, Connection conn, String path_rel) {
//
//        try {
//            parameters.put("SUBREPORT_DIR", new File(path).getParent() + "/");
//            parameters.put("REPORT_CONNECTION", conn);
//
//            File file2 = new File(path_rel + ".pdf").getAbsoluteFile();
//            PreparedStatement pstm = conn.prepareStatement(sql, ResultSet.FETCH_UNKNOWN, ResultSet.CONCUR_UPDATABLE);
//            ResultSet rs = pstm.executeQuery();
//            JRResultSetDataSource jrRS = new JRResultSetDataSource(rs);
//            if (!rs.next()) {
//                System.out.println("Relatório não Contem Registros.");
//                return;
//            } else {
//                rs.beforeFirst();
//            }
//
//            String jasperReport = new File(path).getAbsoluteFile().getPath();
//
//            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrRS);
//
//            Exporter<ExporterInput, PdfReportConfiguration, PdfExporterConfiguration, OutputStreamExporterOutput> exportador = new JRPdfExporter();
//            exportador.setExporterInput(new SimpleExporterInput(jasperPrint));
//            exportador.setExporterOutput(new SimpleOutputStreamExporterOutput(file2));
//            exportador.exportReport();
//
//            Desktop.getDesktop().open(file2);
//
//            try {
//                conn.commit();
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//        } catch (JRException ex) {
//            ex.printStackTrace();
//        } catch (SQLException ex) {
//            try {
//                ex.printStackTrace();
//                conn.commit();
//            } catch (Exception ex1) {
//                ex1.printStackTrace();
//            }
//        } catch (Exception ex) {
//            System.out.println("Erro:\n " + ex.getLocalizedMessage());
//            ex.printStackTrace();
//        } finally {
//
//        }
//
//    }
//
//}
