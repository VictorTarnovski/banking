create or replace trigger insert_users_history
  after insert or update 
  on users 
  for each row
execute function insert_history();