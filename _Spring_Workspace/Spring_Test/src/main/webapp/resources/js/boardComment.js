console.log("boardComment.JS 등장~!!");
console.log(">>> bnoVal >>> " + bnoVal);

document.getElementById("cmtAddBtn").addEventListener('click', () => {
     const cmtText = document.getElementById("cmtText").value;
     const cmtWriter = document.getElementById("cmtWriter").innerText;
     if (cmtText == "" || cmtText == null) {
          alert("댓글을 입력해주세요~!");
          document.getElementById('cmtText').focus();
          return false;
     } else {
          let cmtData = {
               bno: bnoVal,
               writer: cmtWriter,
               content: cmtText
          };
          console.log(cmtData);

          // 댓글 등록시 비동기 통신 호출
          postCommentToServer(cmtData).then(result => {
               console.log(result);
               if (result === "1") {
                    alert("댓글등록 성공 ~ !!");
                    // 화면에 뿌리기 
                    getCommentList(bnoVal);
               }
          })
     }
});

// 비동기 통신 구문
async function postCommentToServer(cmtData) {
     try {
          const url = "/comment/post";
          const config = {
               method: 'post',
               headers: {
                    'content-type': 'application/json; charset=utf-8'
               },
               body: JSON.stringify(cmtData)
          };
          const resp = await fetch(url, config);
          const result = await resp.text();
          return result;
     } catch (error) {
          console.log(error);
     }
};

async function spreadCommentListFromServer(bno) {
     try {
          const resp = await fetch("/comment/" + bno);
          const result = resp.json();
          return result;
     } catch (error) {
          console.log(error);
     }
}

// 댓글 뿌리기
function getCommentList(bno) {
     spreadCommentListFromServer(bno).then(result => {
          console.log(result);
          const div = document.getElementById('accordionExample');
          if (result.length > 0) {
               div.innerHTML = "";
               for (let i = 0; i < result.length; i++) {
                    let add = `<div class="accordion-item">`;
                    add += `<h2 class="accordion-header">`;
                    add += `<button class="accordion-button" type="button" data-bs-toggle="collapse" `;
                    add += `data-bs-target="#collapse${i}" aria-expanded="true" aria-controls="collapse${i}">`;
                    add += `No.${result[i].cno} / 작성자 : ${result[i].writer} / reg_date(${result[i].reg_date})</button> </h2>`;
                    add += `<div id="collapse${i}" class="accordion-collapse collapse show" `
                    add += `data-bs-parent="#accordionExample">`;
                    add += `<div class="accordion-body">`;
                    add += `<input type="text" value="${result[i].content}" class="form-control cmtText"></div>`;
                    add += `<button type="button" data-cno="${result[i].cno}" class="btn btn-sm btn-outline-warning cmtModify">Modify</button>`;
                    add += `<button type="button" data-cno="${result[i].cno}" class="btn btn-sm btn-outline-danger cmtRemove">Remove</button>`
                    add += `</div>`;

                    div.innerHTML += add;
               }
          } else {
               div.innerHTML = `<div class="accordion-body"> Comment List Empty <div>`;
          }
     })
}

async function removeCommentFromServer(cnoVal) {
     try {
          const url = "/comment/" + cnoVal;
          const config = {
               method: "delete",
          }
          const resp = await fetch(url, config);
          const result = await resp.text();
          return result;
     } catch (error) {
          console.log(error);
     }
}

async function updateCommentToServer(cmtData) {
     try {
          const url = "/comment/modify";
          const config = {
               method: "put",
               headers: {
                    "content-type": "application/json; charset=utf-8"
               },
               body: JSON.stringify(cmtData)
          }

          const resp = await fetch(url, config)
          const result = await resp.text();
          return result;
     } catch (error) {
          console.log(error)
     }
}

document.addEventListener('click', (e) => {
     console.log(e.target);
     if (e.target.classList.contains('cmtRemove')) {
          let cnoVal = e.target.dataset.cno;
          console.log(cnoVal);
          removeCommentFromServer(cnoVal).then(result => {
               if (result > 0) {
                    alert("댓글 삭제 완료~!");
                    getCommentList(bnoVal);
               }
          })
     }

     if (e.target.classList.contains('cmtModify')) {
          let cnoVal = e.target.dataset.cno;
          console.log(cnoVal);
          let div = e.target.closest('div');
          console.log(div);
          let cmtText = div.querySelector('.cmtText').value;
          console.log(cmtText);

          const cmtData = { cno: cnoVal, content: cmtText };
          console.log(cmtData);
          updateCommentToServer(cmtData).then(result => {
               if (result > 0) {
                    alert("댓글 수정 완료~!");
                    getCommentList(bnoVal);
               }
          })
     }
})
