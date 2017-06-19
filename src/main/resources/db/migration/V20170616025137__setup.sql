
DROP TABLE IF EXISTS patient;
DROP TABLE IF EXISTS room;
DROP TABLE IF EXISTS staff;
DROP TABLE IF EXISTS patient_record;
DROP TABLE IF EXISTS admission;

CREATE TABLE patient
(
  patient_id    BIGINT AUTO_INCREMENT
    PRIMARY KEY,
  admitted      BIT          NULL,
  birth_date    DATE         NULL,
  first_name    VARCHAR(255) NOT NULL,
  middle_name   VARCHAR(255) NULL,
  gender        VARCHAR(255) NULL,
  height        BIGINT       NOT NULL,
  last_name     VARCHAR(255) NOT NULL,
  next_of_kin   VARCHAR(255) NULL,
  other_details VARCHAR(255) NULL,
  phone         VARCHAR(255) NULL,
  weight        BIGINT       NOT NULL,
  address_line1 VARCHAR(255) NULL,
  address_line2 VARCHAR(255) NULL,
  city          VARCHAR(255) NULL,
  state         VARCHAR(255) NULL,
  zip_code      INT          NOT NULL
);


CREATE TABLE room
(
  room_number BIGINT AUTO_INCREMENT
    PRIMARY KEY,
  free_beds   INT NOT NULL
);

CREATE TABLE staff
(
  staff_id   BIGINT AUTO_INCREMENT
    PRIMARY KEY,
  first_name VARCHAR(255) NULL,
  last_name  VARCHAR(255) NULL,
  position   VARCHAR(255) NULL
);

CREATE TABLE admission
(
  admission_id   BIGINT AUTO_INCREMENT
    PRIMARY KEY,
  admit_date     DATE   NULL,
  discharge_date DATE   NULL,
  staff_id       BIGINT NOT NULL,
  room_number    BIGINT NOT NULL,
  patient_id     BIGINT NOT NULL,
  CONSTRAINT staffFK
  FOREIGN KEY (staff_id) REFERENCES staff (staff_id),
  CONSTRAINT roomFK
  FOREIGN KEY (room_number) REFERENCES room (room_number),
  CONSTRAINT patientFK
  FOREIGN KEY (patient_id) REFERENCES patient (patient_id)
);

CREATE INDEX roomNumberIndex
  ON admission (room_number);

CREATE INDEX patientIndex
  ON admission (patient_id);

CREATE TABLE patient_record
(
  patient_record_id BIGINT AUTO_INCREMENT
    PRIMARY KEY,
  notes             VARCHAR(255) NULL,
  staff_id          BIGINT       NOT NULL,
  patient_id        BIGINT       NOT NULL,
  CONSTRAINT staffFK2
  FOREIGN KEY (staff_id) REFERENCES staff (staff_id),
  CONSTRAINT patientFK2
  FOREIGN KEY (patient_id) REFERENCES patient (patient_id)
);

CREATE INDEX patientIndex
  ON patient_record (patient_id);



