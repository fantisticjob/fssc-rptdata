/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: OpenApiClientDemo
 * Author:   王永合
 * Date:     2020/4/22 0022 下午 5:18
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package ETLJobHandaler;

import com.alibaba.fastjson.JSONObject;
import com.longfor.longchat.openapi.bean.Result;
import com.longfor.longchat.openapi.client.OpenApiClient;
import com.longfor.longchat.openapi.constants.OpenapiConstants;
import com.longfor.longchat.openapi.content.FileMsgContent;
import com.longfor.longchat.openapi.content.TxtMsgContent;
import com.longfor.longchat.openapi.dto.CreateGroupDTO;
import com.longfor.longchat.openapi.dto.PushMessageDTO;
import com.longfor.longchat.openapi.enums.ObjectNameEnum;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * 〈〉
 *
 * @author 王永合
 * @create 2020/4/22 0022
 * @since 1.0.0
 */
public class OpenApiClientDemo {

    public static void main(String[] args) {
        // 建群
        /*List<String> members = new ArrayList<>();
        //members.add("w_duzhenxing");
        members.add("w_zhaobo14");
        //members.add("w_liuyingshuang");
        members.add("w_yaozhanying");
        members.add("monitor_admin");
        CreateGroupDTO createGroupDTO = CreateGroupDTO.builder()
                .groupName("我的测试群2")
                .members(members)
                .usercode("monitor_admin")
                .build();
        Result createGroupResult = OpenApiClient.createGroup(createGroupDTO, OpenapiConstants.CREATE_GROUP_DEFAULT_URL_PRO, "c30aeb8b-b4e0-4217-91a1-63190589248d", null);
        System.out.println(JSONObject.toJSONString(createGroupResult));

        */
        // 获取群成员
//        Result groupMembersResult = OpenApiClient.groupMembers("AD70zRw6R-Uj1-UZE2heQY", OpenapiConstants.GROUP_MEMBER_DEFAULT_URL_UAT, "efef6761-4362-4455-8fee-bf2b4e959921", 20);
//        System.out.println(JSONObject.toJSONString(groupMembersResult));


        // 推送消息
//        TxtMsgContent txtMsgContent = TxtMsgContent.builder()
//                  .content("我要发消息").build();
//        PushMessageDTO pushMessageDTO = PushMessageDTO.builder()
//                  .content(txtMsgContent)
//                  .from("w_duzhenxing")
//                  .fromType(1)
//                  .objectName(ObjectNameEnum.TXTMSG.getName())
//                  .target(groupId)
//                  .ssoToken("TGT-479058-UmYWQQ6p8APjS5Wjia6-pMyTjzcCDIOgj3-CvnxorrsRlJnNYJcmU-fVq53N4Dt227c-longhu")
//                  .targetType(2)
//                  .build();
//          Result sendMessageResult = OpenApiClient.pushMessage(pushMessageDTO, OpenapiConstants.SEND_MSG_DEFAULT_URL_PRO, "c30aeb8b-b4e0-4217-91a1-63190589248d", 20);
//          System.out.println(JSONObject.toJSONString(sendMessageResult));

        //群里推送文件消息
//        List<String> groupId = new ArrayList<>();
//        groupId.add("5fThbfKcSTIjLOzqZzmb18");
//
//        try {
//            String encode = URLEncoder.encode("我.pdf", "utf-8");
//            FileMsgContent fileMsgContent = FileMsgContent.builder()
//                    .name("我.pdf")
//                    .type("pdf")
//                    .size(214)
//                    .fileUrl("https://www.tech.net.cn/upfiles/file/10-39%20%E5%AE%81%E5%A4%8F%E5%B7%A5%E5%95%86%E8%81%8C%E4%B8%9A%E6%8A%80%E6%9C%AF%E5%AD%A6%E9%99%A2%E5%BB%BA%E8%AE%BE%E6%96%B9%E6%A1%88.pdf")
//                    .build();
//            PushMessageDTO pushMessageDTO = PushMessageDTO.builder()
//                    .content(fileMsgContent)
//                    .fromType(3)
//                    .objectName(ObjectNameEnum.FILEMSG.getName())
//                    .from("monitor_admin")
//                    .targetType(2)
//                    .target(groupId)
//                    .build();
//            Result sendMessageResult = OpenApiClient.pushMessage(pushMessageDTO, OpenapiConstants.SEND_MSG_DEFAULT_URL_PRO, "c30aeb8b-b4e0-4217-91a1-63190589248d", 20);
//            System.out.println(JSONObject.toJSONString(sendMessageResult));
//        } catch (UnsupportedEncodingException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }




        //推送图文
//        ImgMsgContent imc = ImgMsgContent.builder().content.build();
//                TxtMsgContent txtMsgContent = TxtMsgContent.builder()
//                .content("消息测试").build();
//        PushMessageDTO pushMessageDTO = PushMessageDTO.builder()
//                .content(txtMsgContent)
//                .from("xWsMx-RTTKYkdwUIlCaQLU")
//                .fromType(1)
//                .objectName(ObjectNameEnum.TXTMSG.getName())
//                .target(members)
//                .ssoToken("TGT-42398-e4tumGNYU22Gm5eZjYIWGurj-urJEYIecK4Xqo2JZjUe3DpVM6IegRvBnHIFOwJlR9o-longhu")
//                .targetType(TargetTypeEnum.USER.getTargetType())
//                .build();
//        Result sendMessageResult = OpenApiClient.pushMessage(pushMessageDTO, OpenapiConstants.SEND_MSG_DEFAULT_URL_UAT, "efef6761-4362-4455-8fee-bf2b4e959921", 20);
//        System.out.println(JSONObject.toJSONString(sendMessageResult));


    }


}