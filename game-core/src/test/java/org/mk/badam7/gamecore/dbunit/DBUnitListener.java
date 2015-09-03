package org.mk.badam7.gamecore.dbunit;

import org.dbunit.IOperationListener;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.ext.hsqldb.HsqldbDataTypeFactory;

public class DBUnitListener implements IOperationListener
{

    @Override
    public void connectionRetrieved(IDatabaseConnection connection)
    {
        connection.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new HsqldbDataTypeFactory());
    }

    @Override
    public void operationSetUpFinished(IDatabaseConnection connection)
    {
    }

    @Override
    public void operationTearDownFinished(IDatabaseConnection connection)
    {
    }

}
