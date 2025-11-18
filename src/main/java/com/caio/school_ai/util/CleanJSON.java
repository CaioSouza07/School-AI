package com.caio.school_ai.util;

public class CleanJSON {

    public static String jsonLimpo(String json){

        String jsonLimpo = json
                .replace("```json", "")
                .replace("```", "")
                .trim();

        return jsonLimpo;

    }

}
