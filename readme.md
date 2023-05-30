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

### JPA는 쿼리를 작성하는 다양한 방법 지원
- JPQL: DB테이블이 아닌 entity를 대상으로한 쿼리 작성(대소문자 구분함)
- 쿼리 메서드: 메서드 이름으로 JPQL을 자동 생성
- Criteria: JPQL을 메서드의 조합으로 작성
- QueryDSL: JPQL을 메서드의 조합으로 작성(오픈소스)
- NativeSQL : SQL을 직접 작성 @Query

