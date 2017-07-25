package net.heliantum.understandable.utils.json_creator;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.heliantum.understandable.database.entity.CustomWordEntity;
import net.heliantum.understandable.utils.json_creator.pattern.CustomWordEntityPatternMap;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

/**
 * Created by Marcin on 2017-07-25.
 */

public class CustomWordsSetJsonCreator extends BaseJsonCreator {

    private static CustomWordEntityPatternMap customWordEntityMap = new CustomWordEntityPatternMap();

    public CustomWordsSetJsonCreator(Context context, File input) {
        super(input, context);
    }

    public void create() {
        try {
            File outputDirectory = new File(context.getFilesDir(), "/words_sets");
            String fileName = input.getName().substring(0, 6) + ".json";
            File output = new File(outputDirectory, fileName);
            if(output.exists()) {
                output.delete();
            }
            output.createNewFile();
            inspectInputFile();
            FileWriter writer = new FileWriter(output);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            writer.write(gson.toJson(customWordEntityMap));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void inspectInputFile() {
        try {
            Workbook workbook = WorkbookFactory.create(input);
            if(workbook instanceof HSSFWorkbook) {
                HSSFSheet sheet = ((HSSFWorkbook)workbook).getSheetAt(0);
                Iterator<Row> iterator = sheet.iterator();
                int n = 0;
                while(iterator.hasNext()) {
                    Row row = iterator.next();
                    int id = n;
                    String polish = row.getCell(1).getStringCellValue();
                    String english = row.getCell(2).getStringCellValue();
                    addEntityToMap(new CustomWordEntity(id, polish, english));
                    n++;
                }
            } else if(workbook instanceof XSSFWorkbook) {
                XSSFSheet sheet = ((XSSFWorkbook)workbook).getSheetAt(0);
                Iterator<Row> iterator = sheet.iterator();
                int n = 0;
                while(iterator.hasNext()) {
                    Row row = iterator.next();
                    int id = n;
                    String polish = row.getCell(1).getStringCellValue();
                    String english = row.getCell(2).getStringCellValue();
                    addEntityToMap(new CustomWordEntity(id, polish, english));
                    n++;
                }
            }

        } catch(IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
    }

    private void addEntityToMap(CustomWordEntity entity) {
        customWordEntityMap.addEntity(entity);
    }

}
