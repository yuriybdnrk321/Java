package com.main.conventers;

import com.main.entity.Faculty;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class ConverterXML {

    public static String serizalize(Faculty newClass){
        try {
            StringWriter writer = new StringWriter();
            JAXBContext context = JAXBContext.newInstance(Faculty.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
            marshaller.marshal(newClass,writer);
            String result = writer.toString();
            return result;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Class deserialize(String data){
        StringReader reader = new StringReader(data);
        try {
            JAXBContext context = JAXBContext.newInstance(Class.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Class aClass = (Class) unmarshaller.unmarshal(reader);
            return aClass;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
