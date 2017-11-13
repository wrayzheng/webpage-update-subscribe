import java.util.HashMap;
import java.util.Map;

import com.codebelief.app.mail.*;

public class EmailSendFacadeTest {
	public static void main(String[] args) throws InterruptedException {
		String recipient = "2316367336@qq.com";
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("name", "何涛");
		SendMail.sendMail(recipient, parameters);
	}
}
