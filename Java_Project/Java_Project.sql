
SELECT * FROM tbl_java_user;

INSERT INTO tbl_java_user
VALUES ('admin', 'admin', '관리자', 'Y', 'admin@naver.com', 0);

INSERT INTO tbl_java_user
VALUES ('user1', 'user1', '사용자', 'N', 'user1@naver.com', 100000);


SELECT * FROM tbl_java_product;

INSERT INTO tbl_java_product
VALUES (1, '혼자 공부하는 자바', '한빛미디어', 30000, 5);

INSERT INTO tbl_java_product
VALUES (2, '같이 공부하는 자바', '한울', 10000, 0);

-- 계정중에서 admin값이 Y인 계정의 id,pw 조회
SELECT  id, pw
FROM    tbl_java_user
WHERE   admin = 'Y';


SELECT  id
FROM    tbl_java_user
WHERE   id = 'admin';

-- 입력받은 id의 pw 조회 (' '의 값이 입력한 값)
SELECT  pw
FROM    tbl_java_user
WHERE   id = 'admin';

-- 입력받은 id의 admin 값 조회
SELECT  admin
FROM    tbl_java_user
WHERE   id = 'admin';

SELECT  admin
FROM    tbl_java_user
WHERE   id = 'asdfa';


COMMIT; -- ★ 커밋을 꼭 합시다

SELECT * FROM tbl_java_product;

