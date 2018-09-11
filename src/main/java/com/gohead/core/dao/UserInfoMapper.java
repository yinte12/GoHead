package com.gohead.core.dao;

import com.gohead.core.entity.User;
import com.gohead.core.entity.UserInfo;

import java.util.List;
import java.util.Map;

public interface UserInfoMapper {
    int deleteUserInfo(Integer id);

    int deleteUser(Integer id);

    int insertUserInfo(UserInfo record);

    int insertUser(User user);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer id);

    int updateUserInfo(UserInfo record);

    int updateUser(User user);

    int updateByPrimaryKey(UserInfo record);

    /**
     * 查找用户信息列表
     *
     * @param map
     * @return
     */
    public List<UserInfo> findUserInfo(Map<String, Object> map);

    /**
     * @param map
     * @return
     */
    public Long getTotalUserInfo(Map<String, Object> map);
}