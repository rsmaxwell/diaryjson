import com.rsmaxwell.diary.json.Version;

public class App {

	public static void main(String[] args) throws Exception {
		System.out.println("version   = " + Version.version());
		System.out.println("buildID   = " + Version.buildID());
		System.out.println("buildDate = " + Version.buildDate());
		System.out.println("gitCommit = " + Version.gitCommit());
		System.out.println("gitBranch = " + Version.gitBranch());
		System.out.println("gitURL    = " + Version.gitURL());
	}
}
