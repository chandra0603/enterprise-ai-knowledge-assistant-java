package com.knowledge.assistant.enterpriseaiknowledgeassistant.repository;

import com.knowledge.assistant.enterpriseaiknowledgeassistant.dto.KeywordSearchResult;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class KeywordSearchRepository {

    private final JdbcTemplate jdbcTemplate;

    public KeywordSearchRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<KeywordSearchResult> search(String query) {

        String sql = """
                SELECT
                    content,
                    metadata->>'category' AS category,
                    metadata->>'fileName' AS fileName
                FROM vector_store
                WHERE content_tsv @@ plainto_tsquery('english', ?)
                """;

        return jdbcTemplate.query(
                sql,
                (rs, rowNum) ->
                        new KeywordSearchResult(
                                rs.getString("content"),
                                rs.getString("category"),
                                rs.getString("fileName")
                        ),
                query
        );
    }
}