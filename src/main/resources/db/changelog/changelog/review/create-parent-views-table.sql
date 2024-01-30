CREATE TABLE parent_views
(
    parent_id INT,
    view_id   INT,
    PRIMARY KEY (parent_id, view_id),
    FOREIGN KEY (parent_id) REFERENCES parent (id) ON DELETE CASCADE,
    FOREIGN KEY (view_id) REFERENCES review (id) ON DELETE CASCADE
);