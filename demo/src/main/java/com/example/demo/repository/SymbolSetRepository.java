package com.example.demo.repository;

import com.example.demo.entity.SymbolSet;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.ArrayList;

@Repository
public class SymbolSetRepository implements IRestRepository<SymbolSet> {
    protected final JdbcOperations jdbcOperations;

    private static String selectQuery = "SELECT \"id\", \"symbol_id_1\", \"symbol_id_2\", \"symbol_id_3\" " +
            "FROM \"symbol_set\" " +
            "ORDER BY \"id\"";

    private static String selectByIdQuery = "SELECT \"id\", \"symbol_id_1\", \"symbol_id_2\", \"symbol_id_3\" " +
            "FROM \"symbol_set\" " +
            "WHERE \"id\" = ?";

    private static String selectBySymbolId1Query = "SELECT \"id\", \"symbol_id_1\", \"symbol_id_2\", \"symbol_id_3\" " +
            "FROM \"symbol_set\" " +
            "WHERE \"symbol_id_1\" = ?";

    private static String selectBySymbolId2Query = "SELECT \"id\", \"symbol_id_1\", \"symbol_id_2\", \"symbol_id_3\" " +
            "FROM \"symbol_set\" " +
            "WHERE \"symbol_id\" = ?";

    private static String selectBySymbolId3Query = "SELECT \"id\", \"symbol_id_1\", \"symbol_id_2\", \"symbol_id_3\" " +
            "FROM \"symbol_set\" " +
            "WHERE \"symbol_id_3\" = ?";

    private static String selectInSSByRuleIdQuery = "SELECT \"ss\".\"id\", \"ss\".\"symbol_id_1\", \"ss\".\"symbol_id_2\", \"ss\".\"symbol_id_3\" " +
            "FROM \"symbol_set\" AS \"ss\" " +
            "JOIN \"rule\" AS \"r\" " +
            "ON \"ss\".\"id\" = \"r\".\"in_ssid\" " +
            "WHERE \"r\".\"id\" = ?";

    private static String selectOutSSByRuleIdQuery = "SELECT \"ss\".\"id\", \"ss\".\"symbol_id_1\", \"ss\".\"symbol_id_2\", \"ss\".\"symbol_id_3\" " +
            "FROM \"symbol_set\" AS \"ss\" " +
            "JOIN \"rule\" AS \"r\" " +
            "ON \"ss\".\"id\" = \"r\".\"out_ssid\" " +
            "WHERE \"r\".\"id\" = ?";

    private static String insertQuery = "INSERT INTO \"symbol_set\"(\"symbol_id_1\", \"symbol_id_2\", \"symbol_id_3\")" +
            "VALUES (?, ?, ?) " +
            "RETURNING \"id\", \"symbol_id_1\", \"symbol_id_2\", \"symbol_id_3\"";

    private static String updateQuery = "UPDATE \"symbol_set\" " +
            "SET \"symbol_id_1\" = ?, \"symbol_id_2\" = ?,  \"symbol_id_3\" = ? " +
            "WHERE \"id\" = ? " +
            "RETURNING \"id\", \"symbol_id_1\", \"symbol_id_2\", \"symbol_id_3\"";

    private static String deleteQuery = "DELETE FROM \"symbol_set\" " +
            "WHERE \"id\" = ? " +
            "RETURNING \"id\", \"symbol_id_1\", \"symbol_id_2\", \"symbol_id_3\"";

