package com.gohead.core.admin;

import com.gohead.core.common.Constants;
import com.gohead.core.common.Result;
import com.gohead.core.common.ResultGenerator;
import com.gohead.core.entity.UserInfo;
import com.gohead.core.service.UserInfoService;
import com.gohead.core.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by super on 2018/7/12.
 */
@Controller
@RequestMapping("/user_info")
public class UserInfoController {

    @Resource
    private UserInfoService userInfoService;

    private static final Logger log = Logger.getLogger(UserInfoController.class);// 日志文件

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Result getUserInfo(@PathVariable(value = "id") String id) throws Exception {
        UserInfo userInfo = userInfoService.selectUserInfo(Integer.valueOf(id));
        log.info("request: userInfo/select , userInfo: " + userInfo.toString());
        if (userInfo == null) {
            return ResultGenerator.genFailResult(Constants.RESULT_EMPTY_REQUEST);
        } else {
            return ResultGenerator.genSuccessResult(userInfo);
        }
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Result addUserInfo(@RequestBody UserInfo userInfo) throws Exception {
        int resultCode = userInfoService.addUserInfo(userInfo);
        if (resultCode > 0) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult(Constants.DEFAULT_FAIL_MESSAGE);
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Result deleteUserInfo(@PathVariable(value = "id") String id) throws Exception {
        int resultCode = userInfoService.deleteUserInfo(Integer.valueOf(id));
        if (resultCode > 0) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult(Constants.DEFAULT_FAIL_MESSAGE);
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    @ResponseBody
    public Result updateUserInfo(@RequestBody UserInfo userInfo) throws Exception {
        int resultCode = userInfoService.updateUser(userInfo);
        if (resultCode > 0) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult(Constants.DEFAULT_FAIL_MESSAGE);
        }
    }


}
