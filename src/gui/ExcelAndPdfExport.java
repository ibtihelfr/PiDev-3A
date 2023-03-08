package gui;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.scene.control.TableView;

import entity.Reclamation;

import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelAndPdfExport {
    
    // Necessite l'api java itextPDF
        public void exportPdf() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tunmix", "root", "");
            Statement stmt = con.createStatement();
            // Read list of claims from database
            ResultSet query_set = stmt.executeQuery("SELECT * From reclamation");
            /* Step-2: Initialize PDF documents - logical objects */
            Document my_pdf_report = new Document();
            PdfWriter.getInstance(my_pdf_report, new FileOutputStream("ReclamationsList.pdf"));
            my_pdf_report.open();
            //we have four columns in our table
            PdfPTable my_report_table = new PdfPTable(4);
            //create a cell object
            PdfPCell table_cell;
            writePdfTableHeader(my_report_table);
            Paragraph paragraph = new Paragraph("Ce tableau représente la liste des réclamations ");

            paragraph.setAlignment(Paragraph.ALIGN_CENTER);
            my_pdf_report.add(paragraph);
            my_report_table.setSpacingBefore(10);
            my_report_table.setSpacingAfter(10);


            while (query_set.next()) {
                String dept_id = query_set.getString("Description");
                table_cell = new PdfPCell(new Phrase(Font.BOLD, dept_id));
                my_report_table.addCell(table_cell);
                String dept_name = query_set.getString("DateReclamation");
                table_cell = new PdfPCell(new Phrase(dept_name));
                my_report_table.addCell(table_cell);
                String manager_id = query_set.getString("EtatReclamation");
                table_cell = new PdfPCell(new Phrase(manager_id));
                my_report_table.addCell(table_cell);
                String location_id = query_set.getString("Reponse");
                table_cell = new PdfPCell(new Phrase(location_id));
                my_report_table.addCell(table_cell);
            }

            my_pdf_report.add(my_report_table);
            my_pdf_report.close();
            query_set.close();
            stmt.close();
            con.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

        //Cette méthode va etre appelé par la méthode exportPDF 
    public void writePdfTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(BaseColor.PINK);
        cell.setPadding(5);

        com.itextpdf.text.Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(BaseColor.BLACK);

        cell.setPhrase(new Phrase("Description", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Date Réclamation", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Etat Réclamation", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Réponse", font));
        table.addCell(cell);
    }

    //Necessite L'api java poi
    public void exportExcel(TableView<Reclamation> tableView) {

        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet hssfSheet = hssfWorkbook.createSheet("Sheet1");
        HSSFRow firstRow = hssfSheet.createRow(0);

        ///set titles of columns
        for (int i = 0; i < tableView.getColumns().size(); i++) {

            firstRow.createCell((short) i).setCellValue(tableView.getColumns().get(i).getText());

        }


        for (int row = 0; row < tableView.getItems().size(); row++) {

            HSSFRow hssfRow = hssfSheet.createRow(row + 1);

            for (int col = 0; col < tableView.getColumns().size(); col++) {

                Object celValue = tableView.getColumns().get(col).getCellObservableValue(row).getValue();

                try {
                    if (celValue != null && Double.parseDouble(celValue.toString()) != 0.0) {
                        hssfRow.createCell((short) col).setCellValue(Double.parseDouble(celValue.toString()));
                    }
                } catch (NumberFormatException e) {

                    hssfRow.createCell((short) col).setCellValue(celValue.toString());
                }

            }

        }

        //save excel file and close the workbook
        try {
            hssfWorkbook.write(new FileOutputStream("ReclamationsList.xls"));
        } catch (IOException e) {
            //
        }


    }


}
