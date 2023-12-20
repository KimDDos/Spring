// 희희 test test

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
               if (result > 0) {
                    alert("댓글등록 성공 ~ !!");
                    document.getElementById('cmtText').value = '';
               }
          })
     }
})

async function postCommentToServer(cmtData) {
     try {
          const url = "/comment/post";
          const config = {
               method: 'post',
               Headers: {
                    'Content-Type': 'application/json; charset=utf-8'
               },
               body: JSON.stringify(cmtData)
          };

          const resp = await fetch(url, config);
          const result = await resp.text();
          return result;
     } catch (error) {
          console.log(error);
     }
}