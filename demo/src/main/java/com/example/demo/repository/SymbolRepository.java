package com.example.demo.repository;

import com.example.demo.entity.Symbol;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.ArrayList;

@Repository
public class SymbolRepository implements IRestRepository<Symbol> {
    protected final JdbcOperations jdbcOperations;

    private static String selectQuery = "SELECT \"id\", \"code\" " +
            "FROM \"symbol\" " +
            "ORDER BY \"id\"";

    private static String selectByIdQuery = "SELECT \"id\", \"code\" " +
            "FROM \"symbol\" " +
            "WHERE \"id\" = ?";

    private static String selectByAlphabetQuery = "SELECT \"s\".\"id\", \"s\".\"code\" " +
            "FROM \"symbol\" AS \"s\" " +
            "JOIN \"alphabet\" AS \"ab\" " +
            "ON \"s\".\"id\" = \"ab\".\"symbol_id\" " +
            "WHERE \"ab\".\"name\" = ?";

    private static String select1BySymbolSetIdQuery = "SELECT \"s\".\"id\", \"s\".\"code\" " +
            "FROM \"symbol\" AS \"s\" " +
            "JOIN \"symbol_set\" AS \"ss\" " +
            "ON \"s\".\"id\" = \"ss\".\"symbol_id_1\" " +
            "WHERE \"ss\".\"id\" = ?";

    private static String select2BySymbolSetIdQuery = "SELECT \"s\".\"id\", \"s\".\"code\" " +
            "FROM \"symbol\" AS \"s\" " +
            "JOIN \"symbol_set\" AS \"ss\" " +
            "ON \"s\".\"id\" = \"ss\".\"symbol_id_2\" " +
            "WHERE \"ss\".\"id\" = ?";

    private static String select3BySymbolSetIdQuery = "SELECT \"s\".\"id\", \"s\".\"code\" " +
            "FROM \"symbol\" AS \"s\" " +
            "JOIN \"symbol_set\" AS \"ss\" " +
            "ON \"s\".\"id\" = \"ss.symbol_id_3\" " +
            "WHERE \"ss\".\"id\" = ?";

    private static String insertQuery = "INSERT INTO \"symbol\"(\"code\")" +
            "VALUES (?) " +
            "RETURNING \"id\", \"code\"";

    private static String updateQuery = "UPDATE \"symbol\" " +
            "SET \"code\" = ? " +
            "WHERE \"id\" = ? " +
            "RETURNING \"id\", \"code\"";

    private static String deleteQuery = "DELETE FROM \"symbol\" " +
            "WHERE \"id\" = ? " +
            "RETURNING \"id\", \"code\"";

    public SymbolRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public Symbol[] select() {
        ArrayList<Symbol> values = new ArrayList<Symbol>();
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectQuery);
        while (rowSet.next()) {
            values.add(new Symbol(
                    rowSet.getInt(1),
                    rowSet.getString(2)
            ));
        }
        Symbol[] result = new Symbol[values.size()];
        result = values.toArray(result);
        return result;
    }

    @Override
    public Symbol select(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByIdQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Symbol(
                rowSet.getInt(1),
                rowSet.getString(2)
        );
    }

    public Symbol[] selectBySymbolSetId(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet1 = jdbcOperations.queryForRowSet(select1BySymbolSetIdQuery, params, types);
        SqlRowSet rowSet2 = jdbcOperations.queryForRowSet(select2BySymbolSetIdQuery, params, types);
        SqlRowSet rowSet3 = jdbcOperations.queryForRowSet(select3BySymbolSetIdQuery, params, types);
        int size = 0;
        if (!rowSet1.next()) {
            return null;
        } else {
            size = 1;
            if (rowSet2.next()) {
                size = 2;
                if (rowSet3.next()) {
                    size = 3;
                }
            }
        }
        Symbol[] returnVal = new Symbol[size];
        if (rowSet1.next()) {
            returnVal[0] = new Symbol(
                    rowSet1.getInt(1),
                    rowSet1.getString(2)
            );
            if (rowSet2.next()) {
                returnVal[1] = new Symbol(
                        rowSet2.getInt(1),
                        rowSet2.getString(2)
                );
                if (rowSet3.next()) {
                    returnVal[2] = new Symbol(
                            rowSet3.getInt(1),
                            rowSet3.getString(2)
                    );
                }
            }
        }
        return returnVal;
    }

    public Symbol select1BySymbolSetId(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(select1BySymbolSetIdQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Symbol(
                rowSet.getInt(1),
                rowSet.getString(2)
        );
    }

    public Symbol select2BySymbolSetId(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(select2BySymbolSetIdQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Symbol(
                rowSet.getInt(1),
                rowSet.getString(2)
        );
    }

    public Symbol select3BySymbolSetId(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(select3BySymbolSetIdQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Symbol(
                rowSet.getInt(1),
                rowSet.getString(2)
        );
    }

    public Symbol[] selectByAlphabet(String alphabetName) {
        ArrayList<Symbol> values = new ArrayList<Symbol>();
        Object[] params = new Object[] { alphabetName };
        int[] types = new int[] { Types.VARCHAR };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByAlphabetQuery, params, types);
        while (rowSet.next()) {
            values.add(new Symbol(
                    rowSet.getInt(1),
                    rowSet.getString(2)
            ));
        }
        Symbol[] result = new Symbol[values.size()];
        result = values.toArray(result);
        return result;
    }

    @Override
    public Symbol insert(Symbol entity) {
        Object[] params = new Object[] { entity.getCode() };
        int[] types = new int[] { Types.VARCHAR };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(insertQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Symbol(
                rowSet.getInt(1),
                rowSet.getString(2)
        );
    }

    @Override
    public Symbol update(Integer id, Symbol entity) {
        Object[] params = new Object[] { entity.getCode(),  id };
        int[] types = new int[] { Types.VARCHAR, Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(updateQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Symbol (
                rowSet.getInt(1),
                rowSet.getString(2)
        );
    }

    @Override
    public Symbol delete(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(deleteQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Symbol(
                rowSet.getInt(1),
                rowSet.getString(2)
        );
    }
}
