document.getElementById('trigger').addEventListener('click', () => {
     document.getElementById('file').click();
})

// 정규표현식을 횔용한 파일 형식제한 함수 만들기
// 실행파일 막기(바이러스 유포 방지를 위해) : exe / bat / sh / mis / dll / jar
// 파일 사이즈 체크 (20Mb 사이즈보다 크면 막기)
// 이미지 파일만 올리기(jpg, jpeg, gif, png, bmp)

const regExp = new RegExp("\.(exe|sh|bat|mis|dll|jar)$");  // 실행파일 패턴
const regEmpImg = new RegExp("\.(jpeg|jpg|gif|png|bmp)$");  // 이미지 파일 패턴
const maxSize = 1024 * 1024 * 20;  // 20Mb 사이즈로 설정


// Validation : 규칙설정
// 버튼을 비활성화해서 1차적으로 막는 역할임
// return : 0(flase) / 1(true) 로 리턴
function fileValidation(name, fileSize) {
     let fileName = name.toLowerCase();
     if (regExp.test(fileName)) {  // 파일 이름을 정규식과 비교후 결과가 맞다면?
          return 0;
     } else if (fileSize > maxSize) {
          return 0;
     } else if (!regEmpImg.test(fileName)) {
          return 0;
     } else {
          return 1;
     }
}

// if(regExp.test(fileName)) : // 파일 이름을 정규식과 비교후 결과가 맞다면? 이란 뜻
// 첨부파일에 따라 등록가능한지 체크 함수
document.addEventListener('change', (e) => {
     console.log(e.target);
     if (e.target.id === 'file') {
          // 여러개의 파일이 배열로 들어옴
          const fileObject = document.getElementById('file').files;
          console.log(fileObject);
          // 한번 true가 되면 다시 false로 돌아올 수 없음. 버튼 활성화

          let div = document.getElementById('fileZone');
          div.innerHTML = '';
          let ul = `<ul>`;
          // fileValidation 함수의 리턴 여부를 체크 모든 파일이 1이여야 통과
          let isOk = 1;
          for (let file of fileObject) {
               let validResult = fileValidation(file.name, file.size);
               // 한 파일에 대한 결과
               isOk *= validResult;
               ul += `<li>`;
               // 업로드 가능 여부 표시
               ul += `<div>${validResult ? '업로드 가능' : '업로드 불가능'}</div>`;
               // 0, 1로 ture/false로 표현 가능
               ul += `${file.name}`;
               ul += `<span class="badge text-bg-${validResult ? 'success' : 'danger'}">${file.size}Byte</span>`;
               ul += `</li>`;
          }
          ul += `</ul>`;
          div.innerHTML = ul;
          // 업로드 불가능한 파일이 1개라도 있다면...
          if (isOk == 0) {
               document.getElementById('regBtn').disabled = true; // 버튼 비활성화
          }
     }
})

// fileZone > ul > li (파일 갯수 만큼)