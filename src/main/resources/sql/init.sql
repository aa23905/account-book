-- 分类表
CREATE TABLE category (
                          id          BIGINT AUTO_INCREMENT PRIMARY KEY,
                          name        VARCHAR(50)  NOT NULL COMMENT '分类名称',
                          type        VARCHAR(10)  NOT NULL COMMENT 'INCOME 收入 / EXPENSE 支出',
                          description VARCHAR(255) DEFAULT NULL COMMENT '描述',
                          created_at  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          updated_at  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='分类表';

-- 账单记录表
CREATE TABLE transaction (
                             id               BIGINT AUTO_INCREMENT PRIMARY KEY,
                             category_id      BIGINT       NOT NULL COMMENT '分类 ID',
                             amount           DECIMAL(12,2) NOT NULL COMMENT '金额',
                             type             VARCHAR(10)  NOT NULL COMMENT 'INCOME 收入 / EXPENSE 支出',
                             description      VARCHAR(255) DEFAULT NULL COMMENT '备注',
                             transaction_date DATETIME     NOT NULL COMMENT '交易时间',
                             created_at       DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
                             updated_at       DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='账单记录表';

ALTER TABLE transaction ADD INDEX idx_category_id (category_id);

-- 初始分类数据
INSERT INTO category (name, type, description) VALUES
                                                   ('餐饮',   'EXPENSE', '三餐、外卖、零食'),
                                                   ('交通',   'EXPENSE', '公交、地铁、打车、油费'),
                                                   ('购物',   'EXPENSE', '日用品、服饰、数码'),
                                                   ('住房',   'EXPENSE', '房租、水电、物业'),
                                                   ('娱乐',   'EXPENSE', '电影、游戏、旅游'),
                                                   ('医疗',   'EXPENSE', '看病、买药'),
                                                   ('教育',   'EXPENSE', '课程、书籍'),
                                                   ('通讯',   'EXPENSE', '话费、网费'),
                                                   ('其他支出', 'EXPENSE', NULL),
                                                   ('工资',   'INCOME', '工资、奖金'),
                                                   ('兼职',   'INCOME', '副业收入'),
                                                   ('红包',   'INCOME', '微信红包、礼金'),
                                                   ('理财',   'INCOME', '利息、基金收益'),
                                                   ('其他收入', 'INCOME', NULL);