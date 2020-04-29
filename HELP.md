
  Bon cependant, se pose tout de même la question : que ce passe t'il en cas de rollback ?

  On ne devrait pas forcément continuer le second process tooLongOperation si la méthode getUsers() a été rollbackée!


https://dzone.com/articles/microservices-and-the-saga-pattern-part-1?preview=true
https://dzone.com/articles/microservices-and-the-saga-pattern-part-2
https://dzone.com/articles/microservices-and-the-saga-pattern-part-3


en plus simple : 
https://panlw.github.io/15459049161482.html

List of useful stat queries for PSQL
https://gist.github.com/anvk/475c22cbca1edc5ce94546c871460fdd

Reliable domain events
http://pillopl.github.io/reliable-domain-events/

In PostgreSQL, as in life, don't wait too long to commit
https://lerner.co.il/2015/09/17/in-postgresql-as-in-life-dont-wait-too-long-to-commit/

Logger les transactions
https://stackoverflow.com/questions/30576385/how-to-log-the-start-and-the-completion-of-db-transactions-in-hibernate


https://dzone.com/articles/transaction-synchronization-and-spring-application

https://resilience4j.readme.io/docs/retry#section-create-a-retryregistry


Spring Async nombre de threads
https://stackoverflow.com/questions/13206792/spring-async-limit-number-of-threads

cf https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-task-execution-scheduling


    // il faudrait trouver un moyen pour store un Event dans le cas ou il s'est toujours pas retry ?
    // que ce passe t'il si on est en cours de retry et le serveur FAIL avant d'avoir fait le retry ?
    
    on a la notion d'event store
    
   https://blog.engineering.publicissapient.fr/2017/01/16/event-sourcing-comprendre-les-bases-dun-systeme-evenementiel/
   
   
Les bombes à retardement de l'Event Sourcing / CQRS
https://www.youtube.com/watch?v=tj80_nCDr18
   
   

