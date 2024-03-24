CREATE TABLE users (
    id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email TEXT NOT NULL,
    password TEXT NOT NULL,
    role SMALLINT
);

