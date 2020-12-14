package mythware.dao;

import mythware.domain.BasicInformation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface BasicInformationDao {

    String selectWebsiteBasicInformationPrefix = "SELECT  COMPANY_NAME companyName ,TELEPHONE telephone" +
            " ,FAX fax ,EMAIL email ,ADDRESS address ,UPDATE_TIME updateTime " +
            "    FROM  website_basic_information ";

    @Select(selectWebsiteBasicInformationPrefix)
    BasicInformation findBasicInformation();

    @Update("update website_basic_information " +
            "   SET COMPANY_NAME  = #{companyName}   " +
            "      ,TELEPHONE     = #{telephone}   " +
            "      ,FAX           = #{fax}   " +
            "      ,EMAIL         = #{email}   " +
            "      ,ADDRESS       = #{address}   " +
            "      ,UPDATE_TIME   = NOW()   ")
    int updateBasicInformation(BasicInformation information);

}

