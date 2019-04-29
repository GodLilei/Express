/*
 * Copyright © 2015-2026 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * @since 0.0.1
 */

package com.myproject.demo.work;


import com.alibaba.fastjson.JSON;
import com.myproject.demo.Dto.SeverJResponse;
import com.myproject.demo.utils.HttpServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WeChatJob {

//    @Scheduled(cron = "0 */2 * * * *")
    public void sendWeChatMsg(){
        HttpServer httpServer = new HttpServer();
        String title ="下班!";
        String message = "别忘了打卡";
//        String response = httpServer.weChatSend(title,message);
//        SeverJResponse severJResponse =JSON.parseObject(response,SeverJResponse.class);
//        if(0==severJResponse.getCode()){
//            log.info("推送成功");
//        }else{
//            log.info("推送失败");
//        }

    }
}
