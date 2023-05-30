# 내용정리
- Entity 클래스: DB테이블의 한 행을 정의한것
  - 키(PK)로 사용할 속성에 @Id를 붙인다.
  - 순서 보장은 하지않음.
  - Hibernate가 PreparedStatement를 제공(보안성, 안정성 증가)
- 트랜젝션이란, 더이상 나눌수 없는 작업의 단위
- **PersistenceContext**, Entity를 저장하는 공간, perist는 entity를 영속 상태로 변경
  - Entity를 영속 상태로 바꾸고, Sql을 db로 전달
  - Map으로 관리 Entity의 @id컬럼을 key로 사용
  - 캐시에 없을 때만 DB에서 조회