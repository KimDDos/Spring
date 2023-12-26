package com.ezen.www.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BoardDTO {

	private BoardVO bvo;
	private List<FileVO> flist;
	// flist에서 두번째 글자에 List는  쓰면 안 됨 살짝 버그 같은건데, jsp에서 인식하지 못 함
	
}
