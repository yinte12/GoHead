package com.gohead.core.service;

import com.gohead.core.entity.User;
import com.gohead.core.entity.UserInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by super on 2018/7/12.
 */
public interface UserInfoService {
    /**
     * @param id
     * @return
     */
    public UserInfo selectUserInfo(Integer id);

    /**
     * @param map
     * @return
     */
    public List<UserInfo> findUserInfo(Map<String, Object> map);

    /**
     * @param map
     * @return
     */
    public Long getTotalUserInfo(Map<String, Object> map);

    /**
     * @param userInfo
     * @return
     */
    public int updateUserInfo(UserInfo userInfo);

    /**
     * @param userInfo
     * @return
     */
    public int addUserInfo(UserInfo userInfo);

    /**
     * @param id
     * @return
     */
    public int deleteUserInfo(Integer id);
}
