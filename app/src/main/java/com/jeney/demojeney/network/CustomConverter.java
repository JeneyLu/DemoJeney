package com.jeney.demojeney.network;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Type;

import retrofit.converter.ConversionException;
import retrofit.converter.Converter;
import retrofit.mime.TypedInput;
import retrofit.mime.TypedOutput;

/**
 * desc:
 * author: Jeney
 * email: jeneylu@anjuke.com
 * date: 2015/8/6
 */
public class CustomConverter implements Converter {
    @Override
    public Object fromBody(TypedInput typedInput, Type type) throws ConversionException {
        try {
            return StreamUtils.readInputStream(typedInput.in(),"utf-8");
        }catch (IOException exception){
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public TypedOutput toBody(Object o) {
        return new StringTypedOutput(o);
    }

    public static class StringTypedOutput implements TypedOutput {
        private Object object;
        private final byte[] stringBytes;

        public StringTypedOutput(Object object, byte[] stringBytes) {
            this.object = object;
            this.stringBytes = stringBytes;
        }

        public StringTypedOutput(Object object) {
            this.object = object;
            stringBytes = object.toString().getBytes();
        }

        @Override
        public String fileName() {
            return null;
        }

        @Override
        public String mimeType() {
            return "application/x-www-form-urlencoded";
        }

        @Override
        public long length() {
            return stringBytes.length;
        }

        @Override
        public void writeTo(OutputStream outputStream) throws IOException {
            outputStream.write(stringBytes);
        }
    }
}
