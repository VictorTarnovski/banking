create trigger update_users_updated_at
  before update
  on users 
  for each row
execute procedure update_updated_at();