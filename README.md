# 스레드 로컬
각 스레드마다 별도의 내부 저장소를 제공하여 해당 스레드에서만 접근할 수 있는 저장소

----------

# 스레드 로컬 테스트
## 적용 전 결과
Thread-A가 실행될 때
- store는 처음에 null인 상태
- Thread-A가 store에 userA를 저장하고, store는 userA로 설정됨

Thread-B가 실행될 때
- Thread-A로 인해 store는 userA 값을 가지고 있음
- Thread-B가 store에 userB를 저장하고, userA에서 userB로 덮어씌워짐

store 라는 인스턴스 필드를 공유하고 있기 때문에 값을 덮어씌우면서 Thread-A는 userA를 가질 수 없게 됐다.

16:34:55.254 [Thread-A] INFO thread.local.BeforeThreadLocal -- [저장] name=userA, store=null </br>
16:34:57.261 [Thread-B] INFO thread.local.BeforeThreadLocal -- [저장] name=userB, store=userA </br>
16:34:58.270 [Thread-A] INFO thread.local.BeforeThreadLocal -- [조회] store=userB </br>
16:35:00.264 [Thread-B] INFO thread.local.BeforeThreadLocal -- [조회] store=userB </br>

--------------

# 스레드 로컬(Thread Local)을 사용하는 이유는 무엇인가요?

# 스레드 로컬의 메모리 누수가 발생할 수 있는 상황은 언제인가요?

# 스레드 로컬과 synchronized의 성능 차이는 어떻게 되나요?

-----------

# 📝 참고
- https://lucas-owner.tistory.com/53
- https://inma.tistory.com/171