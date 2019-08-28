package com.ale.rest.service.person;

import com.ale.common.exception.BusinessException;
import com.ale.domain.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 服务实现类
 *
 * @author alewu
 * @date 2019-08-02
 */
@Service
@Slf4j
public class PersonServiceImpl implements PersonService {

    @Override
    public Person getPerson() {
        Person person = new Person();
        person.setName("jack");
        person.setProfession("java developer");
        person.setBirthday(new Date());
        log.info("ok");
        try {
            int a = 1/0;
        } catch (Exception e) {
            throw new BusinessException(2333, "error");
        }

        return person;
    }
}
