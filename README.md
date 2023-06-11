# Sound Login
<img src="https://github.com/chol0824/Sound_Login/assets/74773561/c15d920e-d8ba-496c-af31-a258ecef1870" width="350" height="300"/>
<img src="https://github.com/chol0824/Sound_Login/assets/74773561/26631a09-5a0c-44be-997f-ce8bff9c9e36" width="200" height="300"/>
<h2>Service Introduction</h2>
<h3>" 비밀번호를 입력하지 않고 STT를 통한 음성 인식 분석을 통해 로그인하는 시스템 "</h3>
<h4>유사 프로젝트와의 차별성</h4>
<li>2차 확인 인증 코드를 웹사이트에 다시 입력할 필요 없이 음성 인식을 통해 간편하게 로그인이 가능함</li>
<li>언제든지 앱을 통해 로그인 이력을 조회 가능</li>
<h3>Structure</h3>
<img src="https://github.com/chol0824/Sound_Login/assets/74773561/1747d850-cf80-445e-887b-dc366c4a00b7" width="700" height="500"/>
<h2>Main Function</h2>
<h4>회원가입 & 로그인</h4>
<li> 아이디, 비밀번호, 이메일을 입력하고 회원가입 시 socket 통신을 통해 File이 생성되고 json형태로 정보가 저장됨</li>
<li> registration file 내 일치하는 아이디, 비밀번호가 존재하면 해당 아이디로 로그인 성공</li>
<h4>소리 인식 로그인</h4>
<li>사용자가 웹과 앱에서 동시에 음성 인식을 진행하여 인식 정보가 일치하면 로그인 성공</li>
<h4>2차 확인 메일 전송</h4>
<li>로그인 성공 시 Flask 서버 속 저장되어 있는 사용자의 이메일로 확인 메일 전송 </li>
<h4>앱 내 로그인 이력 상시 조회</h4>
<li>사용자는 앱을 통해 언제든지 로그인 이력 조회 가능</li>
<h2>Team Introduction</h2>
<table>
    <tr>
      <th scope="col">최서정</td>
      <th scope="col">이경진</td>
      <th scope="col">이지훈</td>
    </tr>
    <tr>
      <td>Android APP-Flask</td>
      <td>Web-Flask</td>
      <td>Overall Assistance</td>
    </tr>
  </table>
<h3>What I did in this Project</h3>
<li>Android APP 전체 개발</li>
<li>Flask-APP 연결</li>
<li>Firebase-App 연결 (로그인 이력 조회 기능 구현)</li>
<li>Firebase--App--Flask Socket 통신</li>
<li>APP->Web 이메일 전송 기능 구현</li>

<h2>Tech Stack</h2>
<img src="https://github.com/chol0824/Sound_Login/assets/74773561/96b572e9-d71f-47f4-85bd-d0b364e02ea3" width="500" height="200"/> <br>
<code>HTML-CSS-JS for WEB</code><br>
<code>Flask for Server</code><br>
<code>Firebase for CLOUD-SERVER</code><br>
<code>Java (Android Studio) for APP </code><br>


<h2>Result_Detail</h2>
<h3>Report</h3> 
<li>https://github.com/chol0824/Sound_Login/files/11627181/7._.pdf?raw=True</li><br>
<img src="https://github.com/chol0824/Sound_Login/assets/74773561/26a0c8f9-086f-41e2-b6ef-d462d5685c00" width="200" height="300"/>
<img src="https://github.com/chol0824/Sound_Login/assets/74773561/ac5dc0db-311f-4c9f-a8f7-bb1f36b14eb2" width="200" height="300"/>
<br><br>
<h3>Presentation</h3>
<li>https://docs.google.com/viewer?url=https://github.com/chol0824/Sound_Login/files/11714318/7.PPT._._Soundlogin-compressed.pdf?raw=True</li><br>
<img src="https://github.com/chol0824/Sound_Login/assets/74773561/19a672ee-a0a7-445c-9bea-7540207c23c4" width="450" height="250"/>
<img src="https://github.com/chol0824/Sound_Login/assets/74773561/7b6fc50f-5fb8-4f7b-a882-b237d4a01a75" width="450" height="250"/>
<img src="https://github.com/chol0824/Sound_Login/assets/74773561/49b0a974-d0d8-481e-93a9-b798ec12f6a9" width="450" height="250"/>
<img src="https://github.com/chol0824/Sound_Login/assets/74773561/808923b3-2e8e-4f65-ba9e-c4c371bbed2f" width="450" height="250"/>
<br><br>
<h3>Video</h3> - https://youtu.be/CIulht_PyYI   // PPT : 0:00 ~ 1:25 , Video : 1:25 ~ 5:02 
<br>
<img src="https://github.com/chol0824/Sound_Login/assets/74773561/32ceda17-81a4-4462-ac73-c0853aadd200" width="450" height="250"/>
<img src="https://github.com/chol0824/Sound_Login/assets/74773561/aad72aee-a89a-4f70-9532-8d06d424c117" width="450" height="250"/>
<img src="https://github.com/chol0824/Sound_Login/assets/74773561/7bda8101-7cf1-43da-a170-52cb10501017" width="450" height="250"/>
<img src="https://github.com/chol0824/Sound_Login/assets/74773561/5e064137-8b20-4ecd-a054-5fd2b01ff2be" width="450" height="250"/>
