package run;
import controller.*;
import domain.mediator.*;
import view.*;

public class ClientMain
{
   public static void main(String[] args)
   {
      Model model = new ClientProxy();
      
      View view = (View)new LoginView();
      
      Controller controller = new ClientController(model, view);
      
      view.start(controller);
   }
}
