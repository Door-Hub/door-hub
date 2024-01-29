CREATE TABLE category_parents
(
    category_id INT unique ,
    parent_id   INT unique ,
    PRIMARY KEY (category_id,parent_id) ,
    FOREIGN KEY (category_id) REFERENCES category(id) ON DELETE CASCADE,
    FOREIGN KEY (parent_id) REFERENCES parent(id) ON DELETE CASCADE
);
