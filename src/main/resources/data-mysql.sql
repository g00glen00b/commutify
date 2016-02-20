# Numbers from https://www.delijn.be/en/overdelijn/organisatie/zorgzaam-ondernemen/milieu/co2-uitstoot-voertuigen.html
INSERT INTO commuty_type (id, name, emission) VALUES
  (1, 'Tram', 0.4),
  (2, 'Bus', 3),
  (3, 'Carpool', 35.75),
  (4, 'Bike', 0),
  (5, 'Train', 0.28),
  (6, 'By foot', 0)
ON DUPLICATE KEY UPDATE name=name, emission=emission;