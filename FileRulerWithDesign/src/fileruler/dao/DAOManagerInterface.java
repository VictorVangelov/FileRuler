package fileruler.dao;

import java.util.List;

public interface DAOManagerInterface<T> {
    public void openDBConn();
    public void addDataToDB(List<? extends T> coll);
    public void delete();
    public List<? extends T> selectAllRecords();
    public List<? extends T> selectSpecificRecords(String whereClause);
    public void closeDBConn();
}
