//package com.ale.common.aspect;
//
//import com.baomidou.mybatisplus.core.toolkit.IOUtils;
//import com.baomidou.mybatisplus.core.toolkit.StringUtils;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.core.MethodParameter;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpInputMessage;
//import org.springframework.http.MediaType;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.lang.reflect.Type;
//import java.util.Iterator;
//import java.util.Map;
//
//@ControllerAdvice
//@Slf4j
//public class RequestHandlerAdvice implements RequestBodyAdvice {
//    @Override
//    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
//        return false;
//    }
//
//    @Override
//    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
//                                           Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
//        return new CustomHttpInputMessage(inputMessage);
//    }
//
//    @Override
//    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter,
//                                Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
//        return body;
//    }
//
//    @Override
//    public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
//        return body;
//    }
//
//    class CustomHttpInputMessage implements HttpInputMessage{
//        private HttpInputMessage origin;
//
//        public CustomHttpInputMessage(HttpInputMessage httpInputMessage) {
//            this.origin = httpInputMessage;
//        }
//
//        @Override
//        public InputStream getBody() throws IOException {
//            HttpHeaders headers = origin.getHeaders();
//            InputStream body = origin.getBody();
//
//            // 空参，get 请求，流为空，非 application/json 请求，不处理参数
//            MediaType contentType = headers.getContentType();
//            if(contentType == null){return body;}
//            if(!contentType.isCompatibleWith(MediaType.APPLICATION_JSON)){return body;}
//            if(body == null){return body;}
//            String params = IOUtils.toString(body, "utf-8");
//            if(StringUtils.isBlank(params)){return body;}
//
//            // 正式过滤 json 参数
//            Object parse = JSON.parse(params);
//            if (parse instanceof JSONArray) {
//                JSONArray jsonArray = (JSONArray) parse;
//                trimJsonArray(jsonArray);
//            } else if (parse instanceof JSONObject) {
//                trimJsonObject((JSONObject) parse);
//            } else {
//                log.error("参数不支持去空格:" + parse+ " contentType:"+contentType);
//            }
//            return IOUtils.toInputStream(JSON.toJSONString(parse, SerializerFeature.WriteMapNullValue), "UTF-8");
//        }
//
//        private void trimJsonObject(JSONObject jsonObject) {
//            Iterator<Map.Entry<String, Object>> iterator = jsonObject.entrySet().iterator();
//            while (iterator.hasNext()) {
//                Map.Entry<String, Object> next = iterator.next();
//                String key = next.getKey();
//                Object value = next.getValue();
//                if (value instanceof JSONArray) {
//                    trimJsonArray((JSONArray) value);
//                }else if(value instanceof JSONObject){
//                    trimJsonObject((JSONObject) value);
//                }else if(value instanceof  String){
//                    String trimValue = StringUtils.trim(ObjectUtils.toString(value));
//                    next.setValue(filterDangerString(trimValue));
//                }
//            }
//        }
//
//        private void trimJsonArray(JSONArray jsonArray) {
//            for (int i = 0; i < jsonArray.size(); i++) {
//                Object object = jsonArray.get(i);
//                if(object instanceof JSONObject){
//                    JSONObject jsonObject = jsonArray.getJSONObject(i);
//                    trimJsonObject(jsonObject);
//                }else if(object instanceof  String){
//                    String trimValue = StringUtils.trim(ObjectUtils.toString(object));
//                    jsonArray.set(i,trimValue);
//                }
//
//            }
//        }
//
//        @Override
//        public HttpHeaders getHeaders() {
//            return origin.getHeaders();
//        }
//
//        private String filterDangerString(String value) {
//            if(StringUtils.isBlank(value))return value;
//
//            value = value.replaceAll(";", "；");
//            value = value.replaceAll("'", "‘");
//            value = value.replaceAll("<", "《");
//            value = value.replaceAll(">", "》");
//            value = value.replaceAll("\\(", "（");
//            value = value.replaceAll("\\)", "）");
//            value = value.replaceAll("\\?", "？");
//            return value;
//        }
//
//    }
//}
