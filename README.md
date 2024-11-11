# 🤹‍♀️ 스레드 로컬
각 스레드마다 별도의 내부 저장소를 제공하여 해당 스레드에서만 접근할 수 있는 저장소

----------

# ✅ 테스트 결과
## 🌚 스레드 로컬 적용 전
Thread-A가 실행될 때
- store는 처음에 null인 상태
- Thread-A가 store에 userA를 저장하고, store는 userA로 설정됨

Thread-B가 실행될 때
- Thread-A로 인해 store는 userA 값을 가지고 있음
- Thread-B가 store에 userB를 저장하고, userA에서 userB로 덮어씌워짐

store 라는 인스턴스 필드를 공유하고 있기 때문에 값을 덮어씌우면서 Thread-A는 userA를 가질 수 없게 됐다. </br>

16:34:55.254 [Thread-A] INFO thread.local.BeforeThreadLocal -- [저장] name=userA, store=null </br>
16:34:57.261 [Thread-B] INFO thread.local.BeforeThreadLocal -- [저장] name=userB, store=userA </br>
16:34:58.270 [Thread-A] INFO thread.local.BeforeThreadLocal -- [조회] store=userB </br>
16:35:00.264 [Thread-B] INFO thread.local.BeforeThreadLocal -- [조회] store=userB

## 🌝 스레드 로컬 적용 후
스레드 로컬을 적용하면 각 스레드가 자신의 고유한 저장 공간을 갖기 때문에 독립적으로 인스턴스 필드를 사용하는 것처럼 동작하게 된다. </br>

17:14:22.674 [Thread-A] INFO thread.local.AfterThreadLocal -- [저장] name=userA, store=null </br>
17:14:24.674 [Thread-B] INFO thread.local.AfterThreadLocal -- [저장] name=userB, store=null </br>
17:14:25.689 [Thread-A] INFO thread.local.AfterThreadLocal -- [조회] store=userA </br>
17:14:27.678 [Thread-B] INFO thread.local.AfterThreadLocal -- [조회] store=userB

--------------

# 스레드 로컬(Thread Local)을 사용하는 이유는 무엇인가요?
모든 스레드는 같은 인스턴스 필드를 공유하기 때문에 데이터가 덮어씌워질 수 있습니다. </br>
데이터가 덮어씌워지는 것을 방지하기 위해 스레드 로컬을 사용해야 합니다. </br>
스레드 로컬을 사용하면 각 스레드는 자신의 고유한 저장 공간을 갖기 때문에 다른 스레드와 상관없이 독립적으로 값을 저장하고 사용할 수 있어서 데이터가 덮어씌워지는 것을 방지할 수 있습니다.

# 스레드 로컬의 메모리 누수가 발생할 수 있는 상황은 언제인가요?
사용한 스레드 로컬 데이터를 지우지 않고 그대로 두면 스레드를 재사용할 때 이전 값이 남아있어서 메모리 누수가 발생할 수 있습니다. </br>
스레드 로컬에 저장된 데이터는 스레드가 종료될 때, 자동으로 삭제되지 않기 때문에 사용한 스레드 로컬 데이터를 remove() 메서드를 사용해 제거해야 합니다.

# 스레드 로컬과 synchronized의 성능 차이는 어떻게 되나요?
상황에 따라 다를 수 있기 때문에 성능 차이에 대해서는 확실하게 답하기 어렵습니다. </br>
스레드 로컬은 각 스레드마다 독립적인 저장 공간을 갖고 있기 때문에 서로 다른 스레드가 자신만의 값을 가질 수 있고, synchronized는 여러 스레드가 동일한 변수에 접근하기 때문에 스레드별로 값을 다르게 가져갈 수 없습니다.

-----------

# 📝 참고
- https://lucas-owner.tistory.com/53
- https://inma.tistory.com/171
