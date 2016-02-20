CREATE TABLE IF NOT EXISTS profile (
  id INT(11) NOT NULL AUTO_INCREMENT,
  email VARCHAR(128) NOT NULL,
  password BINARY(60) NOT NULL,
  first_name VARCHAR(32) NOT NULL,
  last_name VARCHAR(32),
  avg_km_day DECIMAL(5, 2),
  emission DECIMAL(5, 2) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY (email)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS commuty_entry (
  id INT(11) NOT NULL AUTO_INCREMENT,
  type_id INT(11) NOT NULL,
  profile_id INT(11) NOT NULL,
  km DECIMAL(5, 2) NOT NULL,
  date DATE,
  normal_emission DECIMAL(5, 2) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY (profile_id, date)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS commuty_type (
  id INT(11) NOT NULL,
  name VARCHAR(32) NOT NULL,
  emission DECIMAL(5, 2) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY (name)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;