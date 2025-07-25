create or replace trigger insert_wallets_history
  after insert or update 
  on wallets 
  for each row
execute function insert_history();
