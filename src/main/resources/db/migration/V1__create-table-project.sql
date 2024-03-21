CREATE TABLE project (
    id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    type VARCHAR(255) NOT NULL,
    image TEXT NOT NULL,
    deploy TEXT NOT NULL,
    github TEXT NOT NULL,
    description TEXT NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    finishedIn VARCHAR(255) NOT NULL,
    startedIn VARCHAR(255) NOT NULL,
    technologies TEXT[] NOT NULL
);