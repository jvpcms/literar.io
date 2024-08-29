CREATE SCHEMA IF NOT EXISTS public;

CREATE TABLE IF NOT EXISTS public.users (
  id UUID NOT NULL PRIMARY KEY DEFAULT gen_random_uuid(),
  created_at TIMESTAMP NOT NULL DEFAULT now(),
  username VARCHAR(255) NOT NULL,
  salt BYTEA NOT NULL,
  hash TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS public.authors (
    id UUID NOT NULL PRIMARY KEY DEFAULT gen_random_uuid(),
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    name VARCHAR(255) NOT NULL,
    description TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS public.books (
    id UUID NOT NULL PRIMARY KEY DEFAULT gen_random_uuid(),
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    title VARCHAR(255) NOT NULL,
    synopsis TEXT NOT NULL,
    author_id UUID NOT NULL,
    CONSTRAINT fk_author FOREIGN KEY (author_id) REFERENCES authors(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS public.reading_lists (
    id UUID NOT NULL PRIMARY KEY DEFAULT gen_random_uuid(),
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    user_id UUID NOT NULL,
    book_id UUID NOT NULL,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT fk_book FOREIGN KEY (book_id) REFERENCES books(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS public.reviews (
    id UUID NOT NULL PRIMARY KEY DEFAULT gen_random_uuid(),
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    user_id UUID NOT NULL,
    book_id UUID NOT NULL,
    rating INT NOT NULL,
    review TEXT NOT NULL,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT fk_book FOREIGN KEY (book_id) REFERENCES books(id) ON DELETE CASCADE
);