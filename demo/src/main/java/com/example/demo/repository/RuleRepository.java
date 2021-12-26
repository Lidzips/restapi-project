package com.example.demo.repository;

import com.example.demo.entity.Rule;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.ArrayList;

@Repository
public class RuleRepository implements IRestRepository<Rule> {
    protected final JdbcOperations jdbcOperations;

    private static String selectQuery = "SELECT \"id\", \"in_ssid\", \"out_ssid\" " +
            "FROM \"rule\" " +
            "ORDER BY \"id\"";

    private static String selectByIdQuery = "SELECT \"id\", \"in_ssid\", \"out_ssid\" " +
            "FROM \"rule\" " +
            "WHERE \"id\" = ?";

    private static String selectByInSsidQuery = "SELECT \"id\", \"in_ssid\", \"out_ssid\" " +
            "FROM \"rule\" " +
            "WHERE \"in_ssid\" = ?";

    private static String selectByOutSsidQuery = "SELECT \"id\", \"in_ssid\", \"out_ssid\" " +
            "FROM \"rule\" " +
            "WHERE \"out_ssid\" = ?";

    private static String selectByRuleSetIdQuery = "SELECT \"r\".\"id\", \"r\".\"in_ssid\", \"r\".\"out_ssid\" " +
            "FROM \"rule\" AS \"r\" " +
            "JOIN \"rule_set\" AS \"rs\" " +
            "ON \"rs\".\"rule_id\" = \"r\".\"id\" " +
            "WHERE \"rs\".\"id\" = ?";

    private static String insertQuery = "INSERT INTO \"rule\"(\"in_ssid\", \"out_ssid\")" +
            "VALUES (?, ?) " +
            "RETURNING \"id\", \"in_ssid\", \"out_ssid\"";

    private static String updateQuery = "UPDATE \"rule\" " +
            "SET \"in_ssid\" = ?, \"out_ssid\" = ? " +
            "WHERE \"id\" = ? " +
            "RETURNING \"id\", \"in_ssid\", \"out_ssid\"";

    private static String deleteQuery = "DELETE FROM \"rule\" " +
            "WHERE \"id\" = ? " +
            "RETURNING \"id\", \"in_ssid\", \"out_ssid\"";

    public RuleRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public Rule[] select() {
        ArrayList<Rule> values = new ArrayList<Rule>();
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectQuery);
        while (rowSet.next()) {
            values.add(new Rule(
                    rowSet.getInt(1),
                    rowSet.getInt(2),
                    rowSet.getInt(3)
            ));
        }
        Rule[] result = new Rule[values.size()];
        result = values.toArray(result);
        return result;
    }

    @Override
    public Rule select(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] { Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByIdQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Rule(
                rowSet.getInt(1),
                rowSet.getInt(2),
                rowSet.getInt(3)
        );
    }

    public Rule[] selectByInSsid(Integer sourceId) {
        ArrayList<Rule> values = new ArrayList<Rule>();
        Object[] params = new Object[] { sourceId };
        int[] types = new int[] { Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByInSsidQuery, params, types);
        while (rowSet.next()) {
            values.add(new Rule(
                    rowSet.getInt(1),
                    rowSet.getInt(2),
                    rowSet.getInt(3)
            ));
        }
        Rule[] result = new Rule[values.size()];
        result = values.toArray(result);
        return result;
    }

    public Rule[] selectByOutSsid(Integer sourceId) {
        ArrayList<Rule> values = new ArrayList<Rule>();
        Object[] params = new Object[] { sourceId };
        int[] types = new int[] { Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByOutSsidQuery, params, types);
        while (rowSet.next()) {
            values.add(new Rule(
                    rowSet.getInt(1),
                    rowSet.getInt(2),
                    rowSet.getInt(3)
            ));
        }
        Rule[] result = new Rule[values.size()];
        result = values.toArray(result);
        return result;
    }

    public Rule selectByRuleSetId(Integer ruleSetId) {
        Object[] params = new Object[] { ruleSetId };
        int[] types = new int[] { Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByRuleSetIdQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Rule(
                rowSet.getInt(1),
                rowSet.getInt(2),
                rowSet.getInt(3)
        );
    }

    @Override
    public Rule insert(Rule entity) {
        Object[] params = new Object[] { entity.getInSsid(), entity.getOutSsid() };
        int[] types = new int[] { Types.INTEGER, Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(insertQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Rule(
                rowSet.getInt(1),
                rowSet.getInt(2),
                rowSet.getInt(3)
        );
    }

    @Override
    public Rule update(Integer id, Rule entity) {
        Object[] params = new Object[] { entity.getInSsid(), entity.getOutSsid(), id };
        int[] types = new int[] { Types.INTEGER, Types.INTEGER, Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(updateQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Rule(
                rowSet.getInt(1),
                rowSet.getInt(2),
                rowSet.getInt(3)
        );
    }

    @Override
    public Rule delete(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(deleteQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Rule(
                rowSet.getInt(1),
                rowSet.getInt(2),
                rowSet.getInt(3)
        );
    }
}
