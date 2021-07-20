# SpringBoot-Redis
 
 Redis คือ open source ที่เก็บโครงสร้างข้อมูลไว้ใน memory ซึ่งเป็น NOSQL ตัวหนึ่ง สามารถใช้ทำ database, cache, message broker ได้
 
### Redis configuration file

- Windows (redis.windows.conf)
 
		redis-server redis.windows.conf

- Linux (redis.conf)


### Connect to Redis with (redis-cli)

 | Argument name | Description |
 | ------------- |-------------|
 | -h  | hostname |
 | -p  | port |
 | -a  | password |



	redis-cli -h localhost -p 6379 

      
### Redis keys commands

url : https://redis.io/commands

open redis-cli

 - Delete All Key
                  
       FLUSHALL

-  Delete key if it exists.

       DEL key

### Tools
- Another Redis DeskTop Manager (url = https://github.com/qishibo/AnotherRedisDesktopManager)

## Reference

- https://medium.com/@hulunhao/how-to-use-redis-template-in-java-spring-boot-647a7eb8f8cc
- https://github.com/microsoftarchive/redis/releases/tag/win-2.8.2104
- https://docs.spring.io/spring-data/redis/docs/2.1.3.RELEASE/reference/html/
- https://github.com/quintes/redis-sentinel-config
- https://dev.to/setevoy/redis-replication-part-1-an-overview-replication-vs-sharding-sentinel-vs-cluster-redis-topology-3ao9
- https://www.tecmint.com/setup-redis-high-availability-with-sentinel-in-centos-8/
- https://medium.com/@bhanuchaddha/using-redis-pub-sub-with-spring-boot-ea0d7a8c27af
