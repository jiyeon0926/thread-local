package thread.local;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BeforeThreadLocalTest {

    private BeforeThreadLocal before = new BeforeThreadLocal();

    Runnable userA = () -> {
        before.logic("userA");
    };

    Runnable userB = () -> {
        before.logic("userB");
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
        BeforeThreadLocalTest test = new BeforeThreadLocalTest();
        test.execute();
    }
}
