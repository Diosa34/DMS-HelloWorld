package com.github.Diosa34.DMS_HelloWorld

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.Function

class SQLStringLen(
    private val expr: Expression<String>
): Function<String>(TextColumnType()){
    override fun toQueryBuilder(queryBuilder: QueryBuilder) = queryBuilder{append("LEN(", this@SQLStringLen.expr, ")")}
}

object TrueBuilder: Op<Boolean>(){
    override fun toQueryBuilder(queryBuilder: QueryBuilder) = queryBuilder{append(true)}
}