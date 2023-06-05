# Branch 별 구현 목록

---
### entity
- member, participants, organizer, participants_allergens, allergens Table 구현 
- h2 DB 셋팅 
- aggregate 분리 
  - member -> member
  - organizer -> organizer
  - participant -> participants, participants_allergens, allergens

---
### member_join
- 사용자 기본 정보와 주최자/참여자 정보를 받아 가입을 한다.

---
### member_login
- 사용자 로그인 기능을 구현한다
  - 사용자 ID와 Password를 받으면 아래와 같은 로직이 수행되어야 한다. 
    1. ID를 통한 Member Entity 획득 
    2. 사용자가 입력한 Password와 실제 Password를 비교하여 인증한다. 
    3. 인증 성공시, Member ID와 Role들을 저장하여 Token에 담아 반환한다.
- Spring Security를 통한 JWT Login 기능을 구현한다
  - Role은 MemberRoles Class에 있는 아래 3개로 관리된다. 
    - USER : 가장 기본적인 권한. 회원가입을 하면 모든 회원이 이 권한을 갖는다 
    - PARTICIPANT : 참여자 권한. 참여자 등록이 된 사용자는 권한을 가지게 된다 
    - ORGANIZER : 주최자 권한. 주최자 등록이 된 사용자는 권한을 가지게 된다
  - Role은 GrantedAuthorityDefaults 에 의해서, ROLE_ 라는 prefix를 가지고 토큰에 저장된다

---
### member_retrieve
- 사용자 정보 조회 기능을 구현한다 
  1. Authorization Header 내 Jwt Token을 통해 사용자 ID를 조회한다
     - Argument Resolver를 통해 획득한다
  2. 사용자 ID 기반 사용자 정보, 참여자 정보, 주최자 정보를 가져온다
     - MemberFacadeService를 통해 접근하며, 순서대로 사용자 정보, 참여자 정보, 주최자 정보를 가져온다. 

### add_role
- 주최자 권한 추가 기능을 구현한다. 
  1. 이미 주최자 권한이 있다면 추가 로직을 중단한다. 
  2. Authorization Header 내 Jwt Token을 통해 사용자 ID를 조회한다
    - Argument Resolver를 통해 획득한다
  3. 주최자 권한에 필요한 추가정보를 받아 주최자 정보와 Role을 추가한다. 
- 참여자 권한 추가 기능을 구현한다. 
  1. 이미 참여자 권한이 있다면 추가 로직을 중단한다. 
  2. Authorization Header 내 Jwt Token을 통해 사용자 ID를 조회한다
     - Argument Resolver를 통해 획득한다
  3. 참여자 권한에 필요한 추가정보를 받아 참여자 정보와 Role을 추가한다. 

### member_update
- 사용자 정보 수정 기능을 구현한다. 
  1. 수정 정보를 아래와 같이 받는다. 
     - member(필수)
     - organizer(사용자가 주최자 권한을 갖고있다면 필수)
     - participant(사용자가 참여자 권한을 갖고있다면 필수)
  2. 요청값을 통해 각 정보(member, organizer, participant)를 수정한다. 