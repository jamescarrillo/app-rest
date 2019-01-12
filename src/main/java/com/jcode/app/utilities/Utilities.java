package com.jcode.app.utilities;

import java.util.HashMap;

public class Utilities {

    public static HashMap<String, Object> getParametersDefaultBasic() {
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("FILTER", "");
        parameters.put("SQL_ORDERS", " ORDER BY NOMBRE ASC ");
        parameters.put("SQL_PAGINATION", " LIMIT 10  OFFSET 0");
        return parameters;
    }

    public static HashMap<String, Object> getParametersDefaultBasicEstado() {
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("filter", "");
        parameters.put("page", 1);
        parameters.put("size", 10);
        parameters.put("estado", "-1");
        return parameters;
    }
}
