CREATE TABLE mail_hist (
  id SERIAL,
  listTo TEXT,
  listCc TEXT,
  subject TEXT,
  body TEXT,
  state TEXT NOT NULL,
  date TIMESTAMP,
  list_attach VARCHAR,
  CONSTRAINT mail_hist_pkey PRIMARY KEY (id)
);

COMMENT ON TABLE mail_hist
IS 'История отправки email';

COMMENT ON COLUMN mail_hist.date
IS 'Начальное время отправки';
