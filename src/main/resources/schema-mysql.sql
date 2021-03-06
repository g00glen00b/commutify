CREATE TABLE IF NOT EXISTS profile (
  id INT(11) NOT NULL AUTO_INCREMENT,
  email VARCHAR(128) NOT NULL,
  password BINARY(60) NOT NULL,
  first_name VARCHAR(32) NOT NULL,
  last_name VARCHAR(32),
  avg_km_day DECIMAL(5, 2) DEFAULT 0,
  emission DECIMAL(5, 2) DEFAULT 0,
  saved_gasses DECIMAL(8, 2) DEFAULT 0,
  PRIMARY KEY (id),
  UNIQUE KEY (email)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS commutify_entry (
  id INT(11) NOT NULL AUTO_INCREMENT,
  type_id INT(11) NOT NULL,
  profile_id INT(11) NOT NULL,
  km DECIMAL(5, 2) NOT NULL,
  date DATETIME NOT NULL,
  normal_emission DECIMAL(5, 2) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY (profile_id, date)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS commutify_type (
  id INT(11) NOT NULL,
  name VARCHAR(32) NOT NULL,
  emission DECIMAL(5, 2) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY (name)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS car_emission (
  manufacturer VARCHAR(32) NOT NULL,
  model VARCHAR(64) NOT NULL,
  type VARCHAR(128) NOT NULL,
  emission DECIMAL(5, 2) NOT NULL,
  PRIMARY KEY(manufacturer,model,type)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;