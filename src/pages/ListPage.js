import React, { useState } from 'react';
import styled from 'styled-components';

const StyledItemBoxDiv = styled.div`
   display: flex; /* flex 아래 옵션 사용을 위한 부분 */
   justify-content: space-between; /* 좌우로 정렬 */
   border: 1px solid black;
   padding: 10px;
   height: 100px;
   margin: 20px;
   align-items: center; /* 세로 정렬 */
`;

const ListPage = () => {
   const [no, setNo] = useState(6);

   const handleWrite = (e) => {
      //ListPage의 setPosts에 무엇을 담을까요?
      e.preventDefault();
      setPosts([...posts, { ...post, id: no }]);
      setNo(no + 1);
      //   setPosts()
   };
   const [post, setPost] = useState({
      id: no,
      title: '',
      content: '',
   });

   const handleChangeTitle = (e) => {
      console.log(e.target.value);
      setPost({ title: e.target.value });
   };
   const handleChangeContent = (e) => {
      console.log(e);
      setPost({ content: e.target.value });
   };

   const handleForm = (e) => {
      // console.log(e.target.name);
      // console.log(e.target.value);
      // computed property names 문법 (키값 동적할당)
      setPost({
         ...post, // 다시 그려지면서 없어지기 때문에 다시 복사해놓고 해야함..
         [e.target.name]: e.target.value,
      }); // []키값 : value

      // console.log(post.title);
      // console.log(post.content);
   };

   const [posts, setPosts] = useState([
      { id: 1, title: '제목1', content: '내용1' },
      { id: 2, title: '제목2', content: '내용2' },
      { id: 3, title: '제목3', content: '내용3' },
      { id: 4, title: '제목4', content: '내용4' },
      { id: 5, title: '제목5', content: '내용5' },
   ]);
   return (
      <div>
         <h1>리스트페이지</h1>
         <form onSubmit={handleWrite}>
            <input
               type="text"
               placeholder="제목을 입력하세요"
               value={post.title}
               onChange={handleForm} // Change 함수는 input 태그의 변화가 왔을때 실현
               name="title"
            />
            <input
               type="text"
               placeholder="내용을 입력하세요"
               value={post.content}
               onChange={handleForm}
               name="content"
            />
            <button type="submit">글쓰기</button>
         </form>
         <hr />
         {posts.map((post) => (
            <StyledItemBoxDiv>
               <div>
                  번호: {post.id} | 제목: {post.title} | 내용: {post.content}
               </div>
               <button>삭제</button>
            </StyledItemBoxDiv>
         ))}
      </div>
   );
};

export default ListPage;
