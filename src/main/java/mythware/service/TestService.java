package mythware.service;

import mythware.dao.TestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;

@Service
public class TestService {

    public String a = "1";

    @Autowired TestDao dao;
    @Autowired private ThreadPoolExecutor threadPoolExecutor;


    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void addTest1(String id, CountDownLatch countDownLatch) {
//        threadPoolExecutor.execute(() -> {
//            try {
//                Thread.sleep(4000);
//                dao.addTest1(id);
//            } catch (Exception e) {
//
//            } finally {
//                countDownLatch.countDown();
//            }
//        });

        dao.addTest1(id);
    }
}
