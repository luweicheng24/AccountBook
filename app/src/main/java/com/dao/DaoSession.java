package com.dao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.gsww.www.accountbook.bean.AccountsBean;
import com.gsww.www.accountbook.bean.Personal;

import com.dao.AccountsBeanDao;
import com.dao.PersonalDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig accountsBeanDaoConfig;
    private final DaoConfig personalDaoConfig;

    private final AccountsBeanDao accountsBeanDao;
    private final PersonalDao personalDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        accountsBeanDaoConfig = daoConfigMap.get(AccountsBeanDao.class).clone();
        accountsBeanDaoConfig.initIdentityScope(type);

        personalDaoConfig = daoConfigMap.get(PersonalDao.class).clone();
        personalDaoConfig.initIdentityScope(type);

        accountsBeanDao = new AccountsBeanDao(accountsBeanDaoConfig, this);
        personalDao = new PersonalDao(personalDaoConfig, this);

        registerDao(AccountsBean.class, accountsBeanDao);
        registerDao(Personal.class, personalDao);
    }
    
    public void clear() {
        accountsBeanDaoConfig.clearIdentityScope();
        personalDaoConfig.clearIdentityScope();
    }

    public AccountsBeanDao getAccountsBeanDao() {
        return accountsBeanDao;
    }

    public PersonalDao getPersonalDao() {
        return personalDao;
    }

}
