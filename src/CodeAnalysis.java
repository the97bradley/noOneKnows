import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.client.model.Filters;

public class CodeAnalysis extends GenericDAO{
	
	public CodeAnalysis() {
		super(COLLECTION_NAME);
		// TODO Auto-generated constructor stub
	}

	private static final String COLLECTION_NAME = "code_analysis";
	
	public Document findByFileAndCommit(long fileHash, String commit, Bson projection) {
		return findOne(Filters.and(Filters.eq("filehash", fileHash), Filters.eq("commit", commit)), projection);
	}

	public void deleteByReport(ObjectId report) {
		deleteMany(Filters.eq("analysis_report", report));
	}

	public List<Document> findByCommit(String commit,  Bson projection) {
		return findMany(Filters.eq("commit", commit), projection);
	}
	
	public List<Document> findByReport(ObjectId report, Bson projection) {
		return findMany(Filters.eq("analysis_report", report), projection);
	}

}
