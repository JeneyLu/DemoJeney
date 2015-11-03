package com.jeney.demojeney.network;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Type;

import retrofit.converter.ConversionException;
import retrofit.converter.Converter;
import retrofit.mime.MimeUtil;
import retrofit.mime.TypedInput;
import retrofit.mime.TypedOutput;

/**
 * desc:自己随便写的请求与返回的body都是String的转换器
 * author: Jeney
 * email: jeneylu@anjuke.com
 * date: 2015/8/6
 */
public class CustomStringConverter implements Converter {
    public static final String CHARSET = "UTF-8";

    @Override
    public Object fromBody(TypedInput typedInput, Type type) throws ConversionException {
        String charset = CHARSET;
        if (typedInput.mimeType() != null) {
            charset = MimeUtil.parseCharset(typedInput.mimeType(), CHARSET);
        }
        try {
            //直接把服务端返回的body转换成字符串返回
            return StreamUtils.readInputStream(typedInput.in(), charset);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public TypedOutput toBody(Object o) {
        //把客户端的请求body转换成框架需要的TypedOutput,当然请求的参数要重写toString，才能正确的拿到请求内容
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
            //Content-Type 被指定为 application/x-www-form-urlencoded；其次，提交的数据按照 key1=val1&key2=val2 的方式进行编码，key 和 val 都进行了 URL 转码。
            return "application/x-www-form-urlencoded; charset=" + CHARSET;
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