    public SymbolSetRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public SymbolSet[] select() {
        ArrayList<SymbolSet> values = new ArrayList<SymbolSet>();
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectQuery);
        while (rowSet.next()) {
            values.add(new SymbolSet(
                    rowSet.getInt(1),
                    rowSet.getInt(2),
                    rowSet.getInt(3),
                    rowSet.getInt(4)
            ));
        }
        SymbolSet[] result = new SymbolSet[values.size()];
        result = values.toArray(result);
        return result;
    }

    @Override
    public SymbolSet select(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] { Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByIdQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new SymbolSet(
                rowSet.getInt(1),
                rowSet.getInt(2),
                rowSet.getInt(3),
                rowSet.getInt(4)
        );
    }

    public SymbolSet[] selectBySymbolId1(Integer symbolId) {
        ArrayList<SymbolSet> values = new ArrayList<SymbolSet>();
        Object[] params = new Object[] { symbolId };
        int[] types = new int[] { Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectBySymbolId1Query, params, types);
        while (rowSet.next()) {
            values.add(new SymbolSet(
                    rowSet.getInt(1),
                    rowSet.getInt(2),
                    rowSet.getInt(3),
                    rowSet.getInt(4)
            ));
        }
        SymbolSet[] result = new SymbolSet[values.size()];
        result = values.toArray(result);
        return result;
    }

    public SymbolSet[] selectBySymbolId2(Integer symbolId) {
        ArrayList<SymbolSet> values = new ArrayList<SymbolSet>();
        Object[] params = new Object[] { symbolId };
        int[] types = new int[] { Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectBySymbolId2Query, params, types);
        while (rowSet.next()) {
            values.add(new SymbolSet(
                    rowSet.getInt(1),
                    rowSet.getInt(2),
                    rowSet.getInt(3),
                    rowSet.getInt(4)
            ));
        }
        SymbolSet[] result = new SymbolSet[values.size()];
        result = values.toArray(result);
        return result;
    }

    public SymbolSet[] selectBySymbolId3(Integer symbolId) {
        ArrayList<SymbolSet> values = new ArrayList<SymbolSet>();
        Object[] params = new Object[] { symbolId };
        int[] types = new int[] { Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectBySymbolId3Query, params, types);
        while (rowSet.next()) {
            values.add(new SymbolSet(
                    rowSet.getInt(1),
                    rowSet.getInt(2),
                    rowSet.getInt(3),
                    rowSet.getInt(4)
            ));
        }
        SymbolSet[] result = new SymbolSet[values.size()];
        result = values.toArray(result);
        return result;
    }

    public SymbolSet selectInSSByRuleId(Integer ruleId) {
        Object[] params = new Object[] { ruleId };
        int[] types = new int[] { Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectInSSByRuleIdQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new SymbolSet(
                rowSet.getInt(1),
                rowSet.getInt(2),
                rowSet.getInt(3),
                rowSet.getInt(4)
        );
    }

    public SymbolSet selectOutSSByRuleId(Integer ruleId) {
        Object[] params = new Object[] { ruleId };
        int[] types = new int[] { Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectOutSSByRuleIdQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new SymbolSet(
                rowSet.getInt(1),
                rowSet.getInt(2),
                rowSet.getInt(3),
                rowSet.getInt(4)
        );
    }

    @Override
    public SymbolSet insert(SymbolSet entity) {
        Object[] params = new Object[] { entity.getSymbolId1(), entity.getSymbolId2(), entity.getSymbolId3() };
        int[] types = new int[] { Types.INTEGER, Types.INTEGER, Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(insertQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new SymbolSet(
                rowSet.getInt(1),
                rowSet.getInt(2),
                rowSet.getInt(3),
                rowSet.getInt(4)
        );
    }

    @Override
    public SymbolSet update(Integer id, SymbolSet entity) {
        Object[] params = new Object[] { entity.getSymbolId1(), entity.getSymbolId2(), entity.getSymbolId3(), id };
        int[] types = new int[] { Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(updateQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new SymbolSet(
                rowSet.getInt(1),
                rowSet.getInt(2),
                rowSet.getInt(3),
                rowSet.getInt(4)
        );
    }

    @Override
    public SymbolSet delete(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(deleteQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new SymbolSet(
                rowSet.getInt(1),
                rowSet.getInt(2),
                rowSet.getInt(3),
                rowSet.getInt(4)
        );
    }
}
