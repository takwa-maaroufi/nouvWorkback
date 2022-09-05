package work365.work.payload.response;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ApiResponseone {
    private String message,token;
    JSONArray array=null;
    HashMap<String,String> data=null;
    JSONObject jsonobj=null;
    private List<String> errors;
    public ApiResponseone( String message, List<String> errors) {
        super();
        //this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public ApiResponseone( HashMap<String,String> data,String token) {
        this.data = data;
        this.token = token;
    }
    public ApiResponseone( JSONArray array,String token) {
        this.array = array;

        this.token = token;
    }
    public ApiResponseone( JSONObject jsonobj,String token) {
        this.jsonobj = jsonobj;
        this.token = token;
    }
    public ApiResponseone( String token,String message, List<String> errors) {
        super();
        this.token = token;
        this.message = message;
        this.errors = errors;
    }

    public ApiResponseone( String message, String error) {
        super();
        //this.status = status;
        this.message = message;
        errors = Arrays.asList(error);
    }
//	    public ApiResponse( String message, String Reason,String error_code) {
//	        super();
//	        //this.status = status;
//	        this.message = message;
//	        errors = Arrays.asList(error);
//	    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public HashMap<String, String> getData() {
        return data;
    }
    public void setData(HashMap<String, String> data) {
        this.data = data;
    }
    public JSONArray getArray() {
        return array;
    }
    public void setArray(JSONArray array) {
        this.array = array;
    }
    public JSONObject getJsonobj() {
        return jsonobj;
    }
    public void setJsonobj(JSONObject jsonobj) {
        this.jsonobj = jsonobj;
    }
}