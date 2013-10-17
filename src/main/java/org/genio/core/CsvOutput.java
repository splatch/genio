package org.genio.core;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.NullWriter;
import org.genio.api.CompositeGenerator;
import org.genio.api.Generator;
import org.genio.api.GeneratorException;
import org.genio.api.Output;
import org.genio.api.ValueGenerator;

public class CsvOutput implements Output<ValueGenerator<?>> {

    public InputStream generate(CompositeGenerator<ValueGenerator<?>> generator) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output));

        for (ValueGenerator<?> gen : generator) {
            try {
                writer.write(gen.getName());
                writer.write(";");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            writer.write("\n");
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        for (int i = 0; i < 100; i++) {
            try {
                for (Generator<?> gen : generator) {
                    gen.generate(writer);
                    writer.write(";");
                }
                writer.write("\n");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (GeneratorException e) {
                e.printStackTrace();
            }
        }
        try {
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(output.toByteArray());
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static void main(String[] args) throws IOException {
        CsvOutput csvOutput = new CsvOutput();

        NumberGenerator imeiGenerator = new NumberGenerator("imei", 100000000000000L, 999999999999999L);
        List<Long> imei = new ArrayList<Long>();
        for (int i = 0; i < 100; i++) {
            try {
                imei.add(imeiGenerator.generate(new NullWriter()));
            } catch (GeneratorException e) {
                e.printStackTrace();
            }
        }

        CompositeGenerator<ValueGenerator<?>> generator = new DefaultCompositeGenerator(
            new SequenceGenerator("id"),
            new StringGenerator("adslid", 10),
            new MsisdnGenerator("msisdn", 100000000L, 999999999L),
            new RangeGenerator<Long>("imei", imei),
            new RangeGenerator<String>("direction", "IN", "OUT")
        );
        InputStream output = csvOutput.generate(generator);

        System.out.println(IOUtils.toString(output));
    }

}
