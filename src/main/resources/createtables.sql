DROP TABLE IF EXISTS image;
DROP TABLE IF EXISTS app_user;

CREATE TABLE app_user (
    email VARCHAR(50) PRIMARY KEY,
    "password" VARCHAR(60) NOT NULL
);

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE if NOT EXISTS image (
    id uuid DEFAULT uuid_generate_v4 () PRIMARY KEY,
    title VARCHAR(50) UNIQUE NOT NULL,
    "owner" VARCHAR(50) NOT NULL,
    description VARCHAR(250),
    "content" bytea NOT NULL,
    extension VARCHAR(4) NOT NULL,
    CONSTRAINT "fk_image_app_user" FOREIGN KEY ("owner") REFERENCES app_user(email)
);