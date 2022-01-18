package com.example.demo.controller;


import com.example.demo.model.User;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManagerFactory;

@RestController
@RequestMapping("/user")
@Log4j2
public class InsertController {

    @Autowired
    private TaskExecutor taskExecutor;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @GetMapping("/add")
    public Object addBatch() {
        log.info("start time: "+System.currentTimeMillis());
        for (int i = 0; i < 10; ++i) {
            doTask();
        }
        return "ok";
    }

    public void doTask() {

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        taskExecutor.execute(() -> {
            Session session = sessionFactory.openSession();
            session.getTransaction().begin(); // begin transaction

            //begin insert
            for (int j = 0; j < 100000; ++j) {
                session.save(new User(null, "sajdasfasssfajfn asjdafaf", "sajdasfasssfajfn asjdafaf", "sajdasfasssfajfn asjdafaf"));
                if (j % 10000 == 0) {
                    session.flush();
                    session.clear();

                }
            }

            session.getTransaction().commit(); // commit tran
            session.close(); // close
            log.info("finish time: "+System.currentTimeMillis());
        });

    }

}
