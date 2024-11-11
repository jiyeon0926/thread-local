package thread.local;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BeforeThreadLocal {

    private String store;

    public String logic(String name) {
        log.info("[저장] name={}, store={}", name, store);
        store = name;
        sleep(3000); // 현재 스레드를 3초동안 중지
        log.info("[조회] store={}", store);

        return store;
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
