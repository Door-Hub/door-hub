CREATE TABLE parent_discounts
(
    parent_id   INT,
    discount_id INT,
    PRIMARY KEY (parent_id, discount_id),
    FOREIGN KEY (parent_id) REFERENCES parent (id) ON DELETE CASCADE,
    FOREIGN KEY (discount_id) REFERENCES discount (id) ON DELETE CASCADE
);