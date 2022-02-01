insert into scores(gebruikerId, filmId, score)
values ((select id from gebruikers where email = 'test@test'), 1, 1);