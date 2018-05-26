/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.marius.jobmatcher.web.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Marius
 */
public class DateFormatter {

    private static final String PATTERN = "dd.MM.yyyy";

    private static final ThreadLocal<SimpleDateFormat> SDF
            = ThreadLocal.withInitial(DateFormatter::simpleDateTimeFormat);

    public static String format(Date date) {
        return date != null ? SDF.get().format(date) : "";
    }

    private static SimpleDateFormat simpleDateTimeFormat() {
        return new SimpleDateFormat(PATTERN);
    }
}
