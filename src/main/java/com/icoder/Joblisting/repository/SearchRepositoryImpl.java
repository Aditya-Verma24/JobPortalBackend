package com.icoder.Joblisting.repository;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bson.Document;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.AggregateIterable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;

import com.icoder.Joblisting.Model.Post;
import com.mongodb.client.MongoClient;

@Component
public class SearchRepositoryImpl implements SearchRepository {

	@Autowired
	MongoClient client;
	
	@Autowired
	MongoConverter converter;
	
	@Override
	public List<Post> findByText(String text) {
		
		final List<Post> posts = new ArrayList<>();
		
		  MongoDatabase database = client.getDatabase("Job");
	        MongoCollection<Document> collection = database.getCollection("JobPost");

	        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search",
	                        new Document("text",
	                        new Document("query",text)
	                        .append("path", Arrays.asList("techs", "desc", "profile")))),
	                        new Document("$sort",
	                        new Document("exp", 1L)),
	                        new Document("$limit", 5L)));

	        
			result.forEach(doc -> posts.add(converter.read(Post.class, doc)));

		
		return posts;
	}

}
