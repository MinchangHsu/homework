-- for MySQL
DROP TABLE IF EXISTS exchange_rate;
DROP TABLE IF EXISTS exchange_rate_time;
DROP TABLE IF EXISTS coin;

CREATE TABLE IF NOT EXISTS coin
(
    id         bigint       NOT NULL AUTO_INCREMENT COMMENT '流水號',
    disclaimer VARCHAR(300) NULL,
    chartName  VARCHAR(300) NULL,
    PRIMARY KEY (ID)
);

CREATE TABLE IF NOT EXISTS exchange_rate_time
(
    id         bigint    NOT NULL AUTO_INCREMENT COMMENT '流水號',
    coin_id    bigint    NOT NULL,
    updated    timestamp NULL,
    updatediso timestamp NULL,
    updateduk  timestamp NULL,
    PRIMARY KEY (id),
    CONSTRAINT exchange_rate_time_coin_id_FK
        FOREIGN KEY (coin_id) REFERENCES coin (ID)
            ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS exchange_rate
(
    id                    bigint         NOT NULL AUTO_INCREMENT COMMENT '流水號',
    coin_id               bigint         NOT NULL,
    exchange_rate_time_id bigint         NOT NULL,
    code                  varchar(50)    NULL,
    description           varchar(300)   NULL,
    rate                  varchar(50)    NULL,
    symbol                varchar(50)    NULL,
    rate_float            decimal(12, 4) NULL,
    description           timestamp      NULL,
    updateduk             timestamp      NULL,
    PRIMARY KEY (id),
    CONSTRAINT exchange_rate_time_exchange_rate_time_id_FK
        FOREIGN KEY (exchange_rate_time_id) REFERENCES exchange_rate_time (ID)
            ON UPDATE CASCADE ON DELETE CASCADE
);