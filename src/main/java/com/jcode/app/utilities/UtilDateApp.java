/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcode.app.utilities;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author JamesCarrillo
 */
public class UtilDateApp {

    public static LocalDate getLocalDate(java.sql.Date date) {
        return date == null ? null : date.toLocalDate();
    }

    public static java.sql.Date getDate(LocalDate localDate) {
        return localDate == null ? null : java.sql.Date.valueOf(localDate);
    }

    public static LocalDateTime getLocalDateTime(java.sql.Timestamp timestamp) {
        return timestamp == null ? null : timestamp.toLocalDateTime();
    }

    public static java.sql.Timestamp getTimestamp(LocalDateTime localDateTime) {
        return localDateTime == null ? null : java.sql.Timestamp.valueOf(localDateTime);
    }
}
