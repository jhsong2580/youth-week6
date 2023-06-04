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