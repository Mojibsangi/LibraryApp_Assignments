select * from users;

-- US01-1
   -- OPT 1
    select count(id) from users;           -- 138  -- ACTUAL
    select  count(distinct id) from users; -- 138  -- EXPECTED

   -- OPT 2
    select id from users;
    -- getAllColumnAsList --> List --> size  --> EXPECTED
    -- getAllColumnAsList --> Set  --> size  --> EXPECTED

    -- US01-1

select * from book_borrow;

select count(book_id) from book_borrow where is_returned = 0;

SELECT * FROM  users WHERE false;


select name from book_categories;



select * from books where name ='The Way of Kings';




