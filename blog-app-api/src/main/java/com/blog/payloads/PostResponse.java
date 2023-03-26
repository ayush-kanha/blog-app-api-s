package com.blog.payloads;

import java.util.List;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class PostResponse {

	private List<PostDto> content;
	private int pageSize;
	private int totalPages;
	private int pageNumber;
	private long totalElements;
	boolean isLast;
	
	
}
