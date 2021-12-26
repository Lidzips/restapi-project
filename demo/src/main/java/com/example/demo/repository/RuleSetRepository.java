package com.example.demo.repository;

import com.example.demo.entity.RuleSet;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.ArrayList;

@Repository
public class RuleSetRepository implements IRestRepository<RuleSet>{
    protected final JdbcOperations jdbcOperations;

    private static String selectQuery = "SELECT \"id\", \"alphabet_from_id\", \"alphabet_to_id\", \"rule_id\" " +
            "FROM \"rule_set\" " +
            "ORDER BY \"id\"";

    private static String selectByIdQuery = "SELECT \"id\", \"alphabet_from_id\", \"alphabet_to_id\", \"rule_id\" " +
            "FROM \"rule_set\" " +
            "WHERE \"id\" = ?";

    private static String selectByAlphabetFromIdQuery = "SELECT \"id\", \"alphabet_from_id\", \"alphabet_to_id\", \"rule_id\" " +
            "FROM \"rule_set\" " +
            "WHERE \"alphabet_from_id\" = ?";

    private static String selectByAlphabetToIdQuery = "SELECT \"id\", \"alphabet_from_id\", \"alphabet_to_id\", \"rule_id\" " +
            "FROM \"rule_set\" " +
            "WHERE \"alphabet_to_id\" = ?";

    private static String selectByRuleIdQuery = "SELECT \"id\", \"alphabet_from_id\", \"alphabet_to_id\", \"rule_id\" " +
            "FROM \"rule_set\" " +
            "WHERE \"rule_id\" = ?";

    private static String insertQuery = "INSERT INTO \"rule_set\"(\"alphabet_from_id\", \"alphabet_to_id\", \"rule_id\")" +
            "VALUES (?, ?, ?) " +
            "RETURNING \"id\", \"alphabet_from_id\", \"alphabet_to_id\", \"rule_id\"";

    private static String updateQuery = "UPDATE \"rule_set\" " +
            "SET \"alphabet_from_id\" = ?, \"alphabet_to_id\" = ?, \"rule_id\" = ? " +
            "WHERE \"id\" = ? " +
            "RETURNING \"id\", \"alphabet_from_id\", \"alphabet_to_id\", \"rule_id\"";

    private static String deleteQuery = "DELETE FROM \"rule_set\" " +
            "WHERE \"id\" = ? " +
            "RETURNING \"id\", \"alphabet_from_id\", \"alphabet_to_id\", \"rule_id\"";

    public RuleSetRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public RuleSet[] select() {
        ArrayList<RuleSet> values = new ArrayList<RuleSet>();
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectQuery);
        while (rowSet.next()) {
            values.add(new RuleSet(
                    rowSet.getInt(1),
                    rowSet.getInt(2),
                    rowSet.getInt(3),
                    rowSet.getInt(4)
            ));
        }
        RuleSet[] result = new RuleSet[values.size()];
        result = values.toArray(result);
        return result;
    }

    @Override
    public RuleSet select(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] { Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByIdQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new RuleSet(
                rowSet.getInt(1),
                rowSet.getInt(2),
                rowSet.getInt(3),
                rowSet.getInt(4)
        );
    }

    public RuleSet[] selectByAlphabetFromId(Integer sourceId) {
        ArrayList<RuleSet> values = new ArrayList<RuleSet>();
        Object[] params = new Object[] { sourceId };
        int[] types = new int[] { Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByAlphabetFromIdQuery, params, types);
        while (rowSet.next()) {
            values.add(new RuleSet(
                    rowSet.getInt(1),
                    rowSet.getInt(2),
                    rowSet.getInt(3),
                    rowSet.getInt(4)
            ));
        }
        RuleSet[] result = new RuleSet[values.size()];
        result = values.toArray(result);
        return result;
    }

    public RuleSet[] selectByAlphabetToId(Integer sourceId) {
        ArrayList<RuleSet> values = new ArrayList<RuleSet>();
        Object[] params = new Object[] { sourceId };
        int[] types = new int[] { Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByAlphabetToIdQuery, params, types);
        while (rowSet.next()) {
            values.add(new RuleSet(
                    rowSet.getInt(1),
                    rowSet.getInt(2),
                    rowSet.getInt(3),
                    rowSet.getInt(4)
            ));
        }
        RuleSet[] result = new RuleSet[values.size()];
        result = values.toArray(result);
        return result;
    }

    public RuleSet[] selectByRuleId(Integer sourceId) {
        ArrayList<RuleSet> values = new ArrayList<RuleSet>();
        Object[] params = new Object[] { sourceId };
        int[] types = new int[] { Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByRuleIdQuery, params, types);
        while (rowSet.next()) {
            values.add(new RuleSet(
                    rowSet.getInt(1),
                    rowSet.getInt(2),
                    rowSet.getInt(3),
                    rowSet.getInt(4)
            ));
        }
        RuleSet[] result = new RuleSet[values.size()];
        result = values.toArray(result);
        return result;
    }

    @Override
    public RuleSet insert(RuleSet entity) {
        Object[] params = new Object[] { entity.getAlphabetFromId(), entity.getAlphabetToId(), entity.getRuleId() };
        int[] types = new int[] { Types.INTEGER, Types.INTEGER, Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(insertQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new RuleSet(
                rowSet.getInt(1),
                rowSet.getInt(2),
                rowSet.getInt(3),
                rowSet.getInt(4)
        );
    }

    @Override
    public RuleSet update(Integer id, RuleSet entity) {
        Object[] params = new Object[] { entity.getAlphabetFromId(), entity.getAlphabetToId(), entity.getRuleId(),  id };
        int[] types = new int[] { Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(updateQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new RuleSet(
                rowSet.getInt(1),
                rowSet.getInt(2),
                rowSet.getInt(3),
                rowSet.getInt(4)
        );
    }

    @Override
    public RuleSet delete(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(deleteQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new RuleSet(
                rowSet.getInt(1),
                rowSet.getInt(2),
                rowSet.getInt(3),
                rowSet.getInt(4)
        );
    }
}
