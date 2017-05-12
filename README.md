# blogtutorial

`src/main/resources/application.properties`のデータベース接続情報を変更する

sql

```sql

CREATE DATABASE testdb
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Japanese_Japan.932'
    LC_CTYPE = 'Japanese_Japan.932'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;
    
    
CREATE TABLE article
(
    id SERIAL PRIMARY KEY,
    title character varying(50) NOT NULL,
    description character varying(500) NOT NULL,
    created_at timestamp(6) with time zone NOT NULL,
    updated_at timestamp(6) with time zone NOT NULL,
    user_id integer NOT NULL
);

```

`http://localhost:8080/article/`にアクセスする

進捗：
記事のCRUD操作を実装
バリデーションチェックまではできていない。
