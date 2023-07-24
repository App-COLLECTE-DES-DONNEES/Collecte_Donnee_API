package com.ditros.mcd.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ApiResponseHandler {
    public static ResponseEntity<Object> generateResponse(HttpStatus status, boolean success, String message, Object data) {
        Map<String, Object> map = new HashMap();
        try{
            map.put("status",status.value());
            map.put("success",success);
            map.put("message",message);
            map.put("data",data);
            return new ResponseEntity<Object>(map, status);
        }catch (Exception e) {
            e.printStackTrace();
            map.clear();
            map.put("status",HttpStatus.INTERNAL_SERVER_ERROR.value());
            map.put("success",success);
            map.put("message",message);
            map.put("data",data);
            return new ResponseEntity<Object>(map, status);
        }
    }
}
