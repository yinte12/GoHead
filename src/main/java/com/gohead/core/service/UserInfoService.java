package com.gohead.core.service;

import com.gohead.core.entity.UserInfo;

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
     * @param userInfo
     * @return
     */
    public int updateUser(UserInfo userInfo);

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
