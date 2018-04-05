package com.epam.provider.dao;

import com.epam.provider.model.Profile;

import java.util.List;

/**
 * Created by HP on 26.03.2018.
 */
public interface ProfileDAO extends AbstractDAO<Integer, Profile> {

    @Override
    List<Profile> findAll() throws DAOException;

    @Override
    void delete(Integer id) throws DAOException;

    @Override
    void delete(Profile entity) throws DAOException;

    @Override
    void create(Profile entity) throws DAOException;

    @Override
    Profile update(Profile entity) throws DAOException;
}
