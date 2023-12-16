package com.icoder.Joblisting.repository;

import java.util.List;

import com.icoder.Joblisting.Model.Post;

public interface SearchRepository {

	 List<Post> findByText(String text);
	 
}
