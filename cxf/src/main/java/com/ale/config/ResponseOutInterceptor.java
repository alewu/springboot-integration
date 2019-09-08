package com.ale.config;

import com.ale.common.GlobalResponse;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.message.MessageContentsList;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;

import java.util.List;

/**
 * 统一响应类
 *
 * @author alewu
 * @date 2019/8/27
 */
public class ResponseOutInterceptor extends AbstractPhaseInterceptor {

    public ResponseOutInterceptor() {
        super(Phase.PRE_MARSHAL);
    }

    @Override
    public void handleMessage(Message message) throws Fault {
//        final List<Object> objs = MessageContentsList.getContentsList(message);
//        if (objs == null || objs.isEmpty()) {
//            return;
//        }
//        Object responseObj = objs.get(0);
//        GlobalResponse<Object> globalResponse = new GlobalResponse<>();
//        globalResponse.setData(responseObj);
//        objs.set(0, globalResponse);
    }
}
