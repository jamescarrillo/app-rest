/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcode.app.api;

import com.google.gson.Gson;
import com.jcode.app.model.Categoria;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author JamesCarrillo
 */
public class DemoJSON {
    
    public static void main(String[] args) {
        List<Categoria> lista = new ArrayList<>();
        lista.add(new Categoria(1,"ZAPATOS"));
        lista.add(new Categoria(2,"ROPA"));
        lista.add(new Categoria(3,"COMIDAS"));
        String cadenaJson = new Gson().toJson(lista);
        System.out.println(cadenaJson);
        List<Categoria> listad = new Gson().fromJson(cadenaJson, ArrayList.class);
        System.out.println(listad.size());
        LocalDateTime datetime = LocalDateTime.now();
        System.out.println(datetime);
        System.out.println("-> " + LocalDateTime.now());
        System.out.println("->-> " + new Date().toString());
    }
    
}
