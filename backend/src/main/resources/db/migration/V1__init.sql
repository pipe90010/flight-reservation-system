CREATE TYPE reservation_status AS ENUM ('PENDING','CONFIRMED','CANCELLED');

CREATE TABLE users (
  id BIGSERIAL PRIMARY KEY,
  username VARCHAR(100) UNIQUE NOT NULL,
  password VARCHAR(255) NOT NULL,
  role VARCHAR(20) NOT NULL CHECK (role IN ('ADMIN','USER'))
);

CREATE TABLE flight (
  id BIGSERIAL PRIMARY KEY,
  origin VARCHAR(3) NOT NULL,
  destination VARCHAR(3) NOT NULL,
  departure_date DATE NOT NULL,
  available_seats INT NOT NULL CHECK (available_seats >= 0),
  price NUMERIC(10,2) NOT NULL CHECK (price >= 0)
);

CREATE TABLE passenger (
  id BIGSERIAL PRIMARY KEY,
  name VARCHAR(120) NOT NULL,
  email VARCHAR(200) NOT NULL,
  phone VARCHAR(40) NOT NULL
);

CREATE TABLE reservation (
  id BIGSERIAL PRIMARY KEY,
  flight_id BIGINT NOT NULL REFERENCES flight (id),
  passenger_id BIGINT NOT NULL REFERENCES passenger (id),
  status reservation_status NOT NULL DEFAULT 'PENDING',
  created_at TIMESTAMP NOT NULL DEFAULT now()
);
