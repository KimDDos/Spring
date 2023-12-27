package com.ezen.www.service;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.ezen.www.domain.BoardDTO;
import com.ezen.www.domain.BoardVO;
import com.ezen.www.domain.FileVO;
import com.ezen.www.domain.PagingVO;
import com.ezen.www.repository.BoardDAO;
import com.ezen.www.repository.FileDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService{
	// service와 연결은 사용자 본인이 implements 를 해줘야함, 안 하면 각자 독립적인 클래스임
	
	@Inject
	private BoardDAO bdao;
	
	@Inject
	private FileDAO fdao;

	@Override
	public int register(BoardDTO bdto) {
		log.info("register service impl check!");
		// 기존 보드내용 보드에 저장
		int isOk = bdao.insert(bdto.getBvo());
		
		// flist를 db에 저장
		// flist가 controller에서 안에 내용을 체크하고 오기에 null임
		if(bdto.getFlist() == null){
			// 파일의 값이 없다면
			isOk *= 1; // 그냥 성공한걸로 처리
		} else {
			// 파일 저장
			if(isOk > 0 && bdto.getFlist().size()>0) {
				// fvo는 bno가 아직 설정되기 전
				// 현재 bdto 시점에서는 아직 bno가 생성되지 않음
				// insert를 통해 자동생성 => DB에서 검색해서 가져오기
				int bno = bdao.selectBno();
				log.info("bno >>>> {} ", bno);
				for(FileVO fvo : bdto.getFlist()) {
					fvo.setBno(bno);
					// 파일 실제 저장
					isOk *= fdao.insertFile(fvo);
				}
			}
		}
		
		return isOk;
	}

	@Override
	public List<BoardVO> getList(PagingVO pgvo) {
		return bdao.selectList(pgvo);
	}
	

	@Override
	public BoardDTO getDetail(int bno) {
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setBvo(bdao.selectOne(bno));
		boardDTO.setFlist(fdao.getFileList(bno));
		return boardDTO;
	}

	@Override
	public int readCount(int bno) {
		return bdao.countUp(bno);
	}

	@Override
	public void modify(BoardVO bvo) {
		bdao.modify(bvo);
	}

	@Override
	public int remove(int bno) {
		return bdao.delete(bno);
	}

	@Override
	public void countDown(int bno) {
		bdao.down(bno);
	}

	@Override
	public int getTotalCount(PagingVO pgvo) {
		return bdao.getTotalCount(pgvo);
	}

	@Override
	public int deleteFile(String uuid) {
		// TODO Auto-generated method stub
		return fdao.delete(uuid);
	}

	
}
