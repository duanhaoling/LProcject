package com.example.mydemo.greendaotdemo;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.example.mydemo.greendaotdemo.LocalVideo;
import com.example.mydemo.greendaotdemo.User;
import com.example.mydemo.greendaotdemo.Test;

import com.example.mydemo.greendaotdemo.LocalVideoDao;
import com.example.mydemo.greendaotdemo.UserDao;
import com.example.mydemo.greendaotdemo.TestDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig localVideoDaoConfig;
    private final DaoConfig userDaoConfig;
    private final DaoConfig testDaoConfig;

    private final LocalVideoDao localVideoDao;
    private final UserDao userDao;
    private final TestDao testDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        localVideoDaoConfig = daoConfigMap.get(LocalVideoDao.class).clone();
        localVideoDaoConfig.initIdentityScope(type);

        userDaoConfig = daoConfigMap.get(UserDao.class).clone();
        userDaoConfig.initIdentityScope(type);

        testDaoConfig = daoConfigMap.get(TestDao.class).clone();
        testDaoConfig.initIdentityScope(type);

        localVideoDao = new LocalVideoDao(localVideoDaoConfig, this);
        userDao = new UserDao(userDaoConfig, this);
        testDao = new TestDao(testDaoConfig, this);

        registerDao(LocalVideo.class, localVideoDao);
        registerDao(User.class, userDao);
        registerDao(Test.class, testDao);
    }
    
    public void clear() {
        localVideoDaoConfig.clearIdentityScope();
        userDaoConfig.clearIdentityScope();
        testDaoConfig.clearIdentityScope();
    }

    public LocalVideoDao getLocalVideoDao() {
        return localVideoDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public TestDao getTestDao() {
        return testDao;
    }

}
