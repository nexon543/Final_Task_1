package com.epam.provider.dao;

import com.epam.provider.model.Entity;
import org.apache.log4j.Logger;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by HP on 26.03.2018.
 */
public abstract class AbstractDAO <K, T extends Entity>{
    private static Logger logger = Logger.getLogger(AbstractDAO.class);

    protected Connection connection;

    public AbstractDAO(Connection connection){
        this.connection=connection;
    }
    public abstract List<T> findAll() throws SQLException;
    public abstract T findEntityById (K id) throws SQLException;
    public abstract boolean delete (K id);
    public abstract boolean delete (T entity);
    public abstract boolean create(T entity);
    public abstract T update (T entity);
    public void close (Statement st){
        try{
            if (st!=null){
                st.close();
            }
        }
        catch (SQLException ex){
            logger.error(ex.getMessage());
        }
    }
    public void close (Connection con){
        if (con != null){
            try {
                con.close();
            } catch (SQLException ex) {
                logger.error(ex.getMessage());
            }
        }
    }
}
