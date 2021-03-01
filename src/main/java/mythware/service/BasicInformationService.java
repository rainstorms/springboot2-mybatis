package mythware.service;

import mythware.dao.BasicInformationDao;
import mythware.domain.BasicInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BasicInformationService {

    @Autowired BasicInformationDao dao;

    @Transactional
    public int updateBasicInformation(BasicInformation basicInformation) {
        return dao.updateById(basicInformation);
    }

    public BasicInformation findBasicInformation(String id) {
        return dao.selectById(id);
    }
}
