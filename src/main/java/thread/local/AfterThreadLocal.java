package thread.local;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AfterThreadLocal {

    private ThreadLocal<String> store = new ThreadLocal<>();

    public String logic(String name) {
        log.info("[저장] name={}, store={}", name, store.get());
        store.set(name);
        sleep(3000);
        log.info("[조회] store={}", store.get());

        return store.get();
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
