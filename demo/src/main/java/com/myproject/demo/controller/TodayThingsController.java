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

package com.myproject.demo.controller;



import com.myproject.demo.Dto.BaseResponse;
import com.myproject.demo.services.TodayThingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/todayThing")
public class TodayThingsController {


    @Resource
    private TodayThingsService todayThingsService;

    @ResponseBody
    @RequestMapping(value = "/querySchedule")
    public BaseResponse  queryUndoneSchedule(String status){

        return todayThingsService.querySchedule(status);
    }

    @ResponseBody
    @RequestMapping(value = "/getAllSchedule")
    public BaseResponse getAllSchedule(){

        return todayThingsService.getAllSchedule();
    }

}
