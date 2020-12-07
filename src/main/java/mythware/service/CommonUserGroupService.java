package mythware.service;

import com.google.common.collect.ImmutableMap;
import mythware.dao.TestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonUserGroupService {

    @Autowired TestDao dao;

    private String groupTable = "TF_ITVAN_GROUP";
    private String importTable = "TF_ITVAN_GROUP_IMPORT";
    private String importTempTable = "TF_ITVAN_GROUP_IMPORT_";
    private String groupColumn = "GROUP_";
    private String anColumn = "ITV_AN";


    public void addImportFromTemp(String groupId, Integer importTableIndex) {
        String fromTable = importTempTable + importTableIndex;

        long importItvItemNum = dao.countTable(fromTable);
        if (importItvItemNum > 0) {
            ImmutableMap<String, String> dynamics = ImmutableMap.of("fromTable", fromTable, "toTable", importTable, "column", anColumn);

            long rows = 1000000; // 100w
            long l = importItvItemNum / rows;
            for (int i = 0; i < l + 1; i++) {
                long index = i * rows;
                ImmutableMap<String, Object> params = ImmutableMap.of("groupId", groupId, "index", index, "rows", rows);
                dao.addImportFromTemp(dynamics, params);
            }
        }
    }


}
