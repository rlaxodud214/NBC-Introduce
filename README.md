> Level 1. 로그인 페이지 만들기(SignInActivity)
- [ ]  새 프로젝트를 만들고 MainActivity의 이름을 SignInActivity로 바꿔주세요.
- [ ]  로고 이미지는 원하는 이미지로 넣어주세요.
- [ ]  아이디, 비밀번호를 입력 받는 EditText를 넣어주세요.(미리보기 글씨(플레이스 홀더) 포함)
- [ ]  비밀번호 EditText는 입력 내용이 가려져야 합니다.(●●● 처리)
- [ ]  로그인 버튼을 누르면 HomeActivity가 실행되도록 구현합니다.
  - (Extra로 아이디를 넘겨줍니다.)
- [ ]  아이디/비밀번호 모두 입력 되어야만 로그인 버튼이 눌리도록 구현합니다.
  - (“로그인 성공”이라는  토스트 메세지 출력하도록 구현)
- [ ]  아이디/비밀번호 중 하나라도 비어 있다면
  - “아이디/비밀번호를 확인해주세요” 라는 토스트 메세지가 출력되도록 구현합니다.
- [ ]  회원가입 버튼을 누르면 SignUpActivity가 실행되도록 구현합니다.


> Level 2. 회원가입 페이지 만들기(SignUpActivity)
- [ ] SignpActivity를 생성해 주세요.
- [ ] 타이틀 이미지는 원하는 이미지로 넣어주세요.
- [ ] 이름, 아이디, 비밀번호 모두 입력 되었을 때만 회원가입 버튼이 눌리도록 구현합니다.
  - 셋 중 하나라도 비어있으면 “입력되지 않은 정보가 있습니다” 라는 토스트 메세지를 출력하도록 구현합니다.
- [ ] 비밀번호 EditText는 입력 내용이 가려져야 합니다.(●●● 처리)
- [ ] 회원가입 버튼이 눌리면 SignInActivity로 이동하도록 구현합니다. (finish 활용)


> Level 3. 자기소개 페이지 만들기(HomeActivity)
- [ ] HomeActivity를 생성해 주세요.
- [ ] SignInActivity에서 받은 extra data(아이디)를 화면에 표시합니다.
- [ ] ImageView, TextView 외에 각종 Widget을 활용해 자유롭게 화면을 디자인 해주세요.
  - [ ] 이름, 나이, MBTI 등 자기소개등이 들어가는 위젯을 자유롭게 디자인해주세요.
- [ ] 종료 버튼이 눌리면 SignInActivity로 이동하도록 구현합니다. (finish 활용)


> Option 1. 화면 이동 + @
- [ ] 회원 가입 페이지에서 입력한 아이디/비밀번호가 회원 가입 버튼을 눌러 로그인 화면으로 이동할 때 자동으로 입력되도록 구현합니다.
  - Hint :  `registerForActivityResult` 를 알아봅시다.


> Option 2. 자기 소개 랜덤 사진
- [ ] 자기소개 페이지가 시작될 때 5장 중 랜덤으로 1장의 사진이 표시되도록 구현합니다.
  - 5장의 사진을 등록합니다.(drawable 폴더)