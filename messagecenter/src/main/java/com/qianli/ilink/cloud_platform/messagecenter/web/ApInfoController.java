package com.qianli.ilink.cloud_platform.messagecenter.web;

import com.googlecode.protobuf.format.JsonFormat;
import com.qianli.ilink.cloud_platform.messagecenter.enums.MessageEnum;
import com.qianli.ilink.cloud_platform.messagecenter.heartbeat.service.UdpServerNodeManager;
import com.qianli.ilink.cloud_platform.messagecenter.pojo.dto.*;
import com.qianli.ilink.cloud_platform.messagecenter.service.MessageSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
public class ApInfoController {

    @Autowired
    private MessageSender messageSender;

    @Autowired
    private UdpServerNodeManager udpServerNodeManager;

    @RequestMapping(value = "/apbaseinfo", method = RequestMethod.POST,consumes = "application/x-protobuf")
    public void apBaseInfoReceive(@RequestBody ApBaseInfoProto.ApBaseInfo apBaseInfo) {
        if(apBaseInfo == null){
            log.info("api apbaseinfo request protobuf body is null...");
            return;
        }
        messageSender.send(Message.builder().type(MessageEnum.AP_BASE_INFO.getMsg()).body(new JsonFormat().printToString(apBaseInfo)).build());
    }

    @RequestMapping(value = "/apstatusinfo", method = RequestMethod.POST,consumes = "application/x-protobuf")
    public void apStatusInfoReceive(@RequestBody ApStatusInfoProto.ApStatusInfo apStatusInfo) {
        if(apStatusInfo == null){
            log.info("api apstatusinfo request protobuf body is null...");
            return;
        }
        messageSender.send(Message.builder().type(MessageEnum.AP_STATUS_INFO.getMsg()).body(new JsonFormat().printToString(apStatusInfo)).build());
    }

    @RequestMapping(value = "/aponlinestainfo", method = RequestMethod.POST,consumes = "application/x-protobuf")
    public void apOnlineStaInfoReceive(@RequestBody ApOnlineStaInfoProto.TotalApOnlineStaInfo totalApOnlineStaInfo) {
        if(totalApOnlineStaInfo == null){
            log.info("api aponlinestainfo request protobuf body is null...");
            return;
        }
        messageSender.send(Message.builder().type(MessageEnum.AP_ONLINE_STA_INFO.getMsg()).body(new JsonFormat().printToString(totalApOnlineStaInfo)).build());
    }

    @RequestMapping(value = "/apofflinestainfo", method = RequestMethod.POST,consumes = "application/x-protobuf")
    public void apOfflineStaInfoReceive(@RequestBody ApOfflineStaInfoProto.TotalApOfflineStaInfo totalApOfflineStaInfo) {
        if(totalApOfflineStaInfo == null){
            log.info("api apofflinestainfo request protobuf body is null...");
            return;
        }
        messageSender.send(Message.builder().type(MessageEnum.AP_OFFLINE_STA_INFO.getMsg()).body(new JsonFormat().printToString(totalApOfflineStaInfo)).build());
    }

    @RequestMapping(value = "/getValidUdpServer", method = RequestMethod.GET)
    public String apOfflineStaInfoReceive(HttpServletRequest request) {
        String ip = request.getParameter("ip");
        return udpServerNodeManager.getValidUdpServerNode(ip);
    }

}
