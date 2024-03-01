DROP SEQUENCE IF EXISTS token_id_sequence;
CREATE SEQUENCE token_id_sequence START WITH 2 INCREMENT BY 1;

DROP SEQUENCE IF EXISTS user_uid_sequence;
CREATE SEQUENCE user_uid_sequence START WITH 2 INCREMENT BY 1;

CREATE TYPE token_type AS ENUM ('BEARER', 'CUSTOM');

DROP TABLE IF EXISTS "user";
CREATE TABLE "user" (
        uid INT DEFAULT NEXTVAL('user_uid_sequence') PRIMARY KEY,
        usn VARCHAR(250) NOT NULL,
        name VARCHAR(250) NOT NULL,
        password VARCHAR(250) NOT NULL,
        role VARCHAR(250) NOT NULL,
        status INT NOT NULL default 0,
        deleted INT NOT NULL default 0,
        created TIMESTAMP DEFAULT now()
);

DROP TABLE IF EXISTS token;
CREATE TABLE token (
        id INT DEFAULT NEXTVAL('token_id_sequence') PRIMARY KEY,
        user_id VARCHAR(250) NOT NULL,
        user_name VARCHAR(30) NULL,
        token VARCHAR(250) NOT NULL,
        tokenType token_type NOT NULL DEFAULT 'BEARER',
        revoked boolean NOT NULL default false,
        expired boolean NOT NULL default false,
        created TIMESTAMP DEFAULT now()
);


