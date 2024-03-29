package com.codeuptopia.secondhandmarket.dao;

import java.util.*;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class ItemDaoImpl implements ItemDao {
    
    private final JdbcTemplate jdbcTemplate;
    private final RestHighLevelClient elasticSearchClient;
    
    @Inject
    public ItemDaoImpl(DataSource dataSource, RestHighLevelClient elasticSearchClient) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.elasticSearchClient = elasticSearchClient;
    }
    
    @Override
    public void addItem(Item item) {
        String sql = "INSERT INTO items (name, description, price) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, item.getName(), item.getDescription(), item.getPrice());
        indexItem(item);
    }
    
    @Override
    public void updateItem(Item item) {
        String sql = "UPDATE items SET name = ?, description = ?, price = ? WHERE id = ?";
        jdbcTemplate.update(sql, item.getName(), item.getDescription(), item.getPrice(), item.getId());
        indexItem(item);
    }
    
    @Override
    public void deleteItem(long itemId) {
        String sql = "DELETE FROM items WHERE id = ?";
        jdbcTemplate.update(sql, itemId);
        // TODO: delete item from ElasticSearch index
    }
    
    @Override
    public Item getItemById(long itemId) {
        String sql = "SELECT * FROM items WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{itemId}, new ItemRowMapper());
    }
    
    @Override
    public List<Item> getAllItems() {
        String sql = "SELECT * FROM items";
        return jdbcTemplate.query(sql, new ItemRowMapper());
    }
    
    @Override
    public void indexItem(Item item) {
        try {
            IndexRequest indexRequest = new IndexRequest("items");
            indexRequest.id(String.valueOf(item.getId()));
            indexRequest.source(item.toJson(), XContentType.JSON);
            elasticSearchClient.index(indexRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public List<Item> searchItems(String query) {
        try {
            SearchRequest searchRequest = new SearchRequest("items");
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(QueryBuilders.queryStringQuery(query));
            SearchHits searchHits = elasticSearchClient.search(searchRequest).getHits();
            List<Item> items = new ArrayList<>();
            for (SearchHit hit : searchHits) {
                Item item = Item.fromJson(hit.getSourceAsString());
                items.add(item);
            }
            return items;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
