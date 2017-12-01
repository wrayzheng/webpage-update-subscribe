import java.util.HashMap;
import java.util.Map;

import com.codebelief.app.mail.*;

public class EmailSendFacadeTest {
	public static void main(String[] args) throws InterruptedException {
		String recipient = "mczon@qq.com";
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("name", "何涛");
		SendMail.sendMail(recipient, parameters);
	}
}
