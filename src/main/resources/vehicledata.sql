DROP TABLE IF EXISTS vehicle;
 
CREATE TABLE vehicle (
  id VARCHAR(50) PRIMARY KEY,
  vin VARCHAR(17) NOT NULL,
  year INT NOT NULL,
  make VARCHAR(50) DEFAULT NULL,
  model VARCHAR(50) DEFAULT NULL,
  transmission_type VARCHAR(1) NOT NUll
);