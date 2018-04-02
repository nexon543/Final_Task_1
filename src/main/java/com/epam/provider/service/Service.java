package com.epam.provider.service;

import com.epam.provider.dao.DAOConnectionManager;

/**
 * Created by HP on 29.03.2018.
 */
public class Service {
    protected DAOConnectionManager daoManager;
    public Service (){
        daoManager=new DAOConnectionManager();
    }
}
