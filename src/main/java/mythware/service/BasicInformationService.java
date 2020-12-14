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
        return dao.updateBasicInformation(basicInformation);
    }

    public BasicInformation findBasicInformation() {
        return dao.findBasicInformation();
    }
}
