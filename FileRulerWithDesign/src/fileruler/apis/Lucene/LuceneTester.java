package fileruler.apis.Lucene;


import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import org.apache.lucene.document.Document;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;

public class LuceneTester {
	
	public String indexDir;
	public String dataDir;
	
	public LuceneTester(String indexDir,String dataDir) {
		// TODO Auto-generated constructor stub
		this.indexDir = indexDir;
		this.dataDir = dataDir;
	}
   //String indexDir = "/home/kal0ian/Downloads/IndexLuceneDir";
   //String dataDir = "/home/kal0ian/Downloads/DataLuceneDir";
   //String dataDirPath=System.getProperty("user.home");
   Indexer indexer;
   Searcher searcher;

   public static void main(String[] args) {
      LuceneTester tester;
      try {
    	 String indexDir = "D:\\test2";
    	 String dataDir = "D:\\test";
         tester = new LuceneTester(indexDir,dataDir);
         tester.createIndex();
         LinkedList<String> url = tester.search("encho");
         Iterator it = url.iterator();
         while(it.hasNext()){
        	 System.out.println(it.next());
         }
      } catch (IOException e) {
         e.printStackTrace();
      } catch (ParseException e) {
         e.printStackTrace();
      }
   }

   public void createIndex() throws IOException{
      indexer = new Indexer(indexDir);
      int numIndexed;
      numIndexed = indexer.createIndexOfFileSystem(dataDir, new TextFileFilter());
      indexer.close();	
   }

   public LinkedList<String> search(String searchQuery) throws IOException, ParseException{
      searcher = new Searcher(indexDir);
      TopDocs hits = searcher.search(searchQuery);
      LinkedList<String> urlsOfFiles = new LinkedList<String>();
      for(ScoreDoc scoreDoc : hits.scoreDocs) {
         Document doc = searcher.getDocument(scoreDoc);
            urlsOfFiles.add(doc.get(LuceneConstants.FILE_PATH));
      }
      searcher.close();
      return urlsOfFiles;
   }
}