CREATE EXTENSION IF NOT EXISTS pg_trgm;

CREATE OR REPLACE FUNCTION generate_searchable(_nome VARCHAR, _apelido VARCHAR, _stack VARCHAR)
    RETURNS TEXT AS
$$
BEGIN
    RETURN _nome || _apelido || _stack;
END;
$$ LANGUAGE "plpgsql" IMMUTABLE;

CREATE TABLE IF NOT EXISTS people
(
    id         uuid DEFAULT gen_random_uuid() UNIQUE NOT NULL,
    nome       TEXT                                  NOT NULL,
    apelido    TEXT UNIQUE                           NOT NULL,
    nascimento TEXT                                  NOT NULL,
    stack      TEXT,
    searchable TEXT GENERATED ALWAYS AS (generate_searchable(nome, apelido, stack)) STORED
);

CREATE INDEX IF NOT EXISTS idx_people_searchable ON public.people USING gist (searchable public.gist_trgm_ops (siglen='64'));
CREATE UNIQUE INDEX IF NOT EXISTS people_apelido_index ON public.people USING btree (apelido)