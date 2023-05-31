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

### JPQL
- DB 테이블이 아닌, entity를 대상으로 쿼리 작성, SQL과 유사
- 자동생성: 쿼리 메서드(Spring Data), JPA Criteria, Querydsl
- 수동생성: em.createQuery(), @Query 
  - TypedQuery로 변환된다. (nativeQuery=true로 조건 필요)

### QueryDSL
- JPQL과 SQL은 문자열이므로 타입이나 구문 체크가 어려움
- 쿼리를 타입에 안전하게 빌드해주는 JPA Criteria API 등장. but 길고 어려움
  - 쿼리 메서드를 보완한다.
  - Q Type: Entity를 기반으로 자동 생성된 클래스
  - JPAQuery: JPLQuery의 구현체. 직접 생성하거나 JPAQueryFactory를 통해 생성
  - JPAQueryFactory: JPAQuery를 생성해주는 클래스
  - 쿼리 작성에 JPAQueryFactory, Q타입과 Q타입 필드의 메서드를 이용
  - 일부 필드만 조회할 때는 JPAQuery<Tuple>, 하나는 JPAQuery<T>에 타입 지정
  - BooleanBuilder를 이용하면 조건에 따라 쿼리가 달라지게 할수 있다.(동적 쿼리)
 