package com.jeney.demojeney.network;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;

import retrofit.converter.ConversionException;
import retrofit.converter.Converter;
import retrofit.mime.MimeUtil;
import retrofit.mime.TypedInput;
import retrofit.mime.TypedOutput;

/**
 * desc: 自定义json转换器
 * author: Jeney
 * email: jeneylu@anjuke.com
 * date: 2015/8/7
 */
public class CustomJsonConverter implements Converter {
    public static final String CHARSET = "UTF-8";

    @Override
    public Object fromBody(TypedInput typedInput, Type type) throws ConversionException {
        //把服务端返回的body转换成model对象
        String charset = CHARSET;
        if (typedInput.mimeType() != null) {
            charset = MimeUtil.parseCharset(typedInput.mimeType(),CHARSET);
        }
        try {
            return JSON.parseObject(StreamUtils.readInputStream(typedInput.in(), charset), type);
        } catch (IOException e) {
            throw new ConversionException(e);
        } catch (JSONException e) {
            e.printStackTrace();
            throw new ConversionException(e);
        }
    }

    @Override
    public TypedOutput toBody(Object o) {
        //把客户端的请求body转换成框架需要的TypedOutput
        return new JsonTypedOutput(o, CHARSET);
    }

    public static class JsonTypedOutput implements TypedOutput {
        private final byte[] jsonBytes;
        private final String mimeType;
        private Object object;

        JsonTypedOutput(Object object, String encode) {
            this.object = object;
            try {
                this.jsonBytes = JSON.toJSONString(object).getBytes(encode);
            } catch (UnsupportedEncodingException e) {
                throw new AssertionError(e);
            }
            this.mimeType = "application/json; charset=" + encode;
        }

        JsonTypedOutput(byte[] jsonBytes, String encode) {
            this.jsonBytes = jsonBytes;
            this.mimeType = "application/json; charset=" + encode;
        }

        public Object getObject() {
            return object;
        }

        @Override
        public String fileName() {
            return null;
        }

        @Override
        public String mimeType() {
            return mimeType;
        }

        @Override
        public long length() {
            return jsonBytes.length;
        }

        @Override
        public void writeTo(OutputStream out) throws IOException {
            out.write(jsonBytes);
        }
    }
}
