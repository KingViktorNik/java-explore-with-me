CREATE TABLE IF NOT EXISTS endpoint_hit
(
    id         BIGINT GENERATED BY DEFAULT AS IDENTITY,
    app        VARCHAR(100) NOT NULL,
    uri        VARCHAR(100)  NOT NULL,
    ip         VARCHAR(50)  NOT NULL,
    time_stamp TIMESTAMP    NOT NULL,
    CONSTRAINT pk_endpoint_hit PRIMARY KEY (id)
);
