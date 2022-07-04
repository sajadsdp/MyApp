package com.example.myapp.exportion;

import com.example.myapp.user.User;
import org.springframework.stereotype.Repository;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Repository
public class Export {

    public void exportToVSC(List<User> userList, HttpServletResponse response) throws IOException {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String timeSave = dateFormat.format(new Date());
        String fileName = "Users_" + timeSave + ".csv";

        response.setContentType("text/csv");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename="+ fileName;
        response.setHeader(headerKey,headerValue);

        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);

        String[] csvHeader = {"ID","Email","Name","Birthday"};
        String[] fieldMapping = {"id","email","name","birthday"};

        csvWriter.writeHeader(csvHeader);
        for (User user : userList){
            csvWriter.write(user,fieldMapping);
        }

        csvWriter.close();
    }
}
