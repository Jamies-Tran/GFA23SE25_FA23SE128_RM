package com.realman.becore.util;

import org.hibernate.boot.model.FunctionContributions;
import org.hibernate.dialect.MySQLDialect;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.query.sqm.function.SqmFunctionRegistry;
import org.hibernate.type.BasicTypeReference;

public class CustomMySQLDialect extends MySQLDialect {
    public CustomMySQLDialect() {
        super();
    }

    @Override
    public void initializeFunctionRegistry(FunctionContributions functionContributions) {
        SqmFunctionRegistry sqmFunctionRegistry = functionContributions.getFunctionRegistry();
        sqmFunctionRegistry.register("distance", new StandardSQLFunction("calculateDistance", new BasicTypeReference<>(CLOSED_QUOTE, null, getPreferredSqlTypeCodeForArray())));
        super.initializeFunctionRegistry(functionContributions);
    }

    
}
