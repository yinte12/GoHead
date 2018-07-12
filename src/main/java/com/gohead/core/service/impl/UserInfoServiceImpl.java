package com.gohead.core.service.impl;

import com.gohead.core.dao.UserInfoMapper;
import com.gohead.core.entity.UserInfo;
import com.gohead.core.service.UserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by super on 2018/7/12.
 */
@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    private UserInfoMapper userInfoDao;

    @Override
    public UserInfo selectUserInfo(Integer id) {
        return userInfoDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateUser(UserInfo userInfo) {
        return userInfoDao.updateByPrimaryKey(userInfo);
    }

    @Override
    public int addUserInfo(UserInfo userInfo) {
        return userInfoDao.insert(userInfo);
    }

    @Override
    public int deleteUserInfo(Integer id) {
        return userInfoDao.deleteByPrimaryKey(id);
    }
}
