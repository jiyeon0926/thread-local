package thread.local;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AfterThreadLocalTest {

    private AfterThreadLocal after = new AfterThreadLocal();

    Runnable userA = () -> {
        after.logic("userA");
    };

    Runnable userB = () -> {
        after.logic("userB");
    };

    public void execute() {
        Thread threadA = new Thread(userA, "Thread-A");
        Thread threadB = new Thread(userB, "Thread-B");

        threadA.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            log.error("Thread interrupted", e);
        }

        threadB.start();
    }

    public static void main(String[] args) {
        AfterThreadLocalTest test= new AfterThreadLocalTest();
        test.execute();
    }
}
