document.addEventListener('click', (e) => {
     console.log(e.target);
     if (e.target.classList.contains('file-X')) {
          let uuidVal = e.target.dataset.uuid;
          console.log(uuidVal);
          removeFileToServer(uuidVal).then(result => {
               if (result > 0) {
                    alert("사진 제거 완료~!");
                    location.reload();
                    // 사진 지우고 다시 출력하는 메서드 추가
               }
          })
     }
})



async function removeFileToServer(uuidVal) {
     try {
          const url = "/board/modify/" + uuidVal;
          const config = {
               method: 'delete'
          }
          const resp = await fetch(url, config);
          const result = await resp.text();
          return result;
     } catch (error) {
          console.log(error);
     }
}