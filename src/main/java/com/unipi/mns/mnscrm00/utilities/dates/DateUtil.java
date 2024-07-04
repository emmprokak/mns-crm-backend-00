package com.unipi.mns.mnscrm00.utilities.dates;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static Date convertStrToDate(String date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date resultingDate = null;

        try{
            resultingDate = sdf.parse(date);
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid date provided, please use format yyyy-MM-dd");
        }

        return resultingDate;
    }
}
