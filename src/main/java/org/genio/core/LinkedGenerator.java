package org.genio.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;

import org.apache.commons.io.IOUtils;
import org.genio.api.GeneratorException;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class LinkedGenerator<T> extends RangeGenerator<T> {

    private T key;

    public LinkedGenerator(String name, T ... values) {
        super(name, values);
    }

    public T generate(Writer output) throws GeneratorException {
        key = super.generate(output);
        return key;
    }

    public T getKey() {
        return key;
    }

    public static void main(String[] args) throws IOException {
        Multimap<String, String> emails = ArrayListMultimap.<String, String>create();
        emails.put("John", "john@doe.com");
        emails.put("John", "john@gmail.com");
        emails.put("John", "jonny@hotmail.com");
        emails.put("Ammy", "ammy@wp.pl");

        Multimap<String, String> phones = ArrayListMultimap.<String, String>create();
        phones.put("John", "721151666");
        phones.put("John", "721197666");
        phones.put("Ammy", "500600700");
        phones.put("Ammy", "800900100");
        phones.put("Ammy", "123123123");

        LinkedGenerator<String> generator = new LinkedGenerator<String>("onlineId", "John", "Ammy");
        LinkAwareGenerator<String, String> email = new LinkAwareGenerator<String, String>("email", generator, emails);
        LinkAwareGenerator<String, String> phone = new LinkAwareGenerator<String, String>("phone", generator, phones);

        CsvOutput csvOutput = new CsvOutput();
        InputStream output = csvOutput.generate(new DefaultCompositeGenerator(new SequenceGenerator("id"), generator, email, phone));

        System.out.println(IOUtils.toString(output));
    }

/*
john:
    online id: a, b, c
    phone: 1, 2

ammy:
    online id: z
    phone: 5



*/

}
