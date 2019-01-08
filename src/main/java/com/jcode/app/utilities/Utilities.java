package com.jcode.app.utilities;

import java.util.HashMap;

public class Utilities {

    public static HashMap<String, Object> getParametersDefaultBasic() {
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("FILTER", "");
        parameters.put("PAGE", 1);
        parameters.put("SIZE", 10);
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
