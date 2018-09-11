package com.gohead.core.service.impl;

import com.gohead.core.dao.UserInfoMapper;
import com.gohead.core.entity.UserInfo;
import com.gohead.core.service.UserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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
    public List<UserInfo> findUserInfo(Map<String, Object> map) {
        return userInfoDao.findUserInfo(map);
    }

    @Override
    public Long getTotalUserInfo(Map<String, Object> map) {
        return userInfoDao.getTotalUserInfo(map);
    }

    @Override
    public int updateUserInfo(UserInfo userInfo) {
        userInfoDao.updateUser(userInfo.getUser());
        return userInfoDao.updateUserInfo(userInfo);
    }

    @Override
    public int addUserInfo(UserInfo userInfo) {
         userInfoDao.insertUser(userInfo.getUser());
         userInfo.setUid(userInfo.getUser().getId());
         return userInfoDao.insertUserInfo(userInfo);
    }

    @Override
    public int deleteUserInfo(Integer id) {
        userInfoDao.deleteUserInfo(id);
        return userInfoDao.deleteUser(id);
    }
}
