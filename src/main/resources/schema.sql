CREATE TABLE IF NOT EXISTS books (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100)
);

INSERT INTO books (id, name)
VALUES
    (1, 'Java'),
    (2, 'Python')
ON CONFLICT (id) DO NOTHING;

SELECT setval(
    'books_id_seq',
    COALESCE((SELECT MAX(id) FROM books), 1),
    EXISTS (SELECT 1 FROM books)
);