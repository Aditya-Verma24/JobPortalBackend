package com.icoder.Joblisting.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.icoder.Joblisting.Model.Post;

public interface PostRepository  extends MongoRepository<Post,String> {
  
}
