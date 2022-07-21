# DB-Intersection
## Second homework @ DB Cloud School

A multithreaded implementation of an intersection using a single semaphore.

The reasoning behind using only a single semaphore is that both threads access
the same "point of entry". Using two semaphores would've been more error-prone
as the permits would have to be manually increased or decreased in order to
synchronise them.

I implemented a context switch as well, though it is not time based. Rather,
after a certain number of cars pass the semaphore, the thread releases the
permit and sleeps for a very short period, enough to allow the other thread
to acquire the permit. Due to this, if a thread has cleaned its `Direction`,
the semaphore will allow all cars on the other Direction to pass (unlike how
it is in real life).

[Link](https://github.com/Lynuxen/DB-Intersection) to the GitHub repository.