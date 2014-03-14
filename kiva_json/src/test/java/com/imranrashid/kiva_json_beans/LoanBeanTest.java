package com.imranrashid.kiva_json_beans;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.imranrashid.kiva_json_beans.LoanBean;

public class LoanBeanTest {

    @Test
    public void testJson() throws JsonParseException, JsonMappingException, IOException, ParseException {
        File f = new File("src/main/resources/loan.json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        
        LoanBean b = mapper.readValue(f, LoanBean.class);
        assertEquals(84, b.id);
        assertNotNull(b.description);
        assertTrue(b.description.texts.containsKey("en"));
        assertEquals("paid", b.status);
        assertEquals(500, b.funded_amount);
        assertEquals(500, b.paid_amount);
        assertNotNull(b.location);
        assertEquals("UG", b.location.country_code);
        assertEquals("Uganda", b.location.country);
        assertEquals("Tororo", b.location.town);
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd'T'kk:mm:ss'Z'");
        fmt.setTimeZone(TimeZone.getTimeZone("UTC"));
        assertEquals(fmt.parse("2005-04-15T17:00:00Z"),b.posted_date);
        assertEquals(fmt.parse("2005-03-31T06:27:55Z"), b.funded_date);
        assertEquals(fmt.parse("2005-12-13T12:00:40Z"), b.paid_date);
    }

}
