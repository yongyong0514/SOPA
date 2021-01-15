
written by Yonggi Jeong<br>
reporting date 01.01.21

&nbsp;

![SOPA](/web/psd/sopa.png)

### SOPA는 여러 사람과 함께 퀴즈를 만들고 풀 수 있는 플랫폼 입니다
### A platform to make and solve quizzes with many people (Benchmark: Kahoot!) 

&nbsp;

## 링크 Link
### Figma UI https://www.figma.com/file/7o79zsLqC64Xl9LdtjLlJ0/Untitled?node-id=0%3A1

&nbsp;

## 시연 영상 YouTube Clip
* YouTube Link

&nbsp;

## 개발환경 Environment
* JAVA
* Eclipse

&nbsp;

## 주요기능 Developed Functions
1. 회원 가입 및 로그인
<br>Sign-up and Log-in

2. 새로운 퀴즈 생성 및 출력
<br>Creating new quiz and printing

3. 대기 방 채팅 지원
<br>Chat function in the waiting room

4. 멀티 플레이 퀴즈 풀기
<br>Real-time quiz solving competition with several attendees (multi-play)<br>

5. 결과 송출 및 이력관리
<br>Reporting results and user profile management<br>

&nbsp;

## DB 디자인 DataBase Design
데이터베이스 없이 text파일을 입출력으로 리스트를 만들고 구현했습니다.
<br>Text-file is used for input/output function and relevant list creation.  
Database is not used in this project.

&nbsp;

## 담당 역할 및 기여도 Main Responsibilities in the Project
개발 
<br>Development 

- Figma를 활용한 프로젝트 UI 설계 및 디자인
<br>Project User Interface (UI) Design by using Figma

- 퀴즈를 입력해서 저장하고 불러올 수 있는 기능 구현
<br>Functions that can create, save and load quizzes

&nbsp;

## 프로젝트 담당 주요 내용 Project Overview

### 퀴즈를 등록하고 한 세트로 등록해서 세트를 이용해 게임을 만들 수 있습니다.
<br>The users can make a quiz and group quizzes as a set so they can play a game with the set of quizzes.

* 구현기능설명 Function Description
퀴즈 생성 창에서 퀴즈를 만들 수 있습니다. 
뷰 페이지에서 폼으로 입력 받은 데이터를 VO를 통해 Constructor Class를 이용했습니다.
기능에 따라 Method를 정의해서 기능을 용도에 따라 이용했습니다.
Text파일에 저장된 퀴즈들을 DAO pattern을 이용해서 ArrayList에 담아 불러오거나 저장했습니다. 
<br>Quiz can be created in quiz generation form. 
Constructor class is used via ValueObject (VO) for the data given by the form in the view page.
Method is defined for each function. 
Quizzes saved in the text-file can be loaded and saved into ArrayList by using DAO pattern.
![M1](/web/psd/M1.jpg)
