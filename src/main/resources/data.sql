INSERT INTO ARTICLES (article_id, title, author, content, date_created, date_modified, is_public)
VALUES (HIBERNATE_SEQUENCE.NEXTVAL, 'Naslov1', 'Author1', 'Neki tekst1', CURRENT_DATE, CURRENT_DATE, TRUE),
       (HIBERNATE_SEQUENCE.NEXTVAL, 'Naslov2', 'Author1', 'Neki tekst2', CURRENT_DATE, CURRENT_DATE, TRUE),
       (HIBERNATE_SEQUENCE.NEXTVAL, 'Naslov3', 'Author1', 'Neki tekst3', CURRENT_DATE, CURRENT_DATE, TRUE),
       (HIBERNATE_SEQUENCE.NEXTVAL, 'Naslov4', 'Author1', 'Neki tekst4', CURRENT_DATE, CURRENT_DATE, TRUE),
       (HIBERNATE_SEQUENCE.NEXTVAL, 'Naslov5', 'Author1', 'Neki tekst5', CURRENT_DATE, CURRENT_DATE, TRUE),
       (HIBERNATE_SEQUENCE.NEXTVAL, 'Naslov6', 'Author1', 'Neki tekst6', CURRENT_DATE, CURRENT_DATE, TRUE),
       (HIBERNATE_SEQUENCE.NEXTVAL, 'Naslov7', 'Author1', 'Neki tekst7', CURRENT_DATE, CURRENT_DATE, TRUE),
       (HIBERNATE_SEQUENCE.NEXTVAL, 'Naslov8', 'Author1', 'Neki tekst8', CURRENT_DATE, CURRENT_DATE, TRUE),
       (HIBERNATE_SEQUENCE.NEXTVAL, 'Naslov9', 'Author1', 'Neki tekst9', CURRENT_DATE, CURRENT_DATE, TRUE),
       (HIBERNATE_SEQUENCE.NEXTVAL, 'Naslov10', 'Author1', 'Neki tekst10', CURRENT_DATE, CURRENT_DATE, TRUE),
       (HIBERNATE_SEQUENCE.NEXTVAL, 'Naslov11', 'Author1', 'Neki tekst11', CURRENT_DATE, CURRENT_DATE, TRUE),
       (HIBERNATE_SEQUENCE.NEXTVAL, 'Naslov12', 'Author1', 'Neki tekst12', CURRENT_DATE, CURRENT_DATE, TRUE);

INSERT INTO users (username, password, enabled)
values ('user',
        '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.AQubh4a',
        1);

INSERT INTO authorities (username, authority)
values ('user', 'ROLE_USER');
