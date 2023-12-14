package com.ezen.www.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class BoardVO {
	private int bno;
	private String title;
	private String writer;
	private String content;
	private String isDel;
	private String regdate;
	private int read_count;
}
