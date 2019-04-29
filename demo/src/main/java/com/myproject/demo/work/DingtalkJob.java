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

import com.myproject.demo.services.TodayThingsService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class DingtalkJob {


    @Resource
    private TodayThingsService todayThingsService;

    private  static  final Logger LOGGER = log;

    @Scheduled(cron = " 0 50 14 * * *")
    public void workStar(){
        log.info("推送开始");
        String msg=todayThingsService.sendMessage();
        log.info(">>>>>>>>{}",msg);
    }
}
