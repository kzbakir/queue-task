create user queue with encrypted password 'queue';
create database queue;
grant all on database queue to queue;
alter role queue in database queue set timezone to 'Asia/Almaty';