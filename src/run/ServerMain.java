package run;

import controller.*;
import domain.mediator.ServerModelManager;
import view.*;

public class ServerMain {

	public static void main(String[] args) {
		
		ServerModelManager model = new ServerModelManager();
		View view = new ServerView();
		Controller controller = new ServerController(model, view);
	}
}
