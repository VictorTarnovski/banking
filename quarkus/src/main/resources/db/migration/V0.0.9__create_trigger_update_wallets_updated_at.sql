create trigger update_wallets_updated_at
  before update
  on wallets 
  for each row
execute procedure update_updated_at();