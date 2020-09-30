DROP TABLE IF EXISTS meter_reading;
DROP TABLE IF EXISTS client;

CREATE TABLE client (
  id INTEGER(4) AUTO_INCREMENT  PRIMARY KEY,
  address_street_name VARCHAR(50) NOT NULL,
  address_street_number VARCHAR(20) NOT NULL,
  address_city VARCHAR(50) NOT NULL,
  CONSTRAINT uk_address UNIQUE (address_street_name, address_street_number, address_city)
);

INSERT INTO client (address_street_name, address_street_number, address_city) VALUES
  ('Zagrebačka', '4A', 'Zagreb'),
  ('Petrinjska', '14', 'Zagreb'),
  ('Splitska', '5', 'Split');


CREATE TABLE meter_reading (
  id INTEGER(4) AUTO_INCREMENT PRIMARY KEY,
  year INTEGER(4) NOT NULL,
  month INTEGER(2) NOT NULL,
  value DECIMAL(20,2) NOT NULL,
  client_id INTEGER(4) NOT NULL,
  FOREIGN KEY (client_id) REFERENCES client(id),
  CONSTRAINT chk_month CHECK (month>=0 and month <=11),
  CONSTRAINT uk_meter UNIQUE (client_id, year, month)
);

INSERT INTO meter_reading (year, month, value, client_id) VALUES
  (2020, 0, 30.25, 1),
  (2020, 4, 35.7, 1),
  (2020, 8, 14.2, 1),
  (2019, 6, 50.02, 1),
  (2020, 1, 44.5, 2),
  (2020, 3, 22.03, 2),
  (2019, 3, 25.72, 2);